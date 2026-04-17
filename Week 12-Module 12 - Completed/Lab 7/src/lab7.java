/*
Name: Tyler Meserve
Date: 4/1/2026
Homework #: Lab 7
Source file: lab7.java
Class: 255 Online
Action: Creates and tests a Temperature class by initializing objects, displaying values, 
    converting between Celsius and Fahrenheit, and allowing user input to update and display a temperature.
External Assistance Provided By: 
*/

import java.util.Scanner;

class lab7
{

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Temperature t1 = new Temperature();
        Temperature t2 = new Temperature("C", 100);
        Temperature t3 = new Temperature(t2);

        System.out.print("Temperature for t1 is ");
        t1.showTemp();
        System.out.print("Temperature for t2 is ");
        t2.showTemp();
        System.out.print("Temperature for t3 is ");
        t3.showTemp();

        t1.convert("F", 212);
        t1.convert("C", 100);

        System.out.print("Enter temperature scale ");
        String scale = s.nextLine();
        System.out.print("Enter temperature ");
        float temp = s.nextFloat();
        t1.scale = scale;
        t1.setTemp(temp);
        
        System.out.println("Inputted temperature is " + t1.temp + " degrees " + t1.scale);
    }
}


/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 12-Module 12\Lab 7\src>java lab7.java
Temperature for t1 is 0.0 degrees F
Temperature for t2 is 100.0 degrees C
Temperature for t3 is 100.0 degrees C
212.0 degrees F converted to other Scale is 100.0 degrees C
100.0 degrees C converted to other Scale is 212.0 degrees F
Enter temperature scale c
Enter temperature 54
Inputted temperature is 54.0 degrees c
*/