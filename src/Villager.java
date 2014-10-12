import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Villager extends GameObject {
	
	private int interaction_counter=3000;
	private String current_dialogue;
	public Villager(float xPos, float yPos, String image_loc) 
			throws SlickException {
		super(xPos, yPos, image_loc);
		// TODO Auto-generated constructor stub
	}
	public abstract void update_interactions(Player player);
	
	public void update(Player player, double dir_T, int delta) {
		float delta_x=player.getxPos()-this.getxPos();
    	float delta_y=player.getyPos()-this.getyPos();
    	if (Math.sqrt(delta_x*delta_x+delta_y*delta_y)<=50 && dir_T>0
    			&& interaction_counter>3000) {
    		update_interactions(player);
    		interaction_counter=0;
    	}
    	interaction_counter=interaction_counter+delta;
    	
    		
    		
    	
    	
	}
	
	void render(Camera camera, Graphics g) throws SlickException {
		super.render(camera, g);
		float xPos=RPG.screenwidth-(camera.getMaxX()-this.xPos);
		float yPos=(RPG.screenheight-RPG.PANELHEIGHT)-(camera.getMaxY()-this.yPos);
		UI.HPBar(xPos,yPos,1f, this.get_name(), g);
		if (interaction_counter<3000) {
			UI.Dialogue(xPos, yPos, current_dialogue, g);	
		}
			
		
		
	}
	public String getCurrent_dialogue() {
		return current_dialogue;
	}
	public void setCurrent_dialogue(String current_dialogue) {
		this.current_dialogue = current_dialogue;
	}
	
	
}
