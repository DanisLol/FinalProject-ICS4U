import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Projectile here.
 * 
 * @author Zachary Zhao & unknown
 * @version 0.0.2
 */
public class Projectile extends Scroller
{
    private int damage, speed;
    private GreenfootSound sound;
    private HurtableEntity e;
    private double moveX,moveY;
    private double angle;
    public Projectile(HurtableEntity e, int d, int s, double a){
        damage=d;
        speed=s;
        this.e=e;
        angle =a ;
        GreenfootImage img = new GreenfootImage("projectile.png");
        setImage(img);
        sound = new GreenfootSound("arrow.wav");
        sound.play();
        moveX = speed*Math.cos(Math.toRadians(angle));
        moveY = speed*Math.sin(Math.toRadians(angle));

    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        realX = getX()+Scroller.camX;
        realY = getY()+Scroller.camY;
    }

    public void act()
    {
        realX += moveX;
        realY += moveY;
        super.act();
        Tile tile = (Tile)getOneIntersectingObject(Tile.class);
        if (tile != null && !tile.getIsPassable()){
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
    
    
    private void destroy() {
        // play an animation before destroying
        getWorld().removeObject(this);
        return;
    }
}
