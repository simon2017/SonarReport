/**
 * 
 */
package com.everis.sonar.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class CoverageDTO.
 *
 * @author gsaravia
 */
public class CoverageDTO implements Serializable {

	private static final String SEPARATOR = ";";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3662128053033081161L;

	/** The id. */
	private Integer id;
	
	/** The key. */
	private String key;
	
	/** The qualifier. */
	private String qualifier;
	
	/** The date. */
	private String date;
	
	/** The creation date. */
	private String creationDate;
	
	/** The msr. */
	private List<MsgDTO> msr;

	/**
	 * Prints the line.
	 *
	 * @return the string
	 */
	public String printLine(){
		StringBuilder builder = new StringBuilder();
		
		builder.append(id).append(SEPARATOR);
		builder.append(key.replace("everis.", "")).append(SEPARATOR);
		getDateStr(date, builder);
		getDateStr(creationDate, builder);
				
		Map<String, Double> mapa = new HashMap<String, Double>();
		for(MsgDTO dto : msr){
			mapa.put(dto.getKey(), dto.getVal());
		}
		
		builder.append(getByMap("tests", mapa)).append(SEPARATOR);
		builder.append(getByMap("test_success_density", mapa)).append(SEPARATOR);
		builder.append(getByMap("test_errors", mapa) + getByMap("test_failures", mapa)).append(SEPARATOR);
		builder.append(getByMap("ncloc", mapa)).append(SEPARATOR);
		builder.append(getByMap("coverage", mapa));
		
		return builder.toString().replace(".", ",");
	}
	
	private Double getByMap(String key, Map<String, Double> mapa){
		return mapa.get(key) == null ? 0D : mapa.get(key);
	}
	
	private void getDateStr(String unformat, StringBuilder builder){
		
		if(unformat != null){
			String[] aFecha = unformat.substring(0, 10).split("-");
			builder.append(aFecha[2]).append("-").append(aFecha[1]).append("-").append(aFecha[0]).append(SEPARATOR);
		}else{
			builder.append(SEPARATOR);
		}
		
	}
	
	private String getDateStr(String unformat){
		
		if(unformat != null){
			String[] aFecha = unformat.substring(0, 10).split("-");
			return aFecha[2] + "-" + aFecha[1] + "-" + aFecha[0];
		}
		
		return "null";
	}
	
	public String sqlLine(){
		
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append("nextval('h_sonar.h_coverage_seq')").append(", ");
		builder.append("now()").append(", ");
		builder.append(id).append(", ");
		builder.append("'").append(key.replace("everis.", "")).append("'").append(", ");
		builder.append("'").append(getDateStr(date)).append("'").append(", ");
		builder.append("'").append(getDateStr(creationDate)).append("'").append(", ");
		
		
		
		Map<String, Double> mapa = new HashMap<String, Double>();
		for(MsgDTO dto : msr){
			mapa.put(dto.getKey(), dto.getVal());
		}
		
		builder.append(getByMap("tests", mapa)).append(", ");
		builder.append(getByMap("test_success_density", mapa)).append(", ");
		builder.append(getByMap("test_errors", mapa) + getByMap("test_failures", mapa)).append(", ");
		builder.append(getByMap("ncloc", mapa)).append(", ");
		builder.append(getByMap("coverage", mapa));
		
		
		builder.append(")");
		
		return builder.toString();
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the qualifier
	 */
	public String getQualifier() {
		return qualifier;
	}

	/**
	 * @param qualifier the qualifier to set
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the msr
	 */
	public List<MsgDTO> getMsr() {
		return msr;
	}

	/**
	 * @param msr the msr to set
	 */
	public void setMsr(List<MsgDTO> msr) {
		this.msr = msr;
	}
	
	
	
}
