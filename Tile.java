import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tiles have different images and "passability" (whether or not user can move through).
 * Things to fix:
 * - Tile images
 * - Tile types (will probably need more)
 * - Set passability
 * 
 * @author Angela Wang
 * @version June 4 2025
 */
public class Tile extends Actor
{
    private GreenfootImage image;
    public static final int PIXEL_SIZE = 64;
    private char type;
    private boolean isPassable;
    private boolean isMouseHeld;

    public Tile(char type){
        this.type = type;

        String imageName = "";
        switch (type){
            case 'f':
                imageName = "tile_floor.png";
                break;
            case 'w':
                imageName = "tile_wall.png";
                break;
            case 'b':
                imageName = "tile_blank.png";
        }

        image = new GreenfootImage(imageName);
        image.scale(64, 64);
        setImage(image);
    }

    public void act(){
        //if this tile is in a RoomEditor (NOT in a game world), user can edit type
        if (getWorld() instanceof RoomEditor){
            RoomEditor room = (RoomEditor) getWorld();

            //if user is dragging mouse + touches this tile, set tile to new type based on RoomEditor            if (room.getIsMousePressed() && intersects(room.getCursor())){
            if (room.getIsMousePressed() && intersects(room.getCursor())){
                type = room.getNewType();
                if (type == 'f'){
                    setImage(new GreenfootImage("tile_floor.png"));
                } else if (type == 'w'){
                    setImage(new GreenfootImage("tile_wall.png"));
                }
            }
        }
    }

    /**
     * Return type of this tile 
     * @return char     e.g. f for floor, w for wall... (may need to change this)
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
}
