package common.report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import properties.GetResourceBundle;
import steps.common.CommonSteps;
import steps.common.LoginSteps;

public class ReportJson {

	public static void generateFinalReport(String jsonInputDir, String reportOutputDir, String projectName){

		List<String> jsonFiles = new ArrayList<String>();
		GetResourceBundle props = new GetResourceBundle();

		try{
			Path pp = Paths.get(new File(jsonInputDir).getAbsolutePath());
			Files.walk(pp)
					.filter(path -> !Files.isDirectory(path))
					.forEach(path -> {
						jsonFiles.add(path.toAbsolutePath().toString());
					});

		} catch (Exception e) {
			System.err.println(e);
		}

		Configuration configuration = new Configuration(new File(reportOutputDir), projectName);
		// addidtional metadata presented on main page
		configuration.setBuildNumber(props.getVersionFromPOM("version"));
		configuration.addClassifications("Platform", "Windows");
		configuration.addClassifications("Browser", CommonSteps.brw);
		configuration.addClassifications("Enviroment", LoginSteps.environment);
		configuration.addClassifications("Branch", "Dev");


		configuration.setSortingMethod(SortingMethod.NATURAL);
		//Descomment to show all tests results expanded
		//configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
		configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
		configuration.setQualifier("sample", "Some Qualifier");
		// points to the demo trends which is not used for other tests
		configuration.setTrendsStatsFile(new File("target/test-classes/trends.json"));

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);

		reportBuilder.generateReports();

		// and here validate 'result' to decide what to do
		// if report has failed features, undefined steps etc
	}
}
