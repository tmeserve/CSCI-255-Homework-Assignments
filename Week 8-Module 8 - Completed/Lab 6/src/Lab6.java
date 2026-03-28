/*
Name: Tyler Meserve
Date: 3/2/2026
Homework #: Lab6
Source file: Lab6.java
Class: 255 Online
Action: This program will read in a file called "Lab6Data.txt" and process the students scores, providing the name and average score
External Assistance Provided By: 
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Lab6
{
    public static void main(String[] args)
    {
        int[] scores = new int[5];
        File file;
        try
        {
            file =  new File("Lab6Data.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                boolean studentDataObtained = false;
                int amountOfScoresObtained = 0;
                String name = "";
                
                while (!studentDataObtained)
                {
                    String line = input.nextLine();
                    if (line.matches("[a-zA-Z]+"))
                        name = line;
                    else if (line.matches("\\d+"))
                    {
                        scores[amountOfScoresObtained] = Integer.parseInt(line);
                        amountOfScoresObtained++;
                    }
                    
                    if (!name.isBlank() && amountOfScoresObtained == 5)
                        studentDataObtained = true;
                }
                
                Student s = new Student(name, scores);
                System.out.println("Students name --> " + s.getName());
                System.out.println("Average is --> " + s.calculateAverage());
            }

            input.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Lab6Data.txt file not found");
        }
    }
}


/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 8-Module 8\Lab 6\src>java Lab6.java
Students name --> Steve
Average is --> 82.0
Students name --> Jane
Average is --> 73.2
Students name --> Mike
Average is --> 92.6
Students name --> Kelly
Average is --> 74.2
*/