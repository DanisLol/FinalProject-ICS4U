import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * NOTES:
 * - benjamin animation looks weirdly cropped??
 * - what if mouse is clicked several times before attack finishes
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends HurtableEntity
{
    //private double xSpeed, ySpeed, percentXSpeed = 1, percentYSpeed = 1;
    private double dy, dx;
    private boolean isNew;
    private String player;
    private int coins;
    //the number of enemies killed
    private int killed;
    private int weaponDmg;

    public Player (){
        super(SettingsWorld.getPlayerSkinImage(), 192);
        player = SettingsWorld.getPlayerSkinImage();        

        curAction = ActionState.NOTHING; lastAction = ActionState.NOTHING;
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
        coins = 0;
        
        maxSpeed = 8;
        speed = maxSpeed;

        attackSound = new GreenfootSound("player_attack.wav");
        attackSound.setVolume(70);

        damage = 10; //test
        health = 100;   
        //healthStat = new SuperStatBar(50, health, this, 200, 15, Color.GREEN, Color.BLACK, true, Color.BLACK, 3);

        collider = new CollisionBox(32, 32, 16, this, false);
    }

    /**
     * Act - do whatever the Goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        if(getWorld() instanceof ShopWorld) 
            return;

        if (health > 0) {
            checkActionState();
        } else if (curAnimation == deathAnimation && frame == highestIndex){
            //finished animating death
            getWorld().addObject(new Fader("in", new DeathWorld()), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }

        //if action state changed, update what current animation needs to be
        if (curAction != lastAction){
            countdown = 0;
            if (curAction == ActionState.ATTACKING){
                attackSound.play();
                frame = 0;
                if (player.equals("melissa")) highestIndex = 7; else highestIndex = 5;
                curAnimation = attackAnimation;
            } else if (curAction == ActionState.WALKING){
                frame = 1;
                highestIndex = 8;
                curAnimation = walkAnimation;
            } else if (curAction == ActionState.NOTHING){ 
                curAnimation = walkAnimation;
                frame = 0;
                setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
            } 
        }

        //if not unmoving, animate
        if (curAction != ActionState.NOTHING && !dead){
            super.animate();
            if (curAction == ActionState.WALKING){
                //move player
                //realX += xSpeed; 
                //realY += ySpeed;
                tryMove(dx, dy);
            }
        }
        //} 
        
        centreOn(this);
        updateLocation();
    }

    public void pickUpCoin(){
        coins++;
        MyWorld w = (MyWorld) getWorld();
        w.getCoinCounter().setValue(coins);
    }

    private void checkActionState(){
        lastAction = curAction;

        dx = 0;
        dy = 0;

        if (Greenfoot.isKeyDown("a")) {
            direction = 1;
            dx = -1;
        } 

        if (Greenfoot.isKeyDown("d")) {
            direction = 0;
            dx = 1;
        }

        if (Greenfoot.isKeyDown("w")) {
            direction = 2;
            dy = -1;
        }

        if (Greenfoot.isKeyDown("s")) {
            direction = 3;
            dy = 1;
        } 

        // if both are pressed, then decrease magnitude of each one
        if(Math.abs(dx) + Math.abs(dy) == 2) {
            dy *= Math.sqrt(2)/2;
            dx *= Math.sqrt(2)/2;
        }

        dx = dx*speed;
        dy = dy*speed;

        //account for water tile?!?
        //xSpeed = xSpeed * percentXSpeed;
        //ySpeed = ySpeed * percentYSpeed;

        if (Greenfoot.mousePressed(null)){
            curAction = ActionState.ATTACKING;
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
        //killed++; 
    }

    // public void takeDamage(int dmg) {
    // health -= damage;
    // }

    public int getCoin(){
        return coins; 
    }

    //replaced by pickUpCoin()
    // public void earnCoin(){
    // // Actor p = getOneIntersectingObject(Coin.class);
    // // if(p != null){
    // coins++;
    // //}
    // }
    
    public void addKill(){
        killed ++;
    }
    
    public void clearKills(){
        killed = 0;
    }

    public int getKilled(){
        return this.killed;
    }

    public int getWeaponDmg(){
        return this.weaponDmg;
    }

    public int getHealth(){
        return this.health;
    }

    public void setHealth(int h){
        this.health += h;
    }

    public void setWeaponDmg(int dmg){
        this.weaponDmg += dmg;
    }
    
    public void takeDamage(int dmg){
        health -= dmg;
        MyWorld w = (MyWorld) getWorld();
        w.getHealthStat().update(health);

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

    // public void setImageSize(int length, int width)
    // {
    // image.scale(length, width);
    // }

    public void tryMove(double dx, double dy) {
        double oldX = getX();
        double oldY = getY();
        double oldRealX = realX;
        double oldRealY = realY;

        // Try X movement
        realX += dx;
        collider.setLocation(oldX + dx, oldY + 16);

        //List<Tile> touchingX = getIntersectingObjects(Tile.class);
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

        //List<Tile> touchingY = getIntersectingObjects(Tile.class);
        List<Tile> touchingY = collider.getIntersectingTiles();

        for (Tile tile : touchingY) {
            if (!tile.getIsPassable()) {
                realY = oldRealY;
                collider.setLocation(oldX, oldY + 16);
                break;
            }
        }
    }

    // public void setSpeedPercents(double newXPercent, double newYPercent){
        // percentXSpeed = newXPercent;
        // percentYSpeed = newYPercent;
    // }

    public void setRealXY(double newX, double newY){
        realX = newX;
        realY = newY;
    }
}