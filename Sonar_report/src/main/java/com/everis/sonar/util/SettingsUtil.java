/**
 * 
 */
package com.everis.sonar.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.everis.sonar.data.Settings;

/**
 * @author sgutierc
 *
 */
public class SettingsUtil {

	/**
	 * 
	 */
	public SettingsUtil() {
	}

	public static Settings LoadSettingsFromProp(String propertiesPath) {
		FileInputStream in;
		Settings settings = new Settings();
		try {
			in = new FileInputStream(new File(propertiesPath));
			Properties properties = new Properties();
			properties.load(in);
			in.close();

			settings.setUrlApi(properties.getProperty("sonar.url"));
			settings.setUser(properties.getProperty("sonar.user"));
			settings.setPassword(properties.getProperty("sonar.password"));
			settings.setProxyHost(properties.getProperty("sonar.proxyUrl"));
			settings.setProxyPort(properties.getProperty("sonar.proxyPort"));
			settings.setCoverageEnabled(Boolean.valueOf(properties.getProperty("sonar.coverage.enabled")));
			settings.setRuleSet(properties.getProperty("sonar.rules"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return settings;
	}

}
