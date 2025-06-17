import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndWorld extends World
{
    private GreenfootImage bg;
    private SuperTextBox text, text2, restartText;
    private int newTransparency;
    private GreenfootSound music;
    private boolean fadingIn;

    /**
     * Constructor for objects of class EndWorld.
     * 
     */
    public EndWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 

        bg = new GreenfootImage("sky.jpg");
        bg.scale(1024, 768);
        setBackground(bg);
        
        text = new SuperTextBox("YOU WIN", new Color(0, 0, 0, 0), Color.GREEN, new Font("Times New Roman", 60), true, 800, 0, new Color(0, 0, 0, 0));
        addObject(text, getWidth() / 2, getHeight() / 2);
        text = new SuperTextBox("You escape the dungeon (ICS4U). Nature looks so beautiful.", new Color(0, 0, 0, 0), Color.GREEN, new Font("Times New Roman", 20), true, 800, 0, new Color(0, 0, 0, 0));
        addObject(text, getWidth() / 2, getHeight() / 2 + 80);

        restartText = new SuperTextBox("Press enter to return to start", new Color(0, 0, 0, 0), Color.WHITE, new Font("Times New Roman", 20), true, 800, 0, new Color(0, 0, 0, 0));
        restartText.getImage().setTransparency(0);
        fadingIn = true;
        addObject(restartText, getWidth() / 2,((int) (getHeight() * 0.75)));
        
        music = new GreenfootSound("end_music.mp3");
        music.playLoop();
    }

    public void started(){
        music.playLoop();
    }
    
    public void stopped(){
        music.pause();
    }
    
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

    private void fadeIn(Actor a){
        newTransparency = a.getImage().getTransparency() + 2;
        if (newTransparency > 255){
            newTransparency = 255;
            fadingIn = false;
        }

        a.getImage().setTransparency(newTransparency);
    }
}
