import greenfoot.*;
import java.util.List;

public class GateTileEnter extends Tile {
    private boolean activated = false;
    private boolean wasPlayerOnTileLastFrame = false;

    public GateTileEnter() {
        super("tile_gate0.png", 'g'); // initially open/passable
        setIsPassable(true);
    }

    public void act() {
        super.act();

        if (activated) return;

        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
            if (!wasPlayerOnTileLastFrame) {
                wasPlayerOnTileLastFrame = true;
            }
        } else {
            if (wasPlayerOnTileLastFrame) {
                wasPlayerOnTileLastFrame = false;

                if (isInTopHalfOfBoard()) {
                    activateTopHalfGates();
                }
            }
        }
    }

    private boolean isInTopHalfOfBoard() {
        Board board = getBoard();
        if (board == null) return false;

        // Calculate tile row based on vertical position
        int tileRow = (getY() + Tile.SIZE / 2) / Tile.SIZE;

        return tileRow < board.getTileRowCount() / 2;
    }

    private void activateTopHalfGates() {
        MyWorld world = (MyWorld) getWorld();
        Board board = getBoard();
        if (board == null) return;
    
        int midRow = board.getTileRowCount() / 2;
    
        // Activate all GateTileEnter in top half
        for (GateTileEnter gate : world.getObjects(GateTileEnter.class)) {
            int gateRow = (gate.getY() + Tile.SIZE / 2) / Tile.SIZE;
            if (gateRow < midRow) {
                gate.activate();
            }
        }
    
        // Activate all GateTileExit in top half
        for (GateTileExit gate : world.getObjects(GateTileExit.class)) {
            int gateRow = (gate.getY() + Tile.SIZE / 2) / Tile.SIZE;
            if (gateRow < midRow) {
                gate.activate();
            }
        }
    }


    private Board getBoard() {
        List<Board> boards = getWorld().getObjects(Board.class);
        return boards.isEmpty() ? null : boards.get(0);
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
    
    public boolean isActivated(){
        return activated;
    }
}
