import org.newdawn.slick.SlickException;


public class Elvira extends Villager {
	
	private static final String ELVIRA_IMAGE_LOC="assets/units/shaman.png";
	
	private String dialogue1 = "Return to me if you ever need healing.";
	private String dialogue2 = "You're looking much healthier now.";
	
	

	public Elvira(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, ELVIRA_IMAGE_LOC);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update_interactions(Player player) {
		
		
		if(player.getCurrent_HP()< player.getMax_HP()){
			player.setCurrent_HP(player.getMax_HP());
			setCurrent_dialogue(dialogue2);
		
		//print dialogue2
		
		}
		else {
			setCurrent_dialogue(dialogue1);
		//print other dialogue1
		}
		// TODO Auto-generated method stub

	}
	public String get_name(){
		return "Elvira";
	}

}
