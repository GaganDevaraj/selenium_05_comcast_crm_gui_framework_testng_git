package com.comcast.crm.listner;

import java.lang.reflect.Method;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;

public class TS_04_NetworkTest extends BaseClass {
	
	@Test (retryAnalyzer = com.comcast.crm.generic.listenerutility.RetryListenerImpl.class)
	public void activateSimTest(Method mtd) {
		System.out.println("execute "+mtd.getName());
		AssertJUnit.assertEquals("", "Home");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}

