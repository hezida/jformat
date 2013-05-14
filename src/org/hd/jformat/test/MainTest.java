package org.hd.jformat.test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hd.jformat.LoggingUtility;

public class MainTest {

	  public static class Car {
	    	public int licensePlate;
	    	public Car(int licensePlate) {
	    		this.licensePlate=licensePlate;
	    	}
	    }
	    
	  public static class Person3 {
	    	public Person3(int age,String name) {
	    		this.age=age;
	    		this.name=name;
	    		this.cars=new HashMap<String,Car>();
	    	}
	    	private int age;
	    	private String name;
	    	private Map<String,Car> cars;
	    	public int getAge() {
	    		return age;
	    	}
	    	public String getName() {
	    		return name;
	    	}
	    	public void addCar(String name,Car c) {
	    		this.cars.put(name, c);
	    	}
	    }
	  public static class Person2 {
	    	public Person2(int age,String name) {
	    		this.age=age;
	    		this.name=name;
	    		this.cars=new ArrayList<Car>();
	    	}
	    	private int age;
	    	public int getAge() {
	    		return age;
	    	}
	    	private String name;
	    	public String getName() {
	    		return name;
	    	}
	    	private List<Car> cars;
	    	public List<Car> getcars() {
	    		return cars;
	    	}
	    	public void addCar(Car c) {
	    		this.cars.add(c);
	    	}
	  }
	  public static class Person {
	    	public Person(int age,String name) {
	    		this.age=age;
	    		this.name=name;
	    		this.cars=null;
	    	}
	    	private int age;
	    	private String name;
	    	private Car[] cars;
	    	public int getAge() {
	    		return age;
	    	}
	    	public String getName() {
	    		return name;
	    	}
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
	    	PrintWriter pw=new PrintWriter(System.out,true);

	    	Car c1=new Car(3245243);
	    	Car c2=new Car(4564365);

	    	Person p1=new Person(1,"first person name");
	    	p1.addCar(c1);
	    	p1.addCar(c2);
	    	LoggingUtility.dumptoStream(p1, pw);

	    	Person2 p2=new Person2(2,"second person name");
	    	p2.addCar(c1);
	    	p2.addCar(c2);
	    	LoggingUtility.dumptoStream(p2, pw);

	    	Person3 p3=new Person3(3,"third person name");
	    	p3.addCar("mycar",c1);
	    	p3.addCar("my wifes car",c2);
	    	LoggingUtility.dumptoStream(p3, pw);

	    	Person3 p4=new Person3(4,"fourth person name");
	    	p4.addCar("mycar",c1);
	    	p4.addCar("my wifes car",c2);
	    	
	    	StringWriter sw=new StringWriter();
	    	PrintWriter pw2=new PrintWriter(sw);
	    	LoggingUtility.dumptoStream(p4, pw2);
	    	StringBuffer sb=sw.getBuffer();
	    	System.out.println(sb.toString());
	    	
	    	System.out.println(LoggingUtility.convert(p3));
	    }

}
