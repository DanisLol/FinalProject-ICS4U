import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Player is controlled by WASD keys. Click mouse to attack. 
 * 
 * @author Daniel Wang, Angela Wang
 * @version (a version number or a date)
 */
public class Player extends HurtableEntity
{
    //private double xSpeed, ySpeed, percentXSpeed = 1, percentYSpeed = 1;
    private double dy, dx;
    private boolean isNew;
    private String player;
    private static int coins;
    // the number of enemies killed
    private int killed;
    private int weaponDmg;
    private int tempSpeed;
    private SuperStatBar healthStat;
    private int playerMaxHealth;

    /**
     * Player constructor
     */
    public Player() {
        //set player image based on chosen image in settings world
        super(SettingsWorld.getPlayerSkinImage(), 192);
        player = SettingsWorld.getPlayerSkinImage();

        curAction = ActionState.NOTHING;
        lastAction = ActionState.NOTHING;
        // curAnimation = walkAnimation;

        //direction = 3;        
        //image = walkAnimation.getOneImage(Direction.fromInteger(direction), 0); 
        //setImage(image);
        //frame = 0;
        dy = 0;
        dx = 0;
        realX = 0;
        realY = 0;
        isNew = true;
        countdown = 6;
        coins = SettingsWorld.getUserInfoInt(3);        
        maxSpeed = 5;
        speed = maxSpeed;

        attackSound = new GreenfootSound("player_attack.wav");
        attackSound.setVolume(70);

        tempSpeed = 3; 

        //set damages and healths based on difficulty selection
        if(SettingsWorld.getDifficultiyLevelImage() == 0)
        {
            damage = 20; 
            playerMaxHealth = 400;
        }
        else if (SettingsWorld.getDifficultiyLevelImage() == 1)
        {
            damage = 15; 
            playerMaxHealth = 300;
        }
        else 
        {
            damage = 21; 
            playerMaxHealth = 200;
        }
        health = playerMaxHealth;

        collider = new CollisionBox(32, 32, 16, this, false);
    }

    /**
     * Act - do whatever the Goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (getWorld() instanceof ShopWorld)
            return;

        if (health > 0) {
            checkActionState();
        } else if (curAnimation == deathAnimation && frame == highestIndex) {
            // finished animating death
            getWorld().addObject(new Fader("in", new DeathWorld()), getWorld().getWidth() / 2,
                getWorld().getHeight() / 2);
        }

        // if action state changed, update what current animation needs to be
        if (curAction != lastAction) {
            countdown = 0;
            if (curAction == ActionState.ATTACKING) {
                attackSound.play();
                frame = 0;
                if (player.equals("melissa"))
                    highestIndex = 7;
                else
                    highestIndex = 5;
                curAnimation = attackAnimation;
            } else if (curAction == ActionState.WALKING) {
                frame = 1;
                highestIndex = 8;
                curAnimation = walkAnimation;
            } else if (curAction == ActionState.NOTHING) {
                curAnimation = walkAnimation;
                frame = 0;
                setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
            }
        }

        // if not unmoving, animate
        if (curAction != ActionState.NOTHING && !dead) {
            super.animate();
            if (curAction == ActionState.WALKING){
                //move player
                //realX += xSpeed; 
                //realY += ySpeed;
                tryMove(dx, dy);
            }
        }
        //} 

        if (this.isTouching(PortalTile.class)){
            MyWorld w = (MyWorld) getWorld();
            w.increaseLevel();
        }

        centreOn(this);
        updateLocation();
    }
    
    /**
     * Set speed to given new speed
     * @param newSpeed  New speed of player
     */
    public void setSpeed(int newSpeed)
    {
        tempSpeed = newSpeed; 
    }

    /**
     * Adds 1 to coin count
     */
    public void pickUpCoin() {
        coins++;
        MyWorld w = (MyWorld) getWorld();
        w.getCoinCounter().setValue(coins);
    }

    private void checkActionState(){
        lastAction = curAction;

        dx = 0;
        dy = 0;
        
        //set speed based on wasd keys
        if (Greenfoot.isKeyDown("a")) {
            direction = 1;
            dx = -tempSpeed;
        } 

        if (Greenfoot.isKeyDown("d")) {
            direction = 0;
            dx = tempSpeed;
        }

        if (Greenfoot.isKeyDown("w")) {
            direction = 2;
            dy = -tempSpeed;
        }

        if (Greenfoot.isKeyDown("s")) {
            direction = 3;
            dy = tempSpeed;
        } 

        // if both are pressed, then decrease magnitude of each one
        if(Math.abs(dx) + Math.abs(dy) == 2) {
            dy *= Math.sqrt(2)/2;
            dx *= Math.sqrt(2)/2;
        }

        dx = dx*tempSpeed;
        dy = dy*tempSpeed;

        //account for water tile?!?
        //xSpeed = xSpeed * percentXSpeed;
        //ySpeed = ySpeed * percentYSpeed;

        if (Greenfoot.mousePressed(null)) {
            curAction = ActionState.ATTACKING;
            //attack();
        }

        //only nothing or walking if not attacking
        if (curAction != ActionState.ATTACKING){
            if (dx == 0 && dy == 0) {
                curAction = ActionState.NOTHING; 
            } else {
                curAction = ActionState.WALKING;
            }
        }
    }

    /**
     * Deals damage to an enemy intersecting an attack collider based on direction 
     */
    public void attack(){
        //add a collision box based on the player's current direction
        int xOffset, yOffset, x, y;
        if (direction < 2){
            x = 40; 
            y = 40; 
            yOffset = 0;
            xOffset = direction == 0? 25: -25;
        } else {
            x = 40;
            y = 32;
            xOffset = 0;
            yOffset = direction == 3? 25 : -25;
        }
        CollisionBox attackCollider = new CollisionBox(x, y, 0, null, false);
        getWorld().addObject(attackCollider, getX() + xOffset, getY() + yOffset);

        //get intersecting enemies with attack collider instead of player img
        //might need to put a cap on this?????
        List<CollisionBox> enemies = attackCollider.getIntersectingColliders();
        for (CollisionBox c : enemies){
            if (c.getOwner() instanceof Enemy){
                Enemy e = (Enemy) c.getOwner();
                if (e != null) e.takeDamage(damage);
                System.out.println("Reached");
                //System.out.println(killed);
            }
            //but enemy doesn't necessarily die after one attack??? 
        }

        List<BarrelTile> barrels = attackCollider.getIntersectingBarrels();
        if (barrels.size() != 0){
            barrels.get(0).takeDamage(damage);
        }

        getWorld().removeObject(attackCollider);
    }

    /**
     * Reduce player hp by given damage
     * @param dmg   damage to deal to player
     */
    public void takeDamage(int dmg) {
        health -= dmg;

        System.out.println(health);
        
        //really really cheap but it's very late in the morning. 
        if (getWorld() instanceof MyWorld){
            MyWorld w = (MyWorld) getWorld();
            w.getHealthStat().update(health);
        } else if (getWorld() instanceof BossWorld){
            BossWorld w = (BossWorld) getWorld();
            w.updatePlayerHealthBar(health);
        }

        //if health <= 0 start dying
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
     * Return number of coins player has
     * @return coins    Player's coins
     */
    public int getCoin() {
        return coins;
    }

    /**
     * Store player's coin amount?
     */
    public void updateCoin()
    {
        SettingsWorld.setUserInfoInt(3, coins); 
        SettingsWorld.storeInfo();
    }

    //replaced by pickUpCoin()
    // public void earnCoin(){
    // // Actor p = getOneIntersectingObject(Coin.class);
    // // if(p != null){
    // coins++;
    // //}
    // }
    
    //not used
    public void addKill(){
        killed ++;
    }

    public void clearKills(){
        killed = 0;
    }

    public int getKilled() {
        return this.killed;
    }
    
    public int getWeaponDmg() {
        return this.weaponDmg;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public void setWeaponDmg(int dmg) {
        this.weaponDmg += dmg;
    }

    /**
     * Attempt to move dx pixels in the horizontal, dy pixels in the vertical. If resulting position is invalid, do not move.
     * @param dx    
     * @param dy
     */
    public void tryMove(double dx, double dy) {
        double oldX = getX();
        double oldY = getY();
        double oldRealX = realX;
        double oldRealY = realY;

        // Try X movement
        realX += dx;
        collider.setLocation(oldX + dx, oldY + 16);

        // List<Tile> touchingX = getIntersectingObjects(Tile.class);
        List<Tile> touchingX = collider.getIntersectingTiles();

        for (Tile tile : touchingX) {
            if (!tile.getIsPassable()) {
                realX = oldRealX;
                collider.setLocation(oldX, oldY + 16);
                break;
            }
        }

        // Try Y movement
        oldX = getX();
        oldY = getY();
        oldRealX = realX;
        oldRealY = realY;

        realY += dy;
        collider.setLocation(oldX, oldY + dy + 16);

        // List<Tile> touchingY = getIntersectingObjects(Tile.class);
        List<Tile> touchingY = collider.getIntersectingTiles();

        for (Tile tile : touchingY) {
            if (!tile.getIsPassable()) {
                realY = oldRealY;
                collider.setLocation(oldX, oldY + 16);
                break;
            }
        }
    }

    
    public int getPlayerMaxHealth()
    {
        return playerMaxHealth;
    }
    // public void setSpeedPercents(double newXPercent, double newYPercent){
    // percentXSpeed = newXPercent;
    // percentYSpeed = newYPercent;
    // }
}