package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;

public abstract class GameObject {
	
	public GameObject(double x, double y, Sprite[] images){
		setX(x);
		setY(y);
		this.images = images;
		
		setVx(0);
		setVy(0);
		setAx(0);
		setAy(0);
	}
	
	/**
	 * Gets the x-position of this game object.
	 */
	@Basic
	public double getX() {
		return this.x;
	}
	
	/**
	 * Sets the x-position of this game object.
	 * 
	 * @param x
	 * 			This game object's new x-position in pixels.
	 * @post	This game object's new x-position will be equal to the given x.
	 * 			| new.getX() == x
	 * @throws	IllegalArgumentException
	 * 			Throws an exception if x is outside the game world.
	 * 			| !isValidX(x)
	 */
	protected void setX(double x) throws IllegalArgumentException {
		if (!isValidX(x)) {
			throw new IllegalArgumentException();
		}
		this.x = x;
	}
	
	/**
	 * Checks if the given x is a valid x-position for this game object in the game world.
	 * 
	 * @param x
	 * 			The x-position in pixels which should be checked.
	 * @return	Whether or not the given x is within the game world.
	 * 			| result = (x >= 0 && x < getWorldWidth)
	 */
	public boolean isValidX(double x) {
		return (x >= 0 && x < getWorld().getWorldWidth());
	}
	
	private double x;
	
	/**
	 * Gets the y-position of this game object.
	 */
	@Basic
	public double getY() {
		return this.y;
	}
	
	/**
	 * Sets the y-position of this game object.
	 * 
	 * @param y
	 * 			This game object's new y-position in pixels.
	 * @post	This game object's new y-position will be equal to the given y.
	 * 			| new.getY() == y
	 * @throws	IllegalArgumentException
	 * 			If y is outside the game world.
	 * 			| !isValidY(y)
	 */
	protected void setY(double y) throws IllegalArgumentException {
		if (!isValidY(y)) {
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
	/**
	 * Checks if the given y is a valid y-position for this game object in the game world.
	 * 
	 * @param y
	 * 			The y-position in pixels which should be checked.
	 * @return	Whether or not the given y is within the game world.
	 * 			| result = (y >= 0 && y < getWorldHeight())
	 */
	public boolean isValidY(double y) {
		return (y >= 0 && y < getWorld().getWorldHeight()); 
	}
	
	private double y;
	
	/**
	 * Gets the horizontal velocity of this game object.
	 */
	@Basic
	public double getVx() {
		return this.vx;
	}
	
	/**
	 * Sets the horizontal velocity of this game object.
	 * 
	 * @param vx
	 * 			The new horizontal velocity of this game object in meters per second (1m = 100 pixels).
	 * @pre		The given vx must be in between -vxmax and vxmax.
	 * 			| isValidVx(vx)
	 * @post	The new horizontal velocity of this game object is equal to vx.
	 * 			| new.getVx() = vx
	 */
	protected void setVx(double vx) {
		assert isValidVx(vx);
		this.vx = vx;
	}
	
	/**
	 * Checks if the given vx is a valid horizontal velocity.
	 * 
	 * @param vx
	 * @return Whether or not the magnitude of vx is equal to 0 or not smaller than the initial horizontal velocity and not larger than
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
	 * Gets the magnitude of the maximal horizontal velocity this game object can achieve when moving.
	 */
	@Basic
	public double getVxmax() {
		return this.vxmax;
	}
	
	/**
	 * Sets this game object's maximal horizontal velocity to a given vxmax.
	 * 
	 * @param vxmax
	 * 			The maximal horizontal velocity this game object should have.
	 * @post	The new game object's vxmax should be equal to the given max.
	 * 			| new.getVxmax() == vxmax
	 */
	protected void setVxmax(double vxmax) {
		this.vxmax = vxmax;
	}
	
	private double vxmax;
	
	/**
	 * Gets the vertical velocity of this game object.
	 */
	@Basic
	public double getVy() {
		return this.vy;
	}
	
	/**
	 * Sets the vertical velocity of this game object.
	 * 
	 * @param vy
	 * 			This game object's new vertical velocity in meters per second (1m = 100 pixels),
	 * 			which has to be smaller than or equal to 8 meters per second.
	 * @post	This game object's new vertical velocity will be equal to the given vy, unless it's larger than 8 meters per second,
	 * 			in which case the new vertical velocity will be equal to 8 meters per second.
	 * 			| if (vy <= 8)
	 * 			| 	then new.getVy() = vy
	 *			| else
	 *			|	then new.getVy() = 8
	 *
	 */
	protected void setVy(double vy) {
		if (vy <= 8) {
			this.vy = vy;
		} else {
			this.vy = 8;
		}
	}
	
	protected double vy;
	
	/**
	 * Gets the horizontal acceleration of this game object.
	 */
	@Basic
	public double getAx() {
		return this.ax;
	}
	
	/**
	 * Sets the horizontal acceleration of this game object to ax.
	 * 
	 * @param ax
	 * 			The new horizontal acceleration of this game object.
	 * @post	If ax is a valid horizontal acceleration,
	 * 			then the horizontal acceleration will be set to ax.
	 * 			| if (isVAlidAx(ax))
	 * 			|	then new.getAx() = ax
	 * @post	If ax is  not a valid horizontal acceleration,
	 * 			then the horizontal acceleration will be set to 0.
	 * 			| if (!isValidAx(ax))
	 * 			|	then new.getAx() = 0
	 */
	protected void setAx(double ax) {
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
	protected double getAxi() {
		return this.axi;
	}
	
	private double axi = 0.9;
	
	/**
	 * Gets the vertical acceleration of this game object.
	 */
	@Basic
	public double getAy() {
		return this.ay;
	}
	
	/**
	 * Sets the vertical acceleration of this game object to ay.
	 * 
	 * @param ay
	 * 			The new vertical acceleration of this game object.
	 * @post	If ay is a valid vertical acceleration,
	 * 			then the vertical acceleration will be set to ay.
	 * 			| if (isVAlidAy(ay)
	 * 			|	then new.getAy() = ay
	 * @post	If ay is  not a valid vertical acceleration,
	 * 			then the vertical acceleration will be set to 0.
	 * 			| if (!isValidAy(ay))
	 * 			|	then new.getAy() = 0
	 */
	protected void setAy(double ay) {
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
	 * Gets this game object's width.
	 * 
	 * @return	The current width in pixels of this game object.
	 * 			| result = getCurrentSprite().getWidth()
	 */
	@Basic
	public int getWidth() {
		return getCurrentSprite().getWidth();
	}
	
	/**
	 * Gets this Mazub's height.
	 * 
	 * @return	The current height in pixels of this game object.
	 * 			| result = getCurrentSprite().getHeight()
	 */
	@Basic
	public int getHeight() {
		return getCurrentSprite().getHeight();
	}
	
	/**
	 * Gets the image array of this game object.
	 */
	@Basic @Immutable
	public Sprite[] getImages() {
		return this.images;
	}
	
	private final Sprite[] images;
	
	/**
	 * Gets the world this game object is in.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Sets the world this game object is in.
	 * 
	 * @param world
	 * 			The world this game object should be in.
	 * @post	This game object is in the given world.
	 * 			| (getWorld() == world)
	 */
	protected void setWorld(World world) {
		this.world = world;
	}
	
	private World world;
	
	/**
	 * Gets hits game object hitpoints.	
	 */
	@Basic
	public int getHitpoints(){
		return this.hitpoints;
	}
	
	protected abstract void setHitpoints();
	
	private int hitpoints;
	
	/**
	 * Gets this game object time in water.
	 */
	private double getTimeInWater(){
		return this.time_in_water;
	}
	
	/**
	 * Sets this game object time in water.
	 * 
	 * @param timeinwater
	 * 			the new time in water of this game object
	 * @post	The timeinwater of this game object will be equal to timeinwater.
	 * 			| new.getTimeinwater() = timeinwater
	 * @pre		the timeinwater parameter must be larger then or equal to zero
	 * 			| timeinwater >= 0
	 */
	private void setTimeInWater(int timeinwater){
		assert( timeinwater >= 0);
		this.time_in_water = timeinwater;
	}
	
	private double time_in_water;
	
	/**
	 * Gets this game object time in magma.
	 */
	private double getTimeInMagma(){
		return this.time_in_magma;
	}
	
	/**
	 * Sets this game object time in magma.
	 * 
	 * @param timeinmagma
	 * 			the new time in magma of this game object
	 * @post	The timeinmagma of this game object will be equal to timeinmagma.
	 * 			| new.getTimeinmagma() = timeinmagma
	 *  @pre	the timeinmagma parameter must be larger then or equal to zero
	 * 			| timeinmagma >= 0
	 */
	private void setTimeInMagma(int timeinmagma){
		assert( timeinmagma >= 0);
		this.time_in_magma = timeinmagma;
	}
	
	private double time_in_magma;
	 
	protected abstract void terminate();
	
	public abstract void advanceTime(double dt)throws IllegalArgumentException;
	
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
	 * Gets the timestep necessary to move this game object pixel by pixel.
	 * 
	 * @return	If this game object ax en ay is equal to zero, the result will be the minimum of 4 formulas.
	 * 			1) 1 divided by the absolute value of, this mazubs horizontal velocity divided by 100.
	 * 			2) 1 divided by the absolute value of, this mazubs vertical velocity divide by 100.
	 * 			3) the result of computeformula(v,a) with as parameters 
	 * 				this game object's horizontal accelartion and velocity
	 * 			4) the result of computeformula(v,a) with as parameters 
	 * 				this game object's vertical accelartion and velocity
	 * 			| result =  Math.min(computeformula(getVx,getAx)
	 * 			|				Math.min(computeformula(getVy,getAy),
	 * 			|					Math.min( 1/Math.abs(getVx()/100),1/Math.abs(getVy()/100)))
	 */
	private double getTimesstep(){
		double firstparameter = Math.min( 1/Math.abs(getVx()/100),1/Math.abs(getVy()/100));		
		double secondparameter = computeformula(getVx(),getAx());		
		double thirdparameter = computeformula(getVy(),getAy());
		
		return Math.min(firstparameter,Math.min(secondparameter,thirdparameter));
	}
	
	/**
	 * Computes a formula for the calculation of timesteps().
	 * 
	 * @param v
	 * 			the velocity to compute the formula with
	 * @param a
	 * 			the acceleration to compute the formula with	 * 
	 * @return	if a is not equal to zero
	 * 			The square root of two times the absolute value of, a divided by 100, 
	 * 			plus ,the square of v divide by 100, min the absolute value of, v divided by 100. 
	 * 			This square root divide by the absolute value of, a divide by 100.
	 * 			| if a != 0
	 * 			|	then result = Math.sqrt(2*Math.abs(a / 100) + Math.pow(v,2)/100 - Math.abs(v/100))
	 *			| 			/Math.abs(a / 100)
	 *@return 	if a is equal to zero 
	 *			Double.POSITIVE_INFINITY
	 *			| if a == 0
	 *			|  then result = Double.POSITIVE_INFINITY 
	 */
	private double computeformula(double v, double a){
		if(a == 0)
			return Double.POSITIVE_INFINITY;
		double helpparameter = Math.abs(a / 100);
		return Math.sqrt(2*helpparameter + Math.pow(v,2)/100 - Math.abs(v/100))
				/helpparameter;
	}
	
	protected abstract void collisionhandle(Object[][] collisions);
	
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
	@Model
	private void advanceX(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double max_s = maxAdvanceX(dt);
		// This second formula can be changed, the first one is static.
		double actual_s = maxAdvanceX(dt);
		double newx = getX() + Math.min(max_s, actual_s);
		
		setXWithinBounds(newx);
	}
	
	/**
	 * Gets an x-position within the bounds of the game world.
	 * 
	 * @param x
	 * 			The x-position which should be converted to a valid one.
	 * @return	The new x-position. If it's already within the bounds, this will be equal to the given x.
	 * 			If it's too small or too large, it will be set to the smallest or largest possible value, respectively.
	 * 			| result = Math.max(0, Math.min(getWorldWidth() - 1, x))
	 */
	private void setXWithinBounds(double x) {
		if(x >= 0 || x <= getWorld().getWorldWidth()- 1)
			setX(x);
		terminate();
		setX(Math.max(0, Math.min(getWorld().getWorldWidth() - 1, x)));
	}
	
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
	 * 			| result = Math.max(-vxmax, Math.min(vxmax, getVx() + getAx()*dt))
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	private double advanceVx(double dt) throws IllegalArgumentException, IllegalStateException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double newvx = getVx() + getAx()*dt;
		newvx = Math.max(-vxmax, Math.min(vxmax, newvx));
		
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
	 * 			| result = getYWithinBounds(getY() + (int) Math.round(Math.min(max_s, actual_s)))
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	private void advanceY(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double max_s = maxAdvanceY(dt);
		// This second formula can be changed, the first one is static.
		double actual_s = maxAdvanceY(dt);
		double newy = getY() + Math.min(max_s, actual_s);
		
		setYWithinBounds(newy);
	}
	
	/**
	 * Gets an y-position within the bounds of the game world.
	 * 
	 * @param y
	 * 			The y-position which should be converted to a valid one.
	 * @return	The new y-position. If it's already within the bounds, this will be equal to the given y.
	 * 			If it's too small or too large, it will be set to the smallest or largest possible value, respectively.
	 * 			| result = Math.max(0, Math.min(getWorldHeight() - 1, y))
	 */
	private void setYWithinBounds(double y) {
		if(y >= 0 || y <= getWorld().getWindowHeight()-1)
			setY(y);
		terminate();
		setY(Math.max(0, Math.min(getWorld().getWorldHeight() - 1, y)));
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
	 * 			| result = getVy() + getAy()*dt
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 * @throws IllegalStateException
	 * 			If the vertical velocity after updating overflows negatively.
	 * 			| (newvy == Double.NEGATIVE_INFINITY)
	 */
	@Model
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
	 * Returns the new vertical acceleration at this game object's current y-position.
	 * 
	 * @return	The new vertical acceleration at this game object's current y-position has passed in meters per second squared.
	 * 			This will be -10 if this game object's y-position is larger than 0, else it'll be 0.
	 * 			| if (getY() == 0)
	 * 			| 	then result = -10
	 * 			| else
	 * 			|	result = getAy()
	 */
	@Model
	private double advanceAy() {
		
		if (canjump() || (int) getY() == getWorld().getWorldHeight() -1 ) {
			return 0;
		} else {
			return -10;
		}
	}
	
	public Sprite getCurrentSprite() {
		if(this.getVx() <= 0){
			return images[0];
		}
		return images[1];
	}
}
