
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
    //hole the mosters come from
    Rectangle hole = new Rectangle(WIDTH / 2 - 50, HEIGHT / 2 - 50, 100, 100);
    //monsters (can have max of ten on screen at a time)
    Rectangle monster = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster1 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster2 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster3 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster4 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster5 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster6 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster7 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster8 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    Rectangle monster9 = new Rectangle(WIDTH / 2, HEIGHT / 2, 15, 15);
    //hero character
    Rectangle hero = new Rectangle(0, 0, 20, 20);
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
    //end game boolena
    boolean done;
    //death by monsters
    boolean death;
    //death by falling boolean
    boolean falldeath;
    //score system for how many monsters you kill
    int Score = 0;
    //random number of spawns (setting temproarily)
    int spawnNum = 0;
    //arrow direction
    int[] xydir = new int[2];
    //XY arrow direction-temp setting to zero
    int Xdir = 0;
    int Ydir = 0;
    //monster velocity (for each monster)
    //the faster monsters spawn later
    int mvX = 3;
    int mvY = 3;
    int mvX1 = 3;
    int mvY1 = 3;
    int mvX2 = 4;
    int mvY2 = 4;
    int mvX3 = 4;
    int mvY3 = 4;
    int mvX4 = 4;
    int mvY4 = 4;
    int mvX5 = 5;
    int mvY5 = 5;
    int mvX6 = 5;
    int mvY6 = 5;
    int mvX7 = 6;
    int mvY7 = 6;
    int mvX8 = 6;
    int mvY8 = 6;
    int mvX9 = 7;
    int mvY9 = 7;
    //mouse pressed
    boolean lclick;
    //hero health
    int hearts = 3;
    //new counter
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
        //draw the hole
        g.fillRect(hole.x, hole.y, hole.width, hole.height);

        //set the color to text color
        g.setColor(textColor);
        //set the font
        g.setFont(text);
        //draw the score (number of monsters killed
        g.drawString("" + Score, 30, 50);

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
        //if they die from monsters
        if (death == true) {
            //set text color
            g.setColor(textColor);
            //setfont
            g.setFont(text);
            //death message
            g.drawString("And the hero was slain", 30, HEIGHT / 2);
            //end game
            done = true;
        }

        //set monster color to pink (because I can)
        g.setColor(Color.pink);
        //draw the monsters
        g.fillRect(monster.x, monster.y, monster.width, monster.height);
        //as the score increases, more monsters will be drawn
        if (Score > 2) {
            //set monster color to pink (because I can)
            g.setColor(Color.pink);
            g.fillRect(monster1.x, monster1.y, monster1.width, monster1.height);
        }
        if (Score > 3) {
            //set monster color to pink (because I can)
            g.setColor(Color.MAGENTA);
            g.fillRect(monster2.x, monster2.y, monster2.width, monster2.height);
        }
        if (Score > 4) {
            //set monster color to magenta
            g.setColor(Color.magenta);
            g.fillRect(monster3.x, monster3.y, monster3.width, monster3.height);
        }
        if (Score > 5) {
            //set monster color to magenta
            g.setColor(Color.magenta);
            g.fillRect(monster4.x, monster4.y, monster4.width, monster4.height);
        }
        if (Score > 7) {
            //set monster color to orange
            g.setColor(Color.ORANGE);
            g.fillRect(monster5.x, monster5.y, monster5.width, monster5.height);
        }
        if (Score > 8) {
            //set monster color to orange
            g.setColor(Color.orange);
            g.fillRect(monster6.x, monster6.y, monster6.width, monster6.height);
        }
        if (Score > 10) {
            //set monster color to yellow
            g.setColor(Color.YELLOW);
            g.fillRect(monster7.x, monster7.y, monster7.width, monster7.height);
        }
        if (Score > 12) {
            //set monster color to yellow
            g.setColor(Color.pink);
            g.fillRect(monster8.x, monster8.y, monster8.width, monster8.height);
        }
        if (Score > 14) {
            //set monster color to white)
            g.setColor(Color.white);
            g.fillRect(monster9.x, monster9.y, monster9.width, monster9.height);
        }

        //if the hero falls down the hole (game over)
        if (falldeath == true) {
            //set font color
            g.setColor(textColor);
            //set font
            g.setFont(text);
            //game over screen
            g.drawString("And the hero fell to his death...", 30, HEIGHT / 2);
            //end game
            done = true;
        }

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
        done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            //check for collisions
            collisions();
            //check for spawns
            spawnNum = generateRandomNum();

            //if they fall down the pit, end the game
            if (falldeath == true) {
                hearts = 0;
                done = true;
            }
            //death death boolean
            if (hearts > 0) {
                death = false;
            }
            //if they reach 0 lives
            if (hearts == 0) {
                death = true;
            }

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
            //moveing the monsters
            monster.x += mvX;
            monster.y += mvY;
            //as the score incrases add more monsters and make them move
            if (Score > 2) {
                monster1.x += mvX1;
                monster1.y += mvY1;
            }
            if (Score > 3) {
                monster2.x += mvX2;
                monster2.y += mvY2;
            }
            if (Score > 4) {
                monster3.x += mvX3;
                monster3.y += mvY3;
            }
            if (Score > 5) {
                monster4.x += mvX4;
                monster4.y += mvY4;
            }

            if (Score > 7) {
                monster5.x += mvX5;
                monster5.y += mvY5;
            }
            if (Score > 8) {
                monster6.x += mvX6;
                monster6.y += mvY6;
            }
            if (Score > 10) {
                monster7.x += mvX7;
                monster7.y += mvY7;
            }
            if (Score > 12) {
                monster8.x += mvX8;
                monster8.y += mvY8;
            }
            if (Score > 14) {
                monster9.x += mvX9;
                monster9.y += mvY9;
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

            //update arrow x and y
            //if upwards shot
            if (upPressed) {
                arrow.y = arrow.y - 4;
            }
            //if downwards shot
            if (downPressed) {
                arrow.y = arrow.y + 4;
            }
            //left shot
            if (leftPressed) {
                arrow.x = arrow.x - 4;
            }
            //right shot
            if (rightPressed) {
                arrow.x = arrow.x + 4;
            }
            //if none of the arrow keys are pressed, reset arrow x and y
            if ((upPressed == false && downPressed == false && leftPressed == false && rightPressed == false)) {
                arrow.x = hero.x;
                arrow.y = hero.y;
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

            //if the arrow keys are pressed, so they can be used to fire arrows
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                //true for only a second
                downPressed = true;

            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                //true for only a second
                upPressed = true;

            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //true for only a second
                leftPressed = true;


            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //true for only a seccond
                rightPressed = true;

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
    //collision method

    public void collisions() {
        //when the hero hits a monster
        if (hero.intersects(monster)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster.x = WIDTH / 2;
            monster.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster1)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster1.x = WIDTH / 2;
            monster1.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster2)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster2.x = WIDTH / 2;
            monster2.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster3)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster3.x = WIDTH / 2;
            monster3.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster4)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster4.x = WIDTH / 2;
            monster4.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster5)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster5.x = WIDTH / 2;
            monster5.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster6)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster6.x = WIDTH / 2;
            monster6.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster7)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster7.x = WIDTH / 2;
            monster7.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster8)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster8.x = WIDTH / 2;
            monster8.y = HEIGHT / 2;
        }
        //when the hero hits a monster
        if (hero.intersects(monster9)) {
            //subract one life
            hearts = hearts - 1;
            //add one to score
            Score+=1;
            //resets monster after defeating it
            monster9.x = WIDTH / 2;
            monster9.y = HEIGHT / 2;
        }
        //when the hero falls in the hole
        if (hero.intersects(hole)) {
            //set falldeath boolean to true
            falldeath = true;
        }
        //when the arrow hits the walls
        if (arrow.intersects(topWall) || arrow.intersects(bottomWall) || arrow.intersects(leftWall) || arrow.intersects(rightWall)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster.x = WIDTH / 2;
            monster.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster1)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster1.x = WIDTH / 2;
            monster1.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster2)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster2.x = WIDTH / 2;
            monster2.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster3)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster3.x = WIDTH / 2;
            monster3.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster4)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster4.x = WIDTH / 2;
            monster4.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster5)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster5.x = WIDTH / 2;
            monster5.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster6)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster6.x = WIDTH / 2;
            monster6.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster7)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster7.x = WIDTH / 2;
            monster7.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster8)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster8.x = WIDTH / 2;
            monster8.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //when the arrow hits the monsters
        if (arrow.intersects(monster9)) {
            //return arrow to hero
            arrow.x = hero.x;
            arrow.y = hero.y;
            //damage/kill monster
            monster9.x = WIDTH / 2;
            monster9.y = HEIGHT / 2;
            //add to score
            Score += 1;
        }
        //collisions for all the monsters
        //if the monster hits the topwall
        if (monster.intersects(topWall)) {
            //change the velocity
            mvY = -mvY;
        }
        //if the monster hits bottom topwall
        if (monster.intersects(bottomWall)) {
            //change the velocity
            mvY = -mvY;
        }
        //if the monster hits the right topwall
        if (monster.intersects(rightWall)) {
            //change the velocity
            mvX = -mvX;
        }
        //if the monster hits the left topwall
        if (monster.intersects(leftWall)) {
            //change the velocity
            mvX = -mvX;
        }
        //if the monster hits the top wall
        if (monster1.intersects(topWall)) {
            //change the velocity
            mvY1 = -mvY1;
        }
        //if the monster hits bottom wall
        if (monster1.intersects(bottomWall)) {
            //change the velocity
            mvY1 = -mvY1;
        }
        //if the monster hits the right wall
        if (monster1.intersects(rightWall)) {
            //change the velocity
            mvX1 = -mvX1;
        }
        //if the monster hits the left wall
        if (monster1.intersects(leftWall)) {
            //change the velocity
            mvX1 = -mvX1;
        }
        //if the monster hits the top wall
        if (monster2.intersects(topWall)) {
            //change the velocity
            mvY2 = -mvY2;
        }
        //if the monster hits bottom wall
        if (monster2.intersects(bottomWall)) {
            //change the velocity
            mvY2 = -mvY2;
        }
        //if the monster hits the right wall
        if (monster2.intersects(rightWall)) {
            //change the velocity
            mvX2 = -mvX2;
        }
        //if the monster hits the left wall
        if (monster2.intersects(leftWall)) {
            //change the velocity
            mvX2 = -mvX2;
        }
        //if the monster hits the top wall
        if (monster3.intersects(topWall)) {
            //change the velocity
            mvY3 = -mvY3;
        }
        //if the monster hits bottom wall
        if (monster3.intersects(bottomWall)) {
            //change the velocity
            mvY3 = -mvY3;
        }
        //if the monster hits the right wall
        if (monster3.intersects(rightWall)) {
            //change the velocity
            mvX3 = -mvX3;
        }
        //if the monster hits the left wall
        if (monster3.intersects(leftWall)) {
            //change the velocity
            mvX3 = -mvX3;
        }
        //if the monster hits the top wall
        if (monster4.intersects(topWall)) {
            //change the velocity
            mvY4 = -mvY4;
        }
        //if the monster hits bottom wall
        if (monster4.intersects(bottomWall)) {
            //change the velocity
            mvY4 = -mvY4;
        }
        //if the monster hits the right wall
        if (monster4.intersects(rightWall)) {
            //change the velocity
            mvX4 = -mvX4;
        }
        //if the monster hits the left wall
        if (monster4.intersects(leftWall)) {
            //change the velocity
            mvX4 = -mvX4;
        }
        //if the monster hits the top wall
        if (monster5.intersects(topWall)) {
            //change the velocity
            mvY5 = -mvY5;
        }
        //if the monster hits bottom wall
        if (monster5.intersects(bottomWall)) {
            //change the velocity
            mvY5 = -mvY5;
        }
        //if the monster hits the right wall
        if (monster5.intersects(rightWall)) {
            //change the velocity
            mvX5 = -mvX5;
        }
        //if the monster hits the left wall
        if (monster5.intersects(leftWall)) {
            //change the velocity
            mvX5 = -mvX5;
        }
        //if the monster hits the top wall
        if (monster6.intersects(topWall)) {
            //change the velocity
            mvY6 = -mvY6;
        }
        //if the monster hits bottom wall
        if (monster6.intersects(bottomWall)) {
            //change the velocity
            mvY6 = -mvY6;
        }
        //if the monster hits the right wall
        if (monster6.intersects(rightWall)) {
            //change the velocity
            mvX6 = -mvX6;
        }
        //if the monster hits the left wall
        if (monster6.intersects(leftWall)) {
            //change the velocity
            mvX6 = -mvX6;
        }
        //if the monster hits the top wall
        if (monster7.intersects(topWall)) {
            //change the velocity
            mvY7 = -mvY7;
        }
        //if the monster hits bottom wall
        if (monster7.intersects(bottomWall)) {
            //change the velocity
            mvY7 = -mvY7;
        }
        //if the monster hits the right wall
        if (monster7.intersects(rightWall)) {
            //change the velocity
            mvX7 = -mvX7;
        }
        //if the monster hits the left wall
        if (monster7.intersects(leftWall)) {
            //change the velocity
            mvX7 = -mvX7;
        }
        //if the monster hits the top wall
        if (monster8.intersects(topWall)) {
            //change the velocity
            mvY8 = -mvY8;
        }
        //if the monster hits bottom wall
        if (monster8.intersects(bottomWall)) {
            //change the velocity
            mvY8 = -mvY8;
        }
        //if the monster hits the right wall
        if (monster8.intersects(rightWall)) {
            //change the velocity
            mvX8 = -mvX8;
        }
        //if the monster hits the left wall
        if (monster8.intersects(leftWall)) {
            //change the velocity
            mvX8 = -mvX8;
        }
        //if the monster hits the top wall
        if (monster9.intersects(topWall)) {
            //change the velocity
            mvY9 = -mvY9;
        }
        //if the monster hits bottom wall
        if (monster9.intersects(bottomWall)) {
            //change the velocity
            mvY9 = -mvY9;
        }
        //if the monster hits the right wall
        if (monster9.intersects(rightWall)) {
            //change the velocity
            mvX9 = -mvX9;
        }
        //if the monster hits the left wall
        if (monster9.intersects(leftWall)) {
            //change the velocity
            mvX9 = -mvX9;
        }

    }

    public static int generateRandomNum() {
        //randomly genorate monsters from the hole every so often
        //random chance for monsters to spawn if the radom number is greater than 10
        int randNum1 = (int) (Math.random() * (12 - 1 + 1)) + 1;
        //temp set randNum2 to zero
        int randNum2 = 0;
        if (randNum1 > 10) {
            //get a random number for the number of monsters to spawn between 1 and 6
            randNum2 = (int) (Math.random() * (5 - 1 + 1)) + 1;
        }
        //reset randNum2 if no monsters would spawn
        if (randNum1 < 10) {
            randNum2 = 0;
        }

        return randNum2;
    }
}
