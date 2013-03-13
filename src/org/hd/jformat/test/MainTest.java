package org.hd.jformat.test;

import java.util.ArrayList;
import java.util.List;

import org.hd.jformat.LoggingUtility;

public class MainTest {

	  public static class Car {
	    	public int licensePlate;
	    	public Car(int licensePlate) {
	    		this.licensePlate=licensePlate;
	    	}
	    }
	    
	  public static class Person2 {
	    	public Person2(int age,String name) {
	    		this.age=age;
	    		this.name=name;
	    		this.cars=new ArrayList<Car>();
	    	}
	    	public int age;
	    	public String name;
	    	public List<Car> cars;
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
	    	Car c1=new Car(3245243);
	    	Car c2=new Car(4564365);
	    	Person p1=new Person(1,"first person name");
	    	p1.addCar(c1);
	    	p1.addCar(c2);
	    	Person2 p2=new Person2(2,"second person name");
	    	p2.addCar(c1);
	    	p2.addCar(c2);
	    	LoggingUtility.dumptoStream(p1, System.out);
	    	LoggingUtility.dumptoStream(p2, System.out);
	    }

}
