import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HurtableEntity here.
 * 
 * @author Zachary Zhao
 * @version 0.0.1
 */
public abstract class HurtableEntity extends Scroller
{
    protected int health;
    
    public abstract void takeDamage(int dmg);
    
    public abstract void die();
}
