import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 2D Array of Tiles
 * 
 * @author Angela Wang 
 * @version June 4 2025
 */
public class Board extends Actor
{
    private Tile[][] tiles;
    private int offset = Tile.PIXEL_SIZE / 2; //since images are center anchored

    /**
     * Default Board constructor - creates room of blank tiles
     * Currently 12 by 16 but may change depending on world/tile size
     */
    public Board(){
        tiles = new Tile[12][16];
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                tiles[i][j] = new Tile('u');
            }
        }
    }

    /**
     * Board constructor with predetermined layout in the form of a String (each char
     * in the String represents the Tile type)
     * @param layout    String representation of Tile types
     */
    public Board(String layout){
        tiles = new Tile[12][16];
        int x = 0;
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                //System.out.println(layout.charAt(x));
                tiles[i][j] = new Tile(layout.charAt(x));
                x++;
            }
        }
    }

    /**
     * Show Board (must be used in order for Board to show on screen because of Board's nature)
     */
    public void display(){
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                getWorld().addObject(tiles[i][j], j * Tile.PIXEL_SIZE + offset, i * Tile.PIXEL_SIZE + offset);
            }
        }
    }

    /**
     * Returns layout of the Board as a String
     * @return String   String wherein chars represent Tile type
     */
    public String getLayout(){
        String layout = "";
        for (int i = 0 ; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                layout += tiles[i][j].getType();
            }
        }

        return layout;
    }
}
