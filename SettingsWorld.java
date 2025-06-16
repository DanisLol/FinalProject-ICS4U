import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SettingsWorld extends World
{
    private GreenfootImage background; 
    private Cursor cursor; 
    private Button next;
    private Button back; 
    private GreenfootImage image; 

    private static SettingsWorld.StatChooseImage playerSkin, difficultyLevel; 

    private static int playerSkinNumber, difficultyLevelNumber;  
    public SettingsWorld()
    {    
        super(1024, 768, 1); 
        background = new GreenfootImage("SettingsImage.png"); 
        setBackground(background); 
        cursor = new Cursor(false);
        addObject(cursor, 0, 0); 

        next = new Button(cursor, false); 
        addObject(next, 880,650);
        back = new Button(cursor, true);
        addObject(back, 140, 650); 

        playerSkinNumber = 0;
        playerSkin = new StatChooseImage(512,270,754,400, playerSkinNumber); 

        difficultyLevelNumber = 0;
        difficultyLevel = new StatChooseImage(512,270,754,650, difficultyLevelNumber); 
    }

    public void act()
    {   
        playerSkin.choose("benjamin_pose.png", "Melissa_pose.png", "Kevin_pose.png","Gojo_pose.png"); 
        difficultyLevel.choose("easy.png", "medium.png", "hard.png"); 

    }

    public void backWorld()
    {
    }

    /**
     * Go to the next Setting screen when next button is clicked
     * @return void
     */
    public void nextWorld()
    {
        /*
        //if the mouse clicks on the next button, it will take me to the next pages of the settings
        if (Greenfoot.mouseClicked(next))
        {
        //if the world has never been created before, create a new world
        if (world1 == null) {
        world1 = new SettingsWorldS1Stats(this);
        }
        //if it has been created before, go to the one that was created
        Greenfoot.setWorld(world1);
        }
         */ 
    }

    public static String getPlayerSkinImage()
    {
        return difficultyLevel.getChoosenImage(); 
    }
    
    public static String getDifficultiyLevelImage()
    {
        return playerSkin.getChoosenImage(); 
    }

    protected class StatChooseImage
    {
        private Button left; 
        private Button right;
        private int number; 
        private SettingsImage object; 
        private String choosenImage; 

        //several constructors used for different purposes within the world

        //used buttons
        public StatChooseImage(int objectLocationX, int leftLocationX, int rightLocationX, int locationY, int number)
        {
            this.number = number; 
            object = new SettingsImage(); 
            addObject(object, objectLocationX, locationY); 
            left = new Button(cursor, true); 
            right = new Button(cursor, false);
            addObject(left, leftLocationX, locationY); 
            addObject(right, rightLocationX, locationY); 
        }

        //used for image displays
        public StatChooseImage(int objectLocationX, int leftLocationX, int rightLocationX, int locationYButton, int locationYObject, int number)
        {
            this.number = number; 
            object = new SettingsImage(); 
            object.setImageFile("Ameila_run4.png"); 
            addObject(object, objectLocationX, locationYObject); 
            left = new Button(cursor, true); 
            right = new Button(cursor, false);
            addObject(left, leftLocationX, locationYButton); 
            addObject(right, rightLocationX, locationYButton); 
        }

        //used for selecting settings placement for 6 images
        protected void choose(String image1, String image2, String image3, String image4, String image5, String image6)
        {
            if (Greenfoot.mouseClicked(right))
            {
                number++;    
            }
            else if (Greenfoot.mouseClicked(left))
            {
                number--; 
            }
            if(number%6==0)
            {
                object.setImageFile(image1); 
                choosenImage = image1;
            }
            else if (number%6==1 || number%6==-1)
            {
                object.setImageFile(image2); 
                choosenImage = image2;
            }
            else if (number%6 == 2 || number%6==-2)
            {
                object.setImageFile(image3); 
                choosenImage = image3;
            }
            else if (number%6 == 3 || number%6==-3)
            {
                object.setImageFile(image4); 
                choosenImage = image4; 
            }
            else if (number%6 == 4 || number%6==-4)
            {
                object.setImageFile(image5); 
                choosenImage = image5;
            }
            else if (number%6 == 5 || number%6==-5)
            {
                object.setImageFile(image6); 
                choosenImage = image6;
            }
        }

        protected void choose(String image1, String image2, String image3)
        {
            if (Greenfoot.mouseClicked(right))
            {
                number++;    
            }
            else if (Greenfoot.mouseClicked(left))
            {
                number--; 
            }
            if(number%3==0)
            {
                object.setImageFile(image1, 300, 75); 
                choosenImage = image1;
            }
            else if (number%3==1 || number%3==-1)
            {
                object.setImageFile(image2, 300, 75); 
                choosenImage = image2;
            }
            else if (number%3 == 2 || number%3==-2)
            {
                object.setImageFile(image3, 300, 75); 
                choosenImage = image3;
            }
        }

        protected void choose(String image1, String image2, String image3, String image4)
        {
            if (Greenfoot.mouseClicked(right))
            {
                number++;    
            }
            else if (Greenfoot.mouseClicked(left))
            {
                number--; 
            }
            if(number%4==0)
            {
                object.setImageFile(image1); 
                choosenImage = image1;
            }
            else if (number%4==1 || number%4==-1)
            {
                object.setImageFile(image2); 
                choosenImage = image2;
            }
            else if (number%4 == 2 || number%4==-2)
            {
                object.setImageFile(image3); 
                choosenImage = image3;
            }
            else if(number%4 == 3||number%4 ==-3)
            {
                object.setImageFile(image4); 
                choosenImage = image4;
            }
        }

        protected int getNumber()
        {
            return number; 
        }

        protected String getChoosenImage()
        {
            return choosenImage; 
        }
    }
}
