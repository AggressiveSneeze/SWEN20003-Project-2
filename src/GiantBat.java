import org.newdawn.slick.SlickException;


public class GiantBat extends PassiveMonster {
	private static final String BAT_IMAGE_LOC="assets/units/dreadbat.png";
	private static final int HP=40;
	private static final int damage=0;
	private static final int cooldown=0;

	public GiantBat(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, BAT_IMAGE_LOC,HP,
					damage,cooldown);
		// TODO Auto-generated constructor stub
	}
	
	public String get_name(){
		return "Giant Bat";
	}
	

}
