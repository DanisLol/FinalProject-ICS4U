import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


public abstract class Enemy extends HurtableEntity
{
    protected Player play;
    protected boolean inRange;
    protected int cd = 0;
    protected int cooldown;
    private boolean isOld;
    
    public void act()
    {
        super.act();
        attacking();
    }
    
    protected void addedToWorld(World w){
        super.addedToWorld(w);
        if(!isOld) {
            List<Player> players = w.getObjects(Player.class);
            if (!players.isEmpty()) {
                play = players.get(0);
            }
            isOld = true;
        }
    }
    protected void attacking(){
        cd++;
        /*
        if(cd%cooldown==0){
            attack();
        }
        */
    }
    protected abstract void attack();
    
    protected int getHealth(){
        return this.health;
    }
}
