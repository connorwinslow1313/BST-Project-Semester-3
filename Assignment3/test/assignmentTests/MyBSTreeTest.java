package assignmentTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.TreeException;
import referenceBasedTreeImplementation.BSTree;
import referenceBasedTreeImplementation.BSTreeNode;
import utilities.Iterator;

public class MyBSTreeTest {
	private BSTree tree = new BSTree();
	@Before
	public void setUp() throws Exception {
		
		tree.add(10);
		tree.add(2);
		tree.add(1);
		tree.add(3);
		tree.add(15);
		tree.add(20);
		tree.add(6);
		tree.add(14);
	}

	@After
	public void tearDown() throws Exception {
		this.tree.clear();
	}


	@Test
	public void testGetRoot() throws TreeException {
		assertEquals(tree.getRoot().getTreeValue(),10);
	}

	@Test
	public void testGetHeight() {
		assertEquals(tree.getHeight(),4);
	}

	@Test
	public void testSize() {
		assertEquals(tree.size(),8);
	}

	@Test
	public void testContains() throws TreeException {
		assertTrue(tree.contains(20));
	}

	@Test
	public void testSearch() throws TreeException {
		assertEquals(tree.search(20).getTreeValue(), 20);
	}

	@Test
	public void testInorderIterator() {
		Iterator test = tree.inorderIterator();
		String value = ",1,2,3,6,10,14,15,20";
		String testVal = "";
		while (test.hasNext()) {
			testVal = testVal + "," + ((BSTreeNode) test.next()).getTreeValue();
		}
		assertEquals(value, testVal);
	}

	@Test
	public void testPreorderIterator() {
		Iterator test = tree.preorderIterator();
		String value = ",10,2,1,3,6,15,14,20";
		String testVal = "";
		while (test.hasNext()) {
			testVal = testVal + "," + ((BSTreeNode) test.next()).getTreeValue();
		}
		assertEquals(value, testVal);
	}

	@Test
	public void testPostorderIterator() {
		Iterator test = tree.postorderIterator();
		String value = ",1,6,3,2,14,20,15,10";
		String testVal = "";
		while (test.hasNext()) {
			testVal = testVal + "," + ((BSTreeNode) test.next()).getTreeValue();
		}
		assertEquals(value, testVal);
	}
	
	@Test
	public void testAdd() {
		assertTrue(tree.add(40));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(tree.isEmpty());
	}

	@Test
	public void testClear() {
		tree.clear();
		assertTrue(tree.isEmpty());
	}
}
