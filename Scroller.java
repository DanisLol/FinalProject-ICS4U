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
    protected Player player;
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
            w = getWorld();
            player = w.getObjects(Player.class).get(0);
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
    
    public double getRealX(){
        return realX;
    }
    
    public double getRealY(){
        return realY;
    }
    
    public void setRealLocation(double x, double y) {
        this.realX = x;
        this.realY = y;
    }
}

