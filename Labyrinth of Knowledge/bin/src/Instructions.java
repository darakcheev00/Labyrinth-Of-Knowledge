import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * This is the instructions which is a Slide that displays the instructions to the user.
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>May 22,2017</b> Arsh made first draft of the class to display nothing.
 * <p>
 * <b>June 02,2017</b> Daniel finished the class. 
 * 
 * Authors: Daniel Arakcheev
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 1 hour
 * Ms. Krasteva
 * */
public class Instructions extends Slide
{
    /*JLabel that contains the background image*/
    JLabel background;
    /*The JButton that brings user back to the main menu*/
    JButton backButton;

    /*The class constructor that creates an instance of this class and 
    passes in the reference of the Application class*/
    public Instructions (Application app)
    {
 super (app);
 init ();
    }

    /*The init method that displays the button and background*/
    public void init ()
    {
 background = new JLabel (new ImageIcon ("../Resources/Images\\Instructions.png"));
 background.setBounds (0, 0, 1024, 650);


 backButton = new JButton (new ImageIcon ("../Resources/Images\\back button.png"));
 backButton.setBounds (880, 555, 136, 80);
 backButton.setBackground (new Color (120, 79, 37));
 this.add (backButton);
 this.add (background);

 backButton.addActionListener (new ActionListener ()
 {
     public void actionPerformed (ActionEvent e)
     {
  app.goToPane (1);
     }
 }
 );

    }
}
