import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Player extends Unit {
	private static final String PLAYER_IMAGE_LOC = "assets/units/player.png";
	private Vector<Item> inventory =new Vector<Item>(5);
	static final private float speed=0.25f;
	static final private int max_pixel=6912;
	private static final int initial_HP=100;
	private static final int initial_damage=26;
	private static final int initial_cooldown=600;
	Random rand=new Random();
	private float change_x;
	private float change_y;
	private boolean being_attacked=false;
	private int attacked_counter=3000;
	private int flash=0;
	
	


	public boolean isBeing_attacked() {
		return being_attacked;
	}

	public void setBeing_attacked(boolean being_attacked) {
		this.being_attacked = being_attacked;
	}
	
	

	public Player(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, PLAYER_IMAGE_LOC,initial_HP,initial_damage,initial_cooldown);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

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
		//		if ((change_x>0 && xPos<max_pixel) || (change_x<0 && xPos>0)) {
		//			if(world.block(xPos+change_x, yPos)) {
		//					xPos=xPos+change_x;
		//			}
		//		}
		//		
		//		if ((change_y>0 && yPos<max_pixel) || (change_y<0 && yPos>0)) {
		//			if(world.block(xPos,yPos+change_y)) {
		//				yPos=yPos+change_y;
		//			}
		//			
		//		}
		//TO-DO: Add 4 corner blocking 
		if(world.block(xPos+change_x, yPos)==false) {
			xPos=xPos+change_x;
		}
		if(world.block(xPos,yPos+change_y)==false) {
			yPos=yPos+change_y;
		}
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

		}
//		if (being_attacked) being_attacked=false;
//		if (being_attacked=false) 
//		
//		
		attacked_counter+=delta;
		if(attacked_counter<3000 && being_attacked && flash>50) {
			being_attacked=false;
			flash=0;
		}
		if(attacked_counter<1500 && being_attacked==false && flash>50){
			being_attacked=true;
			flash=0;
		}
		if(attacked_counter>1500){
			being_attacked=false;
		}
		flash+=delta;
		//System.out.println(xPos + " " + yPos + " player " + delta);
	}
	public int getAttacked_counter() {
		return attacked_counter;
	}

	public void setAttacked_counter(int attacked_counter) {
		this.attacked_counter = attacked_counter;
	}

	public void attack(Monster enemy) {
		if (this.getCooldown_remaining()==0) {
		int current_hp=enemy.getCurrent_HP();
		int new_hp=current_hp-World.randInt(0,this.getDamage(), rand);
		enemy.setCurrent_HP(new_hp);
		this.setCooldown_remaining(this.getCooldown());
		enemy.setAttacked_counter(0);
		}
		//System.out.println("Current enemy hp: " + enemy.getCurrent_HP());
	}

	public void render(Graphics g) {
		image.drawCentered(400,(RPG.screenheight-RPG.PANELHEIGHT)/2);
		if (being_attacked) {
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
