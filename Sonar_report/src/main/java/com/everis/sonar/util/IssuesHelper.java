/**
 * 
 */
package com.everis.sonar.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

import com.everis.sonar.data.Settings;
import com.everis.sonar.dto.DataDTO;
import com.everis.sonar.dto.IssueDTO;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

/**
 * @author gsaravia
 *
 */
public class IssuesHelper {

	private FileWriter fileWriter;

	private Client client;

	private Gson gson = new Gson();
	private Settings settings;

	public IssuesHelper(Settings settings) {
		this.settings = settings;
	}

	public void getSonarIssue() throws Exception {
		client = createClient();

		Long ini = System.currentTimeMillis();
		// Se crea el fichero de salida
		File tmpFile = new File("tmp" + ini + "file");

		fileWriter = new FileWriter(tmpFile, false);
		fileWriter.write("Id;Proyecto;Modulo;Criticidad;Regla;Clase;Linea;Autor;"
						+ "Fecha Inicio;Fecha Modificacion;Fecha Fin;Estado;Esfuerzo;Tipo;Mensaje"
						+ "\n");
		// Se obtiene la data.
		String[] rules = { "" };
		if (settings.getRuleSet() != null && !settings.getRuleSet().isEmpty()) {
			rules = settings.getRuleSet().split(",");
		}

		for (int i = 0; i < rules.length; i++) {
			try {
				this.createSonarIssue(1, 100, rules[i]);
			} catch (Exception e) {
				System.out.println("Error catched when parsing Sonar Issues.. will check next rule");
			}
		}

		fileWriter.close();
		

		File resultFile = new File("sonar.csv");

		System.out.println("Copying new data");
		if (tmpFile.renameTo(resultFile)) {
			System.out.println("Clean temporal data");
			tmpFile.deleteOnExit();
		}
		Long min = (System.currentTimeMillis() - ini) / (1000 * 60);
		System.out.println("Process finished in " + min + " minutes");
	}

	/**
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param rule
	 * @throws Exception
	 */
	private void createSonarIssue(Integer pageIndex, Integer pageSize, String rule) throws Exception {

		String extra = "";
		if (rule != null && !rule.isEmpty()) {
			extra = "rules=" + rule.replace(":", "%3A") + "&";
		}

		String url = settings.getUrlApi() + extra + "pageIndex=" + pageIndex + "&pageSize=" + pageSize;

		DataDTO data = this.getData(url);
		List<IssueDTO> lista = data.getIssues();

		StringBuilder builder = new StringBuilder();
		for (IssueDTO dto : lista) {
			builder.append(dto.printLine()).append("\n");
		}

		fileWriter.write(builder.toString());

		if ((data.getP() * data.getPs()) < data.getTotal()) {
			this.createSonarIssue(data.getP() + 1, pageSize, rule);
		}

	}

	private DataDTO getData(String url) throws InterruptedException {
		try {
			WebResource resourse = client.resource(url);
			resourse.accept("application/json;charset=utf-8");

			ClientResponse response = resourse.get(ClientResponse.class);

			return gson.fromJson(response.getEntity(String.class), DataDTO.class);
		} catch (Exception e) {
			System.out.println("Exception while getting data, The system will try again in 2 second");
			Thread.sleep(2000L);
			return getData(url);
		}
	}

	public Client getClient() {
		if (client != null) {
			return client;
		} else {
			return createClient();
		}
	}

	private Client createClient() {
		ClientConfig config = new DefaultClientConfig();

		URLConnectionClientHandler handler = new URLConnectionClientHandler(getConnection());

		this.client = new Client(handler, config);

		if (settings.getUser() != null && !"".equals(settings.getUser())) {
			client.addFilter(new HTTPBasicAuthFilter(settings.getUser(), settings.getPassword()));
			client.addFilter(new LoggingFilter());
		}

		return client;
	}

	private HttpURLConnectionFactory getConnection() {
		if (settings.getProxyHost() != null && "".equals(settings.getProxyHost()) == false) {
			Proxy prox = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(settings.getProxyHost(),
					Integer.valueOf(settings.getProxyPort()).intValue()));
			return new ConnectionImp(prox);
		} else {
			return new ConnectionImp();
		}
	}

	/**
	 * 
	 * @author sgutierc
	 *
	 */
	public class ConnectionImp implements HttpURLConnectionFactory {
		Proxy proxy;

		public ConnectionImp(Proxy proxy) {
			this.proxy = proxy;
		}

		public ConnectionImp() {

		}

		public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
			HttpURLConnection connection = null;
			if (proxy != null)
				connection = (HttpURLConnection) url.openConnection(proxy);
			else
				connection = (HttpURLConnection) url.openConnection();

			return connection;
		}

	}

}
