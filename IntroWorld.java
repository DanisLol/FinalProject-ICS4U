import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private GreenfootImage bg;
    private String[] dialogue;
    private Actor player, guide;
    private Textbox[] texts;
    private int curIndex;
    private int duration;
    private SuperTextBox skip;
    private int actNum;
    private int playerX, guideX, speakerY;
    

    /**
     * Constructor for objects of class IntroWorld.
     * 
     */
    public IntroWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 

        bg = new GreenfootImage("intro_bg2.jpg");
        bg.scale(1100, 768);
        setBackground(bg);

        player = new Actor(){};
        player.setImage("Benjamin_pose.png");
        player.getImage().scale(80, 120);
        
        guide = new Actor(){};
        guide.setImage("Benjamin_pose.png");
        guide.getImage().scale(80, 120);
        guide.getImage().setTransparency(0); //hide whoever isn't talking

        dialogue = new String[]{
            "What... Where am I? The last thing I remember is being in school... Why is everything so dark?",
            "Welcome to the waking world. You are now in the dungeon.",
            "The dungeon? But... what? How do I get out?",
            "You'll have to fight your way through.",
            "Fight? But I can't fight!",
            "You can now. Once you enter, click your mouse to attack and use WASD to move.",
            "I don't understand. What is this?",
            "You'll see soon enough. Good luck, and may you survive!",
            "Wait–!"
        };

        texts = new Textbox[dialogue.length];
        for (int i = 0; i < dialogue.length; i++){
            if (i % 2 == 0){
                texts[i] = new Textbox(dialogue[i], player);
            } else {
                texts[i] = new Textbox(dialogue[i], guide);
            }
        }
        
        actNum = 1; //?? to avoid skipping first text box
        
        curIndex = 0;
        
        playerX = 100;
        guideX = 924;
        speakerY = 700;
        addObject(texts[curIndex], 512, 685);
        addObject(player, playerX, speakerY);
        addObject(guide, guideX, speakerY);
        
        duration = 200;
        
        skip = new SuperTextBox(">>>SKIP", new Color(0, 0, 0, 0), Color.WHITE, new Font("Times New Roman", 30), true, 800, 0, new Color(0, 0, 0, 0));
        addObject(skip, 945, 48);
        
        setPaintOrder(Actor.class, Textbox.class);
    }
    
    public void act(){
        //every duration interval, update TextBox with new line

        if (actNum % duration == 0){            
            curIndex++;
            //if curIndex exceeds highest index, go to SettingsWorldGeneral

            if (curIndex == dialogue.length){
                //music.stop();
                Greenfoot.setWorld(new SettingsWorld());

                //don't know why but setting the world wasn't immediately going to SettingsWorldGeneral + was causing errors
                return;
            }

            //new TextBox for next line in dialogue array
            //SuperTextBox text = new SuperTextBox(dialogue[curIndex], Color.BLACK, Color.WHITE, new Font(20), true, 1024, 3, Color.YELLOW);
            //addObject(text, 512, 717);

            if (curIndex % 2 == 0){
                //player speaking
                player.getImage().setTransparency(255);
                guide.getImage().setTransparency(0);
            } else {
                player.getImage().setTransparency(0);
                guide.getImage().setTransparency(255);
            }
            removeObject(texts[curIndex - 1]);

            addObject(texts[curIndex], 512, 685);

            //prevent speaker from moving too far if text animation ends at a different y than start
            player.setLocation(playerX, speakerY);
            guide.setLocation(guideX, speakerY);
        }

        actNum++;
        
        if (Greenfoot.mouseClicked(skip)){
            //music.stop();
            Greenfoot.setWorld(new SettingsWorld());
        }
    }
}

