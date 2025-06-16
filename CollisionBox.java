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
    public static final boolean VISIBLE = false;
    private Actor owner;
    private GreenfootImage image;
    private int yOffset;

    public CollisionBox(int width, int height, int yOffset, Actor owner, boolean show){
        this.owner = owner;
        this.yOffset = yOffset;
        image = new GreenfootImage(width, height);
        if (VISIBLE || show) image.setColor(Color.RED); else image.setColor(new Color(0, 0, 0, 0));
        image.fill();
        setImage(image);
    }

    /**
     * Act - do whatever the CollisionBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (owner != null){
            setLocation(owner.getX(), owner.getY() + yOffset);
        } 
    }
    
    public List<Tile> getIntersectingTiles(){
        return getIntersectingObjects(Tile.class);        
    }
    
    public List<CollisionBox> getIntersectingCollisionBoxes(){
        return getIntersectingObjects(CollisionBox.class);
    }
    
    public List<Enemy> getIntersectingEnemies(){
        return getIntersectingObjects(Enemy.class);
    }
    
    public List<BarrelTile> getIntersectingBarrels(){
        return getIntersectingObjects(BarrelTile.class);
    }
}
