import java.util.Random;

/**
 * Write a description of class Point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point
{
    // instance variables - replace the example below with your own
    private int xCoordinate;
    private int yCoordinate;


    
    
    /**
     * Create a new point with random X and Y coordinates
     */
    public Point()
    {
        // initialise instance variables
        Random randomGenerator = new Random();
        xCoordinate = randomGenerator.nextInt(290);
        yCoordinate = randomGenerator.nextInt(290);
    }
    
    /**
     * Create a point with specified X and Y coordinates
     */
    public Point(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }
    
    public int getX() {
        return xCoordinate;
    }
    
    public int getY() {
        return yCoordinate;
    }

}
