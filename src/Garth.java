import org.newdawn.slick.SlickException;



public class Garth extends Villager {
	private static final String GARTH_IMAGE_LOC = "assets/units/peasant.png";
	private String no_amulet = "Find the Amulet of Vitality, across the river to the west.";
	private String no_sword = "Find the Sword of Strength - cross the river and back, on the east side.";
	private String no_tome = "Find the Tome of Agility, in the Land of Shadows.";
	private String has_treasures = "You have found all the treasure I know of.";

	public Garth(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, GARTH_IMAGE_LOC);
	}

	@Override
	public void update_interactions(Player player) {
		boolean amulet_flag=false, sword_flag=false,tome_flag=false;
		
		for (Item item : player.getInventory()) {
			if (item.get_name().equals("AmuletOfVitality")) amulet_flag=true;
			if (item.get_name().equals("SwordOfStrength")) sword_flag=true;
			if (item.get_name().equals("TomeofAgility")) tome_flag=true;
		}
		if (amulet_flag==false) {
			this.setCurrent_dialogue(no_amulet);
			return;
		}
		if (sword_flag==false) {
			this.setCurrent_dialogue(no_sword);
			return;
		}
		if(tome_flag==false) {
			this.setCurrent_dialogue(no_tome);
			return;
		}
		setCurrent_dialogue(has_treasures);
		

	}
	
	public String get_name(){
		return "Garth";
	}
	

}
