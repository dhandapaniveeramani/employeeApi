package runnerClassConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@CucumberOptions(

		features="src/test",
glue="",

		
tags= {},plugin= {

		 
				  "pretty:target/prettyReport.txt", "html:target/cucumber",
				  "json:target/cucumber.json", "rerun:target/rerun.txt",
				  "junit:target/junit-report.xml", "testng:target/testng-output.xml" ,
				 
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-extent-reports/report.html"},

monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests{
	
}