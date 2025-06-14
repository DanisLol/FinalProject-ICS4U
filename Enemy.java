import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * description goes here
 * 
 * @author Zachary Zhao
 * @version 0.0.2
 */

public abstract class Enemy extends HurtableEntity
{
    protected Player play;
    protected boolean inRange;
    protected int cd = 0;
    protected int cooldown;
    private boolean isOld;
    protected double speed; //magnitude of movement
    protected double dx, dy; // directions
    protected int distanceFromPlayer; // the range at which the enemy will stop moving toward

    public Enemy (String sheetName, int largeSize) {
        // GreenfootImage img = new GreenfootImage(16, 16);
        // img.setColor(Color.RED);
        // img.fillRect(0, 0, 16, 16);
        //setImage(img);

        super(sheetName, largeSize);

        speed = 0.9;
    }

    public void act()
    {
        super.act();
        super.animate();
        attacking();
        pathFindTowardPlayer();
    }

    protected void addedToWorld(World w){
        super.addedToWorld(w);
        if(!isOld) {
            List<Player> players = w.getObjects(Player.class);
            if (!players.isEmpty()) {
                play = players.get(0);
            }
            isOld = true;
        }
    }

    protected void attacking(){
        cd++;
        /*
        if(cd%cooldown==0){
        attack();
        }
         */
    }

    public void pathFindTowardPlayer() {

        boolean[] s = checkSurroundingTiles(); // 0-8 as documented

        double xDiff = play.getX() - getX();
        double yDiff = play.getY() - getY();
        double distance = Math.hypot(xDiff, yDiff); 

        if(distance <= distanceFromPlayer) {
            dx = dy = 0; // dont move you're close enough
        }

        double absX   = Math.abs(xDiff);
        double absY   = Math.abs(yDiff);

        boolean canMoveDiagonally = false;
        if (xDiff < 0 && yDiff < 0)        // NW
            canMoveDiagonally = s[7] && s[1] && s[8];
        else if (xDiff > 0 && yDiff < 0)   // NE
            canMoveDiagonally = s[3] && s[1] && s[2];
        else if (xDiff > 0 && yDiff > 0)   // SE
            canMoveDiagonally = s[3] && s[5] && s[4];
        else if (xDiff < 0 && yDiff > 0)   // SW
            canMoveDiagonally = s[7] && s[5] && s[6];

        if (canMoveDiagonally) {           // clear diagonal tiles means normal vector move
            moveDirectlyTowardPlayer();
            return;
        }

        // if diagonal is blocked, then try this
        if (absX >= absY) {      // favour horizontal first
            if (xDiff < 0 && s[7]) { 
                dx = -speed; 
                dy = 0; 
                realX += dx; 
                return; 
            }
            if (xDiff > 0 && s[3]) { 
                dx =  speed; 
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
                dy =  speed; 
                dx = 0; 
                realY += dy; 
                return; 
            }
        } else {                 // favour vertical first
            if (yDiff < 0 && s[1]) { 
                dy = -speed; 
                dx = 0; 
                realY += dy; 
                return; 
            }
            if (yDiff > 0 && s[5]) { 
                dy =  speed; 
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
                dx =  speed; 
                dy = 0; 
                realX += dx; 
                return; 
            }
        }

        dx = dy = 0; // dont move there's not a good route..
    }

    private void moveDirectlyTowardPlayer() {
        double xDiff = play.getX() - getX();
        double yDiff = play.getY() - getY();
        double distance = Math.hypot(xDiff, yDiff); 

        if (distance <= distanceFromPlayer) return; // stop if within correct range

        dx = (xDiff / distance) * speed;
        dy = (yDiff / distance) * speed;

        realX += dx;
        realY += dy;
    }

    /** returns a boolean array of the surrounding tiles. 
     *  true means that the tile is passible,
     *  false means that the tile is not.
     *  index 0: tile enemy is on
     *  index 1: tile north of enemy
     *  index 2: tile north east of enemy
     *  index 3: tile east of enemy
     *  index 4: tile south east of enemy
     *  index 5: tile south of enemy
     *  index 6: tile south west of enemy
     *  index 7: tile west of enemy
     *  index 8: tile north west of enemy
     */
    public boolean[] checkSurroundingTiles() {
        boolean[] surroundingTiles = new boolean[9];
        int[][] offsets = {
                {0, 0},
                {0, -32},
                {32, -32},
                {32, 0},
                {32, 32},
                {0, 32},
                {-32, 32},
                {-32, 0},
                {-32, -32}
            };

        for(int i = 0; i < offsets.length; i++) {
            Tile tile = (Tile)getOneObjectAtOffset(offsets[i][0], offsets[i][1], Tile.class);
            if (tile != null) {
                surroundingTiles[i] = tile.getIsPassable();
            } else {
                //if no tiles are there, it is also false
                surroundingTiles[i] = false;
            }
        }

        return surroundingTiles;
    }

    protected abstract void attack();

    protected int getHealth(){
        return this.health;
    }
}
