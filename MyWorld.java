import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{    
    private Random random = new Random();
    private String[] testLayouts = {"uuuuuugggguuuuuuugggggggggggggguugwwwwggggffffguugwggggggggggfgugggggggbbgggggggggbbggbuubggbbggggbbggbuubggbbgggggggggbbgggggggugfggggggggggwguugffffggggwwwwguugggggggggggggguuuuuuugggguuuuuu", 
                                    "uuuuuugggguuuuuuugggggggggggggguugbbbgguuggbbbguugbbbgguuggbbbguggggggggggggggggggwwwwwwggggggggggggggggwwwwwwggggggggggggggggggugbbbgguuggbbbguugbbbgguuggbbbguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuggggggbbggggggugggggggbbggggggggggbbbbbbbbbbggggggbbbbbbbbbbggggggggggbbggggggguggggggbbgggggguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugguugggggguugguggguugggggguugggggggggggggggggggggggggggggggggggggguugggggguugggugguugggggguugguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuuuubbbuugggggguggggggguugggggggggggggguugggggggggggggguugggggggggggggguugggggggugggggguubbbuuuuugggggggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugguuwwwwwwuugguggggfggggggfggggggggfggggggfggggggggfggggggfggggggggfggggggfggggugguuwwwwwwuugguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu", 
                                    "uuuuuugggguuuuuuubbbbbggggbbbbbuubbbbbggggbbbbbuubbbbbggggbbbbbuggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggubbbbbggggbbbbbuubbbbbggggbbbbbuubbbbbggggbbbbbuuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguugggguuuuuugggguggggguggggggggggggggguggggggggggggggguuuugggggggggggguggggggggggugggguggggggggguugggguggggggggguugggggggggggggguuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuuwwwwwggggwwwwwuuwggggggggggggwuuwgggbbbbbbgggwugggggbbbbbbggggggggggbbuubbggggggggggbbuubbggggggggggbbbbbbggggguwgggbbbbbbgggwuuwggggggggggggwuuwwwwwggggwwwwwuuuuuuugggguuuuuu",
                                    "uuuuuugggguuuuuuugggggggggggggguugggggggggggggguuggggbbggbbggggugggggbuggubgggggggggggguugggggggggggggguuggggggggggggbuggubggggguggggbbggbbgggguugggggggggggggguugggggggggggggguuuuuuugggguuuuuu"};
    private Player player;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 
        player = new Player();
        addObject(player, getWidth()/2, getHeight()/2);
        Board board = new Board(testLayouts[1]);//random.nextInt(testLayouts.length)
        addObject(board, 0, 0);
        board.display();
        
        setPaintOrder(Player.class);
    }

    public void act(){
    }
    
    public Player getPlayer() {
        return player;
    }
}
