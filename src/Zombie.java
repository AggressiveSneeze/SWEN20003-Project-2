import org.newdawn.slick.SlickException;


public class Zombie extends AggressiveMonster {
	
	private static final String ZOMBIE_IMAGE_LOC="assets/units/zombie.png";
	private static final int HP=60;
	private static final int damage=10;
	private static final int cooldown=800;


	public Zombie(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, ZOMBIE_IMAGE_LOC,HP,damage,cooldown);
		// TODO Auto-generated constructor stub
	}
	
	public String get_name(){
		return "Zombie";
	}

}
