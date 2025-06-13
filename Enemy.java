import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


public abstract class Enemy extends HurtableEntity
{
    protected Player play;
    protected boolean inRange;
    protected int cd = 0;
    protected int cooldown; 
    protected int direction;
    int health;
    
    public Enemy(String sheetName, int largeSize){
        super(sheetName, largeSize);
    }
    
    public void act()
    {
        super.act();
        //attacking();
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
}
