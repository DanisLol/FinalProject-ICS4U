import greenfoot.*;
import java.util.Random;

/**
 * Tile that spawns a set number of enemies in a radius around itself
 * 
 * @author Ethan Ren 
 * @version June 16, 2025
 */
public class SpawnerTile extends Tile {
    private boolean spawned = false;
    Random random = new Random();
    private boolean topHalf;

    public SpawnerTile() {
        super("tile_floor.png", 'p');
    }

    public void act() {
        super.act();
        if (spawned) return;
    
        if (isInTopHalfOfBoard() && allTopHalfGatesActivated()) {
            spawnEnemies(4);
            spawned = true;
        } else{
            if (!isInTopHalfOfBoard() && allBottomHalfGatesActivated()){
                spawnEnemies(4);
                spawned = true;
            }
        }
        /*if (allBottomHalfGatesActivated() && !isInTopHalfOfBoard()) {
                spawnEnemies(4);
                spawned = true;
        }*/
    }

    /**
     * Checks if a top half gate is active
     * @return boolean  True if a top half gate is active
     */
    private boolean topHalfGateActivated() {
        MyWorld world = (MyWorld) getWorld();
        Board board = world.getObjects(Board.class).get(0);
         int midRow = board.getTileRowCount() / 2;

        for (GateTileEnter gate : world.getObjects(GateTileEnter.class)) {
            int gateRow = (gate.getY() + Tile.SIZE / 2) / Tile.SIZE;
            if (gateRow < midRow && !gate.isActivated()) {
                return false;
            }
        }

        for (GateTileExit gate : world.getObjects(GateTileExit.class)) {
            int gateRow = (gate.getY() + Tile.SIZE / 2) / Tile.SIZE;
            if (gateRow < midRow && !gate.isActivated()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if all top half gates are active
     * @return boolean  True if all top half gates are active
     */
    private boolean allTopHalfGatesActivated() {
        MyWorld world = (MyWorld) getWorld();
        Board board = world.getObjects(Board.class).get(0);
        int midRow = board.getTileRowCount() / 2;
    
        for (GateTileEnter gate : world.getObjects(GateTileEnter.class)) {
            if (board.getTileRow(gate) < midRow && !gate.isActivated()) {
                return false;
            }
        }
    
        for (GateTileExit gate : world.getObjects(GateTileExit.class)) {
            if (board.getTileRow(gate) < midRow && !gate.isActivated()) {
                return false;
            }
        }
    
        return true;
    }

    /**
     * Checks if all bottom half gates are active
     * @return boolean  True if all bottom half gates are active
     */
    private boolean allBottomHalfGatesActivated() {
        MyWorld world = (MyWorld) getWorld();
        Board board = world.getObjects(Board.class).get(0);
        int midRow = board.getTileRowCount() / 2;
    
        for (GateTileEnter gate : world.getObjects(GateTileEnter.class)) {
            if (board.getTileRow(gate) >= midRow && !gate.isActivated()) return false;
        }
    
        for (GateTileExit gate : world.getObjects(GateTileExit.class)) {
            if (board.getTileRow(gate) >= midRow && !gate.isActivated()) return false;
        }
    
        return true;
    }

    /**
     * spawns # of enemies in a 2-tile radius around itself
     * @param count  number of enemies spawned by this tile
     */
    private void spawnEnemies(int count) {
        World world = getWorld();
        double centerX = getRealX(); // realX of the spawner
        double centerY = getRealY();
        //System.out.println("Spawning enemies at: (" + centerX + ", " + centerY + ")");

        for (int i = 0; i < count; i++) {
            int dx = Greenfoot.getRandomNumber(257) - 128; // -128 to +128
            int dy = Greenfoot.getRandomNumber(257) - 128;

            Enemy enemy = Greenfoot.getRandomNumber(2) == 0 ? new Melee() : new Ranged();
            world.addObject(enemy, (int)this.getRealX() + random.nextInt(257) - 128, (int)this.getRealY() + random.nextInt(257)-128); // placeholder on-screen position
            enemy.updateLocation();      // applies real position visually
        }
    }
}
