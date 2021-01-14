package com.ashiot.conmanadoc;

import com.ashiot.conmanadoc.Content;
import com.ashiot.conmanadoc.InputParser;

import java.util.HashMap;

import com.ashiot.conmanadoc.Assemble;

public class ConManMain 
{
    public static void main( String[] args )
    {
    	Content content = new Content();
    	Assemble assembly = new Assemble();
    	HashMap <String,String> commandMap;
    	InputParser input = new InputParser(args);
    	commandMap = input.getCommands();
    	if (commandMap != null && (input.help == false))
    	{
    		
    		if (commandMap.get("--type").equals("assembly")) {
    			if (commandMap.get("--context") == null) {
    				assembly.initializeAssembly(commandMap.get("--name"),commandMap.get("--name"));
    			}
    			else {
    				assembly.initializeAssembly(commandMap.get("--name"),commandMap.get("--context"));

    			}
    		}
    		else
    			content.initializeContent(commandMap.get("--type"), commandMap.get("--name"));
    	}

    }
}
