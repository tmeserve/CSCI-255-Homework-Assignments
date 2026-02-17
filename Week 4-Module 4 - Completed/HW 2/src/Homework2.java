
/*
Name: 
Date: 2/8/2026
Homework #: 2
Source file: Homework2.java
Class: 255 Online
Action: Calculates the word length of each word and the average word length in a given input
External Assistance Provided By: 
*/
import java.io.IOException;

public class Homework2
{
    private static int wordCount = 0;
    private static int wordLength1Count = 0;
    private static int wordLength2Count = 0;
    private static int wordLength3Count = 0;
    private static int wordLength4Count = 0;
    private static int wordLength5Count = 0;
    private static int wordLength6Count = 0;
    private static int wordLength7Count = 0;
    private static int wordLength8Count = 0;
    private static int wordLength9Count = 0;
    private static int wordLength10Count = 0;
    private static int wordLength11Count = 0;
    private static int wordLength12Count = 0;
    private static int wordLength13Count = 0;
    private static int wordLength14Count = 0;
    private static int wordLength15Count = 0;

    /*
    Action: Calculates the words length
    Params: A string containing exactly 1 word
    Returns: void
    Precondition: String must be 1 word exactly, punctuation allowed but will not be counted unless a - or '
    */
    private static void calculateWordLength(String s)
    {
        int charCount = 0;
        if (s.matches("\\b\\w*[-']\\w*\\b"))
            charCount = s.length();
        else
        {
            for (int i=0; i<s.length();i++)
            {
                char c = s.charAt(i);
                if (Character.isLetterOrDigit(c))
                    charCount+=1;
                else if (c == '-' || c == '\'')
                    charCount+=1;
            }
        }

        switch (charCount)
        {
            case 0 -> {}
            case 1 -> wordLength1Count+=1;
            case 2 -> wordLength2Count+=1;
            case 3 -> wordLength3Count+=1;
            case 4 -> wordLength4Count+=1;
            case 5 -> wordLength5Count+=1;
            case 6 -> wordLength6Count+=1;
            case 7 -> wordLength7Count+=1;
            case 8 -> wordLength8Count+=1;
            case 9 -> wordLength9Count+=1;
            case 10 -> wordLength10Count+=1;
            case 11 -> wordLength11Count+=1;
            case 12 -> wordLength12Count+=1;
            case 13 -> wordLength13Count+=1;
            case 14 -> wordLength14Count+=1;
            default -> wordLength15Count+=1;
        }
    }

    /*
    Action: Calculates the average word length of sentence(s)
    Params: None
    Returns: Void
    Precondition: None
    */
    private static double calculateAverageWordLength()
    {
        double totalWordLength = wordLength1Count + (wordLength2Count*2) + (wordLength3Count*3) 
            + (wordLength4Count*4) + (wordLength5Count*5) + (wordLength6Count*6) + (wordLength7Count*7)
            + (wordLength8Count*8) + (wordLength9Count*9) + (wordLength10Count*10) + (wordLength11Count*11)
            + (wordLength12Count*12) + (wordLength13Count*13) + (wordLength14Count*14) + (wordLength15Count*15);
        return totalWordLength/wordCount;
    }

    /*
    Action: Determines if a character is a punctuation mark. 
    Params: an integer that represents a character within the sentence/word provided.
    Returns: true or false based upon whether or not a character is considered a punctuation character.
    Precondition: None
    */
    private static boolean isPunct(int ch)
    {
        if (ch == '!' || ch == '\"' || ch == '#' || ch == '$' || ch == '%'
            || ch == '&' || ch == '(' || ch == ')' || ch == '*'
            || ch == '+' || ch == ',' || ch == '.' || ch == '/'
            || ch == ':' || ch == ';' || ch == '<' || ch == '=' || ch == '>'
            || ch == '?' || ch == '@' || ch == '[' || ch == '\\' || ch == ']'
            || ch == '^' || ch == '`' || ch == '{' || ch == '|' || ch == '}' || ch == 32)
            return true;    
        else
            return false;
    }   

    public static void main(String[] args) throws IOException
    {
        int ch;
        String word = "";

        System.out.println("Please input the sentence(s) you'd like the word count to.");
        
        ch = System.in.read();
        while (ch != -1)
        {
            if (isPunct(ch))
            {
                if (!word.isEmpty())
                {
                    calculateWordLength(word);
                    wordCount++;
                }
                word = "";
            }
            else
            {
                if (!isPunct(ch))
                    word += String.valueOf((char) ch);
            }
            
            ch = System.in.read();
        }

        // At end of input, calculates the final word in the buffer if it exists
        if (!word.isEmpty())
        {
            wordCount++;
            calculateWordLength(word);
        }
        
        System.out.println("Word Length                 Frequency");
        System.out.println("-----------                 ---------");
        System.out.println("       1                       " + wordLength1Count);
        System.out.println("       2                       " + wordLength2Count);
        System.out.println("       3                       " + wordLength3Count);
        System.out.println("       4                       " + wordLength4Count);
        System.out.println("       5                       " + wordLength5Count);
        System.out.println("       6                       " + wordLength6Count);
        System.out.println("       7                       " + wordLength7Count);
        System.out.println("       8                       " + wordLength8Count);
        System.out.println("       9                       " + wordLength9Count);
        System.out.println("       10                      " + wordLength10Count);
        System.out.println("       11                      " + wordLength11Count);
        System.out.println("       12                      " + wordLength12Count);
        System.out.println("       13                      " + wordLength13Count);
        System.out.println("       14                      " + wordLength14Count);
        System.out.println("       15                      " + wordLength15Count);
        System.out.println("Average Word Length: " + calculateAverageWordLength());
    }
}

/* ************************  Word Files  *******************************
mywords.1
Her code ran per-
fectly after 3 tries

mywords.2
Have you noticed how programs behave differently
when input comes from files instead of users?
Some answers are obvious, others less so.
Careful testing reveals hidden assumptions.

mywords.3
Wow!
Debugging is oddly satisfying.
Errors, warnings, and logs!!!

mywords.4
re-
write
@@data-driven@@
pre-process the input-stream correctly.
end-of-file matters.
*/

/* ************************  Program Output  *******************************
E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < words.1  
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       0
       2                       1
       3                       1
       4                       1
       5                       0
       6                       0
       7                       0
       8                       0
       9                       0
       10                      0
       11                      0
       12                      0
       13                      0
       14                      0
       15                      0
Average Word Length: 3.0

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < words.2
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       1
       2                       3
       3                       3
       4                       1
       5                       1
       6                       1
       7                       1
       8                       1
       9                       0
       10                      0
       11                      0
       12                      0
       13                      0
       14                      0
       15                      1
Average Word Length: 4.6923076923076925

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < words.3
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       0
       2                       0
       3                       0
       4                       0
       5                       0
       6                       0
       7                       0
       8                       0
       9                       0
       10                      0
       11                      0
       12                      0
       13                      0
       14                      0
       15                      0
Average Word Length: NaN

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < words.4
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       4
       2                       6
       3                       12
       4                       13
       5                       8
       6                       5
       7                       3
       8                       4
       9                       1
       10                      0
       11                      1
       12                      0
       13                      0
       14                      1
       15                      1
Average Word Length: 4.677966101694915

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < words.5
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       0
       2                       0
       3                       0
       4                       0
       5                       0
       6                       1
       7                       0
       8                       0
       9                       0
       10                      0
       11                      1
       12                      0
       13                      0
       14                      0
       15                      0
Average Word Length: 8.5

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < mywords.1
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       1
       2                       0
       3                       2
       4                       1
       5                       2
       6                       0
       7                       0
       8                       0
       9                       0
       10                      1
       11                      0
       12                      0
       13                      0
       14                      0
       15                      0
Average Word Length: 4.428571428571429

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < mywords.2
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       0
       2                       2
       3                       3
       4                       4
       5                       4
       6                       3
       7                       7
       8                       1
       9                       0
       10                      0
       11                      1
       12                      0
       13                      0
       14                      0
       15                      1
Average Word Length: 5.769230769230769

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < mywords.3
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       0
       2                       1
       3                       2
       4                       1
       5                       1
       6                       1
       7                       0
       8                       1
       9                       1
       10                      1
       11                      0
       12                      0
       13                      0
       14                      0
       15                      0
Average Word Length: 5.555555555555555

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 4-Module 4\HW 2\src>java Homework2 < mywords.4
Please input the sentence(s) you'd like the word count to.
Word Length                 Frequency
-----------                 ---------
       1                       0
       2                       0
       3                       1
       4                       0
       5                       0
       6                       0
       7                       1
       8                       1
       9                       1
       10                      0
       11                      3
       12                      1
       13                      0
       14                      0
       15                      0
Average Word Length: 9.0
*/