import javax.swing.*;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the SplashScreen class is the class that runs the animation at the beginning of the program.
 * <h3>Modifications</h3>
 * <p>
 * <b>May 28,2017</b> Daniel made the entire class.
 * <p>
 *
 * Authors: Daniel Arakcheev
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 5 hours
 * Ms. Krasteva
 * */
public class SplashScreen extends Slide implements ActionListener
{
    /*The JLabel that contains the background image*/
    JLabel background;
    /*The JLabel that contains the scroll image*/
    JLabel scroll;
    /*The JLabel that contains the leftScroll image*/
    JLabel leftScroll;
    /*The JLabel that contains the rightScroll image*/
    JLabel rightScroll;
    /*The JLabel that contains the rolledScroll image*/
    JLabel rolledScroll;
    /*The JLabel that contains the sun image*/
    JLabel sun;
    /*The JPanel that fades out in the beginning*/
    JPanel panel1;

    /*The reference variable to the Timer class*/
    Timer timer;
    /*The animation step - how many pixels it moves each iteration*/
    int animationStep = 0;
    /*The yPosition used to move the folded scroll up*/
    int yPos = 0;
    /*The x position used to move the scrolls out to the sides*/
    int xPos = 0;
    /*The iteration*/
    int a = 0;
    /*The sun increment length*/
    int sunInc;

    /*The class contructor that creates and instance of the Splashscreen class.
    This method calls the init method
       @param Application app the reference variable for the application class.
    */
    public SplashScreen (Application app)
    {
 super (app);
 this.app = app;
 init ();
    }


    /*The init method that sets up and initializes all the Jlabels and add them to the Slide*/
    public void init ()
    {
 background = new JLabel (new ImageIcon ("../Resources/Images\\pyramids.png"));
 background.setBounds (0, 0, 1024, 650);

 sun = new JLabel (new ImageIcon ("../Resources/Images\\sun.png"));

 scroll = new JLabel (new ImageIcon ("../Resources/Images\\Scroll.png"));

 leftScroll = new JLabel (new ImageIcon ("../Resources/Images\\LeftScroll.png"));

 rightScroll = new JLabel (new ImageIcon ("../Resources/Images\\RightScroll.png"));

 rolledScroll = new JLabel (new ImageIcon ("../Resources/Images\\RolledScroll.png"));
 panel1 = new JPanel ();

 this.add (panel1);
 this.add (leftScroll);
 this.add (rightScroll);
 this.add (scroll);
 this.add (rolledScroll);
 this.add (sun);
 this.add (background);
 panel1.setBackground (new Color (0, 0, 0, 0));
 panel1.setBounds (0, 0, 1024, 690);
 panel1.setVisible (true);
    }


    public void activate ()
    {
 animate ();
    }


    /*The animate method starts the timer and sets the delay.*/
    private void animate ()
    {
 int delay = 48;
 a = 255;

 timer = new Timer (delay, this);
 timer.setRepeats (true);
 timer.start ();
    }


    /*The actionPerformed method executes the animations.
 *It has an if statement which switches the animations
 *The animations are nested. 
    */
    public void actionPerformed (ActionEvent evt)
    {
 //Pane fading out
 if (animationStep == 0)
 {
     panel1.setBackground (new Color (0, 0, 0, a));
     repaint ();
     sun.setBounds (750, 200 - sunInc, 104, 104);
     a -= 4;
     sunInc += 2;
     if (a <= 0)
     {
  animationStep = 1;
     }
 }
 //Scroll sliding up
 else if (animationStep == 1)
 {
     rolledScroll.setBounds (470, 580 - yPos, 94, 190);
     yPos += 10;
     if (yPos == 160)
     {
  animationStep = 2;
     }
 }
 //Scroll unrolling
 else if (animationStep == 2)
 {
     scroll.setBounds (115, 420, 800, 190);
     leftScroll.setBounds (120 - xPos, 426, 403, 180);
     rightScroll.setBounds (506 + xPos, 420, 476, 190);
     xPos += 10;
     rolledScroll.setVisible (false);
     if (xPos == 370)
     {
  timer.stop ();
  try
  {
      Thread.sleep (10);
  }
  catch (InterruptedException e)
  {
  }
  app.goToPane (1);
     }
 }

    }
}
