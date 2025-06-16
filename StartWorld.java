import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartWorld extends World
{
    private GifImage background;
    private SuperTextBox title;
    private int newTransparency;
    private boolean fadingIn;
    private Button startButton;
    private Cursor cursor;
    public static final GreenfootSound START_MUSIC = new GreenfootSound("start_music.mp3");
    private Fader fader;

    /**
     * Constructor for objects of class StartWorld.
     * 
     */
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 
        background = new GifImage("start_bg.gif");
        background.getCurrentImage().scale(1100, 900);
        setBackground(background.getCurrentImage());

        title = new SuperTextBox("Escaping ICS4U", new Color(0, 0, 0, 0), Color.WHITE, new Font("Times New Roman", 60), true, 800, 0, new Color(0, 0, 0, 0));
        title.getImage().setTransparency(0);
        fadingIn = true;
        addObject(title, getWidth() / 2, getHeight() / 2);

        cursor = new Cursor(false);
        addObject(cursor, 0, 0); 

        startButton = new Button(cursor, "start_button");
        addObject(startButton, getWidth() / 2, 580);
        
        fader = new Fader("out", null);
        addObject(fader, getWidth() / 2, getHeight() / 2);
    }

    public void started(){
        START_MUSIC.playLoop();
    }
    
    public void stopped(){
        START_MUSIC.pause();
    }
    
    public void act(){
        animate();

        if (fadingIn){
            fadeIn(title);
        }
        
        //um so this is not registered if fader has not finished
        if (Greenfoot.mousePressed(startButton)){
            START_MUSIC.stop();
            GreenfootSound sound = new GreenfootSound("click.wav");
            sound.play();
            Greenfoot.setWorld(new InformationWorld());
        }
    }

    private void fadeIn(Actor a){
        newTransparency = a.getImage().getTransparency() + 10;
        if (newTransparency > 255){
            newTransparency = 255;
            fadingIn = false;
        }

        a.getImage().setTransparency(newTransparency);
    }

    private void animate(){
        background.getCurrentImage().scale(1100, 900);
        setBackground(background.getCurrentImage());
    }
}
