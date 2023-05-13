package referenceBasedTreeImplementation;

import java.io.Serializable;

public class BSTreeNode<E> implements Serializable {

	private static final long serialVersionUID = 1L;
	private BSTreeNode<E> leftNode, rightNode;
	private E treeValue;
	private int line;
	private String file;
	private int occurrence;
	private int occurrsInLine;
	
	public BSTreeNode (E treeValue) {
		this.treeValue = treeValue;
	}
	
	/**
	 * constructor for line number and file location
	 */
	public BSTreeNode (E treeValue, int line, String file, int occurrence, int occurrsInLine) {
		this.treeValue = treeValue;
		this.line = line;
		this.file = file;
		this.occurrence = occurrence;
		this.occurrsInLine = occurrsInLine;
	}
	/**
	 * Returns value stored in node
	 * @return Value stored in the referenced node.
	 */
	public E getTreeValue() {
		return treeValue;
	}

	/**
	 * Returns left node
	 * @return Reference to the left node of the node.
	 */
	public BSTreeNode<E> getLeftNode() {
		return leftNode;
	}

	/**
	 * Set pointer to the left node
	 */
	public void setLeftNode(BSTreeNode<E> leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * Returns right node
	 * @return Reference to the right node of the node.
	 */
	public BSTreeNode<E> getRightNode() {
		return rightNode;
	}

	/**
	 * Set pointer to the right node
	 */
	public void setRightNode(BSTreeNode<E> rightNode) {
		this.rightNode = rightNode;
	}
	
	/**
	 * Setter for line number
	 */
	public void setLine(int line) {
		this.line = line;
	}
	
	/**
	 * @return line occurred on
	 */
	public int getLine() {
		return this.line;
	}

	/**
	 * @return file occurred in
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Setter for file name
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the occurrence
	 */
	public int getOccurrence() {
		return occurrence;
	}

	/**
	 * @param occurrence the occurrence to set
	 */
	public void setOccurrence(int occurrence) {
		this.occurrence = occurrence;
	}

	/**
	 * @return the occurrsInLine
	 */
	public int getOccurrsInLine() {
		return occurrsInLine;
	}

	/**
	 * @param occurrsInLine the occurrsInLine to set
	 */
	public void setOccurrsInLine(int occurrsInLine) {
		this.occurrsInLine = occurrsInLine;
	}


}
