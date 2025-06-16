import greenfoot.*;
import java.util.*;
public class Melee extends Enemy
{      
    public Melee(){
        super("guard", 128);
        
        
        cooldown = 120;
    }
    
    public void act()
    {
        super.act();
    }
    protected void attack(){
        //player.damage() or something
        Player p = (Player) getOneIntersectingObject(Player.class);
        if(p != null && p.getCollider() != null){
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
