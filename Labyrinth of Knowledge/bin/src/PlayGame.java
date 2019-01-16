
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import java.io.*;
import java.util.*;
import java.awt.*; 
import javax.swing.*;
/**
 * This is the main class where the gameplay takes place. The rooms, character data, and dungeon is set up here.
 * The map is set up at first, then all the graphics are added to the panel.
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>May 22,2017</b> Arsh made first draft of the class to display nothing.
 * <p>
 * <b>May 27,2017</b> Daniel made second draft of the playGame class to display a rooms and be able to move through them.
 * <p>
 * <b>June 02,2017</b> Arsh changes the playGame class to make it more simple to set up the map and rooms.
 * <p>
 * <b>June 04,2016</b> Arsh merges Daniel's dungeon class with the playGame class.
 * <p>
 * 
 * Authors: Arsh Raza, Daniel Arakcheev
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 14 hours
 * Ms. Krasteva
 * */
public class PlayGame extends Slide implements ActionListener, KeyListener{
 final int numRooms = 6;
 Room[] rooms = new Room[numRooms+1];
 
 /** Current room being displayed*/
 Room room = rooms[1];
 /**Amount of lives.*/
 int lives = 3;
 /**Amount of keys.*/
 int keys = 0;
 /**Current room number being displayed.*/
 int curRoomNum = 1;
 
 /**The back button.*/
 JButton backButton;
 
 /**The panel for the contents of the room.*/
 JPanel roomPanel = new JPanel(null);
 /**The panel for the character.*/
 JPanel characterPanel;
 /**The panel for the entire map, includes evreything.*/
 JPanel mapPanel = new JPanel(null);
 
 /**The JLabel for the floor image.*/
 JLabel floor;
 /**The JLabel for the north door.*/
 JLabel doorNorth = new JLabel(new ImageIcon("../Resources/Images\\door.png"));
 /**The JLabel for the east door.*/
 JLabel doorEast = new JLabel(new ImageIcon("../Resources/Images\\doorEast.png"));
 /**The JLabel for the west door.*/
 JLabel doorWest = new JLabel(new ImageIcon("../Resources/Images\\doorWest.png"));
 /**The JLabel for the south door.*/
 JLabel doorSouth = new JLabel(new ImageIcon("../Resources/Images\\doorSouth.png"));

 /**The JLabel for the prompt to unlock a door.*/
 JLabel prompt1 = new JLabel("Press 'q' to unlock this door.");
 /**The JLabel for the prompt to need keys to open a door.*/
 JLabel prompt2 = new JLabel("You need two keys to open this door!");
 /** The JLabel for upwards position of the character.*/
 JLabel upPos;
 /**The JLabel for the downwards position of the character.*/
 JLabel downPos;
 /**The JLabel for the left position of the character.*/
 JLabel leftPos;
 /**The JLabel for the right position of the character.*/
 JLabel rightPos;
 /**The direction the character is facing right now.*/
 int dir;
 /**The x coordinate position of the player.*/
 int x = 485;
 /**The y pixel coordinate position of the player.*/
 int y = 470;
 /**The speed at which the player moves.*/
 int speed = 10;
 /**The starting direction of the player.*/
 int startDir = 1;// 1-Up, 2-Right, 3-Down, 4-Left
 
 /**Reference variable to the Application class.*/
 Application app;

 /**The current question being displayed.*/
 Question question;
 /**The JLabel for the background of the dungeon.*/
 JLabel background;
 /**The JLabel for displaying the question.*/
 JLabel questionLabel;

 /**The panel for the dungeon content.*/
 JPanel dunPanel = new JPanel(null);
 
 /**Animation object for the green flash.*/
 Animation greenFlash;
 /**The Animation object for the blue flash.*/
 Animation blueFlash;
 /**Animation object for the character.*/
 Animation character;
 /**The monster number to display.*/
 int monsterNumber;
 /**The JLabel for the picture of the monster.*/
 JLabel monsterLabel;
 
 /**The button for the answer choice.*/
 JButton choice1;
 /**The button for the answer choice.*/
 JButton choice2;
 /**The button for the answer choice.*/
 JButton choice3;
 /**The button for the answer choice.*/
 JButton choice4;
 
 /**Array to keep track of displaying hearts.*/
 JLabel [] hearts;
 /**Panel to add the amount of hearts to display.*/
 JPanel heartsPanel;
 
 /**This is the class constructor. Used to call the functions to draw the rooms and characters. Adds
   * everything to the main panel.*/
 public PlayGame(Application app) {
   super(app);
   
   this.app = app;
   app.lives = lives;
  setMap();
  addKeyListener(this);
  
  // button | messes the program up--------------------------------------------------
   backButton = new JButton(new ImageIcon("../Resources/Images\\back button.png"));
  backButton.setBounds(880, 5, 136, 80);
  backButton.setBackground(new Color(120, 79, 37));
  mapPanel.add(backButton);
  backButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    app.goToPane(1);
     
     
   }
  });
  //--------------------------------------------
  setCharacter();

  drawRoom(curRoomNum);
  roomPanel.setBounds(0, 0, 1024, 643);
  mapPanel.setBounds(0,0,1024,643);
  dunPanel.setBounds(0,0,1024,643);
  
  // prompt setup ------------------------
  mapPanel.add(prompt1);
  mapPanel.add(prompt2);
  prompt1.setLocation(340,280);
  prompt1.setSize(500,100);
  prompt1.setForeground(Color.white);
  prompt1.setFont (prompt1.getFont ().deriveFont (28.0f));
  prompt1.setVisible(false);
  prompt2.setLocation(340,300);
  prompt2.setSize(500,100);
  prompt2.setForeground(Color.white);
  prompt2.setFont (prompt2.getFont ().deriveFont (28.0f));
  prompt2.setVisible(false);
  //-------------------------------
  mapPanel.add(roomPanel);
  this.add(mapPanel);
  this.add(dunPanel);
  dunPanel.setVisible(false);
  
  
  app.dunPanel = dunPanel;
  app.mapPanel = mapPanel;


 }
 
 /**This method is used to set up the character panel.
   * It adds all the images in and then adds them to the correct location on the panel.
   * */
 public void setCharacter()
 {
  upPos = new JLabel(new ImageIcon("../Resources/Images\\moveUp.png"));
  downPos = new JLabel(new ImageIcon("../Resources/Images\\moveDown.png"));
  leftPos = new JLabel(new ImageIcon("../Resources/Images\\moveLeft.png"));
  rightPos = new JLabel(new ImageIcon("../Resources/Images\\moveRight.png"));
  upPos.setBounds(0, 0, 83, 83);
  downPos.setBounds(0, 0, 83, 83);
  leftPos.setBounds(0, 0, 83, 83);
  rightPos.setBounds(0, 0, 83, 83);

  characterPanel = new JPanel(null);
  characterPanel.add(upPos);
  characterPanel.add(downPos);
  characterPanel.add(leftPos);
  characterPanel.add(rightPos);
  characterPanel.setBounds(x, y, 83, 83);
  characterPanel.setBackground(new Color(0, 0, 0, 0));
  characterPanel.setVisible(true);

  mapPanel.add(characterPanel); 
 }
 
 /**
  * This method is used to turn the direction of the character and draw the correct
  * new character image*/
  private void turnCharacter(int direction) {
  if (this.dir != direction) {
   downPos.setVisible(direction == 3);
   upPos.setVisible(direction == 1);
   rightPos.setVisible(direction == 2);
   leftPos.setVisible(direction == 4);
   repaint();

   this.dir = direction;
  }
 }

 // dir determines up down or left right (0 = up/down, 1 = left/right)
 // step determines when up and when down (for up/down movement) (step>0 =
 // downwards, step<0 = upwards)
 // and when right and when left (for left/right movement)
 // x and y are the starting position
  /**
   * This method moves the character. It moves it either vertically or horizontally by the speed.
   * 
   * 
  * <h3>Local Variables</h3>
  * <p>
  * <b>dir</b> An integer that represents which direction to move the character.
  * <p>
   * */
 public void move(int dir) {
  // down
  if (dir == 3 && y < 450) {
   y += speed;
  }
  // up
  if (dir == 1 && y > 130) {
   y -= speed;
  }
  // right
  if (dir == 2 && x < 795) {
   x += speed;
  }
  // left
  if (dir == 4 && x > 150) {
   x -= speed;
  }
  characterPanel.setBounds(x, y, 83, 83);

 }
 
 /**
  * This room is used to transition from one room to another.
  * It sets the prompts to nonvisible.
  * */
 private void transitionRoom(int dir)
 {
  drawRoom(room.doors[dir]);
  if(dir==0)
  {
  x = 500;
  y = 145;
  }
  else if(dir == 1)
  {
    x = 780;
    y = 300;
  }
  else if(dir == 2)
  {
   x = 500;
   y = 435;
  }
  else
  {
   x= 165;
   y = 300; 
  }
  this.prompt1.setVisible(false);
  this.prompt2.setVisible(false);
  characterPanel.setBounds(x, y, 83, 83);
 }
 
 /**
  * This method is called after a key is pressed to move the character.
  * It calls the other methods to move the character and also checks whether
  * there are any collisions with trapdoors and regular doors.
  * * <h3>Local Variables</h3>
  * <p>
  * <b>td</b> This is a temporary Trapdoor variable to keep track of the trapdoor player collided with.
  * <p>
  * <b>nearDoor</b> This keeps track of which door the player is near.
  * <p>
  * */
 private void startMove(int direction) {
  turnCharacter(direction);
  move(direction);
  
  int nearDoor = doorIsNear();
  if(nearDoor!=-1)
  {
    if(room.doorsVis[nearDoor])
    {
     transitionRoom(nearDoor); 
     return;
    }
    if(keys<=1)
      this.prompt2.setVisible(true);
    else
    {
     this.prompt1.setVisible(true);
    }

  }
  else
  {
   this.prompt1.setVisible(false); 
   this.prompt2.setVisible(false); 
  }
  Trapdoor td = detectTrapdoor();
  if(curRoomNum!=-1 && td!=null)
  {
    question = td.question;
    td.enter();
    initDun();
    
  }
 }
 
 /**
  * This method is used to detect whether or not a player has collided with a trapdoor.
  * *<h3>Local Variables</h3>
  * <p>
  * <b>player</b> A rectangle object to represent the player location.
  * <p>
  * <b>td</b> A rectangle object to represent the locations of the trapdoors.
  * 
  * */
 public Trapdoor detectTrapdoor()  
 {
   Rectangle player = new Rectangle(x,y,83,83);
   Rectangle td = new Rectangle(250,260,51,51);
   if(player.intersects(td)&&room.trapdoor1.isOpen==true)
   {
    return room.trapdoor1; 
   }
   td =  new Rectangle(650,260,51,51);
   if(player.intersects(td)&&room.trapdoor2.isOpen==true)
     return room.trapdoor2;
   return null;
   
 }
 
 /**
  * This method checks whether or not the player has collided with a door. 
  * If they have, it returns the door direction number, otherwise it returns -1.
  * */
 public int doorIsNear()
 {
   if(room.checkDoor(0)&&x>=457&&x<=557&&y>440)
   {
     return 0;
   }
   if(room.checkDoor(1)&&y>=251&&y<=361&&x<160)
   {
    return 1;
   }
   if(room.checkDoor(2) && x>=457&&x<=557&&y<140)
   {
     return 2;
   }
   if(room.checkDoor(3) && y>=251&&y<=361&&x>785)
   {
     return 3;
   }
   return -1;
  
 }
 /**
  * This method sets the specified door as visited so the character doesn't need to unlock it later.
  * */
 private void setAsVis(int doorNum)
 {
   room.doorsVis[doorNum]=true;
   rooms[room.doors[doorNum]].doorsVis[(doorNum+2)%4]=true;
 }
 
 
 /**
  * This methods draws the room.
  *  <h3>Local Variables</h3>
  * <p>
  * <b>heartImg</b>This is a variable for the picture of the heart,
  * <p>
  * <b>keyImg</b>This is a variable for the picture of the key,
  * <p>
  * <b>heartCount</b>This is a JLabel to display the lives count,
  * <p>
  * <b>keyCount</b>This is a JLabel to keep track of the count of keys.
  * <p>
  * */
  public void drawRoom(int roomNum)
 {
    if(roomNum == 6)
      app.goToPane(6);
   roomPanel.removeAll();
   
   curRoomNum = roomNum;
   
   JLabel heartImg = new JLabel(new ImageIcon("../Resources/Images\\pixel_heart.png"));
   JLabel keyImg = new JLabel(new ImageIcon("../Resources/Images\\pixel_key.png"));
   JLabel heartCount = new JLabel("" + lives);
   JLabel keyCount = new JLabel(""+keys);
   
  roomPanel.add(heartCount);
  heartCount.setLocation(50,0);
  heartCount.setSize(500,100);
  heartCount.setForeground(Color.white);
  heartCount.setFont (heartCount.getFont ().deriveFont (28.0f));
  roomPanel.add(keyCount);
  keyCount.setLocation(50,60);
  keyCount.setSize(500,100);
  keyCount.setForeground(Color.white);
  keyCount.setFont (keyCount.getFont ().deriveFont (28.0f));

   heartImg.setBounds(10,35,36,34);
   keyImg.setBounds(10,75,30,56);
   roomPanel.add(heartImg);
   roomPanel.add(keyImg);
   
   room = rooms[roomNum];
   
   if(roomNum!=6){
   roomPanel.add(room.trapdoor1);
   roomPanel.add(room.trapdoor2);
   }
   floor = room.floor;
   floor.setBounds(0,0,1024,643);
   if(room.checkDoor(0))
   {
     doorSouth.setBounds(457,520,110,74);
     roomPanel.add(doorSouth);
   }
   if(room.checkDoor(1))
   {
     doorWest.setBounds(80,251,74,110);
     roomPanel.add(doorWest);
   }
   if(room.checkDoor(2))
   {
     doorNorth.setBounds(457,58,110,74);
     roomPanel.add(doorNorth);
   }
   if(room.checkDoor(3))
   {
     doorEast.setBounds(870,251,74,110);
     roomPanel.add(doorEast);
   }
   roomPanel.add(floor);
   repaint();
 }
 
 /**
  * This method sets the map arrays up by reading in the map from the Map.txt folder.
  * It also sets up the trapdoors in each room.
  * <p>
  * <h3>Local Variables </h3>
  * <p>
  * <b>roomNum</b> The number of the correct room being processed.
  * <p>
  * <b>questionSet</b> A QuestionSet variable used  to get random questions for each trapdoor.
  * <p>
  * <h3>Exception handling</h3>
  * <p>
  * Exception handling used to read from the file.
  * <p>
  * */
 public void setMap()
 {
   int row = -1;
   
   try{
    BufferedReader input = new BufferedReader (new FileReader ("Map.txt")); 
    ArrayList<String> grid = new ArrayList<String>();
    while(true)
    {
      String temp = input.readLine();
      if(temp == null)
        break;
      grid.add(temp);
      row++;
      for(int col = 1; col<temp.length();col++)
      {
       if(temp.charAt(col)!='.')
       {
        int roomNum = temp.charAt(col)-'0'; 
        rooms[roomNum] = new Room();
        if(temp.charAt(col-1)!='.')
        {
         int otherRoom = temp.charAt(col-1)-'0';
         rooms[roomNum].setDoor(1,otherRoom);
         rooms[otherRoom].setDoor(3,roomNum);
        }
        if(row!=0&&grid.get(row-1).charAt(col)!='.')
        {
          int otherRoom = grid.get(row-1).charAt(col)-'0';
          //System.out.println(roomNum+" "+otherRoom);
          rooms[roomNum].setDoor(2,otherRoom);
          rooms[otherRoom].setDoor(0,roomNum);
        }
        
       }
      }
    }
   }
   catch(IOException e)
   {
   }
   
   QuestionSet questionSet = new QuestionSet(app);
   for(int i = 1; i<=numRooms-1;i++)
   {
     rooms[i].trapdoor1 = new Trapdoor(250,260,questionSet.getInd());
     rooms[i].trapdoor2 = new Trapdoor(650,260,questionSet.getInd());
   }
 }
 
 @Override
 public void keyPressed(KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_DOWN) {
   startMove(3);
  }
  if (e.getKeyCode() == KeyEvent.VK_UP) {
   startMove(1);
  }
  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
   startMove(4);
  }
  if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
   startMove(2);
  }
  if(e.getKeyCode()=='Q' && doorIsNear()!=-1&&keys>1)
  {
    keys=keys-2;
    setAsVis(doorIsNear());
    transitionRoom(doorIsNear());
  }
  
 }
 @Override
 public void activate() {
   turnCharacter(startDir);
  this.requestFocus();
 }

 /**This method is needed for moving the character properly.*/
 public boolean isFocusable() {
  return true;
 }
 @Override
  public void actionPerformed(ActionEvent evt) {

 }
 @Override
 public void keyReleased(KeyEvent arg0) {
 }
 
 @Override
 public void keyTyped(KeyEvent arg0) {

 }
 
 /**
  * This method is used to initialize the dungeon panel. 
  * It displays the questions, character, monsters, and triggers the animations.
  * <p>
  * <h3>Local Variables</h3>
  * <p>
  * <b>j</b> Used to position the hearts properly by multiplying y value of hearts by j.
  * <p>
  * */
 public void initDun() {
  monsterNumber = (int)(Math.random()*10)+1;
  mapPanel.setVisible(false);
  dunPanel.setVisible(true);
  
  hearts = new JLabel[3];
  heartsPanel = new JPanel();
  int j = 0;
  for (int i = 0;i<lives;i++){
   hearts[i]=new JLabel(new ImageIcon("../Resources/Images\\pixel_heart.png"));
   hearts[i].setBounds (0,50*j,50,47);
   heartsPanel.add(hearts[i]);
   j++;
  }
  heartsPanel.setBackground(new Color(21,21,21));
  heartsPanel.setBounds(10,10,50,160);
  heartsPanel.setVisible(true);
  dunPanel.add(heartsPanel);
  
  questionLabel = new JLabel(question.question);
  questionLabel.setFont(new Font("Papyrus", Font.PLAIN, 36));
  questionLabel.setBounds(120, 42, 800, 55);
  setText();
  questionLabel.setVisible(true);
  dunPanel.add(questionLabel);

  Font buttonFont = new Font("Papyrus", Font.PLAIN, 26);

  choice1 = new JButton(question.answers.get(0));
  choice1.setBackground(new Color(205, 161, 112));
  choice1.setBounds(25, 568, 226, 55);
  choice1.setFont(buttonFont);
  choice1.setVisible(true);

  choice2 = new JButton(question.answers.get(1));
  choice2.setBackground(new Color(205, 161, 112));
  choice2.setBounds(274, 568, 226, 55);
  choice2.setFont(buttonFont);
  choice2.setVisible(true);

  choice3 = new JButton(question.answers.get(2));
  choice3.setBackground(new Color(205, 161, 112));
  choice3.setBounds(523, 568, 226, 55);
  choice3.setFont(buttonFont);
  choice3.setVisible(true);

  choice4 = new JButton(question.answers.get(3));
  choice4.setBackground(new Color(205, 161, 112));
  choice4.setBounds(772, 568, 226, 55);
  choice4.setFont(buttonFont);
  choice4.setVisible(true);

  choice1.setEnabled(true);
  choice2.setEnabled(true);
  choice3.setEnabled(true);
  choice4.setEnabled(true);
  dunPanel.add(choice1);
  dunPanel.add(choice2);
  dunPanel.add(choice3);
  dunPanel.add(choice4);

  choice1.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    checkAnswer (0);
   }
  });
  choice2.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    checkAnswer (1);
   }
  });
  choice3.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    checkAnswer (2);   
   }
  });
  choice4.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    checkAnswer (3);   
   }
  });

  //Monster label created
  monsterLabel = new JLabel (new ImageIcon ("../Resources/monsters\\monster" +monsterNumber+ ".png"));
  monsterLabel.setBounds(700, 300, 200, 200);
  
  //Blue attack
  blueFlash = new Animation(this.app,3,null);
  blueFlash.setBounds(150,300,700,208);
  dunPanel.add(blueFlash);
  
  //Green Attack & monster run away, and key animation 
  greenFlash = new Animation(this.app,1,monsterLabel);
  greenFlash.setBounds(200,150,700,450);
  dunPanel.add(greenFlash);
  
  //Character animation
  character = new Animation(this.app,2,null);
  character.setBounds(200,300,150,208);
  dunPanel.add(character);
    character.startAnimation();
  //Adding monster label
  dunPanel.add(monsterLabel);   
  
  // background
  background = new JLabel(new ImageIcon("../Resources/Images\\pixel_dungeon.jpg"));
  background.setBounds(0, 0, 1024, 650);
  dunPanel.add(background); 
 }
 
 /**
  * Checks whether or not the player selects the button with the correction answer.
  * If they do, winAnimation is triggered and keys is incremented. 
  * Otherwise, the loseAnimation is triggered and player loses a life.
  * */
 private void checkAnswer(int playerAnswer){
   if (playerAnswer == question.correct){
   winAnimation();
   keys++;
   choice1.setEnabled(false);
   choice2.setEnabled(false);
   choice3.setEnabled(false);
   choice4.setEnabled(false);
   drawRoom(curRoomNum);
 }
  else {

   loseAnimation();
   lives--;
   app.lives--;
  }
 }

 /**
  * Triggers the win animation.
  * */
 private void winAnimation(){
  greenFlash.reset();
  greenFlash.startAnimation();
 
 }
 /**
  * Triggers the lose animation and also makes a heart disappear.
  * */
 private void loseAnimation(){
  blueFlash.reset();
  blueFlash.startAnimation();
  hearts[lives-1].setVisible(false);
 }
 /**
  * Used to delay for the end screen.
  * */
 private void gameOver(){
  try{
   Thread.sleep(1000);
  } catch (InterruptedException e) {
  }
 }

 /**
  * Sets the proper text for the questions and buttons.
  * <p>
  * <h3>Local Variables</h3>
  * <p>
  * <b>labelFont</b> Font for the questionLabel.
  * <p>
  * <b>labelText</b> The text for the question.
  * <p>
  * <b>stringWidth</b>Keeps track of the string width by using FontMetrics.
  * <p>
  * <b>componentWidth</b> Holds the compenent width for the questionLabel.
  * <p>
  * <b>widthRatio</b> The ratio for the component to the string. Used for increasing font size.
  * <p>
  * <b>newFontSize</b> The new font size found with the widthRatio.
  * <p>
  * <b>fontSizeToUse</b> If the font size is too big, then use original font, otherwise use it.
  * <p>
  * */
 private void setText() {
  Font labelFont = questionLabel.getFont();
  String labelText = questionLabel.getText();

  int stringWidth = questionLabel.getFontMetrics(labelFont).stringWidth(labelText);
  int componentWidth = questionLabel.getWidth();

  // Find out how much the font can grow in width.
  double widthRatio = (double) componentWidth / (double) stringWidth;

  int newFontSize = (int) (labelFont.getSize() * widthRatio);
  int componentHeight = questionLabel.getHeight();

  // Pick a new font size so it will not be larger than the height of
  // label.
  int fontSizeToUse = Math.min(newFontSize, componentHeight - 5);

  // Set the label's font size to the newly determined size.
  questionLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
 }
 
 /**
  * Used to reset amount of lives and greenflash.
  * */
 public void reset(){  
  //greenFlash.setVisible(false);
  for(int i = 0;i<lives;i++){
   hearts[i].setVisible(true);
  }  
  monsterLabel.setBounds(700, 300, 200, 200);
 }
 
}

