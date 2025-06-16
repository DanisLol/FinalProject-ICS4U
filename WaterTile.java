import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class WaterTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterTile extends Tile
{
    public WaterTile(){
        super("tile_water.png", 'w');
        //sound = new GreenfootSound("water.wav");
        //sound.setVolume(10);
    }

    //change to slow player and enemy down
    /**
     * Act - do whatever the WaterTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();

        List<Player> p = getWorld().getObjects(Player.class);
        
        if (this.intersects(p.get(0).getCollider())){
            p.get(0).setSpeedPercents(0.2, 0.2);
            //sound.play();
        } else {
            p.get(0).setSpeedPercents(1, 1);
        }
    }
}
