import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Tile of water that slows characters
 * 
 * @author Ethan Ren, Angela Wang 
 * @version June 16, 2025
 */
public class WaterTile extends Damager
{
    public WaterTile(){
        super("tile_water.png", 'w');
        sound = new GreenfootSound("water.wav");
        sound.setVolume(95);
    }

    //change to slow player and enemy down
    /**
     * Act - do whatever the WaterTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        //checkHurtablesAfterCooldowns();
    }
    
    /**
     * Changes HurtableEntity speed when it is on water tile
     */
    public void checkHurtables(){
        List<HurtableEntity> entities = getWorld().getObjects(HurtableEntity.class);
        for (HurtableEntity e: entities){
            if (this.intersects(e.getCollider())){
                e.alterSpeed(0.4);
                sound.play();
            } else {
                e.resetSpeed();
            }
        }
    }
}
