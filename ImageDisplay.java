import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *ImageDisplay - used to display images, lines of texts and integers. Variety of versions to display text occurding to what is needed, allowing flexibility.
 *Flexibility with color, text size, font, text message and image size.
 *
 * @author Stephanie Xia
 * @version June 2025
 */
public class ImageDisplay extends Actor
{
    private GreenfootImage image;
    private static final Color transparent = new Color(0,0,0,0);

    /**
     * Displays a single integer in the font sized passed in, color black, transparent background.  
     * @param text     the integer that is being displayed
     * @param size     the font size of the integer
     */
    public ImageDisplay(int text, int size)
    {
        image = new GreenfootImage(Integer.toString(text), size, Color.BLACK, transparent);
        setImage(image);
    }

    /**
     * Displays a string (line of text) in the font sized passed in, with the color passed in, transparent background.  
     * @param text      the string that is being displayed
     * @param size      the font size of the string
     * @param color     the color of the string
     */

    public ImageDisplay(String text, int size, Color color)
    {
        image = new GreenfootImage(text, size, color, transparent);
        setImage(image);
    }

    /**
     * Displays a string (line of text) in the font sized passed in, color black, transparent background.  
     * @param text      the string that is being displayed
     * @param size      the font size of the string
     */

    public ImageDisplay(String text, int size)
    {
        image = new GreenfootImage(text, size, Color.BLACK, transparent);
        setImage(image);
    }

    /**
     * Displays an image of the name of the image file passed in through the parameter, image size 100x100
     * @param imageName      the name of the image file that is being set as the image displayed
     */
    public ImageDisplay(String imageName)
    {
        image = new GreenfootImage(imageName);
        image.scale(100,100); //sets the image size the permanent 100 x 100
        setImage(image); 
    }

    /**
     * Displays an image of the name of the image file passed in through the parameter, image size indicated by parameters width: x, length: y
     * @param imageName     the name of the image file that is being set as the image displayed
     * @param x        the width of the image
     * @param y        the length of the image
     */

    public ImageDisplay(String imageName, int x, int y)
    {
        image = new GreenfootImage(imageName);
        image.scale(x,y); // allows for flexibility in setting images -- different images have different porportional sizes
        setImage(image); 
    }
}
