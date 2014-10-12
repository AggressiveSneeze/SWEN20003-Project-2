import org.newdawn.slick.SlickException;


public class PrinceAldric extends Villager {
	
	private static final String PRINCE_IMAGE_LOC="assets/units/prince.png";
	private static final String had_elixir = "The elixir! My father is cured! Thankyou!";
	private static final String no_elixir = "Please seek out the Elixir of Life to cure the king.";
	private static final String given_elixir= "You gave me the elixir already. I no longer need you, baby";
	public PrinceAldric(float xPos, float yPos)
			throws SlickException {
		super(xPos, yPos, PRINCE_IMAGE_LOC);
		// TODO Auto-generated constructor stub
	}
	private boolean did_have_elixir=false;

	@Override
	public void update_interactions(Player player) {
		boolean elixir_flag=false;
		
		for (int i=0; i<player.getInventory().size();i++) {
			Item item=player.getInventory().elementAt(i);
		    if (item.get_name().equals("ElixirOfLife")) {
		    	elixir_flag=true;
		    	did_have_elixir=true;
		    	player.getInventory().remove(i);
		    	
		    	//remove
		    }
		    
		}
		if (elixir_flag) setCurrent_dialogue(had_elixir);
	
		if (elixir_flag==false) {
			if (did_have_elixir==false) setCurrent_dialogue(no_elixir);
			if (did_have_elixir) setCurrent_dialogue(given_elixir);
			
		}
			
		
		
		
		// TODO Auto-generated method stub

	}
	
	public String get_name(){
		return "Prince Aldric";
	}

}
