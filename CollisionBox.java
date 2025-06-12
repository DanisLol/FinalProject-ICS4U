import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    
    public CollisionBox(int width, int height, Actor owner){
        this.owner = owner;
        image = new GreenfootImage(width, height);
        image.setColor(Color.RED);
        image.fill();
        setImage(image);
    }
    
    /**
     * Act - do whatever the CollisionBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(owner.getX(), owner.getY());
    }
    
    public boolean isClear(){
        Tile t = (Tile) getOneIntersectingObject(Tile.class);
        if (t != null){
            if (!t.getIsPassable()){
                return false;
            }
        }
        return true;
    }
}
