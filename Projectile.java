import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The Projectile class is a projectile object that is fired from a source entity.
 * It moves in a straight line at a given angle and speed, dealing damage to entities it collides with.
 * This class extends `Scroller` to account for world scrolling and implements projectile behavior,
 * including collision detection with non-passable tiles and other hurtable entities.
 * When a projectile hits a tile or entity, it will either destroy itself or deal damage to the entity.
 * <p>
 * The projectile plays a sound on creation and displays an image.
 * </p>
 * @author Zachary Zhao & Ricky Zhu
 * @version June 2025
 */
public class Projectile extends Scroller
{
    private int damage, speed;
    private GreenfootSound sound;
    private HurtableEntity e;
    private double moveX,moveY;
    private double angle;
    
     /**
     * Constructs a new Projectile object.
     * 
     * @param e The entity that the projectile is fired from. Used to avoid self harm.
     * @param d The damage dealt by the projectile.
     * @param s The speed at which the projectile moves.
     * @param a The angle at which the projectile moves.
     */
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
    /**
     * Called when the projectile is added to the world. This method updates its real world coordinates.
     * 
     * @param w The world the projectile is added to.
     */
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        realX = getX()+Scroller.camX;
        realY = getY()+Scroller.camY;
    }
    
    /**
     * The act method
     * It moves the projectile according to its speed and angle, checks for collisions with nonpassable tiles and
     * hurtable entities, and handles destruction of the projectile upon collision.
     */
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
    
    /**
     * kaboom
     */
    private void destroy() {
        // play an animation before destroying
        getWorld().removeObject(this);
        return;
    }
}
