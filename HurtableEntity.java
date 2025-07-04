import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A abstract class comprised of all of the entities that can be hurt
 * 
 * @author Zachary Zhao, Daniel Wang
 * @version 0.0.1
 */
public abstract class HurtableEntity extends Scroller
{
    protected GreenfootImage spritesheet, spritesheetLarge;
    protected Animation walkAnimation, deathAnimation, attackAnimation, curAnimation = walkAnimation;
    protected int countdown, direction, frame = 0;
    protected int highestIndex;
    protected ActionState curAction = ActionState.WALKING, lastAction = ActionState.WALKING;
    protected CollisionBox collider;
    protected GreenfootSound attackSound;
    protected int damage;
    protected boolean dead = false;

    protected int health;
    
    

    public HurtableEntity(String sheetName, int largeSize){
        
        String sheet1 = sheetName + ".png"; 
        String sheet2 = sheetName + "_attack.png";
        spritesheet = new GreenfootImage(sheet1);
        walkAnimation = AnimationManager.createAnimation(spritesheet, 9, 4, 9, 64, 64);
        deathAnimation = AnimationManager.createAnimation(spritesheet, 20, 1, 6, 64, 64);
        if (this instanceof Ranged) {
            attackAnimation = AnimationManager.createAnimation(spritesheet, 4, 4, 7, 64, 64);
        } else if (this instanceof Player && sheetName.equals("melissa")){
            spritesheetLarge = new GreenfootImage(sheet2);
            attackAnimation = AnimationManager.createAnimation(spritesheetLarge, 1, 4, 8, largeSize, largeSize);
        }
        else { 
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

        collider = new CollisionBox(32, 32, 16, this, false);
    }

    public void addedToWorld(World w){
        super.addedToWorld(w);
        w.addObject(collider, getX(), getY() + 16);
    }

    public void act(){
        super.act();
    }

    //Animation:
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

            if (frame > highestIndex){
                if (curAction == ActionState.WALKING) {
                    frame = 1;
                } else if (curAction == ActionState.ATTACKING){
                    //on last frame of attack animation, deal damage 
                    if (curAnimation == attackAnimation){
                        attack();
                        // if (this instanceof Enemy){
                        // java.util.List<Player> p = getWorld().getObjects(Player.class);
                        // if (p != null){
                        // p.get(0).takeDamage(damage);
                        // }
                        // } else {

                        // java.util.List<Enemy> enemies = getIntersectingObjects(Enemy.class);
                        // if (enemies.size() != 0){
                        // enemies.get(0).takeDamage(damage);
                        // }
                        // }
                    }

                    frame = 0; 

                    //need to change this probably
                    if (this instanceof Player )curAction = ActionState.NOTHING;  else curAction = ActionState.WALKING;
                    lastAction = ActionState.ATTACKING; 
                    curAnimation = walkAnimation;
                    setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
                } else if (curAction == ActionState.DYING){
                    dead = true;
                }
            }
        }
    }

    public abstract void attack(); 

    public void takeDamage(int dmg){
        health -= dmg;
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

    public void die(){
        player.addKill();
        getWorld().removeObject(this);
    }

    public CollisionBox getCollider(){
        return collider;
    }
    
    protected int getDmg(){
        return damage;
    }
    
    protected void changeDmg(){
        damage -= 5;
    }
    
 
}
