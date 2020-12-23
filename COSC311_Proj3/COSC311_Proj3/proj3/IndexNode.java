package proj3;
public class IndexNode
{
	private String key; // first name, last name, or ID
	private int where; // location in database array
	private IndexNode leftChild; // references left child
	private IndexNode rightChild; // references right child
	
	// constructor
	IndexNode(String inputKey, int location)
	{
		this.key = inputKey; // sets node data
		this.where = location; // sets database location
		this.leftChild = null; // left child null
		this.rightChild = null; // right child null
	}
	
	public void setRight(IndexNode node) // sets next node
	{
		rightChild = node;
	}
	
	public IndexNode getRight() // returns next node
	{
		return this.rightChild;
	}
	
	public void setLeft(IndexNode node) // sets previous node
	{
		leftChild = node;
	}
	
	public IndexNode getLeft() // returns previous node
	{
		return this.leftChild;
	}
	
	public int compareTo(IndexNode otherIndexNode) // compares data to other node's data
	{
		return (this.key.compareToIgnoreCase(otherIndexNode.key));
	}
	
	public String getKey() // returns data
	{
		return key;
	}
	
	public int getWhere() // returns database location
	{
		return where;
	}
}
