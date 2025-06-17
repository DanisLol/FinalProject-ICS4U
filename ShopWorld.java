import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ShopWorld -appears right before final boss level- allows users to buy buffs to heal, strengthen or better protect themselves, including armor, 
 * weapon, health, invincibility, speed.
 * 
 * @Stephanie Xia
 * @June 16, 2025
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

    private static int potionCount;

    private Button upgradeArmor, upgradeWeapon,upgradeHealth, buyChestbox; 

    private Button next; 
    private Cursor cursor;

    private ImageDisplay damageToEnemyStats, damageIntakeStats, healthStats; 
    private ImageDisplay armorImage, weaponImage, healthImage, chestboxImage; 
    private ImageDisplay coinsDisplay;
    private int coins;

    private ImageDisplay speedBoost,invisibility, randomBoost; 
    private ImageDisplay upgradePopUp, deletePopUp, chestboxPopUp;

    private ImageDisplay text; 

    public ShopWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1);

        cursor = new Cursor(false);
        addObject(cursor, 0, 0); 

        upgradeCostDamageToEnemy = 20;
        upgradeCostDamageIntake = 20;
        upgradeCostHealth = 20; 
        background = new GreenfootImage("ShopDesign.png");

        setBackground(background);
        player = new Player();
        addObject(player, 820, 560); 
        player.getImage().scale(80,90);
        damageToEnemy = player.getWeaponDmg();
        damageIntake= 20;
        health = player.getHealth();

        damageToEnemy = player.getDmg();
        damageIntake= player.getDmg();
        health = player.getHealth();

        damageToEnemyDisplay = new ImageDisplay(upgradeCostDamageToEnemy, 30); 
        damageIntakeDisplay = new ImageDisplay(upgradeCostDamageIntake, 30);
        healthDisplay = new ImageDisplay (upgradeCostHealth, 30);

        upgradeArmor = new Button (cursor, 20); 
        addObject(upgradeArmor, 245, 470); 

        upgradeWeapon = new Button (cursor, 20);
        addObject(upgradeWeapon, 537, 470);

        upgradeHealth = new Button (cursor, 20);
        addObject(upgradeHealth, 826, 410); 

        buyChestbox = new Button (cursor, 15); 
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

        coins = player.getCoin();
        coinsDisplay = new ImageDisplay ("$" + coins, 40);
        addObject(coinsDisplay, 925, 150);

        speedBoost = new ImageDisplay("Speed Boost ", 20); 
        invisibility = new ImageDisplay("Invincibility ", 20); 
        randomBoost = new ImageDisplay("Bomb", 20); 

        addObject(speedBoost, 391, 613);
        addObject(invisibility, 386, 648); 
        addObject(randomBoost, 360, 682);

        deletePopUp = new ImageDisplay ("deletePopup.png", 50, 50);
        upgradePopUp = new ImageDisplay ("popup.png", 500, 340); 

        cursor = new Cursor(false);
        addObject(cursor, 0, 0);

        next = new Button(cursor, false); 
        addObject(next, 985,685);

        text = new ImageDisplay ("Next...", 22, Color.WHITE); 
        addObject(text, 979,680); 

        setPaintOrder(ImageDisplay.class, Button.class); 
    }

    public void act()
    {
        addObject(player, 820, 560); 
        checkPurchase();
        removePopUp(); 
        purchase();
        nextWorld(); 
    }

    public static int getPotionCount()
    {
        return potionCount; 
    }

    public void nextWorld()
    {
        if(UserInfo.isStorageAvailable())
        {
            SettingsWorld.setUserInfoInt(4, potionCount); 
        }
        if (Greenfoot.mouseClicked(next)){
            Greenfoot.setWorld(new MyWorld());
        }
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
            addObject(deletePopUp, 740, 300); 
            updateStats();
        }
        else if (Greenfoot.mouseClicked(buyChestbox))
        {
            int i = Greenfoot.getRandomNumber(3);
            String fileName = "placeholder"; 
            if (i == 0)
            {
                fileName = "speedPopup.png";
                player.setSpeed(13); 
            }
            else if (i==1)
            {
                fileName = "invincibilityPopup.png";
                potionCount++; 
            }
            else if(i==2)
            {
                fileName = "bomb.png";
                player.setHealth(10); 
            }
            chestboxPopUp = new ImageDisplay(fileName, 500, 340); 
        }
    }

    public void purchase(){
        if (Greenfoot.mouseClicked(upgradeArmor)) {
            if (coins >= upgradeCostDamageIntake) {
                coins -= upgradeCostDamageIntake;
                //change the damage value that enemy gives to the player
                for (Object obj : getObjects(Enemy.class)) {
                    Enemy enemy = (Enemy) obj;
                    enemy.changeDmg();
                }
                damageToEnemy +=5; 
                updateStats();                                                                                                                                                              
            }
        }
        else if (Greenfoot.mouseClicked(upgradeWeapon)) {
            if (coins >= upgradeCostDamageToEnemy) {
                coins -= upgradeCostDamageToEnemy;
                player.increaseDmg(); // Add method to Player class
                damageIntake += 5; 
                updateStats();
            }
        }
        else if (Greenfoot.mouseClicked(upgradeHealth)) {
            if (coins >= upgradeCostHealth) {
                coins -= upgradeCostHealth;
                player.setHealth(100); // Add method to Player class
                health= 100; 
                updateStats();
            }
        }
        else if (Greenfoot.mouseClicked(buyChestbox)) {
            int cost = 15;
            if (coins >= cost) {
                coins -= cost;
                int i = Greenfoot.getRandomNumber(2);
                String fileName = (i == 0) ? "speedPopup.png" : (i == 1) ? "invisibilityPopup.png" : "bomb.png";                
                chestboxPopUp = new ImageDisplay(fileName, 500, 340); 
                addObject (chestboxPopUp , 500, 455);
                addObject(deletePopUp, 740, 300); 
            }
        }
    }

    public void updateStats() {
        coinsDisplay = new ImageDisplay("$" + coins, 40);
        damageToEnemyStats = new ImageDisplay("Damage to Enemy: " + damageToEnemy, 22); 
        damageIntakeStats = new ImageDisplay("Damage Intake Stats: " + damageIntake, 20); 
        healthStats = new ImageDisplay("Health Stats: " + health, 22); 
    }
}