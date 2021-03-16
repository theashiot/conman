package roleutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentOject {

	StringBuffer stats;
	StringBuffer log;
	
	public ContentOject(String sourceDirectory) {
		stats = new StringBuffer();
		log = new StringBuffer();
		
		this.filesInDirectory(sourceDirectory);
	}
	
	private void filesInDirectory (String sourceDirectory) {
		List<String> results = new ArrayList<String>();
		//File[] files = new File(sourceDirectory).listFiles();
		File[] files = new File(sourceDirectory).listFiles(new FilenameFilter() {
			@Override public boolean accept(File dir, String name) {
				return name.endsWith(".adoc");
				}
			}
		
		);
		int noOfFiles = 0;
		if (files == null) {
			log.append(sourceDirectory + " is not a directory");
			
			return;
		}
		else {
			for (File file : files) {
				 if (file.isFile()) {
				        results.add(file.getName());
				        noOfFiles++;
				        System.out.println(file.getName() + "\n");
				        this.addRoleAbstractToFile(sourceDirectory + "/" + file.getName());
				        this.addAdditionalResourcesToFile (sourceDirectory + "/" + file.getName());
				 }
				 
			}
			
		}

	}
	
	private void addRoleAbstractToFile (String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader inputStream = null;
		List<String> lines = new ArrayList<String>();
		File contentFile;
		
		try {
            inputStream = new BufferedReader(new FileReader(fileName));
            int i = 0;
            String l;
            int found = 0;
            
            while ((l = inputStream.readLine()) != null) {
           	 	lines.add (l);
           	 	if (l.equals("[role=\"_abstract\"]")) {
           	 		System.out.println ("role abstract already present; skipping");
           	 		return;
           	 	}
           	 	i++;
           	 }
            
            inputStream.close();
            
            int foundHeading = 0; 
            
            for (String line : lines) {
            	
              	if ((found == 1) && line.trim().isEmpty())
            		 continue;
            	 
            	else if ((found == 1) && !line.trim().isEmpty())
            		 found = 0;
            	
              	stringBuffer.append(line);
              	stringBuffer.append("\n");
            	
              	if (line.startsWith("=")) {
            		if (foundHeading == 0) {
            			foundHeading = 1;
           		 		stringBuffer.append("\n[role=\"_abstract\"]\n");
           		 		found = 1;
            		}

            	}
            }
            
            // System.out.println (stringBuffer.toString());
            
            
            
            contentFile = new File(fileName);
            contentFile.createNewFile();
            FileWriter writer = new FileWriter(contentFile);
            writer.write(stringBuffer.toString());
            writer.flush();
            writer.close();
            
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addAdditionalResourcesToFile (String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader inputStream = null;
		List<String> lines = new ArrayList<String>();
		List<String> reverseLines = new ArrayList<String>();
		
		File contentFile;
		
		try {
            inputStream = new BufferedReader(new FileReader(fileName));
            int i = 0;
            String l;
            int found = 0;
            
            while ((l = inputStream.readLine()) != null) {
           	 	lines.add (l);
           	 	if (l.equals("[role=\"_additional-resources\"]")) {
           	 		System.out.println ("role additional resources already present; skipping");
           	 		return;
           	 	}
           	 	i++;
           	 }
            
            inputStream.close();
            
            
            
           Collections.reverse(lines);
            
           for (String line : lines) {
        	   reverseLines.add(line);
        	   if (line.equals(".Additional resources"))
        		   reverseLines.add("[role=\"_additional-resources\"]");      		   
        	   
            }
           
           Collections.reverse(reverseLines);
           StringBuffer stringBuffer1 = new StringBuffer();
           for (String line : reverseLines) {
        	   stringBuffer1.append(line + "\n");
           }
            
           //System.out.println (stringBuffer1.toString());
            
           
            contentFile = new File(fileName);
            contentFile.createNewFile();
            FileWriter writer = new FileWriter(contentFile);
            writer.write(stringBuffer1.toString());
            writer.flush();
            writer.close();
           
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
