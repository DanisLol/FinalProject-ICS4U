import greenfoot.*;
import java.util.Random;

public class SpawnerTile extends Tile {
    private boolean spawned = false;
    Random random = new Random();

    public SpawnerTile() {
        super("tile_floor.png", 'p');
    }

    public void act() {
        super.act();
        if (spawned) return;

        if (topHalfGateActivated()) {
            spawnEnemies(5);
            spawned = true;
        }
        
    }

    private boolean topHalfGateActivated() {
        MyWorld world = (MyWorld) getWorld();
        Board board = world.getObjects(Board.class).get(0);
        int midRow = board.getTileRowCount() / 2;

        for (GateTileEnter gate : world.getObjects(GateTileEnter.class)) {
            int gateRow = (gate.getY() + Tile.SIZE / 2) / Tile.SIZE;
            if (gateRow < midRow && gate.isActivated()) {
                return true;
            }
        }

        for (GateTileExit gate : world.getObjects(GateTileExit.class)) {
            int gateRow = (gate.getY() + Tile.SIZE / 2) / Tile.SIZE;
            if (gateRow < midRow && gate.isActivated()) {
                return true;
            }
        }

        return false;
    }

    private void spawnEnemies(int count) {
        World world = getWorld();
        double centerX = getRealX(); // realX of the spawner
        double centerY = getRealY();
        System.out.println("Spawning enemies at: (" + centerX + ", " + centerY + ")");

        for (int i = 0; i < count; i++) {
            int dx = Greenfoot.getRandomNumber(257) - 128; // -128 to +128
            int dy = Greenfoot.getRandomNumber(257) - 128;

            Enemy enemy = Greenfoot.getRandomNumber(2) == 0 ? new Melee() : new Ranged();
            world.addObject(enemy, (int)this.getRealX() + random.nextInt(257) - 128, (int)this.getRealY() + random.nextInt(257)-128); // placeholder on-screen position
            enemy.updateLocation();      // applies real position visually
        }
    }
}
