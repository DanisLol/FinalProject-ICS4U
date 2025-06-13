import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * NOTES:
 * - benjamin animation looks weirdly cropped??
 * - what if mouse is clicked several times before attack finishes
 * 
 * @Daniel Wang
 * @version (a version number or a date)
 */
public class Player extends HurtableEntity {
    private GreenfootImage image;
    private Animation walkAnimation, deathAnimation, attackAnimation, curAnimation;
    private int countdown, direction, frame;
    private int xSpeed, ySpeed;
    private boolean isNew;

    private int coins;
    // the number of enemies killed
    private int killed;

    // Stat bar:
    private SuperStatBar healthStat;
    private SuperStatBar coinStat;
    private int health;
    private GreenfootImage healthImage;
    private GreenfootImage coinImage;

    // Damage
    private int damage;

    private int weaponDamage = 10;

    // just to test
    private boolean toResting = false;

    private int highestIndex;
    private ActionState curAction = ActionState.NOTHING, lastAction = ActionState.NOTHING; // player starts off unmoving

    public Player() {
        // need to make variable based on settingworld
        GreenfootImage spritesheet = new GreenfootImage("Benjamin.png");
        walkAnimation = AnimationManager.createAnimation(spritesheet, 9, 4, 9, 64, 64);
        deathAnimation = AnimationManager.createAnimation(spritesheet, 20, 1, 6, 64, 64);
        attackAnimation = AnimationManager.createAnimation(new GreenfootImage("BenjaminAttacked copy.png"), 1, 4, 6,
                192, 192);
        curAnimation = walkAnimation;

        direction = 3;
        image = walkAnimation.getOneImage(Direction.fromInteger(direction), 0);
        setImage(image);
        frame = 0;
        xSpeed = 0;
        ySpeed = 0;
        realX = 0;
        realY = 0;
        isNew = true;
        countdown = 6;
        coins = 0;

        // Health SuperStatBar:
        healthImage = new GreenfootImage("heart.png");
        healthStat = new SuperStatBar(50, health, this, 200, 15, Color.RED, Color.BLACK, false, Color.BLACK, 3);
<<<<<<< Updated upstream

        // addObject(healthStat, 160, 23);

        // Coin SuperStatBar:
        coinStat = new SuperStatBar(10, coins, this, 200, 15, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);
        // addObject(coinStat, 160, 45);

        // getWorld().addObject(healthStat, 160, 23);

        // Coin SuperStatBar:
        coinStat = new SuperStatBar(10, coins, this, 200, 15, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);
        // getWorld().addObject(coinStat, 160, 45);

=======
        //getWorld().addObject(healthStat, 160, 23);
        
        //Coin SuperStatBar:
        coinStat =  new SuperStatBar(10, coins, this, 200, 15, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);       
        //getWorld().addObject(coinStat, 160, 45);
>>>>>>> Stashed changes
    }

    /**
     * Act - do whatever the Goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        checkActionState();
        System.out.println("Current: " + curAction + "\t Last: " + lastAction);

        // if action state changed, update what current animation needs to be
        if (curAction != lastAction) {
            countdown = 6;
            if (curAction == ActionState.ATTACKING) {
                frame = 0;
                highestIndex = 5;
                curAnimation = attackAnimation;
            } else if (curAction == ActionState.WALKING) {
                frame = 1;
                highestIndex = 8;
                curAnimation = walkAnimation;
            } else if (curAction == ActionState.NOTHING) {
                toResting = true; // tbh should've just been another action state
            }
        }

        // if attacking or walking, animate normally
        if (curAction == ActionState.ATTACKING || curAction == ActionState.WALKING) {
            animate();
            if (curAction == ActionState.WALKING) {
                // move player
                realX += xSpeed;
                realY += ySpeed;
            }
        } else if (toResting) {
            // player walking --> nothing: return to idle frame
            if (frame == 0) {
                toResting = false;
            } else if (frame > 0) {
                if (countdown > 0) {
                    countdown--;
                } else {
                    if (frame < 5) {
                        frame--;
                    } else {
                        frame++;
                        if (frame > 8)
                            frame = 0;
                    }
                    setImage(walkAnimation.getOneImage(Direction.fromInteger(direction), frame));
                    countdown = 3;
                }
            }
        }

        centreOn(this);
        updateLocation();
    }
    
    
    protected void addedToWorld(World world)
    {
        world.addObject(new SuperStatBar(), getX(), getY());
    }

    protected void addedToWorld(World world) {
        world.addObject(new SuperStatBar(), getX(), getY());
    }

    public void pickUpCoin() {
        coins++;
    }

    private void checkActionState() {
        lastAction = curAction;

        xSpeed = 0;
        ySpeed = 0;

        if (Greenfoot.isKeyDown("a")) {
            direction = 1;
            xSpeed = -10;
        }

        if (Greenfoot.isKeyDown("d")) {
            direction = 0;
            xSpeed = 10;
        }

        if (Greenfoot.isKeyDown("w")) {
            direction = 2;
            ySpeed = -10;
        }

        if (Greenfoot.isKeyDown("s")) {
            direction = 3;
            ySpeed = 10;
        }

        if (Greenfoot.mouseClicked(null)) {
            curAction = ActionState.ATTACKING;
            attack();
        }

        // only nothing or walking if not attacking
        if (curAction != ActionState.ATTACKING) {
            if (xSpeed == 0 && ySpeed == 0) {
                curAction = ActionState.NOTHING;
            } else {
                curAction = ActionState.WALKING;
            }
        }
    }

    public void attack() {
        Actor p = getOneIntersectingObject(Enemy.class);
        if (p != null) {
            Enemy enemy = (Enemy) p;

            // Assuming Enemy has a health system
            enemy.takeDamage(weaponDamage); // or enemy.reduceHealth(10);

            // If enemy is dead after taking damage
            if (enemy.getHealth() <= 0) {
                getWorld().removeObject(enemy);
                killed++; // Only increment kill count when enemy is destroyed
            }
        }
        killed++;
    }

    public void animate() {
        if (countdown > 0) {
            countdown--;
        } else {
            frame++;
            if (frame > highestIndex) {
                if (curAction == ActionState.WALKING) {
                    frame = 1;
                } else {
                    frame = 0;
                    curAction = ActionState.NOTHING; // change?????
                    lastAction = ActionState.ATTACKING;
                }
            }
            setImage(curAnimation.getOneImage(Direction.fromInteger(direction), frame));
            countdown = 6;
        }
    }

    public void takeDamage(int dmg) {
        this.health -= dmg;
    }

    // currently keeps looping death animation, need to fix later
    public void die() {
        if (countdown > 0) {
            countdown--;
        } else {
            frame++;
            if (frame > 5) {
                frame = 1;
            }
            System.out.println(frame);
            setImage(deathAnimation.getOneImage(frame));
            countdown = 6;
        }
    }
<<<<<<< Updated upstream

    public int getCoin() {
        return coins;
    }

    public int getCoins() {
        return coins;
    }

    public void earnCoins() {

=======
    
    public int getCoins(){
        return coins; 
    }
    
    public void earnCoins(){
>>>>>>> Stashed changes
        Actor p = getOneIntersectingObject(Coin.class);
        if (p != null) {
            coins++;
        }
    }

    // when player needs to kill specific number of enemies to pass onto next room
    public int getKilled() {
        return killed;
    }
}
