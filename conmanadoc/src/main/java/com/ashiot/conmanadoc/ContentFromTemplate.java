package com.ashiot.conmanadoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ContentFromTemplate {

	String fileName;
	String id;
	String moduleName;
	String moduleType;
	private File contentFile;
	
	String templateFile;

	ContentFromTemplate (String moduleName, String moduleType) {
		this.moduleName = moduleName;
		this.moduleType = moduleType;
		
		switch (moduleType) {
			case "con" : templateFile = "con-template.json";
						 break;
			case "proc" : templateFile = "proc-template.json";
			 			  break;
			case "ref" : templateFile = "ref-template.json";
			 			 break;
		}
	}
	
	public void getValuesFromTemplate ()
	{
		System.out.println("Templalte file is: " + templateFile);
	
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(templateFile);
		InputStreamReader inputStreamReader;
		StringBuffer stringBuffer;
		String line;
		
		if (!(inputStream == null)) {
			inputStreamReader = new InputStreamReader (inputStream);
			stringBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(inputStreamReader);
			System.out.println("Created BufferedReader");
			try {
				while ((line = reader.readLine()) != null) {
						stringBuffer.append(line);
				}
				
				JSONObject jsonObj = (JSONObject) JSONValue.parse(stringBuffer.toString());
				String wordSeperator = jsonObj.get("wordSeperator").toString();
				String stub = this.createStub(this.moduleName, wordSeperator);
				
				/*StringBuffer fileNameBuffer = new StringBuffer();
				fileNameBuffer.append(jsonObj.get("fileNameFormat"));
				fileNameBuffer.toString().replace("{prefix}",jsonObj.get("prefix").toString());
				fileNameBuffer.toString().replace("{prefixSepetaror}",jsonObj.get("prefixSeperator").toString());
				fileNameBuffer.toString().replace("{<stub>}",stub);
				fileNameBuffer.toString().replace("{fileExtension}", jsonObj.get("fileExtension").toString());
				*/
				
				String fileNameFormat = jsonObj.get("fileNameFormat").toString(); 
				String idFormat = jsonObj.get("idFormat").toString(); 
				
		        String processePrefix = fileNameFormat.replace("{prefix}", jsonObj.get("prefix").toString());
		        String processPrefixeSeperator = processePrefix.replace("{prefixSeperator}", jsonObj.get("prefixSeperator").toString());
		        String processStub = processPrefixeSeperator.replace("{<stub>}", stub);
		        String processFileName = processStub.replace("{fileExtension}", jsonObj.get("fileExtension").toString());
				
		        
		        String processIdPrefix = idFormat.replace("{prefix}", jsonObj.get("prefix").toString());
		        String processIdPrefixeSeperator = processIdPrefix.replace("{prefixSeperator}", jsonObj.get("prefixSeperator").toString());
		        String processIdStub = processIdPrefixeSeperator.replace("{<stub>}", stub);      
		        String processPostFixSeperator = processIdStub.replace("{postfixSeperator}",jsonObj.get("postfixSeperator").toString());
				String processPostFix = processPostFixSeperator.replace("{postfix}", jsonObj.get("postfix").toString());
		        //System.out.println (processFileName.toString());
		        
		        this.fileName = processFileName;
		        this.id = "[id=\""+processPostFix+"\"]";
		        
		        System.out.println (this.fileName + "\n" + this.id);
		        
		        String body = jsonObj.get("head").toString();
		        String processIdBody = body.replace("{<id>}",this.id);
		        String processHeading = processIdBody.replace("{<moduleName>}", this.moduleName);
		        
		        StringBuffer content = new StringBuffer();
		        
		        content.append(processHeading);
		        content.append(jsonObj.get("body"));
		        content.append(jsonObj.get("foot"));
		        		        
		        System.out.println(content.toString());
		        
		        this.contentFile = new File(this.fileName);
				this.contentFile.createNewFile();
				
				FileWriter writer = new FileWriter(this.contentFile);
				writer.write(content.toString());
				writer.flush();
			    writer.close();
				
		
		        
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private String createStub (String moduleName, String wordSeperator) {
		String stub;
		moduleName = moduleName.toLowerCase();
		StringBuffer stringBuffer = new StringBuffer();
        for (char c : moduleName.toCharArray()) {
			if (!(Character.isLetter(c)) && !(Character.isDigit(c))) {
				stringBuffer.append(wordSeperator);
			}
			else {
				stringBuffer.append(c);
			}
        }
        stub = stringBuffer.toString();
		return stub;
	}
		
}
