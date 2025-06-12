import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coin extends Scroller
{
    private GreenfootImage[] images;
    private int countdown, frame;
    
    public Coin(){
        frame = 0;
        countdown = 6;
        addFrames();
    }
    
    private void addFrames(){
        images = new GreenfootImage[6];
        for (int i = 0; i < 6; i++){
            String fileName = "coin_" + i + ".png";
            images[i] = new GreenfootImage(fileName);
        }
        setImage(images[frame]);
    }
    
    /**
     * Act - do whatever the Coin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        animate();
        Player player = (Player)getOneIntersectingObject(Player.class);
        if (player != null && player.getWorld() != null) {
            player.pickUpCoin();
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
        }
    }
    
    private void animate(){
        if (countdown > 0){
            countdown--;
        } else {
            frame++;
            if (frame > 5){
                frame = 0;
            }
            setImage(images[frame]);
            countdown = 6;
        }
    }
}
