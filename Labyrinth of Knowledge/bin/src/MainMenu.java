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
/*
 * Main menu class
 * 
 * Arsh Raza
 * ICS4U0
 * Ms. Krasteva
 * */

public class MainMenu extends Slide {
   JLabel background;
   JButton playButton;
   JButton iButton;
   JButton hsButton;
   JButton exitButton;
  
  public MainMenu(Application app) 
  {
    super(app);
    init();
  }
  
  public void init(){
   background = new JLabel(new ImageIcon("../Resources/Images\\mainmenu.png"));
   background.setBounds(0, 0, 1024, 650);
   
   
   playButton = new JButton (new ImageIcon("../Resources/Images\\play button.png"));
   playButton.setBounds(320, 180, 400, 100);
   playButton.setBackground(new Color (120,79,37));
   
   iButton = new JButton(new ImageIcon("../Resources/Images\\inst button.png"));
   iButton.setBounds(320, 290, 400, 100);
   iButton.setBackground(new Color (120,79,37));
   
   hsButton = new JButton(new ImageIcon("../Resources/Images\\highscores button.png"));
   hsButton.setBounds(320, 400, 400, 100);
   hsButton.setBackground(new Color (120,79,37));
   
   exitButton = new JButton (new ImageIcon("../Resources/Images\\exit button.png"));
   exitButton.setBounds(320, 510, 400, 100);
   exitButton.setBackground(new Color (120,79,37));
   
   this.add(playButton);
   this.add(iButton);
   this.add(hsButton); 
   this.add(exitButton);
   this.add(background);
   
   playButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
     app.goToPane(5);
    }
   });
   
   iButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
     app.goToPane(2);
    }
   });
   
   hsButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
     app.goToPane(3);
    }
   });
   
   exitButton.addActionListener(new ActionListener (){
    public void actionPerformed (ActionEvent e){
     System.exit(0);
    }
   });

   //ADD FOLDING OUT ANIMATION
  }

   
}