package properties;

import java.util.ResourceBundle;

public class GetResourceBundle {
	
	public String getValueFromCredential(String key) {
		return ResourceBundle.getBundle("properties.credentials").getString(key);
	}
	
	public String getValueFromEnvironment(String key) {
		return ResourceBundle.getBundle("properties.env").getString(key);
	}
	
	public String getVersionFromPOM(String key) {
		return ResourceBundle.getBundle("properties.report").getString(key);
	}
}