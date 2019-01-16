import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


/**
 * This is the gameover class that shows the game over screen. It is extends the Slide class
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>June 04,2016</b> Daniel made the final version of this class.
 * <p>
 * 
 * Authors: Daniel Arakcheev, Arsh Raza 
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 10 min
 * Ms. Krasteva
 * */

public class Gameover extends Slide {
  /**The JLabel that contains the background*/
  JLabel background;
  /**The JButton that is the back button*/
  JButton backButton;
  
  /*The class constructor creates an instance of this class, and set the position of the 
   * JLabel and JButton on the screen. It also sets the states of these components*/
  public Gameover(Application app) {
    super(app);
    backButton = new JButton (new ImageIcon("../Resources/Images\\back button.png"));
    backButton.setBounds(880, 555, 136, 80);
    backButton.setBackground(new Color (120,79,37));
    this.add(backButton);
    
    
    backButton.addActionListener(new ActionListener (){
      public void actionPerformed (ActionEvent e){
        app.goToPane(1);
      }
    });
    
    // background
    background = new JLabel(new ImageIcon("../Resources/Images\\gameOverScreen.png"));
    background.setBounds(0, 0, 1024, 650);
    this.add(background);
  }
}
