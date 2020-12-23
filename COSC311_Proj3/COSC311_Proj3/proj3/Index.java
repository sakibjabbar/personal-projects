package proj3;
public class Index
{
	private IndexNode root;
	
	public Index()
	{
		root = null;
	}
	
	public int find(String key) // search for node, return either database location or -1
	{
		IndexNode current = root; // start at root

		while(current.getKey().compareTo(key) != 0) // while no match
		{
			if(key.compareTo(current.getKey()) < 0) // go left
				current = current.getLeft();
			else
				current = current.getRight(); // go right
			
			if(current == null) // not found
				return -1;
		}

		return current.getWhere(); // found
	} // end find()
	
	public void insert(String key, int where)
	{
		IndexNode newNode = new IndexNode(key, where); // create new node
		
		if(root == null) // if root is empty
			root = newNode; // root occupied by new node
		
		else
		{
			IndexNode current = root; // start at root
			IndexNode parent;
	
			while(true)
			{
				parent = current;
				
				if(newNode.getKey().compareTo(current.getKey()) < 0) // go left
				{	
					current = current.getLeft();
					if(current == null) // if end of the line
					{
						parent.setLeft(newNode); // insert on left
						return;
					}
				} // end go left
				
				else // go right
				{
					current = current.getRight();
					if (current == null) // if end of the line
					{
						parent.setRight(newNode); // insert on right
						return;
					}
				} // end go right
			} // end while
		} // end else not root
	} // end insert()
	
	public void delete(String key) // delete node with given key
	{
		IndexNode current = root;
		IndexNode parent = root;
		boolean isLeftChild = true;
		
		while(current.getKey().compareTo(key) != 0) // search for node
		{
			parent = current;
			
			if(current.getKey().compareTo(key) > 0) // go left
			{
				isLeftChild = true;
				current = current.getLeft();
			}
			
			else { // go right
				isLeftChild = false;
				current = current.getRight();
			}
			if(current == null) // end of the line
				return; // node not found
		}
		// found node to delete
		
		if(current.getLeft() == null && current.getRight() == null) // if no children
		{
			if(current == root) // if root
				root = null; // tree is now empty
			
			else if (isLeftChild) // disconnect from parent
				parent.setLeft(null);
			
			else // disconnect from parent
				parent.setRight(null);
		}
		
		else if(current.getRight() == null) // if no right child
		{
			if(current == root)
				root = current.getLeft();
			
			else if(isLeftChild)
				parent.setLeft(current.getLeft());
			
			else
				parent.setRight(current.getLeft());
		}
		
		else if(current.getLeft() == null) // if no left child
		{
			if(current == root)
				root = current.getRight();
			
			else if(isLeftChild)
				parent.setLeft(current.getRight());
			
			else
				parent.setRight(current.getRight());
		}
		
		else // two children
		{
			IndexNode successor = getSuccessor(current); // get successor of node to delete
			
			// connect parent of current to successor
			if(current == root)
				root = successor;
			
			else if(isLeftChild)
				parent.setLeft(successor);
			
			else
				parent.setRight(successor);
			
			// connect successor to current's left child
			successor.setLeft(current.getLeft());
		}
	} // end delete()
	
	private IndexNode getSuccessor(IndexNode delNode) // returns node with next highest value after deNode
	{
		IndexNode successorParent = delNode;
		IndexNode successor = delNode;
		IndexNode current = delNode.getRight(); // go to right child until no more left children
		
		while(current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.getLeft(); // go to left child
		}
	
		if(successor != delNode.getRight()) // if successor not right child, make connections
		{
			successorParent.setLeft(successor.getRight());
			successor.setRight(delNode.getRight());
		}
		
		return successor;
	}

	public void printIt(StudentRecord[] database, IndexNode localRoot, int choice)
	{
		if (choice == 1)
		{
			if(localRoot != null)
			{
				printIt(database, localRoot.getLeft(), choice);
				System.out.println(database[localRoot.getWhere()]);
				printIt(database, localRoot.getRight(), choice);
			}
		}
		
		else
		{
			if(localRoot != null)
			{
				printIt(database, localRoot.getRight(), choice);
				System.out.println(database[localRoot.getWhere()]);
				printIt(database, localRoot.getLeft(), choice);
			}
		}
	}
	
	public IndexNode getRoot()
	{
		return root;
	}
}