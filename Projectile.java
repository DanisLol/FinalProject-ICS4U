import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    private int damage, speed;
    public Projectile(int d, int s){
        damage=d;
        speed=s;
        
        //image
    }
    public void act()
    {
        move(speed);
        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        Player target = (Player)getOneIntersectingObject(Player.class);
        if (target != null && target.getWorld() != null) {
            // some damage method here
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
        }
    }
}
