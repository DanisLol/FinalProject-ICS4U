import greenfoot.*;
import java.util.List;

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
        if (activated){
            // if (isInTopHalfOfBoard()){
                // if (player.getKilled() == 8){
                    // deactivate();
                // }
            // } else{
                // if (player.getKilled() == 24){
                    // deactivate();
                // }
            // }
            
            //desperate attempt to reduce lag
            if (actNum % 30 == 0){
                allDead = true;
                
                List<Enemy> enemies = getWorld().getObjects(Enemy.class);
                for (Enemy e : enemies){
                    if (!e.isDead()){
                        allDead = false;
                    }
                }
                
                if (allDead) deactivate();
            }
        }
    }

    public void activate() {
        if (activated) return;
        activated = true;
        setIsPassable(false);
        setImage(new GreenfootImage("tile_gate1.png"));
    }

    public void deactivate() {
        activated = false;
        setIsPassable(true);
        setImage(new GreenfootImage("tile_gate0.png"));
    }

    public boolean isActivated() {
        return activated;
    }
}
