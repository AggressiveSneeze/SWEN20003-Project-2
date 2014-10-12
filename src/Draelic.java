import org.newdawn.slick.SlickException;


public class Draelic extends AggressiveMonster {
	
	private static final String DRAELIC_IMAGE_LOC="assets/units/necromancer.png";
	private static final int HP=140;
	private static final int damage=30;
	private static final int cooldown=400;

	public Draelic(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, DRAELIC_IMAGE_LOC,HP,damage,cooldown);
		// TODO Auto-generated constructor stub
	}

	public String get_name(){
		return "Draelic";
	}
}
