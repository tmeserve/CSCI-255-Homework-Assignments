/* *************************************************************************
   AUTHOR  : Tyler Meserve and  Matt Holloway  (with John Russo )
                                     
   SOURCE  : Homework8.java

   DATE    : 4/17/2026

   COMPILER: NetBeans

   ACTION  : The program tests routines designed to perform various
             operations on singly linked lists. The lists have a
             dummy head node that holds the "Happy Face" character. 
             The tail of the lists points to NULL.
------------------------------------------------------------------------------*/ 
import java.util.Scanner;

class Node
{
  final int DUMMY_VALUE = 1;  //  Value () stored in dummy head node.
  char  Ch;                   //  Holds the char data.
  Node Link;                  //  Points to another object of type Node.
}

public class A8shell
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
                       "Z(apList", "Q(uit", "" };

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

/* ***************************   BuildList    *****************************
  DESCRIPTION   Builds a singly linked list with a dummy head noed. The
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
        OUT :  A reference to a singly linked list with a dummy head node.
               After this call, List will contain only the dummy head node.
-----------------------------------------------------------------------------*/
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
}

/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 13-Module 13\Homework 8\src>java A8shell.java
This program allows you to test the routines needed 
for homework 8.
----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => b
----------------------------------------------------

================  Testing BuildList  ================

Type the characters for the list -  when finished, press enter key

 ->

After BuildList, List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => d
----------------------------------------------------

===============  Testing DeleteNode  ================

Character to be deleted? a

'a' was not in the list,

List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => b
----------------------------------------------------

================  Testing BuildList  ================

Type the characters for the list -  when finished, press enter key

 -> a

After BuildList, List = "a"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => b
----------------------------------------------------

================  Testing BuildList  ================

Type the characters for the list -  when finished, press enter key

 -> aabb1122

After BuildList, List = "aabb1122"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => a
----------------------------------------------------

================  Testing AddNode  =================

Character to be added? d
 --  Adding 'd'

The new list: "aabb1122d"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => d
----------------------------------------------------

===============  Testing DeleteNode  ================

Character to be deleted? l

'l' was not in the list,

List = "aabb1122d"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => d
----------------------------------------------------

===============  Testing DeleteNode  ================

Character to be deleted? a

'a' has been deleted,

List = "abb1122d"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => d
----------------------------------------------------

===============  Testing DeleteNode  ================

Character to be deleted? 1

'1' has been deleted,

List = "abb122d"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => d
----------------------------------------------------

===============  Testing DeleteNode  ================

Character to be deleted? d

'd' has been deleted,

List = "abb122"

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => z
----------------------------------------------------

===============  Calling ZapList  ====================


List =  NULL LIST

----------------------------------------------------
B(uildList  A(ddNode  D(eleteNode  Z(apList  Q(uit  => q
----------------------------------------------------
*/