import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * description goes here
 * 
 * @author Zachary Zhao, Daniel Wang
 * @version 0.0.2
 */

public abstract class Enemy extends HurtableEntity {
    protected Player play;
    protected boolean inRange;
    protected int cd = 0;
    protected int cooldown;
    private boolean isOld;
    protected double speed; // magnitude of movement
    protected double dx, dy; // directions
    protected int distanceFromPlayer; // the range at which the enemy will stop moving toward
    private boolean horizontallyBlocked, verticallyBlocked;
    private boolean horizontalSideSteppingCommenced, verticalSideSteppingCommenced;
    private boolean attemptingSideStepHorizontally, attemptingSideStepVertically;
    private boolean counted = false;
    protected SuperStatBar healthStat;

    public Enemy(String sheetName, int largeSize) {
        super(sheetName, largeSize);
        healthStat = new SuperStatBar(50, health, this, 200, 15, Color.RED, Color.BLACK, false, Color.BLACK, 3);
        GreenfootImage img = new GreenfootImage(16, 16);
        img.setColor(Color.RED);
        img.fillRect(0, 0, 16, 16);
        setImage(img);
    }

    // private int direction; //for animation

    public void act() {
        super.act();
        if (!dead) {
            attacking();
            pathFindTowardPlayer();
            pushEntities();
            getDirection();
            super.animate();
        }

        if (dead) {
            if (!counted) {
                play.addKill();
                counted = true;
            }
        }

    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        w.addObject(collider, getX(), getY() + 16);
        if (!isOld) {
            List<Player> players = w.getObjects(Player.class);
            if (!players.isEmpty()) {
                play = players.get(0);
            }
            isOld = true;
        }
    }

    private void getDirection() {
    }

    private void pushEntities() {
        List<HurtableEntity> enemies = getWorld().getObjects(HurtableEntity.class);
        for (HurtableEntity e : enemies) {
            if (e != this) {
                double dist = Math.hypot(getX() - e.getX(), getY() - e.getY());
                if (dist < 30) { // adjust if necesary
                    double dx = getX() - e.getX();
                    double dy = getY() - e.getY();
                    double mag = Math.hypot(dx, dy);
                    if (mag != 0) {
                        dx /= mag;
                        dy /= mag;
                        realX += dx * 5;
                        realY += dy * 5;
                    }
                }
            }

        }
    }

    protected void attacking() {
        cd++;
        if (cd % cooldown == 0 && inRange && health > 0) {
            // attack(); --> move this to be called in HurtableEntity
            curAction = ActionState.ATTACKING;
            curAnimation = attackAnimation;
            frame = 0;
            if (this instanceof Melee)
                highestIndex = 5;
            else
                highestIndex = 6;
        }
    }

    public void pathFindTowardPlayer() {

        boolean[] s = checkSurroundingTiles(); // 0-8 as documented

        double xDiff = play.getX() - getX();
        double yDiff = play.getY() - getY();
        double distance = Math.hypot(xDiff, yDiff);

        if (distance <= distanceFromPlayer) {
            dx = dy = 0; // dont move you're close enough
        }

        double absX = Math.abs(xDiff);
        double absY = Math.abs(yDiff);

        boolean canMoveDiagonally = false;
        if (xDiff < 0 && yDiff < 0) // NW
            canMoveDiagonally = s[7] && s[1] && s[8];
        else if (xDiff > 0 && yDiff < 0) // NE
            canMoveDiagonally = s[3] && s[1] && s[2];
        else if (xDiff > 0 && yDiff > 0) // SE
            canMoveDiagonally = s[3] && s[5] && s[4];
        else if (xDiff < 0 && yDiff > 0) // SW
            canMoveDiagonally = s[7] && s[5] && s[6];

        if (canMoveDiagonally) { // clear diagonal tiles means normal vector move
            moveDirectlyTowardPlayer();
            return;
        }

        // if diagonal is blocked, then try this
        if (absX >= absY) { // favour horizontal first
            if (xDiff < 0 && s[7]) {
                dx = -speed;
                dy = 0;
                realX += dx;
                return;
            }

            // if diagonal is blocked, then try moving horizontally or vertically
            if (absX >= absY) { // favour horizontal first
                if (xDiff < 0 && s[7]) {
                    dx = -speed;
                    dy = 0;
                    realX += dx;
                    return;
                }
                if (xDiff > 0 && s[3]) {
                    dx = speed;
                    dy = 0;
                    realX += dx;
                    return;
                }
                // horizontal blocked, try vertical
                if (yDiff < 0 && s[1]) {
                    dy = -speed;
                    dx = 0;
                    realY += dy;
                    return;
                }
                if (yDiff > 0 && s[5]) {
                    dy = speed;
                    dx = 0;
                    realY += dy;
                    return;
                }
            } else { // favour vertical first
                if (yDiff < 0 && s[1]) {
                    dy = -speed;
                    dx = 0;
                    realY += dy;
                    return;
                }
                if (yDiff > 0 && s[5]) {
                    dy = speed;
                    dx = 0;
                    realY += dy;
                    return;
                }
                // vertical blocked, try horizontal
                if (xDiff < 0 && s[7]) {
                    dx = -speed;
                    dy = 0;
                    realX += dx;
                    return;
                }
                if (xDiff > 0 && s[3]) {
                    dx = speed;
                    dy = 0;
                    realX += dx;
                    return;
                }
            }
        }

        // if the enemy and player are on the same axis, then it will side step
        // until moved past the obstruction

        if (xDiff > -32 && xDiff < 32)
            attemptingSideStepHorizontally = true; // if the player is within the magnitude of speed to the enemy on the
                                                   // x axis
        if (yDiff > -32 && yDiff < 32)
            attemptingSideStepVertically = true;

        // checks if the player can move horizontally/ vertically
        if (xDiff < 0)
            horizontallyBlocked = !s[7]; // W
        else
            horizontallyBlocked = !s[3]; // E

        if (yDiff > 0)
            verticallyBlocked = !s[5]; // S
        else
            verticallyBlocked = !s[1]; // N

        if (attemptingSideStepVertically && horizontallyBlocked) {
            if (!verticalSideSteppingCommenced) {
                if (Greenfoot.getRandomNumber(2) == 0) // randomly decides which direction to go
                    dy = -speed;
                else
                    dy = speed;
                dx = 0;
                verticalSideSteppingCommenced = true;
            }
            // if the path you're moving is also blocked, stop!
            if ((Math.signum(dy) < 0 && !s[1]) || (Math.signum(dy) > 0 && !s[5])) {
                attemptingSideStepVertically = false;
            }
            realY += dy;
            return;
        } else {
            attemptingSideStepVertically = false;
            verticalSideSteppingCommenced = false;
        }

        if (attemptingSideStepHorizontally && verticallyBlocked) {
            if (!horizontalSideSteppingCommenced) {
                if (Greenfoot.getRandomNumber(2) == 0) // randomly decides which direction to go
                    dx = -speed;
                else
                    dx = speed;
                dy = 0;
                horizontalSideSteppingCommenced = true;
            }
            if ((Math.signum(dx) < 0 && !s[7]) || (Math.signum(dx) > 0 && !s[3])) {
                attemptingSideStepHorizontally = false;
            }
            realX += dx;
            return;
        } else {
            attemptingSideStepHorizontally = false;
            horizontalSideSteppingCommenced = false;
        }
        dx = dy = 0; // dont move there's not a good route..
    }

    private void moveDirectlyTowardPlayer() {
        double xDiff = play.getX() - getX();
        double yDiff = play.getY() - getY();
        double distance = Math.hypot(xDiff, yDiff);
        int dist = this instanceof Ranged ? 300 : 50;
        if (distance <= distanceFromPlayer + dist) {
            inRange = true;
            return;
        }
        inRange = false;

        dx = (xDiff / distance) * speed; // unit vector times magnitude

        dy = (yDiff / distance) * speed;

        realX += dx;
        realY += dy;
    }

    private boolean simpleSideStep(boolean sameCol, boolean sameRow, boolean[] s, double speed) {
        if (sameCol) { // blocked north/south, so go E/W
            int[] picks = { 3, 7 }; // east, west
            int idx = picks[Greenfoot.getRandomNumber(2)];
            if (s[idx]) { // only move if that tile is passable
                dx = (idx == 3 ? speed : -speed);
                dy = 0;
                realX += dx;
                return true;
            }
        } else if (sameRow) { // blocked east/west, so go N/S
            int[] picks = { 1, 5 }; // north, south
            int idx = picks[Greenfoot.getRandomNumber(2)];
            if (s[idx]) {
                dy = (idx == 5 ? speed : -speed);
                dx = 0;
                realY += dy;
                return true;
            }
        }
        return false; // couldn’t move
    }

    /**
     * returns a boolean array of the surrounding tiles.
     * true means that the tile is passible,
     * false means that the tile is not.
     * index 0: tile enemy is on
     * index 1: tile north of enemy
     * index 2: tile north east of enemy
     * index 3: tile east of enemy
     * index 4: tile south east of enemy
     * index 5: tile south of enemy
     * index 6: tile south west of enemy
     * index 7: tile west of enemy
     * index 8: tile north west of enemy
     */
    public boolean[] checkSurroundingTiles() {
        boolean[] surroundingTiles = new boolean[9];
        int[][] offsets = {
                { 0, 0 },
                { 0, -32 },
                { 32, -32 },
                { 32, 0 },
                { 32, 32 },
                { 0, 32 },
                { -32, 32 },
                { -32, 0 },
                { -32, -32 }
        };

        for (int i = 0; i < offsets.length; i++) {
            Tile tile = (Tile) getOneObjectAtOffset(offsets[i][0], offsets[i][1], Tile.class);
            if (tile != null) {
                surroundingTiles[i] = tile.getIsPassable();
            } else {
                // if no tiles are there, it is also false
                surroundingTiles[i] = false;
            }
        }
        if (surroundingTiles[0] == false) {
            // moveOffUnPassableTile();
        }

        return surroundingTiles;
    }

    public void moveOffUnPassableTile() {
        while (((Tile) getOneObjectAtOffset(0, 0, Tile.class)).getIsPassable()) {
            setLocation(getX() - dx, getY() - dy);
            realX = getX();
            realY = getY();
        }
    }

    protected int getHealth() {
        return this.health;
    }

    public boolean isDead() {
        return dead;
    }

    public double getSpeed() {
        return speed;
    }

}
