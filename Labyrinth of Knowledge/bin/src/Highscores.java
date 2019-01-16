import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;
import java.io.*;

/**
 * This class displays the highscores on the screen. 
 * 
 * Author: Daniel Arakcheev
 * Version 1.0
 * Date June 04, 2017
 * ICS4U0
 * Ms.Krasteva
 * */
public class Highscores extends Slide {
  /** Background image.*/
 JLabel background;
 /**Back button.*/
 JButton backButton;
 /**Labels for the names of players.*/
 JLabel [] nameLabels = new JLabel [10];
 /** Labels for the scores of players.*/
 JLabel [] scoreLabels = new JLabel [10];
 /**Font that we use.*/
 Font font = new Font ("Papyrus", Font.BOLD, 36);
 /**Constructor which calls init.*/
 public Highscores(Application app) {
  super(app);
  init();
 }

 /**This method sets up the nameLabels and scoreLabels.
   * Also it adds the back button and background to the panel.*/
 public void init() {  
  for (int i = 0;i<7;i++){
   nameLabels[i]=new JLabel();
   nameLabels[i].setBounds(300,250+45*i,250,50);
   nameLabels[i].setFont(font);
   nameLabels[i].setBackground(new Color (0,0,0,0));
   nameLabels[i].setForeground(Color.BLACK);
   nameLabels[i].setVisible(true);
   this.add(nameLabels[i]);
   
   scoreLabels[i]=new JLabel();
   scoreLabels[i].setBounds(600,250+45*i,250,50);
   scoreLabels[i].setFont(font);
   scoreLabels[i].setBackground(new Color (0,0,0,0));
   scoreLabels[i].setForeground(Color.BLACK);
   scoreLabels[i].setVisible(true);
   this.add(scoreLabels[i]);
  }
  
  
  backButton = new JButton (new ImageIcon("../Resources/Images\\back button.png"));
  backButton.setBounds(880, 555, 136, 80);
  backButton.setBackground(new Color (120,79,37));
  this.add(backButton);
  
  background = new JLabel(new ImageIcon("../Resources/Images\\highScores.png"));
  background.setBounds(0, 0, 1024, 650);
  this.add(background);
  
  backButton.addActionListener(new ActionListener (){
     public void actionPerformed (ActionEvent e){
      app.goToPane(1);
     }
  });
 }
 /**
  * This sorts the highscores just in case the highscore text file was manually changed.
  * <p>
  * 
  * */
 public void sortHighscores()
 {
   int n = app.highscores.size();
        for (int i=1; i<n; ++i)
        {
            Highscore key = new Highscore(app.highscores.get(i).name,app.highscores.get(i).score);
            int j = i-1;

            while (j>=0 && app.highscores.get(j).score > key.score)
            {
                change(j+1,j);
                j = j-1;
            }

            app.highscores.get(j+1).score = key.score;
    app.highscores.get(j+1).name = key.name;
    app.highscores.get(j+1).scoreString = key.scoreString;
        }
 }
 
 /**
  * This method used to update a highscores index.
  * */
 public void change(int i, int j)
 {
    app.highscores.get(i).score = app.highscores.get(j).score;
    app.highscores.get(i).name = app.highscores.get(j).name;
    app.highscores.get(i).scoreString = app.highscores.get(j).scoreString;
 }
 @Override
 public void activate(){
  backButton.setVisible(true);
 // sortHighscores();
  printHighscores();
 }

 public void deactivate(){
  backButton.setVisible(false);
 }
 /**
  * Prints the highscores.
  * */
 private void printHighscores(){
  for (int i = 0;i<7;i++){
   if (i<app.highscores.size()){
    nameLabels[i].setText(app.highscores.get(i).name);
    scoreLabels[i].setText(app.highscores.get(i).scoreString);
   }
  }
 }
 
 
 
}
