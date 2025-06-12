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
    public static final int SIZE = 64;
    private char type;
    private boolean isPassable;
    private boolean isMouseHeld;

    public Tile(String imageName, char type){
        image = new GreenfootImage(imageName);
        setImage(image);
        this.type = type;
    }
    
    //omds i need to change sm
    public Tile(char type){
        setType(type);  // use setType instead of manual code
    }

    public void act(){
        super.act();
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
            case 'g':
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
            case 'f':
                imageName = "tile_lava.png";
                break;
            case 's':
                imageName = "tile_spike0.png";
            default:
                imageName = "tile_floor.png";  

        }

        image = new GreenfootImage(imageName);
        setImage(image);
    }
}
