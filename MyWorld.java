import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @Daniel Wang, Ethan Ren, Zachary Zhao? Angela Wang
 * @version June 2025
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

    //world layout, represented as characters
    private String testLayout =
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuuuuuuuuuuuuuuuueeeeeeuuuuuuuuuuuuuuuueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuffffffffffffffueeeeeeussssssssssssssueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuffffffffffffffueeeeeeusffffffffffffsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuffffffffffffffuuuuuuuusfuuwwwwwwuufsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeufffffffffffffffffffffgsfflfffffflffsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeufffffffffffffffffffffgsfflfffpfflffsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeufffffffffffffffffffffgsfflffpffflffsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeufffffffffffffffffffffgsfflfffffflffsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuffffffffffffffuuuuuuuusfuuwwwwwwuufsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuffffffffffffffueeeeeeusffffffffffffsueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuffffffffffffffueeeeeeussssssssssssssueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeuuuuuuuuuuuuuuuueeeeeeuuuuuuqqqquuuuuueeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuugggguuuuuueeeeeeuuuuuuuuuuuuuuuueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffffffffffffueeeeeeuffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeufwwwwffffllllfueeeeeeuffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeufwfpffffffpflfuuuuuuuuffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffffbbffffffqfffffffffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeufbbffbuubffbbfqfffffffffffffttffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeufbbffbuubffbbfqfffffffffffffttffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffffbbffffffqfffffffffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuflfpffffffpfwfuuuuuuuuffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeufllllffffwwwwfueeeeeeuffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuffffffffffffffueeeeeeuffffffffffffffueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuuuuuuuuuuuueeeeeeuuuuuuuuuuuuuuuueeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";

    private int currentWave = 0;
    private Player player;

    //Stat bar:
    private SuperStatBar healthStat; 
    //coin counter
    private Counter coinStat;
    private int health;
    private GreenfootImage healthImage;
    private GreenfootImage coinImage;

    //Timer:
    int actNum;
    public static Counter counter = new Counter();
    SimpleTimer st = new SimpleTimer();
    Counter counter2 = new Counter();
    public final int GAME_LENGTH = 90; // the length of the game, in seconds.
    
    private int level;
    private Board board;
    
    public static final GreenfootSound GAME_MUSIC = new GreenfootSound("game_music.mp3");
    //music plays through BossWorld so sorry 

    private UserInfo user; 
    
    /**
     * MyWorld constructor 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1, false); 
        player = new Player();
        //player.setCoin(0);
        addObject(player, getWidth()/2, getHeight()/2);
        board = new Board(testLayout);//random.nextInt(testLayouts.length)
        addObject(board,0,0);
        board.display();

        //Health SuperStatBar:
        healthImage = new GreenfootImage("heart.png");
        healthStat = new SuperStatBar(player.getHealth(), player.getHealth(), player, 200, 15, Color.RED, Color.BLACK, false, Color.BLACK, 3);
        addObject(healthStat, 160, 23);

        Actor imgActor1 = new Actor() {};  // create a basic Actor
        imgActor1.setImage("heart.png"); // set the image (must be in the images folder)
        addObject(imgActor1, 40, 23);

        //Coin counter:
        coinStat =  new Counter(); 
        coinStat.setValue(player.getCoin());
        addObject(coinStat, 120, 45);

        Actor imgActor2 = new Actor() {};  // create a basic Actor
        imgActor2.setImage("coin.png"); // set the image (must be in the images folder)
        addObject(imgActor2, 40, 45);

        //Timer:
        counter2.setValue(0);
        st.mark();
        counter2.setPrefix("Time Taken: ");
        addObject(counter2, 928, 68);

        actNum = 0;

        GAME_MUSIC.setVolume(50);
        GAME_MUSIC.playLoop();
        
        level = 0;
        
        setPaintOrder(Fader.class, SuperStatBar.class,Counter.class, CollisionBox.class, Projectile.class, HurtableEntity.class, Coin.class, Actor.class, Tile.class, Board.class);
        prepare();
    }
    
    /**
     * Increase timer every act
     */
    public void act(){
        actNum++;
        if (actNum % 60 == 0) counter2.add(1); // Increment the counter by 1
    }

    /**
     * Get the player 
     * @return Player   player in MyWorld
     */
    public Player getPlayer() {
        return player;
    }

    //Checking if all enemies are eliminated
    //if all enemies are eleiminated, we can let the user go on to the next room in the dungeon
    //NOT USED?
    public boolean allEnemiesGone(){
        boolean result = true;
        //if the number of enemies killed by player is equal to that wave of enemies number
        //set true that all enemies are killed
        //the number one here is just a placeholder. There will be a method that gets the number
        //of enemies that are in each wave 
        // if(player.getKilled() == 1){
            // result = true;
        // }
        // return result;
        
        ArrayList<Enemy> enemies = (ArrayList<Enemy>)getObjects(Enemy.class);
        for (Enemy e : enemies){
            if (!e.isDead()){
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Play music when execution started
     */
    public void started(){
        GAME_MUSIC.playLoop();
    }

    /**
     * Pause music when execution stopped
     */
    public void stopped(){
        GAME_MUSIC.pause();
    }
        
    
    /*public void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; i++ ) {
            // spawn an enemy on a real tile, if not a real tile, try again
            while(true) {
                int coordX = Greenfoot.getRandomNumber(1000);
                int coordY = Greenfoot.getRandomNumber(800);

                System.out.println(coordX + ", " + coordY);

                List<Tile> tiles = getObjectsAt(coordX, coordY, Tile.class);
                if(!tiles.isEmpty()) {
                    // additional check to see if the tile is passible
                    // placed here to prevent out of scope exception
                    // if(!tiles.get(0).getIsPassable()) {
                    // continue;
                    // }
                    if(Greenfoot.getRandomNumber(2) == 0) {
                        addObject(new Melee(), coordX, coordY);
                    } else {
                        addObject(new Ranged(), coordX, coordY);
                    }
                    break;
                }
            }
        }
    }*/
    
    /**
     * Prevent user from passing through gates
     */
    public void activateGate() {
        List<GateTileEnter> gates = getObjects(GateTileEnter.class);
        for (GateTileEnter gate : gates) {
            gate.activate();
        }
    }
    
    /**
     * Allow player to pass through
     */
    public void deactivateGate() {
        List<GateTileEnter> gates = getObjects(GateTileEnter.class);
        for (GateTileEnter gate : gates) {
            gate.deactivate();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    public void prepare()
    {
    }
    
    /**
     * Poorly named but transitions to BossWorld
     */
    public void increaseLevel(){
        //was originally going to have multiple levels but ran out of time.
        //level++; 
        //System.out.println("NEXT LEVEL");
        board.delete();
        //board = new Board(testLayout2);
        //addObject(board, 0, 0);
        //board.display();
        //GAME_MUSIC.stop();
        Greenfoot.setWorld(new BossWorld());
        
        //PlayerSpawnTile startTile = getObjects(PlayerSpawnTile.class).get(0);
        //player.setRealXY(startTile.getX(), startTile.getY());
        //player.setRealXY(getWidth() / 2, getHeight() / 2);
    }
    
    /**
     * Return player health statbar
     * @return SuperStatBar Player healthbar
     */
    public SuperStatBar getHealthStat(){
        return healthStat;
    }
    
    /**
     * Return player coin counter
     * @return Counter      coin counter
     */
    public Counter getCoinCounter(){
        return coinStat;
    }
}
