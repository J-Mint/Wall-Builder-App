import java.awt.*;

/**
 * A Rectangle that can be manipulated and that draws itself on a canvas.
 * Based on the Square class in the original project by Kolling and Barnes..
 * All values for distances or coordinates on the canvas are in pixels.
 * 
 * @author  Tony Beaumont.   Based on code written by Michael Kolling and David J. Barnes
 * @version 2011/14/09
 */

public class Rectangle
{
    private int length;
    private int height;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Constructor for Rectangle objects.  New Rectangle objects are
     * all positioned with their top left hand corner at (60,50), 
     * have a length of 50 and a height of 30, are coloured red
     * and are initially invisible.
     */
    public Rectangle()
    {
        this(50, 30);
    }

    /**
     * Constructor for Rectangle objects.  New Rectangle objects are
     * all positioned with their top left hand corner at (60,50), are coloured red
     * and are initially invisible.  Using this constructor the length and height
     * of the rectangle can be provided as parameters at runtime.
     * @param length The initial length of the rectangle
     * @param height The initial height of the rectangle
     */
    public Rectangle(int length, int height) {
        this.length = length;
        this.height = height;
        xPosition = 60;
        yPosition = 50;
        color = "red";
        isVisible = false;
    }

    /**
     * Draw a graphical representation of the rectangle on the canvas. 
     * If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }

    /**
     * Erase the graphical representation of the rectangle on the canvas. 
     * If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }

    /**
     * Reposition the rectangle horizontally across the canvas by a 
     * measure of 20.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Reposition the rectangle horizontally across the canvas by a 
     * measure of -20.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Reposition the rectangle vertically by a measure of -20.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Reposition the rectangle vertically by a measure of 20.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Reposition the rectangle horizontally across the canvas by a given amount.
     * @param distance The amount by which to move the rectangle.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Reposition the rectangle vertically across the canvas by a given amount.
     * @param distance The amount by which to move the rectangle.
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the rectangle horizontally across the canvas by a 
     * given amount.  The movement across the canvas will be visible. 
     * @param distance The amount by which to move the rectangle.
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the rectangle vertically across the canvas by a 
     * given amount.  The movement across the canvas will be visible. 
     * @param distance The amount by which to move the rectangle.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the length and height of the rectangle to the given values. 
     * The value of length and height must each be >= 0.
     * @param newLength the new length of the rectangle.
     * @param newHeight the new height of the rectangle.
     */
    public void changeSize(int newLength, int newHeight)
    {
        erase();
        length = newLength;
        height = newHeight;
        draw();
    }

    /**
     * @return The X coordinate of the top left hand corner of the rectangle on the canvas.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * @return The Y coordinate of the top left hand corner of the rectangle on the canvas.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Change the position of the rectangle using the given values as the coordinates of
     * the top left hand corner of the rectangle.
     * @param xPos the X coordinate of the top left corner of the rectangle.
     * @param yPos the Y coordinate of the top left corner of the rectangle.
     */
    public void setPosition(int xPos, int yPos) {
        erase();
        xPosition = xPos;
        yPosition = yPos;
        draw();
    }

    /**
     * Set the position of the rectangle to be a new, random point
     */
    public void randomizePosition() {
        Point p = new Point();
        this.setPosition(p.getX(), p.getY());
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     * @param newColor The name of the new colour of the rectangle.
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }
    
    /**
     * @return The colour of this rectangle
     */
    public String getColor() {
        return color;
    }

    /**
     * Draw the rectangle with current specifications on screen.
     */
    private void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, length, height));
            canvas.wait(10);
        }
    }

    /**
     * Erase the rectangle on screen.
     */
    private void erase()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
