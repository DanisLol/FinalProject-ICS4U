import greenfoot.*;
import java.util.List;

/**
 * Tile gate that is the entrance to a room, triggers spawning
 * 
 * @author Ethan Ren 
 * @version June 16, 2025
 */
public class GateTileEnter extends Tile {
    private boolean activated = false;
    private boolean wasPlayerOnTileLastFrame = false;

    public GateTileEnter() {
        super("tile_gate0.png", 'g'); // initially open/passable
        setIsPassable(true);
    }

    public void act() {
        super.act();
    
        if (activated){
            if (isInTopHalfOfBoard()){
                if (player.getKilled() == 8){
                    deactivate();
                }
            } else{
                if (player.getKilled() == 24){
                    deactivate();
                }
            }
        }
        /*List<Enemy> enemies = getWorld().getObjects(Enemy.class);
        if (enemies.size() == 0){
            deactivate();
        }*/
    
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
            if (!wasPlayerOnTileLastFrame) {
                wasPlayerOnTileLastFrame = true;
            }
        } else {
            if (wasPlayerOnTileLastFrame) {
                wasPlayerOnTileLastFrame = false;
    
                boolean topHalf = isInTopHalfOfBoard();
                activateGatesInHalf(topHalf);
            }
        }
        
        
    }

    /**
     * activates all gate tiles in its half of the board
     * @param topHalf   Boolean for if the tile is in the top half of the board
     */
    private void activateGatesInHalf(boolean topHalf) {
        MyWorld world = (MyWorld) getWorld();
        Board board = getBoard();
        int midRow = board.getTileRowCount() / 2;
    
        for (GateTileEnter gate : world.getObjects(GateTileEnter.class)) {
            int row = board.getTileRow(gate);
            if ((topHalf && row < midRow) || (!topHalf && row >= midRow)) {
                gate.activate();
            }
        }
    
        for (GateTileExit gate : world.getObjects(GateTileExit.class)) {
            int row = board.getTileRow(gate);
            if ((topHalf && row < midRow) || (!topHalf && row >= midRow)) {
                gate.activate();
            }
        }
    }


    private Board getBoard() {
        List<Board> boards = getWorld().getObjects(Board.class);
        return boards.isEmpty() ? null : boards.get(0);
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
    public boolean isActivated(){
        return activated;
    }
}
