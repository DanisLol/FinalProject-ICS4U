import greenfoot.*;
import java.util.*;
public class Melee extends Enemy
{
    public Melee(){
        super();
        spritesheet = new GreenfootImage("Melissa.png");
        spritesheetLarge = new GreenfootImage("benjamin_attack.png");
        walkAnimation = AnimationManager.createAnimation(spritesheet, 9, 4, 9, 64, 64);
        deathAnimation = AnimationManager.createAnimation(spritesheet, 20, 1, 6, 64, 64);
        attackAnimation = AnimationManager.createAnimation(spritesheet, 1, 4, 6, 128, 128);
        
        curAnimation = walkAnimation;
        direction = 0;
        frame = 0;
        highestIndex = 8;
        countdown = 6;
        
        setImage(curAnimation.getOneImage(Direction.fromInteger(0), 0));
        
        curAction = ActionState.WALKING;
        lastAction = ActionState.WALKING;
        
        realX = 100;
        realY = 100;
        
        cooldown = 120;
    }
    public void act()
    {
        super.act();
    }
    protected void attack(){
        //player.damage() or something
    }
    
    public void takeDamage(int dmg) {
        // place holder add stuff pls
    }
    public void die() {
        
    }
}
