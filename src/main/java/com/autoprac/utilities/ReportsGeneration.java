package com.autoprac.utilities;

import org.automationtesting.excelreport.Xl;

import com.autoprac.base.Base;
import com.autoprac.config.ObjectRespo;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ReportsGeneration extends Base{


	//HTML Reports
	public static void htmlReports() {
		extent = new ExtentReports(ObjectRespo.htmlReport,true);
			
	}


	//Excel Reports
	public static void excelReports() throws Exception {
		Xl.generateReport(ObjectRespo.reportsPath, ObjectRespo.excelReport);
	}


	//All reports generate
	public static void generateReports() throws Exception {
		extent.flush();
		excelReports();
	}

}
