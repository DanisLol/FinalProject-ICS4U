import greenfoot.*;
import java.util.*;
/**
 * The Melee class represents a melee enemy that attacks the player when in close range.
 * This class extends the Enemy class, inheriting the basic enemy functionality, and adds a melee attack mechanism.
 * The enemy damages the player when colliding with them and has a cooldown to prevent constant attacking.
 * The attack sound is played every time the enemy attacks.
 * 
 * @author Ricky Zhu +
 * @version June 2025
 */
public class Melee extends Enemy
{      
    /**
     * Constructor for Melee
     * Initializes cooldown and damage
     */
    public Melee(){
        super("guard", 128);

        // curAnimation = walkAnimation;
        // direction = 0;
        // frame = 0;
        // highestIndex = 8;
        // countdown = 6;

        // setImage(curAnimation.getOneImage(Direction.fromInteger(0), 0));

        // curAction = ActionState.WALKING;
        // lastAction = ActionState.WALKING;

        //??????
        // realX = 100;
        // realY = 100;

        cooldown = 120;
        
        attackSound = new GreenfootSound("melee.mp3");
        attackSound.setVolume(50);
        damage = 5;
    }
    
    /**
     * Act method - calls from Enemy act
     */
    public void act()
    {
        super.act();
    }
    
    /**
     * The `attack` method is called when the melee enemy comes into contact with the player.
     * It checks for a collision with the player and applies damage to the player if they are hit.
     * It also plays an attack sound when the enemy attacks.
     */
    public  void attack(){
        //player.damage() or something

        //if(!inRange)return;
        Player p = (Player) getOneIntersectingObject(Player.class);
        if (p != null && p.getCollider() != null){
            p.takeDamage(damage);
        }
        
        attackSound.play();

    }

    // public void takeDamage(int dmg) {
    // // place holder add stuff pls
    // }
    // public void die() {
    // getWorld().removeObject(this);
    // }
}
