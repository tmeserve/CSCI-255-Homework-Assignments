/* ************************************************************************
  PROGRAMMER: Matt Holloway and John Russo
  SOURCE:     TestMyFl2.java

  COMPILER:   NetBeans

  ACTION:     Driver to test more functions in the class of
              MyFloat.java. At the present, these include.

              isGreater    Method to compare two MyFloats, returns true if first 
                           on is greater than the second.	

              toString     Method to convert MyFloat to a string. For example, 
                           if MyFloat is 0.123, then will have a string “0.123”.  
                           If error, then string has “0.?”.  This method has no 
                           parameters and returns a String.

              toMyFloat    Method will Convert a string representing a float 
                           between 0 and 1 to a MyFloat.  For example, if X is an 
                           instance of MyFloat, a programmer could use 
                                 X.toMyFloat("0.3482"); 
                           to store 0.3482 in X.  Has one parameter of type String 
                           and is void.

            Copy Constructor  Used when instance of MyFloat is created with another
                              object of MyFloat.  Make sure to copy each element 
                              in the array.

             SetMyFloat    Method will have two parameters, first on is number of
                           elements in a character array of numbers, second 
                           parameter is the character array containing the numbers.
                           Remember these are both data type char, as are the 
                           private data members in MyFloat.  This is called within 
                           the test driver in the TestCopyConstructor function.

  IMPORTANT:  This test driver is assuming all functions tested in 
                  testmyfl.java are working correctly.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
import java.util.*;        

public class testmyfl2 
{
   static Scanner Input = new Scanner(System.in).useDelimiter( "(\\b|\\B)" );
   
                 /* the use of .useDelimiter("(\\b|\\B)") above, 
                 will use boundaries of every characters as delimiter. */

   public static void main(String[] args) throws java.io.IOException
   {
      char Choice;      
      do
      {
         Choice = DisplayOptions();        

         System.out.println();
         switch ( Choice )
         {
            case '1': TesttoMyFloat();
                      break;
            case '2': TesttoString();
                      break;
            case '3': TestCopyConstructor();
                      break;
            case '4': TestisGreaterThan();
                      break;
            case 'Q': System.out.println("Good Bye");       // Exit
         };
      }while ( Choice != 'Q' );
}

/* **********************  KeyToContinue    **************************
   Displays "Press Any Key then Enter key to continue
             or just Enter Key to quit" 
   Returns: true iff Any key pressed.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
public static boolean KeyToContinue() throws java.io.IOException
{ 
  String Extra = null;
  
  System.out.println("\n-------Press Any key then Enter key to continue");
  System.out.print("-------or just Enter Key to quit: ");
  
  Extra = Input.nextLine();
  if (Extra.isEmpty())
     return false;
  else
     return true; 
}

/* **********************  DisplayOptions   *******************************
Action:  Displays a menu of choices and repeats if wrong choice inputted.
returns: user's choice as a character
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
public static char DisplayOptions()
{
   char Choice;
   boolean Repeat;
 
   do
   {
      Repeat = true;
      System.out.println("\nTesting Member Functions of MyFloat Class");

      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

      System.out.println("   1)  Test toMyFloat function");

      System.out.println("   2)  Test toString function");

      System.out.println("   3)  Test Copy Constructor");

      System.out.println("   4)  Test isGreater function");

      System.out.println("   Q)  Quit program");

      System.out.print("\nChoice? --> ");     
   
      Choice = Character.toUpperCase(Input.next().charAt(0)); 
      
      Input.nextLine();
   
      if (((Choice != '1') && (Choice != '2') && (Choice != '3') && (Choice != '4')
            && (Choice != 'Q')))
      {
         System.out.print("Incorrect choice, choose again!!\n\n");
         Repeat = false;
      }    
   } 
   while (!Repeat);
   
   return Choice;        
}

/* ********************  TesttoMyFloat   ***************************************
   Allows testing of the member function toMyFloat of MyFloat.

   The Write function is assumed to be correct!
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
public static void TesttoMyFloat() throws java.io.IOException
{
  MyFloat X = new MyFloat();
  
  String N;   
  
  System.out.println("\n========= Testing toMyFloat function ================");
  
  do
    {       
       System.out.print("\nEnter a MyFloat as a String ");
       N = Input.nextLine();
       
       if (N.length() == 0)
       {
          System.out.println("\nInput error! ");
          System.out.println("0.?");
       }
       else
       {        
         X.toMyFloat(N);   
       
         if ( X.Digits() == 0 )             //  No significant digits
            System.out.println("\nInput error! ");        
              
         System.out.print("\nAfter method toMyFloat,   X = '");
         X.Write();
         System.out.println("'");  
       }
       
       if (Input.hasNext())
          Input.nextLine();       //removes any remaining characters 
    }
  while ( KeyToContinue() );
}

/* ********************  TesttoString   *************************************
   Allows testing of the member functions toString.

   This routine assumes that input and output functions for MyFloats
   have been written and debugged.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
public static void TesttoString() throws java.io.IOException
{
  MyFloat X = new MyFloat();
  
  System.out.println("\n============ Testing toString Function  ============");

  do
  {
     System.out.print("\nEnter MyFloat ==> ");
     
     X.Read();
   
     System.out.print("\nString value is \""); 
     System.out.print(X.toString());
     System.out.println("\"");
         
     if (Input.hasNext())
          Input.nextLine();        //removes any remaining characters
  }
  while ( KeyToContinue() );
}

/* ********************  TestCopyConstructor   *********************************
   Allows testing of the copy constructor of MyFloat.

   This routine assumes that input and output functions for MyFloats
   have been written and debugged.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
public static void TestCopyConstructor() throws java.io.IOException
{
   MyFloat X = new MyFloat();   
   
   System.out.println("\n======= Testing Copy Constructor for MyFloats ==========");
   
   do
   {
      System.out.print("\nEnter a MyFloat X ==> ");
      X.Read();
    
      if ( X.Digits() == 0 )         //  Error in input X, no digits
      {
         System.out.println("\nFormat error! ");
         System.out.print("\n     'X = ");
         X.Write();
         System.out.println("'" );
         Input.nextLine(); //removes any remaining characters 
         continue;                   // no more input redo loop
      }
      
      MyFloat Y = new MyFloat(X);   // calls copy constructor
      
      System.out.print("X = ");
      X.Write();
      System.out.println();
      
      System.out.println("After creating Y with copy Constructor ");
      System.out.print("Y = ");
      Y.Write();
      System.out.println();
      
      System.out.println("\nNow changing X to 0.1234");
      
      char [] M = {1, 2, 3, 4};      
      X.SetMyFloat((char)4,M);      // now change X, and Y should be different
      
      System.out.print("X = ");
      X.Write();
      System.out.println();
      
      System.out.print("Y = ");
      Y.Write();
      System.out.println();   
      
      if(X.equals(Y))
         System.out.println("Copy Constructor not working, Y has been changed");
      else
         System.out.println("Copy Constructor is working, Y not affected");
      
      if (Input.hasNext())
          Input.nextLine();      //removes any remaining characters       
    }
  while ( KeyToContinue() );
}

/* ********************  TestGreaterThan   *********************************
   Allows testing of the member function isGreater.

This routine assumes that input and output functions for MyFloats
have been written and debugged.

This routine will not check to make user input is error free.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
public static void TestisGreaterThan() throws java.io.IOException
{
  MyFloat X = new MyFloat();
  MyFloat Y = new MyFloat();
  System.out.println("\n======== Testing isGreater than for MyFloats  =========");

  do
  {
     System.out.print("\nEnter X  ==> ");
     X.Read();
   
     System.out.print("Enter Y  ==> ");
     Y.Read();    
    
     System.out.print("\n");
     
     if ( X.isGreater(Y) )
     {
        X.Write();
        System.out.print(" is Greater than ");
        Y.Write();
     }
     else
     {
        Y.Write();
        System.out.print(" is Greater than ");
        X.Write();
     }
     
     System.out.println();
     if (Input.hasNext())
        Input.nextLine();       //removes any remaining characters      
  }
  while ( KeyToContinue() );
}
}