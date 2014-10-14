import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Unit extends GameObject {
	
	private int current_HP, max_HP, damage, cooldown;
	private float cooldown_remaining=0;
	private boolean being_attacked=false;
	private int attacked_counter=3000;
	Random rand=new Random();
	
	//getters and setters
	public int getAttacked_counter() {
		return attacked_counter;
	}

	public void setAttacked_counter(int attacked_counter) {
		this.attacked_counter = attacked_counter;
	}
	
	
	public boolean isBeing_attacked() {
		return being_attacked;
	}

	public void setBeing_attacked(boolean being_attacked) {
		this.being_attacked = being_attacked;
	}
	
	public int getCurrent_HP() {
		return current_HP;
	}

	public void setCurrent_HP(int current_HP) {
		this.current_HP = current_HP;
	}

	public int getMax_HP() {
		return max_HP;
	}

	public void setMax_HP(int max_HP) {
		this.max_HP = max_HP;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public float getCooldown_remaining() {
		return cooldown_remaining;
	}

	public void setCooldown_remaining(float cooldown_remaining) {
		this.cooldown_remaining = cooldown_remaining;
	}
	
	public float health_percentage() {
		return (1.0f*current_HP/max_HP);
	}
	
	/**still need to implement **/
	public void move_to(float new_xPos, float new_yPos, World world) {
	}

	public Unit(float xPos, float yPos, String image_loc, int max_HP,
				int damage, int cooldown)
			throws SlickException {
		super(xPos, yPos, image_loc);
		this.max_HP=max_HP;
		this.damage=damage;
		this.cooldown=cooldown;
		current_HP=this.max_HP;
		// TODO Auto-generated constructor stub
	}
	
	void render(Camera camera, Graphics g) throws SlickException {
		super.render(camera, g);
		UI.HPBar(RPG.screenwidth-(camera.getMaxX()-this.xPos),
				(RPG.screenheight-RPG.PANELHEIGHT)-(camera.getMaxY()-this.yPos), 
				this.health_percentage(), this.get_name(), g);
	}
	
	public void attack(Unit unit) {
		
		if (this.getCooldown_remaining()==0) {
			int current_hp=unit.getCurrent_HP();
			int new_hp=current_hp-World.randInt(0,this.getDamage(), rand);
			unit.setCurrent_HP(new_hp);
			unit.setBeing_attacked(true);
			unit.setAttacked_counter(0);
			this.setCooldown_remaining(this.getCooldown());
		}
		//TODO
	}

}
