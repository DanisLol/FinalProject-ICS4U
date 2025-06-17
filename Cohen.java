import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cohen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cohen extends Enemy
{
    public Cohen(){
        super("cohen", 192);
        attackSound = new GreenfootSound("melee.mp3");
        
        cooldown = 90;
        
        health = BossWorld.MAX_BOSS_HEALTH;
    }
    
    /**
     * Act - do whatever the Cohen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public void takeDamage(int dmg){
        health -= dmg;
        
        BossWorld w = (BossWorld) getWorld();
        if (w != null ) w.updateBossHealthBar(health);
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
    
    public void attack(){
        Player p = (Player) getOneIntersectingObject(Player.class);
        if (p != null && p.getCollider() != null) {
            p.takeDamage(damage);
        }

        attackSound.play();
    }
}
