import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Scroller
{
    private GreenfootImage image;
    private Animation walkAnimation, deathAnimation;
    private int countdown, direction, frame;
    private int xSpeed, ySpeed;
    private boolean isNew;
    
    private int coins;
    //the number of enemies killed
    private int killed;

    public Player (){
        GreenfootImage spritesheet = new GreenfootImage("testplayer.png");
        walkAnimation = AnimationManager.createAnimation(spritesheet, 9, 4, 9, 64, 64);
        deathAnimation = AnimationManager.createAnimation(spritesheet, 20, 1, 6, 64, 64);
        direction = 3;        
        image = walkAnimation.getOneImage(Direction.fromInteger(direction), 0); 
        setImage(image);
        frame = 0;
        xSpeed = 0;
        ySpeed = 0;
        realX = 0;
        realY = 0;
        isNew = true;
        countdown = 6;
        coins = 0;
    }

    /**
     * Act - do whatever the Goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        checkKeys();
        if (isMoving()){
            animate();
            realX += xSpeed; 
            realY += ySpeed;
        } else {
            if (frame > 0){
                if (countdown > 0){
                    countdown--;
                } else {
                    if (frame < 5){
                        frame--;
                    } else {
                        frame++;
                        if (frame > 8) frame = 0;
                    }
                    setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
                    countdown = 3;
                }
            }
        }
        
        centreOn(this);
        updateLocation();
    }

    private void checkKeys(){
        xSpeed = 0;
        ySpeed = 0;
        
        if (Greenfoot.isKeyDown("a")){
            direction = 1;
            xSpeed = -2;
        } 
        
        if (Greenfoot.isKeyDown("d")){
            direction = 0;
            xSpeed = 2;
        }
        
        if (Greenfoot.isKeyDown("w")){
            direction = 2;
            ySpeed = -2;
        }
        
        if (Greenfoot.isKeyDown("s")){
            direction = 3;
            ySpeed = 2;
        } 
        if(Greenfoot.mouseClicked(this)){
            attack();
        }
    }
    
    public boolean isMoving(){
        if (xSpeed == 0 && ySpeed == 0){
            return false;
        }
        return true;
    }
    
    public void attack(){
        Actor p = getOneIntersectingObject(Enemy.class);
        if(p != null){
            
        }
        killed++;
    }

    public void animate(){
        if (countdown > 0){
            countdown--;
        } else {
            frame++;
            if (frame > 8){
                frame = 1;
            }
            setImage (walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
            countdown = 6;
        }
    }
    
    
    //currently keeps looping death animation, need to fix later
    public void die(){
        if (countdown > 0){
            countdown--;
        } else {
            frame++;
            if (frame > 5) {
                frame = 1;
            }
            System.out.println(frame);
            setImage(deathAnimation.getOneImage(frame));
            countdown = 6;
        }
    }
    
    public int getCoin(){
        return coins; 
    }
    
    public void earnCoin(){
        Actor p = getOneIntersectingObject(Coin.class);
        if(p != null){
            coins++;
        }
    }
    
    public int getKilled(){
        return killed;
    }
}
