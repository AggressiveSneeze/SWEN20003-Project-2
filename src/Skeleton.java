import org.newdawn.slick.SlickException;


public class Skeleton extends AggressiveMonster {
	
	private static final String SKELETON_IMAGE_LOC="assets/units/skeleton.png";
	private static final int HP=100;
	private static final int damage=16;
	private static final int cooldown=500;


	public Skeleton(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos,SKELETON_IMAGE_LOC,HP,damage,cooldown);
		// TODO Auto-generated constructor stub
	}
	
	public String get_name(){
		return "Skeleton";
	}

}
