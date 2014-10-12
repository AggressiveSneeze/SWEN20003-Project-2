import org.newdawn.slick.SlickException;


public class ElixirOfLife extends Item {

	private static final String ELIXIR_IMAGE_LOC="assets/items/elixir.png";

	public ElixirOfLife(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, ELIXIR_IMAGE_LOC);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pickUp(Player player) {
		// TODO Auto-generated method stub

	}
	
	public String get_name(){
		return "ElixirOfLife";
	}

}
