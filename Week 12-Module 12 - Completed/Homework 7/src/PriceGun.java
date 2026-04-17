/*
Name: Tyler Meserve
Date: 4/11/2026
Homework #: Homework 7
Source file: PriceGun.java
Class: 255 Online
Action: This program creates a PriceGun class that scans item prices, tracks the total spent and last item entered, manages a budget limit, and provides functions to update, compare, display, and combine shopping data.
External Assistance Provided By: 
*/

import java.util.Scanner;

class PriceGun
{
    private double MaxPrice;
    private double CurrPrice;
    private double LastPrice;

    public PriceGun()
    { this.ClearPrice(); }

    public PriceGun(double MaxPrice, double CurrPrice, double LastPrice)
    {
        this.MaxPrice = MaxPrice;
        this.CurrPrice = CurrPrice;
        this.LastPrice = LastPrice;
    }

    public PriceGun(PriceGun p)
    {
        this.MaxPrice = p.MaxPrice;
        this.CurrPrice = p.CurrPrice;
        this.LastPrice = p.LastPrice;
    }

    public final void ClearPrice()
    {
        this.MaxPrice = 250;
        this.CurrPrice = 0;
        this.LastPrice = 0;
    }

    public void SetMaxPrice(double MaxPrice)
    { this.MaxPrice = MaxPrice; }

    public void DeleteItem()
    { this.CurrPrice = this.CurrPrice - this.LastPrice; }

    public double AmountLeft()
    { return Math.round((this.MaxPrice - this.CurrPrice) * 100.0) / 100.0; }

    public double CurrentPrice()
    { return Math.round(this.CurrPrice * 100.0) / 100.0; }

    public double Budget()
    { return this.MaxPrice; }

    /*
    Action: Prompts the user to enter a valid price and adds it to the current total while updating the last scanned price.
    Params: none
    Returns: void
    Precondition: User must input a positive numeric value for the price.
    */
    public void Scan()
    {
        Scanner s = new Scanner(System.in);
        double number = -1;
        // boolean priceEntered = false;

        while (!(number > 0))
        {
            System.out.println("Enter Price -> ");
            try
            {
                number = s.nextDouble();
                if (number < 0)
                    System.out.println("Please input a number greater than 0. ");
            }
            catch (Exception e)
            {
                System.out.println("Please enter a number.");
                s.nextLine();
            }

            // if (number > 0)
            //     priceEntered = true;
        }

        this.CurrPrice += number;
        this.LastPrice = number;
    }

    public boolean UnderBudget()
    { return this.CurrPrice <= this.MaxPrice; }

    public void DisplayAll()
    {
        System.out.println("Current Price = $" + this.CurrentPrice());
        System.out.println("Budget        = $" + this.Budget());
        System.out.println("Amount left   = $" + this.AmountLeft());
    }

    public boolean isGreater(PriceGun p)
    { return this.CurrPrice > p.CurrPrice; }

    public PriceGun Merge(PriceGun p)
    { return new PriceGun(this.MaxPrice + p.MaxPrice, this.CurrPrice + p.CurrPrice, 0); }
}


/* ************************  Program Output  *******************************
Run 1:

Let's go shopping!
Jim will go first, with a budget of $250.0
Jim is a party animal so he is buying supplies for a
party this coming weekend, and cannot go over budget.
2

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
2

How much do you want to ask for, be reasonable? 
275
OK, budget increased to $275.0

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
1

Start shopping!!. Enter Price -> 
122.35
Item added to list
Current Price is $122.35
Continue shopping, Y or N 
y
Enter Price -> 
53.65
Item added to list
Current Price is $176.0
Continue shopping, Y or N 
y
Enter Price -> 
12.643
Item added to list
Current Price is $188.64
Continue shopping, Y or N 
y
Enter Price -> 
64.7834
Item added to list
Current Price is $253.43
Continue shopping, Y or N 
n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
q

Good Bye Jim

This is what you have Jim.
Current Price = $253.43
Budget        = $275.0
Amount left   = $21.57
2

Now it is little Sue's turn to shop!
She comes from a wealthy family and has a higher 
budget of $500.0, and she loves to 
buy clothes, but still has to stay within budget.

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
1

Start shopping!!. Enter Price -> 
124.64
Item added to list
Current Price is $124.64
Continue shopping, Y or N 
y
Enter Price -> 
423.7646
Item added to list
Current Price is $548.4
Continue shopping, Y or N 
n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
3
2

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
1

Start shopping!!. Enter Price -> 
124.24564
Item added to list
Current Price is $124.25
Continue shopping, Y or N 
y
Enter Price -> 
245.23
Item added to list
Current Price is $369.48
Continue shopping, Y or N 
n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
q

Good Bye Sue

This is what you have Sue
Current Price = $369.48
Budget        = $250.0
Amount left   = $-119.48

Let's see who spent the most!
Sue spent more by $116.05000000000001
2

Jim and Sue are getting married!! About time!
They will now be able to merge their price guns.

This is what they now have together
Current Price = $622.9
Budget        = $525.0
Amount left   = $-97.9


Run 2:

Let's go shopping!
Jim will go first, with a budget of $250.0
Jim is a party animal so he is buying supplies for a
party this coming weekend, and cannot go over budget.
2

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
1

Start shopping!!. Enter Price -> 
234.35
Item added to list
Current Price is $234.35
Continue shopping, Y or N 
n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
q

Good Bye Jim

This is what you have Jim.
Current Price = $234.35
Budget        = $250.0
Amount left   = $15.65
2

Now it is little Sue's turn to shop!
She comes from a wealthy family and has a higher 
budget of $500.0, and she loves to 
buy clothes, but still has to stay within budget.

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
1

Start shopping!!. Enter Price -> 
456.35
Item added to list
Current Price is $456.35
Continue shopping, Y or N 
n

What do you want to do?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   1)  Go Shopping
   2)  Ask Mom and Dad for more money
   3)  Empty cart and restart
   Q)  Quit Shopping

Choice? --> 
q

Good Bye Sue

This is what you have Sue
Current Price = $456.35
Budget        = $500.0
Amount left   = $43.65

Let's see who spent the most!
Sue spent more by $222.00000000000003
2

Jim and Sue are getting married!! About time!
They will now be able to merge their price guns.

This is what they now have together
Current Price = $690.7
Budget        = $750.0
Amount left   = $59.3
*/