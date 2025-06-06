import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


public abstract class Enemy extends Actor
{
    protected Player play;
    protected boolean inRange;
    protected int cd = 0;
    protected int cooldown;
    public void act()
    {
        attacking();
    }
    protected void addedToWorld(World world){
        List<Player> players = world.getObjects(Player.class);
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
}
