/*   randdemo.java

Program goes over two ways to produce random numbers in java. One is by 
importing the class Random and the other is by using the random() method 
found in the Math class. 

When program runs, RandInt3, will always produce the same number because this 
one started with same Seed number.
---------------------------------------------------------------------*/
import java.util.Random;

public class randdemo 
{
    public static void main(String[] args) 
    {
        int RandInt1, RandInt2, RandInt3, RandInt4,Seed = 25;
        float RandFloat;
        
        Random Rand1 = new Random();     // create instance of Random class   
        Random Rand2 = new Random(Seed); // Starts random number formula
                                             // with different number or Seed
        
        RandInt1 = Rand1.nextInt();          // Generate random integer + or -
        RandInt2 = Rand1.nextInt(100);     // Generate int in range 0 to 99   
        RandInt3 = Rand2.nextInt(10) + 1;  // Generate number 1 to 10 
      
        System.out.println("Random integer:          " + RandInt1);
        System.out.println("Random integer 0 to 99:  " + RandInt2); 
        System.out.println("\nRandom integer created with starting Seed");
        System.out.println("Random integer 1 to 10:  " + RandInt3);  
        
        RandFloat = Rand1.nextFloat();      // Generate Random float 0.0 to 1
    
        System.out.println("\nRandom float 0.0 to 1:   " + RandFloat);   
        
        // Generating random double using the Math class
        System.out.println("\nRandom double with Math class: " + Math.random());
        
        // Generates randome integer between 1 and 6 usig Math class
        RandInt4 = (int)(Math.random()*6 + 1);
        System.out.println("Random integer between 1 and 6: " + RandInt4);
    }
}
/* *********************** Program Output ****************************
Random integer:          -1255317651
Random integer 0 to 99:  6

Random integer created with starting Seed
Random integer 1 to 10:  2

Random float 0.0 to 1:   0.6212272

Random double with Math class: 0.0766579800821926
Random integer between 1 and 6: 5                         */