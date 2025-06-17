import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tiles have different images and "passability" (whether or not user can move through).
 * 
 * @author Angela Wang
 * @version June 4 2025
 */
public class Tile extends Scroller
{
    protected GreenfootImage image;
    protected GreenfootSound sound;
    public static final int SIZE = 64;
    private char type;
    protected boolean isPassable = true;
    private boolean isMouseHeld;
    protected boolean isActive = false;

    public Tile(String imageName, char type){
        image = new GreenfootImage(imageName);
        setImage(image);
        this.type = type;
        if (/*type == 'u' || */type == 'b'){
            isPassable = false;
        }
    }
    
    public Tile(char type){
        setType(type);  // use setType instead of manual code
    }

    public void act(){
        super.act();
        
        //checkActive();
        
        // if this tile is in a RoomEditor (NOT in a game world), user can edit type
        if (getWorld() instanceof RoomEditor){
            RoomEditor room = (RoomEditor) getWorld();

            // if user is dragging mouse + touches this tile, set tile to new type based on RoomEditor
            if (room.getIsMousePressed() && intersects(room.getCursor())){
                setType(room.getNewType());  // use setType() here too
            }
        }
    }

    /**
     * Return type of this tile 
     * @return char     e.g. f for floor, w for wall...
     */
    public char getType(){
        return type;
    }

    /**
     * Return whether or not characters can go through this tile
     * @return boolean      True if characters can pass through
     */
    public boolean getIsPassable(){
        return isPassable;
    }

    /**
     * Safely sets the type and updates the image together
     * @param newType    The new type of the tile
     */
    public void setType(char newType){
        this.type = newType;

        String imageName = "";
        switch (newType){
            case 'f':
                imageName = "tile_floor.png";
                break;
            case 'w':
                imageName = "tile_water.png";
                break;
            case 'u':
                imageName = "tile_wall.png";
                break;
            case 'b':
                imageName = "tile_barrel.png";
                break;
            case 'l':
                imageName = "tile_lava.png";
                break;
            case 's':
                imageName = "tile_spike0.png";
                break;
            case 'e':
                imageName = "tile_blank.png";
                break;
            case 'g':
                imageName = "tile_gate0.png";
                break;
            case 'q':
                imageName = "tile_gate0.png";
                break;
            case 'p':
                imageName = "tile_floor.png";
                break;
            case 't':
                imageName = "tile_portal.png";
                break;
            default:
                imageName = "tile_floor.png";
        }

        image = new GreenfootImage(imageName);
        setImage(image);
    }
    
    public void setIsPassable(boolean bool){
        isPassable = bool;
    }
    
    protected boolean isInTopHalfOfBoard() {
        MyWorld world = (MyWorld) getWorld();
        Board board = world.getObjects(Board.class).get(0);
    
        int row = board.getTileRow(this);
        return row != -1 && row < board.getTileRowCount() / 2;
    }
    
    public void checkActive(){
        java.util.List<Player> p = getObjectsInRange(1024, Player.class);
        if (p.size() != 0){
            isActive = true;
        } else {
            isActive = false;
        }
    }
}
