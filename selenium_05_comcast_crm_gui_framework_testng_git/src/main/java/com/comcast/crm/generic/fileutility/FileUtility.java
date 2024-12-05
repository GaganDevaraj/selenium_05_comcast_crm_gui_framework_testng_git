package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertyFile(String key) throws Exception {
		FileInputStream fin = new FileInputStream("configAppData/common_data.properties");
		Properties properties = new Properties();
		properties.load(fin);
		String value = properties.getProperty(key);
		return value;
	}
}
