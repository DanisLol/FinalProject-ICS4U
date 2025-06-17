import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpikeTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpikeTile extends Damager
{
    private GreenfootImage[] images;
    private int act, period, activationCountdown, activationTime = 45, spikeCooldown = 10, cooldown = 10;
    private boolean trapActive;

    public SpikeTile(){
        super("tile_spike0.png", 's');

        images = new GreenfootImage[2];
        images[0] = new GreenfootImage("tile_spike0.png");
        images[1] = new GreenfootImage("tile_spike1.png");

        act = 0;

        period = 60 * spikeCooldown;

        trapActive = false;

        activationCountdown = activationTime;
    }

    /**
     * Act - do whatever the SpikeTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();

        act++;

        //if (isActive){
        if (act % period == 0){
            trapActive = true;
            setImage(images[1]);
        }

        if (trapActive){
            //deal damage to intersecting hurtables
            checkHurtables();

            activationCountdown--;

            if (activationCountdown == 0){
                setImage(images[0]);
                countdown = activationTime;
                trapActive = false;
            }
        }
        //}
    }

    public void checkHurtables(){
        java.util.List<HurtableEntity> hurtables = getWorld().getObjects(HurtableEntity.class);
        if (hurtables.size() != 0){
            for (HurtableEntity h : hurtables){
                if (h.getCollider().getIntersectingTiles().contains(this)){
                    h.takeDamage(1);
                }
            }
        }

        countdown = cooldown;
    }
}
