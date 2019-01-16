import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.*;

/**
 * This class is used to pick the difficulty right before the game.
 * It has 3 buttons, easy medium and hard to select the difficulty.
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>June 04,2016</b> Arsh added this class so that the level select works.
 * <p>
 * 
 * Author: Arsh Raza
 * ICS4U0
 * Ms. Krasteva
 * Date: June 04,2017
 * Time Spent: 30 minutes
 * */


public class PickDifficulty extends Slide {
  /**Variable for the background picture*/
   JLabel background;
   /**Varaible for easy button.*/
   JButton easyButton;
   /**Variable for medium difficulty button*/
   JButton mediumButton;
   /**Variable for the hard difficulty button*/
   JButton hardButton;
   /**Variable for the back button.*/
   JButton backButton;
   
   /**Reference to Application class.*/
   Application app;
   /**Class constructor used to set up the panel.*/
  public PickDifficulty(Application app) 
  {
    super(app);
    this.app = app;
    init();
  }
  /**This method is used to draw the images onto the panel. 
  It draws the backgruond and all the buttons.
  This method sets up the initial states of the buttons and labels*/
  public void init(){
   background = new JLabel(new ImageIcon("../Resources/Images\\mainmenu.png"));
   background.setBounds(0, 0, 1024, 650);
   
   
   easyButton = new JButton (new ImageIcon("../Resources/Images\\easy_button.png"));
   easyButton.setBounds(320, 180, 400, 100);
   easyButton.setBackground(new Color (120,79,37));
   
   mediumButton = new JButton(new ImageIcon("../Resources/Images\\medium_button.png"));
   mediumButton.setBounds(320, 290, 400, 100);
   mediumButton.setBackground(new Color (120,79,37));
   
   hardButton = new JButton(new ImageIcon("../Resources/Images\\hard_button.png"));
   hardButton.setBounds(320, 400, 400, 100);
   hardButton.setBackground(new Color (120,79,37));
   
  backButton = new JButton (new ImageIcon("../Resources/Images\\back button.png"));
  backButton.setBounds(880, 555, 136, 80);
  backButton.setBackground(new Color (120,79,37));
  
  this.add(backButton);
   this.add(easyButton);
   this.add(mediumButton);
   this.add(hardButton); 
   this.add(background);
   
     backButton.addActionListener(new ActionListener (){
     public void actionPerformed (ActionEvent e){
      app.goToPane(1);
     }
  });
   easyButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
     app.difficulty = 0;
     app.gameStartTime = System.currentTimeMillis();
     app.goToPane(4);
    }
   });
   
   mediumButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
      app.difficulty = 1;
      app.gameStartTime = System.currentTimeMillis();
     app.goToPane(4);
    }
   });
   
   hardButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
      app.difficulty = 2;
      app.gameStartTime = System.currentTimeMillis();
     app.goToPane(4);
    }
   });
  }

   
}
