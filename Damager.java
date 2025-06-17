import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Damager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Damager extends Tile
{
    protected int countdown, cooldown;
    
    public Damager(String imageName, char type){
        super(imageName, type);
        countdown = cooldown;
    }
    
    /**
     * Act - do whatever the Damager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public void checkHurtablesAfterCooldowns(){
        if (countdown > 0){
            countdown--;
        } else {
            checkHurtables();
        }
    }
    
    public abstract void checkHurtables();
}
