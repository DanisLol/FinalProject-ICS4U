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
    private String[] testLayouts = {"uuuuuugggguuuuuuugggggggggggggguugwwwwggggffffguugwggggggggggfgugggggggbbgggggggggbbggbuubggbbggggbbggbuubggbbgggggggggbbgggggggugfggggggggggwguugffffggggwwwwguugggggggggggggguuuuuuugggguuuuuu", 
                                    "uuuuuugggguuuuuuugggggggggggggguugbbbgguuggbbbguugbbbgguuggbbbguggggggggggggggggggwwwwwwggggggggggggggggwwwwwwggggggggggggggggggugbbbgguuggbbbguugbbbgguuggbbbguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuggggggbbggggggugggggggbbggggggggggbbbbbbbbbbggggggbbbbbbbbbbggggggggggbbggggggguggggggbbgggggguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugguugggggguugguggguugggggguugggggggggggggggggggggggggggggggggggggguugggggguugggugguugggggguugguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuuuubbbuugggggguggggggguugggggggggggggguugggggggggggggguugggggggggggggguugggggggugggggguubbbuuuuugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugguuwwwwwwuugguggggfggggggfggggggggfggggggfggggggggfggggggfggggggggfggggggfggggugguuwwwwwwuugguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu", 
                                    "uuuuuugggguuuuuuubbbbbggggbbbbbuubbbbbggggbbbbbuubbbbbggggbbbbbuggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggubbbbbggggbbbbbuubbbbbggggbbbbbuubbbbbggggbbbbbuuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugggguuuuuugggguggggguggggggggggggggguggggggggggggggguuuugggggggggggguggggggggggugggguggggggggguugggguggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuuwwwwwggggwwwwwuuwggggggggggggwuuwgggbbbbbbgggwugggggbbbbbbggggggggggbbuubbggggggggggbbuubbggggggggggbbbbbbggggguwgggbbbbbbgggwuuwggggggggggggwuuwwwwwggggwwwwwuuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuggggbbggbbggggugggggbuggubgggggggggggguugggggggggggggguuggggggggggggbuggubggggguggggbbggbbgggguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu"};
    private int currentWave = 0;
    private Player player;
    
    //Stat bar:
    private SuperStatBar healthStat; 
    private SuperStatBar coinStat;
    private int health;
    private GreenfootImage healthImage;
    private GreenfootImage coinImage;
    
    //Timer:
    int actNum;
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
        Board board = new Board(testLayouts[1]);//random.nextInt(testLayouts.length)
        addObject(board, 0, 0);
        board.display();
        
        
        //Health SuperStatBar:
        healthImage = new GreenfootImage("heart.png");
        healthStat = new SuperStatBar(50, 50, player, 200, 15, Color.RED, Color.BLACK, false, Color.BLACK, 3);
        addObject(healthStat, 160, 23);
        
        Actor imgActor1 = new Actor() {};  // create a basic Actor
        imgActor1.setImage("heart.png"); // set the image (must be in the images folder)
        addObject(imgActor1, 40, 23);
        
        //Coin SuperStatBar:
        coinStat =  new SuperStatBar(7, 0, player, 200, 15, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);       
        addObject(coinStat, 160, 45);
        
        Actor imgActor2 = new Actor() {};  // create a basic Actor
        imgActor2.setImage("coin.png"); // set the image (must be in the images folder)
        addObject(imgActor2, 40, 45);
            
            
        //Timer:
        counter2.setValue(GAME_LENGTH);
        st.mark();
        counter2.setPrefix("Time Left: ");
        addObject(counter2, 928, 68);
        
        actNum = 0;
        
        setPaintOrder(SuperStatBar.class,Counter.class, HurtableEntity.class);
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
        spawnEnemies(10);
    }
    
    public void stopped(){
        
    }
    
    public void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; i++ ) {
            // spawn an enemy on a real tile, if not a real tile, try again
            while(true) {
                int coordX = Greenfoot.getRandomNumber(1000);
                int coordY = Greenfoot.getRandomNumber(800);
                
                System.out.println(coordX + ", " + coordY);
                
                List<Tile> tiles = getObjectsAt(coordX, coordY, Tile.class);
                if(!tiles.isEmpty()) {
                    if(Greenfoot.getRandomNumber(2) == 0) {
                        addObject(new Melee(), coordX, coordY);
                    } else {
                        addObject(new Ranged(), coordX, coordY);
                    }
                    break;
                }
            }
        }
    }
}
