/**
 * 
 */
package com.everis.sonar.data;

/**
 * @author sgutierc
 *
 */
public class Settings {

	private String UrlApi;
	private String User;
	private String Password;
	private String ProxyHost;
	private String ProxyPort;
	private boolean CoverageEnabled;
	private String RuleSet;

	public Settings() {
	};

	/**
	 * @param urlApi
	 * @param user
	 * @param password
	 * @param proxyHost
	 * @param proxyPort
	 * @param coverageEnabled
	 * @param ruleSet
	 */
	public Settings(String urlApi, String user, String password, String proxyHost, String proxyPort,
			boolean coverageEnabled, String ruleSet) {
		UrlApi = urlApi;
		User = user;
		Password = password;
		ProxyHost = proxyHost;
		ProxyPort = proxyPort;
		CoverageEnabled = coverageEnabled;
		RuleSet = ruleSet;
	}

	/**
	 * @return the urlApi
	 */
	public String getUrlApi() {
		return UrlApi;
	}

	/**
	 * @param urlApi
	 *            the urlApi to set
	 */
	public void setUrlApi(String urlApi) {
		UrlApi = urlApi;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return User;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		User = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * @return the proxyHost
	 */
	public String getProxyHost() {
		return ProxyHost;
	}

	/**
	 * @param proxyHost
	 *            the proxyHost to set
	 */
	public void setProxyHost(String proxyHost) {
		ProxyHost = proxyHost;
	}

	/**
	 * @return the proxyPort
	 */
	public String getProxyPort() {
		return ProxyPort;
	}

	/**
	 * @param proxyPort
	 *            the proxyPort to set
	 */
	public void setProxyPort(String proxyPort) {
		ProxyPort = proxyPort;
	}

	/**
	 * @return the coverageEnabled
	 */
	public boolean isCoverageEnabled() {
		return CoverageEnabled;
	}

	/**
	 * @param coverageEnabled
	 *            the coverageEnabled to set
	 */
	public void setCoverageEnabled(boolean coverageEnabled) {
		CoverageEnabled = coverageEnabled;
	}

	/**
	 * @param coverageEnabled
	 *            the coverageEnabled to set
	 */
	public void setCoverageEnabled(String coverageEnabled) {
		setCoverageEnabled(Boolean.valueOf(CoverageEnabled));
	}

	/**
	 * @return the ruleSet
	 */
	public String getRuleSet() {
		return RuleSet;
	}

	/**
	 * @param ruleSet
	 *            the ruleSet to set
	 */
	public void setRuleSet(String ruleSet) {
		RuleSet = ruleSet;
	}

}
