

class Temperature
{
    String scale;
    float temp;

    public Temperature()
    {
        this.scale = "F";
        this.temp = 0;
    }

    public Temperature(String scale, float temp)
    {
        this.scale = scale;
        this.temp = temp;
    }

    public Temperature(Temperature t)
    {
        this.scale = t.scale;
        this.temp = t.temp;
    }

    public void showTemp()
    { System.out.print(this.temp + " degrees " + this.scale + "\n"); }

    public void setTemp(float temp)
    { this.temp = temp; }

    public void convert()
    {
        double convertedTemp;
        switch (this.scale)
        {
            case "C" -> {
                convertedTemp = (this.temp * (9/5)) + 32;
                System.out.print(convertedTemp + " degrees F\n");
            }
            case "F" -> {
                convertedTemp = (this.temp - 32) * (5/9);
                System.out.print(convertedTemp + " degrees C\n");
            }
            default -> System.out.println("Scale type not recognized, please use \"C\" or \"F\"");
        }
    }

    public void convert(String scale, float temp)
    {
        double convertedTemp;
        System.out.print(temp + " degrees " + scale + " converted to other Scale is ");
        switch (scale)
        {
            case "C" -> {
                convertedTemp = (temp * (9.0/5.0)) + 32;
                System.out.print(convertedTemp + " degrees F\n");
            }
            case "F" -> {
                convertedTemp = (temp - 32) * (5.0/9.0);
                System.out.print(convertedTemp + " degrees C\n");
            }
            default -> System.out.println("Scale type not recognized, please use \"C\" or \"F\"");
        }
    }
}