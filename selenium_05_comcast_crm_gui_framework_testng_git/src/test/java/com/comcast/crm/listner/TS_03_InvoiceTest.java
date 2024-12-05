package com.comcast.crm.listner;

import java.lang.reflect.Method;

import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;


//if we are declaring the listener on suite level, then no need to define listener here
@Listeners (com.comcast.crm.generic.listenerutility.ListnerImplClass.class)
public class TS_03_InvoiceTest extends BaseClass {
	
	@Test
	public void createInvoiceTest(Method mtd) {
		System.out.println("execute "+mtd.getName());
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Home");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	
	@Test
	public void createInvoiceWithContactTest(Method mtd) {
		System.out.println("execute "+mtd.getName());
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}

