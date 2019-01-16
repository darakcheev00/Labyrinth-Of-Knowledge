import java.awt.*;
/*import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the Room class it displays the background JPanel with the floor image and the 
 doors that must be on it.
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>June 04,2016</b> Arsh created this class when combining Daniel's & Arsh's programs.
 * <p>
 * 
 * Authors: Arsh Raza 
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 1 hour
 * Ms. Krasteva
 * */
public class Room extends JPanel
{
    /*Reference variable to the Application class*/
    Application app;
    /*int array that contains the doors of this specific room.*/
    int[] doors = new int [4]; //0 is south , 1 is west, 2 is north, 3 is east
    /*Boolean array which stores which doors are visible on the screen*/
    boolean[] doorsVis = new boolean [4];
    /*The JLabel that contains the floor image*/
    JLabel floor;
    /*The JLabel array that stores the 2 different types of floor images*/
    JLabel[] floorOptions = {new JLabel (new ImageIcon ("../Resources/Images\\floor.png")), new JLabel (new ImageIcon ("../Resources/Images\\stone floor.png")) };
    /*A reference to the trap door class*/
    Trapdoor trapdoor1;
     /*A reference to the trap door class*/
    Trapdoor trapdoor2;

    /*The class constructor that creates an instance of the Room class.
    It sets the floor image of the floor jpanel*/
    public Room ()
    {
 floor = floorOptions [(int) (Math.random () * 2)];
    }

    /*This method sets the door to be positioned in a certain direction, 
    and in a specific room.*/
    public void setDoor (int dir, int otherRoom)
    {
 doors [dir] = otherRoom;
    }

    /*This method checks the door*/
    public boolean checkDoor (int dir)
    {
 if (doors [dir] != 0)
     return true;
 return false;
    }
}

