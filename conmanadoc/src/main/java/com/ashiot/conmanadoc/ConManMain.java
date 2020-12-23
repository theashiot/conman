package com.ashiot.conmanadoc;

import com.ashiot.conmanadoc.Content;
import com.ashiot.conmanadoc.ContentFromTemplate;

public class ConManMain 
{
    public static void main( String[] args )
    {
    	/*
        System.out.println( "Hello World!" );
		int run =  0;
		Content content = new Content();
		if (args.length == 0)
		{
			System.out.println ("Incorrect input");
			System.out.println ("Usage:\n<command> <type> \"Module name\"");
			System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference moule");
		}
		else {
				
		switch (args[0])
		{
			case "con" : 
				content.initializeContent("con", args[1]);
				break;
			case "proc" : 
				content.initializeContent("proc", args[1]);
				break;
			case "ref" : 
				content.initializeContent("ref", args[1]);
				break;
			case "help":
				System.out.println ("Usage:\n<command> <type> \"Module name\"");
				System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference moule");
				break;
			default:
				System.out.println ("Incorrect input");
				System.out.println ("Usage:\n<command> <type> \"Module name\"");
				System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference moule");
		}
		//System.out.println (args[0]);
		//System.out.println (args[1]);


		content.initializeContent("proc", "My amazing module.java name 1");
		} */
    	//ParseJson parseJson = new ParseJson();
    	ContentFromTemplate content = null;
    	if (args.length == 0)
		{
			System.out.println ("Incorrect input");
			System.out.println ("Usage:\n<command> <type> \"Module name\"");
			System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference moule");
		}
		else {
    	switch (args[0])
		{
			case "con" : 
				
	
			case  "proc": 
				
			case "ref" : 
				content = new ContentFromTemplate(args[1],args[0]);
				break;
			case "help":
			default:
				System.out.println ("Incorrect input");
				System.out.println ("Usage:\n<command> <type> \"Module name\"");
				System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference moule");
		}
    	
    	content.getValuesFromTemplate();
    }
    }
}
