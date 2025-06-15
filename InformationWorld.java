import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Write a description of class InformationWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InformationWorld extends World
{
    private GreenfootImage bg;
    private String[] lines;
    private FadingText[] texts;
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
        
        texts = new FadingText[lines.length];
        for (int i = 0; i < texts.length; i++){
            line = new StringTokenizer(lines[i]);
            System.out.println(line.countTokens());
            texts[i] = new FadingText(lines[i], true, ((int) (line.countTokens() * 60 / 2.5 )));
        }
        
        curIndex = 0;
        addObject(texts[curIndex], getWidth() / 2, getHeight() / 2);
    }
    
    public void started(){
        music.playLoop();
    }
    
    public void stopped(){
        music.pause();
    }
    
    public void act(){
        ArrayList<FadingText> currentTexts = (ArrayList<FadingText>)getObjects(FadingText.class);
        if (currentTexts.size() == 0){
            curIndex++;
            if (curIndex == lines.length){
                music.stop();
                Greenfoot.setWorld(new MyWorld());
                return;
            }
            addObject(texts[curIndex], getWidth() / 2, getHeight() / 2);
        }
    }
}
