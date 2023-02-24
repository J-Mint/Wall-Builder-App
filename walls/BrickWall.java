import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Java tool to draw brick walls
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BrickWall
{
    private int wallHeight;
    private int wallWidth;

    private String[] colors;
    private int currentColor, colorMatch;

    private Scanner scanner;

    private boolean multicolor, decrease, symmetry, found;

    private ArrayList<Rectangle> bricks, colorBricks;
    //complete with remaining field declarations

    /**
     * Constructor for objects of class BrickWall.
     * @param rows The number of rows in the wall
     * @param rowlen The maximum number of bricks in a row
     */
    public BrickWall(int wallWidth, int wallHeight)
    {
        if( wallWidth >0 && wallWidth <=24 && wallHeight >0 && wallHeight <= 31){
            setUpColors();
            currentColor = 0;
            bricks = new ArrayList<Rectangle>();

            //complete the constructor
            this.wallWidth = wallWidth;
            this.wallHeight = wallHeight;
        } else {
            //specified size is not acceptable. use defualt values for the width and height.
            setUpColors();
            currentColor = 0;
            bricks = new ArrayList<Rectangle>();
            this.wallWidth = 24;
            this.wallHeight = 31;
        }
    }
    
    public int getDimension(String dimensionToFind){
        if (dimensionToFind == "width"){
            return wallWidth;
        } else if (dimensionToFind == "height"){
            return wallHeight;
        } else {
           return 0; 
        }
    }
    
    private void setUpColors() {
        colors = new String[6];
        colors[0] = "red";
        colors[1] = "yellow";
        colors[2] = "blue";
        colors[3] = "green";
        colors[4] = "magenta";
        colors[5] = "black";    
    }

    /**
     * Draw the wall.  The first brick will be positioned at the coordinates (10, 550).  
     * The number of bricks in a row is specified by setRowLength().  The maximum number of rows
     * is specified by setNumRows().  If decrease is true, each subsequent row of bricks 
     * contains one brick less than the previous row.  If symmetric is true AND decrease is true then
     * the wall is pyramid shaped.  If symmetric is false AND decrease is true then the wall is shaped
     * like a right angle triangle.
     */
    public void draw()
    {
        eraseWall(); 

        /* ------------------------------
        // Write your code below this comment
        -------------------------------- */

        drawManyRows(20, 485, wallWidth, wallHeight);
    }

    /**
     * Draw a rectangle 
     */
    public void drawBrick(int startX, int startY){
        //create a rectangle called brick of size 52 width, 15 height
        Rectangle brick = new Rectangle(52, 15);
        // set the location of brick to the specified location
        brick.setPosition(startX,startY);
        //check to see what color the brick should be. 
        if(multicolor){
            //if the brick should be multicolored set the color to the current index of the color array and then add 1 to the index so that the next brick is a different color.
            brick.changeColor(colors[currentColor]);
            currentColor++;
            if (currentColor >5){
                currentColor = 0;
            }
            //if the brick is not supposed to be multicolored, set the color to red (index 0)
        }else{
            brick.changeColor(colors[0]);
        }
        brick.makeVisible();
        //add the brick to the array list so that it gets deleted by erase wall and is searchable.
        bricks.add(brick);
    }
    
    /**
     * Return the current color
     */
    public int getCurrentColor(){
        return currentColor;
    }
    
    /**
     * Draw a row of rectangles
     */
    public void drawRow(int startX, int startY, int lengthOfRow){
        //draw a row of specified length by iterating through lengthOfRow and drawing a brick for each iteration.
        for (int i = 0; i < lengthOfRow; i++){
            drawBrick(startX, startY);
            //move the location to the right by the length of a brick so that the next brick gets placed directly next to the previous.
            startX += 52; 
        }
    }

    /**
     * Draw a stack of rows of rectangles
     */
    public void drawManyRows(int startX, int startY, int lengthOfRow, int numOfRows){
        for (int j = 0; j < numOfRows; j++){
            drawRow(startX, startY, lengthOfRow);
            startY -= 15;
            //if symmetry is on, make each new row have a half brick indent from the previous row.
            if(symmetry){
                startX += 26;
            }
            //if symmetry is on make each new row one brick less than the previous.
            if (decrease){
                lengthOfRow--;
            }
        }
    }

    /**
     * Turn on multicolor
     */
    public void multicolorOn(){
        multicolor = true;
    }

    /**
     * Turn off multicolor
     */
    public void multicolorOff(){
        multicolor = false;
    }

    /**
     * Turn on decrease
     */    
    public void decreaseOn(){
        decrease = true;
    }

    /**
     * Turn off decrease
     */ 
    public void decreaseOff(){
        decrease = false;
    }
    
    /**
     * find out whether symmetry is on
     */ 
    public boolean isSymmetryOn(){
        return symmetry;
    }
    
    /**
     * Turn on symmetry
     */ 
    public void symmetricalOn(){
        if (decrease){
            symmetry = true;
        }
    }

    /**
     * Turn off symmetry
     */ 
    public void symmetricalOff(){
        symmetry = false;
    }

    /**
     * Check whether a color input is a recognised color
     */ 
    public void isValidColor(String colorToFind){
        //determine if color is valid
        colorMatch = 999;
        for (int i = 0; i < colors.length; i++){
            if (colors[i] == colorToFind){
                colorMatch = i;
            }
        }
    }

    /**
     * Return the first brick that matches the specified color to find
     */ 
    public Rectangle findFirstBrickByColor(String colorToFind){
        isValidColor(colorToFind);
        //if color is not valid, colorMatch will still be 999, an impossible number
        if (colorMatch == 999){
            System.out.println("not a valid color");
            return null;
        } else {
            //if it is a valid color but multicolor is off, the only color can be red and if the color to find isn't red then return null
            if (multicolor == false && colors[colorMatch] != colors[0]){
                System.out.println("color cannot be found");
                return null;
            } else {
                int i = 0;
                //search through bricks arraylist until a match is found.
                while (i < bricks.size() && found == false){
                    //if match is found, make found true and return the rectangle. 
                    if (bricks.get(i).getColor() == colors[colorMatch]){
                        found = true;
                        return bricks.get(i);
                    }
                    i++;
                }
                //no match found
                System.out.println("color cannot be found");
                return null;
            }
        }
    }

    /**
     * Return a list of bricks which match the specified color to find.
     */ 
    public ArrayList findBricksByColor(String colorToFind){
        found = false;
        colorBricks = new ArrayList<Rectangle>();
        isValidColor(colorToFind);
        //if color is not valid, colorMatch will still be 999, an impossible number
        if (colorMatch == 999){
            System.out.println("not a vlaid color");
            return null;
        } else {
            //if it is a valid color but multicolor is off, the only color can be red and if the color to find isn't red then return null
           if (multicolor == false && colors[colorMatch] != colors[0]){
               System.out.println("color cannot be found"); 
               return null;
            } else {
                int i = 0;
                while (i < bricks.size()){
                    if (bricks.get(i).getColor() == colors[colorMatch]){
                        colorBricks.add(bricks.get(i));
                    }
                    i++;
                }
                System.out.println("color cannot be found");
                return colorBricks;
            }
        }
    }

    /**
     * Find out whether a rectangle exists at the specified location and if it does return that brick.
     */ 
    public Rectangle findBrickAt(int xCoord, int yCoord){
        for (int i = 0; i < bricks.size(); i++){
            //xCoordinate match
            if (bricks.get(i).getXPosition() == xCoord){
                //yCoordinate Match
                if (bricks.get(i).getYPosition() == yCoord){
                    //perfect match
                    return bricks.get(i);
                }
            }
        }
        //no match
        System.out.println("No brick at specified coordinates");
        return null;
    }

    /**
     * Clear the canvas
     */ 
    public void eraseWall() {
        Canvas canvas = Canvas.getCanvas();
        for(int i = 0; i < bricks.size(); ++i) {
            canvas.erase(bricks.get(i));
        }
        bricks = new ArrayList<Rectangle>();
    }    

    /**
     * Accessor for the colors array
     */
    public String[] getColors(){
        return colors;
    }
}
