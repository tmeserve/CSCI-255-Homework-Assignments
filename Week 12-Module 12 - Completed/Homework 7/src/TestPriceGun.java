/*          TestPriceGun.java

Program used to test the class PriceGun. 
 ----------------------------------------------------------------------------*/
import java.util.Scanner;

public class TestPriceGun
{
   static Scanner Input = new Scanner(System.in);
   static boolean Request = false;          //global variable for this file
   
   public static void main(String[] args)
   {
      char Ch;      
      
      PriceGun Jim = new PriceGun();
      PriceGun Sue = new PriceGun(500,0,0);
      
      System.out.println("Let's go shopping!");
      System.out.println("Jim will go first, with a budget of $" + Jim.Budget());
      System.out.println("Jim is a party animal so he is buying supplies for a");
      System.out.println("party this coming weekend, and cannot go over budget.\n");
      
      char Choice;      
      do
      {
         Choice = DisplayOptions();        

         System.out.println();
         switch ( Choice )
         {
            case '1': GoShopping(Jim);
                      break;
            case '2': AskMomDad(Jim);
                      break;
            case '3': Jim.ClearPrice();
                      Request = false;   // allow to ask again
                      break;            
            case 'Q': System.out.println("Good Bye Jim");       // Exit
         };
      }while ( Choice != 'Q' );   
      
      System.out.println("\nThis is what you have Jim.");
      Jim.DisplayAll();
      
      System.out.println("\n\nNow it is little Sue's turn to shop!");
      System.out.println("She comes from a wealthy family and has a higher ");
      System.out.println("budget of $" + Sue.Budget() + ", and she loves to ");
      System.out.println("buy clothes, but still has to stay within budget.");
      
      Request = false;   // Let Sue get one request
      do
      {
         Choice = DisplayOptions();        

         System.out.println();
         switch ( Choice )
         {
            case '1': GoShopping(Sue);
                      break;
            case '2': AskMomDad(Sue);
                      break;
            case '3': Sue.ClearPrice();
                      Request = false;   // allow to ask again
                      break;            
            case 'Q': System.out.println("Good Bye Sue");       // Exit
         };
      }while ( Choice != 'Q' );
      
      System.out.println("\nThis is what you have Sue");
      Sue.DisplayAll();
      
      System.out.println("\nLet's see who spent the most!");
      if (Jim.isGreater(Sue))
         System.out.println("Jim spent more by $" + 
                             (Jim.CurrentPrice() - Sue.CurrentPrice()));
      else
         System.out.println("Sue spent more by $" +
                 (Sue.CurrentPrice() - Jim.CurrentPrice()));
      
      System.out.println("\n\nJim and Sue are getting married!! About time!");
      System.out.println("They will now be able to merge their price guns.");
      System.out.println("\nThis is what they now have together");
      
      PriceGun Both;
      Both = Jim.Merge(Sue);
      
      Both.DisplayAll();
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
      System.out.println("\nWhat do you want to do?");

      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

      System.out.println("   1)  Go Shopping");

      System.out.println("   2)  Ask Mom and Dad for more money");

      System.out.println("   3)  Empty cart and restart");   

      System.out.println("   Q)  Quit Shopping");

      System.out.print("\nChoice? --> ");     
   
      Choice = Character.toUpperCase(Input.next().charAt(0));        
   
      if (((Choice != '1') && (Choice != '2') && (Choice != '3')
            && (Choice != 'Q')))
      {
         System.out.print("Incorrect choice, choose again!!\n\n");
         Repeat = false;
      }    
   } 
   while (!Repeat);
   
   return Choice;        
}   

/* ********************* AskMomDad ***************************************
Action will allow the user to request money from parents. If request is double 
the budget it is automatically denied. If request is under double, then a 65% 
chance the new budget will be approved. Only allowed to request one time, unless 
you reset everthing back to default values.
------------------------------------------------------------------------------*/
public static void AskMomDad(PriceGun J)
{
   float NewLimit;
   
   if (!Request)
   {
     System.out.println("How much do you want to ask for, be reasonable? ");
     NewLimit = Input.nextFloat();
   
     if (NewLimit > 2*J.Budget())  
        System.out.println("Way too much, request denied");
     else
     {
        if (Math.random() >= 0.45)
        {
          System.out.println("OK, budget increased to $" + NewLimit);
          J.SetMaxPrice(NewLimit);
        }
        else
           System.out.println("Sorry request is denied");
     }     
     Request = true;
   }
   else
      System.out.println("!!! Cannot request again !!!");
}

/* ************************** GoShopping **************************************
Action: Will allow an object of PriceGun to shop.  Will record each purchase 
and display current total. If go over budget, then will let you know and ask 
to remove the last item.
--------------------------------------------------------------------------------*/
public static void GoShopping(PriceGun J)
{
   char Ch;
   System.out.print("Start shopping!!. ");
      do
      {      
         if (J.UnderBudget())  // if true, keep shopping
         {
            J.Scan();
            System.out.println("Item added to list");
            System.out.println("Current Price is $" + J.CurrentPrice());
         }
         else
         {
            System.out.println("\n-------- OVER BUDGET -------------");
            System.out.print("Delete last item?  Enter D to delete ");
            Ch = Input.next().charAt(0);
            if (Ch == 'D' || Ch == 'd')
            {
               J.DeleteItem();
               System.out.println("\nCurrent Price is now $" + J.CurrentPrice());               
            }
         }         
         System.out.print("Continue shopping, Y or N ");
         Ch = Input.next().charAt(0);
      } while ( Ch == 'Y' || Ch == 'y');      
}
}