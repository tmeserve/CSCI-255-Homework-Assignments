/*
Name: Tyler Meserve
Date: 1/28/2026
Homework #: 1
Source file: Homework1.java
Class: 255 Online
Action: Program will repeatedly ask for a sentence to censor. The word the program will censor is "hell."
External Assistance Provided By: ChatGPT for test data sets and JavaDoc comments for the function changeHellToHeck; action, params, precondition. 
*/

import java.util.Scanner;

public class Homework1
{
    // Declare class constants; these represent the ASCII values of characters.
    private static final int UPPERCASE_H = 72;
    private static final int LOWERCASE_H = 104;
    private static final int UPPERCASE_E = 69;
    private static final int LOWERCASE_E = 101;
    private static final int UPPERCASE_L = 76;
    private static final int LOWERCASE_L = 108;
    private static final int SPACE = 32;

    /*
    Action: Converts a string that contains the word "hell" into "heck".
    Params: hell a literal string containing exactly one word: "hell"
    Returns: the word "heck" after replacing "hell" in a case-insensitive manner, preserving the original letter casing
    Precondition: The variable "hell" is expected to be "hell"
    */
    public static String changeHellToHeck(String hell)
    {
        String censoredWord = "";
        for (int i = 0; i < hell.length(); i++)
        {
            char c = hell.charAt(i);

            // Converts the first 3 characters of the word "hell" to "hec"; casing dependent on user input. 
            if (i < 3)
            {
                switch ((int) c)
                {
                    case UPPERCASE_H -> censoredWord += "H";
                    case LOWERCASE_H -> censoredWord += "h";
                    case UPPERCASE_E -> censoredWord += "E";
                    case LOWERCASE_E -> censoredWord += "e";
                    case UPPERCASE_L -> censoredWord += "C";
                    case LOWERCASE_L -> censoredWord += "c";
                    default -> { }
                }
            }
            // When for loop is complete, changes last letter to an upper or lowercase "k" dependent on user input. 
            else if (i == 3 && (int) c == UPPERCASE_L)
                censoredWord = censoredWord.substring(0, 3) + "K";
            else if (i == 3 && (int) c == LOWERCASE_L)
                censoredWord = censoredWord.substring(0, 3) + "k";
        }
        return censoredWord;
    }
    
    /*
    Action: Takes an uncensored sentence and censors it. 
    Params: Sentence is the users uncensored sentence to censor. 
    Returns: A censored version of the users sentence; censors the word "hell" by changing it to "heck"
    Precondition: None; any sentence the user enters will succeed. 
    */
    public static String censorSentence(String sentence)
    {
        // Declare function variables
        String censoredSentence = "";
        String word = "";

        for (int i=0; i<sentence.length(); i++)
        {
            if ((int) sentence.charAt(i) == SPACE)
            {
                if (word.equalsIgnoreCase("hell"))
                    censoredSentence += changeHellToHeck(word) + sentence.charAt(i);
                else
                    censoredSentence += word + sentence.charAt(i);
                word = "";
            }
            // Accounts for punctuation marks, such as "." "!" and "," mid sentence. IE Hi. How're you today?
            else if (!Character.isLetter(sentence.charAt(i)))
            {
                // Splits the string up to exclude the punctuation mark and check if the word is equal to "hell"
                if (word.substring(0,word.length()).equalsIgnoreCase("hell"))
                    censoredSentence += changeHellToHeck(word) + sentence.charAt(i);
                else
                    censoredSentence += word + sentence.charAt(i);
                word = "";
            }
            else if (i+1 == sentence.length())
            {
                if (word.equalsIgnoreCase("hell"))
                    censoredSentence += changeHellToHeck(word) + sentence.charAt(i);
                else
                    censoredSentence += word + sentence.charAt(i);
            }
            else
                word += sentence.charAt(i);
        }
        return censoredSentence;
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        while (true)
        {
            System.out.println("Enter sentence to censor: ");
            String sentence = s.nextLine();
            if (sentence.isEmpty())
            {
                System.out.println("Goodbye...");
                break;
            }

            String censoredSentence = censorSentence(sentence);
            System.out.println("Original: \"" + sentence + "\"");
            System.out.println("Censored: \"" + censoredSentence + "\"");
            System.out.println("---------------------------------------------------------------------------------\n");
        }
    }
}

/* ************************  Program Output  *******************************

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 3-Module 3\Homework 1\src>java Homework1 < TestData.text
Enter sentence to censor: 
Original: "wHaT tHe hELl iS gOiNg oN hErE?!"
Censored: "wHaT tHe hECk iS gOiNg oN hErE?!"
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "iT?s hOt aS HeLl oUtSiDe tOdAy!!!"
Censored: "iT?s hOt aS HeCk oUtSiDe tOdAy!!!"
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "I wAiTeD iN lInE fOr HeLl aNd bAcK..."
Censored: "I wAiTeD iN lInE fOr HeCk aNd bAcK..."
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "hE wEnT tHrOuGh hElL jUsT tO fInIsH tHe pRoJeCt."
Censored: "hE wEnT tHrOuGh hEcK jUsT tO fInIsH tHe pRoJeCt."
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "tHiS pLaCe fEeLs LiKe hElL wHeN tHe A/C bReAkS!!!"
Censored: "tHiS pLaCe fEeLs LiKe hEcK wHeN tHe A/C bReAkS!!!"
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "wHy tHe hElL wOuLd aNyOnE dO tHaT?!"
Censored: "wHy tHe hEcK wOuLd aNyOnE dO tHaT?!"
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "tHe tRaFfIc wAs HeLl tHiS mOrNiNg..."
Censored: "tHe tRaFfIc wAs HeCk tHiS mOrNiNg..."
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "iT sMeLlEd LiKe hElL iN tHe kItChEn aFtEr tHaT!"
Censored: "iT sMeLlEd LiKe hEcK iN tHe kItChEn aFtEr tHaT!"
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "Hello, ar3 y0u aLsO hOt As hell t0d@y?"
Censored: "Hello, ar3 y0u aLsO hOt As heck t0d@y?"
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "Sea shells are on the shore for all to collect. Careful for the glowing red ones, legend has it, those will take you straight to hell."
Censored: "Sea shells are on the shore for all to collect. Careful for the glowing red ones, legend has it, those will take you straight to heck."
---------------------------------------------------------------------------------

Enter sentence to censor:
Original: "tHaT eXaM wAs HeLl, fOr eVeRyOnE iN tHe cLaSs."
Censored: "tHaT eXaM wAs HeCk, fOr eVeRyOnE iN tHe cLaSs."
---------------------------------------------------------------------------------

Enter sentence to censor:
Goodbye...

*/