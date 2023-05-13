package referenceBasedTreeImplementation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import exceptions.TreeException;
import utilities.BSTreeADT;
import utilities.Iterator;

public class BSTree<E> implements BSTreeADT, Serializable {

	
	private static final long serialVersionUID = 1L;
	private BSTreeNode<E> root;
	
	/**
	 * Writes tree to repository.ser
	 * @throws IOException.
	 */
	public void writeTofile(){
		try {
			ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("repository.ser"));
			write.writeObject(this);
			write.close();
		}catch(IOException ignored) {
			
		}
	}
	
	/**
	 * Reconstructs tree from repository
	 * @throws TreeException if the root is empty. ClassNotFoundException. TreeException.
	 */
	public void readFromFile() {
		
		try {
			ObjectInputStream read = new ObjectInputStream(new FileInputStream("repository.ser"));
			this.root = ((BSTree) read.readObject()).getRoot();
		}catch(IOException | ClassNotFoundException | TreeException e) {
			e.printStackTrace();
		}
	}
	
	public BSTree() {
		this.root = null;
	}
	
	/**
	 * The node at the root of the Binary Search Tree will be returned.
	 * @return node stored at the root of tree is returned
	 * @throws TreeException if the root is empty.
	 */
	@Override
	public BSTreeNode<E> getRoot() throws TreeException {
		if(this.root == null) throw new TreeException("Root does not exist");
		return this.root;
	}
	
	/**
	 * Determines the row height of the tree and returns that value as an
	 * integer value.
	 * @return the height of the tree.
	 */
	@Override
	public int getHeight() {
		BSTreeNode curr = root;
		Queue<BSTreeNode> myQueue = new LinkedList<>();
		
		int height = 0;
		if(curr == null) {
			return 0;
		}
		myQueue.add(curr);
		while(!myQueue.isEmpty()) {
			int count = myQueue.size();
			
			while(count > 0) {
				curr = myQueue.poll();
				if(curr.getLeftNode() != null) {
					myQueue.add(curr.getLeftNode());
				}
				if(curr.getRightNode() != null) {
					myQueue.add(curr.getRightNode());
				}
				count--;
			}
			height++;
		}
		return height;
	}

	/**
	 * The number of elements currently stored in the tree is counted and
	 * the value is returned.
	 * @return number of elements currently stored in tree.
	 */
	@Override
	public int size() {
		int count = 0;
		Iterator test = this.inorderIterator();
    	while(test.hasNext()) {
    		count++;
    		test.next();
    	}
    	return count;
	}
	
	/**
	 * Checks if the tree is currently empty.
	 * @return returns boolean true if the tree is empty otherwise false.
	 */
	@Override
	public boolean isEmpty() {
		if(root == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Clears all elements currently stored in tree and makes the tree empty.
	 */
	@Override
	public void clear() {
		root = null;
		
	}
	
	/**
	 * Checks the current tree to see if the element passed in is stored in
	 * the tree. If the element is found in the tree the method returns true
	 * and if the element is not in the tree the method returns false.
	 * @param entry the element to find in the tree
	 * @return returns boolean true if element is currently in the tree and
	 * false if the element is not found in the tree
	 * @throws TreeException if the tree is empty.
	 */
	@Override
	public boolean contains(Comparable entry) throws TreeException {
		BSTreeNode curr = root;
		while(true) {
			if(entry.compareTo(curr.getTreeValue()) == 0) {
				return true;
			}
			if(entry.compareTo(curr.getTreeValue()) > 0) {
				if(curr.getRightNode() != null) {
					curr = curr.getRightNode();
				} else {
					return false;
				}
			}
			if(entry.compareTo(curr.getTreeValue()) < 0) {
				if(curr.getLeftNode() != null) {
						curr = curr.getLeftNode();
				} else {
					return false;
				}
			}
		}
	}
	
	/**
	 * Retrieves a node from the tree given the object to search for.
	 * @param entry element object being searched
	 * @return the node with the element located in tree, null if not found
	 * @throws TreeException if the tree is empty
	 */
	@Override
	public BSTreeNode search(Comparable entry) throws TreeException {
		BSTreeNode curr = root;
		while(true) {
			if(entry.compareTo(curr.getTreeValue()) == 0) {
				return curr;
			}
			if(entry.compareTo(curr.getTreeValue()) > 0) {
				if(curr.getRightNode() != null) {
					curr = curr.getRightNode();
				} else {
					return null;
				}
			}
			if(entry.compareTo(curr.getTreeValue()) < 0) {
				if(curr.getLeftNode() != null) {
						curr = curr.getLeftNode();
				} else {
					return null;
				}
			}
		}
		
	}
	
	/**
	 * Adds a new element to the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * @param newEntry the element being added to the tree
	 * @return a boolean true if the element is added successfully else false
	 * @throws NullPointerException if the element being added is null
	 */
	@Override
	public boolean add(Comparable newEntry) throws NullPointerException {
		BSTreeNode node = null;
		Stack<BSTreeNode> myStack = new Stack<>();
		if(root == null) {
			this.root = new BSTreeNode(newEntry);
			return true;
		}
		
		node = new BSTreeNode(newEntry);
		myStack.push(root);
		
		while(true) {
			if(newEntry.compareTo(myStack.peek().getTreeValue()) > 0) {
				
				if(myStack.peek().getRightNode() == null) {
					
					myStack.pop().setRightNode(node);
					return true;
				}
				myStack.push(myStack.peek().getRightNode());
			}
			if(newEntry.compareTo(myStack.peek().getTreeValue()) < 0) {
				
				if(myStack.peek().getLeftNode() == null) {
					
					myStack.pop().setLeftNode(node);
					return true;
				}
				myStack.push(myStack.peek().getLeftNode());
				
			}
		}
	}
	
	public boolean add(Comparable newEntry, int line, String file, int occurrence, int occurrsInLine) throws NullPointerException {
		BSTreeNode node = null;
		Stack<BSTreeNode> myStack = new Stack<>();
		if(root == null) {
			this.root = new BSTreeNode(newEntry, line, file, occurrence, occurrsInLine);
			return true;
		}
		
		node = new BSTreeNode(newEntry, line, file, occurrence, occurrsInLine);
		myStack.push(root);
		
		while(true) {
			if(newEntry.compareTo(myStack.peek().getTreeValue()) > 0) {
				
				if(myStack.peek().getRightNode() == null) {
					
					myStack.pop().setRightNode(node);
					return true;
				}
				myStack.push(myStack.peek().getRightNode());
			}
			if(newEntry.compareTo(myStack.peek().getTreeValue()) < 0) {
				
				if(myStack.peek().getLeftNode() == null) {
					
					myStack.pop().setLeftNode(node);
					return true;
				}
				myStack.push(myStack.peek().getLeftNode());
				
			}
		}
	}
	
	/**
	 * Generates an in-order iteration over the contents of the tree. Elements
	 * are in their natural order.
	 * @return an iterator with the elements in the natural order
	 */
	@Override
	public Iterator<E> inorderIterator() {
		class InorderIterator implements Iterator{
			
            private Stack<BSTreeNode> myStack = new Stack<>();
            public InorderIterator() {
				traverseLeft(root);
			}
            public void traverseLeft(BSTreeNode node) {
            	while(node != null) {
            		myStack.push(node);
            		node = node.getLeftNode();
            	}
            }
            @Override
            public boolean hasNext() {
                return !myStack.isEmpty();
            }

            @Override
            public E next() {
            	
            	if(!hasNext()) {
            		return null;
            	}
            	
                BSTreeNode node = myStack.pop();
                if(node.getRightNode() != null) {
                	traverseLeft(node.getRightNode());
                }
                return (E) node;
            }
		}
        return new InorderIterator();
	}
	
	/**
	 * Generates a pre-order iteration over the contents of the tree. Elements
	 * are order in such a way as the root element is first.
	 * @return an iterator with the elements in a root element first order
	 */
	@Override
	public Iterator preorderIterator() {
		class PreorderIterator implements Iterator{
			
            private Stack<BSTreeNode> myStack = new Stack<>();
            public PreorderIterator() {
            	myStack.push(root);
			}
            
            
            
            @Override
            public boolean hasNext() {
                return !myStack.isEmpty();
            }

            @Override
            public E next() {
            	
            	if(!hasNext()) {
            		return null;
            	}
            	
            	BSTreeNode node = myStack.pop();
            	
            	if(node.getRightNode() != null) {
            		myStack.push(node.getRightNode());
            	}
            	if(node.getLeftNode() != null) {
            		myStack.push(node.getLeftNode());
            	}
              
                
                return (E) node;
            }
		}
        return new PreorderIterator();

	}
	
	/**
	 * Generates a post-order iteration over the contents of the tree. Elements
	 * are order in such a way as the root element is last.
	 * @return an iterator with the elements in a root element last order
	 */
	@Override
	public Iterator postorderIterator() {
		class PostorderIterator implements Iterator{
            private Stack<BSTreeNode> myStack = new Stack<>();
            private BSTreeNode prevNode = null;
            public PostorderIterator() {
            	BSTreeNode node = root;
            	while(node != null) {
            		myStack.push(node);
            		if(node.getLeftNode() != null) {
            			node = node.getLeftNode();
            		} else if(node.getRightNode() != null) {
            			node = node.getRightNode();
            		} else {
            			node = null;
            		}
            	}
			}

            @Override
            public boolean hasNext() {
                return !myStack.isEmpty();
            }

            @Override
            public E next() {
            	BSTreeNode node = myStack.peek();
            	
            	if(node == null) {
            		return null;
            	}
            	
            	if(prevNode == node.getRightNode()) {
            		prevNode = node;
            		return (E) myStack.pop();
            	} else {
            		if(node.getRightNode() != null) {
                		BSTreeNode curr = node.getRightNode();
                		
                		while(curr != null) {
                    		myStack.push(curr);
                    		if(curr.getLeftNode() != null) {
                    			curr = curr.getLeftNode();
                    		} else if(curr.getRightNode() != null) {
                    			curr = curr.getRightNode();
                    		} else {
                    			curr = null;
                    			
                    		}
                		}
                		prevNode = myStack.pop();
                		return (E) prevNode;
                	} 
            	}
            	
            	return null;
            }
		}
		return new PostorderIterator();

	}

}