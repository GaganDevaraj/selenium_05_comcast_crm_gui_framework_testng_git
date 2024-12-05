package com.comcast.crm.generic.fileutility;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws Exception {

		FileReader fileReader = new FileReader("configAppData/appCommonData.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fileReader);
		JSONObject jsonObject = (JSONObject) obj;
		String value = (String) jsonObject.get(key);
		return value;
	}
}
