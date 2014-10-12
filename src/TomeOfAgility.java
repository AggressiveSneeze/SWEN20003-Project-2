import org.newdawn.slick.SlickException;


public class TomeOfAgility extends Item {

	private static final String TOME_IMAGE_LOC="assets/items/book.png";

	public TomeOfAgility(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, TOME_IMAGE_LOC);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pickUp(Player player) {
		player.setCooldown(player.getCooldown()-300);
		// TODO Auto-generated method stub

	}
	
	public String get_name(){
		return "TomeofAgility";
	}

}
