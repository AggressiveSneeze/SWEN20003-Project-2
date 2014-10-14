import org.newdawn.slick.SlickException;


public class Bandit extends AggressiveMonster {
	
	private static final String BANDIT_IMAGE_LOC="assets/units/bandit.png";
	private static final int HP=40;
	private static final int damage=8;
	private static final int cooldown=200;

	public Bandit(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, BANDIT_IMAGE_LOC,HP,damage,cooldown);
	}
	@Override
	public String get_name(){
		return "Bandit";
	}

}
