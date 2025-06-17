------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Escaping ICS4U
AUTHORS: Angela Wang, Ethan Ren, Daniel Wang, Stephanie Xia, Zachary Zhao
PURPOSE OF PROJECT: Fun dungeon game that reflects the journey of our grade 12 computer science class!
VERSION or DATE: April 2025
HOW TO START THIS PROJECT: Open in Greenfoot, instantiate new StartingWorld() and click Run!
AUTHORS: Angela Wang, Ethan Ren, Daniel Wang, Stephanie Xia, Zachary Zhao
USER INSTRUCTIONS: 

==============DETAILS!==============

You wake up in an unfamiliar setting with a weapon in your hand. Where are you, and who brought you there? You will soon find out...


==============MOVEMENT AND ATTACK==============
WASD to move
Click to attack

==============SETTING INSTRUCTIONS==============

The user has the freedom to choose one of the four character skins. Depending on their comfortability, the user can choose between easy, medium 
and hard mode, with increasing difficulty to defeat the enemies while staying alive!


==============MAIN FEATURES==============

WORLDS:

- MyWorld: the game world that the player will play in - consist of 3 different game rooms which are hidden until the user discovers them. 
The camera view of the world moves with the player's movements, allowing players to see what comes next only by advancing. 
- SettingsWorld: The settings menu - control the difficulty level and player skin
- ShopWorld: With the coins the user collects after each game - the user can buy buffs (weapon, armor, health upgrade + mystery chest box) in
our shop

- Scrolling world (hopefully your new computer's CPU can handle the lack of optimization because at least one MacBook gave up on this)

- Tile system uses a 2D array, which then uses a HashMap to convert from characters to Tiles

- UserInfo saves user's coin info 

- Own enemy pathfinding algorithm

===========KNOWN BUGS===========
- sound files may error even after exporting from audacity
- IF YOU MOVE BACK FROM AN OPEN GATE ENTRY TILE YOU WILL GET LOCKED OUT OF THE ROOM. 
- similarly, if you step on a gate tile and move to another gate tile, you will get locked inside of the gate tile.
- gate opening/locking may otherwise error
- not really a bug but please wait until fade effects have completely finished before clicking on buttons or pressing enter to restart
- also not really a bug? but enemy animations are directionless and consequently may look odd
- ALSO not a bug but note that red health bars represent the player's health while the green health bar represents the boss's health

==========CREDITS========
- Tiles except for water, portal and fire drawn by Angela Wang
- Portal, water tiles drawn by Freeman Wang
- Player, enemy graphics from LPC spritesheets
- https://www.chosic.com/download-audio/29634/
- https://www.chosic.com/download-audio/58934/ 
- https://www.pinterest.com/pin/pixel-art-bow-arrows-and-quiver--3940718415727168/
- https://dribbble.com/shots/2040007-The-Dungeons
- https://www.youtube.com/watch?v=-lrkFliNH4Y
- https://www.youtube.com/watch?v=r91qO3XthVw
- https://www.chosic.com/download-audio/28515/ here 
- www.chosic.com/download-audio/27018/  
- https://www.chosic.com/download-audio/29653/ 
- https://www.pexels.com/photo/blue-skies-53594/ 
- https://www.chosic.com/download-audio/58930/ 
- https://www.chosic.com/download-audio/27018/
- https://www.freepik.com/premium-vector/barrel-pixel-art_22093697.htm
- https://www.pixilart.com/art/lava-tile-e3eac80bb085dec
- https://www.istockphoto.com/vector/set-of-8-bit-pixel-graphics-icons-isolated-vector-illustration-game-art-coins-of-gm1787774093-547487398


