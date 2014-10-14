import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Player extends Unit {
	private static final String PLAYER_IMAGE_LOC = "assets/units/player.png";
	private Vector<Item> inventory =new Vector<Item>(5);
	static final private float speed=1f;
	private static final int initial_HP=100;
	private static final int initial_damage=26;
	private static final int initial_cooldown=600;
	private float change_x;
	private float change_y;
    private int flash=0;
	
	

	public Player(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, PLAYER_IMAGE_LOC,initial_HP,initial_damage,initial_cooldown);
		// TODO Auto-generated constructor stub
	}


	public float getChange_x() {
		return change_x;
	}

	public float getChange_y() {
		return change_y;
	}

	public void update(float dir_x,float dir_y,int delta,World world) {
		change_x=dir_x*delta*speed;
		change_y=dir_y*delta*speed;
		
		//TO-DO: Add 4 corner blocking 
		//if(world.block(xPos+change_x, yPos)==false) {
			xPos=xPos+change_x;
		//}
		//if(world.block(xPos,yPos+change_y)==false) {
			yPos=yPos+change_y;
		//}
		if(getCooldown_remaining()-delta>0){
			setCooldown_remaining(getCooldown_remaining()-delta);
		}
		else {
			setCooldown_remaining(0);
		}
		if (getCurrent_HP() < 0 ) {
			setxPos(738);
			setyPos(549);
			setCurrent_HP(getMax_HP());
			this.setAttacked_counter(3000);

		}
		/* block responsible for flashing a warning "You're being attacked" above the player when
		 * it is being attacked 
		 */
		this.setAttacked_counter(this.getAttacked_counter()+delta);
		if(this.getAttacked_counter()<1500 && this.isBeing_attacked() && flash>50) {
			this.setBeing_attacked(false);
			flash=0;
		}
		if(this.getAttacked_counter()<1500 && this.isBeing_attacked()==false && flash>50) {
			this.setBeing_attacked(true);
			flash=0;
		}
		flash+=delta;
		
		if(this.getAttacked_counter()>1500){
			this.setBeing_attacked(false);
		}
	
	}


	public void render(Graphics g) {
		image.drawCentered(400,(RPG.screenheight-RPG.PANELHEIGHT)/2);
		if (this.isBeing_attacked()) {
			UI.being_attacked(g);
		
		}

	}

	public String get_name(){
		return "Player";
	}
	
	public void add_to_inventory(Item item) {
		inventory.addElement(item);
	}

	public Vector<Item> getInventory() {
		return inventory;
	}
	



}
