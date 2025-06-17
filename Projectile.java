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
        sound = new GreenfootSound("arrow.wav");
        sound.play();
    }
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        // Since arrows are projectiles that are also affected by scrolling, need to use world coordinates over screen coordinates
        // to get account for camera movement
        // The problem is that Scroller's addedToWorld() will set realX and realY to screen coordinates, essentially teleporting
        // arrows to realX/realY bounded by the 1024x768 starting room instead
        // Using the reference to the entity that spawned this projectile it can use the correct world coordinates
        realX = e.getRealX();
        realY = e.getRealY();
    }
    public void act()
    {
        super.act();
        
        moving();
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
    
    private void moving(){
        setRealLocation(getRealX() + moveX, getRealY() + moveY);
        System.out.println(realX + ", " + realY);
        //setLocation(getX() + moveX, getY() + moveY);
        /*int moveXInput = 0;
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
        setLocation(getX() + moveXInput, getY() + moveYInput);*/
    }
    
    private void destroy() {
        // play an animation before destroying
        getWorld().removeObject(this);
        return;
    }
}
