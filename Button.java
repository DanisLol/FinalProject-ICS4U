import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Buttons are images that change appearance when the cursor hovers over them
 * and may do things when clicked. To use them, check if the Button has been clicked and
 * do whatever function needed.
 * 
 * @author Angela Wang, Stephanie Xia
 * @version April 2025
 */
public class Button extends Actor
{
    private GreenfootImage image, hoverImage;
    private GreenfootSound[] clicks;
    private String text;
    private Font font = new Font(40);
    private Cursor cursor;
    private int curIndex;

    /**
     * Button constructor - pass user cursor and direction of button (for image setting purposes). 
     * We are aware that the L/R issue could have been solved by flipping the image, but 
     * were too short on time to fix. 
     * 
     * @param cursor    User's cursor, as created by the World the Button is in
     * @param isLeft    Boolean: if true, button is left-facing (used to determine which button image)
     */
    public Button(Cursor cursor, boolean isLeft){

        if (isLeft == true)
        {
            image = new GreenfootImage ("SettingsArrowL.png"); 
            setImage(image);
            hoverImage = new GreenfootImage("SettingsArrowHoverL.png"); 
        }
        else 
        {
            image = new GreenfootImage ("SettingsArrowR.png"); 
            setImage(image);
            hoverImage = new GreenfootImage("SettingsArrowHoverR.png"); 
        }
        this.cursor = cursor;

    }

    public Button(Cursor cursor, boolean isShopWorld, int price){
        if (price == 10)
        {
            image = new GreenfootImage ("UpgradeButton10.png"); 
            setImage(image);
            hoverImage = new GreenfootImage("UpgradeButtonPD10.png");
            image.scale(230,76);
            hoverImage.scale(230,76);
        }
        else if(price == 20)
        {
            image = new GreenfootImage ("UpgradeButton20.png"); 
            setImage(image);
            hoverImage = new GreenfootImage("UpgradeButtonPD20.png");
            image.scale(230,76);
            hoverImage.scale(230,76);
        }
        else
        {
            image = new GreenfootImage ("BuyButton15.png"); 
            setImage(image);
            hoverImage = new GreenfootImage("BuyButtonPD15.png");
            image.scale(190,60);
            hoverImage.scale(190,60);
        }

        this.cursor = cursor;
        //array of click sounds --> can click quickly
        /*
        clicks = new GreenfootSound[5];
        for (int i = 0; i < 5; i++){
        clicks[i] = new GreenfootSound("click.wav");
        }

        curIndex = 0;
         */

    }

    /**
     * Act checks for if the cursor is hovering above the button and changes the image if yes.
     * If the button is clicked, a click sound is played.
     * @return void
     */
    public void act()
    {
        //if the cursor is hovering over the button, change the image
        if (cursor.getHoveredActors().contains(this)){
            setImage(hoverImage);

            //if button is clicked, play sound
            if (Greenfoot.mouseClicked(this)){
                /*
                curIndex++;
                //if current index exceeds maximum possible index, reset to 0
                if (curIndex > clicks.length - 1){
                curIndex = 0;
                }
                clicks[curIndex].play();
                 */
            }
        } else {
            //if not hovered over, normal image
            setImage(image);
        }
    }

}