/*
Name: Tyler Meserve
Date: 2/15/2026
Homework #: Homework 3
Source file: Homework3.java
Class: 255 Online
Action: This program will explain, verify and display the birthday paradox
External Assistance Provided By: 
*/
import java.util.Random;
import java.util.Scanner;

public class Homework3
{
    private static final int SAMPLE_SIZE = 23;
    private static final int NUMBER_OF_SETS = 1000;
    private static final int FW = 3;      // field width for display
    private static final Scanner input = new Scanner(System.in);
    private static int monthNumber; // 1 to 12, for January to December
    private static int dayNumber; // 1 to 30 or 31 or 28

    static void displayMenu()
    {
        System.out.println("1: Explain Birthday Paradox");
        System.out.println("2: Check birthday paradox by generating 1000 sets of birthdays");
        System.out.println("3: Display one set of 23 birthdays");
        System.out.println("E: Exit");
    }

    
    static void explainParadox()
    {
        System.out.println("If 23 persons are chosen at random, then the chances are more than 50% that at least two"
        + "will have the same birthday!");
    }

    /*
    Action: Generates NUMBER_OF_SETS random birthday sets (each of size SAMPLE_SIZE),
            checks each set for at least one matching birthday, and prints the total
            number of sets that contained a match plus the percent.
    Params: None
    Returns: Void; prints results to console
    Precondition: NUMBER_OF_SETS > 0 and SAMPLE_SIZE > 1. generateBirthdaySet(),
    */
    static void verifyParadox()
    {
        int numberOfMatches = 0;
        int[] birthdaySet;

        System.out.println("Generating " + NUMBER_OF_SETS + " sets of " + SAMPLE_SIZE + " birthdays and checking for matches.");
        for (int i=0; i<NUMBER_OF_SETS; i++)
        {
            // Generate Set
            birthdaySet = generateBirthdaySet();

            // Sort Set
            selectionSort(birthdaySet, SAMPLE_SIZE);

            // Look For Match
            if (findMatchInSet(birthdaySet))
                numberOfMatches++;
        }
        
        // Calculates Percent
        double percent = ((double) numberOfMatches/NUMBER_OF_SETS) * 100;
        percent = Math.round(percent * 100.0) / 100.0; // Prevents an issue where some numbers provided false percents (IE 513/1000 would give 51.300000004%)

        // Display Results
        System.out.println("Results: " + numberOfMatches + " out of " + NUMBER_OF_SETS + " ("
        + percent + "%) of the sets contained matching birthdays.");
        System.out.println("============================================================================");
    }

    /*
    Action: Generates one random set of SAMPLE_SIZE birthdays, sorts the set, converts
            each day-of-year value to month/day, and prints the set in aligned columns.
            If duplicate birthdays occur, prints the count in parentheses (ex: "(2)").
    Params: None
    Returns: Void; prints results to console
    Precondition: SAMPLE_SIZE > 0, FW > 0
    */
    static void displaySet()
    {
        String month, prefix, day;
        int printed = 0;

        // Generate Set
        int[] birthdaySet = generateBirthdaySet();

        // Sort Set
        selectionSort(birthdaySet, SAMPLE_SIZE);

        // Display
        System.out.println("\nHere are the results of generating a set of 23 birthdays");
        System.out.println("===========================================================");
        for (int i=0; i<birthdaySet.length; i++)
        {
            int numOfMatches = 1;
            convertDayOfYear(birthdaySet[i]);

            while (i < birthdaySet.length-1 && birthdaySet[i] == birthdaySet[i+1])
            {
                if (birthdaySet[i] == birthdaySet[i+1])
                    numOfMatches++;
                i++;
            }
            month = monthName(monthNumber);
            month = padRight(month, 9);
            prefix = (numOfMatches == 1) ? "    " : "(" + numOfMatches + ") ";
            day = padLeft(String.valueOf(dayNumber), 2);
            System.out.print(prefix + month + " " + day + " ");
            // Makes Rows
            if ((printed+1)%FW == 0)
                System.out.println();
            printed++;
        }
        System.out.println("\n");
    }

    /*
    Action: Converts a day-of-year value (1..365) into a month number (1..12) and a
            day number within that month, storing the results in monthNumber and dayNumber.
    Params: day - an integer representing the day of year; a number between 1-365
    Returns: Void; changes global variables
    Precondition: day must be in the range 1..365
    */
    public static void convertDayOfYear(int day)
    {
        monthNumber = 1;
        while (day > dayInMonth())
        {
            day = day - dayInMonth();
            monthNumber++;
        }
        dayNumber = day;
    }

    /*
    Action: Returns the number of days in the current monthNumber (non-leap year).
    Params: None; uses a global variable
    Returns: An integer representing the number of days within that month
    Precondition: monthNumber should be in the range 1..12
    */
    static int dayInMonth()
    {
        switch (monthNumber)
        {
            case 1: return 31;
            case 2: return 28;
            case 3: return 31;
            case 4: return 30;
            case 5: return 31;
            case 6: return 30;
            case 7: return 31;
            case 8: return 31;
            case 9: return 30;
            case 10: return 31;
            case 11: return 30;
            default: return 31;
        }
    }

    /*
    Action: Returns the English month name for a given month number
    Params: m - an integer representing the month of the year; a number between 1-12
    Returns: A string representing the month of year
    Precondition: m should be in the range 1..12. If m is outside this range,
                  the function defaults to "December"
    */
    static String monthName(int m)
    {
        switch (m) {
            case 1:  return "January";
            case 2:  return "February";
            case 3:  return "March";
            case 4:  return "April";
            case 5:  return "May";
            case 6:  return "June";
            case 7:  return "July";
            case 8:  return "August";
            case 9:  return "September";
            case 10: return "October";
            case 11: return "November";
            default: return "December";
        }
    }

    /*
    Action: Pads a string with trailing spaces until it reaches the requested width.
    Params: s - the string to pad.
            width - the final minimum length of the string.
    Returns: String - s padded on the right with spaces up to width characters.
    Precondition: width should be >= 0. If s.length() >= width, the original string
                  is returned unchanged.
    */
    static String padRight(String s, int width)
    {
        while (s.length() < width)
            s += " ";
        return s;
    }

    /*
    Action: Pads a string with leading spaces until it reaches the requested width.
    Params: s - the string to pad.
            width - the final minimum length of the string.
    Returns: String - s padded on the left with spaces up to width characters.
    Precondition: width should be >= 0. If s.length() >= width, the original string
                  is returned unchanged.
    */
    static String padLeft(String s, int width)
    {
        while (s.length() < width)
            s = " " + s;
        return s;
    }

    /*
    Action: Creates and returns an array of SAMPLE_SIZE randomly generated birthdays
            as day-of-year values (1..365 inclusive).
    Params: None
    Returns: int[] - an array of length SAMPLE_SIZE containing values from 1 to 365.
    Precondition: SAMPLE_SIZE > 0
    */
    static int[] generateBirthdaySet()
    {
        int[] birthdaySet = new int[SAMPLE_SIZE];
        Random r = new Random();
            // Generate Set
            for (int x=0; x<SAMPLE_SIZE; x++)
                birthdaySet[x] = r.nextInt(1, 366);
        return birthdaySet;
    }

    /*
    Action: Checks whether the given birthday set contains at least one duplicate value
            (two or more equal day-of-year values).
    Params: birthdaySet - the array of day-of-year values to check.
    Returns: boolean - true if any duplicate exists, false otherwise.
    Precondition: birthdaySet is not null and has length >= 2.
    */
    static boolean findMatchInSet(int[] birthdaySet)
    {
        boolean matchFound = false;
        for (int x=0; x<birthdaySet.length; x++)
        {
            for (int y=x+1; y<birthdaySet.length; y++)
            {
                // Match Found
                if (birthdaySet[x] == birthdaySet[y])
                {
                    matchFound = true;
                    break;
                }
            }
            
            if (matchFound)
                break;
        }

        return matchFound;
    }

    /*
    Action: Sorts the first Last elements of an integer array into ascending order using
            the selection sort algorithm.
    Params: List - the integer array to sort.
            Last - the number of elements to sort (sorts indexes 0..Last-1).
    Returns: Void; modifies List param.
    Precondition: List is not null, and 0 <= Last <= List.length.
    */
    static void selectionSort(int[] List, int  Last)
    {
        int i, Start, SubSmall, Temp;
        for (Start=1; Start<Last; ++Start)   // Sort items 1..Last
        {
            SubSmall = Start;                        //  subscript of smallest elt.

            for (i = Start + 1; i < Last; i++)    //  Find subscript of smallest
                if (List[i] < List[SubSmall])        //   elt. in tail end of array
                    SubSmall= i;

            Temp= List[SubSmall];
            List[SubSmall] = List[Start];
            List[Start] = Temp;
        }
    }

    public static void main(String[] args)
    {
        String menuChoice;
        do
        {
            displayMenu();
            menuChoice = input.nextLine();
            switch (menuChoice.toLowerCase())
            {
                case "1" -> explainParadox();
                case "2" -> verifyParadox();
                case "3" -> displaySet();
                case "e" -> {
                }
                default -> {
                    System.out.println("Invalid menu choice, please select a valid menu option.");
                    displayMenu();
                }
            }
        } while (!menuChoice.equalsIgnoreCase("e"));

        System.out.println("\nGoodbye...\n");
    }
}

/* ************************  Test Data  *******************************
int[] test1 = {
    5, 18, 31, 44, 57, 70, 83, 96, 109, 122, 135,
    148, 161, 174, 187, 200, 213, 226, 239, 252, 265, 278, 291
};

int[] test2 = {
    5, 5, 5, 17, 29, 41, 53, 65, 77, 89, 101,
    113, 125, 137, 149, 161, 173, 185, 197, 209, 221, 233, 245
};

int[] test3 = {
    12, 24, 36, 48, 60, 72, 84, 96, 108, 120, 132,
    144, 156, 168, 180, 192, 204, 216, 228, 240, 283, 283, 283
};

int[] test4 = {
    90, 90, 15, 33, 57, 61, 88, 120, 143, 167,
    168, 168, 168, 199, 210, 222, 240, 255, 271,
    300, 300, 315, 360
};

int[] test5 = {
    1, 1, 1, 1, 14, 28, 42, 56, 70, 84,
    200, 200, 200, 215, 230, 245, 260, 275, 290,
    365, 365, 310, 330
};

int[] test6 = {
    62, 9, 17, 24, 31, 38, 45, 52, 59, 66,
    120, 73, 80, 87, 94, 101, 108, 115, 122,
    225, 132, 62, 120
};



************************  Program Output w/ Test Data  *******************************
test1
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\HW 3\src>java Homework3.java
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    January    5     January   18     January   31
    February  13     February  26     March     11
    March     24     April      6     April     19
    May        2     May       15     May       28
    June      10     June      23     July       6
    July      19     August     1     August    14
    August    27     September  9     September 22
    October    5     October   18

1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
e

Goodbye...


test2
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\HW 3\src>java Homework3.java
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
(3) January    5     January   17     January   29
    February  10     February  22     March      6
    March     18     March     30     April     11
    April     23     May        5     May       17
    May       29     June      10     June      22
    July       4     July      16     July      28
    August     9     August    21     September  2


1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
e

Goodbye...

test3
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\HW 3\src>java Homework3.java
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    January   12     January   24     February   5
    February  17     March      1     March     13
    March     25     April      6     April     18
    April     30     May       12     May       24
    June       5     June      17     June      29
    July      11     July      23     August     4
    August    16     August    28 (3) October   10


1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
e

Goodbye...


test4
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\HW 3\src>java Homework3.java
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    March     31     January   15     February   2
    February  26     March      2     March     29
    March     31     April     30     May       23
    June      16 (3) June      17     July      18
    July      29     August    10     August    28
    September 12     September 28 (2) October   27
    November  11     December  26


1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
e

Goodbye...


test5
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\HW 3\src>java Homework3.java
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
(4) January    1     January   14     January   28
    February  11     February  25     March     11
    March     25 (3) July      19     August     3
    August    18     September  2     September 17
    October    2     October   17     November   6
    November  26 (2) December  31

1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
e

Goodbye...

test6
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\HW 3\src>java Homework3.java
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    March      3     January    9     January   17
    January   24     January   31     February   7
    February  14     February  21     February  28
    March      3     March      7     March     14
    March     21     March     28     April      4
    April     11     April     18     April     25
(2) April     30     May        2     May       12
    August    13

1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
e

Goodbye...


************************  Program Output w/o Test Data *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 5-Module 5\HW 3\src>java Homework3.java
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
1
If 23 persons are chosen at random, then the chances are more than 50% that at least twowill have the same birthday!
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
2
Generating 1000 sets of 23 birthdays and checking for matches.
Results: 517 out of 1000 (51.7%) of the sets contained matching birthdays.
============================================================================
1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    December   5     January    1     January   20
    February  14     March     10     March     13
    March     18     March     29     April     20
    May       29     June       6     July      10
    August     2     August     3 (2) August    19
    September 20     October    4     October   15
    October   31     November  13     November  21
    November  29

1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    October   26     January    9     February   4
    February  18     March      2     March      3
    March     13     March     15 (2) May       10
    May       25     June       2     June      10
    June      20     July       6     August     3
    August    11     September 17     October    9
    October   16     November   8     November   9
    November  27

1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    March      1     January   19     January   29
    January   31     February   6     March      2
    March      9     March     12     March     26
    March     28     May        4     May       13
    July      12     July      24     July      26
    August     4     August    16     August    18
    August    19     September  9     October    7
    October   17     November  30

1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
3

Here are the results of generating a set of 23 birthdays
===========================================================
    October   16     January   11     January   23
    January   27     March      9     March     11
    March     13     March     18     March     22
    March     28 (2) April     22     April     30
    July      12     July      22     July      31
    August    16     September 14     September 25
    November   8     November  16     November  22
    November  27

1: Explain Birthday Paradox
2: Check birthday paradox by generating 1000 sets of birthdays
3: Display one set of 23 birthdays
E: Exit
e

Goodbye...
*/