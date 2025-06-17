import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Hi Mr. Cohen. "Boss" that appears in BossWorld.
 * 
 * @author Angela Wang
 * @version June 2025
 */
public class Cohen extends Enemy
{
    private static int tempDamage;
    public Cohen(){
        super("cohen", 192);
        attackSound = new GreenfootSound("melee.mp3");
        
        cooldown = 60;
        
        health = BossWorld.MAX_BOSS_HEALTH;
        
        damage = 20;
        tempDamage = damage;
    }
    
    /**
     * Act - do whatever the Cohen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    /**
     * Receive damage and update health bar in BossWorld. If health <=0, start dying animation
     */
    public void takeDamage(int dmg){
        damage = tempDamage; 
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
    
    /**
     * Attack intersecting player
     */
    public void attack(){
        Player p = (Player) getOneIntersectingObject(Player.class);
        if (p != null && p.getCollider() != null) {
            p.takeDamage(damage);
        }

        attackSound.play();
    }
    
    public static int getDamage()
    {
        return tempDamage;
    }
    public static void setDamage(int dmg)
    {  
       tempDamage = dmg;  
    }
}
