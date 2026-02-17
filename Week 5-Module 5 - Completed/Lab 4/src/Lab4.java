/*
Name: Tyler Meserve
Date: 2/15/2025
Homework #: Lab4
Source file: Lab4.java
Class: 255 Online
Action: Generates a 2D int array between 0 to 100 and displays the array based off of the command line arguments provided
CMD Arguments:
Letter Number
Letter - 'R' for Row, and 'C' for column
Number - Number of rows or columns to display
External Assistance Provided By: 
*/
import java.util.Random;

public class Lab4
{
    private static int ROW_SIZE = 3;
    private static int COLUMN_SIZE = 5;

    private static int[][] generate2DArray()
    {
        Random r = new Random();
        int[][] generatedArray = new int[ROW_SIZE][COLUMN_SIZE];

        for (int i=0; i<ROW_SIZE; i++)
        {
            for (int x=0; x<COLUMN_SIZE; x++)
                generatedArray[i][x] = r.nextInt(0, 101);
        }

        return generatedArray;
    }

    public static void main(String[] args)
    {
        if (args.length == 0)       //  No command line parameters.
            System.out.println("No command line args provided");
        else
        {
            for (int i = 0; i < args.length-1; i++)
            {
                if (args[i].equals("R"))
                    ROW_SIZE = Integer.valueOf(args[i+1]);
                else if (args[i].equals("C"))
                    COLUMN_SIZE = Integer.valueOf(args[i+1]);
            }

            int[][] twoDArray = generate2DArray();
            for (int i=0; i<twoDArray.length; i++)
            {
                for (int x=0; x<twoDArray[i].length; x++)
                {
                    System.out.print(twoDArray[i][x] + " ");
                }
                System.out.println();
            }
        }
    }
}


/* ************************  Program Output *******************************
2 3 1 4 5 
2 9 1 10 23 
1 2 0 3 6 


E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\Lab 4\src>java Lab4.java R 4 C 6
2 11 67 2 97 30 
36 85 85 84 8 2
65 2 53 54 9 35
7 10 93 2 91 85

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\Lab 4\src>java Lab4.java R 4    
23 45 67 64 62 
1 10 15 63 3
21 15 87 74 79
91 59 10 85 71

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\Lab 4\src>java Lab4.java C 4
53 91 10 1 
54 36 18 97
70 32 57 71

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\Lab 4\src>java Lab4.java
No command line args provided
*/