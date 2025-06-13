import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ImageDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageDisplay extends Actor
{
    /**
     * Act - do whatever the ImageDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage image;
    private static final Color transparent = new Color(0,0,0,0);

    public ImageDisplay(int text, int size)
    {
        image = new GreenfootImage(Integer.toString(text), size, Color.BLACK, transparent);
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
