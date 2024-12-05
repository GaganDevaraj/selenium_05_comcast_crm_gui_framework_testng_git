package com.comcast.crm.listner;

import java.lang.reflect.Method;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

@Listeners (com.comcast.crm.generic.listenerutility.ListnerImplClass.class)

public class TS_05_NetworkTest extends BaseClass {
	
	@Test
	public void activateSimTest(Method mtd) {

		ExtentTest extentTest = UtilityClassObject.getExtentTest();
		
		extentTest.log(Status.INFO, "execute " + mtd.getName());
		extentTest.log(Status.INFO, "Step-1");
		extentTest.log(Status.INFO, "Step-2");
		if ("test".equals("Test")) {
			extentTest.log(Status.PASS, "activateSimTest PASS");
		} else {
			extentTest.log(Status.FAIL, "activateSimTest FAIL");
		}
		extentTest.log(Status.INFO, "Step-3");
		extentTest.log(Status.INFO, "Step-4");
	}
}

