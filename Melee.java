import greenfoot.*;
import java.util.*;

public class Melee extends Enemy {
    public Melee() {
        super("guard", 128);
        cooldown = 120;

        attackSound = new GreenfootSound("melee.mp3");
        attackSound.setVolume(50);
    }

    public void act() {
        super.act();
    }

    public void attack() {
        // player.damage() or something

        // if(!inRange)return;
        Player p = (Player) getOneIntersectingObject(Player.class);
        if (p != null && p.getCollider() != null) {
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
