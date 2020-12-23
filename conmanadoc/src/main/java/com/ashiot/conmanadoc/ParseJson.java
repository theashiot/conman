package com.ashiot.conmanadoc;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
		ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("con-template.json");
        
        if (inputStream == null) {
        	System.out.println("not found");
        }
        else {
        	InputStreamReader inputStreamReader = new InputStreamReader (inputStream);
        	StringBuffer strBuffer = new StringBuffer();
        	try {
        	BufferedReader reader = new BufferedReader(inputStreamReader);
                 String line;
                 while ((line = reader.readLine()) != null) {
             			strBuffer.append(line);
                 }
           
           
   		
           
           
        	JSONObject jsonObj = (JSONObject) JSONValue.parse(strBuffer.toString());	
            String module = "hello world";
            StringBuffer sb = new StringBuffer();
            for (char c : module.toCharArray()) {
    			if (!(Character.isLetter(c)) && !(Character.isDigit(c))) {
    				sb.append(jsonObj.get("wordSeparator"));
    			}
    			else {
    				sb.append(c);
    			}
            }
           
           System.out.println(sb.toString());
           
           StringBuffer fileName = new StringBuffer ();
           fileName.append(jsonObj.get("prefix"));
           fileName.append(jsonObj.get("prefixSeperator"));
           fileName.append(sb.toString());
           fileName.append(jsonObj.get("fileExtension"));
           
           String fileNameFormat = jsonObj.get("fileNameFormat").toString(); 
           
           String processePrefix = fileNameFormat.replace("{prefix}", jsonObj.get("prefix").toString());
           String processPrefixeSeperator = processePrefix.replace("{prefixSeperator}", jsonObj.get("prefixSeperator").toString());
           String processStub = processPrefixeSeperator.replace("{<stub>}", sb.toString());
           String processFileName = processStub.replace("{fileExtension}", jsonObj.get("fileExtension").toString());
           System.out.println(fileName.toString());
           
           System.out.println (processFileName);

           
        	} catch (Exception e) {}
        } 

	}
}
