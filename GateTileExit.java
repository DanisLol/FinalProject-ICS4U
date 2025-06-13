import greenfoot.*;

public class GateTileExit extends Tile
{
    private boolean activated = false;

    public GateTileExit() {
        super("tile_gate0.png", 'q'); // initially open
        setIsPassable(true);
    }

    public void act() {
        super.act();
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
