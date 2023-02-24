

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BrickWallTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BrickWallTest
{
    /**
     * Default constructor for test class BrickWallTest
     */
    public BrickWallTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    
    /**
     * A test to check when the wall gets created that it is the right size and that only acceptable values are used for the height and width.
     *
     * 
     */
    @Test
    public void checkWallCreation(){
        //check that it's the right size
        //positive boundary width 1 and 24, height 1 and 31
        BrickWall wall = new BrickWall(1, 1);
        assertEquals(1, wall.getDimension("width"));
        assertEquals(1, wall.getDimension("height"));
        
        BrickWall wall1 = new BrickWall(24, 1);
        assertEquals(24, wall1.getDimension("width"));
        assertEquals(1, wall1.getDimension("height"));
        
        BrickWall wall2 = new BrickWall(1, 31);
        assertEquals(1, wall2.getDimension("width"));
        assertEquals(31, wall2.getDimension("height"));
        
        //check that unacceptable values get ignored and defualt values applied instead
        //negative boundary width 0 and 25, height 0 and 32
        BrickWall wall0 = new BrickWall(0, 0);
        assertEquals(24, wall0.getDimension("width"));
        assertEquals(31, wall0.getDimension("height"));
        
        BrickWall wall3 = new BrickWall(25, 1);
        assertEquals(24, wall3.getDimension("width"));
        assertEquals(31, wall3.getDimension("height"));
        
        BrickWall wall4 = new BrickWall(1, 32);
        assertEquals(24, wall4.getDimension("width"));
        assertEquals(31, wall4.getDimension("height"));                
    }
    
    /**
     * A test to check that the brick gets positioned correctly
     *
     * 
     */
    @Test
    public void checkDrawBrick(){
        //test to check its position is at the user stated position
        //create a wall
        BrickWall wall = new BrickWall(1, 1);
        //clear canvas
        wall.eraseWall();
        //create a brick
        wall.drawBrick(200,200);
        //if function returns null, it means that there wasnt a match and thus nothing was found at the specified coordinates.
        assertNotNull(wall.findBrickAt(200,200));
        //clear canvas
        wall.eraseWall();
    }
    
    /**
     * A test to check the row length and positioning is correct
     *
     * 
     */
    @Test
    public void checkDrawRow(){
        //test to check row length and position is correct.
        //create a wall
        BrickWall wall = new BrickWall(1, 1);
        //clear canvas
        wall.eraseWall();
        //create a row
        wall.drawRow(10, 10, 10);
        //check it's position is at the user stated position by checking the position of the first and last brick
        assertNotNull(wall.findBrickAt(10,10));
        assertNotNull(wall.findBrickAt(10+52*9,10)); 
        //clear canvas
        wall.eraseWall();
    }
    
    /**
     * A test to check that the height and position of draw many rows method.
     *
     * 
     */
    @Test
    public void checkDrawManyRows(){
        //test to check height and position is correct since we know from above that row length is correct.
        //create a wall
        BrickWall wall = new BrickWall(1, 1);
        //clear canvas
        wall.eraseWall();
        //create a wall
        wall.drawManyRows(10, 10, 10, 10);
        //check the position of the first brick (bottom left) and top right brick (the last)
        assertNotNull(wall.findBrickAt(10,10));
        assertNotNull(wall.findBrickAt(10+52*9,10+(-15*9)));
        //clear canvas
        wall.eraseWall();
    }
    
    /**
     *  A test to chekc that the mulitcolor method works as intended and that each subsequent brick is a different color
     *
     * 
     */
    @Test
    public void checkMulticolor(){
        //a test to check that multicolor works as intended and the color of each subsequent brick gets changed.
        //create a wall
        BrickWall wall = new BrickWall(1, 1);
        //clear canvas
        wall.eraseWall();
        //turn on multicolor
        wall.multicolorOn();
        //create a brick
        wall.drawBrick(10, 10);
        assertEquals(1, wall.getCurrentColor());
        //check currentColor gets incremented with every brick
        wall.drawBrick(10+52, 10);
        assertEquals(2, wall.getCurrentColor());
        wall.drawBrick(10+52*2, 10);
        assertEquals(3, wall.getCurrentColor());  
        wall.drawBrick(10+52*3, 10);
        assertEquals(4, wall.getCurrentColor()); 
        wall.drawBrick(10+52*4, 10);
        assertEquals(5, wall.getCurrentColor()); 
        wall.drawBrick(10+52*5, 10);
        //check it goes back to red after reaching the last color in the string. Where red is index 0
        assertEquals(0, wall.getCurrentColor()); 
        wall.drawBrick(10+52*6, 10);
        //check that currentColor continues to get incremented with every brick after being set back to 0
        assertEquals(1, wall.getCurrentColor()); 
        wall.drawBrick(10+52*7, 10);
        assertEquals(2, wall.getCurrentColor());
        //clear canvas
        wall.eraseWall();
    }
    
    /**
     * A test to check the decrease method and ensure that the row length is 1 less than the previous row after the first row. 
     *
     * 
     */
    @Test
    public void checkDecrease(){
        //a test to check each row is 1 less than the previous after the first row.
        //create a wall
        BrickWall wall = new BrickWall(1, 1);
        //clear canvas
        wall.eraseWall();
        //turn on decrease
        wall.decreaseOn();
        //create a wall
        wall.drawManyRows(0, 500, 10, 5);
        // check that the length of each row after the first is decreased by the correct amount
        assertNotNull(wall.findBrickAt(52 * 9,500));
        //should be no bricks at the following locations. return null if no brick found.
        assertNull(wall.findBrickAt(52 * 9,500-15));
        assertNull(wall.findBrickAt(52 * 8,500-15*2));
        assertNull(wall.findBrickAt(52 * 7,500-15*3));
        assertNull(wall.findBrickAt(52 * 6,500-15*4));
        assertNull(wall.findBrickAt(52 * 5,500-15*5));
        //there should be bricks at the following locations. returns an object if found, returns null if no object found.
        assertNotNull(wall.findBrickAt(52 * 8,500-15));
        assertNotNull(wall.findBrickAt(52 * 7,500-15*2));
        assertNotNull(wall.findBrickAt(52 * 6,500-15*3));
        assertNotNull(wall.findBrickAt(52 * 5,500-15*4));
        //clear canvas
        wall.eraseWall();
    }
    
    /**
     * A test to check the symmetry method, ensuring that it does not work without the decrease method being enabled. Also ensure that the method works as intended.
     *
     * 
     */
    @Test
    public void checkSymmetry(){
        //check it does not work with decrease not on.
        //create a wall
        BrickWall wall = new BrickWall(1, 1);
        //erase the canvas
        wall.eraseWall();
        //turn on symmetry
        wall.symmetricalOn();
        //symmetry should be false as it should not work without decrease on first
        assertFalse(wall.isSymmetryOn());
        //turn on decrease
        wall.decreaseOn();
        //turn on symmetry
        wall.symmetricalOn();
        //check that symmetry is on
        assertTrue(wall.isSymmetryOn());
        //draw a wall
        wall.drawManyRows(0,500,5,5);
        // check that the bricks get positioned corectly.
        //first row should be positioned normally - check first and last brick
        assertNotNull(wall.findBrickAt(0,500));
        assertNotNull(wall.findBrickAt(52 * 4,500));
        //second row should have no bricks directly above the first row start and finish
        assertNull(wall.findBrickAt(0,500-15));
        assertNull(wall.findBrickAt(52 * 4,500-15));
        //fifth row should have no bricks in the following locations
        assertNull(wall.findBrickAt(0,500-60));
        assertNull(wall.findBrickAt(52,500-60));
        assertNull(wall.findBrickAt(52*3,500-60));
        assertNull(wall.findBrickAt(52*4,500-60));
        //clear canvas
        wall.eraseWall();
    }
    
    /**
     * 
     *
     * 
     */
    @Test
    public void checkFindFirstBrickByColor(){
        //create a wall
        BrickWall wall = new BrickWall(1, 1);
        //erase the canvas
        wall.eraseWall();
         //turn on multicolor
        wall.multicolorOn();
        //create a brick
        wall.drawBrick(10, 10);
        assertEquals(1, wall.getCurrentColor());
        wall.drawBrick(10+52, 10);
        assertEquals(2, wall.getCurrentColor());
        wall.drawBrick(10+52*2, 10);
        assertEquals(3, wall.getCurrentColor());
        //check that it returns a brick
        //if the program finds a brick with the color blue, it will return a brick, if it does not find a brick with that color it will return null
        assertNotNull(wall.findFirstBrickByColor("blue"));
        //a black brick has not been made yet and should return null
        assertNull(wall.findFirstBrickByColor("black"));
        
        //check that it returns null when multicolor is off and you choose a color other than red.
        //erase the canvas
        wall.eraseWall();
        //turn off multicolor so that the only color can be red (color index 0)
        wall.multicolorOff();
        //draw 10 bricks
        wall.drawRow(0,0,10);
        //check that returns null when it searches for any color other than red
        assertNull(wall.findFirstBrickByColor("blue"));
        assertNull(wall.findFirstBrickByColor("yellow"));
        //check that it returns null if the color isn't recognised.
        assertNull(wall.findFirstBrickByColor("bottle green"));
    }
    
    @Test
    public void checkFindBricksByColor(){
        //check that it returns a list of bricks
        //check that it returns null when multicolor is off and you choose a color other than red.
        //check that it returns null if the color isn't recognised.
    }
    
    @Test
    public void checkFindBricksAt(){
        
    }
    
    
    /**
     * This test checks that the color array has been correctly
     * created and is of the intended length.
     */
    @Test
    public void checkColorsArrayLength(){
        BrickWall wall = new BrickWall(3, 5);
        assertNotNull(wall.getColors());
        assertEquals(6, wall.getColors().length);
    }
    
    
    /**
     * This test checks some of the content of the color array 
     * to verify that it contains the names of colours as expected.
     */
    @Test
    public void checkColorsArrayContent(){
        BrickWall wall = new BrickWall(3, 5);
        assertNotNull(wall.getColors());
        /* 
         * Local reference to the colors array to make code easier
         * to read.
         * English spelling for readability.
         */
        String[] colours = wall.getColors();
        
        assertEquals("red", colours[0]);
        assertEquals("blue", colours[2]);
        assertEquals("black", colours[5]);
    }
    
        
}
