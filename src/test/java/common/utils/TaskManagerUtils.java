package common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskManagerUtils {
	public static boolean isRunning(String process) {
		boolean found = false;
		try {
			File file = File.createTempFile("realhowto",".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
					+ "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
					+ "Set service = locator.ConnectServer()\n"
					+ "Set processes = service.ExecQuery _\n"
					+ " (\"select * from Win32_Process where name='" + process +"'\")\n"
					+ "For Each process in processes\n"
					+ "wscript.echo process.Name \n"
					+ "Next\n"
					+ "Set WSHShell = Nothing\n";

			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input =
					new BufferedReader
					(new InputStreamReader(p.getInputStream()));
			String line;
			line = input.readLine();
			if (line != null) {
				if (line.equals(process)) {
					found = true;
				}
			}
			input.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return found;
	}

	public static void killProcess(String process) throws IOException, InterruptedException { 
		boolean result = isRunning(process);

		while(result) {
			Runtime.getRuntime().exec("taskkill /F /IM " + process);
			Thread.sleep(2000);
			result = isRunning(process);
		}
	}
}
