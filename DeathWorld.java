import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World for when player dies. Press enter (after text finishes fading in) to return to start screen.
 * 
 * @author Angela Wang 
 * @version 
 */
public class DeathWorld extends World
{
    private GreenfootImage bg;
    private GreenfootSound music;
    private int newTransparency;
    private boolean fadingIn;
    private SuperTextBox text, restartText;
    
    /**
     * Constructor for objects of class DeathWorld.
     * 
     */
    public DeathWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 
        bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);

        text = new SuperTextBox("GAME OVER", new Color(0, 0, 0, 0), Color.RED, new Font("Times New Roman", 60), true, 800, 0, new Color(0, 0, 0, 0));
        //text.getImage().setTransparency(0);
        //fadingIn = true;
        addObject(text, getWidth() / 2, getHeight() / 2);

        restartText = new SuperTextBox("Press enter to return to start", new Color(0, 0, 0, 0), Color.WHITE, new Font("Times New Roman", 28), true, 800, 0, new Color(0, 0, 0, 0));
        restartText.getImage().setTransparency(0);
        fadingIn = true;
        addObject(restartText, getWidth() / 2,((int) (getHeight() * 0.75)));
        
        MyWorld.GAME_MUSIC.stop(); //don't know why this isn't stopping!
        music = new GreenfootSound("death_music.mp3");
        music.playLoop();
    }
    
    /**
     * Play music when started
     */
    public void started(){
        music.playLoop();
    }
    
    /**
     * Pause music when world stopped
     */
    public void stopped(){
        music.pause();
    }

    /**
     * Fade restart text in and check for if enter has been pressed to set world to start
     */
    public void act(){
        if (fadingIn){
            fadeIn(restartText);
        } else {
            if (Greenfoot.isKeyDown("Enter")){
                music.stop();
                StartWorld.START_MUSIC.playLoop();
                Greenfoot.setWorld(new StartWorld());
            }
        }
    }

    /**
     * Fade in provided actor. Need new transparency int and fadingIn boolean. Not the best code but taken from gr11 culminating.
     * @param a     Actor to be faded in
     */
    private void fadeIn(Actor a){
        newTransparency = a.getImage().getTransparency() + 2;
        if (newTransparency > 255){
            newTransparency = 255;
            fadingIn = false;
        }

        a.getImage().setTransparency(newTransparency);
    }
}
