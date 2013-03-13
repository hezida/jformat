package org.hd.jformat;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This is the main logging class...
 * 
 * @author hezi
 *
 */
public class LoggingUtility {
	

	/**
	 *
	 * @author hezi 
	 *
	 */
	static class DumpingRules {
		public boolean dumpPrivates;
		public boolean dumpPublic;
		public boolean dumpPrimitives;
		public boolean dumpArrays;
		public boolean dumpCollections;
		public int maxCollectionItemsToPrint;
		public boolean printClasses;
		public int recurseLevel;
		public Class<Object>[] fieldsToDump;
		public Class<Object>[] fieldsNotToDump;
	}
	public static DumpingRules default_rules;
	public static Set<Class<?>> primitives;
	/**
	 * static block follows
	 */
	static {
		default_rules=new DumpingRules();
		default_rules.dumpPrivates=false;
		default_rules.dumpPublic=true;
		default_rules.dumpPrimitives=true;
		default_rules.dumpArrays=true;
		default_rules.dumpCollections=true;
		default_rules.maxCollectionItemsToPrint=20;
		default_rules.printClasses=false;
		default_rules.recurseLevel=4;
		
		primitives=new HashSet<Class<?>>();
		primitives.add(Integer.class);
		primitives.add(String.class);
		primitives.add(Float.class);
		primitives.add(Double.class);
		primitives.add(Boolean.class);
		primitives.add(Long.class);
		primitives.add(Short.class);
		primitives.add(Byte.class);
	}
	/**
	 * This is the main method to use. Just give it an object and a stream and it will
	 * dump the object to the stream
	 * @param value the object to dump
	 * @param s the stream to dump to
	 */
	/**
	 * This method dumps only primitive members of the object
	 * @param value
	 * @param s
	 * @param members
	 */
	public static void dumptoStream(Object value,PrintWriter s) {
		LoggingUtility.dumptoStream(null,value, s, 0, 0, LoggingUtility.default_rules);
	}
	public static void dumptoStream(Object value,PrintWriter s,DumpingRules rules) {
		LoggingUtility.dumptoStream(null,value, s, 0, 0, rules);
	}
	private static void printTabs(int depth,PrintWriter s) {
		for(int i=0;i<depth;i++)
			s.print("\t");
	}
	private static void dumptoStream(String name,Object value,PrintWriter s,int depth,int cip,DumpingRules rules)
	{
		if(depth==rules.recurseLevel) {
			return;
		}
		if (value.getClass()==Class.class) {
			if(rules.printClasses) {
				// TODO: dump the Class
			}
			return;
		}
		if (LoggingUtility.primitives.contains(value.getClass())) {
			if (rules.dumpPrimitives) {
				LoggingUtility.printTabs(depth, s);
				s.println(name+": "+value.getClass().getSimpleName()+": "+value);
			}
			return;
		}
		if (value.getClass().isArray()) {
			if(rules.dumpArrays) {
				LoggingUtility.printTabs(depth, s);
				s.println(name+": "+value.getClass().getSimpleName()+": "+Array.getLength(value));
				for (int i = 0; i < Array.getLength(value); i++) {
		            LoggingUtility.dumptoStream("element "+i,Array.get(value, i),s,depth+1,cip,rules);
		            cip++;
		            if(cip>=rules.maxCollectionItemsToPrint) {
						LoggingUtility.printTabs(depth, s);
						s.println("(more data missing...)");
		            	return;
		            }
				}
			}
			return;
		}
		if (value instanceof Collection<?>) {
			if(rules.dumpCollections) {
				Collection<?> col=(Collection<?>)value;
				LoggingUtility.printTabs(depth, s);
				s.println(name+": "+value.getClass().getSimpleName()+": "+col.size()+":");
				int i=0;
				for(Object x:col) {
		            LoggingUtility.dumptoStream("element "+i,x,s,depth+1,cip,rules);
		            cip++;
		            if(cip>=rules.maxCollectionItemsToPrint) {
						LoggingUtility.printTabs(depth, s);
						s.println("(more data missing...)");
		            	return;
		            }
		            i++;
				}
			}
			return;
		}
		if (value instanceof Map<?,?>) {
			if(rules.dumpCollections) {
				Map<?,?> map=(Map<?,?>)value;
				LoggingUtility.printTabs(depth, s);
				s.println(name+": "+value.getClass().getSimpleName()+": "+map.size()+":");
				int i=0;
				for (Map.Entry<?, ?> entry : map.entrySet()) {
				    Object key =entry.getKey();
				    Object val=entry.getValue();
		            LoggingUtility.dumptoStream("key "+i,key,s,depth+1,cip,rules);
		            LoggingUtility.dumptoStream("val "+i,val,s,depth+1,cip,rules);
		            cip++;
		            if(cip>=rules.maxCollectionItemsToPrint) {
						LoggingUtility.printTabs(depth, s);
						s.println("(more data missing...)");
		            	return;
		            }
		            i++;
				}
			}
			return;
		}
		// now dumping other kinds of objects
		LoggingUtility.printTabs(depth, s);
		if(name==null) {
			s.println(value.getClass().getSimpleName()+":");
		} else {
			s.println(name+": "+value.getClass().getSimpleName()+":");			
		}
		
		for(Field f:value.getClass().getFields()) {
			try {
				LoggingUtility.dumptoStream(f.getName(),f.get(value),s,depth+1,cip,rules);
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
