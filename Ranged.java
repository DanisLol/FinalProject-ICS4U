import greenfoot.*;
import java.util.*;

/**
 * The Ranged class represents a ranged enemy that attacks the player from a distance by shooting projectiles.
 * It extends the Enemy class, inheriting the basic enemy functionality, and adds a ranged attack mechanism.
 * The enemy shoots projectiles at the player when within range, with a cooldown to prevent constant firing.
 * The attack involves calculating the angle towards the player, creating a projectile, and firing it with a set damage and speed.
 * 
 * @author Ricky Zhu + 
 * @version June 2025
 */


public class Ranged extends Enemy
{   
    /**
     * Constructor for Ranged enemy
     * Uses default health and initializes a cooldown for attacks
     */
    public Ranged(){
        super("skeleton", 192);

        cooldown = 60;
    }
    
    /**
     * Act method - calls from Enemy act
     */
    public void act()
    {
        super.act();
    }
    
    /**
     * The attack method is called when the enemy is within range of the player. 
     * It calculates the angle towards the player, spawns a projectile, and sets its rotation accordingly.
     * The projectile is fired with an offset from the enemy’s position.
     * The enemy's current action is updated to "ATTACKING", and the animation is set to reflect the attack.
     */
    public void attack(){
        if(!inRange)return;

        //test
        if (health > 0) {
            //start: set animation to attack at frame 0
            //think this was because something with the animation was bugging out
            if (lastAction == ActionState.WALKING){
                curAction = ActionState.ATTACKING;
                curAnimation = attackAnimation;
                frame = 0;
                highestIndex = 6;
            }
        }

        double angle = Math.toDegrees(Math.atan2(play.getY()-getY(), play.getX()-getX()));
        int damage = 5, speed = 5;
        Projectile p = new Projectile(this,damage,speed,angle);
        int offsetX = (int)(25*Math.cos(Math.toRadians(angle)));
        int offsetY = (int)(25*Math.sin(Math.toRadians(angle)));
        getWorld().addObject(p,getX()+offsetX,getY()+offsetY);
        p.setRotation((int)angle);
    }

    // public void takeDamage(int dmg) {
    // // place holder add stuff pls
    // }
    public void die() {

    }
}
