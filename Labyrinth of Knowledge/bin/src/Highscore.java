/**
 * This class keeps track of the name and score for players with highscores.
 * 
 * Author: Daniel Arakcheev
 * Version 1.0
 * Date June 04, 2017
 * ICS4U0
 * Ms.Krasteva
 * */
public class Highscore {
 /**name of player.*/
 String name;
 /**Player scores.*/
 int score;
 String scoreString;
 public Highscore (String name, int score){
  this.name = name;
  this.score = score;
  scoreString = score/60+"min "+score%60+"sec";
 }
}
