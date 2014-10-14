/* SWEN20003 Object Oriented Software Development Project 1
 * RPG Game Engine
 * Author: <James Adams> <adj> <Student no: 572541>
 */


import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public class Camera
{
	
	
    /** The unit this camera is following */
    private Player unitFollow;
    
    /** The width and height of the screen */
    /** Screen width, in pixels. */
    private final int screenwidth;
    /** Screen height, in pixels. */
    private final int screenheight;
    
    /** The camera's position in the world, in x and y coordinates. */
   private int xPos;
   private int yPos;

    
    public int getxPos() { 
    	return xPos;
    }

    public int getyPos() {
    	return yPos;
    }

    
    /** Create a new camera object. */
    protected Camera(Player player)
    {   
    	this.unitFollow=player;
    	this.screenwidth=RPG.screenwidth;
    	this.screenheight=RPG.screenheight-RPG.PANELHEIGHT;
    	//initial positions 
    	this.xPos=(int)(unitFollow.getxPos());
    	this.yPos=(int)(unitFollow.getyPos());
        //  Filled
    }

    /** Update the game camera to re-centre it's viewpoint around the player 
     */
    protected void update()
    throws SlickException
    {
    	xPos=(int)(unitFollow.getxPos());
    	yPos=(int)(unitFollow.getyPos());
        // TO DO: Fill In
    }
    
    /** Returns the minimum x value on screen 
     */
    protected int getMinX(){
    	return (int) (xPos-0.5*screenwidth);
        //  Filled
    }
    
    /** Returns the maximum x value on screen 
     */
    protected int getMaxX(){
    	return (int) (xPos+0.5*screenwidth);
        // Filled
    }
    
    /** Returns the minimum y value on screen 
     */
    protected int getMinY(){
    	return (int) (yPos-0.5*screenheight);
        // Filled
    }
    
    /** Returns the maximum y value on screen 
     */
    protected int getMaxY(){
    	return (int) (yPos+0.5*screenheight);
    	
        // Filled
    }

    
}