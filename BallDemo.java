import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class BallDemo - a short demonstration with the Canvas Class showing animation 
 * of multiple balls bouncing inside a box.
 *
 * @author Karen Stagg
 * @version October 12, 2020
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * Simulate multiple bouncing balls inside a box.
     * 
     * @param numBalls is the number of balls specified for simulation ranging from
     * 5-30 balls for this project.
     * 
     */
    public void boxBounce(int numBalls)
    {
        myCanvas.setVisible(true);

        // draw a Rectangele for the box
        Rectangle box = new Rectangle(50,50,250,250);
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.draw(box);

        //Create a Set collection to hold 5-30 balls
        HashSet<BoxBall> ballSet = new HashSet<BoxBall>();

        //Create a random object for assigning random attributes
        Random random = new Random();

        //Populate the Set collection with 5-30 balls
        for (int index = 0; index < numBalls; index++) 
        {
            //Set the ball's diameter
            int diameter = 16; 
            //Set x and y coordinates based on width & height of box, - balls diameter
            //Cast x and y as int since the get method on Rectangle returns double
            int x = (int)box.getX() + random.nextInt((int)box.getWidth() - 16);
            int y = (int)box.getY() + random.nextInt((int)box.getHeight() - 16);

            //Create random speeds, speed can not be = to 0
            int xSpeed = random.nextInt(50) + 1;
            int ySpeed = random.nextInt(50) +1 ;

            //Create color object & assign 3 random rgb values to max 230 so !=white
            Color color = new Color(random.nextInt(230), random.nextInt(230), 
                    random.nextInt(230));

            //create a new BoxBall object with the above details
            BoxBall ball = new BoxBall(x, y, xSpeed, ySpeed, diameter, color,
                    box, myCanvas);

            //add the new object to the collection set
            ballSet.add(ball);

            //Draw the instantiated ball
            ball.draw();
        }                        

        // make them bounce until each ball's speed becomes 0 and finishes the demo
        boolean demoNotFinished = true;
         while (demoNotFinished)
        {
            myCanvas.wait(50);     //small delay
            //Create an Interator object to iterate over set
            Iterator<BoxBall> it = ballSet.iterator();
            demoNotFinished = false;
            //Process each ball in the set
            while(it.hasNext())
            {
                BoxBall bBall = it.next();
                bBall.move();
                if (bBall.getXSpeed() != 0 || bBall.getYSpeed() != 0)
                {
                    demoNotFinished = true;
                }  
            }    
        }    
    } 
}
