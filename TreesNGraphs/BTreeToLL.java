import java.io.*;
import java.util.*;
//given a BinaryTree of depth D , all nodes of same depth go to the same linked list..so we have an array of D linked lists
class Node
{
	public Node(int data)
	{
		this.data=data;
		this.right=null;
		this.left=null;
	}
	int data;
	Node right;
	Node left;
}

class BTree
{
	Node root;
	
	public void insertNode(Node node)
	{
		if(root == null)
		root = node;
		else
		{
			Node trav = root;
			Node prev = root;
			while(trav!=null)
			{
				prev=trav;
				if(node.data<=trav.data)
				{
					trav=trav.left;
				}
				else
				{
					trav=trav.right;
				}
			}
			System.out.println("parent = "+prev.data);
			System.out.println("node = "+node.data);
			if(node.data<=prev.data)
			{
				prev.left=node;
			}
			else
				prev.right=node;
		}
	}
}


public class BTreeToLL
{
	
	BTree btree;
	ArrayList<LinkedList<Node>> depthList; /*each element is a list of nodes all of which where discovered at the same depth*/
	public static void main(String args[])
	{
		int []arr = {8,3,10,1,6,4,7,14,13,9};
		BTreeToLL btl = new BTreeToLL(); 
		/*only way to call non-static method..create an instance of that class "within the static method"*/
		btl.createTree(arr);
		//btl.breadthFirstTrav();
		btl.bftWithLLInsertion();
	}
	
	public void createTree( int arr[])
	{
		btree = new BTree();
		for(int i=0;i<arr.length;i++)
		{
			Node node = new Node(arr[i]);
			btree.insertNode(node);
		}
	}
	
	public void bftWithLLInsertion() 
	{
	  //given a BinaryTree of depth D , all nodes of same depth go to the same linked list..so we have an array of D linked lists
	  //the following method creates this.
		Node trav = btree.root;
		System.out.println("Root data ="+trav.data);
		
		depthList = new ArrayList<LinkedList<Node>>();//array of D linked lists
		LinkedList<Node>tempList = new LinkedList<Node>();
		tempList.add(trav); 
		depthList.add(tempList);
		int l_index=0; //begin with q1
		while(!depthList.get(l_index).isEmpty())
		{
			System.out.println("Using "+l_index+"\n");
			LinkedList<Node>childList = new LinkedList<Node>();//create list for next level
			LinkedList<Node>parentList= depthList.get(l_index); //list of the current level being traversed
			ListIterator<Node>listIterator = parentList.listIterator();
			while(listIterator.hasNext())
			{
				Node temp=listIterator.next();
				System.out.print(temp.data+",");
				if(temp.left!=null)
					{
						childList.add(temp.left);
					}
				if(temp.right!=null)
					{
						childList.add(temp.right);
					}
			}
			
			depthList.add(childList);//add list of child nodes to array of depth lists
			l_index++;
				
		}
	}
	public void breadthFirstTrav() //method of breadth first traversal..for zig zagging switch insertion directions into queues
	{
		/*two queues are required. While parent nodes at depth "d" are being traversed and dequed, child nodes at depth "d+1"
		are being added to the other queue. This alterantes between the the two queues. Each plays role of queue of parent and child nodes alternatively.*/
		
		Queue<Node>q1 = new LinkedList<Node>();
		Queue<Node>q2 = new LinkedList<Node>();
		
		Node trav = btree.root;
		System.out.println("Root data ="+trav.data);
		
		q1.add(trav); 
		int q_index=1; //begin with q1
		while((!q1.isEmpty())||(!q2.isEmpty()))
		{
			
			if(q_index==1)
			{
				System.out.println("Using q1\n");
				while(!q1.isEmpty())
				{
					
					Node temp = q1.remove();
					System.out.print(temp.data+",");
					//add child nodes to other queues
					if(temp.left!=null)
					{
						q2.add(temp.left);
					}
					if(temp.right!=null)
					q2.add(temp.right);
				}
				q_index=2;
				System.out.println("");
			}
			
			if(q_index==2)
			{
				System.out.println("Using q2\n");
				while(!q2.isEmpty())
				{
					Node temp = q2.remove();
					System.out.print(temp.data+",");
					//add child nodes to other queues
					if(temp.left!=null)
					q1.add(temp.left);
					if(temp.right!=null)
					q1.add(temp.right);
				}
				q_index=1;
				System.out.println("");
			}
		}
		
	}
}