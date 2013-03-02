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
	
	
	//These test the targets for a walkway cell, given a number of turns. These do not enter door.
	//These tests are colored NEON BLUE
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
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(1, 5))));
		Assert.assertEquals(5, targets.size());
		
		
		
	}
	
	//These test targets that enter doors on their last move
	//These tests are NEON PINK
	@Test
	public void fullDoorTargets(){
		
		myBoard.trackStart(myBoard.calcIndex(16, 5), 2);
		Set<BoardCell> targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(17, 4))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(18, 5))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(17, 6))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(16, 7))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(15, 4))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(14, 5))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(15, 6))));
		Assert.assertEquals(7, targets.size());
		
		myBoard.trackStart(myBoard.calcIndex(4, 18), 2);
		targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(4, 20))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(2, 18))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(3, 19))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(5, 19))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(6, 18))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(5, 17))));
		Assert.assertEquals(6, targets.size());
		
		//test door direction
		myBoard.trackStart(myBoard.calcIndex(4, 4),1);
		targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(4, 3))));
		
		myBoard.trackStart(myBoard.calcIndex(5, 3),1);
		targets = myBoard.getTargets();
		Assert.assertFalse(targets.contains(myBoard.getCellAt(myBoard.calcIndex(4, 3))));
		
		
	}
	
	//These test targets that could enter doors before the last turn
	//These are SALMON PINK (A.K.A. "Really Ugly Pink")
	@Test
	public void shortDoorTargets(){
		myBoard.trackStart(myBoard.calcIndex(6, 19), 3);
		Set<BoardCell> targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(7, 20))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(6, 22))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(3, 19))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(4, 20))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(4, 18))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(6, 16))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(5, 17))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(5, 19))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(6, 18))));
		Assert.assertEquals(9, targets.size());
	}
	
	
	//These test targets leaving a room
	//These are doors colored RED
	@Test
	public void testDoorExit(){
		myBoard.trackStart(myBoard.calcIndex(4, 8), 2);
		Set<BoardCell> targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(5, 7))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(6, 8))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(5, 9))));
		Assert.assertEquals(3, targets.size());
		
		myBoard.trackStart(myBoard.calcIndex(15, 18), 2);
		targets = myBoard.getTargets();
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(15, 16))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(14, 17))));
		Assert.assertTrue(targets.contains(myBoard.getCellAt(myBoard.calcIndex(16, 17))));
		Assert.assertEquals(3, targets.size());
	}
	
	
}
