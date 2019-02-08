/**
 * 
 */
package com.everis.sonar.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PagingDTO.
 *
 * @author gsaravia
 */
public class PagingDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3471045214987990849L;
	
	/** The page index. */
	private Integer pageIndex;
	
	/** The page size. */
	private Integer pageSize;
	
	/** The total. */
	private Integer total;
	
	/** The issues. */
	private List<IssueDTO> issues;

	/**
	 * @return the pageIndex
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the issues
	 */
	public List<IssueDTO> getIssues() {
		return issues;
	}

	/**
	 * @param issues the issues to set
	 */
	public void setIssues(List<IssueDTO> issues) {
		this.issues = issues;
	}

}
