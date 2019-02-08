/**
 * 
 */
package com.everis.sonar.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DataDTO.
 *
 * @author gsaravia
 */
public class DataDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5772669715173838882L;

	/** The total. */
	private Integer total;
	
	/** The p. */
	private Integer p;
	
	/** The ps. */
	private Integer ps;
	
	/** The paging. */
	private List<IssueDTO> issues;

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * Gets the issues.
	 *
	 * @return the issues
	 */
	public List<IssueDTO> getIssues() {
		return issues;
	}

	/**
	 * Sets the issues.
	 *
	 * @param issues the issues to set
	 */
	public void setIssues(List<IssueDTO> issues) {
		this.issues = issues;
	}

	/**
	 * @return the p
	 */
	public Integer getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Integer p) {
		this.p = p;
	}

	/**
	 * @return the ps
	 */
	public Integer getPs() {
		return ps;
	}

	/**
	 * @param ps the ps to set
	 */
	public void setPs(Integer ps) {
		this.ps = ps;
	}



}
