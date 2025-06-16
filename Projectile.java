import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
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
    private int moveX,moveY;
    private double angle;
    public Projectile(HurtableEntity e, int d, int s, double a){
        damage=d;
        speed=s;
        this.e=e;
        angle =a ;
        GreenfootImage img = new GreenfootImage("projectile.png");
        setImage(img);
        moveX = (int) (speed * Math.cos(Math.toRadians(angle)));
        moveY = (int) (speed * Math.sin(Math.toRadians(angle)));
    }
    
    public void act()
    {
        moving();
        Tile tile = (Tile)getOneIntersectingObject(Tile.class);
        if (!tile.getIsPassable()){
            if(getWorld() != null){
                if(this!=null){
                    destroy();
                    return;
                }else{
                    return;
                }
            }
        }
        HurtableEntity victim = (HurtableEntity)getOneIntersectingObject(HurtableEntity.class);
        if (victim != null && victim.getWorld() != null && victim!=e) {
            victim.takeDamage(damage);
            if (getWorld() != null) {
                if(this!=null){
                    destroy();
                }else{
                    return;
                }
            }
        }
    }
    
    private void moving(){
        setLocation(getX() + moveX, getY() + moveY);
        int moveXInput = 0;
        int moveYInput = 0;

        if(Greenfoot.isKeyDown("a")){
            moveXInput = 10;
        }
        if(Greenfoot.isKeyDown("d")){
            moveXInput = -10;
        }
        if(Greenfoot.isKeyDown("w")){
            moveYInput = 10;
        }
        if(Greenfoot.isKeyDown("s")){
            moveYInput = -10;
        }
        setLocation(getX() + moveXInput, getY() + moveYInput);
    }
    
    private void destroy() {
        // play an animation before destroying
        getWorld().removeObject(this);
        return;
    }
}
