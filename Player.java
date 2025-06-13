import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
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
    private GreenfootImage image;
    private Animation walkAnimation, deathAnimation, attackAnimation, curAnimation;
    private int countdown, direction, frame;
    private int xSpeed, ySpeed;
    private boolean isNew;

    private int coins;
    //the number of enemies killed
    private int killed;

    //just to test 
    private boolean toResting = false;

    private int highestIndex;
    private ActionState curAction = ActionState.NOTHING, lastAction = ActionState.NOTHING; //player starts off unmoving

    public Player (){
        //need to make variable based on settingworld
        GreenfootImage spritesheet = new GreenfootImage("Benjamin.png");
        walkAnimation = AnimationManager.createAnimation(spritesheet, 9, 4, 9, 64, 64);
        deathAnimation = AnimationManager.createAnimation(spritesheet, 20, 1, 6, 64, 64);
        attackAnimation = AnimationManager.createAnimation(new GreenfootImage("BenjaminAttacked copy.png"), 1, 4, 6, 192, 192);
        curAnimation = walkAnimation;

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
        if(getWorld() instanceof ShopWorld) 
            return;

        checkActionState();
        //System.out.println("Current: " + curAction + "\t Last: " + lastAction);

        //if action state changed, update what current animation needs to be
        if (curAction != lastAction){
            countdown = 6;
            if (curAction == ActionState.ATTACKING){
                frame = 0;
                highestIndex = 5;
                curAnimation = attackAnimation;
            } else if (curAction == ActionState.WALKING){
                frame = 1;
                highestIndex = 8;
                curAnimation = walkAnimation;
            } else if (curAction == ActionState.NOTHING){ 
                toResting = true; //tbh should've just been another action state
            }
        }

        //if attacking or walking, animate normally 
        if (curAction == ActionState.ATTACKING || curAction == ActionState.WALKING){
            animate();
            if (curAction == ActionState.WALKING){
                //move player
                //realX += xSpeed; 
                //realY += ySpeed;
                tryMove(xSpeed, ySpeed);
            }
        } else if (toResting){
            //player walking --> nothing: return to idle frame
            if (frame == 0){
                toResting = false;
            } else if (frame > 0){
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

    public void pickUpCoin(){
        coins++;
    }

    private void checkActionState(){
        lastAction = curAction;

        xSpeed = 0;
        ySpeed = 0;

        if(getWorld() instanceof ShopWorld)
        {
        }
        else
        {

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

    public void animate(){
        if (countdown > 0){
            countdown--;
        } else {
            frame++;
            if (frame > highestIndex){
                if (curAction == ActionState.WALKING) {
                    frame = 1;
                } else {
                    frame = 0; 
                    curAction = ActionState.NOTHING; //change????? 
                    lastAction = ActionState.ATTACKING;
                }
            }
            setImage (curAnimation.getOneImage(Direction.fromInteger(direction), frame));
            countdown = 6;
        }
    }

    public void takeDamage(int dmg) {
        // place holder add stuff pls
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

    public void setImageSize(int length, int width)
    {
        image.scale(length, width);
    }
    
    public void tryMove(int dx, int dy) {
        int oldX = getX();
        int oldY = getY();
        double oldRealX = realX;
        double oldRealY = realY;
    
        // Try X movement
        realX += dx;
        setLocation(oldX + dx, oldY);
    
        java.util.List<Tile> touchingX = getIntersectingObjects(Tile.class);
        for (Tile tile : touchingX) {
            if (!tile.getIsPassable()) {
                realX = oldRealX;
                setLocation(oldX, oldY);
                break;
            }
        }
    
        // Try Y movement
        oldX = getX();  
        oldY = getY();
        oldRealX = realX;
        oldRealY = realY;
    
        realY += dy;
        setLocation(oldX, oldY + dy);
    
        java.util.List<Tile> touchingY = getIntersectingObjects(Tile.class);
        for (Tile tile : touchingY) {
            if (!tile.getIsPassable()) {
                realY = oldRealY;
                setLocation(oldX, oldY);
                break;
            }
        }
    }
}

