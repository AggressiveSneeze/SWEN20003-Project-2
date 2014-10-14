import org.newdawn.slick.SlickException;


public class AmuletOfVitality extends Item {

	private static final String AMULET_IMAGE_LOC="assets/items/amulet.png";

	public AmuletOfVitality(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, AMULET_IMAGE_LOC);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pickUp(Player player) {
		player.setMax_HP(player.getMax_HP()+80);
		player.setCurrent_HP(player.getCurrent_HP()+80);
	}
		
	public String get_name(){
		return "AmuletOfVitality";
	}
}
