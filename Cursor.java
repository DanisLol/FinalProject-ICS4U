import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Cursor is basically the mouse and is used for easier detection of the mouse over a Tile.
 * Not really helpful for much else.
 * 
 * @author Angela Wang
 * @version June 4 2025
 */
public class Cursor extends Actor
{
    private GreenfootImage image;
    private MouseInfo mouse;
    private int prevX, prevY, x, y;

    /**
     * Cursor constructor 
     * @param show      True to show green square where Cursor is, false otherwise
     */
    public Cursor(boolean show){
        image = new GreenfootImage(5, 5);
        image.setColor(Color.GREEN);
        if (show) image.fill();
        
        setImage(image);
    }

    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //get mouse information
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        //if mouse is in world bounds, set cursor to location
        if (mouse != null){
            setLocation(mouse.getX(), mouse.getY());
        }
    }
    /**
     * Returns ArrayList of Actors the cursor is hovering over. Code by Neelan Thurairajah
     * @return ArrayList<Actor> Hovered actors if available, null if not
     */
    public ArrayList<Actor> getHoveredActors() {
        return (ArrayList<Actor>) getObjectsAtOffset(0, 0, Actor.class);
    }
}
