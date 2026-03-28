import java.io.IOException;

class test
{
    public static void main(String[] args) throws IOException
    {
        MyFloat x = new MyFloat();

        System.out.print("\nEnter a MyFloat ==> ");

        x.Read();
        if (x.Digits() == 0)
        {
            System.out.println("Format error");
        }
        x.Write();
    }
}