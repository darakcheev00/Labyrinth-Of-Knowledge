import java.awt.*;
/*import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the Question class that stores the question info
 *
 * <h3>Modifications</h3>
 * <p>
 * <b>June 04,2016</b> Arsh and daniel made the class .
 * <p>
 *
 * Authors: Daniel Arakcheev, Arsh Raza
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 6 hours
 * Ms. Krasteva
 * */

public class Question
{
    /*The string variable that stores the question text*/
    String question;
    /*The String type array list that stores the answers to the question*/
    ArrayList < String > answers = new ArrayList < String > ();
    /*The index of the correct answer*/
    int correct;

    /*The class constructor that makes an instance of the class*/
    public Question ()
    {

    }
}

