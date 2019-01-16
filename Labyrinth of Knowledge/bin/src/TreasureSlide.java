import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.*;
/**
 * This is the TreasureSlide class which is the slide that shows up after you finish the game
    It congradulates the player and allows them to enter their name.
 *
 * <h3>Modifications</h3>
 * <p>
 * <b>June 04,2016</b> Daniel makes class.
 * <p>
 *
 * Authors: Daniel Arakcheev, Arsh Raza
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 2 hours
 * Ms. Krasteva
 * */
public class TreasureSlide extends Slide implements ActionListener
{


    /**Background image.*/
    JLabel background;
    /** Submite button.*/
    JButton submitButton;
    /**Congrats picture.*/
    JLabel congratsLabel;
    /**Enter name prompt.*/
    JLabel enterNameLabel;
    /**Time picture.*/
    JLabel timeLabel;
    /**The text field for user input.*/
    JTextField textField;
    /**Highscore to keep track of data.*/
    Highscore highscore;

    /**Keeps track of time.*/
    Timer timer;
    /**Keeps track of iterations done.*/
    int iteration = 0;

    /*The class constructor sets up the labels and submission button.
    Sets up textfield
 @param Application app reference to the Application class
    */
    public TreasureSlide (Application app)
    {
 super (app);

 submitButton = new JButton (new ImageIcon ("../Resources/Images\\submitButton.png"));
 submitButton.setBounds (852, 440, 130, 73);
 submitButton.setBackground (new Color (120, 79, 37));
 submitButton.setVisible (false);
 this.add (submitButton);

 submitButton.addActionListener (new ActionListener ()
 {
     public void actionPerformed (ActionEvent e)
     {
  //adds new highscore
  app.playerName = textField.getText ();
  app.highscores.add (new Highscore (app.playerName, (int) app.gameTime));
  app.highscores.sort (new Comparator < Highscore > ()
  {
      @ Override
   public int compare (Highscore one, Highscore two)
      {
   if (one.score < two.score)
       return -1;
   if (one.score > two.score)
       return 1;
   return 0;
      }
  }
  );
  app.saveHighscores ();
  app.goToPane (1);
     }
 }
 );

 Font timeFont = new Font ("Papyrus", Font.PLAIN, 36);
 timeLabel = new JLabel ();
 timeLabel.setFont (timeFont);
 timeLabel.setBounds (670, 330, 300, 100);
 timeLabel.setBackground (new Color (0, 0, 0, 0));
 timeLabel.setForeground (new Color (253, 229, 14));
 timeLabel.setVisible (false);
 this.add (timeLabel);

 textField = new JTextField ();
 textField.setBounds (560, 450, 250, 50);
 textField.setColumns (20);
 textField.setFont (timeFont);
 textField.setBackground (new Color (253, 229, 14));
 textField.setVisible (false);
 this.add (textField);

 enterNameLabel = new JLabel (new ImageIcon ("../Resources/Images\\getNameScreen.png"));
 enterNameLabel.setBounds (0, 0, 1024, 650);
 enterNameLabel.setVisible (false);
 this.add (enterNameLabel);

 congratsLabel = new JLabel (new ImageIcon ("../Resources/Images\\congratsScreen.png"));
 congratsLabel.setBounds (0, 0, 1024, 650);
 congratsLabel.setVisible (false);
 this.add (congratsLabel);

 // background
 background = new JLabel (new ImageIcon ("../Resources/Images\\room6.png"));
 background.setBounds (0, 0, 1024, 650);
 this.add (background);
    }


    /*The activate method is executed when the the user enters the final congradulations screen
 It starts the timer which delays the images.
    */
    public void activate ()
    {
 app.gameEndTime = System.currentTimeMillis ();
 app.gameTime = (app.gameEndTime - app.gameStartTime) / 1000;
 textField.setText ("");
 iteration = 0;
 this.repaint ();
 timer = new Timer (500, this);
 timer.setRepeats (true);
 timer.start ();
    }


    /*The actionPerformed method executes the timer delay.
    *
    */
    public void actionPerformed (ActionEvent evt)
    {
 if (iteration == 0)
     congratsLabel.setVisible (true);
 if (iteration == 2)
 {
     enterNameLabel.setVisible (true);
     timer.stop ();
     timer = null;
     showHighscorePrompt ();
 }
 iteration++;
    }

    /*The showHighscorePrompt method sets up and shows the textfield. 
    Activates the submit button.*/
    public void showHighscorePrompt ()
    {
 timeLabel.setText (app.gameTime / 60 + "min " + app.gameTime % 60 + "sec");
 timeLabel.setVisible (true);
 textField.setVisible (true);
 textField.requestFocus ();
 submitButton.setVisible (true);
 repaint ();
    }



}
