/**
 * 
 */
package com.everis.sonar.dto;

import java.io.Serializable;

/**
 * The Class MsgDTO.
 *
 * @author gsaravia
 */
public class MsgDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7594296811432651995L;
	
	/** The key. */
	private String key;
	
	/** The val. */
	private Double val;

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
	 * @return the val
	 */
	public Double getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(Double val) {
		this.val = val;
	}
	
	

}
