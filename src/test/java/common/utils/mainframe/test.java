package common.utils.mainframe;

import org.junit.Assert;


public class test extends Mainframe {

	public static void main(String[] args) {
		mfConnect("pw3270:A", "192.168.101.1:1025", 10);
		setText(23, 7, "AC");
		Enter();
		System.out.println(getRevision());
		System.out.println(getText(14, 21, 36));
		System.out.println(getScreen()); 
		pfKey(3);
		disconnect();
	}
}