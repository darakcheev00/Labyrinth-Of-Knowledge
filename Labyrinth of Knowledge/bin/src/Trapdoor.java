import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the Trapdoor class.
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>June 02,2017</b> Daniel and Arsh created the class. 
 * 
 * Authors: Daniel Arakcheev
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 1 hour
 * Ms. Krasteva
 * */

public class Trapdoor extends JPanel
{
    /*The JLabel that hold the closed door image*/
    JLabel closed;
    /*The JLabel that hold the open door image*/
    JLabel open;
    /*The question to be displayed*/
    Question question;
    /*The boolean variable that determines if the trapdoor is open or closed*/
    boolean isOpen = true;

    /*The class constructor that creates an instance of the Trapdoor class. Sets up trapdoor labels.
       @param int x x position of trapdoor
       @param int y y position of trapdoor
       @param Question q reference to the question in trapdoor
    */
    public Trapdoor (int x, int y, Question q)
    {
 super (null);

 question = q;

 closed = new JLabel (new ImageIcon ("../Resources/Images\\trapDoorClosed.png"));
 closed.setBounds (0, 0, 51, 51);
 closed.setVisible (false);
 this.add (closed);

 open = new JLabel (new ImageIcon ("../Resources/Images\\trapDoorOpen.png"));
 open.setBounds (0, 0, 51, 80);
 open.setVisible (true);
 this.add (open);

 this.setBounds (x, y, 51, 80);

 this.setBackground (new Color (0, 0, 0, 0));
    }

    /*This method executes when the character enters the trapdoor*/
    public void enter ()
    {
 closed.setVisible (true);
 open.setVisible (false);
 isOpen = false;
 repaint ();
    }

    /*This method executes when character exits the trapdoor*/
    public void reset ()
    {
 closed.setVisible (false);
 open.setVisible (true);
 isOpen = true;
 repaint ();
    }
}
