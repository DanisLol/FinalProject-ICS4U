import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeathWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathWorld extends World
{
    private GreenfootImage bg;
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
    }

    public void act(){
        if (fadingIn){
            fadeIn(restartText);
        } else {
            if (Greenfoot.isKeyDown("Enter")){
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
