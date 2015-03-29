package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;

public class Plant {
	
	/**
	 * Gets this plants x-position.
	 */
	@Basic
	public double getX(){
		return this.x;
	}
	
	/**
	 * Sets the x-position of this plant.
	 * 
	 * @param x
	 * 			the new x position of this plant
	 * @pre		x must be a valid x position
	 * 			|isvalidx(x)
	 * @post	the new x position of this plant is equal to x
	 * 			| new.getX() = x
	 */
	private void setX(double x){
		assert(isValidX(x));
		this.x = x;
	}
	
	
	/**
	 * Checks whether or not x is a valid x-position. 
	 * 
	 * @param x
	 * 			The x-position that which should be checked.
	 * @return	Returns true if x is between 0 and the world width.
	 * 			| result = (x >= 0 && x < getWorld().getWorldWidth())
	 */
	public boolean isValidX(double x){
		return(x >= 0 && x < getWorld().getWorldWidth());
	}
	
	private double x;
	
	/**
	 * Gets this plants horizontal velocity.
	 */
	@Basic
	public double getVx(){
		return this.vx;
	}
	
	/**
	 * Sets this plants horizontal velocity to vx
	 * 
	 * @param vx
	 * 			the new horizontal velocity of this plant
	 * @pre		vx must be a valid horizontal velocity
	 * 			| isValidvx(double vx)
	 * @post	the new horizontal velocity of this plant is equal to vx
	 * 			| new.getVx() = vx
	 */
	private void setVx(double vx){
		assert(isValidVx(vx));
		this.vx = vx;
	}
	
	/**
	 * Returns whether or not the given vx is a valid horizontal velocity.
	 * 
	 * @param vx
	 * 			The horizontal velocity wich should be checked.
	 * @return	true if the absolute value of vx is equal to 0.5	
	 * 			| result = return(Math.abs(vx) == 0.5)
	 */
	public boolean isValidVx(double vx){
		return(Math.abs(vx) == 0.5);
	}
	
	private double vx = 0.5;
	
	/**
	 * Gets the time this plant is moving in one direction.
	 */
	@Basic
	public double getMovementTime(){
		return this.movement_time;
	}
	
	/**
	 * Sets the time this plant is moving in one direction.
	 * 
	 * @param movement_time
	 * 				the new movement time of this plant
	 */
	private void setMovementTime(double movement_time){
		this.movement_time = movement_time;
	}
	
	private double movement_time;
	
	
	/**
	 * Gets the time this plant is been dead for.
	 */
	@Basic
	public double getDeathTime(){
		return this.death_time;
	}
	
	/**
	 * Sets the time this plant has been dead for.
	 * 
	 * @param deathtime
	 * 			The new time this plant has been dead for.
	 * @post	The new time this plant has been dead for is equal to deathtime.
	 * 			| new.getDeathTime() = deathtime 
	 */	
	private void setDeathTime(double deathtime){
		this.death_time = deathtime;
	}
	
	private double death_time;
	
	/**
	 * Gets the world this plant belongs to
	 */
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Sets this plants world to world.
	 * @param world
	 * 			the new world of this plant
	 * @post	the new world of this plant is equal to world
	 * 			| new.getWorld() = world
	 */
	private void setWorld(World world){
		this.world = world;
	}
	
	private World world;
	
	/**
	 * Advances the time by a given time period.
	 * 
	 * @param dt
	 * 			The amount of seconds to advance.
	 * @effect	if the current movement time exceeds 0.5 it will be reduced by 0.5 
	 * 			and the direction of this plant will be changed
	 * 			|if(getMovementTime() + dt > 0.5)
	 *			|	setMovementTime(getMovementTime() +dt - 0.5)
	 *			|	changeDirection()
	 *@effect	Advances the DeathTime of this plant.
	 *			|advanceDeathTime(dt)
	 *@effect	
	 */
	public void advanceTime(double dt)throws IllegalArgumentException{
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double timestep = 1 / Math.abs(getVx()/100); 
		for(double time = timestep; timestep <= dt; time += timestep){
			// collision detect toevoegen
			advanceDeathTime(time);
			advanceX(time);
			if(getMovementTime() + time > 0.5){
				setMovementTime(getMovementTime() + time - 0.5);
				changeDirection();			
			}
		}
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
	 * Returns the x-position of this plant after a given time. 
	 * @param dt
	 * 			The time to advance.
	 * @return	The new x-position after dt time has passed.
	 * 			| result = getXWithinBounds(getX() + GetVx()*dt)
	 * @throws 	IllegalArgumentException
	 * 			if dt is not a valid time
	 * 			| !isValidDt(dt)
	 */
	private void advanceX(double dt)throws IllegalArgumentException{
		if(!isValidDt(dt))
			throw new IllegalArgumentException();
		
		double newx = getX() + getVx()*dt;
		setXWithinBounds(newx);
	}
	
	/**
	 * Returns an x-position withing the boundaries of the world and if necessary kills the plant.
	 * 
	 * @param x
	 * 			The x-position which should be converted to a valid one.
	 * @return	The new x-position if is already was within bound it will be equal to x
	 * 			| result = Math.max(0, Math.min(x, getWorld().getWorldWidth() - 1))
	 */
	private void setXWithinBounds(double x){
		setX( Math.max(0, Math.min(x, getWorld().getWorldWidth() - 1)));
		if(x < 0 || x >= getWorld().getWorldWidth())
			kill();
	}
	
	private void advanceDeathTime(double time){
		if(getDeathTime() != 0){
			if (getDeathTime() - time < 0)
				terminate();
			else
				setDeathTime(getDeathTime() - time);
		}
	}
	
	private void kill(){
		setDeathTime(0.6);
	}
	
	private void terminate(){
	}
	
	
	/**
	 * Sets this plants horizontal velocity to -0.5
	 * 
	 * @post	this plants new horizontal velocity is equal to -0.5
	 * 			| new.getVx() = -0.5
	 */
	private void startMoveLeft(){
		setVx(-0.5);		
	}
	
	/**
	 * Sets this plants horizontal velocity to 0.5
	 * 
	 * @post	this plants new horizontal velocity is equal to 0.5
	 * 			| new.getVx() = 0.5
	 */
	private void startMoveRight(){
		setVx(0.5);		
	}
	
	/**
	 * Changes the direction in which this plant is moving.
	 * 
	 * @post	the new horizontal velocity is equal to the negative of the old horizontal velocity
	 * 			| new.getVx() = -this.getVx()
	 */
	private void changeDirection(){
			setVx(-getVx());
	}
}
