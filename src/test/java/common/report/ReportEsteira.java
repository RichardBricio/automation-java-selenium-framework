package common.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class ReportEsteira {
	
	
	public static void generateCucumberReportBasedOnTags(String tags){
        String TEST_FOLDER = System.getProperty("user.dir") + "/src/main/resources/";

        String reportDirName = "Run";
        String reportRoot = "cucumber-html-reports/";
        String reportDir = reportRoot + reportDirName + "/";
        reportDirName = reportDirName.replace(" ", "");
        reportDir = reportDir.replace(" ", "");

        Configurator.initialize(new DefaultConfiguration());
        if((new File(reportRoot).exists())){
            try {
                FileUtils.deleteDirectory(new File(reportRoot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String glue = "steps";
        String hook = "hooks";
        String[] plugins = { "pretty", "json:" + reportDir + reportDirName + ".json"};
        String[] arguments = { "-m", "-p", plugins[0], "-p", plugins[1], "-g", glue, "-g", hook, TEST_FOLDER };
        String[] newTag = new String[]{tags};
        for(String tag:newTag) {
            arguments = ArrayUtils.add(arguments, "-t");
            arguments = ArrayUtils.add(arguments, tag);
        }

        try {
        	io.cucumber.core.cli.Main.run(arguments, Thread.currentThread().getContextClassLoader());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ReportJson.generateFinalReport(reportDir, reportDir, "Concentre");
        String reportAbsPath = new File(reportDir).getAbsolutePath();
        String report = reportAbsPath + "/cucumber-html-reports/overview-features.html";

        System.out.println("REPORT GENERATED: " + report);
    }

    public static void runTestBasesOnTags(String tag){
        String features = tag;
        generateCucumberReportBasedOnTags(features.substring(0, features.length()));
    }

}
