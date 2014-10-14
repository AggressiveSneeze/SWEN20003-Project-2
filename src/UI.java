import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class UI {
	
	static private Image panel;
	
	/** Renders the player's status panel.
     * @param g The current Slick graphics context.
	 * @throws SlickException 
     */
	
	static Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
    static Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
    static Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
    static Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp
   
    static public void renderPanel(Player player,Graphics g) throws SlickException
    {
    	int text_x, text_y;         // Coordinates to draw text
    	int bar_x, bar_y;           // Coordinates to draw rectangles
    	int bar_width, bar_height;  // Size of rectangle to draw
    	int hp_bar_width; 
    	panel= new Image("assets/panel.png");
        // Panel colours
        

        // Variables for layout
        String text;                // Text to display
                 // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.screenheight - RPG.PANELHEIGHT);

        // Display the player's health
        text_x = 15;
        text_y = RPG.screenheight - RPG.PANELHEIGHT + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = (player.getCurrent_HP())+"/"+ (player.getMax_HP());                                // TODO: HP / Max-HP

        bar_x = 90;
        bar_y = RPG.screenheight - RPG.PANELHEIGHT + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = player.health_percentage();                         // TODO: HP / Max-HP
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = ""+player.getDamage();                                    // TODO: Damage
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = ""+player.getCooldown_remaining();                                    // TODO: Cooldown
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.screenheight - RPG.PANELHEIGHT + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.screenheight - RPG.PANELHEIGHT
            + ((RPG.PANELHEIGHT - 72) / 2);
        // for (each item in the player's inventory)                // TODO
        for(Item item: player.getInventory())
        {
            // Render the item to (inv_x, inv_y)
        	item.getImage().draw(inv_x, inv_y);
            inv_x += 72;
        }
    }
    
    static public void HPBar(float xPos, float yPos, float health_percent,
    		String name, Graphics g) throws SlickException {
    	                             // TODO: HP / Max-HP
    	if (health_percent < 0 ) {
    		health_percent=0;
    	}

    	int bar_x,bar_y,bar_width,bar_height,hp_bar_width;
    	int text_x,text_y;
        bar_x = (int)(xPos-34);
        bar_y = (int)(yPos-45);
        //bar width = max{textwidth+6,70} as per the spec
        if (g.getFont().getWidth(name)+6 > 70) {
        	bar_width=g.getFont().getWidth(name)+6;
        }
        else { 
        	bar_width=70;
        }
        
        bar_height = 20;
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(name)) / 2;
        text_y=bar_y;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(name, text_x, text_y);
        
        //System.out.println(name +" at " + xPos + "," + yPos);
        
        

    
    }
    
    static public void Dialogue(float xPos, float yPos, String dialogue, Graphics g) {
    	int bar_x,bar_y,bar_width,bar_height;
    	int text_x,text_y;
        bar_x = (int)(xPos-160);
        bar_y = (int)(yPos-65);
        //bar width = max{textwidth+6,70} as per the spec
        if (g.getFont().getWidth(dialogue)+6 > 70) {
        	bar_width=g.getFont().getWidth(dialogue)+6;
        }
        else { 
        	bar_width=70;
        }
        bar_height = 20;
        text_x = bar_x + (bar_width - g.getFont().getWidth(dialogue)) / 2;
        text_y=bar_y;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(dialogue, text_x, text_y);
    }
    
    static public void being_attacked(Graphics g) {
    	g.setColor(BAR);
    	g.drawString("YOU'RE BEING ATTACKED", 300, 200);
    	
    	
    }
	
}
