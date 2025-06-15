import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LavalTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LavaTile extends Tile
{
    //deals damage

    public LavaTile(){
        super("tile_lava.png", 'l');
    }

    /**
     * Act - do whatever the LavalTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        java.util.List<HurtableEntity> hurtables = getWorld().getObjects(HurtableEntity.class);
        if (hurtables.size() != 0){
            for (HurtableEntity h : hurtables){
                if (h.getCollider().getIntersectingTiles().contains(this)){
                    h.takeDamage(1);
                }
            }
        }

    }
}
