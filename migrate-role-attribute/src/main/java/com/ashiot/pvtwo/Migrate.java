package com.ashiot.pvtwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import roleutils.ContentOject;

/**
 * Hello world!
 *
 */
public class Migrate 
{
	private static void addRoleAbstract (String fileName) 
	{
		 
		BufferedReader inputStream = null;
		 PrintWriter outputStream = null;
		 File contentFile;
		 StringBuffer stringBuffer = new StringBuffer();
		 try {
             inputStream = new BufferedReader(new FileReader(fileName));
             //outputStream = new PrintWriter(new FileWriter("temp.adoc"));
             
             int i = 0;
             String l;
             int found = 0;
             while ((l = inputStream.readLine()) != null) {
                 
            	 if ((found == 1) && l.trim().isEmpty())
            		 continue;
            	 
            	 else if ((found == 1) && !l.trim().isEmpty())
            		 found = 0;
            	 
            	 System.out.println (l);
            	 stringBuffer.append(l);
            	 stringBuffer.append("\n");
            	             	 
            	 if (l.startsWith("=")) {
            		 //System.out.println("Found heading at line " + i + "\n");
            		 System.out.println("\n[role=\"_abstract\"]");
            		 stringBuffer.append("\n[role=\"_abstract\"]\n");
            		 found = 1;
            	 } 
            	 
            	 i++;
            	 
            	 }
             
             inputStream.close();
             
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
	
    public static void main(String[] args)
    {
    	
         PrintWriter outputStream = null;

         if (args.length == 0)
         {
        	 System.out.println("no argument");
        	 return;
         }
         else {
        	 ContentOject conObj = new ContentOject(args[0]);
        	 //addRoleAbstract(args[0]);
         }
         
    }
}
