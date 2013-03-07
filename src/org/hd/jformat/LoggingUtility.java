package org.hd.jformat;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * This is the main logging class...
 * bla bla, this is some more docs...
 * 
 * @author hezi
 *
 */
public class LoggingUtility {
	

	/**
	 * This is the main method to use. Just give it an object and a stream and it will
	 * dump the object to the stream
	 * @param value the object to dump
	 * @param s the stream to dump to
	 */
	public static void dumptoStream(Object value,PrintStream s) {
		LoggingUtility.dumptoStream(null,value, s, 0, 10);
	}
	private static void dumptoStream(String name,Object value,PrintStream s,int depth,int limit)
	{
		// end the recursion (safety)...
		if(depth==limit) {
			return;
		}
		//System.out.println(value.getClass().getName());
		if (value.getClass()==Class.class) {
			return;
		}
		if (
				value.getClass()==Integer.class ||
				value.getClass()==String.class ||
				value.getClass()==Float.class ||
				value.getClass()==Double.class ||
				value.getClass()==Boolean.class ||
				value.getClass()==Long.class ||
				value.getClass()==Short.class ||
				value.getClass()==Byte.class				
				) {
			for(int i=0;i<depth;i++)
				s.print("\t");
			s.println(name+": "+value.getClass().getSimpleName()+": "+value);
			return;
		}
		if(value.getClass().isArray()) {
			for(int i=0;i<depth;i++)
				s.print("\t");
			s.println(name+": "+value.getClass().getSimpleName()+":");
			//s.println(name+": Array");
			for (int i = 0; i < Array.getLength(value); i++) {
	            LoggingUtility.dumptoStream("element "+i,Array.get(value, i),s,depth+1,limit);
			}
			return;
		}
		for(int i=0;i<depth;i++)
			s.print("\t");
		if(name==null) {
			s.println(value.getClass().getSimpleName()+":");
		} else {
			s.println(name+": "+value.getClass().getSimpleName()+":");			
		}
		
		for(Field f:value.getClass().getFields()) {
			try {
				LoggingUtility.dumptoStream(f.getName(),f.get(value),s,depth+1,limit);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		/*
 		value = removeWrappingMarks(Arrays.deepToString(new Object[]{value}), START, END);
//		if (value.getClass().isArray())
		if (!value.getClass().isPrimitive())	
		{
			return name + " : " + value + SEMICOLON; 
		}
		else
		{
			return name + " @ " + value + SEMICOLON; 
		}
		*/
	}
}
