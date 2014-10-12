import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class GameObject {
	float xPos,yPos;
	Image image;
	
	public GameObject(float xPos, float yPos, String image_loc) 
			throws SlickException {
		this.xPos = xPos;
		this.yPos = yPos;
		image= new Image(image_loc);
	}

	/**getters & setters **/


	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	
	public Image getImage() {
		return image; 
	}
	
	
	void render(Camera camera, Graphics g) throws SlickException {
		image.drawCentered(RPG.screenwidth-(camera.getMaxX()-this.xPos),
				(RPG.screenheight-RPG.PANELHEIGHT)-(camera.getMaxY()-this.yPos));
		
	}
	
	public abstract String get_name();

}
