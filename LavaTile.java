import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LavalTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LavaTile extends Damager
{
    public LavaTile(){
        super("tile_lava.png", 'l');
        sound = new GreenfootSound("lava.wav");
        sound.setVolume(80);
        cooldown = 30;
    }

    /**
     * Act - do whatever the LavalTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        //if (isActive) 
        checkHurtablesAfterCooldowns();
    }

    public void checkHurtables(){
        java.util.List<HurtableEntity> hurtables = getWorld().getObjects(HurtableEntity.class);
        if (hurtables.size() != 0){
            for (HurtableEntity h : hurtables){
                if (h.getCollider().getIntersectingTiles().contains(this)){
                    sound.play();
                    h.takeDamage(1);
                }
            }
        }
        
        countdown = cooldown;
    }
}
