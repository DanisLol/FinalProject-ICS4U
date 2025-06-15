import greenfoot.*;
import java.util.*;

public class Ranged extends Enemy
{   
    public Ranged(){
        super("skeleton", 192);
        
        cooldown = 60;
    }
    public void act()
    {
        super.act();
    }
    protected void attack(){
        if(!inRange)return;
        double angle = Math.toDegrees(Math.atan2(play.getY()-getY(), play.getX()-getX()));
        int damage = 0, speed = 5;
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
