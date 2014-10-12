import org.newdawn.slick.SlickException;


public class SwordOfStrength extends Item {

	private static final String SWORD_IMAGE_LOC="assets/items/sword.png";
	
	public SwordOfStrength(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, SWORD_IMAGE_LOC);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pickUp(Player player) {
		player.setDamage(player.getDamage()+30);
		// TODO Auto-generated method stub

	}
	
	public String get_name(){
		return "SwordOfStrength";
	}

}
