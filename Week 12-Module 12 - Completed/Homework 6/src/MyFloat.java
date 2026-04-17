
import java.io.IOException;

/*
Name: Tyler Meserve
Date: 4/2/2026
Homework #: Homework 6
Source file: MyFloat.java?? Not sure if it's supposed to be this or testmyfl.java
Class: 255 Online
*/

class MyFloat
{
    private final int MAX_DIGITS = 20;
    private char[] smallFloat = new char[this.MAX_DIGITS + 1];
    private char numberOfDigits = (char) 0;
    private boolean errorEncountered = false;

    public MyFloat()
    {}

    // Copy Constructor
    public MyFloat(MyFloat y)
    {
        char[] tempFloat = y.getSmallFloat();
        this.numberOfDigits = (char) y.Digits();

        for (int i=1; i<=this.numberOfDigits; i++)
            this.smallFloat[i] = tempFloat[i];
    }

    public char[] getSmallFloat()
    { return this.smallFloat; }

    public int Digits()
    { return this.numberOfDigits; }

    public int MaxDigits()
    { return this.smallFloat.length - 1; }

    @Override
    public String toString()
    {
        String s = "0.";
        if (this.errorEncountered)
            return s + "?";
        if (this.numberOfDigits == 0)
            return s + "0";
        for (int i=1; i<this.numberOfDigits+1; i++)
            s+=this.smallFloat[i];
        return s;
    }

    /*
    Action: Reads input from the user and constructs a MyFloat value by extracting digit characters (up to 20) after an optional decimal point.
    Params: void
    Returns: void
    Precondition: Input must come from standard input and should contain valid numeric characters (digits and at most one decimal point); the smallFloat array must have enough space to store up to 20 digits
    */
    public void Read() throws IOException
    {
        boolean decimalEncountered = false;
        
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
                            this.errorEncountered = true;
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
        } while ((ch != -1 && !Character.isAlphabetic(ch) && this.numberOfDigits != (char) 20) && !this.errorEncountered);
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
        
        System.out.print(toPrint);
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

    /*
    Action: Compares this MyFloat object with another and determines if it is greater.
    Params: y = the MyFloat object to compare against.
    Returns: true if this object is greater than y, otherwise false.
    Precondition: Both MyFloat objects must contain valid numeric values that can be converted to strings and parsed as doubles.
    */
    public boolean isGreater(MyFloat y)
    {
        double num1, num2;
        try { num1 = Double.parseDouble(this.toString()); }
        catch (NumberFormatException e) { return false; }
        
        try { num2 = Double.parseDouble(y.toString()); }
        catch (NumberFormatException e) { return true; }

        return num1 > num2;
    }

    /*
    Action: Converts a string representation of a float between 0 and 1 into the internal digit array format.
    Params: myFloat = string containing the float value to convert.
    Returns: void
    Precondition: myFloat is properly formatted (contains digits and at most one decimal point, representing a value between 0 and 1).
    */
    public void toMyFloat(String myFloat)
    {
        boolean decimalEncountered = false;
        char[] myFloatCharArray = myFloat.toCharArray();
        this.smallFloat = new char[this.MAX_DIGITS + 1];
        this.numberOfDigits = 0;

        for (int i=0; i<myFloatCharArray.length; i++)
        {
            char c = myFloatCharArray[i];
            if (Character.isDigit(c))
            {
                // Looks ahead if the first digit is a 0 and hasn't encountered a decimal yet, stops when another digit is encountered or an error is found
                if (c == '0' && !decimalEncountered && !(i+1==myFloatCharArray.length))
                {
                    boolean lookAhead = true;
                    int ch2 = i+1;
                    char c2;
                    while (lookAhead)
                    {
                        c2 = myFloatCharArray[ch2];
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
                            this.errorEncountered = true;
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
                    i = ch2;
                }
                else
                {
                    if (c == '0' && (i+1==myFloatCharArray.length))
                        continue;
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
                    continue;
                }
                else if (c == ' ')
                    continue;
                break;
            }
        }
    }

    /*
    Action: Sets the MyFloat object using a provided array of digit values.
    Params: elements = number of digits; temp = array containing digit values.
    Returns: void
    Precondition:  temp contains valid digit values (0–9) and elements does not exceed MAX_DIGITS.
    */
    public void SetMyFloat(int elements, char[] temp)
    {
        //
        this.numberOfDigits = (char) elements;
        this.smallFloat = new char[this.MAX_DIGITS];
        for (int i=1; i<=elements; i++)
        {
            this.smallFloat[i] = (char) (temp[i-1] + '0');
        }
    }
}

/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 12-Module 12\Homework 6\src>java testmyfl2.java

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test toMyFloat function
   2)  Test toString function
   3)  Test Copy Constructor
   4)  Test isGreater function
   Q)  Quit program

Choice? --> 1


========= Testing toMyFloat function ================

Enter a MyFloat as a String 12330

After method toMyFloat,   X = '0.1233'


-------Press Any key then Enter key to continue
-------or just Enter Key to quit:  

Enter a MyFloat as a String 32154A

After method toMyFloat,   X = '0.32154'
 

-------Press Any key then Enter key to continue
-------or just Enter Key to quit: q

Enter a MyFloat as a String 0.212150

After method toMyFloat,   X = '0.21215'


-------Press Any key then Enter key to continue
-------or just Enter Key to quit:

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test toMyFloat function
   2)  Test toString function
   3)  Test Copy Constructor
   4)  Test isGreater function
   Q)  Quit program

Choice? --> 2


============ Testing toString Function  ============

Enter MyFloat ==> 315

String value is "0.315"

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test toMyFloat function
   2)  Test toString function
   3)  Test Copy Constructor
   4)  Test isGreater function
   Q)  Quit program

Choice? --> 3


======= Testing Copy Constructor for MyFloats ==========

Enter a MyFloat X ==> 31561
X = 0.31561
After creating Y with copy Constructor
Y = 0.31561

Now changing X to 0.1234
X = 0.1234
Y = 0.31561
Copy Constructor is working, Y not affected

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test toMyFloat function
   2)  Test toString function
   3)  Test Copy Constructor
   4)  Test isGreater function
   Q)  Quit program

Choice? --> 3


======= Testing Copy Constructor for MyFloats ==========

Enter a MyFloat X ==> 6454AS
X = 0.6454
After creating Y with copy Constructor
Y = 0.6454

Now changing X to 0.1234
X = 0.1234
Y = 0.6454
Copy Constructor is working, Y not affected

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test toMyFloat function
   2)  Test toString function
   3)  Test Copy Constructor
   4)  Test isGreater function
   Q)  Quit program

Choice? --> 4


======== Testing isGreater than for MyFloats  =========

Enter X  ==> 5448
Enter Y  ==> 574

0.574 is Greater than 0.5448

-------Press Any key then Enter key to continue
-------or just Enter Key to quit:  

Testing Member Functions of MyFloat Class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Test toMyFloat function
   2)  Test toString function
   3)  Test Copy Constructor
   4)  Test isGreater function
   Q)  Quit program

Choice? --> q

Good Bye
*/