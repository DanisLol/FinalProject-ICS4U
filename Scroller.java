import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class that contains all of the objects that have to move according to the player
 * for a camera following effect.
 * 
 * @author Zachary Zhao
 * @version 0.0.2
 */
public class Scroller extends SuperSmoothMover
{
    private boolean inRange;
    private Player player;
    private MyWorld w;
    private boolean isNew;

    protected static double camX = 0; // the "camera's" position
    protected static double camY = 0;

    public Scroller() {
        isNew = true;
    }

    @Override
    protected void addedToWorld(World w) {
        if(isNew) {
            //w = (MyWorld)getWorld();
            //player = w.getPlayer();
            realX = getX();
            realY = getY();

            isNew = false;
        }
    }

    public void act()
    {
        updateLocation();
    }

    public void centreOn(SuperSmoothMover target) { // this is essentially static, only being called ONCE per act (not instance) on the player.
        if(target != null) {
            camX = target.realX - (getWorld().getWidth() / 2);
            camY = target.realY - (getWorld().getHeight() / 2);
        }
    }

    public void updateLocation(){
        setLocation(realX - camX, realY - camY);
    }
    
    //NOT WORKING - keeps enemies from overlapping each other/player but causes weird dispersion when player tries to move through enemies
    // public void updateLocation(CollisionBox collider, double dx, double dy){
        // int oldX = getX();
        // int oldY = getY();
        // double oldRealX = realX;
        // double oldRealY = realY;
    
        // // Try X movement
        // realX += dx;
        // collider.setLocation(oldX + dx - camX, oldY + 16 - camY);
    
        // //List<Tile> touchingX = getIntersectingObjects(Tile.class);
        // java.util.List<CollisionBox> touchingX = collider.getIntersectingCollisionBoxes();
        
        // // for (Tile tile : touchingX) {
            // // if (!tile.getIsPassable()) {
                // // realX = oldRealX;
                // // collider.setLocation(oldX - camX, oldY + 16 - camY);
                // // break;
            // // }
        // // }
        // if (touchingX.size() != 0) realX = oldX; collider.setLocation(oldX - camX, oldY + 16 - camY); 
    
        // // Try Y movement
        // oldX = getX();  
        // oldY = getY();
        // oldRealX = realX;
        // oldRealY = realY;
    
        // realY += dy;
        // collider.setLocation(oldX, oldY + dy + 16);
    
        // //List<Tile> touchingY = getIntersectingObjects(Tile.class);
        // java.util.List<CollisionBox> touchingY = collider.getIntersectingCollisionBoxes();
        
        // // for (Tile tile : touchingY) {
            // // if (!tile.getIsPassable()) {
                // // realY = oldRealY;
                // // collider.setLocation(oldX - camX, oldY + 16 - camY);
                // // break;
            // // }
        // // }
        // if (touchingY.size() != 0) realY = oldY; collider.setLocation(oldX - camX, oldY + 16 - camY);
        
    // }
}

