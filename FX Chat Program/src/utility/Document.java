package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Document 
{
	private static PrintWriter test1;
	private Document() {}
	public static void CreateFile(String a)
	{
		try
		{
			test1 = new PrintWriter(new FileOutputStream(a));
			System.out.println("you created a "+ a +" file");
		}
		catch(IOException e)
		{
			System.out.println("error, file not created");
		}
	}
	public static void writeToFile(String a)
	{
		test1.write(a + "\n");
		
	}
	public static void closeFile()
	{
		test1.close();
	}
	public static ArrayList<String> Read(String name) throws IOException
	{
	
		ArrayList<String> data = new ArrayList<String>();
		File file = new File(name);
		if(file.exists()) 
		{
			Scanner read = new Scanner(file);

			while(read.hasNextLine())
			{
				data.add(read.nextLine());
			}
			read.close();
			return data;
		}
		else
			throw new IOException();	
	}
	
	public static boolean  fileExists(String name) 
	
	{
		File file = new File(name);
		return file.exists();
	}
	
	public static String getValue(String line) throws IOException 
	{
		for(int i=0 ; i<line.length(); i++) 
		{
			if(line.charAt(i)==' ') 
			{
				String value = line.substring(i+1);
				return value;
			}
		}
		throw new IOException() ;
	}
	
	public static boolean isInteger( String input ) 
	{
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( NumberFormatException e ) {
	        return false;
	    }
	}
		

	
}
