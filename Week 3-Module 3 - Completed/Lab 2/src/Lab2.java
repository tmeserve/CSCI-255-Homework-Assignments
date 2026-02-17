/*
Name: Tyler Meserve
Date: 1/28/2026
Homework #: Lab 2
Class: 255 Online
*/


public class Lab2
{
    // private static final int [] Numer = {4,8,16,32,64,128};
    // private static final int [] Denom = {2,2,4,4,64,8};
    private static final int [] Numer = {4,8,16,32,64,128,256, 512};
    private static final int [] Denom = {2,0,4,4,0,8};

    public static void main(String[] args)
    {
        for (int i = 0; i < Numer.length; i++)
        {
            try
            {
                System.out.println(Numer[i] + " / " + Denom[i] + " = " + Numer[i]/Denom[i]);

            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("No matching element found, out of bounds");
            }
            catch (ArithmeticException e)
            {
                //
                System.out.println("Can't divide by zero");
            }
        }
    }
}

/*
Normal Run

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 3-Module 3\Lab 2\src>java Lab2      
4 / 2 = 2
8 / 2 = 4
16 / 4 = 4
32 / 4 = 8
64 / 64 = 1
128 / 8 = 16

Run With Exceptions

E:\School\IUO\Sophomore Spring 2026\CSCI-C255\Homework\Week 3-Module 3\Lab 2\src>java Lab2      
4 / 2 = 2
Can't divide by zero
16 / 4 = 4
32 / 4 = 8
Can't divide by zero
128 / 8 = 16
No matching element found, out of bounds
No matching element found, out of bounds
*/