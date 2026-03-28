
import java.io.IOException;
import java.util.Scanner;

/*
Name: Tyler Meserve
Date: 3/27/2026
Homework #: Homework 5
Source file: MyFloat.java?? Not sure if it's supposed to be this or testmyfl.java
Class: 255 Online
  ACTION:     Driver to test functions in the class member functions of
              MyFloat.java. At the present, these include.

              Read          Reads a MyFloat from standard intput. Standard
                            reading conventions should be followed.

              Write         Writes a leading 0, a decimal point, and the
                            digits in a MyFloat to standard output.

              Digits        Returns the number of digits of a MyFloat.

              MaxDigits     Returns the maximum number of digits of MyFloat.

              Add           Adds two MyFloat variables together using standard
                            addition rules and returns a MyFloat. Carry over into 
                            the ones place on left of decimal is ignored and left 
                            to be zero.
                            Example output:
                              X   0.514
                            + Y   0.52
                            -----------
                              Z   0.034       note 5+5 resulted in 0 with no
                                              carry over into ones place

              equals        Returns true if two MyFloats are equal, false otherwise.

   default constructor      sets NumberofDigits to 0


  IMPORTANT:  The program assumes that the Write function has been tested
              and is working properly. Consider using the following
              simple program below to test your Write function.
   

  **NOTE** In MyFloat class data member "char {} Number" ignore the zero
           subscript when filling in the Number array, so start at
           subscript number 1 
*/

class MyFloat
{
    private final int MAX_DIGITS = 20;
    private char[] smallFloat = new char[MAX_DIGITS + 1];
    private char numberOfDigits = (char) 0;

    public MyFloat()
    {}

    public int Digits()
    { return this.numberOfDigits; }

    public int MaxDigits()
    { return this.smallFloat.length - 1; }

    /*
    Action: Reads input from the user and constructs a MyFloat value by extracting digit characters (up to 20) after an optional decimal point.
    Params: void
    Returns: void
    Precondition: Input must come from standard input and should contain valid numeric characters (digits and at most one decimal point); the smallFloat array must have enough space to store up to 20 digits
    */
    public void Read() throws IOException
    {
        Scanner s = new Scanner(System.in);
        boolean decimalEncountered = false;
        boolean errorEncountered = false;
        
        // clears all initial whitespaces
        int ch = 0;
        do
        {
            ch = System.in.read();
        } while (Character.isWhitespace(ch));
        
        do
        {
            char c = (char) ch;
            if (Character.isDigit(c))
            {
                // Looks ahead if the first digit is a 0 and hasn't encountered a decimal yet, stops when another digit is encountered or an error is found
                if (c == '0' && !decimalEncountered)
                {
                    boolean lookAhead = true;
                    int ch2 = 0;
                    char c2;
                    while (lookAhead)
                    {
                        ch2 = System.in.read();
                        c2 = (char) ch2;
                        if (c2 == '0' || c2 == ' ')
                            continue;
                        
                        if (c2 == '.')
                        {
                            lookAhead = false;
                            decimalEncountered = true;
                        }
                        // This is something like 0'123, 0A123, 0A0.123
                        else if (!Character.isDigit(c2))
                        {
                            lookAhead = false;
                            errorEncountered = true;
                        }
                        else if (Character.isDigit(c2))
                        {
                            this.numberOfDigits = (char) (this.numberOfDigits + 1);
                            this.smallFloat[(int) this.numberOfDigits] = c;
                            this.numberOfDigits = (char) (this.numberOfDigits + 1);
                            this.smallFloat[(int) this.numberOfDigits] = c2;
                            lookAhead = false;
                        }
                    }
                }
                else
                {
                    this.numberOfDigits = (char) (this.numberOfDigits + 1);
                    this.smallFloat[(int) this.numberOfDigits] = c;
                }
            }
            else if (!Character.isDigit(c))
            {
                if (c == '.')
                {
                    if (decimalEncountered)
                        break;
                    decimalEncountered = true;
                    ch = System.in.read();
                    continue;
                }
                else if (c == ' ')
                {
                    ch = System.in.read();
                    continue;
                }
                break;
            }

            ch = System.in.read();
        } while ((ch != -1 && !Character.isAlphabetic(ch) && this.numberOfDigits != (char) 20) && !errorEncountered);
    }

    /*
    Action: Outputs the MyFloat value to the console in decimal format starting with "0." followed by its digits.
    Params: None
    Returns: void
    Precondition: The MyFloat object must be properly initialized, with numberOfDigits representing the count of digits and smallFloat containing valid digit characters ('0'–'9')
    */
    public void Write()
    {
        if (this.numberOfDigits == (char) 0)
        {
            System.out.println("0.?");
            return;
        }

        String toPrint = "0.";
        for (int i=1; i < (int) this.numberOfDigits+1; i++)
            toPrint += this.smallFloat[i];
        
        System.out.println(toPrint);
    }

    /*
    Action: Adds this MyFloat object with another MyFloat object digit-by-digit and returns the result as a new MyFloat.
    Params: y - another MyFloat object whose value will be added to this object
    Returns: A new MyFloat object (z) containing the result of adding the two MyFloat values
    Precondition: y must not be null, and both MyFloat objects must be properly initialized with valid numberOfDigits and smallFloat arrays containing digit characters ('0'–'9')
    */
    public MyFloat Add(MyFloat y)
    {
        MyFloat z = new MyFloat();

        if (this.numberOfDigits > y.Digits())
            z.numberOfDigits = this.numberOfDigits;
        else
            z.numberOfDigits = y.numberOfDigits;

        for (int i=1; i <= z.numberOfDigits; i++)
        {
            if (i > this.numberOfDigits)
                z.smallFloat[i] = y.smallFloat[i];
            else if (i > y.numberOfDigits)
                z.smallFloat[i] = this.smallFloat[i];
            else
            {
                char num1 = this.smallFloat[i];
                char num2 = y.smallFloat[i];
                z.smallFloat[i] = (char) (((num1 - '0') + (num2 - '0')) % 10 + '0');
            }
        }
        return z;
    }

    /*
    Action: Compares this MyFloat object with another MyFloat object to determine if they are equal.
    Params: y - another MyFloat object to compare against this object
    Returns: true if both MyFloat objects have the same number of digits and identical values in their smallFloat arrays; false otherwise
    Precondition: y must not be null and should be a properly initialized MyFloat object with valid numberOfDigits and smallFloat values
    */
    public boolean equals(MyFloat y)
    {
        if (this.numberOfDigits != y.numberOfDigits)
            return false;

        for (int i=1; i <= this.numberOfDigits; i++)
        {
            if (this.smallFloat[i] != y.smallFloat[i])
                return false;
        }
        return true;
    }
}

/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 9-Module 9\Homework 5\src>java testmyfl.java

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 1


============ Testing Read function ==================

Enter a MyFloat ==> 0123

After read,   X = '0.0123
'

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 1


============ Testing Read function ==================

Enter a MyFloat ==> 0A123

Input error!

After read,   X = '0.?
'

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 1


============ Testing Read function ==================

Enter a MyFloat ==> 0.01234 

After read,   X = '0.01234
'

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 1


============ Testing Read function ==================

Enter a MyFloat ==> .1133  

After read,   X = '0.1133
'

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 2


============ Testing Digits and MaxDigits  ============

X.MaxDigits() = 20

Enter MyFloat ==> 1234

Digits(0.1234
) = 4

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 1


============ Testing Read function ==================

Enter a MyFloat ==> 1234A

After read,   X = '0.1234
'

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 3


============ Testing Addition for MyFloats ============

Enter a MyFloat X ==> 1234
Enter another MyFloat Y ==> 1234

  X    0.1234

+ Y    0.1234

-------------
  Z    0.2468


-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 4



============ Testing Equality for MyFloats  ===========

Enter X  ==> 1234
Enter Y  ==> 1234


0.1234
 is equal to 0.1234



-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> 4



============ Testing Equality for MyFloats  ===========

Enter X  ==> 1234
Enter Y  ==> 123


0.1234
 is NOT equal to 0.123



-------Press Any key then Enter key to continue
-------or just Enter Key to quit:
Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test Read function
   2)  Test MaxDigits and Digits
   3)  Test Addition
   4)  Test equality
   Q)  Quit program

Choice? --> q

Good Bye
*/