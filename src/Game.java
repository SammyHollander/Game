
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
    //rectangle for the arrow
    Rectangle arrow = new Rectangle(hero.x, hero.y, 5, 15);
    //hero movement
    boolean wPressed;
    boolean sPressed;
    boolean aPressed;
    boolean dPressed;
    //arrow fireing
    boolean downPressed;
    boolean upPressed;
    boolean leftPressed;
    boolean rightPressed;
    //arrow direction
    int[] xydir = new int[2];
    //XY arrow direction-temp setting to zero
    int Xdir = 0;
    int Ydir = 0;
    //mouse pressed
    boolean lclick;
    //hero health
    int hearts = 3;
    //new counter for arrows
    int count = 0;
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

        //arrows
        //if a key was pressed to fire an arrow

        //horozontal shot
        if (leftPressed == true) {
            //red arrow
            g.setColor(Color.red);
            g.fillRect(arrow.x, arrow.y, 15, 5);
        }
        if (rightPressed == true) {
            //red arrow
            g.setColor(Color.red);
            g.fillRect(arrow.x, arrow.y, 15, 5);
        }
        //vertical shot
        if (upPressed == true) {
            //red arrow
            g.setColor(Color.red);
            g.fillRect(arrow.x, arrow.y, 5, 15);
        }
        if (downPressed == true) {
            //red arrow
            g.setColor(Color.red);
            g.fillRect(arrow.x, arrow.y, 5, 15);
        }


        // GAME DRAWING ENDS HERE
    }
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
//           if(upPressed==true){
//               arrow.x=hero.x;
//               arrow.y=hero.y;
//           }
//           if(downPressed==true){
//               arrow.x=hero.x;
//               arrow.y=hero.y;
//           }
//           if(leftPressed==true){
//               arrow.x=hero.x;
//               arrow.y=hero.y;
//           }
//           if(rightPressed==true){
//               arrow.x=hero.x;
//               arrow.y=hero.y;
//           }



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

            //if the arrow keys are pressed, so they can be used to fire arrows
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                //true for only a second
                downPressed = true;
                arrow.x = hero.x;
                arrow.y = hero.y;
                downPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                //true for only a second
                upPressed = true;
                arrow.x = hero.x;
                arrow.y = hero.y;
                upPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //true for only a second
                leftPressed = true;
                arrow.x = hero.x;
                arrow.y = hero.y;
                leftPressed = false;

            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //true for only a seccond
                rightPressed = true;
                arrow.x = hero.x;
                arrow.y = hero.y;
                rightPressed = false;
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

            //if the arrow keys are released
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
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
}
