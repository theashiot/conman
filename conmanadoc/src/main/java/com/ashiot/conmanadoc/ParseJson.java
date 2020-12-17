package com.ashiot.conmanadoc;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class ParseJson {
	
	
	JSONObject obj=new JSONObject();    
	
	//@SuppressWarnings("unchecked")
	ParseJson () {
		//String jsonString = "{\"document\": {\"type\":\"concept\",\"hello\":\"world\"}}";
		String jsonString = "{\"prefix\" : \"con\",\"prefixSeperator\" : \"-\",\"wordSeperator\" : \"-\"}";
		JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString); 
		String stub = "hello world";
		StringBuffer stringBuffer = new StringBuffer();
		for (char c : stub.toCharArray()) {
			//ch = new Character (c)
			if (!(Character.isLetter(c)) && !(Character.isDigit(c))) {
				stringBuffer.append(jsonObject.get("wordSeperator"));
			}
			else {
				stringBuffer.append(c);
			}
		}
		
		System.out.println(stringBuffer.toString());
	}
}
