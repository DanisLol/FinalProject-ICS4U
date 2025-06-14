import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class CollisionBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CollisionBox extends Scroller
{
    public static final boolean VISIBLE = true;
    private Actor owner;
    private GreenfootImage image;
    private int yOffset;

    public CollisionBox(int width, int height, int yOffset, Actor owner){
        this.owner = owner;
        this.yOffset = yOffset;
        image = new GreenfootImage(width, height);
        if (VISIBLE) image.setColor(Color.RED); else image.setColor(new Color(0, 0, 0, 0));
        image.fill();
        setImage(image);
    }

    /**
     * Act - do whatever the CollisionBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(owner.getX(), owner.getY() + yOffset);
    }

    // public boolean isClear(){
        // Tile t = (Tile) getOneIntersectingObject(Tile.class);
        // if (t != null){
            // if (!t.getIsPassable()){
                // return false;
            // }
        // }
        // return true;
    // }
    
    public List<Tile> getIntersectingTiles(){
        return getIntersectingObjects(Tile.class);        
    }
}
