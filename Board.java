import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.List;

/**
 * 2D Array of Tiles
 * 
 * @author Angela Wang 
 * @version June 4 2025
 */
public class Board extends Actor
{
    private Tile[][] tiles;
    private int offset = Tile.SIZE / 2; //since images are center anchored

    private HashMap<String, Class> test = new HashMap<String, Class>(){{
        put("w", WaterTile.class);
        put("u", WallTile.class);
        put("f", FloorTile.class);
        put("s", SpikeTile.class);
        put("l", LavaTile.class);
        put("b", BarrelTile.class);
        put("e", BlankTile.class);
        put("g", GateTileEnter.class);
        put("q", GateTileExit.class);
        put("p", SpawnerTile.class);
    }};

    /**
     * Default Board constructor - creates room of blank tiles
     */
    public Board(){
        tiles = new Tile[38][74];

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                tiles[i][j] = new FloorTile();
            }
        }
    }

    /**
     * Board constructor with predetermined layout in the form of a String (each char
     * in the String represents the Tile type)
     * @param layout    String representation of Tile types
     */
    public Board(String layout){
        tiles = new Tile[38][74];

        int x = 0;
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                //System.out.println(layout.charAt(x));
                //tiles[i][j] = new Tile(layout.charAt(x));
                Object instance;
                try {
                    instance = test.get(String.valueOf(layout.charAt(x))).newInstance();
                    tiles[i][j] = (Tile) instance;
                    tiles[i][j].setType(layout.charAt(x));
                } catch (InstantiationException e) {
                    System.out.println("something went wrong with loading tiles.");
                } catch (IllegalAccessException e){
                    System.out.println("something went wrong with loading tiles.");
                }
                x++;
            }
        }
    }

    /**
     * Show Board (must be used in order for Board to show on screen because of Board's nature)
     */
    public void display() {
        int displayStartRow = 5;
        int displayStartCol = 7;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                int x = (j - displayStartCol) * Tile.SIZE + offset;
                int y = (i - displayStartRow) * Tile.SIZE + offset;
                getWorld().addObject(tiles[i][j], x, y);
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
    
    public int getTileRowCount(){
        return tiles.length;
    }
    
    public int getTileRow(Tile tile) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == tile) {
                    return i;
                }
            }
        }
        return -1; // not found
    }
    
    public void delete(){
        List<Tile> tiles = getWorld().getObjects(Tile.class);
        for (Tile t: tiles){
            getWorld().removeObject(t);
        }
    }
}
