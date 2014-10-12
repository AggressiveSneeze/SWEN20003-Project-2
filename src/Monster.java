import org.newdawn.slick.SlickException;


public abstract class Monster extends Unit {

	public Monster(float xPos, float yPos, String image_loc, int max_HP,
					int damage, int cooldown)
			throws SlickException {
		super(xPos, yPos, image_loc,max_HP,damage,cooldown);
		// TODO Auto-generated constructor stub
	}
	
	private boolean alive=true;

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	private int attacked_counter=5000;


	public int getAttacked_counter() {
		return attacked_counter;
	}

	public void setAttacked_counter(int attacked_counter) {
		this.attacked_counter = attacked_counter;
	}

	public abstract void updateAI(Player player, int delta,World world);
	//public abstract void updateInteractions(int delta, World world);
	
	public void update(World world,Player player,int delta) {
		updateAI(player,delta,world);
		if(getCurrent_HP() < 0) alive=false;
		
	
	}
	public void die() {
		
	}
	

} 
