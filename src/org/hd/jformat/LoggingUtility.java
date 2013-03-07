package org.hd.jformat;

//import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

public class LoggingUtility {
	
	//private static final String SEMICOLON = " ;; ";
	private static final String START = "["; //$NON-NLS-1$ 
	private static final String END = "]"; //$NON-NLS-1$ 

	public static void dumptoStream(Object value,PrintStream s) {
		LoggingUtility.dumptoStream(null,value, s, 0, 10);
	}
	public static void dumptoStream(String name,Object value,PrintStream s,int depth,int limit)
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

	public static String toString(Object value)
	{
		return removeWrappingMarks(Arrays.deepToString(new Object[]{value}), START, END);
	}

	public static char nl() 
	{
		return '\n';
	}

	public static String removeWrappingMarks(String string, String start, String end) {
        if (string == null) {
              return "[NULL]";
        }

        if (string.startsWith(start)) {
              string = string.substring(start.length());
        }

        if (string.endsWith(end)) {
              string = string.substring(0, string.length() - end.length());
        }

        return string;
  }
    
    public static String printValue (String name, Object[] array)
    {
    	return (array == null ? (name + " == null !!!") : (name + ".length = " + array.length));
    }
    
    static class Car {
    	public int licensePlate;
    	public Car(int licensePlate) {
    		this.licensePlate=licensePlate;
    	}
    }
    
    static class Person {
    	public Person(int age,String name) {
    		this.age=age;
    		this.name=name;
    		this.cars=null;
    	}
    	public int age;
    	public String name;
    	public Car[] cars;
    	public void addCar(Car c) {
    		if(this.cars==null) {
    			this.cars=new Car[1];
    			this.cars[0]=c;
    		} else {
    			Car[] newcars=new Car[this.cars.length+1];
    			for(int i=0;i<this.cars.length;i++) {
    				newcars[i]=cars[i];
    			}
    			newcars[this.cars.length]=c;
    			this.cars=newcars;
    		}
    	}
    }
    
    public static void main(String[] args) {
    	Person p=new Person(324,"sadfads");
    	Car c1=new Car(3245243);
    	Car c2=new Car(4564365);
    	p.addCar(c1);
    	p.addCar(c2);
    	LoggingUtility.dumptoStream(p, System.out);
    }
}
