import greenfoot.*;
import java.util.*;

public class Melee extends Enemy
{      
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
    }

    public void act()
    {
        super.act();
    }

    public  void attack(){
        //player.damage() or something

        //if(!inRange)return;

        Player p = (Player) getOneIntersectingObject(Player.class);
        if (p != null){
            p.takeDamage(damage);
        }

    }

    // public void takeDamage(int dmg) {
    // // place holder add stuff pls
    // }
    // public void die() {
    // getWorld().removeObject(this);
    // }
}
