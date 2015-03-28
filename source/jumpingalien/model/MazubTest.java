package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.*;
import jumpingalien.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MazubTest {

	@Before
	public void setUp() throws Exception {
		this.basic_alien = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET);
	}
	
	private Mazub basic_alien;
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestBasicConstructorCorrectParameters() {
		assertEquals(basic_alien.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getY(), 0, Util.DEFAULT_EPSILON);
		assertArrayEquals(basic_alien.getImages(), JumpingAlienSprites.ALIEN_SPRITESET);
		assertEquals(basic_alien.getVx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getVxmax(), 3, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getVy(), 0, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAy(), 0, Util.DEFAULT_EPSILON);

		assertEquals(basic_alien.getLastMove(), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestConstructorIllegalCoordinates() {
		
		try {
			Mazub alien = new Mazub(-5, 0, JumpingAlienSprites.ALIEN_SPRITESET);
			alien.setX(alien.getX());
		} catch (IllegalArgumentException exc1) {
			try {
				Mazub alien = new Mazub(1500, 0, JumpingAlienSprites.ALIEN_SPRITESET);
				alien.setX(alien.getX());
			} catch (IllegalArgumentException exc2) {
				try {
					Mazub alien = new Mazub(0, -10, JumpingAlienSprites.ALIEN_SPRITESET);
					alien.setX(alien.getX());
				} catch (IllegalArgumentException exc3) {
					try {
						Mazub alien = new Mazub(0, 1000, JumpingAlienSprites.ALIEN_SPRITESET);
						alien.setX(alien.getX());
					} catch (IllegalArgumentException exc4) {
						assert(true);
						return;
					}
				}
			}
		}
		assert(false);
	}
	
	@Test
	public void TestCoordinateSettersCorrectParameters() {
		
		basic_alien.setX(10);
		assertEquals(basic_alien.getX(), 10, Util.DEFAULT_EPSILON);
		basic_alien.setY(100);
		assertEquals(basic_alien.getY(), 100, Util.DEFAULT_EPSILON);
		
		basic_alien.setX(0);
		assertEquals(basic_alien.getX(),0, Util.DEFAULT_EPSILON);
		basic_alien.setY(0);
		assertEquals(basic_alien.getY(),0, Util.DEFAULT_EPSILON);
		
		basic_alien.setX(1023);
		assertEquals(basic_alien.getX(), 1023, Util.DEFAULT_EPSILON);
		basic_alien.setY(767);
		assertEquals(basic_alien.getY(), 767, Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void TestCoordinateSettersIllegalParameters() {
		
		try {
			basic_alien.setX(-1);
		} catch (IllegalArgumentException exc1) {
			try {
				basic_alien.setX(1024);
			} catch (IllegalArgumentException exc2) {
				try {
					basic_alien.setY(-1);
				} catch (IllegalArgumentException exc3) {
					try {
						basic_alien.setY(768);
					} catch (IllegalArgumentException exc4) {
						assert(true);
						return;
					}
				}
			}
		}
		assert(false);
		
	}

	@Test
	public void TestValidCoordinates() {
		
		assertEquals(basic_alien.isValidX(-1), false);
		assertEquals(basic_alien.isValidX(0), true);
		assertEquals(basic_alien.isValidX(1023), true);
		assertEquals(basic_alien.isValidX(1024), false);
		
		assertEquals(basic_alien.isValidY(-1), false);
		assertEquals(basic_alien.isValidY(0), true);
		assertEquals(basic_alien.isValidY(767), true);
		assertEquals(basic_alien.isValidY(768), false);
		
	}
	
	@Test
	public void TestVelocitySetters() {

		basic_alien.setVx(-3);
		assertEquals(basic_alien.getVx(), -3, Util.DEFAULT_EPSILON);
		basic_alien.setVx(-1);
		assertEquals(basic_alien.getVx(), -1, Util.DEFAULT_EPSILON);
		basic_alien.setVx(0);
		assertEquals(basic_alien.getVx(), 0, Util.DEFAULT_EPSILON);
		basic_alien.setVx(1);
		assertEquals(basic_alien.getVx(), 1, Util.DEFAULT_EPSILON);
		basic_alien.setVx(3);
		assertEquals(basic_alien.getVx(), 3, Util.DEFAULT_EPSILON);

		basic_alien.setVy(-16);
		assertEquals(basic_alien.getVy(), -16, Util.DEFAULT_EPSILON);
		basic_alien.setVy(-8);
		assertEquals(basic_alien.getVy(), -8, Util.DEFAULT_EPSILON);
		basic_alien.setVy(0);
		assertEquals(basic_alien.getVy(), 0, Util.DEFAULT_EPSILON);
		basic_alien.setVy(8);
		assertEquals(basic_alien.getVy(), 8, Util.DEFAULT_EPSILON);
		basic_alien.setVy(16);
		assertEquals(basic_alien.getVy(), 8, Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void TestVxmaxIsValidX() {
		
		assertEquals(basic_alien.isValidVx(-6), false);
		assertEquals(basic_alien.isValidVx(-3), true);
		assertEquals(basic_alien.isValidVx(-2), true);
		assertEquals(basic_alien.isValidVx(-1), true);
		assertEquals(basic_alien.isValidVx(-0.5), false);
		assertEquals(basic_alien.isValidVx(0), true);
		assertEquals(basic_alien.isValidVx(0.5), false);
		assertEquals(basic_alien.isValidVx(1), true);
		assertEquals(basic_alien.isValidVx(2), true);
		assertEquals(basic_alien.isValidVx(3), true);
		assertEquals(basic_alien.isValidVx(6), false);
		
		basic_alien.setVxmax(5);
		
		assertEquals(basic_alien.isValidVx(-6), false);
		assertEquals(basic_alien.isValidVx(-5), true);
		assertEquals(basic_alien.isValidVx(-2), true);
		assertEquals(basic_alien.isValidVx(-1), true);
		assertEquals(basic_alien.isValidVx(-0.5), false);
		assertEquals(basic_alien.isValidVx(0), true);
		assertEquals(basic_alien.isValidVx(0.5), false);
		assertEquals(basic_alien.isValidVx(1), true);
		assertEquals(basic_alien.isValidVx(2), true);
		assertEquals(basic_alien.isValidVx(5), true);
		assertEquals(basic_alien.isValidVx(6), false);
		
		basic_alien.setVxmax(3);
		
		assertEquals(basic_alien.isValidVx(-6), false);
		assertEquals(basic_alien.isValidVx(-3), true);
		assertEquals(basic_alien.isValidVx(-2), true);
		assertEquals(basic_alien.isValidVx(-1), true);
		assertEquals(basic_alien.isValidVx(-0.5), false);
		assertEquals(basic_alien.isValidVx(0), true);
		assertEquals(basic_alien.isValidVx(0.5), false);
		assertEquals(basic_alien.isValidVx(1), true);
		assertEquals(basic_alien.isValidVx(2), true);
		assertEquals(basic_alien.isValidVx(3), true);
		assertEquals(basic_alien.isValidVx(6), false);
		
	}
	
	@Test
	public void TestMoving() {
		
		basic_alien.setX(500);
		double pos = basic_alien.getX();
		
		basic_alien.startMove("left");

		assertEquals(basic_alien.getVx(), -1, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAx(), -0.9, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAnimationTime(), 0, Util.DEFAULT_EPSILON);
		
		basic_alien.advanceTime(0.1);
		
		basic_alien.endMove();

		assertEquals(basic_alien.getVx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(pos > basic_alien.getX(), true);
		pos = basic_alien.getX();
		
		basic_alien.startMove("right");

		assertEquals(basic_alien.getVx(), 1, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAx(), 0.9, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAnimationTime(), 0, Util.DEFAULT_EPSILON);
		
		basic_alien.advanceTime(0.1);
		
		basic_alien.endMove();

		assertEquals(basic_alien.getVx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(basic_alien.getAx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(pos < basic_alien.getX(), true);
	}
	
	@Test
	public void TestJumpingFalling() {
		
		double pos = basic_alien.getY();
		
		basic_alien.startJump();

		assertEquals(basic_alien.getVy(), 8, Util.DEFAULT_EPSILON);
		
		basic_alien.advanceTime(0.1);
		
		basic_alien.endJump();

		assertEquals(basic_alien.getVy(), 0, Util.DEFAULT_EPSILON);
		assertEquals(pos < basic_alien.getY(), true);
		pos = basic_alien.getY();
		
		basic_alien.startJump();
		
		assertEquals(basic_alien.getVy(), 8, Util.DEFAULT_EPSILON);
		
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		
		basic_alien.endJump();

		assertEquals(basic_alien.getVy(), -2, Util.DEFAULT_EPSILON);
		assertEquals(pos < basic_alien.getY(), true);
		pos = basic_alien.getY();
		
		basic_alien.advanceTime(0.1);
		
		assertEquals(pos > basic_alien.getY(), true);
	}
	
	@Test
	public void TestDucking() {
		
		basic_alien.startDuck();
		assertEquals(basic_alien.getDucking(), true);
		basic_alien.endDuck();
		assertEquals(basic_alien.getDucking(), false);
		
	}
	
	@Test
	public void TestGetCurrentSprite() {
		
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[0]);
		
		basic_alien.startDuck();
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[1]);
		basic_alien.endDuck();
		
		basic_alien.startMove("right");
		basic_alien.advanceTime(0.1);
		basic_alien.endMove();
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[2]);
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[2]);
		basic_alien.advanceTime(0.1);
		assertNotEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[2]);
		
		basic_alien.startMove("left");
		basic_alien.advanceTime(0.1);
		basic_alien.endMove();
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[3]);
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[3]);
		basic_alien.advanceTime(0.1);
		assertNotEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[3]);
		
		basic_alien.startMove("right");
		basic_alien.startJump();
		basic_alien.advanceTime(0.1);
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[4]);
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[4]);
		basic_alien.endJump();
		basic_alien.endMove();
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		
		basic_alien.startMove("left");
		basic_alien.startJump();
		basic_alien.advanceTime(0.1);
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[5]);
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[5]);
		basic_alien.endJump();
		basic_alien.endMove();
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		
		basic_alien.startDuck();
		
		basic_alien.startMove("right");
		basic_alien.advanceTime(0.1);
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[6]);
		basic_alien.endMove();
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[6]);
		basic_alien.advanceTime(0.1);
		assertNotEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[6]);
		
		basic_alien.startMove("left");
		basic_alien.advanceTime(0.1);
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[7]);
		basic_alien.endMove();
		for(int i = 0; i < 10; i++) {
			basic_alien.advanceTime(0.1);
		}
		assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[7]);
		basic_alien.advanceTime(0.1);
		assertNotEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[7]);
		
		basic_alien.endDuck();

		assertEquals(basic_alien.getY() > 0, false);
		assertEquals(basic_alien.getDucking(), false);
		
		basic_alien.startMove("right");
		basic_alien.advanceTime(0.01);
		
		for(int i = 0; i < 25; i++) {
			assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()[8 + i%basic_alien.getFramesAmount()]);
			basic_alien.advanceTime(0.075);
		}
		
		basic_alien.endMove();
		
		basic_alien.startMove("left");
		basic_alien.advanceTime(0.01);
		
		for(int i = 0; i < 25; i++) {
			assertEquals(basic_alien.getCurrentSprite(), basic_alien.getImages()
					[8 + basic_alien.getFramesAmount() + i%basic_alien.getFramesAmount()]);
			basic_alien.advanceTime(0.075);
		}
		
		basic_alien.endMove();
	}@Test
	public void TestsetAxcorrectparameters(){
		basic_alien.setAx(basic_alien.getAxi());
		assertEquals(basic_alien.getAx(),basic_alien.getAxi(),Util.DEFAULT_EPSILON);
		basic_alien.setAx(-basic_alien.getAxi());
		assertEquals(basic_alien.getAx(),-basic_alien.getAxi(),Util.DEFAULT_EPSILON);
		basic_alien.setAx(0);
		assertEquals(basic_alien.getAx(),0,Util.DEFAULT_EPSILON);		
	}
	
	@Test
	public void TestsetAxillegalparameters(){
		try {
			basic_alien.setAx(0.1);
		} catch (IllegalArgumentException exc1) {
			try {
				basic_alien.setAx(-0.1);
			} catch (IllegalArgumentException exc2) {
				try {
					basic_alien.setAx(1);
				} catch (IllegalArgumentException exc3) {
					try {
						basic_alien.setAx(-1);
					} catch (IllegalArgumentException exc4) {
						assert(true);
						return;
					}
				}
			}
		}
		assert(false);
	}
	
	@Test
	public void TestisValidAxvalidAx(){
		assert(basic_alien.isValidAx(0));
		assert(basic_alien.isValidAx(0.9));
		assert(basic_alien.isValidAx(-0.9));
	}
	
	@Test
	public void TestisValidAxillegalAx(){
		assert(!basic_alien.isValidAx(0.1));
	}
	
	@Test
	public void TestsetAYcorrectparameters(){
		basic_alien.setAy(0);
		assertEquals(basic_alien.getAy(),0,Util.DEFAULT_EPSILON);
		basic_alien.setAy(-10);
		assertEquals(basic_alien.getAy(),-10,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestsetAYillegalparameters(){
		try {
			basic_alien.setAy(0.1);
		} catch (IllegalArgumentException exc1) {
			try {
				basic_alien.setAy(-0.1);
			} catch (IllegalArgumentException exc2) {
				try {
					basic_alien.setAy(100);
				}catch (IllegalArgumentException exc4) {
						assert(true);
						return;					
				}
			}
		}
		assert(false);
		
	}
	
	@Test
	public void TestisValidAyvalidAy(){
		assert(basic_alien.isValidAy(0));
		assert(basic_alien.isValidAy(-10));
	}
	
	@Test
	public void TestisValidAyillegalAy(){
		assert(!basic_alien.isValidAy(5));
	}
	
	@Test
	public void TestsetDuckingcorrectparameter(){
		basic_alien.setDucking(true);
		assert(basic_alien.getDucking());
		basic_alien.setDucking(false);
		assert(!basic_alien.getDucking());
	}
	
	@Test
	public void TestsetLastMovecorrectparameters(){
		basic_alien.setLastMove(-1);
		assert(basic_alien.getLastMove() == -1);
		basic_alien.setLastMove(1);
		assert(basic_alien.getLastMove() == 1);
		basic_alien.setLastMove(0.5);
		assert(basic_alien.getLastMove() == 0.5);		
	}
	
	@Test
	public void TestsetLastMoveillegalparameters(){
		try {
			basic_alien.setLastMove(50);
		} catch (IllegalArgumentException exc1) {
			try {
				basic_alien.setAx(-50);
			} catch (IllegalArgumentException exc4) {
						assert(true);
						return;					
			}
		}
		assert(false);
	}
	
	@Test
	public void Testgetframesamount(){
		assert(basic_alien.getFramesAmount() == (basic_alien.getImages().length - 8) /2);
	}
	
	@Test
	public void TestsetAnimationTimesparametersmallerthenthehighestreachableanimationtime(){
		basic_alien.setAnimationTime(basic_alien.getFramesAmount()*Mazub.getFrameTime()-0.1);
		assert(basic_alien.getAnimationTime() 
				== basic_alien.getAnimationTime()%(basic_alien.getFramesAmount()*Mazub.getFrameTime()));
	}
	
	@Test
	public void TestsetAnimationTimesparameterequaltothehighestreachableanimationtime(){
		basic_alien.setAnimationTime(basic_alien.getFramesAmount()*Mazub.getFrameTime());
		assert(basic_alien.getAnimationTime() 
				== basic_alien.getAnimationTime()%(basic_alien.getFramesAmount()*Mazub.getFrameTime()));
	}
	
	@Test
	public void TestsetAnimationTimesparametershigherthenthehighestreachableanimationtime(){
		basic_alien.setAnimationTime(basic_alien.getFramesAmount()*Mazub.getFrameTime()+ 0.1);
		assert(basic_alien.getAnimationTime() 
				== basic_alien.getAnimationTime()%(basic_alien.getFramesAmount()*Mazub.getFrameTime()));
	}
	
	@Test
	public void Testgetcurrentframeindexanimationtimedivisiblebyframetime(){
		basic_alien.setAnimationTime(Mazub.getFrameTime() *3);
		assert(basic_alien.getAnimationTime() == 3);
	}
	
	@Test
	public void Testgetcurrentframeindexanimationtimenotdivisiblebyframetime(){
		basic_alien.setAnimationTime(Mazub.getFrameTime()*2.5);
		assert(basic_alien.getAnimationTime() == 2);
	}
	
	@Test
	public void TestisValidDtvalidDt(){
		assert(Mazub.isValidDt(0.13));
	}
	
	@Test
	public void TestisValidDtinvalidDt(){
		assert(! Mazub.isValidDt(5));
		assert(! Mazub.isValidDt(-5));
		assert(! Mazub.isValidDt(0.2));
		assert(! Mazub.isValidDt(0));
	}
	
	@Test
	public void TestadvanceTimeupdatingxpositionnewxwithinbounds(){
		basic_alien.setVx(1);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getX() == 
				 (basic_alien.getVx()*0.1 + 1/2*basic_alien.getAx()*Math.pow(0.1, 2)));
	}
	
	@Test
	public void TestadvanceTimeupdatingxpositionnewxoutsidebounds(){
		basic_alien.setVx(-1);
		basic_alien.setAx(-1);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getX() == 0);
		basic_alien.setVx(1);
		basic_alien.setAx(1);
		basic_alien.setX(basic_alien.getWorld().getWorldWidth()-1);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getX() == basic_alien.getWorld().getWorldWidth()-1);
	}
	
	@Test
	public void TestadvanceTimeupdatinghorizontalvelocitynewvxwithinbounds(){
		basic_alien.setAx(1);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getVx() == basic_alien.getVx() + basic_alien.getAx()*0.1);		
	}
	
	@Test
	public void TestadvanceTimeupdatinghorizontalvelocitynewvxoutsidebounds(){
		basic_alien.setAx(-1);
		basic_alien.setVx(-basic_alien.getVxmax());
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getVx() == -basic_alien.getVxmax());
		basic_alien.setAx(1);
		basic_alien.setVx(basic_alien.getVxmax());
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getVx() == basic_alien.getVxmax());
	}
	
	@Test
	public void TestadvanceTimeupdatingypositionnewywithinbounds(){
			basic_alien.setVy(6);
			basic_alien.advanceTime(0.1);
			assert(basic_alien.getY() == 
					basic_alien.getY() + basic_alien.getVy()*0.1 + 1/2*basic_alien.getAy()*Math.pow(0.1, 2));
		}		
	
	@Test
	public void TestadvanceTimeupdatingypositionnewyoutsidebounds(){
		basic_alien.setVy(-8);
		basic_alien.setAy(-10);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getY() == 0);
		basic_alien.setVy(1);
		basic_alien.setY(basic_alien.getWorld().getWorldHeight()-1);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getY() == basic_alien.getWorld().getWorldHeight()-1);
	}
	
	@Test
	public void TestadvanceTimeupdatingverticalvelocitynewvywithinbounds(){
		basic_alien.setVy(7);
		basic_alien.setAy(-10);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getVy() == basic_alien.getVy() + basic_alien.getAy()*0.1);		
	}
	
	@Test
	public void TestadvanceTimeupdatingverticalacceleration(){
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getAy() == 0);
		basic_alien.setY(5);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getAx() == -10);
	}
	
	@Test
	public void TestadvanceTimeupdatingLastMovewhilemovingtotherightortheleft(){
		basic_alien.setVx(2);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getLastMove() == 1);
		basic_alien.setVx(-1);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getLastMove() == -1);
	}
	
	@Test
	public void TestadvanceTimeupdatingLastMovewhilestandingstillandnewLastMovewithinbounds(){
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getLastMove() == 0);
		basic_alien.setLastMove(0.5);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getLastMove() == 0.4);
		basic_alien.setLastMove(-0.5);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getLastMove() == -0.4);		
	}
	
	@Test
	public void TestadvanceTimeupdatingLastMovewhilestandingstillandnewLastMoveoutsidebounds(){
		basic_alien.setLastMove(0.05);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getLastMove() == 0);
		basic_alien.setLastMove(-0.05);
		basic_alien.advanceTime(0.1);
		assert(basic_alien.getLastMove() == 0);		
	}
	
	@Test
	public void TestadvanceTimeupdatinganimationtimenewanimationtimewithinbounds(){
		basic_alien.advanceTime(0.1);
		double animationtimeafteradvancetime = basic_alien.getAnimationTime();
		basic_alien.setAnimationTime(0);
		basic_alien.setAnimationTime(0.1);
		double animationtimeaftersetter = basic_alien.getAnimationTime();
		assert(animationtimeafteradvancetime == animationtimeaftersetter);
	}
	
	@Test
	public void TestadvanceTimeupdatinganimationtimenewanimationtimeoutsidebounds(){
		basic_alien.setAnimationTime(Mazub.getFrameTime()*basic_alien.getFramesAmount()-0.05);
		basic_alien.advanceTime(0.1);
		double animationtimeafteradvancetime = basic_alien.getAnimationTime();
		basic_alien.setAnimationTime(Mazub.getFrameTime()*basic_alien.getFramesAmount()-0.05);
		basic_alien.setAnimationTime(basic_alien.getAnimationTime() + 0.1);
		double animationtimeaftersetter = basic_alien.getAnimationTime();
		assert(animationtimeafteradvancetime == animationtimeaftersetter);		
	}
	
	@Test
	public void TestadvanceTimeillegalparameter(){
		try{
			basic_alien.advanceTime(0.2);
		}catch(IllegalArgumentException exc){
			try{
				basic_alien.advanceTime(0);
			}catch(IllegalArgumentException exc1){
				assert(true);
			}
		}
		assert(false);
	}
}
