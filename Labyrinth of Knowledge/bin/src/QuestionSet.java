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

public class QuestionSet {
  
  String[] difficulties = {"easy", "medium","hard"};
  String[] types = {"english", "math", "geo"};
  /** Arraylist of question class which stores question and answers.*/
  ArrayList<Question> questions = new ArrayList<Question>();
  ArrayList<Integer> notUsedInd = new ArrayList<Integer>();
 public QuestionSet(Application app) {
   int diffInd = app.difficulty;
   int ind =0;
   for(int i = 0; i<3;i++)
   {
     String path = difficulties[diffInd]+types[i]+".txt";
     int co = 0;
     try{
      BufferedReader input = new BufferedReader (new FileReader (path)); 
      while(true)
      {
       String s = input.readLine();
       if(s==null)
         break;
       if(co==0){
         questions.add(new Question());
         questions.get(ind).question= s;
       }
       else
       {
         if(s.charAt(s.length()-1)=='*')
         {
           questions.get(ind).correct=co-1;
           s = s.substring(0,s.length()-1);
         }
        questions.get(ind).answers.add(s);
       }
       co++;
       if(co==5){
         ind++;
         co=0;
       }
      }
   }
   catch(IOException e)
   {
   }
   }
   for(int i = 0;i<questions.size();i++)
   {
    notUsedInd.add(i); 
   }
 }
 public Question getInd()
 {
  return questions.get(notUsedInd.remove((int)(Math.random()*notUsedInd.size()))); 
 }
 public static void main(String[] args)
 {
 }

}

