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
    private HurtableEntity e;
    public Projectile(HurtableEntity e, int d, int s){
        damage=d;
        speed=s;
        this.e=e;
        GreenfootImage img = new GreenfootImage("Gojo_pose.png");
        setImage(img);
        System.out.println("Projectile created " + damage + "  " + speed);
        //image
    }
    
    public void act()
    {
        move(speed);
        Tile tile = (Tile)getOneIntersectingObject(Tile.class);
        if (!tile.getIsPassable()){
            if(getWorld() != null){
                destroy();
            }
        }
        HurtableEntity victim = (HurtableEntity)getOneIntersectingObject(HurtableEntity.class);
        if (victim != null && victim.getWorld() != null && victim!=e) {
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
