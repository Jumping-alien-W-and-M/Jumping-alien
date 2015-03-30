package jumpingalien.model;

import java.util.ArrayList;
import java.util.HashMap;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * The world class, which stores general information about the state of the game, like the player character, enemies, the environment,
 * the position of the view, ...
 * 
 * @invar	...
 * 			| (getXWindow() == getProperXWindow(getXWindow()))
 * @invar	...
 * 			| (getYWindow() == getProperYWindow(getYWindow()))
 * 
 * @author Michiel Bollen en Wouter Baert
 * @version 1.0
 *
 */
public class World {
	
	/**
	 * Creates a new world.
	 * 
	 * @param tile_size
	 * 			The size (both width and height) of the tiles in this world, in pixels.
	 * @param tiles_x_amount
	 * 			The amount of tiles in the X-direction in this world.
	 * @param tiles_y_amount
	 * 			The amount of tiles in the Y-direction in this world.
	 * @pre		...
	 * 			| (tile_size > 0)
	 * @pre		...
	 * 			| (tiles_x_amount > 0)
	 * @pre		...
	 * 			| (tiles_y_amount > 0)
	 * @pre		...
	 * 			| (x_target > 0)
	 * @pre		...
	 * 			| (y_target > 0)
	 * @pre		...
	 * 			| (x_target < tiles_x_amount)
	 * @pre		...
	 * 			| (y_target < tiles_y_amount)
	 * @post	...
	 * 			| (getTileSize() == tile_size)
	 * @post	...
	 * 			| (getWorldWidth() == tiles_x_amount*getTileSize())
	 * @post	...
	 * 			| (getWorldHeight() == tiles_y_amount*getTileSize())
	 * @post	...
	 * 			| (getXTarget() == x_target)
	 * @post	...
	 * 			| (getYTarget() == y_target)
	 * @effect	...
	 * 			| setWindowWidth(window_width)
	 * @effect	...
	 * 			| setWindowHeight(window_height)
	 * @effect	...
	 * 			| setXWindow(0)
	 * @effect	...
	 * 			| setYWindow(0)
	 */
	public World(int tile_size, int tiles_x_amount, int tiles_y_amount, int window_width, int window_height, int x_target, int y_target) {
		assert(tile_size > 0);
		assert(tiles_x_amount > 0);
		assert(tiles_y_amount > 0);
		assert(x_target > 0);
		assert(y_target > 0);
		assert(x_target < tiles_x_amount);
		assert(y_target < tiles_y_amount);
		
		this.tile_size = tile_size;
		this.world_width = tiles_x_amount*getTileSize();
		this.world_height = tiles_y_amount*getTileSize();
		this.hashDigitsAmount = (int) Math.floor(Math.log10(tiles_y_amount) + 1);
		
		setWindowWidth(window_width);
		setWindowHeight(window_height);
		setXWindow(0);
		setYWindow(0);

		this.x_target = x_target;
		this.y_target = y_target;
	}

	/**
	 * Gets the width of the game world.
	 */
	@Basic @Immutable
	public int getWorldWidth() {
		return world_width;
	}
	
	private final int world_width;
	
	/**
	 * Gets the height of the game world.
	 */
	@Basic @Immutable
	public int getWorldHeight() {
		return world_height;
	}
	
	private final int world_height;
	
	/**
	 * Gets the tile size of this game world.
	 */
	@Basic @Immutable
	public int getTileSize() {
		return tile_size;
	}
	
	private final int tile_size;
	
	/**
	 * Gets the x-position of this world's target tile, in tiles.
	 */
	@Basic @Immutable
	public int getXTarget() {
		return x_target;
	}
	
	private final int x_target;
	
	/**
	 * Gets the y-position of this world's target tile, in tiles.
	 */
	@Basic @Immutable
	public int getYTarget() {
		return y_target;
	}
	
	private final int y_target;
	
	/**
	 * Gets the X-position of this game world's window.
	 */
	@Basic @Immutable
	public int getXWindow() {
		return x_window;
	}
	
	/**
	 * Sets the X-position of this game world's window.
	 * 
	 * @param x
	 * 			The new X-position of this game world's window.
	 * @post	...
	 * 			| new.getXWindow() == getProperXWindow(x)
	 */
	private void setXWindow(int x) {
		this.x_window = getProperXWindow(x);
	}
	
	private int x_window;
	
	/**
	 * Gets the Y-position of this game world's window.
	 */
	@Basic @Immutable
	public int getYWindow() {
		return y_window;
	}
	
	/**
	 * Sets the Y-position of this game world's window.
	 * 
	 * @param y
	 * 			The new Y-position of this game world's window.
	 * @post	...
	 * 			| new.getXWindow() == getProperXWindow(y)
	 */
	private void setYWindow(int y) {
		this.y_window = getProperYWindow(y);
	}
	
	private int y_window;
	
	/**
	 * Gets a proper window X-position based on the boundaries of the world and the player's position.
	 * 
	 * @param x
	 * 			The X-position which should be converted.
	 * @return	...
	 * 			| result = Math.max(0, Math.min(getWorldWidth() - getWindowWidth(),
	 * 			| 	Math.max(player.getX() + window_margin - getWindowWidth(), Math.min(player.getX() - window_margin, x))
	 * 			| ))
	 */
	public int getProperXWindow(int x) {
		
		if (x > player.getX() - window_margin) {
			// If Mazub is too close to the left wall.
			x = (int) player.getX() - window_margin;
		} else if (x + getWindowWidth() < player.getX() + window_margin) {
			// If Mazub is too close to the right wall.
			x = (int) player.getX() + window_margin - getWindowWidth();
		}
		
		return Math.max(0, Math.min(getWorldWidth() - getWindowWidth(), x));
		
	}
	
	/**
	 * Gets a proper window Y-position based on the boundaries of the world and the player's position.
	 * 
	 * @param y
	 * 			The Y-position which should be converted.
	 * @return	...
	 * 			| result = Math.max(0, Math.min(getWorldHeight() - getWindowHeight(),
	 * 			| 	Math.max(player.getY() + window_margin - getWindowHeight(), Math.min(player.getY() - window_margin, y))
	 * 			| ))
	 */
	public int getProperYWindow(int y) {
		
		if (y > player.getY() - window_margin) {
			// If Mazub is too close to the left wall.
			y = (int) player.getY() - window_margin;
		} else if (y + getWindowHeight() < player.getY() + window_margin) {
			// If Mazub is too close to the right wall.
			y = (int) player.getY() + window_margin - getWindowHeight();
		}
		
		return Math.max(0, Math.min(getWorldHeight() - getWindowHeight(), y));
		
	}
	
	private static final int window_margin = 200;
	
	/**
	 * Gets the window width of this game world's window.
	 */
	@Basic @Immutable
	public int getWindowWidth() {
		return window_width;
	}
	
	/**
	 * Sets the window width of this game world's window.
	 * 
	 * @param y
	 * 			The new window width of this game world's window.
	 * @pre		...
	 * 			| (window_width > 0)
	 * @pre		...
	 * 			| (window_width <= getWorldWidth())
	 * @post	...
	 * 			| new.getWindowWidth() == window_width
	 */
	private void setWindowWidth(int window_width) {
		assert(window_width > 0);
		assert(window_width <= getWorldWidth());
		this.window_width = window_width;
	}
	
	private int window_width;
	
	/**
	 * Gets the window height of this game world's window.
	 */
	@Basic @Immutable
	public int getWindowHeight() {
		return window_height;
	}
	
	/**
	 * Sets the window height of this game world's window.
	 * 
	 * @param y
	 * 			The new window height of this game world's window.
	 * @pre		...
	 * 			| (window_height > 0)
	 * @pre		...
	 * 			| (window_height <= getWorldHeight())
	 * @post	...
	 * 			| new.getWindowHeight() == window_height
	 */
	private void setWindowHeight(int window_height) {
		assert(window_height > 0);
		assert(window_height <= getWorldHeight());
		this.window_height = window_height;
	}
	
	private int window_height;
	
	/**
	 * Checks whether or not this world is terminated.
	 * 
	 * @return	...
	 * 			| result = ((player == null) || (touchesTarget()))
	 */
	public boolean isGameOver() {
		return ((player == null) || (touchesTarget()));
		

	}
	
	/**
	 * Checks whether or not this world's player is touching the target tile.
	 * 
	 * @return	...
	 * 			| for(x = player.getX(); x <= player.getX() + player.getWidth(); x += player.getWidth())
	 * 			|	for(y = player.getY(); y <= player.getY() + player.getHeight(); y += player.getHeight())
	 * 			| 		if ((x >= getXTarget()) && (x < getXTarget() + getTileSize())
	 * 			|			&& (y >= getYTarget()) && (y < getYTarget() + getTileSize()))
	 * 			|			result = true
	 * 			| result = false
	 */
	private boolean touchesTarget() {
		
		for(int x = (int) player.getX(); x <= player.getX() + player.getWidth(); x += player.getWidth()) {
			for(int y = (int) player.getY(); y <= player.getY() + player.getHeight(); y += player.getHeight()) {
				if ((getTilePos(x) == getXTarget())
						&& (getTilePos(y) == getYTarget())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the position of a position in pixels in the tile coordinate system.
	 * 
	 * @param pos
	 * 			The position which should be checked.
	 * @return	...
	 * 			| result = (int) Math.floor(pos/getTileSize())
	 */
	protected int getTilePos(int pos) {
		return (int) Math.floor(pos/getTileSize());
	}
	
	/**
	 * Connects a given player with a given world, and vice versa.
	 * 
	 * @param world
	 * 			The world which should be associated with the player.
	 * @param player
	 * 			The player which should be associated with the world.
	 * @pre		...
	 * 			| (world != null)
	 * @post	...
	 * 			| (world.getPlayer() == player)
	 * @effect	...
	 * 			| player.setWorld(world)
	 */
	public static void setMazub(World world, Mazub player) {
		assert(world != null);
		
		world.player = player;
		player.setWorld(world);
	}
	
	private Mazub player;
	
	/**
	 * Gets the list of sharks in this world.
	 */
	@Basic @Immutable
	public ArrayList<Shark> getSharks() {
		return this.sharks;
	}
	
	private final ArrayList<Shark> sharks = new ArrayList<Shark>();
	
	/**
	 * Gets the list of sharks in this world.
	 */
	@Basic @Immutable
	public ArrayList<Plant> getPlants() {
		return this.plants;
	}

	private final ArrayList<Plant> plants = new ArrayList<Plant>();
	
	/**
	 * Gets the list of sharks in this world.
	 */
	@Basic @Immutable
	public ArrayList<School> getSchools() {
		return this.schools;
	}
	
	private final ArrayList<School> schools = new ArrayList<School>();
	
	/**
	 * Gets the hash of a given x and y position.
	 * 
	 * @param x
	 * 			The X-position which should be converted to a hash.
	 * @param y
	 * 			The Y-position which should be converted to a hash.
	 * @return	...
	 * 			| result = ((int) Math.pow(10, getHashDigitsAmount()))*x + y
	 */
	public int getHash(int x, int y) {
		return ((int) Math.pow(10, getHashDigitsAmount()))*x + y;
	}
	
	/**
	 * Gets the feature at a given X- and Y-position.
	 * 
	 * @param x
	 * 			The X-position which should be checked, in pixels.
	 * @param y
	 * 			The Y-position which should be checked, in pixels.
	 * @return	...
	 * 			| result = getFeatures().get(getHash(getTilePos(x), getTilePos(y)))
	 */
	public Feature getFeature(int x, int y) {
		return getFeatures().get(getHash(getTilePos(x), getTilePos(y)));
	}
	
	/**
	 * Gets the hashmap containing the features of this world.
	 */
	@Basic @Immutable
	public HashMap<Integer, Feature> getFeatures() {
		return features;
	}
	
	private final HashMap<Integer, Feature> features = new HashMap<Integer,Feature>();
	
	/**
	 * Gets the amount of digits needed to store the Y-position of a tile in base 10 in this world.
	 */
	@Basic @Immutable
	public int getHashDigitsAmount() {
		return this.hashDigitsAmount;
	}
	
	private final int hashDigitsAmount;
	
	public Object[][] collisionDetect(Object object) {
		
		ArrayList[] collision_objects = {new ArrayList<Object>(), new ArrayList<Object>(),
											new ArrayList<Object>(), new ArrayList<Object>()};
		
		for(Shark shark : sharks) {
			collisionDetect(object, shark, collision_objects);
		}
		
		Object[][] collisions = {{new ArrayList<Object>(), null}, {new ArrayList<Object>(), null},
									{new ArrayList<Object>(), null}, {new ArrayList<Object>(), null}};
		return collisions; 
	}
	
	private static void collisionDetect(Object mobile_object, Object static_object, ArrayList[] collision_objects)  {
		
		Class mobile_class = mobile_object.getClass();
				
	}
	
}
