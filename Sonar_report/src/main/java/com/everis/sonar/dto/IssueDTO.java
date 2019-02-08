/**
 * 
 */
package com.everis.sonar.dto;

import java.io.Serializable;

/**
 * The Class IssueDTO.
 *
 * @author gsaravia
 */
public class IssueDTO implements Serializable {

	private static final String SEPARATOR = ";";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2553915009182111914L;

	/** The key. */
	private String key;

	/** The severity. */
	private String severity;

	/** The project. */
	private String project;

	/** The sub project. */
	private String subProject;

	/** The status. */
	private String status;

	/** The component. */
	private String component;

	/** The author. */
	private String author;

	private Integer line;

	/** The creation date. */
	private String creationDate;

	/** The update date. */
	private String updateDate;

	/** The close date. */
	private String closeDate;

	/** The rule. */
	private String rule;

	/** The effort. */
	private String effort;

	/** The type. */
	private String type;

	private String message;

	/**
	 * Prints the line.
	 *
	 * @return the string
	 */
	public String printLine() {

		StringBuilder builder = new StringBuilder();

		builder.append(this.getKey()).append(SEPARATOR);

		builder.append(this.getProject()).append(SEPARATOR);
		builder.append(this.getSub()).append(SEPARATOR);
		builder.append(this.getSeverity()).append(SEPARATOR);
		builder.append(this.getRule()).append(SEPARATOR);
		builder.append(this.getName()).append(SEPARATOR);
		builder.append(this.getLine() == null ? "" : this.line).append(SEPARATOR);
		builder.append(this.getAuthor()).append(SEPARATOR);
		builder.append(formatDate(this.getCreationDate())).append(SEPARATOR);
		builder.append(formatDate(this.getUpdateDate())).append(SEPARATOR);
		builder.append(formatDate(this.getCloseDate())).append(SEPARATOR);
		builder.append(this.getStatus()).append(SEPARATOR);
		builder.append(this.getEffort()).append(SEPARATOR);
		builder.append(this.getType()).append(SEPARATOR);
		builder.append(this.getMessage());

		return builder.toString();
	}

	private String getName() {
		if (this.component == null)
			return "";

		String[] aCom = this.component.split(":");
		return aCom[aCom.length - 1].replace("/", ".");
	}

	private String getSub() {
		if (this.subProject == null)
			return "";

		String[] aCom = this.subProject.split(":");
		return aCom[aCom.length - 1];
	}

	private String formatDate(String sdate) {
		if (sdate == null)
			return "";

		String[] aFecha = sdate.substring(0, 10).split("-");
		String fdate = String.format("%s-%s-%s", aFecha[2], aFecha[1], aFecha[0]);
		return fdate;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the rule.
	 *
	 * @return the rule
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * Sets the rule.
	 *
	 * @param rule
	 *            the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

	/**
	 * Gets the severity.
	 *
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * Sets the severity.
	 *
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * Gets the sub project.
	 *
	 * @return the subProject
	 */
	public String getSubProject() {
		return subProject;
	}

	/**
	 * Sets the sub project.
	 *
	 * @param subProject
	 *            the subProject to set
	 */
	public void setSubProject(String subProject) {
		this.subProject = subProject;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the component.
	 *
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * Sets the component.
	 *
	 * @param component
	 *            the component to set
	 */
	public void setComponent(String component) {
		this.component = component;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the update date.
	 *
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the update date.
	 *
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Gets the close date.
	 *
	 * @return the closeDate
	 */
	public String getCloseDate() {
		return closeDate;
	}

	/**
	 * Sets the close date.
	 *
	 * @param closeDate
	 *            the closeDate to set
	 */
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	/**
	 * @return the line
	 */
	public Integer getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(Integer line) {
		this.line = line;
	}

	/**
	 * @return the effort
	 */
	public String getEffort() {
		return effort;
	}

	/**
	 * @param effort
	 *            the effort to set
	 */
	public void setEffort(String effort) {
		this.effort = effort;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
