import greenfoot.*;
import java.util.*;

/**
 * Melee enemies attack player at a close range.
 * @author Angela Wang & unknown?
 */
public class Melee extends Enemy {
    /**
     * Melee constructor
     */
    public Melee() {
        super("guard", 128);
        cooldown = 120;

        attackSound = new GreenfootSound("melee.mp3");
        attackSound.setVolume(50);
        damage = 5;
    }

    public void act() {
        super.act();
    }

    /**
     * Deal damage to an intersecting player
     */
    public void attack() {
        // player.damage() or something

        // if(!inRange)return;
        Player p = (Player) getOneIntersectingObject(Player.class);
        if (p != null && p.getCollider() != null) {
            p.takeDamage(damage);
        }

        attackSound.play();
    }

    /**
     * Receive damage. If health <= 0, start dying.
     */
    public void takeDamage(int dmg) {
        health -= dmg;

        if (health <= 0){
            if (lastAction != ActionState.DYING){
                curAction = ActionState.DYING;
                lastAction = ActionState.DYING;
                curAnimation = deathAnimation;
                frame = 0;
                highestIndex = 5;
            }
        }
    }
}
