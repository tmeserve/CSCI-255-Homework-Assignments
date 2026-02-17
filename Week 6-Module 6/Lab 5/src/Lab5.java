/*
Name: Tyler Meserve
Date: 2/16/2025
Homework #: Lab 5
Source file: Lab5.java
Class: 255 Online
Action: This program asks the user to input a file name to write to and will write any sentence the user gives that file breaking
down each character and their ascii values and counting the amount of letters and digits there are.
External Assistance Provided By: 
*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab5
{
    private static Scanner input = new Scanner(System.in);
    private static PrintWriter fileWriter;
    private static String fileName;
    private static int amountOfLetters=0, amountOfNumbers=0;

    public static void main(String[] args) throws IOException
    {
        System.out.print("Enter the name of the output data file --> ");
        fileName = input.nextLine();
        fileWriter = new PrintWriter(new FileOutputStream(fileName));

        System.out.println("Enter your sentence");
        String sentence = input.nextLine();
        processSentence(sentence);
        
        fileWriter.close();
        System.out.println("Program done");
    }

    private static void processSentence(String s)
    {
        fileWriter.println("Letter   ASCII value");
        for (char c: s.toCharArray())
        {
            int ascii = c;
            if (Character.isDigit(c))
                amountOfNumbers++;
            else if (Character.isLetter(c))
                amountOfLetters++;
            
            if (ascii < 10)
                fileWriter.println(c + "            " + ascii);
            else if (ascii > 10 && ascii < 100)
                fileWriter.println(c + "           " + ascii);
            else if (ascii > 100)
                fileWriter.println(c + "          " + ascii);
        }

        fileWriter.println("Letters = " + amountOfLetters + "   Digits = " + amountOfNumbers);
    }
}

/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 6-Module 6\Lab 4\src>java Lab5.java
Enter the name of the output data file --> Lab5Out.txt
Enter your sentence
ABC 123 Hello!
Program done

Lab5Out.txt
Letter   ASCII value
A           65
B           66
C           67
            32
1           49
2           50
3           51
            32
H           72
e          101
l          108
l          108
o          111
!           33
Letters = 8   Digits = 3
*/