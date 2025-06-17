import greenfoot.*;
import java.util.List;
/**
 * Tile gate that is the exit to a room
 * 
 * @author Ethan Ren 
 * @version June 16, 2025
 */
public class GateTileExit extends Tile {

    private boolean activated = false, allDead = false;
    private int actNum;

    public GateTileExit() {
        super("tile_gate0.png", 'q');
        setIsPassable(true);
        actNum = 0;
    }

    public void act() {
        super.act(); 

        actNum++;
        /*if (activated){
            if (isInTopHalfOfBoard()){
                if (player.getKilled() == 8){
                    deactivate();
                }
            } else{
                if (player.getKilled() == 24){
                    deactivate();
                }
            }
        }*/
        List<Enemy> enemies = getWorld().getObjects(Enemy.class);
        if (enemies.size() == 0) deactivate();
    }

    /**
     * activates this tile. Turns it into unpassable, changes image
     */
    public void activate() {
        if (activated) return;
        activated = true;
        setIsPassable(false);
        setImage(new GreenfootImage("tile_gate1.png"));
    }

    /**
     * deactivates this tile. Turns it into passable, changes image
     */
    public void deactivate() {
        activated = false;
        setIsPassable(true);
        setImage(new GreenfootImage("tile_gate0.png"));
    }

    /**
     * returns if this gate is activated
     * @return activated    boolean if gate has been activated
     */
    public boolean isActivated() {
        return activated;
    }
}
