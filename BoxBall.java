import java.util.Random;
import java.awt.Color;;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 * The BoxBall class gives a graphical representation of multiple bouncing balls. 
 * Each ball has the ability to move inside a box. 
 * Details of movement for each balls position and speed inside the box are randomly set.
 * There can be multiple balls, specified rom 5-30 balls for this project, that can move inside the box.
 * Ball movement is initiated by repeated calls to the 'move' method.
 * Ball movement ends when each ball's speed becomes 0.
 *  
 * @author Karen Stagg
 * @version October 12, 2020
 */
public class BoxBall
{
    private Ellipse2D.Double circle;
        
    private int xAxis;
    private int yAxis;
    private int xSpeed;
    private int ySpeed; 
    private int ballDiameter;
    private Color ballColor;
    private final Rectangle boxSettings;
    private Canvas canvas;
    
    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param speedX  the horizontal speed of the ball
     * @param speedY   the vertical speed of the ball
     * @param diameterBall  the diameter (in pixels) of the ball
     * @param colorBall  the color of the ball
     * @param settingsBox   the x, y, xwidth, and y width values of the box
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int speedX, int speedY, int diameterBall,
                     Color colorBall, Rectangle settingsBox, Canvas drawingCanvas)
    {
        xAxis = xPos;
        yAxis = yPos;
        xSpeed = speedX;
        ySpeed = speedY;
        ballDiameter = diameterBall;
        ballColor = colorBall;
        boxSettings = settingsBox;
        canvas = drawingCanvas;
        
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(ballColor);
        canvas.fillCircle(xAxis, yAxis, ballDiameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xAxis, yAxis, ballDiameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
        //Create the random object 
        Random random = new Random();
        //Compute the new position
        xAxis = xAxis + xSpeed;
        yAxis = yAxis + ySpeed;
        
        //Check to see if new position exceeds box borders,
        //use int cast since  getMax method returns a double
        //use int cast since Rectangle "get" methods returns a double
        //This is the check on the bottom edge, with ball traveling with a positive value going down
        if (yAxis > ((int)boxSettings.getY()+(int)(boxSettings.getHeight())-1) - ballDiameter && ySpeed > 0)
        {
            yAxis = ((int)boxSettings.getY()+(int)(boxSettings.getHeight())-1) - ballDiameter ;
            //Have ball travel back and re-position with new speed, that can't be 0
            ySpeed = -ySpeed + (random.nextInt(7) + 1);
            //Do a check to see if speed incrementing has run out - if so, set speed to 0
            if (ySpeed > 0)
            {
                ySpeed = 0;
            }   
        }
        //This is the check on the top edge, with ball traveling with a negative value going up
        else if (yAxis < ((int)(boxSettings.getY())+1)  && ySpeed < 0)
        {
            yAxis = (int)(boxSettings.getY()) + 1;
            //Have ball travel back and re-position with new speed, that can't be 0
            ySpeed = -ySpeed - (random.nextInt(7) + 1 );
             //Do a check to see if speed incrementing has run out - if so, set speed to 0
            if (ySpeed < 0)
            {
                ySpeed = 0;
            }  
        }   
        //This is the check on the right edge with ball traveling with a positive value going right
        if (xAxis > ((int)boxSettings.getX()+(int)(boxSettings.getWidth()) -1) - ballDiameter && xSpeed > 0)
        {
            xAxis = ((int)boxSettings.getX()+(int)(boxSettings.getWidth())-1) - ballDiameter ;
            //Have ball travel back and re-position with new speed, that can't be 0
            xSpeed = -xSpeed + (random.nextInt(7) + 1);
             //Do a check to see if speed incrementing has run out - if so, set speed to 0 
            if (xSpeed > 0)
            {
                xSpeed = 0;
            }  
            
        }
        //This is the check on the left edge with ball traveling with a negative value going left
        else if (xAxis < ((int)(boxSettings.getX())+1)  && xSpeed < 0)
        {
            xAxis = (int)(boxSettings.getX()) + 1;
            //Have ball travel back and re-position with new speed, that can't be 0
            xSpeed = -xSpeed - (random.nextInt(7) + 1);
             //Do a check to see if speed incrementing has run out - if so, set speed to 0
            if (xSpeed < 0)
            {
                xSpeed = 0;
            }  
        }  
        // draw again at new position
        draw();
    }    
     
    /**
     * return the horizontal position of this ball
     * @return int returns an integer value for the x axis
     */
    public int getXAxis()
    {
        return xAxis;
    }

    /**
     * return the vertical position of this ball
     * @return int returns an integer value for the y axis 
     */
    public int getYAxis()
    {
        return yAxis;
    }
    
    /**
     * return the horizontal speed
     * @return int returns an integer value for the x coordinate speed
     */
    public int getXSpeed()
    {
        return xSpeed;
    }
    
    /**
     * return the vertical speed
     * @return int returns an integerr value for the y coorrdinate speed
     */
    public int getYSpeed()
    {
        return ySpeed;
    }
}
