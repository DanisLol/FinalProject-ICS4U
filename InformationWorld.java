import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Sets up story for the game. Unfortunately >>>SKIP skips the entire intro and does not fast-forward a single line.
 * 
 * @author Angela Wang 
 * @version June 2025
 */
public class InformationWorld extends World
{
    private GreenfootImage bg;
    private String[] lines;
    private FadingText[] texts;
    private SuperTextBox skip;
    private int curIndex;
    private StringTokenizer line;
    private GreenfootSound music;

    /**
     * Constructor for objects of class InformationWorld.
     * 
     */
    public InformationWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 
        bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);

        music = new GreenfootSound("info_music.mp3");
        music.playLoop();

        lines = new String[]{
            "You wake up disoriented, blinking to let your eyes adjust to the light.",
            "Stone is cold against your back. You hold a weapon in your hand. \nYou look at it, confused; you have no memory of how it got there. \nWhere are you?",
            "In the distance, you hear footsteps. Sounds. Inhuman. Coming closer.",
            "Your questions will have to be answered later. Your first priority is to survive."
        };

        //make a new fading text box for every line of dialogue
        texts = new FadingText[lines.length];
        for (int i = 0; i < texts.length; i++){
            //change display time based on number of words (going off approx 2.5 words per second??
            line = new StringTokenizer(lines[i]);
            System.out.println(line.countTokens());
            texts[i] = new FadingText(lines[i], true, ((int) (line.countTokens() * 60 / 2.5 )));
        }

        curIndex = 0;
        addObject(texts[curIndex], getWidth() / 2, getHeight() / 2);
        
        skip = new SuperTextBox(">>>SKIP", new Color(0, 0, 0, 0), Color.WHITE, new Font("Times New Roman", 30), true, 800, 0, new Color(0, 0, 0, 0));
        addObject(skip, 945, 48);
    }

    /**
     * Play music on start
     */
    public void started(){
        music.playLoop();
    }

    /**
     * Stop music when execution is paused
     */
    public void stopped(){
        music.pause();
    }

    /**
     * Check for FadingText. If there is no FadingText, move on to the next one. If there are no more FadingTexts, move on to next world.
     */
    public void act(){
        ArrayList<FadingText> currentTexts = (ArrayList<FadingText>)getObjects(FadingText.class);
        if (currentTexts.size() == 0){
            curIndex++;
            if (curIndex == lines.length){
                music.stop();
                Greenfoot.setWorld(new SettingsWorld());
                return;
            }
            addObject(texts[curIndex], getWidth() / 2, getHeight() / 2);
        }
        
        if (Greenfoot.mouseClicked(skip)){
            music.stop();
            Greenfoot.setWorld(new SettingsWorld());
        }
    }

}
