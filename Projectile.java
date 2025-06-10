import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author Zachary Zhao & unknown
 * @version 0.0.2
 */
public class Projectile extends SuperSmoothMover
{
    private int damage, speed;
    public Projectile(int x, int y, int d, int s){
        damage=d;
        speed=s;
        turnTowards(x, y);
        //image
    }
    
    public Projectile(Actor target, int d,  int s) {
        damage=d;
        speed=s;
        turnTowards(target);
    }
    
    public void act()
    {
        move(speed);
        Tile tile = (Tile)getOneIntersectingObject(Tile.class);
        if (!tile.getIsPassable()){
            if(getWorld() != null){
                destroy();
            }

        HurtableEntity victim = (HurtableEntity)getOneIntersectingObject(HurtableEntity.class);
        if (victim != null && victim.getWorld() != null) {
            victim.takeDamage(damage);
            if (getWorld() != null) {
                destroy();
            }
        }
    }
    
    private void destroy() {
        // play an animation before destroying
        getWorld().removeObject(this);
    }
}
