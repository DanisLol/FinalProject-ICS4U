import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.StringTokenizer; 

/**
 *SettingsWorld allows user to set the character image and the difficulty level of the game.
 *Saves user's preferences. 
 *
 * @author Stephanie Xia
 * @version June 16, 2025
 */
public class SettingsWorld extends World
{
    private GreenfootImage background; 
    private GreenfootSound music;
    private Cursor cursor;
    private Button next;
    private Button back; 
    private GreenfootImage image; 

    private static SettingsWorld.StatChooseImage playerSkin, difficultyLevel; 

    private static int playerSkinNumber, difficultyLevelNumber;  

    //the creation user info object -- there is only one in the whole game, other classes would access it through getter/setter methods
    private static UserInfo user; 

    /**
     * SettingsWorld - constructor
     */

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

        //to make the user not return null
        user = UserInfo.getMyInfo();

        //the numbers will be set based on the previous user's perferences. the default number if there was no previous data is zero --> perfectly
        //aligns with the default number that will be passed in settings if reset
        //pass into first integer index for user info: 0
        playerSkinNumber = user.getInt(0);
        //pass into second integer index for user info: 1
        difficultyLevelNumber = user.getInt(1); 

        //objects created for the user image-choosing system in seperate class StatChooseImage
        playerSkin = new StatChooseImage(512,270,754,400, playerSkinNumber); 
        difficultyLevel = new StatChooseImage(512,270,754,650, difficultyLevelNumber); 

        music = new GreenfootSound("settings_music.mp3");
        music.setVolume(70);
        music.playLoop();
    }

    /**
     * music start playing if press run
     * @return void
     */
    public void started(){
        music.playLoop();
    }

    /**
     * music stop playing if press stop
     * @return void
     */
    public void stopped(){
        music.pause();
    }

    /**
     * Act 
     * @return void
     */
    public void act()
    {   
        //image chooices that will be choosen from passed in as paramaters
        playerSkin.choose("benjamin_pose.png", "melissa_pose.png", "kevin_pose.png","gojo_pose.png"); 
        difficultyLevel.choose("easy.png", "medium.png", "hard.png"); 

        backWorld();
        nextWorld();
    }

    /**
     *  going back the previous world
     *  @return void
     */
    public void backWorld()
    {   
        if (Greenfoot.mouseClicked(back))
        {
            music.stop();
            Greenfoot.setWorld(new InformationWorld());
        }
    }

    /**
     * advancing the user to game world
     * @return void
     */
    public void nextWorld()
    {
        if(UserInfo.isStorageAvailable())
        {
            user = UserInfo.getMyInfo();

            playerSkinNumber = playerSkin.getNumber(); 
            difficultyLevelNumber = difficultyLevel.getNumber();             

            user.setInt(0, playerSkinNumber); 
            user.setInt(1, difficultyLevelNumber); 
            user.store(); 

        }
        if (Greenfoot.mouseClicked(next)){
            music.stop();
            Greenfoot.setWorld(new MyWorld());
        }
    }

    /**
     * setter method for integer index of user info
     * @param   the numberical index
     * @param   the variable that is passed into that index
     * @return void
     */
    public static void setUserInfoInt(int index, int varible)
    {
        user.setInt(index, varible);
        user.store(); 
    }

    /**
     * getter method for integer index of user info
     * @param   the numberical index - used to indicate the location of the varible stored previously
     * @return  int
     */
    public static int getUserInfoInt(int index)
    {
        return user.getInt(index); 
    }

    /**
     * getter method for the image name of the player skin
     * @return  String
     */
    public static String getPlayerSkinImage()
    {
        StringTokenizer imageName;
        String imageBase;
        try {
            imageName = new StringTokenizer(playerSkin.getChoosenImage(), "_");
            imageBase = imageName.nextToken().toLowerCase();
        } catch (NullPointerException e) {
            imageBase = "benjamin";
        }
        return imageBase; 
    }

    /**
     * getter method for the image name of the difficulty level
     * @return int
     */
    public static int getDifficultiyLevelImage()
    {
        if(difficultyLevel.getChoosenImage().equals("easy.png"))
        {
            return 0;
        }
        else if (difficultyLevel.getChoosenImage().equals("medium.png"))
        {
            return 1;
        }
        else if (difficultyLevel.getChoosenImage().equals("medium.png")) 
        {
            return 2; 
        }
        return -1; 
    }

    /**
     * setter method for player skin number
     * @param x   the number that is set to the playerSkinNumber
     * @return void
     */
    public static void setPlayerSkinNumber(int x)
    {
        playerSkinNumber = x; 
    }

    /**
     * setter method for difficulty level number
     * @param x   the number that is set to the difficultyLevelNumber
     * @return void
     */

    public static void setDifficultyLevelNumber(int x)
    {
        difficultyLevelNumber = x; 
    }

    /**
     * access method to store the information passed into user info
     * @return void
     */
    public static void storeInfo()
    {
        user.store(); 
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

        //used for image displays with precise x, y location of object and x,y location of buttons
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

        //used for selecting settings placement for 3 images
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

        //used for selecting settings placement for 4 images
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

        //getter method for the specific module number assoicated with each image
        protected int getNumber()
        {
            return number; 
        }

        //getter method for the file name of the image choosen
        protected String getChoosenImage()
        {
            return choosenImage; 
        }
    }
}
