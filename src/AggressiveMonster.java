import java.util.Random;

import org.newdawn.slick.SlickException;


public abstract class AggressiveMonster extends Monster {




	public AggressiveMonster(float xPos, float yPos, String image_loc,
			int max_HP, int damage, int cooldown)
			throws SlickException {
		super(xPos, yPos, image_loc, max_HP,damage,cooldown);
		// TODO Auto-generated constructor stub
	}
	
	Random rand = new Random();

	
	public void updateAI(Player player,int delta, World world) {
		float delta_x=player.getxPos()-this.getxPos();
		float delta_y=player.getyPos()-this.getyPos();
		double distance=Math.sqrt(delta_x*delta_x + delta_y*delta_y);
		float d_x=(float) (delta*delta_x*0.25/distance);
		float d_y=(float) (delta*delta_y*0.25/distance);
		
		if(getCooldown_remaining()-delta>0){
			setCooldown_remaining(getCooldown_remaining()-delta);
		}
		else {
			setCooldown_remaining(0);
		}
		
		if (distance<150 &&  distance>50 && this.getCurrent_HP()>0) {
			if (world.block(this.getxPos()+d_x,this.getyPos()+d_y)==false && getCurrent_HP()>0) {
			this.setxPos(this.getxPos()+d_x);
			this.setyPos(this.getyPos()+d_y);
			}
		}
		if (distance<50 && this.getCurrent_HP()>0) {
			attack(player);
		}
		
		
		
		
		// TODO Auto-generated method stub

	}
	
	public void attack(Player player) {
		
		if (this.getCooldown_remaining()==0) {
			int current_hp=player.getCurrent_HP();
			int new_hp=current_hp-World.randInt(0,this.getDamage(), rand);
			player.setCurrent_HP(new_hp);
			player.setBeing_attacked(true);
			player.setAttacked_counter(0);
			this.setCooldown_remaining(this.getCooldown());
		}
		//TODO
	}
	

//	@Override
//	public void updateInteractions() {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

}
