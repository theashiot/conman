package com.ashiot.conmanadoc;

import java.util.ArrayList;
import java.util.HashMap;

public class InputParser {
	
	private ArrayList <String> commandArgs;
	private final String contentTypeArg = "--type";
	private final String contentNameArg = "--name";
	private final String contentContextArg = "--context";
	private final String contentCommentArg = "--comments";
	private final String helpArg = "--help";
	public boolean help;
	private HashMap<String,String> commands;

	private enum contentType {con,proc,ref,assembly};
	private boolean commandValid;
	
	public InputParser (String args[]) {
		commandArgs = new ArrayList <String> ();
		commandArgs.add(contentTypeArg);
		commandArgs.add(contentNameArg);
	//	commandArgs.add(contentContextArg);
	//	commandArgs.add(contentCommentArg);
		this.commandValid = false;
		this.help = false;
		this.commands = new HashMap <String,String>();
		
		for (String item : commandArgs) {
			commands.put(item, null);
		}
		
		if (args.length == 0)
			this.printUsage();
		
		else {
			this.createHashMapFromInputArgs (args);
			this.validateCommand();
		}
	}
	
	private boolean validateContentType (String content) {
		contentType[] values = contentType.values();
		
		for (contentType value : values) {
			if (value.name().equals(content)) {
				//System.out.println ("Correct type");
				this.commandValid = true;
				return true;
			}
		}
		this.commandValid = false;
		return false;
	}
	
	public HashMap <String,String> getCommands () {
		if (this.commandValid)
			return this.commands;
		
		else
			return null;
	}
	
	private boolean validateCommand () {
		
		if (commands.containsKey(helpArg))
		{
			System.out.println ("in help");
			this.commandValid = true;
			this.printUsage();
			this.help = true;
			return true;
		}

		else {
			for (String hash : commands.keySet())
			{
				if (commands.get(hash) == null) {
					this.commandValid = false;
					System.out.println("incorrect input");
					return false;
				}
			}
			this.commandValid = true;
		}
		
		return true;
	}
	
	private void printUsage () {
		String usageLine = "\nUsage:\n\njava -jar conman.jar " +  contentTypeArg + " <TYPE> " + contentNameArg + " <NAME> \n";
		String optionsLine = "TYPE is one of:\ncon\t\t:\tuse for concept module\nproc\t\t:\tuse for procedure module\nref\t\t:\tuse for reference module\nassembly\t:\tuse for assembly\n";
		String exampleLine = "Example: java -jar conman "+contentTypeArg+" con "+contentNameArg+" \"My new module\"\n";		
		String optionalContextLine = "Optionally use "+contentContextArg+" with assembly to provide a custom assembly context\n";
		String assemblyExampleLine = "Example: java -jar conman "+contentTypeArg+" assembly "+contentNameArg+" \"My new assembly\" "+contentContextArg+" \"my custom context\"\n";
		System.out.println (usageLine);
		System.out.println(optionsLine);
		System.out.println (exampleLine);
		System.out.println (optionalContextLine);
		System.out.println (assemblyExampleLine);
	}
	
	private void createHashMapFromInputArgs (String args[]) {
		int index = 0;	
		
		
		for (String arg : args) {
			//System.out.println ("index = " + index + " " + arg);
			
			if (arg.equals(helpArg)) {
				commands.put(helpArg, null);
				return;
			}
			else if (arg.equals(contentTypeArg)) {
				if ((index+1) < (args.length)) {
					if (args[index + 1].startsWith("--")) {
						System.out.println("incorrect input");
						break;
					}
					else {
						if (validateContentType(args[index + 1]))
							commands.put(contentTypeArg, args[index+1]);
						
						else {
							System.out.println("incorrect input");
							break;
						}
					}
				}
			}
			
			else if (arg.equals(contentNameArg)) {
				if ((index+1) < (args.length)) {
					if (args[index + 1].startsWith("--")) {
						System.out.println("incorrect input");
						break;
					}
					else
						commands.put(contentNameArg, args[index+1]);
				}
				
				else 
					System.out.println("incorrect input");
			}
				
			
			else if (arg.equals(contentContextArg)) {
				if ((index+1) < (args.length)) {
					if (args[index + 1].startsWith("--")) {
						System.out.println("incorrect input");
						break;
					}
					else
						commands.put(contentContextArg, args[index+1]);
				}
				else 
					System.out.println("incorrect input");
			}
				
			index++;
		}
		
		/*for (String hash : commands.keySet())
		{
			System.out.println (hash +" "+ commands.get(hash));
		} */
		
		// System.out.println (validateCommand());
	}
}