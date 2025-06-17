import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ImageDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageDisplay extends Actor
{
    private GreenfootImage image;
    private static final Color transparent = new Color(0,0,0,0);
    
    /**
     * Description for constructor
     * @param text  desc parameters
     * @param size
     */
    public ImageDisplay(int text, int size)
    {
        image = new GreenfootImage(Integer.toString(text), size, Color.BLACK, transparent);
        setImage(image);
    }
    
    public ImageDisplay(String text, int size, Color color)
    {
        image = new GreenfootImage(text, size, color, transparent);
        setImage(image);
    }

    public ImageDisplay(String text, int size)
    {
        image = new GreenfootImage(text, size, Color.BLACK, transparent);
        setImage(image);
    }

    public ImageDisplay(String imageName)
    {
        image = new GreenfootImage(imageName);
        image.scale(100,100);
        setImage(image); 
    }

    public ImageDisplay(String imageName, int x, int y)
    {
        image = new GreenfootImage(imageName);
        image.scale(x,y);
        setImage(image); 
    }
}
