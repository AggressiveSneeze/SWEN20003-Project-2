/* SWEN20003 Object Oriented Software Development Project 1
 * RPG Game Engine
 * Author: <James Adams> <adj> <Student no: 572541>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class PlayerOld {
	protected String image_loc;
	private Image hero_image;
	private float xPos;
	private float yPos;
	static final private float initial_x=756;
	static final private float initial_y=684;
	static final private float speed=0.25f;
	static final private int max_pixel=6912;
	protected float getxPos() {
		return xPos;
	}

	protected float getyPos() {
		return yPos;
	}




	
	
	
	protected PlayerOld(String image_loc)
	throws SlickException
	{
		this.image_loc=image_loc;
		hero_image=new Image(image_loc);
		this.xPos=initial_x;
		this.yPos=initial_y;
		
	}
	
	
	/**Updates the player position,
	  if and only if the prospective update won't place the (center of the) player
	  in a blocked tile.
	 * 
	 *
	 * @param dir_x change in x from keyboard input
	 * @param dir_y change in y from keyboard input
	 * @param delta magnitude of change
	 * @param world the tiled map world instance
	 */
	public void update(float dir_x,float dir_y,int delta,World world) {
		float change_x=dir_x*delta*speed;
		float change_y=dir_y*delta*speed;
		if ((change_x>0 && xPos<max_pixel) || (change_x<0 && xPos>0)) {
			if(world.block(xPos+change_x, yPos)) {
					xPos=xPos+change_x;
			}
		}
		
		if ((change_y>0 && yPos<max_pixel) || (change_y<0 && yPos>0)) {
			if(world.block(xPos,yPos+change_y)) {
				yPos=yPos+change_y;
			}
			
		}
		
		
		
	}
	
	
	protected void hero_draw() {
		//draws the hero at the center of the screen
		hero_image.drawCentered(RPG.screenwidth/2,RPG.screenheight/2); 
	}
	
	
	
	

}


// 