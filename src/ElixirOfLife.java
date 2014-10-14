import org.newdawn.slick.SlickException;


public class ElixirOfLife extends Item {

	private static final String ELIXIR_IMAGE_LOC="assets/items/elixir.png";

	public ElixirOfLife(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, ELIXIR_IMAGE_LOC);
	}

	@Override
	public void pickUp(Player player) {
		// Nothing needed to be done

	}
	
	public String get_name(){
		return "ElixirOfLife";
	}

}
