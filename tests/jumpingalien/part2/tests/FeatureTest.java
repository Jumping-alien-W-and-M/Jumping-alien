package jumpingalien.part2.tests;

import jumpingalien.model.Feature;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FeatureTest {


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testpassablefeatures() {
		assert(Feature.magma.isPassable());
		assert(Feature.air.isPassable());
		assert(Feature.water.isPassable());
	}
	
	@Test
	public void testnotpassablefeatures(){
		assert(! Feature.ground.isPassable());
	}
}
