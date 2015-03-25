package model;

import jumpingalien.util.Sprite;
import jumpingalien.util.*;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * The main character, which the players controls.
 * 
 * @invar	The x-position is valid.
 * 			| isValidX(getX())
 * @invar	The y-position is valid.
 * 			| isValidY(getY())
 * @invar	The horizontal velocity is valid.
 * 			| isValidVx(getVx())
 * 
 * @author Michiel Bollen en Wouter Baert
 * @version 1.0
 */
public class Mazub {
	
	/**
	 * Creates a new Mazub at a given position with a series of given sprites.
	 * 
	 * @param x
	 * 			The new Mazub's x-position.
	 * @param y
	 * 			The new Mazub's y-position.
	 * @param images
	 * 			The new Mazub's array of sprites.
	 * @post	This Mazub's x-position will be equal to the given x.
	 * 			| new.getX() = x
	 */
	public Mazub(int x, int y, Sprite[] images) {
		setX(x);
		setY(y);
		this.images = images;

		setVx(0);
		setVxmax(3);
		setVy(0);
		setAx(0);
		setAy(0);
		
		setLastMove(0);
	}
	
	/**
	 * Gets the x-position of this Mazub.
	 */
	@Basic
	public int getX() {
		return this.x;
	}
	
	/**
	 * Sets the x-position of this Mazub.
	 * 
	 * @param x
	 * 			This Mazub's new x-position in pixels.
	 * @post	This Mazub's new x-position will be equal to the given x.
	 * 			| new.getX() == x
	 * @throws	IllegalArgumentException
	 * 			Throws an exception if x is outside the game world.
	 * 			| !isValidX(x)
	 */
	public void setX(int x) throws IllegalArgumentException {
		if (!isValidX(x)) {
			throw new IllegalArgumentException();
		}
		this.x = x;
	}
	
	/**
	 * Checks if the given x is a valid x-position for this Mazub in the game world.
	 * 
	 * @param x
	 * 			The x-position in pixels which should be checked.
	 * @return	Whether or not the given x is within the game world.
	 * 			| result = (x >= 0 && x < getWorldWidth)
	 */
	public boolean isValidX(int x) {
		return (x >= 0 && x < getWorldWidth());
	}
	
	private int x;
	
	/**
	 * Gets the y-position of this Mazub.
	 */
	@Basic
	public int getY() {
		return this.y;
	}
	
	/**
	 * Sets the y-position of this Mazub.
	 * 
	 * @param y
	 * 			This Mazub's new y-position in pixels.
	 * @post	This Mazub's new y-position will be equal to the given y.
	 * 			| new.getY() == y
	 * @throws	IllegalArgumentException
	 * 			If y is outside the game world.
	 * 			| !isValidY(y)
	 */
	public void setY(int y) throws IllegalArgumentException {
		if (!isValidY(y)) {
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
	/**
	 * Checks if the given y is a valid y-position for this Mazub in the game world.
	 * 
	 * @param y
	 * 			The y-position in pixels which should be checked.
	 * @return	Whether or not the given y is within the game world.
	 * 			| result = (y >= 0 && y < getWorldHeight())
	 */
	public boolean isValidY(int y) {
		return (y >= 0 && y < getWorldHeight()); 
	}
	
	private int y;
	
	
	
	/**
	 * Gets the horizontal velocity of this Mazub.
	 */
	@Basic
	public double getVx() {
		return this.vx;
	}
	
	/**
	 * Sets the horizontal velocity of this Mazub.
	 * 
	 * @param vx
	 * 			The new horizontal velocity of this Mazub in meters per second (1m = 100 pixels).
	 * @pre		The given vx must be in between -vxmax and vxmax.
	 * 			| isValidVx(vx)
	 * @post	The new horizontal velocity of this Mazub is equal to vx.
	 * 			| new.getVx() = vx
	 */
	public void setVx(double vx) {
		assert isValidVx(vx);
		this.vx = vx;
	}
	
	/**
	 * Checks if the given vx is a valid horizontal velocity.
	 * 
	 * @param vx
	 * @return Whether or not the magnitude of vx is not smaller than the initial horizontal velocity and not larger than
	 * 			the maximal horizontal velocity.
	 * 			| result = ((-vxmax <= vx && vx <= -vxi) || (vx == 0) || (vxi <= vx && vx <= vxmax))
	 */
	public boolean isValidVx(double vx) {
		return ((-vxmax <= vx && vx <= -vxi)
				|| (vx == 0)
				|| (vxi <= vx && vx <= vxmax));
	}
	
	private double vx;
	
	/**
	 * Gets the initial horizontal velocity any Mazub starts with when they start moving.
	 */
	@Basic
	public double getVxi() {
		return this.vxi;
	}

	private final double vxi = 1;
	
	/**
	 * Gets the maximal horizontal velocity any Mazub can achieve when moving.
	 */
	@Basic
	public double getVxmax() {
		return this.vxmax;
	}
	
	/**
	 * Sets this Mazub's maximal horizontal velocity to a given vxmax.
	 * 
	 * @param vxmax
	 * 			The maximal horizontal velocity this Mazub should have.
	 * @post	The new Mazub's vxmax should be equal to the given max.
	 * 			| new.getVxmax() == vxmax
	 */
	public void setVxmax(double vxmax) {
		this.vxmax = vxmax;
	}
	
	private double vxmax;
	
	/**
	 * Gets the vertical velocity of this Mazub.
	 */
	@Basic
	public double getVy() {
		return this.vy;
	}
	
	/**
	 * Sets the vertical velocity of this Mazub.
	 * 
	 * @param vy
	 * 			This Mazub's new vertical velocity in meters per second (1m = 100 pixels),
	 * 			which has to be smaller than or equal to 8 meters per second.
	 * @post	This Mazub's new vertical velocity will be equal to the given vy, unless it's larger than 8 meters per second,
	 * 			in which case the new vertical velocity will be equal to 8 meters per second.
	 * 			| if (vy <= 8)
	 * 			| 	then new.getVy() = vy
	 *			| else
	 *			|	then new.getVy() = 8
	 *
	 */
	public void setVy(double vy) {
		if (vy <= 8) {
			this.vy = vy;
		} else {
			this.vy = 8;
		}
	}
	
	private double vy;
	
	/**
	 * Gets the horizontal acceleration of this Mazub.
	 */
	@Basic
	public double getAx() {
		return this.ax;
	}
	
	/**
	 * Sets the horizontal acceleration of this Mazub to ax.
	 * 
	 * @param ax
	 * 			The new horizontal acceleration of this Mazub.
	 * @post	If ax is a valid horizontal acceleration,
	 * 			then the horizontal acceleration will be set to ax.
	 * 			| if (isVAlidAx(ax))
	 * 			|	then new.ax = ax
	 * @post	If ax is  not a valid horizontal acceleration,
	 * 			then the horizontal acceleration will be set to 0.
	 * 			| if (!isValidAx(ax))
	 * 			|	then new.ax = 0
	 */
	public void setAx(double ax) {
		if (isValidAx(ax))
			this.ax = ax;		
		else 
			this.ax = 0;		
	}
	
	/**
	 * Checks if ax is a valid horizontal acceleration.
	 * 
	 * @param ax
	 * 			The value to be checked.
	 * @result
	 * 			True if and only if ax is equal to 0 or (min) axi.
	 * 			| result = ((ax == 0) || (ax == axi) || (ax = -axi))
	 */
	public boolean isValidAx(double ax) {
		return ((ax == 0) || (ax == axi) || (ax == -axi));
	}
	
	private double ax;
	
	/**
	 * Gets the horizontal initial acceleration
	 */
	@Basic
	public double getAxi() {
		return Mazub.axi;
	}
	
	private final static double axi = 0.9;
	
	/**
	 * Gets the vertical acceleration of this Mazub.
	 */
	@Basic
	public double getAy() {
		return this.ay;
	}
	
	/**
	 * Sets the vertical acceleration of this Mazub to ay.
	 * 
	 * @param ay
	 * 			The new vertical acceleration of this Mazub.
	 * @post	If ay is a valid vertical acceleration,
	 * 			then the vertical acceleration will be set to ay.
	 * 			| if (isVAlidAy(ay)
	 * 			|	then new.ay = ay
	 * @post	If ay is  not a valid vertical acceleration,
	 * 			then the vertical acceleration will be set to 0.
	 * 			| if (!isValidAy(ay))
	 * 			|	then new.ay = 0
	 */
	
	public void setAy(double ay) {
		if (isValidAy(ay))
			this.ay = ay;		
		else 
			this.ay = 0;		
	}
	
	/**
	 * Checks if ay is a valid vertical acceleration.
	 * 
	 * @param ay
	 * 			the value to be checked
	 * @result	True if and only if ay is equal to 0 or -10.
	 * 			| result = ((ay == 0) || (ay == -10))
	 */
	public boolean isValidAy(double ay) {
		return ((ay == 0) || (ay == -10));
	}
	
	private double ay;
	
	/**
	 * Gets this Mazub's width.
	 * 
	 * @return	The current width in pixels of this Mazub.
	 * 			| result = getCurrentSprite().getWidth()
	 */
	@Basic
	public int getWidth() {
		return getCurrentSprite().getWidth();
	}
	
	/**
	 * Gets this Mazub's height.
	 * 
	 * @return	The current height in pixels of this Mazub.
	 * 			| result = getCurrentSprite().getHeight()
	 */
	@Basic
	public int getHeight() {
		return getCurrentSprite().getHeight();
	}
	
	/**
	 * Returns whether or not this Mazub is currently ducking.
	 */
	@Basic
	public boolean getDucking() {
		return this.ducking;
	}
	
	/**
	 * Sets this Mazub's state of ducking to the given state.
	 * 
	 * @param ducking
	 * 		Whether or not this Mazub should be ducking.
	 * @post	If ducking is true, this Mazub should be ducking, else it shouldn't be.
	 * 			| new.getDucking() == ducking
	 */
	public void setDucking(boolean ducking) {
		this.ducking = ducking;
	}
	
	private boolean ducking;
	
	/**
	 * Gets the variable specifying the last move. If it's smaller than 0, this Mazub's has moved to the left during the last second,
	 * if it's larger than 0, this Mazub's has moved to the right during the last second. If it's zero this Mazub's hasn't moved
	 * horizontally at all during the last second.
	 */
	@Basic
	public double getLastMove() {
		return this.last_move;
	}
	
	/**
	 * Sets the variable specifying the last move.
	 * 
	 * @param last_move
	 * 		The new value for the last move variable.
	 * @pre		The given last_move must be in between -1 and 1, inclusive.
	 * 			| (last_move >= -1 && last_move <= 1)
	 * @post	The last move variable will be equal to last_move.
	 * 			| new.getLastMove() == last_move
	 */
	public void setLastMove(double last_move) {
		assert(last_move >= -1 && last_move <= 1);
		this.last_move = last_move;
	}
	
	private double last_move;

	/**
	 * Gets the image array of this Mazub.
	 */
	@Basic
	public Sprite[] getImages() {
		return this.images;
	}
	
	private final Sprite[] images;
	
	/**
	 * Gets the amount of frames in this Mazub's running left/right animation.
	 * 
	 * @return The number of frames in both the animation of running left and running right, as a double so it can be compared more
	 * 			easily to the frame time.
	 * 			| result = (images.length - 8)/2
	 */
	public int getFramesAmount() {
		return (getImages().length - 8)/2;
	}

	/**
	 * Gets the animation time in seconds of this Mazub.
	 */
	@Basic
	public double getAnimationTime() {
		return this.animation_time;
	}
	
	/**
	 * Sets this Mazub's time within the animation.
	 * 
	 * @param animation_time
	 * 			This Mazub's new time within its running animation in seconds. Starts at 0, and goes up to the maximal animation time
	 * 			until it resets.
	 * @post	This Mazub's new animation time will be equal to the given animation time, possibly shifted by the maximal
	 * 			animation time to make sure it's in the right interval.
	 * 			| new.getAnimationTime() == getAnimationTime()%(getFramesAmount()*getFrameTime())
	 */
	public void setAnimationTime(double animation_time) {
		this.animation_time = animation_time;
		while (getAnimationTime() >= getFramesAmount()*getFrameTime()) {
			this.animation_time -= getFramesAmount()*getFrameTime();
		}
	}
	
	private double animation_time;
	
	/**
	 * Returns the time in between two frames.
	 */
	@Basic
	public static double getFrameTime() {
		return Mazub.frame_time;
	}
	
	private static final double frame_time = 0.075;
	
	/**
	 * Gets the index of the current frame in the running animation.
	 * 
	 * @return	The index of the current frame in the running animation.
	 * 			| result = (int) Math.floor(getAnimationTime()/getFrameTime())
	 */
	public int getCurrentFrameIndex() {
		return (int) Math.floor(getAnimationTime()/getFrameTime());
	}
	
	/**
	 * Gets the width of the game world.
	 */
	@Basic
	public static int getWorldWidth() {
		return world_width;
	}
	
	private static final int world_width = 1024;
	
	/**
	 * Gets the height of the game world.
	 */
	@Basic
	public static int getWorldHeight() {
		return world_height;
	}
	
	private static final int world_height = 768;
	
	/**
	 * Advances the time by a given time.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @post	The x-position will be advanced using the given time.
	 * 			| new.getX() == Math.max(0, Math.min(getWorldWidth() - 1, getX() + getVx()*dt + 1/2*getAx()*Math.pow(dt, 2)))
	 * @post	The horizontal velocity will be advanced using the given time.
	 * 			| new.getVx() == Math.max(-getVxmax(), Math.min(getVxmax(), getVx() + getAx()*dt))
	 * @post	The y-position will be advanced using the given time.
	 * 			| new.getY() == Math.max(0, Math.min(getWorldHeight() - 1, getY() + getVy()*dt + 1/2*getAy()*Math.pow(dt, 2)))
	 * @post	The vertical velocity will be advanced using the given time.
	 * 			| new.getVy() == getVy() + getAy()*dt
	 * @post	The vertical acceleration will be equal to -10 if this Mazub is in mid-air, else it will be 0.
	 * 			| if (getY() > 0)
	 * 			| 	then new.getAy() == -10
	 * 			| else
	 * 			| 	then new.getAy() == 0
	 * @post	The last movement time will be equal to -1 if this Mazub is moving to the left,
	 * 			1 if this Mazub is moving to the right, and if this Mazub is standing still the absolute value of the
	 * 			previous last movement time will be decreased by dt, unless it hits 0, in which case it will stick to 0.
	 * 			| if getVx() < 0
	 * 			|	then new.getLastMove() == -1
	 * 			| else if getVx() > 0
	 * 			| 	then new.getLastMove() == 1
	 * 			| else
	 * 			| 	if getLastMove() < 0
	 * 			|		then new.getLastMove() == Math.min(0, getLastMove() + dt)
	 * 			|	else if getLastMove() > 0
	 * 			|		then new.getLastMove() == Math.max(0, getLastMove() - dt)
	 * @effect	The animation time will be increased by dt.
	 * 			| setAnimationTime(getAnimationTime() + dt))
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		setX(advanceX(dt));
		setVx(advanceVx(dt));
		setY(advanceY(dt));
		setVy(advanceVy(dt));
		
		setAy(advanceAy());
		
		setLastMove(advanceLastMove(dt));
		setAnimationTime(advanceAnimationTime(dt));
	}

	/**
	 * Checks if a given time is a valid time interval to advance the time with.
	 * 
	 * @param dt
	 * 			The amount of seconds which should be checked.
	 * @return	Whether or not dt is in between 0 and 0.2 seconds, exclusively.
	 * 			| result = ((0 < dt) && (dt < 0.2))
	 */
	public static boolean isValidDt(double dt) {
		return ((0 < dt) && (dt < 0.2));
	}
	
	/**
	 * Returns the new x-position after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @return	The new x-position after dt time has passed.
	 * 			| max_s = maxAdvanceX(dt)
	 * 			| actual_s = maxAdvanceX(dt)
	 * 			| result = getXWithinBounds(getX() + (int) Math.round(Math.min(max_s, actual_s)))
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	private int advanceX(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double max_s = maxAdvanceX(dt);
		// This second formula can be changed, the first one is static.
		double actual_s = maxAdvanceX(dt);
		int newx = getX() + (int) Math.round(Math.min(max_s, actual_s));
		
		return getXWithinBounds(newx);
	}
	
	/**
	 * Gets an x-position within the bounds of the game world.
	 * 
	 * @param newx
	 * 			The x-position which should be converted to a valid one.
	 * @return	The new x-position. If it's already within the bounds, this will be equal to the given x.
	 * 			If it's too small or too large, it will be set to the smallest or largest possible value, respectively.
	 * 			| result = Math.max(0, Math.min(getWorldWidth() - 1, x))
	 */
	private int getXWithinBounds(int x) {
		return Math.max(0, Math.min(getWorldWidth() - 1, x));
	}
	
	/**
	 * Calculates the maximal change in x-position within a given period of time.
	 * Currently also used to calculate the actual change in x-position within a given period of time.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced.
	 * @return	The maximal change in x-position using dt, in pixels.
	 * 			| result = 100*(getVx()*dt + getAx()*Math.pow(dt, 2)/2)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	private double maxAdvanceX(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		return 100*(getVx()*dt + getAx()*Math.pow(dt, 2)/2);
	}
	
	/**
	 * Returns the new horizontal velocity after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @return	The new horizontal velocity after dt time has passed in meters per second.
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 * @throws IllegalStateException
	 * 			If the horizontal velocity after updating and capping is not valid.
	 * 			| !isValidVx(Math.max(-vxmax, Math.min(vxmax, getVx() + getAx()*dt)))
	 */
	private double advanceVx(double dt) throws IllegalArgumentException, IllegalStateException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double newvx = getVx() + getAx()*dt;
		newvx = Math.max(-vxmax, Math.min(vxmax, newvx));
		
		if (!isValidVx(newvx)) {
			throw new IllegalStateException();
		}
		return newvx;
	}
	
	/**
	 * Returns the new y-position after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @return	The new y-position after dt time has passed.
	 * 			| max_s = maxAdvanceX(dt)
	 * 			| actual_s = maxAdvanceX(dt)
	 * 			| result = (int) Math.round(Math.min(max_s, actual_s))
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	private int advanceY(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double max_s = maxAdvanceY(dt);
		// This second formula can be changed, the first one is static.
		double actual_s = maxAdvanceY(dt);
		int newy = getY() + (int) Math.round(Math.min(max_s, actual_s));
		
		return getYWithinBounds(newy);
	}
	
	/**
	 * Gets an y-position within the bounds of the game world.
	 * 
	 * @param newy
	 * 			The y-position which should be converted to a valid one.
	 * @return	The new y-position. If it's already within the bounds, this will be equal to the given y.
	 * 			If it's too small or too large, it will be set to the smallest or largest possible value, respectively.
	 * 			| result = Math.max(0, Math.min(getWorldHeight() - 1, y))
	 */
	private int getYWithinBounds(int y) {
		return Math.max(0, Math.min(getWorldHeight() - 1, y));
	}
	
	/**
	 * Calculates the maximal change in y-position within a given period of time.
	 * Currently also used to calculate the actual change in y-position within a given period of time.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced.
	 * @return	The maximal change in y-position using dt, in pixels.
	 * 			| result = 100*(getVy()*dt + getAy()*Math.pow(dt, 2)/2)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	private double maxAdvanceY(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		return 100*(getVy()*dt + getAy()*Math.pow(dt, 2)/2);
	}
	
	/**
	 * Returns the new vertical velocity after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @return	The new vertical velocity after dt time has passed in meters per second.
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 * @throws IllegalStateException
	 * 			If the vertical velocity after updating overflows negatively.
	 * 			| (newvy == Double.NEGATIVE_INFINITY)
	 */
	private double advanceVy(double dt) throws IllegalArgumentException, IllegalStateException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		if (getY() <= 0) {
			return 0;
		}
		
		double newvy = getVy() + getAy()*dt;
		
		if (newvy == Double.NEGATIVE_INFINITY) {
			throw new IllegalStateException();
		}
		return newvy;
	}
	
	/**
	 * Returns the new vertical acceleration at this Mazub's current y-position.
	 * 
	 * @return	The new vertical acceleration at this Mazub's current y-position has passed in meters per second squared.
	 * 			This will be -10 if this Mazub's y-position is larger than 0, else it'll be 0.
	 * 			| if (getY() == 0)
	 * 			| 	then result = -10
	 * 			| else
	 * 			|	result = getAy()
	 */
	private double advanceAy() {
		
		if (getY() > 0) {
			return -10;
		} else {
			return 0;
		}
	}
	
	/**
	 * Gets the last move variable using the given dt.
	 * 
	 * @param dt
	 * 			The period of time which has to be advanced, in seconds.
	 * @return	If this Mazub is moving right, 1, and if it's moving left, -1. If neither cases are true,
	 * 			it will add/subtract dt to/from the current last move variable in order to bring it closer to 0. If it surpasses 0,
	 * 			it will be set to zero.
	 * 			If the last move variable was already 0 to begin with and this Mazub isn't moving, this method will simply return 0.
	 * 			| if (getVx() > 0)
	 * 			| 	then result = 1
	 * 			| else if (getVx() > 0)
	 * 			| 	then result = -1
	 * 			| else if (getLastMove() > 0)
	 * 			| 	then result = Math.max(0, getLastMove() - dt)
	 * 			| else if (getLastMove() < 0)
	 * 			| 	then result = Math.min(0, getLastMove() + dt)
	 * 			| else then result = 0
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	private double advanceLastMove(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		if (getVx() > 0) {
			return 1;
		} else if (getVx() < 0) {
			return -1;
		}
		
		if (getLastMove() > 0) {
			return Math.max(0, getLastMove() - dt);
		} else if (getLastMove() < 0) {
			return Math.min(0, getLastMove() + dt);
		}
		return 0;
	}
	
	/**
	 * Returns the new animation time for this Mazub time after a given period of time has passed.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced, in seconds.
	 * @return	The current animation time plus the given dt.
	 * 			| result = getAnimationTime() + dt
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	private double advanceAnimationTime(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		return getAnimationTime() + dt;
	}
	
	/**
	 * Initiates horizontal movement of this Mazub.
	 * 
	 * @param direction
	 * 			the direction in which this Mazub starts moving.
	 * @pre		The given direction is equal to left or direction is equal to right.
	 * 			| direction == "left" || direction == "right"
	 * @post 	If direction is equal to right vx will be set to vxi and ax will be set to axi.
	 * 			| if (direction == "right")
	 * 			|	then (new.vx = vxi) && (new.ax = axi)
	 * @post 	If direction is equal to left vx will be set to -vxi and ax will be set to -axi.
	 * 			| if (direction == "left")
	 * 			|	then (new.vx = -vxi) && (new.ax = -axi)
	 */
	public void startMove(String direction){
		assert(direction == "left" || direction == "right");
		if (direction == "left"){
			setVx(-vxi);
			setAx(-axi);
		} else {
			setVx(vxi);
			setAx(axi);
		}
		setAnimationTime(0);
	}
	
	/**
	 * Stops the horizontal movement of this Mazub.
	 * 
	 * @post	vx and ax are set to 0.
	 * 			| new.vx == 0 && new.ax  == 0
	 */
	public void endMove(){
		setVx(0);
		setAx(0);
	}
	
	/**
	 * Starts a jump for this Mazub.
	 * 
	 * @post	This Mazub's vertical velocity will be equal to 8.
	 * 			| new.getVy() = 8
	 */
	public void startJump() {
		setVy(8);
	}
	
	/**
	 * Ends a jump for this Mazub.
	 * 
	 * @post	This Mazub's vertical velocity will be equal to or smaller than 0.
	 * 			| new.getVy() <= 0
	 */
	public void endJump() {
		setVy(Math.min(0, getVy()));
	}
	
	/**
	 * Sets this Mazub's ducking state to true.
	 * 
	 * @post	The new Mazub's ducking state will be true.
	 * 			| new.getDucking() == true
	 */
	public void startDuck() {
		setDucking(true);
		setVxmax(1);
	}

	/**
	 * Sets this Mazub's ducking state to false.
	 * 
	 * @post	The new Mazub's ducking state will be false.
	 * 			| new.getDucking() == false
	 */
	public void endDuck() {
		setDucking(false);
		setVxmax(3);
	}
	
	/**
	 * Gets the current sprite object given this Mazub's past and current horizontal velocity, vertical position and ducking state.
	 * 
	 * @return	The current sprite object fitting this Mazub's current state, which follows these rules:
	 * 			
	 * 			CORRESPONDING INDEX			STATE
	 * 			0							Not moving horizontally, not moving horizontally during the last second, not ducking.
	 * 			1							Not moving horizontally, not moving horizontally during the last second, ducking.
	 * 			2							Not moving horizontally, was moving right during the last second, not ducking.
	 * 			3							Not moving horizontally, was moving left during the last second, not ducking.
	 * 			4							Moving right, jumping, not ducking
	 * 			5							Moving left, jumping, not ducking
	 * 			6							Moving right or was moving right during the last second, ducking.
	 * 			7							Moving left or was moving left during the last second, ducking.
	 * 			8 ... 8 + m					Moving right, neither jumping nor ducking.
	 * 			9 + m ... 9 + 2*m			Moving left, neither jumping nor ducking.
	 * 			
	 * 			The m used above corresponds to getFramesAmount(), which is the amount of frames in both the running left and
	 * 			running right animations.
	 */
	public Sprite getCurrentSprite() {
		
		if (getVx() == 0) {
			
			// Everything which happens if Mazub's not moving horizontally.
			
			if (getLastMove() == 0) {
				// No last move
				if (getDucking()) {
					return getImages()[1];
				} else {
					return getImages()[0];
				}
			} else if (getLastMove() > 0) {
				// Last move was right.
				if (!getDucking()) {
					return getImages()[2];
				}
			} else {
				// Last move was left.
				if (!getDucking()) {
					return getImages()[3];
				}
			}
		} else if (getVx() > 0) {
			
			// Everything which happens if Mazub's moving right.
			
			if (!getDucking()) {
				if (getY() > 0) {
					return getImages()[4];
				} else {
					return getImages()[8 + getCurrentFrameIndex()];
				}
			}
			
		} else {
			
			// Everything which happens if Mazub's moving left.
			
			if (!getDucking()) {
				if (getY() > 0) {
					return getImages()[5];
				} else {
					return getImages()[8 + getFramesAmount() + getCurrentFrameIndex()];
				}
			}
		}
		
		if (getDucking()) {
			if (getVx() > 0 || getLastMove() > 0) {
				return getImages()[6];
			} else if (getVx() < 0 || getLastMove() < 0) {
				return getImages()[7];
			}
		}
		
		return getImages()[0];
	}
}