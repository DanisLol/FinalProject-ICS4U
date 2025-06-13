import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BarrelTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BarrelTile extends Tile
{
    private int health;
    private static final int MAX_HEALTH = 50;

    public BarrelTile(){
        super("tile_barrel.png", 'b');
        health = MAX_HEALTH;
        
        isPassable = false;
    }

    /**
     * Act - do whatever the BarrelTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        //just to test
        if (Greenfoot.mouseClicked(this)){
            health = 0;
        }

        if (health <= 0){
            rupture();
        }
    }

    private void rupture(){
        FloorTile floor = new FloorTile();
        Coin coin = new Coin();
                        
        getWorld().addObject(floor, ((int) (this.realX + 0.5)), ((int) (this.realY + 0.5)));
        floor.updateLocation();
        
        //drop coin
        getWorld().addObject(coin, ((int) (this.realX + 0.5)), ((int) (this.realY + 0.5)));
        coin.updateLocation();
        
        getWorld().removeObject(this);
        //?? 
    }
}
