import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HurtableEntity here.
 * 
 * @author Zachary Zhao
 * @version 0.0.1
 */
public abstract class HurtableEntity extends Scroller
{
    protected GreenfootImage spritesheet, spritesheetLarge;
    protected Animation walkAnimation, deathAnimation, attackAnimation, curAnimation = walkAnimation;
    protected int countdown, direction, frame = 0;
    protected int highestIndex;
    protected ActionState curAction = ActionState.NOTHING, lastAction = ActionState.NOTHING;

    protected int health;
    
    public void act(){
        super.act();
        animate();
    }
    
    public void animate(){
        if (countdown > 0){
            countdown--;
        } else {
            setImage (curAnimation.getOneImage(Direction.fromInteger(direction), frame));

            frame++;
            if (frame > highestIndex){
                if (curAction == ActionState.WALKING) {
                    frame = 1;
                } else {
                    frame = 0; 
                    curAction = ActionState.NOTHING; //change????? 
                    lastAction = ActionState.ATTACKING; 
                    //why does this not work properly omds OH
                    curAnimation = walkAnimation;
                    //ok ykw no i swear this was working before i give up
                    setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
                }
            }
            countdown = 6;
        }
    }

    public abstract void takeDamage(int dmg);

    public abstract void die();
}
