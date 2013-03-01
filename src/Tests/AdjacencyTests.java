package Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueBoard.Board;
import clueBoard.BoardCell;


public class AdjacencyTests {
	private static Board myBoard;
	@Before
	public void setUp(){
		myBoard = new Board();
		myBoard.calcAdjacencies();
	}
	
	
	// Test a variety of walkway scenarios
	// These tests are Green on the planning spreadsheet
	@Test
	public void walkwayTests(){
		
		LinkedList<Integer> adj = new LinkedList<Integer>();
		adj = myBoard.getAdjList(myBoard.calcIndex(17, 6));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(16, 6)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(18, 6)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(17, 7)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(17, 5)));
		Assert.assertEquals(4, adj.size());
		
		adj = myBoard.getAdjList(myBoard.calcIndex(6, 21));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(6, 20)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(6 ,22)));
		Assert.assertEquals(4, adj.size());
		
		adj = myBoard.getAdjList(myBoard.calcIndex(14, 19));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(14, 20)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(14, 18)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(13, 19)));
		Assert.assertEquals(4, adj.size());
		
		adj = myBoard.getAdjList(myBoard.calcIndex(0, 4));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(0, 5)));
		Assert.assertEquals(4, adj.size());
	
		
	}
	
	//Test cells that are on the edge of the board
	//These tests are Purple on the planning spreadsheet
	@Test
	public void edgeBoardTests(){
		
		LinkedList<Integer> adj = new LinkedList<Integer>();
		adj = myBoard.getAdjList(myBoard.calcIndex(0, 18));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(0, 19)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(1, 18)));
		Assert.assertEquals(2, adj.size());
		
		adj = myBoard.getAdjList(myBoard.calcIndex(11, 22));
		Assert.assertEquals(0, adj.size());
		
		adj = myBoard.getAdjList(myBoard.calcIndex(12, 0));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(13, 0)));
		Assert.assertEquals(1, adj.size());
		
		
		adj = myBoard.getAdjList(myBoard.calcIndex(21, 17));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(21, 16)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(20, 17)));
		Assert.assertEquals(2, adj.size());
		
	}
	
	//These tests are for walkway cells adjacent to door cells
	//These will be colored Orange in the planning spreadsheet
	@Test
	public void enterTests(){
		
		LinkedList<Integer> adj = new LinkedList<Integer>();
		adj = myBoard.getAdjList(myBoard.calcIndex(13, 11));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(13, 12)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(13, 10)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(12, 11)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(14, 11)));
		Assert.assertEquals(4, adj.size());
		
		adj = myBoard.getAdjList(myBoard.calcIndex(15, 17));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(15, 18)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(15, 16)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(16, 17)));
		Assert.assertTrue(adj.contains(myBoard.calcIndex(14, 17)));
		Assert.assertEquals(4, adj.size());
		
		
	}
	
	
	//These tests are for door cells
	//These will be colored White in the planning spreadsheet
	@Test
	public void doorTests(){
			
			LinkedList<Integer> adj = new LinkedList<Integer>();
			adj = myBoard.getAdjList(myBoard.calcIndex(5, 15));
			Assert.assertTrue(adj.contains(myBoard.calcIndex(6, 15)));
			Assert.assertEquals(1, adj.size());
			
			adj = myBoard.getAdjList(myBoard.calcIndex(10, 6));
			Assert.assertTrue(adj.contains(myBoard.calcIndex(10, 7)));
			Assert.assertEquals(1, adj.size());
			
			
		}
	
	@Test
	public void walkwayTargets(){
		
		myBoard.trackStart(myBoard.calcIndex(0, 6), 4);
		Set<BoardCell> targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(2, 6))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(3, 5))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(4, 6))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(0, 4))));
		Assert.assertEquals(4, targets.size());
		
		
		myBoard.trackStart(myBoard.calcIndex(0, 6), 4);
		targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(2, 6))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(3, 5))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(4, 6))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(0, 4))));
		Assert.assertEquals(4, targets.size());
		
		
		
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
