import java.util.Random;

import org.newdawn.slick.SlickException;


public abstract class PassiveMonster extends Monster {
	private static Random rand = new Random();
	private int movement_counter=0;
	private int direction= World.randInt(0,8,rand);
	private float speed=0.2f;
	private float new_x_pos;
	private float new_y_pos;

	public PassiveMonster(float xPos, float yPos, String image_loc,
			int max_HP, int damage, int cooldown)
			throws SlickException {
		super(xPos, yPos, image_loc, max_HP,damage,cooldown);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updateAI(Player player, int delta, World world) {
		/* if monster hasn't been attacked, behave as required */
		if(this.getAttacked_counter()>=5000) updateInteractions(delta,world);
		else {
			float delta_x=player.getxPos()-this.getxPos();
			float delta_y=player.getyPos()-this.getyPos();
			double distance=Math.sqrt(delta_x*delta_x + delta_y*delta_y);
			float d_x=(float) (delta*delta_x*speed/distance);
			float d_y=(float) (delta*delta_y*speed/distance);
			new_x_pos=this.getxPos()-d_x;
			new_y_pos=this.getyPos()-d_y;
			if(getCurrent_HP()>0){
				if(world.block(new_x_pos, this.getyPos())==false) {
					xPos=new_x_pos;
				}
				if(world.block(this.getxPos(),new_y_pos)==false) {
					yPos=new_y_pos;
				}
			}
			this.setAttacked_counter(this.getAttacked_counter()+delta);
			
			
			
		}
		//time to run away from player
		
		
	}

	
	public void updateInteractions(int delta, World world) {
		//rerandomises every 3 seconds
		if (movement_counter>=3000) {
			direction = World.randInt(0,8, rand);
			movement_counter=0;
			}
			
			switch(direction) {
			case 0:
				//no direction, do nothing
				break;
			case 1:
				new_y_pos=getyPos()-delta*speed;
				//up
				break;
			case 2:
				//down
				new_y_pos=getyPos()+delta*speed;
				break;
			case 3:
				//left
				new_x_pos=getxPos()-delta*speed;
				break;
			case 4:
				//right
				new_x_pos=getxPos()+delta*speed;
				break;
				
				//explain working, but on paper
			case 5:
				//Up-Right
				new_y_pos=(float)(getyPos()-delta*speed/Math.sqrt(2));
				new_x_pos=(float)(getxPos()+delta*speed/Math.sqrt(2));
				break;
			case 6:
				//Down-Right
				new_y_pos=(float)(getyPos()+delta*speed/Math.sqrt(2));
				new_x_pos=(float)(getxPos()+delta*speed/Math.sqrt(2));
				break;
			case 7:
				//Down-Left
				new_y_pos=(float)(getyPos()-delta*speed/Math.sqrt(2));
				new_x_pos=(float)(getxPos()-delta*speed/Math.sqrt(2));
				break;
			case 8:
				//Up-Left
				new_y_pos=(float)(getyPos()-delta*speed/Math.sqrt(2));
				new_x_pos=(float)(getxPos()-delta*speed/Math.sqrt(2));
				break;
			}
			if (world.block(new_x_pos,new_y_pos)==false && getCurrent_HP()>0) {
				setxPos(new_x_pos);
				setyPos(new_y_pos);
			}
			/* if the monster is walking into a blocked tile in its current orientation,
			 * make it turn around.
			 */
			if (world.block(new_x_pos,new_y_pos)==true) {
				switch (direction) {
				case 1:
					direction=2; 
					break;
				case 2:
					direction=1;
					break;
				case 3:
					direction=4;
					break;
				case 4:
					direction=3;
					break;
				case 5:
					direction=7;
					break;
				case 7:
					direction=5;
					break;
				case 6:
					direction=8;
					break;
				case 8:
					direction=6;
					break; 		
				}
			}
			movement_counter+=delta;

	}

}
