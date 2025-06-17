import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Write a description of class BossWorld here.
 * 
 * @Angela Wang, Stephanie Xia
 * @June 2025
 */
public class BossWorld extends World
{
    private Board board;
    private int actNum;

    //player and boss + statbars
    private Player player;
    private SuperStatBar playerHealthBar, bossHealthBar;
    private Enemy boss;

    public static final int MAX_BOSS_HEALTH = 1200;
    private int bossHealth = MAX_BOSS_HEALTH;

    //"dialogue"
    private FadingText[] texts;
    private int curIndex;
    private String[] lines;

    private boolean keyWasDown;

    private int countDown, maxCountDown; 

    private int tempHealth; 
    
    private ImageDisplay potionDisplay;

    //timer
    private Counter timer;
    private SimpleTimer st = new SimpleTimer();
    private String worldLayout = 
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuuuuuuuuuuuuuuuueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuffffffffffffffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuffuuffffffuuffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuffuuffffffuuffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeufflfffffffflffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeufflfffffffflffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeufflfffffffflffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeufflfffffffflffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuffuuffffffuuffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuffuuffffffuuffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuffffffffffffffueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeuuuuuuuuuuuuuuuueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";

    /**
     * Constructor for objects of class BossWorld.
     * 
     */
    public BossWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1, false); 

        player = new Player();
        addObject(player, getWidth() / 2, getHeight() / 2);

        board = new Board(worldLayout);
        addObject(board, 0, 0);
        board.display();

        playerHealthBar = new SuperStatBar(player.getHealth(), player.getHealth(), player, 200, 15, Color.RED, Color.BLACK, false, Color.BLACK, 3);
        addObject(playerHealthBar, 160, 23);
        showText("YOU", 160, 23);

        boss = new Cohen();
        addObject(boss, getWidth() / 2 + 100, getHeight() / 2);
        bossHealthBar = new SuperStatBar(MAX_BOSS_HEALTH, bossHealth, boss, 200, 15, Color.GREEN, Color.BLACK, false, Color.BLACK, 3);
        addObject(bossHealthBar, 160, 45);
        showText("MR. COHEN", 160, 50);
        
        timer = new Counter();
        timer.setValue(0);
        st.mark();
        timer.setPrefix("Time Taken: ");
        addObject(timer, 928, 68);

        //dialogue
        lines = new String[]{
            "You stand face to face with... Mr. Cohen???",
            "He has locked you in the dungeon until you finish your ICS4U culminating.",
            "Defeat him to escape!! to breathe!! to touch grass!!"
        };
        curIndex = 0;

        texts = new FadingText[lines.length];
        for (int i = 0; i < texts.length; i++){
            StringTokenizer line = new StringTokenizer(lines[i]);
            System.out.println(line.countTokens());
            texts[i] = new FadingText(lines[i], true, ((int) (line.countTokens() * 60 / 2.5 )));
        }        
        addObject(texts[curIndex], getWidth() / 2, ((int) (getHeight() * 0.85)));

        setPaintOrder(SuperStatBar.class, Actor.class, Player.class, HurtableEntity.class, Tile.class, Board.class);

        showText("Click p to activate your invincibility potion", 320, 100);
        showText("Potion left: " + Integer.toString(SettingsWorld.getUserInfoInt(4)), 160, 130);

        countDown = 500;
        maxCountDown = 500; 
        
        potionDisplay = new ImageDisplay("Potion in use", 22, Color.WHITE); 
    }

    public void started(){
        MyWorld.GAME_MUSIC.playLoop();
    }

    public void stopped(){
        MyWorld.GAME_MUSIC.pause();
    }

    public void act(){
        System.out.println(player.getHealth()); 
        showText("Potion left: " + Integer.toString(SettingsWorld.getUserInfoInt(4)), 160, 130);

        if (curIndex < lines.length){
            ArrayList<FadingText> currentTexts = (ArrayList<FadingText>)getObjects(FadingText.class);
            if (currentTexts.size() == 0){
                curIndex++;
                if (curIndex < lines.length) addObject(texts[curIndex], getWidth() / 2, ((int) (getHeight() * 0.85)));
            }
        }

        actNum++;
        if (actNum % 60 == 0) {
            timer.add(1); // Increment the counter by 1

            //check if boss is dead
            List<Enemy> enemies = getObjects(Enemy.class);
            if (enemies.size() == 0){
                //MyWorld.GAME_MUSIC.stop();
                //Greenfoot.setWorld(new EndWorld());
                addObject(new Fader("in", new EndWorld()), getWidth() / 2, getHeight() / 2);
            }
        }
        usePotion();
    }

    public void usePotion()
    {
        if(Greenfoot.isKeyDown("p") && SettingsWorld.getUserInfoInt(4)>=1)
        {
            addObject(potionDisplay, 900, 100); 
            keyWasDown = true;
            int x = SettingsWorld.getUserInfoInt(4) -1; 
            SettingsWorld.setUserInfoInt(4, x); 
            tempHealth = player.getHealth();
        }
        if(keyWasDown)
        {
            player.setHealth(tempHealth); 
            countDown--; 
        }
        if(countDown==0)
        {
            removeObject(potionDisplay); 
            keyWasDown = false;
            countDown = maxCountDown; 
        }

    }

    public void updatePlayerHealthBar(int health){
        playerHealthBar.update(health);
    }

    public void updateBossHealthBar(int health){
        bossHealthBar.update(health);
    }
}
