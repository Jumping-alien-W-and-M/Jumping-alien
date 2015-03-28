package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {
	
	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites)throws ModelException {
		try{
			new Mazub(pixelLeftX,pixelBottomY,sprites);
		}catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		}
		return new Mazub(pixelLeftX,pixelBottomY,sprites);
		
	}

	@Override
	public int[] getLocation(Mazub alien) {
		int [] location = {(int)alien.getX(), (int) alien.getY()};
		return location;
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		double[] velocity = {alien.getVx(),alien.getVy()};
		return velocity;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		double[] acceleration = {alien.getAx(),alien.getAy()};
		return acceleration;
	}

	@Override
	public int[] getSize(Mazub alien) {
		int[] size = {alien.getWidth(),alien.getHeight()};
		return size;
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		alien.startJump();
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove("left");
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove();
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove("right");
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove();
	}

	@Override
	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	@Override
	public void endDuck(Mazub alien) {
		alien.endDuck();
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		try{
			alien.advanceTime(dt);
		}catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		}
	}
}
