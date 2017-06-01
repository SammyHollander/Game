
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author holls9719
 */
public class Game extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    //Title of the window
    String title = "Adventure Game";
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // YOUR GAME VARIABLES WOULD GO HERE
    //walls of the dungon
    Rectangle leftWall = new Rectangle(0, 0, 10, HEIGHT);
    Rectangle rightWall = new Rectangle(WIDTH - 10, 0, 10, HEIGHT);
    Rectangle topWall = new Rectangle(0, 0, WIDTH, 10);
    Rectangle bottomWall = new Rectangle(0, HEIGHT - 10, WIDTH, 10);
    //hero character
    Rectangle hero = new Rectangle(WIDTH / 2, HEIGHT / 2, 20, 20);
    //hero movement
    boolean wPressed;
    boolean sPressed;
    boolean aPressed;
    boolean dPressed;
    //mouse pressed
    boolean lclick;
    //hero health
    int hearts = 3;
    //mouse location
    double mouseX = MouseInfo.getPointerInfo().getLocation().x;
    double mouseY = MouseInfo.getPointerInfo().getLocation().y;
    //coordinates of beam end point
    int[] XYdir = new int[2];
    //new counter for beam
    int count = 0;
    //beam velocity
    int avX = 3;
    int avY = 3;
    //game font
    Font text = new Font("Trade Winds", Font.BOLD, 50);
    //text color
    Color textColor = new Color(42, 92, 17);

    // GAME VARIABLES END HERE   
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public Game() {
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();

        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        //set the color to black for the walls
        g.setColor(Color.black);
        //draw the left wall
        g.fillRect(leftWall.x, leftWall.y, 10, HEIGHT);
        //draw the right wall
        g.fillRect(rightWall.x, rightWall.y, 10, HEIGHT);
        //draw the top wall
        g.fillRect(topWall.x, topWall.y, WIDTH, 10);
        //draw the bottom wall
        g.fillRect(bottomWall.x, bottomWall.y, WIDTH, HEIGHT - 10);

        //set the color to blue
        g.setColor(Color.blue);
        //drawing the hero
        g.fillRect(hero.x, hero.y, 20, 20);

        //drawing the beam
        if (lclick == true) {
            //set beam color to red
            g.setColor(Color.red);
            //getting the cordinates that the mouse is at
            mouseX = MouseInfo.getPointerInfo().getLocation().x;
            mouseY = MouseInfo.getPointerInfo().getLocation().y;
            //call the beam direction method
            XYdir = beamDirection(mouseX, mouseY, hero.x, hero.y);
            //draw the arrow
            g.drawLine(hero.x, hero.y, count * XYdir[0], count * XYdir[1]);
            //add to counter
            count++;
            if (lclick == false) {
                count = 0;
            }

        }

        //drawing the healthbar
        if (hearts == 3) {
            //set color to green
            g.setColor(Color.green);
            g.fillRect(hero.x - 5, hero.y + 25, 30, 10);
        }
        if (hearts == 2) {
            //set color to orange
            g.setColor(Color.orange);
            g.fillRect(hero.x - 5, hero.y + 25, 20, 10);
        }
        if (hearts == 1) {
            //set color to red
            g.setColor(Color.red);
            g.fillRect(hero.x - 5, hero.y + 25, 10, 10);
        }



    }
    // GAME DRAWING ENDS HERE

// This method is used to do any pre-setup you might need to do
// This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 


            //makeing it so the hero can't walk over the walls of the dungeon
            //leftwall
            if (hero.x <= 10) {
                hero.x = leftWall.x + 10;
            }
            //right wall
            if (hero.x >= rightWall.x - 20) {
                hero.x = rightWall.x - 20;
            }
            //top wall
            if (hero.y <= 10) {
                hero.y = topWall.y + 10;
            }
            //bottom wall
            if (hero.y >= bottomWall.y - 20) {
                hero.y = bottomWall.y - 20;
            }


            //moveing the hero forward
            if (wPressed) {
                hero.y = hero.y - 2;
            }
            //moveing the hero down
            if (sPressed) {
                hero.y = hero.y + 2;
            }
            //moveing the hero left
            if (aPressed) {
                hero.x = hero.x - 2;
            }
            //moveing the hero right
            if (dPressed) {
                hero.x = hero.x + 2;
            }




            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };


        }
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down

        @Override
        public void mousePressed(MouseEvent e) {
            //pushing button1
            if (e.getButton() == MouseEvent.BUTTON1) {
                lclick = true;
            }
        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {
            //releasing button 1
            if (e.getButton() == MouseEvent.BUTTON1) {
                lclick = false;
            }
        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

// Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {
        // if a key has been pressed down

        @Override
        public void keyPressed(KeyEvent e) {
            //hero movement, so the WASD keys can be used to move character
            if (e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = true;
            }

        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            //hero movement, so the WASD keys can be used to move character
            if (e.getKeyCode() == KeyEvent.VK_W) {
                wPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = false;
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        Game game = new Game();

        // starts the game loop
        game.run();
    }
//method for fireing arrows

    public static int[] beamDirection(double mouseX, double mouseY, int heroX, int heroY) {
        //getting the angle it should fire at
        //if it would be undefined
        int[] XYdir = new int[2];
        //placeholder x and y values
        int x = 0;
        int y = 0;
        //if it would be undefined
        if(heroX==(int)(mouseX)){
            x=0;
            if(mouseY<heroY){
                y=-1;
            }
            if(mouseY>heroY){
                y=1;
            }
        }
        //if its not undefined
        if(!(heroX==(int)(mouseX))){
            
        }

//        //temp setting Xdir and Ydir
//        int Xdir = 0;
//        int Ydir = 0;
//
//        //direction of thing
//        if (mouseX > heroX) {
//            Xdir = 1;
//        }
//        if (mouseX < heroX) {
//            Xdir = -1;
//        }
//        if (mouseY > heroY) {
//            Ydir = 1;
//        }
//        if (mouseY < heroY) {
//            Ydir = -1;
//        }
//        //filling XYdir array to return values
//        XYdir[0] = Xdir;
//        XYdir[1] = Ydir;

        //return the direction of beam    
        return XYdir;
    }
}
