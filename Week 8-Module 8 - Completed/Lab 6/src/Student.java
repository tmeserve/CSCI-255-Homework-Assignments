

class Student
{
    private String name;
    private int[] scores;

    public Student(String name, int[] scores)
    {
        this.name = name;
        this.scores = scores;
    }

    /*
    Action: Calculates the average of the scores
    Params: None
    Returns: Returns the average of the scores with object type float
    Precondition: Scores needs to be initialized, whether it's an empty array or an array that has values in it.
    */
    public float calculateAverage()
    {
        double total = 0;
        for (int score: this.scores)
            total += score;
        return (float) total/this.scores.length;
    }

    public String getName()
    { return this.name; }
}