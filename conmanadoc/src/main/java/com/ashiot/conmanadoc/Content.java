package com.ashiot.conmanadoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.Character;


public class Content {

	private String contentType;
	
	private String conURL = "https://raw.githubusercontent.com/redhat-documentation/modular-docs/master/modular-docs-manual/files/TEMPLATE_CONCEPT_concept-explanation.adoc";
	private String procURL = "https://raw.githubusercontent.com/redhat-documentation/modular-docs/master/modular-docs-manual/files/TEMPLATE_PROCEDURE_doing-one-procedure.adoc";
	private String refURL = "https://raw.githubusercontent.com/redhat-documentation/modular-docs/master/modular-docs-manual/files/TEMPLATE_REFERENCE_reference-material.adoc";

		
	private String contentName;
	private String contentID;
	private String contentFileName;
	private File contentFile;
	
	public void initializeContent(String type, String name)
	{
		this.contentType = type;
		this.contentName = name;
		
		//this.makeContent();
		this.metadataFromName();
		this.makeContent();
		
	}
	
	private void makeContent()
	{
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader bufferedReader;
		String inputLine;
		boolean insideComment = false;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			switch (this.contentType)
			{
			case "con" :
				url = new URL(this.conURL);		
				break;
			case "proc" :
				url = new URL(this.procURL);
				break;
			case "ref" :
				url = new URL(this.refURL);
				break;
			}
			
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			// Read the template to a buffer
			bufferedReader = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
			
			while ((inputLine = bufferedReader.readLine()) != null) {
				if (inputLine.startsWith("////"))
				{
					if (insideComment == true) {
						insideComment = false;
					}
					else
						insideComment = true;
					
				}
				
				else if (insideComment == false) {
					if (inputLine.contains("* file name:")) {
				    	//System.out.println("* file name: "+this.contentFileName);
						stringBuffer.append("* file name: "+this.contentFileName).append("\n");
					}
					else if (inputLine.contains("* ID:")) {
						stringBuffer.append("* ID: "+this.contentID).append("\n");
					}
					else if (inputLine.contains("* Title:")) {
						stringBuffer.append("* Title: "+this.contentName).append("\n");
					}
					else if (inputLine.contains("[id=")) {
						stringBuffer.append(this.contentID).append("\n");
					}
					else if (inputLine.contains("= My")) {
						stringBuffer.append("= "+this.contentName).append("\n");
					}
					else if (inputLine.contains("= Reference")) {
						stringBuffer.append("= "+this.contentName).append("\n");
					}
					else if (inputLine.contains("= Doing procedure A")) {
						stringBuffer.append("= "+this.contentName).append("\n");
					}
					else {
						stringBuffer.append(inputLine).append("\n");
					}
				}
			}
			bufferedReader.close();
			
			//System.out.println(stringBuffer.toString());
			this.contentFile = new File(this.contentFileName);
			this.contentFile.createNewFile();
			FileWriter writer = new FileWriter(this.contentFile);
			writer.write(stringBuffer.toString());
			writer.flush();
		    writer.close();
			
		} catch (Exception e){
			System.out.println ("lookks like you are disconnected");
		} finally {
			con.disconnect();
			
		}
		
	}
	
	private void metadataFromName () {
		
		String contentNameLower = this.contentName.toLowerCase();
		StringBuffer stringBuffer = new StringBuffer();
		//Character ch;
		
		for (char c : contentNameLower.toCharArray()) {
			//ch = new Character (c)
			if (!(Character.isLetter(c)) && !(Character.isDigit(c))) {
				stringBuffer.append('-');
			}
			else {
				stringBuffer.append(c);
			}
		}
		
		if (stringBuffer.charAt(stringBuffer.length() -1) == '-' ) {
			stringBuffer.deleteCharAt(stringBuffer.length() -1);
		}
		
		this.contentID = "[id=\""+this.contentType+"-"+stringBuffer.toString()+"_{context}\"]";
		this.contentFileName = this.contentType+"-"+stringBuffer.toString()+".adoc";
		System.out.println("The id is "+this.contentID);
		System.out.println("The file name is "+this.contentFileName);

	}
}
