import org.newdawn.slick.SlickException;


public abstract class Item extends GameObject {

	public Item(float xPos, float yPos, String image_loc) throws SlickException {
		super(xPos, yPos, image_loc);
		
	}
	
	private boolean picked_up=false;
	
	
	
	public boolean isPicked_up() {
		return picked_up;
	}



	public void setPicked_up(boolean picked_up) {
		this.picked_up = picked_up;
	}



	public abstract void pickUp(Player player);

}
