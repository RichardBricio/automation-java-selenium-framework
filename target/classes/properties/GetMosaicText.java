package properties;

import java.util.ResourceBundle;
/**
 * @author Fernando Castro
 */
public class GetMosaicText {

	public String getValueFromMosaicCode(String key) {
		return ResourceBundle.getBundle("properties.codMosaic").getString(key);
	}
	
}