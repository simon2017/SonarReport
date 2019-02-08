/**
 * 
 */
package com.everis.sonar.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.everis.sonar.data.Settings;
import com.everis.sonar.dto.CoverageDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author gsaravia
 *
 */
public class CoverageHelper implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4214691471759862674L;

	private FileWriter fileWriter;

	private Gson gson = new Gson();

	/** The sql insert. */
	private StringBuilder sqlInsert = new StringBuilder();

	private Settings settings;
	private Client client;

	public CoverageHelper(Settings settings, Client client) {
		this.settings = settings;
		this.client = client;
	}

	/**
	 * Coverage.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void coverage() throws Exception {

		Long ini = System.currentTimeMillis();

		File cFileTmp = new File("tmp" + ini + "file");

		fileWriter = new FileWriter(cFileTmp, false);
		fileWriter.write(
				"id1;nombre_proyecto1;Fecha Modificacion1;Fecha Inicio1;Total de Test;Porcentaje exito;Tests con errores;Lineas de codigo;Cobertura\n");

		try {
			this.createCoverage();
		} catch (Exception e) {
			fileWriter.close();
			cFileTmp.delete();
		}

		fileWriter.close();

		Long fin = System.currentTimeMillis();
		Long min = (fin - ini) / (1000 * 60);

		File fileCoverage = new File("coverage.csv");
		System.out.println("Copying new coverage");
		cFileTmp.renameTo(fileCoverage);
		System.out.println("Coverage finished in " + min + " minutes");
	}

	private void createCoverage() throws IOException, ClassNotFoundException, SQLException, InterruptedException {

		String url = settings.getUrlApi()
				+ "metrics=ncloc,coverage,tests,test_failures,test_errors,test_success_density";

		List<CoverageDTO> lista = this.getData(url);

		if (lista != null && !lista.isEmpty()) {

			sqlInsert.append(
					"INSERT INTO h_sonar.h_coverage (h_coverage_id, h_date_insert, h_id_excentia, h_project_name, h_init_date, h_upd_date, h_total_test, h_success_per, h_failed_test, h_code_line, h_coverage) VALUES ");

			StringBuilder builder = new StringBuilder();
			for (CoverageDTO dto : lista) {
				builder.append(dto.printLine()).append("\n");
				sqlInsert.append(dto.sqlLine()).append(", ");
			}
			fileWriter.write(builder.toString());
			saveLocalBd();
		}

	}

	private List<CoverageDTO> getData(String url) throws InterruptedException {
		try {
			WebResource resourse = client.resource(url);
			resourse.accept("application/json;charset=utf-8");

			ClientResponse response = resourse.get(ClientResponse.class);

			return gson.fromJson(response.getEntity(String.class), new TypeToken<ArrayList<CoverageDTO>>() {
			}.getType());

		} catch (Exception e) {
			System.out.println("Exception while getting covegare, The system will try again in 5 second");
			Thread.sleep(5000L);
			return getData(url);
		}
	}

	private void saveLocalBd() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sonar", "postgres",
				"Sonar2016");

		Statement stmt = connection.createStatement();

		String sqlExist = "select count(h_coverage_id) from h_sonar.h_coverage where date_trunc('day', h_date_insert) = date_trunc('day', now());";

		ResultSet result = stmt.executeQuery(sqlExist);

		if (result.next() && result.getInt(1) == 0) {
			System.out.println("Inserting data in local data base");
			String sql = sqlInsert.toString().substring(0, sqlInsert.toString().length() - 2) + ";";
			stmt.executeUpdate(sql);
		} else {
			System.out.println("The result already exist");
		}

		stmt.close();

		connection.close();

	}

}
