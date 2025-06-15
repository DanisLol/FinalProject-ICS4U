import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * NOTES:
 * - benjamin animation looks weirdly cropped??
 * - what if mouse is clicked several times before attack finishes
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends HurtableEntity
{
    private int xSpeed, ySpeed;
    private boolean isNew;

    private int coins;
    //the number of enemies killed
    private int killed;

    public Player (){
        super("benjamin", 192);
        //need to make variable based on settingworld

        curAction = ActionState.NOTHING; lastAction = ActionState.NOTHING;
        // curAnimation = walkAnimation;

        //direction = 3;        
        //image = walkAnimation.getOneImage(Direction.fromInteger(direction), 0); 
        //setImage(image);
        //frame = 0;
        xSpeed = 0;
        ySpeed = 0;
        realX = 0;
        realY = 0;
        isNew = true;
        countdown = 6;
        coins = 0;

        attackSound = new GreenfootSound("player_attack.wav");
        attackSound.setVolume(70);

        damage = 10; //test
        health = 100;

    }

    /**
     * Act - do whatever the Goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        if(getWorld() instanceof ShopWorld) 
            return;

        if (health > 0) checkActionState();
        //if (dead) { die(); return;}; 
        //error

        //if action state changed, update what current animation needs to be
        if (curAction != lastAction){
            countdown = 0;
            if (curAction == ActionState.ATTACKING){
                attackSound.play();
                frame = 0;
                highestIndex = 5;
                curAnimation = attackAnimation;
            } else if (curAction == ActionState.WALKING){
                frame = 1;
                highestIndex = 8;
                curAnimation = walkAnimation;
            } else if (curAction == ActionState.NOTHING){ 
                curAnimation = walkAnimation;
                frame = 0;
                setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
            } 
        }

        //if not unmoving, animate
        if (curAction != ActionState.NOTHING && !dead){
            super.animate();
            if (curAction == ActionState.WALKING){
                //move player
                //realX += xSpeed; 
                //realY += ySpeed;
                tryMove(xSpeed, ySpeed);
            }
        } 

        centreOn(this);
        updateLocation();
    }

    public void pickUpCoin(){
        coins++;
    }

    private void checkActionState(){
        lastAction = curAction;

        xSpeed = 0;
        ySpeed = 0;

        //is this if statement not redundant 
        if(getWorld() instanceof ShopWorld)
        {
        }
        else
        {

            if (Greenfoot.isKeyDown("a")){
                direction = 1;
                xSpeed = -10;
            } 

            if (Greenfoot.isKeyDown("d")){
                direction = 0;
                xSpeed = 10;
            }

            if (Greenfoot.isKeyDown("w")){
                direction = 2;
                ySpeed = -10;
            }

            if (Greenfoot.isKeyDown("s")){
                direction = 3;
                ySpeed = 10;
            } 
        }

        if (Greenfoot.mousePressed(null)){
            curAction = ActionState.ATTACKING;
        }

        //only nothing or walking if not attacking
        if (curAction != ActionState.ATTACKING){
            if (xSpeed == 0 && ySpeed == 0) {
                curAction = ActionState.NOTHING; 
            } else {
                curAction = ActionState.WALKING;
            }
        }
    }

    public void attack(){
        Actor p = getOneIntersectingObject(Enemy.class);
        if(p != null){

        }
        killed++;
    }

    // public void takeDamage(int dmg) {
    // health -= damage;
    // }

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

    // public void setImageSize(int length, int width)
    // {
    // image.scale(length, width);
    // }

    public void tryMove(int dx, int dy) {
        int oldX = getX();
        int oldY = getY();
        double oldRealX = realX;
        double oldRealY = realY;

        // Try X movement
        realX += dx;
        collider.setLocation(oldX + dx, oldY + 16);

        //List<Tile> touchingX = getIntersectingObjects(Tile.class);
        List<Tile> touchingX = collider.getIntersectingTiles();

        for (Tile tile : touchingX) {
            if (!tile.getIsPassable()) {
                realX = oldRealX;
                collider.setLocation(oldX, oldY + 16);
                break;
            }
        }

        // Try Y movement
        oldX = getX();  
        oldY = getY();
        oldRealX = realX;
        oldRealY = realY;

        realY += dy;
        collider.setLocation(oldX, oldY + dy + 16);

        //List<Tile> touchingY = getIntersectingObjects(Tile.class);
        List<Tile> touchingY = collider.getIntersectingTiles();

        for (Tile tile : touchingY) {
            if (!tile.getIsPassable()) {
                realY = oldRealY;
                collider.setLocation(oldX, oldY + 16);
                break;
            }
        }
    }
}