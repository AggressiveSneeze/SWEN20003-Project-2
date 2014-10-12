/* SWEN20003 Object Oriented Software Development Project 1
 * RPG Game Engine
 * Author: <James Adams> <adj> <Student no: 572541>
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;



/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	private TiledMap map;
	private Camera cam;
	private Player player;
    static final private int layer_index=0;
    static final private int tiles_across=13;
    static final private int tiles_down=10;
    
    //items
    private AmuletOfVitality amulet;
    private SwordOfStrength sword;
    private TomeOfAgility tome;
    private ElixirOfLife elixir;
    //villagers
    private PrinceAldric prince_aldric;
    private Elvira elvira;
    private Garth garth;
    //monsters
    private Draelic draelic;
    private String monster[];
    
    Vector<Item> items = new Vector<Item>(5);
    Vector<Villager> villagers = new Vector<Villager>(5);
    
    Vector<Zombie> zombies = new Vector<Zombie>(50);
    Vector<GiantBat> bats= new Vector<GiantBat>(50);
    Vector<Bandit> bandits = new Vector<Bandit>(50);
    Vector<Skeleton> skeletons=new Vector<Skeleton>(50);
    Vector<AggressiveMonster> aggressive_monsters=new Vector<AggressiveMonster>(200);
    Vector<PassiveMonster> passive_monsters=new Vector<PassiveMonster>(200);
    Vector<Monster> monsters=new Vector<Monster>(200);
    
    //Vector<Vector<Object>> renderables= new Vector<Vector<Object>>(15);
    private Zombie temp_zombie;
    private GiantBat temp_bat;
    private Bandit temp_bandit;
    private Skeleton temp_skeleton;
	
	

    /** Create a new World object. 
     * @throws IOException */
    protected World()
    throws SlickException, IOException
    {
    	map=new TiledMap("assets/map.tmx", "assets");
    	// hero=new Image("assets/units/player.png");
    	player= new Player(756,684);
    	//deal with items
    	amulet=new AmuletOfVitality(965,3563);
    	//item in spawn spot
    	//amulet=new AmuletOfVitality(900,684);
    	sword= new SwordOfStrength(4791,1253);
    	tome= new TomeOfAgility(546,6707);
    	elixir=new ElixirOfLife(1976,402);
    	items.addElement(amulet);
    	items.addElement(sword);
    	items.addElement(tome);
    	items.addElement(elixir);
    	
    	//deal with villagers
    	prince_aldric= new PrinceAldric(467,679);
    	elvira=new Elvira(738,549);
    	garth=new Garth(756,870);
    	villagers.addElement(prince_aldric);
    	villagers.addElement(elvira);
    	villagers.addElement(garth);
    	
    	//deal with monsters
    	List<String> lines = Files.readAllLines(Paths.get("assets/unitsloc.txt"), Charset.defaultCharset());
    	String monster_name;
    	int monster_xPos, monster_yPos;
    	for(String potato:lines) {
    		monster=potato.split(",");
    		monster[1]=monster[1].trim();
    		monster[2]=monster[2].trim();
    		monster_name=monster[0];
    		monster_xPos=Integer.parseInt(monster[1]);
    		//System.out.println("xPos of monster is"+ monster_xPos);
    		monster_yPos=Integer.parseInt(monster[2]);
    		switch(monster_name) {
    		case " Bat":
    			temp_bat= new GiantBat(monster_xPos,monster_yPos);
    			bats.addElement(temp_bat);
    			passive_monsters.addElement(temp_bat);
    			monsters.addElement(temp_bat);
    			break;
    		case "Zombie":
    			temp_zombie=new Zombie(monster_xPos,monster_yPos);
    			zombies.addElement(temp_zombie);
    			aggressive_monsters.addElement(temp_zombie);
    			monsters.addElement(temp_zombie);
    			break;
    		case "Bandit":
    			temp_bandit=new Bandit(monster_xPos,monster_yPos);
    			bandits.addElement(temp_bandit);
    			aggressive_monsters.addElement(temp_bandit);
    			monsters.addElement(temp_bandit);
    			break;
    		case "Skeleton":
    			temp_skeleton=new Skeleton(monster_xPos,monster_yPos);
    			skeletons.addElement(temp_skeleton);
    			aggressive_monsters.addElement(temp_skeleton);
    			monsters.addElement(temp_skeleton);
    			break;
    		case "Drealic":
    			draelic=new Draelic(monster_xPos,monster_yPos);
    			aggressive_monsters.addElement(draelic);
    			monsters.addElement(draelic);
    			System.out.println("Found a draelic!");
    			break;
    		}
    	}
    	//renderables.addElement(monsters);
    	//renderables.addElement(items);
    	//renderables.addElement(villagers);
    	
    	
    		
    	cam=new Camera(player);
    	
        // TODO: Fill in
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
   
    protected void update(double dir_x, double dir_y, double dir_A, double dir_T, int delta)
    throws SlickException
    {
    	
    	// update player position, subsequently camera position of course (camera follows player)
        player.update((float)(dir_x), (float)(dir_y),delta,this);
        cam.update();
        
   
        //player attacking monster
        for(Monster monster: monsters) {
        	float delta_x=player.getxPos()-monster.getxPos();
    		float delta_y=player.getyPos()-monster.getyPos();
    		//updates the monster's movement.
        	monster.update(this, player,delta);
        	
        	if((dir_A>0) && (Math.sqrt(delta_x*delta_x+delta_y*delta_y)<=50)) {
        			player.attack(monster);
        	}
        }
        for(Item item: items) {
        	float delta_x=player.getxPos()-item.getxPos();
        	float delta_y=player.getyPos()-item.getyPos();
        	if(item.isPicked_up()==false && (Math.sqrt(delta_x*delta_x+delta_y*delta_y)<=50)) {
        		player.add_to_inventory(item);
        		item.setPicked_up(true);
        		item.pickUp(player);
        	}
        	
        }
        
        for (Villager villager: villagers) {
        	villager.update(player, dir_T,delta);
        }
        
        
    }

    /** Render the entire screen, so it reflects the current game state, and draws the player
     * on to the center of the map.
     * @param g The Slick graphics object, used for drawing.
     */
    protected void render(Graphics g)
    throws SlickException
    {
    	//pixels must be negative
    	//tiles must be positive
    	map.render(-cam.getMinX()%map.getTileWidth(),-cam.getMinY()%map.getTileHeight(), 
    			cam.getMinX()/map.getTileWidth(),cam.getMinY()/map.getTileHeight(), tiles_across,tiles_down );
    	//System.out.println(cam.getMinX() + " " + cam.getMinY());
    	//amulet.render(cam);
    	player.render(g);
    	//render monsters,villagers,items
    	for (Monster monster: monsters){
    		if (on_map(monster) && monster.isAlive()==true) monster.render(cam, g);
    	}
    	for(Villager villager:villagers){
    		if (on_map(villager)) villager.render(cam,g);
    	}
    	for(Item item:items){
    		if (on_map(item) && item.isPicked_up()==false) item.render(cam,g);
    	}
    	
    	
    	//Fix to show earlier, i.e when bottom hits 
    	
    	UI.renderPanel(player,g);
  
    	/**prints some co-ordinate on map for debug purposes
    	g.drawString("Player Position = (" + player.getxPos() + "," + player.getyPos()+ ")", 100, 100);
    	g.drawString("Cam Position = ("+cam.getxPos()+ "," + cam.getyPos()+ ")", 100,200);
    	g.drawString("Min Cam Position = ("+cam.getMinX()+ "," + cam.getMinY()+ ")", 100,300);
    	g.drawString("Max Cam Position = ("+cam.getMaxX()+ "," + cam.getMaxY()+ ")", 100,400);
    	**/
    }
    
    /**Checks whether a specific co-ordinate in pixels is blocked on the map. returns:
    true for unblocked, false for blocked 
    */
    
    
   public boolean block(float xPos,float yPos) {
    	int tile_id;
    	String block;
    	tile_id=map.getTileId((int)(xPos/map.getTileWidth()),(int)(yPos/map.getTileHeight()), layer_index);
    	block=map.getTileProperty(tile_id, "block", "0");
    	if (block=="0") {
    		//success or not blocked
    		return false;
    	}
    	if (block=="1") {
    		//blocked
    		return true;
    	}
    	return true;
    }
   
   private boolean on_map(GameObject object) {
	   if (object.getxPos()<=cam.getMaxX() &&
				object.getxPos()>=cam.getMinX() &&
				object.getyPos()<=cam.getMaxY() &&
				object.getyPos()>=cam.getMinY()) return true;
	   return false;
	   
   }
   
  public static int randInt(int min, int max, Random rand ) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
   
   
    	
    	
}
