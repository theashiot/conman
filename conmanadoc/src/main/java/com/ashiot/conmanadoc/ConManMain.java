package com.ashiot.conmanadoc;

import com.ashiot.conmanadoc.Content;
import com.ashiot.conmanadoc.Assemble;

public class ConManMain 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		int run =  0;
		Content content = new Content();
		Assemble assembly = new Assemble();
		String context;
		if (args.length == 0)
		{
			System.out.println ("Incorrect input");
			System.out.println ("Usage:\n<command> <type> \"Module name\"");
			System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference moule");
			System.out.println ("OR <command> assembly \"Assembly name\" \"context\" (context is to define the assembly level context");
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
			case "assembly" :
				if (args.length == 2)
					context = args[1];
				else
					context = args[2];
				
				assembly.initializeAssembly(args[1], context);
				break;
			case "help":
				System.out.println ("Usage:\n<command> <type> \"Module name\"");
				System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference moule");
				System.out.println ("\nOR <command> assembly \"Assembly name\" \"context\" (context is to define the assembly level context");
				break;
			default:
				System.out.println ("Incorrect input");
				System.out.println ("Usage:\n<command> <type> \"Module name\"");
				System.out.println ("type is one of:\ncon\t concept module\nproc\tprocedure module\nref\treference module");
				System.out.println ("OR <command> assembly \"Assembly name\" \"context\" (context is to define the assembly level context");
		}
		//System.out.println (args[0]);
		//System.out.println (args[1]);
		/*

		content.initializeContent("proc", "My amazing module.java name 1");
*/
		}
    }
}
