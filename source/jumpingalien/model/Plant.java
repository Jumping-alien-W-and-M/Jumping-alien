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
	 * 			the new x postion of this plant
	 * @pre		x must be a valid x position
	 * 			|isvalidx(x)
	 * @post	the new x position of this plant is equal to x
	 * 			| new.getX() = x
	 */
	private void setX(double x){
		assert(isvalid(x));
		this.x = x;
	}
	
	public boolean isValidx(double x){
		
	}
	private double x;
}
