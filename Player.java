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
    //private Animation walkAnimation, deathAnimation, attackAnimation, curAnimation;
    //private int countdown, direction, frame;
    private int xSpeed, ySpeed;
    private boolean isNew;

    private int coins;
    //the number of enemies killed
    private int killed;

    //just to test 
    private boolean toResting = false;
    private int lastFrame = 0;

    private CollisionBox collider;

    // private int highestIndex;
    // private ActionState curAction = ActionState.NOTHING, lastAction = ActionState.NOTHING; //player starts off unmoving

    public Player (){
        super("benjamin", 192);
        //need to make variable based on settingworld

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

        collider = new CollisionBox(image.getWidth(), image.getHeight(), this);
        System.out.println("Width: " + image.getWidth() + " Height: " + image.getHeight());
    }

    public void addedToWorld(World w){
        w.addObject(collider, getX(), getY());
    }

    /**
     * Act - do whatever the Goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        checkActionState();
        //System.out.println("Current: " + curAction + "\t Last: " + lastAction);

        if (lastFrame != frame) System.out.println(frame);

        //if action state changed, update what current animation needs to be
        if (curAction != lastAction){
            countdown = 0;
            if (curAction == ActionState.ATTACKING){
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

        //lastFrame = frame;

        //if attacking or walking, animate normally 
        if (curAction == ActionState.ATTACKING || curAction == ActionState.WALKING){
            super.animate();
            if (curAction == ActionState.WALKING){
                //move player
                //if (collider.isClear()){
                realX += xSpeed; 
                realY += ySpeed;
                //}
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

    public void takeDamage(int dmg) {
        // place holder add stuff pls
    }

    //fix this
    public void die(){
        if (countdown > 0){
            countdown--;
        } else {
            frame++; //change this
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
