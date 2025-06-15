import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A abstract class comprised of all of the entities that can be hurt
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
