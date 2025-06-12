import greenfoot.*;
import java.util.*;

public class Ranged extends Enemy
{   
    public Ranged(){
        super();
        spritesheet = new GreenfootImage("skeleton.png");
        spritesheetLarge = new GreenfootImage("skeleton_attack.png");
        walkAnimation = AnimationManager.createAnimation(spritesheet, 9, 4, 9, 64, 64);
        deathAnimation = AnimationManager.createAnimation(spritesheet, 20, 1, 6, 64, 64);
        attackAnimation = AnimationManager.createAnimation(spritesheetLarge, 1, 4, 6, 192, 192);
        curAnimation = walkAnimation;
        
        cooldown = 180;
    }
    public void act()
    {
        super.act();
    }
    protected void attack(){
        if(!inRange)return;
        
        double angle = Math.toDegrees(Math.atan2(play.getY()-getY(), play.getX()-getX()));
        int damage = 0, speed = 0;
        Projectile p = new Projectile(play,damage,speed);
        int offsetX = (int)(25*Math.cos(Math.toRadians(angle)));
        int offsetY = (int)(25*Math.sin(Math.toRadians(angle)));
        getWorld().addObject(p,getX()+offsetX,getY()+offsetY);
        p.setRotation((int)angle);
    }
    
    public void takeDamage(int dmg) {
        // place holder add stuff pls
    }
    public void die() {
        
    }
}
