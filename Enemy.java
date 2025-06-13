import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @Daniel Wang 
 * @version (a version number or a date)
 */
public abstract class Enemy extends HurtableEntity
{
    protected Player play;
    protected boolean inRange;
    protected int cd = 0;
    protected int cooldown;
    
    
    
    private SuperStatBar healthStat;
    private int health;
    
    public Enemy(){
        healthStat = new SuperStatBar(50, health, this, 200, 15, Color.RED, Color.BLACK, false, Color.BLACK, 3);
        
    }
    
    public void chooseEnemy(){
        
    }
    
    public void act()
    {
        super.act();
        attacking();
    }
    protected void addedToWorld(World w){
        List<Player> players = w.getObjects(Player.class);
        if (!players.isEmpty()) {
            play = players.get(0);
        }
    }
    protected void attacking(){
        cd++;
        if(cd%cooldown==0){
            attack();
        }
    }
    protected abstract void attack();
    
    protected int getHealth(){
        return this.health;
    }
    
    public void getDamage(int damage){
        this.health -= damage;
    }
}
