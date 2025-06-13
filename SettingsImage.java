import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SettingsImage extends Actor
{
    /**
     * Act - do whatever the SettingsImage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage image; 
    //transparent color
    private static final Color transparent = new Color(0,0,0,0);
    public void act()
    {
        // Add your action code here.
    }

    public void setImageFile(String imageFile)
    {
        image = new GreenfootImage(imageFile); 
        image.scale(120, 190); 
        setImage(image); 
    }
}
