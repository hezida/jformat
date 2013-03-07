package org.hd.jformat.test;

import org.hd.jformat.LoggingUtility;

public class MainTest {

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
