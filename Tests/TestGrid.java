import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGrid {

	static Grid grid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// set grid size dimension
		grid = new Grid(6, 6);
		grid.setValue(1, 0, 0);
		grid.setValue(0, 1, 0);
		grid.setValue(1, 2, 0);
		grid.setValue(0, 3, 0);
		grid.setValue(0, 4, 0);
		grid.setValue(1, 5, 0);
		grid.setValue(0, 0, 1);
		grid.setValue(1, 1, 1);
		grid.setValue(0, 2, 1);
		grid.setValue(1, 3, 1);
		grid.setValue(0, 4, 1);
		grid.setValue(1, 5, 1);
		grid.setValue(1, 0, 2);
		grid.setValue(0, 1, 2);
		grid.setValue(0, 2, 2);
		grid.setValue(1, 3, 2);
		grid.setValue(1, 4, 2);
		grid.setValue(0, 5, 2);
		grid.setValue(0, 0, 3);
		grid.setValue(0, 1, 3);
		grid.setValue(1, 2, 3);
		grid.setValue(0, 3, 3);
		grid.setValue(1, 4, 3);
		grid.setValue(1, 5, 3);
		grid.setValue(1, 0, 4);
		grid.setValue(1, 1, 4);
		grid.setValue(0, 2, 4);
		grid.setValue(1, 3, 4);
		grid.setValue(0, 4, 4);
		grid.setValue(0, 5, 4);
		grid.setValue(0, 0, 5);
		grid.setValue(1, 1, 5);
		grid.setValue(1, 2, 5);
		grid.setValue(0, 3, 5);
		grid.setValue(1, 4, 5);
		grid.setValue(0, 5, 5);
	}

	@Test
	public void testSetXdim() {
		// first test
		// set x dimension of grid
		assertEquals("test setting x dimension", 6, grid.getXdimention());
	}

	@Test
	public void testSetYdim() {
		// second test
		// set y dimension of grid
		assertEquals("test setting y dimension", 6, grid.getYdimention());
	}
	
	@Test
	public void testValue(){
		// third test
		// set value in grid
		grid.setValue(1, 2, 3);
		assertEquals("test setting value in grid", 1, grid.getValue(2, 3));
	}

	@Test
	public void testfilledGrid(){
		// forth test
		//test if grid is filled
		assertTrue(grid.filledGrid());
	}
	
	@Test
	public void testvalidGrid(){
		// fifth test
		//test if grid is valid
		assertTrue(grid.validGrid());
	}
	
	@Test
	public void testNumOfVal(){
		// sixth test
		//test if each row and column has equal amount of 0's and 1's
		assertTrue(grid.checkCol(0));
		assertTrue(grid.checkRow(0));
		assertTrue(grid.checkCol(1));
		assertTrue(grid.checkRow(1));
		assertTrue(grid.checkCol(2));
		assertTrue(grid.checkRow(2));
		assertTrue(grid.checkCol(3));
		assertTrue(grid.checkRow(3));
		assertTrue(grid.checkCol(4));
		assertTrue(grid.checkRow(4));
		assertTrue(grid.checkCol(5));
		assertTrue(grid.checkRow(5));
	}
	
	@Test
	public void testUniqueCol(){
		// seventh test
		// test if each column is unique to each other
		assertTrue(grid.uniqueCol());
	}
	
	@Test
	public void testUniqueRow(){
		// eighth test
		// test if each row is unique to each other
		assertTrue(grid.uniqueRow());
	}
}
