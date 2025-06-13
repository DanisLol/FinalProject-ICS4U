import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


/**
 * Write a description of class MyWorld here.
 * 
 * @Daniel Wang 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{    
    private Random random = new Random();

    //private String[] testLayouts = {"uuuuuugggguuuuuuugggggggggggggguugwwwwggggffffguugwggggggggggfgugggggggbbgggggggggbbggbuubggbbggggbbggbuubggbbgggggggggbbgggggggugfggggggggggwguugffffggggwwwwguugggggggggggggguuuuuuugggguuuuuu", 
    //                                "uuuuuugggguuuuuuugggggggggggggguugbbbgguuggbbbguugbbbgguuggbbbguggggggggggggggggggwwwwwwggggggggggggggggwwwwwwggggggggggggggggggugbbbgguuggbbbguugbbbgguuggbbbguugggggggggggggguuuuuuugggguuuuuu",
    //                               "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuggggggbbggggggugggggggbbggggggggggbbbbbbbbbbggggggbbbbbbbbbbggggggggggbbggggggguggggggbbgggggguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
    //                                "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugguugggggguugguggguugggggguugggggggggggggggggggggggggggggggggggggguugggggguugggugguugggggguugguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
    //                                "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuuuubbbuugggggguggggggguugggggggggggggguugggggggggggggguugggggggggggggguugggggggugggggguubbbuuuuugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
    //                                "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugguuwwwwwwuugguggggfggggggfggggggggfggggggfggggggggfggggggfggggggggfggggggfggggugguuwwwwwwuugguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu", 
    //                                "uuuuuugggguuuuuuubbbbbggggbbbbbuubbbbbggggbbbbbuubbbbbggggbbbbbuggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggubbbbbggggbbbbbuubbbbbggggbbbbbuubbbbbggggbbbbbuuuuuuugggguuuuuu",
    //                                "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugggguuuuuugggguggggguggggggggggggggguggggggggggggggguuuugggggggggggguggggggggggugggguggggggggguugggguggggggggguugggggggggggggguuuuuuugggguuuuuu",
    //                                "uuuuuugggguuuuuuuwwwwwggggwwwwwuuwggggggggggggwuuwgggbbbbbbgggwugggggbbbbbbggggggggggbbuubbggggggggggbbuubbggggggggggbbbbbbggggguwgggbbbbbbgggwuuwggggggggggggwuuwwwwwggggwwwwwuuuuuuugggguuuuuu",
    //                                "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuggggbbggbbggggugggggbuggubgggggggggggguugggggggggggggguuggggggggggggbuggubggggguggggbbggbbgggguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu"};
    
    private String testLayout =
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuuuuuuuuuuuuuuuueeeeeeuuuuuuuuuuuuuuuueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggueeeeeeussssssssssssssueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggueeeeeeusggggggggggggsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeugggggggggggggguuuuuuuusguuwwwwwwuugsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggggggggggsggfggggggfggsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggggggggggsggfggggggfggsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggggggggggsggfggggggfggsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggggggggggsggfggggggfggsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeugggggggggggggguuuuuuuusguuwwwwwwuugsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggueeeeeeusggggggggggggsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuggggggggggggggueeeeeeussssssssssssssueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuuuuuuuuuuuuuuuueeeeeeuuuuuugggguuuuuueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuugggguuuuuueeeeeeuuuuuuuuuuuuuuuueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggggggggggggueeeeeeuggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeugwwwwggggffffgueeeeeeuggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeugwggggggggggfguuuuuuuuggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggggbbggggggggggggggggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeugbbggbuubggbbgggggggggggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeugbbggbuubggbbgggggggggggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggggbbggggggggggggggggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeugfggggggggggwguuuuuuuuggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeugffffggggwwwwgueeeeeeuggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuggggggggggggggueeeeeeuggggggggggggggueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuuuuuuuuuuuueeeeeeuuuuuuuuuuuuuuuueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";

    private int currentWave = 0;
    private Player player;
    
    
    
    
    
    //Timer:
    private int actNum;
    public static Counter counter = new Counter();
    SimpleTimer st = new SimpleTimer();
    Counter counter2 = new Counter();
    public final int GAME_LENGTH = 90; // the length of the game, in seconds.
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1, false); 
        player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);
        Board board = new Board(testLayout);//random.nextInt(testLayouts.length)
        addObject(board,0,0);
        board.display();
        
        
        
        
        Actor imgActor1 = new Actor() {};  // create a basic Actor
        imgActor1.setImage("heart.png"); // set the image (must be in the images folder)
        addObject(imgActor1, 40, 23);
        
        
        Actor imgActor2 = new Actor() {};  // create a basic Actor
        imgActor2.setImage("coin.png"); // set the image (must be in the images folder)
        addObject(imgActor2, 40, 45);
            
            
        //Timer:
        counter2.setValue(GAME_LENGTH);
        st.mark();
        counter2.setPrefix("Time Left: ");
        addObject(counter2, 928, 68);
        
        actNum = 0;
        
        setPaintOrder(SuperStatBar.class,Counter.class, Player.class);
    }

    public void act(){
         actNum++;
         if (actNum % 60 == 0) counter2.add(-1); // Decrement the counter by 1
    }
    

    public Player getPlayer() {
        return player;
    }
    
    //Checking if all enemies are eliminated
    //if all enemies are eleiminated, we can let the user go on to the next room in the dungeon
    public boolean allEnemiesGone(){
        boolean result = false;
        //if the number of enemies killed by player is equal to that wave of enemies number
        //set true that all enemies are killed
        //the number one here is just a placeholder. There will be a method that gets the number
        //of enemies that are in each wave 
        if(player.getKilled() == 1){
            result = true;
        }
        return result;
    }
    
    
    public void started(){
        
    }
    
    public void stopped(){
        
    }
    
    //If weapon is upgraded return true
    public boolean weaponUpgraded(){
        boolean result = false;
        
        return result;
    }
    
        
    
    
}
