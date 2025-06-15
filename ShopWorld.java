import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopWorld extends World
{
    private GreenfootImage background;
    private Player player; 
    private int damageToEnemy;
    private int damageIntake;
    private int health; 
    private int upgradeCostDamageToEnemy, upgradeCostDamageIntake, upgradeCostHealth;
    private ImageDisplay damageToEnemyDisplay, damageIntakeDisplay, healthDisplay;

    private Button upgradeArmor, upgradeWeapon,upgradeHealth, buyChestbox; 
    private Cursor cursor;

    private ImageDisplay damageToEnemyStats, damageIntakeStats, healthStats; 
    private ImageDisplay armorImage, weaponImage, healthImage, chestboxImage; 
    private ImageDisplay coinsDisplay;
    private int coins;

    private ImageDisplay speedBoost,invisibility, randomBoost; 

    private ImageDisplay upgradePopUp, deletePopUp, chestboxPopUp;

    public ShopWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1);

        cursor = new Cursor(false);
        addObject(cursor, 0, 0); 

        damageToEnemy = 20;
        damageIntake= 20;
        health=10;

        upgradeCostDamageToEnemy = 20;
        upgradeCostDamageIntake = 20;
        upgradeCostHealth = 20; 
        background = new GreenfootImage("ShopDesign.png");

        setBackground(background);
        player = new Player();
        addObject(player, 820, 560); 
        player.getImage().scale(80,90);

        damageToEnemyDisplay = new ImageDisplay(upgradeCostDamageToEnemy, 30); 
        damageIntakeDisplay = new ImageDisplay(upgradeCostDamageIntake, 30);
        healthDisplay = new ImageDisplay (upgradeCostHealth, 30);

        //addObject(damageToEnemyDisplay, 320, 465); 
        //addObject(damageIntakeDisplay, 617, 465);
        //addObject(healthDisplay, 900, 405); 

        upgradeArmor = new Button (cursor, true, 20); 
        addObject(upgradeArmor, 245, 470); 

        upgradeWeapon = new Button (cursor, true, 20);
        addObject(upgradeWeapon, 537, 470);

        upgradeHealth = new Button (cursor, true, 20);
        addObject(upgradeHealth, 826, 410); 

        buyChestbox = new Button (cursor, true, 15); 
        addObject(buyChestbox, 578, 585);

        damageToEnemyStats = new ImageDisplay("Damage to Enemy: " + damageToEnemy, 22); 
        damageIntakeStats = new ImageDisplay("Damage Intake Stats: " + damageIntake, 20); 
        healthStats = new ImageDisplay("Health Stats: " + health, 22); 

        addObject(damageToEnemyStats, 824, 620); 
        addObject(damageIntakeStats, 825, 650);
        addObject(healthStats, 798, 679); 

        armorImage = new ImageDisplay("armor.png"); 
        weaponImage = new ImageDisplay("damage.png");
        healthImage = new ImageDisplay("health.png", 64, 64);
        chestboxImage = new ImageDisplay("chestbox.png", 100, 100);

        addObject(armorImage, 250, 375); 
        addObject(weaponImage, 550, 375);
        addObject(healthImage, 830, 337); 
        addObject(chestboxImage, 190, 645);

        coins = 1000;
        coinsDisplay = new ImageDisplay ("$" + coins, 40);
        addObject(coinsDisplay, 925, 150);

        speedBoost = new ImageDisplay("Speed Boost ", 20); 
        invisibility = new ImageDisplay("Invisibility ", 20); 
        randomBoost = new ImageDisplay("Random Boost", 20); 

        addObject(speedBoost, 391, 613);
        addObject(invisibility, 384, 648); 
        addObject(randomBoost, 395, 682);

        deletePopUp = new ImageDisplay ("deletePopup.png", 50, 50);
        upgradePopUp = new ImageDisplay ("popup.png", 500, 340); 

        setPaintOrder(ImageDisplay.class, Button.class); 
    }

    public void act()
    {
        addObject(player, 820, 560); 
        player.animate(); 
        checkPurchase();
        removePopUp(); 
    }

    public void removePopUp()
    {
        if(Greenfoot.mouseClicked(deletePopUp))
        {
            if(getObjects(ImageDisplay.class).contains(upgradePopUp))
            {
                removeObject(upgradePopUp); 
            }
            else
            {
                removeObject(chestboxPopUp); 
            }

            removeObject(upgradePopUp); 
            removeObject(deletePopUp);
        }
    }

    public void checkPurchase()
    {
        if(Greenfoot.mouseClicked(upgradeArmor) ||Greenfoot.mouseClicked(upgradeWeapon) || Greenfoot.mouseClicked(upgradeHealth))
        {
            addObject(upgradePopUp, 500, 455);
            addObject(deletePopUp, 740, 295); 
        }
        else if (Greenfoot.mouseClicked(buyChestbox))
        {
            int i = Greenfoot.getRandomNumber(2);
            String fileName; 
            if (i == 0)
            {
                fileName = "speedPopup.png";
            }
            else if (i==1)
            {
                fileName = "invisibilityPopup.png";
            }
            else 
            {
                fileName = "invisibilityPopup.png";
            }

            chestboxPopUp = new ImageDisplay(fileName, 500, 340); 
            addObject (chestboxPopUp , 500, 455);
            addObject(deletePopUp, 740, 295); 

        }
    }
}