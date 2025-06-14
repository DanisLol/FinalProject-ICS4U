import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tool to help with designing room layouts. Draw out room layout and press 'e' to save (*changed!)- 
 * String representation of layout will be printed to terminal so you can copy and paste.
 * 
 * @author Angela Wang
 * @version June 4 2025
 */
public class RoomEditor extends World
{
    private Board room; 
    private char newType;
    
    private boolean isMousePressed;
    private Cursor mouse;
        
    /**
     * Constructor for objects of class RoomEditor.
     * 
     */
    public RoomEditor()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1, false); 
        
        room = new Board();
        addObject(room, 0, 0);
        room.display();
        
        mouse = new Cursor(false);
        addObject(mouse, 0, 0);
    }
    //test
    public void act(){
        //press key to set tile type/save
        if (Greenfoot.isKeyDown("g")){
            newType = 'g';
        } else if (Greenfoot.isKeyDown("q")){
            newType = 'q';
        } else if (Greenfoot.isKeyDown("w")){
            newType = 'w';
        } else if (Greenfoot.isKeyDown("u")){
            newType = 'u';
        } else if (Greenfoot.isKeyDown("b")){
            newType = 'b';
        } else if (Greenfoot.isKeyDown("f")){
            newType = 'f';
        } else if (Greenfoot.isKeyDown("s")){
            newType = 's';
        } else if (Greenfoot.isKeyDown("e")){
            newType = 'e';
        } else if (Greenfoot.isKeyDown("Enter")){
            System.out.println("Saved.");
            System.out.println(room.getLayout());
        }
        
        //if user presses mouse, start drag
        if (Greenfoot.mousePressed(null)){
            isMousePressed = true;
        } else if (Greenfoot.mouseClicked(null)){
            //if user lets go of mouse, end drag
            isMousePressed = false;
        }
        
    }
    
    /**
     * Returns the type of Tile user is drawing
     * @return char    
     */
    public char getNewType(){
        return newType;
    }
    
    /**
     * Returns true if mouse is being dragged
     * @return boolean 
     */
    public boolean getIsMousePressed(){
        return isMousePressed;
    }
    
    /**
     * Returns the world's Cursor so Tiles know if they are being moved over by the mouse
     * @return Cursor
     */
    public Cursor getCursor(){
        return mouse;
    }
}
