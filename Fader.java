import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Transition between worlds. Taken from CatCollectors by Angela Wang
 * 
 * @author Angela Wang
 * @version January 2025
 * Fade in = 0 to opaque
 * Fade out = opaque to 0
 */
public class Fader extends Actor
{
    private GreenfootImage image;
    private static final int ACTS_TO_FADE = 60;
    private int transparency;
    private int diffPerAct;
    private String type;
    private World newWorld;

    /**
     * Construtor for Fader - pass in String for in/out fader and new World
     * @param type      "in" = fade from 0 to opaque, "out" = fade from opaque to 0
     * @param newWorld      World to transition to
     */
    public Fader(String type, World newWorld){
        this.type = type;
        this.newWorld = newWorld;

        diffPerAct = (int) 255 / ACTS_TO_FADE;

        if (type.equals("in")){
            transparency = 0;
        } else if (type.equals("out")){
            transparency = diffPerAct * ACTS_TO_FADE; //sometimes transparency isn't perfectly divisible by acts to fade (no double transparency)
        }
    }
    
    public void addedToWorld(World w){
        drawImage();
    }

    /**
     * Act - do whatever the Fader wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (type.equals("in")){
            fadeIn();
        } else if (type.equals("out")){
            fadeOut();
        }
    }

    //draw black image
    private void drawImage(){
        image = new GreenfootImage(getWorld().getWidth(), getWorld().getHeight());
        image.setColor(Color.BLACK);
        image.fill();
        image.setTransparency(transparency);
        setImage(image);
    }

    private void fadeIn(){
        //increase transparency by diffPerAct each act until it hits max transparency, then set world to new world
        if (transparency <= diffPerAct * ACTS_TO_FADE - diffPerAct){
            transparency += diffPerAct;
            image.setTransparency(transparency);
            setImage(image);
        } else {
            if (newWorld != null){
                if (getWorld() instanceof MyWorld){
                    //cheap
                    MyWorld.GAME_MUSIC.stop();
                }
                Greenfoot.setWorld(newWorld);
            }
        }
    }

    private void fadeOut(){
        //decrease transparency by diffPerAct until transparent
        if (transparency >= diffPerAct){
            transparency -= diffPerAct;
            image.setTransparency(transparency);
            setImage(image);
        } else {
            getWorld().removeObject(this);
            return;
        }
    }

}
