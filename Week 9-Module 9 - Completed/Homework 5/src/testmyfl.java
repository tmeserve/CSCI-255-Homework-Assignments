/* ************************************************************************
  PROGRAMMER: Matt Holloway and John Russo
  SOURCE:     TestMyFl.java

  COMPILER:   NetBeans

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
              simple program below to test your Write function. To do this need to 
              first let following be set in your class Constructor:

                  NumberofDigits = 3;  
                  Number[1] = 1;   
                  Number[2] = 2;
                  Number[3] = 3;

            Then create a little program and do and type as follows:

            public class TestWrite
            {
               public static void main(String[] args)
               {
                  MyFloat X = new MyFloat();
      
                  System.out.print("X = ");
                  X.Write();
                  System.out.println();      
               }   
            }

            This should produce as output  0.123
            Now let NumberofDigits = 0 and rerun and should get 0.?. 

            Once these both work, the Write function should be OK and you can put 
            the constructor back to normal.         

  **NOTE** In MyFloat class data member "char {} Number" ignore the zero
           subscript when filling in the Number array, so start at
           subscript number 1 
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
import java.util.*;        

public class testmyfl 
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
            case '1': TestRead();
                      break;
            case '2': TestDigits();
                      break;
            case '3': TestAddition();
                      break;
            case '4': TestComparison();
                      break;
            case 'Q': System.out.println("Good Bye");       // Exit
         };
      } while ( Choice != 'Q' );
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
         System.out.println("   1)  Test Read function");
         System.out.println("   2)  Test MaxDigits and Digits");
         System.out.println("   3)  Test Addition");
         System.out.println("   4)  Test equality");
         System.out.println("   Q)  Quit program");
         System.out.print("\nChoice? --> ");
         Choice = Character.toUpperCase(Input.next().charAt(0));        
      
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

   /* ********************  TestRead   ***************************************
      Allows testing of the member function Read of MyFloat.

      The Write function is assumed to be correct!
   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
   public static void TestRead() throws java.io.IOException
   {
      MyFloat X = new MyFloat();
      
      System.out.println("\n============ Testing Read function ==================");
      
      do
         {
            System.out.print("\nEnter a MyFloat ==> ");
            X.Read();
            
            if ( X.Digits() == 0 )             //  No significant digits
            {
               System.out.println("\nInput error! ");
               Input.nextLine();    //removes remaining characters if error
            }
            
            System.out.print("\nAfter read,   X = '");
            X.Write();
            System.out.println("'");
            
            if (Input.hasNext())
               Input.nextLine(); //removes any remaining characters 
         }
      while ( KeyToContinue() );
   }

   /* ********************  TestDigits   *************************************
      Allows testing of the member functions Digits and MaxDigits.

      This routine assumes that input and output functions for MyFloats
      have been written and debugged.
   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
   public static void TestDigits() throws java.io.IOException
   {
      MyFloat X = new MyFloat();
      
      System.out.println("\n============ Testing Digits and MaxDigits  ============\n");
      System.out.println("X.MaxDigits() = " + X.MaxDigits());

      do
      {
         System.out.print("\nEnter MyFloat ==> ");
         X.Read();
         System.out.print("\nDigits("); 
         X.Write();
         System.out.println(") = " + X.Digits());
         
         if (Input.hasNext())
               Input.nextLine(); //removes any remaining characters
      }
      while ( KeyToContinue() );
   }

   /* ********************  TestAddition   *********************************
      Allows testing of the member functions "Add" of MyFloat.

      This routine assumes that input and output functions for MyFloats
      have been written and debugged.
   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
   public static void TestAddition() throws java.io.IOException
   {
      MyFloat X = new MyFloat();
      MyFloat Y = new MyFloat();
      MyFloat Z = new MyFloat();
      
      System.out.println("\n============ Testing Addition for MyFloats ============");
      
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
         
         System.out.print("Enter another MyFloat Y ==> ");
         Y.Read();
               
         if ( Y.Digits() == 0 )         //  Error in input Y, no digits
         {
            System.out.println("\nFormat error! ");
            System.out.print("\n     'Y = ");
            Y.Write();
            System.out.println("'");
            Input.nextLine(); //removes any remaining characters
            continue;                 // no calculation redo loop
         }
         
         Z = X.Add(Y);                   // add two MyFloats together
         
         if ( Z.Digits() == 0 )         //  Error in addition
            System.out.println("\nFormat error! ");
         
         System.out.print("\n  X    ");
         X.Write();
         
         System.out.print("\n+ Y    ");
         Y.Write();
         
         System.out.print("\n---------");
         
         int i;
         for (i = 1; i <= Z.Digits(); i++)   // this displays '---------'
            System.out.print("-");           // to output device
         
         System.out.print("\n  Z    ");
         Z.Write();
         System.out.println();
         
         if (Input.hasNext())
            Input.nextLine(); //removes any remaining characters 
         
      }
      while ( KeyToContinue() );
   }

   /* ********************  TestComparison   *********************************
      Allows testing of the member function Equal.

   This routine assumes that input and output functions for MyFloats
   have been written and debugged.

   This routine will not check to make user input is error free.
   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
   public static void TestComparison() throws java.io.IOException
   {
      MyFloat X = new MyFloat();
      MyFloat Y = new MyFloat();
      System.out.println("\n\n============ Testing Equality for MyFloats  ===========");

      do
      {
         System.out.print("\nEnter X  ==> ");
         X.Read();
         
         System.out.print("Enter Y  ==> ");
         Y.Read();    
         
         System.out.println("\n");
         
         if ( X.equals(Y) )
         {
            X.Write();
            System.out.print(" is equal to ");
            Y.Write();
         }
         else
         {
            X.Write();
            System.out.print(" is NOT equal to ");
            Y.Write();
         }
         
         System.out.println("\n");
         if (Input.hasNext())
            Input.nextLine(); //removes any remaining characters 
         
      }
      while ( KeyToContinue() );
      }
}