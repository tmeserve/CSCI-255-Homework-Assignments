/*
Name: Tyler Meserve
Date: 2/22/2026
Homework #: Homework 4
Source file: Homework4.java
Class: 255 Online
Action: This program will only work by using command line arguments, the program will read a file and count the
    characters, words, and line count. These can be toggled off by using the options l, w, c. 
    Example runs:
        java Homework4.java words.1 /lwc
        java Homework4.java words.1
        java Homework4.java words.1 /w
External Assistance Provided By:
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class Homework4
{
    public static void main(String[] args) throws IOException
    {
        CmdLineRecord user = new CmdLineRecord();
        CountsRecord data = new CountsRecord();
        int fileOpened = 1;

        DetermineWhatUserWants(user, args);
        if (user.SyntaxError==0)
            fileOpened = CountLineWordChar(data, args[0]);
        if (fileOpened == 1)
            ReportResults(user, data);
        else
            System.err.print("Cannot open file " + args[0] + "\n");
    }

/* *************  DetermineWhatUserWants  ***************************************
    Action  :  Function will determine what the user wants to have displayed
            for the count, based on command line input, i.e. does user
            want to display how many lines or words or characters or
            combination of them, or if an input error occurred. First 
            argument in command line must be the file name the second argument
            is the option of display.

            Command line options are inputed after the "/" char and are:
                    c : character count
                    w : word count
                    l : line count

            If no options are inputed then the default of display all count
            totals is done, otherwise the option is done.  One option or two
            is permitted after the "/", illegal character option will result
            in a syntax error.

    Parameters:
    U      : User variable of class type given above
    Arg      : array of strings holding the arguments 
        
    Returns    : nothing
    Preconditions : None
=================================================================================*/
    static void DetermineWhatUserWants(CmdLineRecord U, String[] Arg)
    {
        U.SyntaxError = 0;           // assume no syntax error
        if (Arg.length == 1)
        {
            U.WantsLineCount = true;
            U.WantsCharCount = true;
            U.WantsWordCount = true;
        }
        else if (Arg.length == 2)
        {
            if ((char) Arg[1].toCharArray()[0] != '/')
            {
                U.SyntaxError = 3;
                return;
            }
            else if (Arg[1].toCharArray().length == 1)
            {
                U.SyntaxError = 4;
                return;
            }
            for (char c: Arg[1].toCharArray())
            {
                switch (Character.toLowerCase(c))
                {
                    case 'l' -> U.WantsLineCount = true;
                    case 'c' -> U.WantsCharCount = true;
                    case 'w' -> U.WantsWordCount = true;
                }
            }
        }
        else if (Arg.length > 2)
            U.SyntaxError = 1;
        else
            U.SyntaxError = 2;
    }

/* ********************  CountLineWordChar  ************************************
    Action  :  Function will count the number of characters, words and lines
            in given input stream of text ended by control Z, ^Z or EOF

    Parameters:
        Data    : variable of type class given above
        File  : second command line argument that has the file to read from

    Returns   : 1 if file opened, 0 if can not open file or not found or error

    NOTE   :  Characters are everything, including newline and form feed.
            Words are delimited by whitespace characters and EOF.
            Does not take into consideration hypentation, words are composed
            numbers and letters, punctuation also included in words.

    Precondition : none
================================================================================*/
    static int CountLineWordChar(CountsRecord Data, String File) throws IOException
    {
        BufferedReader FileIn;
        Data.CharCount = 0;
        Data.WordCount = 0;
        Data.LineCount = 0;
        try
        {
            FileIn = new BufferedReader(new FileReader(File));    // declare FileIn to be input file       
        }
        catch (FileNotFoundException e)
        {
            return 0;
        }

        // checks if file is empty first
        int ch = FileIn.read();
        if (ch == -1)
            return 1;

        String word = "";
        while (ch != -1)
        {
            if (Character.isWhitespace(ch))
            {
                if ((int) ch == 10)
                    Data.LineCount++;
                if (!word.isBlank())
                    Data.WordCount++;
                word = "";
            }
            else
                word += (char) ch;
            Data.CharCount++;
            ch = FileIn.read();
        }

        if (!word.isBlank()) // Accounts for a word at the very end of the file without a whitespace
            Data.WordCount++;
        if (Data.CharCount > 0) // Accounts for the last line in the file or the first line if only 1 line
            Data.LineCount++;
        return 1;
    }

/* *********************  ReportResults  ********************************
Action  :  Function will display the number of words, lines or characters
           or all of them depending on what the user entered on the command
           line input.
Parameters :
  User - what to display
  Data - holds the number to display
Returns    : nothing
======================================================================*/
    static void ReportResults(CmdLineRecord User, CountsRecord Data)
    {
        switch (User.SyntaxError) {
            case 1 -> System.out.println("Too many arguments provided, please only provide the file name and/or options. IE java Homework4.java words.1 /lwc");
            case 2 -> System.out.println("Unknown syntax error, please only provide the file name and/or options. IE java Homework4.java words.1 /lwc");
            case 3 -> System.out.println("Options require a \"/\" beforehand to be recognized. IE java Homework4.java /c");
            case 4 -> System.out.println("Options flag provided but no options were given, please provide the options l, w, or c.");
            default -> {
                if (User.WantsCharCount)
                    System.out.println("Characters = " + Data.CharCount);
                if (User.WantsWordCount)
                    System.out.println("Words = " + Data.WordCount);
                if (User.WantsLineCount)
                    System.out.println("Lines = " + Data.LineCount);
            }
        }
    }

}

class CmdLineRecord
{
    int SyntaxError;                      // error codes for syntax eror
    boolean WantsLineCount = false;               // initialize all wants to false to start
    boolean WantsWordCount = false;               
    boolean WantsCharCount = false;               
}

class CountsRecord
{
    int LineCount;          // number of lines
    int WordCount;          // number of words
    int CharCount;          // number of characters
}

/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.1
Characters = 12
Words = 3
Lines = 1

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.2 /c
Characters = 100

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.3     
Characters = 0
Words = 0
Lines = 0

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.4      
Characters = 355
Words = 64
Lines = 6

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.5
Characters = 23
Words = 3
Lines = 3

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.5 /
Options flag provided but no options were given, please provide the options l, w, or c.

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.5 w
Options require a "/" beforehand to be recognized. IE java Homework4.java /c

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 7-Module 7\HW4\src>java Homework4.java words.5 w 234
Too many arguments provided, please only provide the file name and/or options. IE java Homework4.java words.1 /lwc
*/