package com.serasa.properties;

import java.util.ResourceBundle;

public class GetResourceBundle {
	
	public String getValueFromCredential(String key) {
		return ResourceBundle.getBundle("com.serasa.properties.credentials").getString(key);
	}
	
	public String getValueFromEnvironment(String key) {
		return ResourceBundle.getBundle("com.serasa.properties.env").getString(key);
	}
	
	public String getVersionFromPOM(String key) {
		return ResourceBundle.getBundle("com.serasa.properties.report").getString(key);
	}
}