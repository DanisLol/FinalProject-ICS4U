import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A abstract class comprised of all of the entities that can be hurt
 * 
 * @author Zachary Zhao
 * @version 0.0.1
 */
public abstract class HurtableEntity extends Scroller
{
<<<<<<< Updated upstream
    protected int health;
=======
    protected GreenfootImage spritesheet, spritesheetLarge;
    protected Animation walkAnimation, deathAnimation, attackAnimation, curAnimation = walkAnimation;
    protected int countdown, direction, frame = 0;
    protected int highestIndex;
    protected ActionState curAction = ActionState.WALKING, lastAction = ActionState.WALKING;
    protected CollisionBox collider;
    protected GreenfootSound attackSound;
    protected int damage;
    protected double speed;
    protected double maxSpeed;
    protected boolean dead;

    protected int health;

    public HurtableEntity(String sheetName, int largeSize){
        String sheet1 = sheetName + ".png"; 
        String sheet2 = sheetName + "_attack.png";
        spritesheet = new GreenfootImage(sheet1);
        walkAnimation = AnimationManager.createAnimation(spritesheet, 9, 4, 9, 64, 64);
        deathAnimation = AnimationManager.createAnimation(spritesheet, 20, 1, 6, 64, 64);
        if (this instanceof Ranged) {
            attackAnimation = AnimationManager.createAnimation(spritesheet, 4, 4, 7, 64, 64);
        } else { 
            spritesheetLarge = new GreenfootImage(sheet2);
            attackAnimation = AnimationManager.createAnimation(spritesheetLarge, 1, 4, 6, largeSize, largeSize); //don't know why but this is different for some sheets
        }

        direction = 3;
        frame = 0;
        curAnimation = walkAnimation;
        frame = 0;
        highestIndex = 8;
        countdown = 6;

        curAnimation = walkAnimation;
        setImage(curAnimation.getOneImage(Direction.fromInteger(direction), frame));

        collider = new CollisionBox(32, 32, 16, this);
    }

    public void addedToWorld(World w){
        super.addedToWorld(w);
        w.addObject(collider, getX(), getY() + 16);
    }

    public void act(){
        super.act();
        //if (this instanceof Player) animate();
    }

    public void animate(){
        if (countdown > 0){
            countdown--;
        } else {
            countdown = 6;
            if (curAction == ActionState.DYING){
                setImage(curAnimation.getOneImage(frame));
            } else {  
                setImage (curAnimation.getOneImage(Direction.fromInteger(direction), frame));
            }

            frame++;
            if (this instanceof Player) System.out.println(frame);
            if (frame > highestIndex){
                if (curAction == ActionState.WALKING) {
                    frame = 1;
                } else if (curAction == ActionState.ATTACKING){
                    if (curAnimation == attackAnimation){
                        if (this instanceof Enemy){
                            java.util.List<Player> p = getWorld().getObjects(Player.class);
                            if (p != null){
                                p.get(0).takeDamage(damage);
                            }
                        } else {
                            java.util.List<Enemy> enemies = getIntersectingObjects(Enemy.class);
                            if (enemies.size() != 0){
                                enemies.get(0).takeDamage(damage);
                            }
                        }
                    }

                    frame = 0; 
                    curAction = ActionState.NOTHING; //change????? 
                    lastAction = ActionState.ATTACKING; 
                    curAnimation = walkAnimation;
                    setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));

                } else if (curAction == ActionState.DYING){
                    //die(); I DON'T KNOW WHY BUT THIS CAUSES ACTORREMOVEDFROMWORLD ERROR tried return everywhere too fried for this
                    dead = true;
                    //System.out.println("Reached");
                    //return;
                }
            }
        }
    }

    public void alterSpeed(double multiplier) {
        speed = maxSpeed*multiplier;
    }
    
    public void resetSpeed() {
        speed = maxSpeed;
    }
    
    public void takeDamage(int dmg){
        health -= dmg;
        if (health <= 0){
            curAction = ActionState.DYING;
            lastAction = ActionState.DYING;
            curAnimation = deathAnimation;
            frame = 0;
            highestIndex = 5;
        }
    }
    
    public boolean isDead() {
        return dead;
    }

    public void die(){
        getWorld().removeObject(this);
    }
>>>>>>> Stashed changes
    
    public abstract void takeDamage(int dmg);
    
    public abstract void die();
}
