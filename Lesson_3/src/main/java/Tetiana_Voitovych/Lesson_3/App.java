package Tetiana_Voitovych.Lesson_3;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	Top10 t = new Top10("ratings.csv", "movies.csv");
        System.out.println( "Hello World!" );
    }
}
