import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cursor is basically the mouse and is used for easier detection of the mouse over a Tile.
 * Not really helpful for much else.
 * 
 * @author Angela Wang
 * @version June 4 2025
 */
public class Cursor extends Actor
{
    private GreenfootImage image;
    private MouseInfo mouse;
    private int prevX, prevY, x, y;

    /**
     * Cursor constructor 
     * @param show      True to show red square where Cursor is, false otherwise
     */
    public Cursor(boolean show){
        image = new GreenfootImage(10, 10);
        if (show == true){
            image.setColor(Color.RED);
        } else {
            image.setColor(new Color(0, 0, 0, 0)); //transparent
        }
        image.fill();
        setImage(image);

        prevX = 0;
        prevY = 0;
    }

    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //get mouse information
        mouse = Greenfoot.getMouseInfo();
        //if mouse is within World boundaries
        if (mouse != null){
            //set x to mouse x if mouse is within World x
            if (mouse.getX() > 0 || mouse.getX() < getWorld().getWidth()){
                x = mouse.getX();
            } else {
                x = prevX;
            }
            
            //set y to mouse y if mouse is within World y
            if (mouse.getY() > 0 || mouse.getY() < getWorld().getHeight()){
                y = mouse.getY();
            } else {
                y = prevY;
            }
        }
        
        //set cursor to follow mouse position (mouse's last position if mouse is outside)
        setLocation(x, y);
        prevX = x;
        prevY = y;
    }
}
