import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the animations class where the animations are made. For an animation to happen an instance of this class 
 * must be made specifying the animation number
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>May 22,2017</b> Arsh made first draft of the class to display nothing.
 * <p>
 * <b>June 02,2017</b> Daniel made the attack and character entrance animations. 
 * <p>
 * <b>June 04,2016</b> Arsh merges Daniel's dungeon class with the playGame class.
 * <p>
 * 
 * Authors: Daniel Arakcheev, Arsh Raza 
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 6 hours
 * Ms. Krasteva
 * */

public class Animation extends JPanel implements ActionListener {
 //Reference variable to the Application class
 Application app;
 //Timer used to delay animations
 Timer timer;
 //An array of the images used for the green attack animation 
 JLabel[] greenFlashPics = new JLabel[8];
 //An array of the images used for the blue attack animation
 JLabel[] blueFlashPics = new JLabel[9];
 //An array of the images used for the character entrance animation
 JLabel[] characterPics = new JLabel[8];
 //Iterations of the timer
 int iteration = 0;
 //The space between the moving images for the attack animations.
 int step = 50;
 //Animation number
 int num;
 //x position of monster - used for "running away animation"
 int xPos = 0;
 //JLabel that contains the monster
 JLabel monster;
 //JLabel that contains the key
 JLabel key;
 //Key enlargement initial step
 int keyStep = 0;
 
 /**This is the class constructor. Used when creating an instance of the animation class. 
   * Sets up the settings to run the correct animation.
   Variable Dictionary
   Application app - a reference variable for the application class
   int num - the variable that decides which animation to execute
   JLabel monster - the label used to animate the monster
   */
 public Animation(Application app, int num, JLabel monster) {
  super(null);
  this.app = app;
  this.num = num;
  this.monster = monster;
  init();
 }
 /*The init method is used to inialize and setup the animation further.
  * It sets up the initial position and state of all the images used.
  * */
 public void init() {
  key = new JLabel(new ImageIcon("key.png"));
  this.add(key);

  for (int i = 0; i < 8; i++) {
   greenFlashPics[i] = new JLabel(new ImageIcon("../Resources/greenFlash\\greenFlash" + (i + 1) + ".png"));
   this.add(greenFlashPics[i]);
   greenFlashPics[i].setBounds(i*50, 200, 250, 146);
   greenFlashPics[i].setVisible(false);
   greenFlashPics[i].setBackground(new Color(0, 0, 0, 0));
  }

  for (int i = 0; i < 8; i++) {
   characterPics[i] = new JLabel(new ImageIcon("../Resources/character\\char" + (i + 1) + ".png"));
   this.add(characterPics[i]);
   characterPics[i].setBounds(0, 0, 150, 208);
   characterPics[i].setVisible(false);
   characterPics[i].setBackground(new Color(0, 0, 0, 0));
  }

  for (int i = 0; i < 9; i++) {
   blueFlashPics[i] = new JLabel(new ImageIcon("../Resources/blueFlash\\blueFlash" + (i + 1) + ".png"));
   this.add(blueFlashPics[i]);
   blueFlashPics[i].setBounds(450-i*50, 0, 250, 208);
   blueFlashPics[i].setVisible(false);
   blueFlashPics[i].setBackground(new Color(0, 0, 0, 0));
  }

  this.setBackground(new Color(0, 0, 0, 0));
  this.setVisible(false);
 }
 /*The reset method resets the animations after they are done so they can be run again.*/
 public void reset(){
  step = 0;
  keyStep = 0;
  xPos = 0;
  key.setVisible(false);
  repaint();
  iteration = 0;
 }
 /*The startAnimation method sets up the timer and starts it. 
  * It also determines the delay of the different animations based on the variable num passed into the class.
  The timer fires events every delay milliseconds.*/
 public void startAnimation() {
  int delay;
  if (timer == null) {
   if (num == 1)
    delay = 60;
   else
    delay = 70;

   timer = new Timer(delay, this);
   timer.setRepeats(true);
   timer.start();
   this.setVisible(true);
   this.repaint();
  }
 }

 /*The actionPerformed method executes the animations.
  * It has 3 if statements which determine the animation to be performed. 
  * If there is more that one animation happening consequtively, the following animations are nested.*/
 public void actionPerformed(ActionEvent evt) {
  this.setVisible(true);
  // Green attack & monster runs away
  if (num == 1) {
   if (iteration < 8) {
    if (iteration > 0) {
     greenFlashPics[iteration - 1].setVisible(false);
    }
    greenFlashPics[iteration].setVisible(true);
   }
   if (iteration >= 7) {
    greenFlashPics[7].setVisible(false);
    //Monster animation
    monster.setBounds(700 + xPos, 300, 200, 200);
    xPos += 12;
    if (iteration >= 40) {
     //Key animation
     ImageIcon imageIcon = new ImageIcon("../Resources/Images\\pixel_key.png");
     Image image = imageIcon.getImage(); // transform it
     Image newImg = image.getScaledInstance(0 + 10*keyStep, 0 + 5*keyStep, java.awt.Image.SCALE_SMOOTH);
     imageIcon = new ImageIcon(newImg); // transform it back
     key.setIcon(imageIcon);
     key.setVisible(true);
     key.setBounds(300-5*keyStep, 130-2*keyStep, 0 + 10*keyStep, 0 + 5*keyStep);
     //stop timer and exit
     if (iteration >=60){
      timer.stop();
      timer = null;
      key.setVisible(false);
      try {
      Thread.sleep(500);
      } catch (InterruptedException e) {
      }
      app.dunPanel.setVisible(false);
      app.dunPanel.removeAll();
      app.mapPanel.setVisible(true);
     }
    }
   }
   keyStep ++;
   step += 50;
  }
  // character appears
  if (num == 2) {
   if (iteration < 8) {
    if (iteration > 0) {
     characterPics[iteration - 1].setVisible(false);
     this.repaint();
    }

    characterPics[iteration].setVisible(true);
   }
   if (iteration == 9) {
    timer.stop();
    timer = null;
   }
  }
  // Blue flash
  if (num == 3) {
   if (iteration < 9) {
    if (iteration > 0) {
     blueFlashPics[iteration - 1].setVisible(false);
     this.repaint();
    }
    blueFlashPics[iteration].setVisible(true);
   }
   if (iteration >= 8) {
    blueFlashPics[8].setVisible(false);
    if (iteration == 20) {
     timer.stop();
     timer = null;
     try {
      Thread.sleep(500);
     } catch (InterruptedException e) {
     }
    if (app.lives == 0){
      app.goToPane(7);
     }  
    }
   }
   step += 50;
  }
  this.getParent().getParent().repaint();
  iteration++;
 }
}
