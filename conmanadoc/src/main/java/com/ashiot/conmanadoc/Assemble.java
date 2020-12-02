package com.ashiot.conmanadoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Assemble {

	private String assemblyURL = "https://raw.githubusercontent.com/redhat-documentation/modular-docs/master/modular-docs-manual/files/TEMPLATE_ASSEMBLY_a-collection-of-modules.adoc";
	
	private String assemblyName;
	private String assemblyID;
	private String assemblyContext;
	private String assemblyFileName;
	private File assemblyFile;
	
	public void initializeAssembly(String name, String keyword)
	{
		this.assemblyName = name;
		//this.makeContent();
		this.metadataFromName();
		this.assemblyContext = keyword;
		System.out.println(keyword);
		this.makeContent();

	}
	
	private void makeContent()
	{
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader bufferedReader;
		String inputLine;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			url = new URL(this.assemblyURL);
		
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			// Read the template to a buffer
			bufferedReader = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
			
			while ((inputLine = bufferedReader.readLine()) != null) {
				if (inputLine.contains("* file name:")) {
			    	//System.out.println("* file name: "+this.contentFileName);
					stringBuffer.append("* file name: "+this.assemblyFileName).append("\n");
				}
				else if (inputLine.contains("* ID:")) {
					stringBuffer.append("* ID: "+this.assemblyID).append("\n");
				}
				else if (inputLine.contains("* Title:")) {
					stringBuffer.append("* Title: "+this.assemblyName).append("\n");
				}
				else if (inputLine.contains("[id=")) {
					stringBuffer.append(this.assemblyID).append("\n");
				}
				else if (inputLine.contains("= My")) {
					stringBuffer.append("= "+this.assemblyName).append("\n");
				}
				else if (inputLine.contains(":context:")) {
					stringBuffer.append(":context: "+this.assemblyContext).append("\n");
				}
				else {
					stringBuffer.append(inputLine).append("\n");
				}
			}
			bufferedReader.close();
			
			//System.out.println(stringBuffer.toString());
			this.assemblyFile = new File(this.assemblyFileName);
			this.assemblyFile.createNewFile();
			FileWriter writer = new FileWriter(this.assemblyFile);
			writer.write(stringBuffer.toString());
			writer.flush();
		    writer.close();
			
		} catch (Exception e){
		} finally {
			con.disconnect();
			
		}
		
	}
	
	private void metadataFromName () {
		
		String contentNameLower = this.assemblyName.toLowerCase();
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
		
		this.assemblyID = "[id=\"assembly-"+stringBuffer.toString()+"_{context}\"]";
		this.assemblyFileName = "assembly-"+stringBuffer.toString()+".adoc";
		System.out.println("The id is "+this.assemblyID);
		System.out.println("The file name is "+this.assemblyFileName);

	}

}
