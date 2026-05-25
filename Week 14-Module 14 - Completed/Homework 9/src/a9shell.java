/* *************************************************************************
   AUTHOR  :   Tyler Meserve and Matt Holloway  (with John Russo )
                                     
   SOURCE  : Homework9.java

   DATE    : 4/22/2026

   COMPILER: NetBeans

   ACTION  : The program tests routines designed to perform various
             operations on singly linked lists. The lists have a
             dummy head node that holds the "Happy Face" character. 
             The tail of the lists points to NULL.

             This is an extended program of homework #8. The menu choices 
             include now reading in a list from an external file and sorting a list.
---------------------------------------------------------------------------------*/ 
import java.io.*;
import java.util.Scanner;

class Node
{
  final int DUMMY_VALUE = 1;  //  Value () stored in dummy head node.
  char  Ch;                   //  Holds the char data.
  Node Link;                  //  Points to another object of type Node.
}

public class a9shell
{
   static Scanner Input = new Scanner(System.in);  

   public static void main(String[] args)
   {      
      Node List = null;
      char MenuChoice;
      
      System.out.println("This program allows you to test the routines needed \n"
           + "for homework 8.");
      
      // Start list with Dummy Head Node
      
      List = new Node();
      List.Ch = (char)List.DUMMY_VALUE;
      List.Link = null;     
      
      do
      {
         MenuChoice = DisplayMenuAndGetMenuChoice();
         
         switch( MenuChoice )
         {
            case 'Q': break;                // Exit program
            case 'B': TestBuildList(List);
                      break;
            case 'A': TestAddNode(List);
                      break;
            case 'D': TestDeleteNode(List);
                      break;
            case 'Z': TestZapList(List);
                      break;
            case 'R':  TestReadList(List);
                      break;
            case 'S':  TestSortList(List);
                      break;
            default : System.out.println("\nError: '" + MenuChoice
                      + "' is not an option\n");
         }         
      }while ( MenuChoice != 'Q' );
}
   
/* ********************   DisplayMenuAndGetMenuChoice *********************
   Displays a menu of options and returns the user's choice.    
   The Choice is returned as uppercase.
---------------------------------------------------------------------------*/
public static char DisplayMenuAndGetMenuChoice ()
{
   char Choice;
   String [] Option = {"B(uildList", "A(ddNode", "D(eleteNode",
                       "R(eadList", "S(ortList","Z(apList","Q(uit","" };

   int K = 0;

   System.out.println("----------------------------------------------------");

   while ( Option[K] != "" )    // while we haven't gotten to ""
   {
     System.out.print(Option[K]);      // Display menu option
     System.out.print("  ");          // Add some white space.
     ++K;
   }
  
   System.out.print("=> ");
   Choice = Character.toUpperCase(Input.next().charAt(0));

   System.out.println("----------------------------------------------------");
   Input.nextLine();
   return Choice;
}

/* ************************    TestBuildList     **************************
   Facilitates the testing of the function BuildList, which is supposed
   to build an ordered linked list of characters.
--------------------------------------------------------------------------*/
public static void TestBuildList (Node List)
{
   System.out.println("\n================  Testing BuildList  ================");
   System.out.print("\nType the characters for the list -  " +
                       "when finished, press enter key\n\n -> ");

   BuildList(List);
   System.out.print("\nAfter BuildList, List = ");
   ShowList(List);
}

/* ***********************    TestAddNode  ********************************
   Facilitates the testing of the function AddNode, a function which
   adds a node to the tail end of a linked list. 
-------------------------------------------------------------------------*/
public static void TestAddNode (Node List)
{
   char NewChar; 
   
   System.out.println("\n================  Testing AddNode  =================\n");
   
   System.out.print("Character to be added? ");
   NewChar = Input.next().charAt(0);
   
   System.out.println(" --  Adding \'" + NewChar  + "\'");
   
   AddNode (NewChar, List);
   
   System.out.print("\nThe new list: ");
   ShowList(List);
}

/* **********************    TestDeleteNode   *****************************
   Facilitates the testing of DeleteNode, a function which is supposed
   to delete characters from a linked list.
----------------------------------------------------------------------------*/
public static void TestDeleteNode (Node List)
{
   boolean CharThere;
   char CharToBeDeleted;
  
   System.out.println("\n===============  Testing DeleteNode  ================\n");
   
   System.out.print("Character to be deleted? ");
   
   CharToBeDeleted = Input.next().charAt(0);
   
   CharThere = DeleteNode(CharToBeDeleted, List);
   
   if ( CharThere )
      System.out.println("\n'" + CharToBeDeleted + "' has been deleted,");
   else
      System.out.println("\n'" + CharToBeDeleted + "' was not in the list,");
  
   System.out.print("\nList = ");
   ShowList(List);
}

/* **********************    TestZapList  *********************************
   Facilitates the testing of ZapList, a function that is supposed to
   return all storage allocated for a linked list to the heap (except the
   storage occupied by the dummy head node).
----------------------------------------------------------------------------*/
public static void TestZapList (Node List)
{
   System.out.println("\n===============  Calling ZapList  ====================\n");
   
   ZapList(List);
   
   System.out.print("\nList = ");
   
   ShowList(List);
}

/* ***********************    TestReadList  ********************************
   Facilitates the testing of the function ReadList, a function which
   reads in a list from an external file.  The file is typed in by the
   user, if file does not exist, then an error will occur.
------------------------------------------------------------------------------*/
public static void TestReadList (Node List)
{
   String FileName;
   
   System.out.println("\n================  Testing ReadList ==================\n");
   
   System.out.print("Please enter the file to read from? ");
   FileName = Input.nextLine();
   
   if (!ReadList(List, FileName))
   {
      System.err.println("\nError in opening the file " + FileName);
      System.err.print("Press ENTER KEY");
      Input.nextLine();
      return;
   }
   
   System.out.println("\nThe list created from the file -- " + FileName + " --\n");
   ShowList(List);
}

/* **********************    TestSortList  *********************************
   Facilitates the testing of SortList, a function that is supposed to
   return a list that has been sorted, from lowest character value to
   highest.
---------------------------------------------------------------------------*/
public static void TestSortList (Node List)
{
   System.out.println("\n================   Calling SortList  =================\n");
   
   SortList(List);
   
   System.out.println("\n\nList = ");
   
   ShowList(List);
}

/* ***************************   BuildList    *****************************
  DESCRIPTION   Builds a singly linked list with a dummy head node. The
                characters in the list are in the same order in which the
                user enters them, i.e. new characters are added to the tail
                end of the list.  If there was a list to begin with, this routine
                will disregard it, so is lost.

                Input terminates when the enter key is pressed.
  PARAMETERS
     IN : L     A reference to a singly linked list with a dummy head node.
                It is imperative that List be initialized before calling
                this routine.
-----------------------------------------------------------------------------*/
public static void BuildList (Node L)
{
   String s = Input.nextLine();
   char[] ch = s.toCharArray();
   for (int i=0; i<ch.length; i++)
   {
      Node n = new Node();
      n.Ch = ch[i];
      L.Link = n;
      L = n;
   }
}

/* ***************************   AddNode  *********************************
  DESCRIPTION  Adds a node containing NewChar to the end of List.

  PARAMETERS
    IN : NewChar The character to be added to the end of the list.

    IN : L    A reference to a singly linked list with a dummy head node.
              The value of List (address of dummy head node) is not
              changed by this routine.
----------------------------------------------------------------------------*/
public static void AddNode (char NewChar, Node L)
{
   Node t = new Node();
   t.Ch = NewChar;
   do
   {
      L = L.Link;
   } while (L.Link != null);
   L.Link = t;
}

/* ****************************   DeleteNode   ****************************
  DESCRIPTION  Deletes the first node of List that contains the char
               CharToDelete. The storage occupied by the deleted
               node is returned to the heap.

  PARAMETERS
    IN : CharToDelete  The character to be deleted.

    IN : L    A reference to a singly linked list with a dummy head node.
              The value of List is not changed by this routine but the
              linked list itself is changed.

    returns, CharFound Set to true if the CharToDelete is found and deleted and
             false otherwise.
--------------------------------------------------------------------------------*/
public static boolean DeleteNode (char CharToDelete, Node L)
{
   if (L.Link == null)
      return false;

   boolean charFound = false;
   Node previousNode = L;
   L = L.Link; // Skips head node

   while (!charFound)
   {
      if (CharToDelete == L.Ch)
      {
         charFound = true;
         previousNode.Link = L.Link;
      }
      // Reaches the end of the linked list, was unable to find char
      else if (L.Link == null)
         break;
      else
      {
         previousNode = L;
         L = L.Link;
      }
   }
   return charFound;
}

/* ****************************   ZapList  ********************************
  DESCRIPTION  Frees all the storage space currently occupied by the
               linked list pointed to by List. Does NOT delete the delete
               the dummy head node.

  PARAMETER
      OUT: L   A reference to a singly linked list with a dummy head node.
               After this call, List will contain only the dummy head node.
----------------------------------------------------------------------------*/
public static void ZapList (Node L)
{ L.Link = null; }

/* ************************   ShowList  ***********************************
  DESCRIPTION  Displays the character field of all of the nodes in L, a
               singly linked list with a dummy head node. The list is
               enclosed in quotes.  The dummy head node is not displayed.
  PARAMETER
     IN : L   A reference to a singly linked list with a dummy head node.

  NOTE         To facilitate debugging this routine displays "NULL"
               if called with L == null or L.Link == null
-----------------------------------------------------------------------------*/
public static void ShowList (Node L)
{
   if ( L == null || L.Link == null)
   {
      System.out.println(" NULL LIST\n");
      return;
   }
   System.out.print("\"");           // Display quote for ease of testing.
   
   while ( L.Link != null )
   {
      L = L.Link;
      System.out.print(L.Ch);      
   }
   System.out.println("\"\n");       // Display ending quote
}

/* ***************************   ReadList    *****************************
DESCRIPTION   Builds a singly linked list with a dummy head node. The
              characters in the list are read in from an external file
              in the same order in which they are found in file.

              Input to list terminates when the End of File is encountered             
PARAMETERS
  IN : L     A reference to a singly linked list with a dummy head node.
             It is imperative that List be initialized before calling
             this routine.
 IN : FileName  A string that has the name of the file to open, if error 
                in opening then return a false.
RETURNS:     true if file opened successfully, else false

NOTE        Before building the new list, ZapList is called.            
----------------------------------------------------------------------------*/
public static boolean ReadList (Node L, String FileName)
{
   int Ch;
   BufferedReader FileIn;
   Node LastNode = L;                  //Will point to last node of list

   try
   {
      FileIn = new BufferedReader(new FileReader(FileName));
   }
   catch (FileNotFoundException e)
   {
      return false;
   }

   while (LastNode.Link != null)
      LastNode = LastNode.Link;

   try
   {
      Ch = FileIn.read();
      if (Ch == -1)
         return true;
      do
      {
         Node n = new Node();
         n.Ch = (char) Ch;
         LastNode.Link = n;
         LastNode = n;

         Ch = FileIn.read();
      } while (Ch != -1);

      FileIn.close();
   }
   catch (IOException e)
   {
      return false;
   }
   return true;
}

/* *************************  SortList ************************************
Description  Arranges the singly linked list pointed to by L in
             natural order.  It is assumed that the list has a dummy head node.

             The algorithm used is a linked variation of the selection
             sort and works like this:

               Start with EndSorted aimed at first node of list
               repeat
                    Find smallest char between EndSorted and end of list
                    Swap smallest element with char in EndSorted
                    Change EndSorted to next node
               until we get to end of list

             None of the references in linked list are changed

Parameters
  IN :  L    A reference to a singly linked list with a dummy head node
---------------------------------------------------------------------------*/
public static void SortList(Node L)
{
   Node Smallest;        //points to smallest char
   Node Current;       //used to search each node in list
   Node EndSorted = L.Link;        //points to list to sort

   while (EndSorted != null)
   {
      Smallest = EndSorted;
      Current = EndSorted;

      while (Current != null)
      {
         if (Current.Ch < Smallest.Ch)
            Smallest = Current;
         
         Current = Current.Link;
      }

      char tempCh = EndSorted.Ch;
      EndSorted.Ch = Smallest.Ch;
      Smallest.Ch = tempCh;

      EndSorted = EndSorted.Link;
   }
}
}


/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 14-Module 14\Homework 9\src>java a9shell.java
This program allows you to test the routines needed 
for homework 8.
----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => r
----------------------------------------------------

================  Testing ReadList ==================

Please enter the file to read from? words.1

The list created from the file -- words.1 --

"This is fun!"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => s
----------------------------------------------------

================   Calling SortList  =================



List =
"  !Tfhiinssu"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => r
----------------------------------------------------

================  Testing ReadList ==================

Please enter the file to read from? words.2

The list created from the file -- words.2 --

"My number is as fol-
lows, (219) 237 - 4496.
Two large numbers are
123456789123456
10000000000000005"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => s
----------------------------------------------------

================   Calling SortList  =================



List =
"



           (),--.000000000000000111122223334444555666778999MTaaabbeeeefgilllmmnnooorrrrssssuuwwy"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => r
----------------------------------------------------

================  Testing ReadList ==================

Please enter the file to read from? words.3

The list created from the file -- words.3 --

 NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => s
----------------------------------------------------

================   Calling SortList  =================



List =
 NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => r
----------------------------------------------------

================  Testing ReadList ==================

Please enter the file to read from? words.4

The list created from the file -- words.4 --

"Did you ever wonder what happened to Dick, Jane, and Spot?  As you
might expect, they lived happily ever after.  Jane is a top account
executive for a large brokerage firm.  Dick is a senior engineer for
a major computer vendor.  Spot passed away a few years ago, but he
was happy to the end.  All of his success never altered Spot's
pleasant personality."

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => s
----------------------------------------------------

================   Calling SortList  =================



List =
"




                                                               ',,,,.....?AADDDJJSSSaaaaaaaaaaaaaaaaaaaaaaaaaaabbcccccccccdddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeffffffggggghhhhhhhhhiiiiiiiiiiiiiijkkkllllllllmmmmnnnnnnnnnnnnnnooooooooooooooooooooppppppppppppppprrrrrrrrrrrrrrrrrrrssssssssssssssstttttttttttttttttttuuuuuuuvvvvvvwwwwwxxyyyyyyyy"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => r
----------------------------------------------------

================  Testing ReadList ==================

Please enter the file to read from? words.5

The list created from the file -- words.5 --

"hy-
pen
!!wishy-washy!!"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => s
----------------------------------------------------

================   Calling SortList  =================



List =
"

!!!!--aehhhinpsswwyyy"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  R(eadList  S(ortList  Z(apList  Q(uit  => q
----------------------------------------------------
*/