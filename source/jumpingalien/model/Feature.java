package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public enum Feature {
	
	water, magma, air, ground;
	
	public boolean isPassable(){
		return (this != ground);
	}
}
