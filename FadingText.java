import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Text that can either fade in and out or stay on screen.
 * 
 */
public class FadingText extends Actor
{
    private GreenfootImage image;
    private static final Color transparent = new Color(0, 0, 0, 0);
    private static final Color textColor = Color.WHITE;
    private int transparency, maxTransparency, actsLeft, diffPerAct, actsDisplayed, actsToDisplay;
    private static final int ACTS_TO_FADE = 50;
    private boolean fade, isFadingIn, isFadingOut;

    public FadingText(String text, boolean fade, int actsToDisplay){
        image = new GreenfootImage(text, 20, textColor, transparent);
        setImage(image);
        
        actsLeft = ACTS_TO_FADE;
        actsDisplayed = 0;
        
        diffPerAct = (int) 255 / ACTS_TO_FADE;
        maxTransparency = diffPerAct * ACTS_TO_FADE;
        
        this.fade = fade;
        this.actsToDisplay = actsToDisplay;
        
        //if fading in and out, start from transparent image
        if (fade == true){
            transparency = 0;
            image.setTransparency(transparency);
            isFadingIn = true;
            isFadingOut = false;
        }
    }

    public void act(){
        if (fade == true){
            if (isFadingIn){
                fadeIn();
            } else {
                //after faded in: increase acts displayed until text has been displayed for actsToDisplay, then fade out
                actsDisplayed++;
                if (actsDisplayed >= actsToDisplay){
                    fadeOut();
                }
            }
        }
        //else do nothing
    }

    private void fadeIn(){
        //increase transparency by diffPerAct
        actsLeft--;
        if (actsLeft > 0){
            transparency += diffPerAct;
            image.setTransparency(transparency);
            setImage(image);
        } else {
            //reset actsLeft to fade out
            isFadingIn = false;
            actsLeft = ACTS_TO_FADE;
        }
    }

    private void fadeOut(){
        //decrease transparency by diffPerAct
        actsLeft--;
        if (actsLeft > 0){
            transparency -= diffPerAct;
            image.setTransparency(transparency);
            setImage(image);
        } else {
            getWorld().removeObject(this);
        }
    }
}
