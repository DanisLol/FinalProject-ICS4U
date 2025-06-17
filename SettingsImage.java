import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Displays the images needed for the settings world. Has flexibility in the specific image that the user wants and the size of the image (length,
 * width)
 * 
 * @author Stephanie Xia
 * @version June 2025
 */
public class SettingsImage extends Actor
{
    private GreenfootImage image; 
    //transparent color
    private static final Color transparent = new Color(0,0,0,0);

    /**
     * setter method - display the image of the name of file param passed in, fixed dimensions 120x190 
     * 
     * @param imageFile     name of a image file 
     * @return     void
     */
    public void setImageFile(String imageFile)
    {
        image = new GreenfootImage(imageFile); 
        //set size 120x190
        image.scale(120, 190); 
        setImage(image); 
    }

    /**
     * setter method - display the image of the name of file param passed in, unfixed dimensions x by y
     * 
     * @param imageFile    name of a image file 
     * @param x        width of image
     * @param y        length of image
     * @return void
     */
    public void setImageFile(String imageFile, int x, int y)
    {
        image = new GreenfootImage(imageFile); 
        //unset sized with width x and length y
        image.scale(x, y); 
        setImage(image); 
    }
}