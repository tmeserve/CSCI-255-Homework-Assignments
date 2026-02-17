/*
Name: 
Date: 2/8/2026
Homework #: Lab 3
Source file: Lab3.java
Class: 255 Online
Action: Randomly generates 2 integer arrays at sizes x and y, prints them and combines them into 1 array.
External Assistance Provided By: 
*/

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Lab3
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Random r = new Random();
        int sizeOfFirstArray = 0;
        int sizeOfSecondArray = 0;
        int sizeOfCombinedArray = 0;
        boolean sizesEntered = false;

        do
        {
            try
            {
                System.out.print("Enter the size of the first array -> ");
                sizeOfFirstArray = input.nextInt();
                if (!(sizeOfFirstArray > 0))
                {
                    System.out.println("Please enter a number greater than 0");
                    continue;
                }
                System.out.print("Enter the size of the second array -> ");
                sizeOfSecondArray = input.nextInt();
                if (!(sizeOfSecondArray > 0))
                {
                    System.out.println("Please enter a number greater than 0");
                    continue;
                }

                sizesEntered = true;
                sizeOfCombinedArray = sizeOfFirstArray + sizeOfSecondArray;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a number greater than 0.");
            }
        } while (!sizesEntered);

        int[] firstArray = new int[sizeOfFirstArray];
        int[] secondArray = new int[sizeOfSecondArray];
        int[] combinedArray = new int[sizeOfCombinedArray];

        for (int i=0; i<sizeOfFirstArray; i++)
            firstArray[i] = r.nextInt(10);
        for (int i=0; i<sizeOfSecondArray; i++)
            secondArray[i] = r.nextInt(10);

        System.out.println("First Array");
        for (int i=0; i<sizeOfFirstArray; i++)
        {
            combinedArray[i] = firstArray[i];
            System.out.print(firstArray[i] + " ");
        }
        
        System.out.println("\nSecond Array");
        for (int i=0; i<sizeOfSecondArray; i++)
        {
            combinedArray[firstArray.length+i] = secondArray[i];
            System.out.print(secondArray[i] + " ");
        }

        System.out.println("\nCombined Array");
        for (int i=0; i<sizeOfCombinedArray; i++)
            System.out.print(combinedArray[i] + " ");
    }
}