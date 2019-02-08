package com.everis.sonar.util;

import com.everis.sonar.data.Settings;

/**
 * Main sonar.
 * 
 * @author gsaravia
 */
public class Sonar {

	private Sonar() {
		// Do noting.
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		Settings settings = null;

		if (args.length == 7)
			settings = parseSettings(args);
		else
			settings = SettingsUtil.LoadSettingsFromProp("sonar.properties");

		IssuesHelper util = new IssuesHelper(settings);
		util.getSonarIssue();

		if (settings.isCoverageEnabled()) {
			CoverageHelper cov = new CoverageHelper(settings, util.getClient());
			cov.coverage();
		}
	}

	private static Settings parseSettings(String[] args) {
		if (args.length != 7)
			return null;
		// else
		Settings settings = new Settings();
		settings.setUrlApi(args[0]);// sonar.url
		settings.setUser(args[1].isEmpty() ? null : args[1]);// sonar.user
		settings.setPassword(args[2].isEmpty() ? null : args[2]);// sonar.password
		settings.setProxyHost(args[3].isEmpty() ? null : args[3]);// sonar.proxyUrl
		settings.setProxyPort(args[4].isEmpty() ? null : args[4]);// sonar.proxyPort
		settings.setCoverageEnabled(args[5]);// sonar.coverage.enabled
		settings.setRuleSet(args[6].isEmpty() ? null : args[6]);// sonar.rules

		return settings;
	}
}
