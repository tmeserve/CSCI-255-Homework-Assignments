/*
Name: Tyler Meserve
Date: 4/17/2026
Homework #: Lab 8
Source file: Lab8.java
Class: 255 Online
Action: This program uses recursion to find and return the sum of all elements in an integer array.
External Assistance Provided By: 
*/

public class Lab8
{

    public static int sum(int[] arr, int index)
    {
        if (index == arr.length - 1)
            return arr[index];
        return arr[index] + sum(arr, ++index);
    }

    public static void main(String[] args)
    {
        int[] arr1 = new int[] {2, 4, 6, 8, 0};
        int[] arr2 = new int[] {2, 4, 6, 8, 7};

        System.out.println("Sum of array elements is " + sum(arr1, 0));
        System.out.println("Sum of array elements is " + sum(arr2, 0));
    }
}

/* ************************  Program Output  *******************************
Sum of array elements is 20
Sum of array elements is 27
*/