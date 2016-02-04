import java.io.*;
//find the in-order successor of a node in a Binary Search Tree..i.e. next greater element of a given node
class Node
{
	int data;
	Node right;
	Node left;
	Node parent;
}

class BTree
{
	Node root;
	
	public void insert(Node node)
	{
		System.out.println("Inserting "+node.data);
		if(root ==null)
		root = node;
		else
		{
			Node trav=root;
			Node prev = root;
			while(trav!=null)
			{
				prev=trav;
				
				if(node.data<=trav.data)
				trav=trav.left;
				else
				trav=trav.right;
			}
			
			if(prev.data<node.data)
			{
				prev.right=node;
			}
			else
			{
				prev.left=node;
			}
			
			node.parent = prev; //linking parent node
			System.out.println("parent = "+prev.data);
		}
	}
	
	public void findInOrderSuccessor(Node node)
	{
			Node trav=root;
			Node prev = root;
			while(trav!=null) //take care of case where given node does not exist in BST..didn't bother in this example
			{
				prev=trav;
				if(node.data == trav.data)
				break;
				
				if(node.data<trav.data)
				trav=trav.left;
				else
				trav=trav.right;
			}
			
			if(trav == null)
			{
				System.out.println("Given node does not exist in bst.\n");
				return;
			}
			//node has been found
			/*
			To find in-order successor:
			1.) if right child exists ...find left most element of right child subtree
			2.) use back-link to go backwards to find the first ancestor that is greater than given node..until root node is reached
				2.a) if root node > given node..it is the in-order successor 
				2.b) else, it is the largest node in the BST and so has no in-order successor
			3.) If however, root node is reached in (2) then, in-order successor does not exist..i.e. given node is largest node in BST
			*/
			
			if(trav.right !=null)
			{
				//go to the right subtree..
				trav=trav.right;
				while(trav.left!=null)
				trav=trav.left; //find smallest element in right subtree
				
				System.out.println("Successor is "+trav.data);
				return;
			}
			else
			{
				while(trav!=root) //== and != do ref comparison unlike .equals() which does data comparison
				{
					trav = trav.parent;
					if(trav.data > node.data)
					{
						System.out.println("Successor is "+trav.data); //first ancestor that is greater than given node
						return;
					}
				}
				
				if(trav.data>node.data)
				{
					System.out.println("Successor is "+trav.data); //in-order successor is root node
						return;
				}
				
				System.out.println("Node has no in-order successor");
			}
	}
}

public class BSTSuccessor
{
	public BTree btree;
	
	public static void main(String args[])
	{
		
		int []arr = {8,3,10,1,6,4,7,14,13,9};
		
		BSTSuccessor bst = new BSTSuccessor();
		bst.makeTree(arr);
		try
		{
		bst.findSuccessors();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void makeTree(int []arr)
	{
		btree = new BTree();
		for(int i=0;i<arr.length;i++)
		{
			Node node = new Node();
			node.data=arr[i];
			btree.insert(node);
		}
	}
	
	public void findSuccessors()throws IOException
	{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int temp =0;
		while(temp!=-1)
		{
			System.out.println("Enter node\n");
			temp = Integer.parseInt(br.readLine());
			
			Node tempNode = new Node();
			tempNode.data = temp;
			btree.findInOrderSuccessor(tempNode);
		}
	}
	
}