
import org.newdawn.slick.SlickException;


public abstract class AggressiveMonster extends Monster {




	public AggressiveMonster(float xPos, float yPos, String image_loc,
			int max_HP, int damage, int cooldown)
			throws SlickException {
		super(xPos, yPos, image_loc, max_HP,damage,cooldown);
	}
	
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
			
			if(world.block(this.getxPos()+d_x, this.getyPos())==false) {
				xPos=xPos+d_x;
			}
			if(world.block(this.getxPos(),yPos+d_y)==false) {
				yPos=yPos+d_y;
			}
	
		}
		if (distance<50 && this.getCurrent_HP()>0) {
			attack(player);
		}
		
	}


}
