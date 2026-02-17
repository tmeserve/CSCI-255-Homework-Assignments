/*
Name:   Tyler Meserve
Date:   1/24/2026
Homework #:  Lab #1
Source File: Lab1.java

Action: Program will calculate the average of 3 numbers that the user inputs
*/
import java.util.Scanner;

public class Lab1
{
    private static Scanner input = new Scanner(System.in);

    // Calculates the average between 3 numbers
    private static double calculateAverage(int firstNumber, int secondNumber, int thirdNumber)
    { return (firstNumber + secondNumber + thirdNumber) / 3; }

    // This will get user input and verify if the data the user inputted is what is expected. 
    private static int getNumber()
    {
        int number = -1;
        while (true)
        {
            try
            {
                number = input.nextInt();
                if (number < 0)
                    System.out.println("Please input a number greater than 0. ");
            }
            catch (Exception e)
            {
                System.out.println("Please enter a number.");
                input.nextLine();
            }

            if (number > 0)
                break;
        }

        return number;
    }

    public static void main(String[] args)
    {
        int firstNumber = -1;
        int secondNumber = -1;
        int thirdNumber = -1;

        System.out.print("First Number: ");
        firstNumber = getNumber();
        
        System.out.print("Second Number: ");
        secondNumber = getNumber();

        System.out.print("Third Number: ");
        thirdNumber = getNumber();

        System.out.println("Average: " + calculateAverage(firstNumber, secondNumber, thirdNumber));
    }
}


/*
First
First Number: 6
Second Number: 3
Third Number: 9
Average: 6.0

Second
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 2-Module 2\src>java Lab1 
First Number: 6
Second Number: 3
Third Number: 9
Average: 6.0

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 2-Module 2\src>

Third
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 2-Module 2\src>java Lab1 < Lab1Data.txt
First Number: Second Number: Third Number: Average: 45.0

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 2-Module 2\src>
*/