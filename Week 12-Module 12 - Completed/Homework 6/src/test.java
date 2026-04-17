import java.io.IOException;

class test
{
    public static void main(String[] args) throws IOException
    {
        // MyFloat x = new MyFloat();
        MyFloat y = new MyFloat();

        // System.out.print("\nEnter a MyFloat ==> ");

        // x.Read();
        // if (x.Digits() == 0)
        // {
        //     System.out.println("Format error");
        // }

        y.Read();
        if (y.Digits() == 0)
        {
            System.out.println("Format error");
        }

        // System.out.println(x.isGreater(y));

        y.Write();
        // System.out.println(y.toString());
        // y.toMyFloat("0.123");
    }
}