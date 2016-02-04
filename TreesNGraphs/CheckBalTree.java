import java.io.*;
class Node
{
	int data;
	public Node left;
	public Node right;
}

class BalTree
{
	public Node head;
	/*this var is true if the tree is balanced and false if it's not..balanced being the diff in the heights 
	of the subtrees for every node is not greater than 1 */
	public boolean balFlag = true; 
	
	//returns the larger height of the two sub - trees or -1 if a node is unbalanced.
	//The -1 is a sentinel value that  is passed along all the way up the recursive stack.
	public int balancedTree(Node root)
	{
		int l_height=0;
		int r_height=0;
		
		if(root.left == null)
			l_height=0;
		else
			{
				l_height = balancedTree(root.left);
				if(l_height<0)
				return l_height;//pass up the sentinel value
					
				l_height++; //all child nodes on left subtree are balanced
	
			}
			
		if(root.right == null)
		   r_height = 0;
		   
		else
			{
				r_height = balancedTree(root.right);
				if(r_height<0)
				return r_height; //pass up the sentinel value
				
				r_height++;//all child nodes on rightsubtree are balanced
			}
			

		
		System.out.println("Node data = "+root.data);
		System.out.println("r_height = "+r_height);
		System.out.println("l_height = "+l_height);
		
		int diff = r_height - l_height;
		
		if(diff<0)
		diff=diff*-1; 
		
		if(diff>1) //node is unbalanced
			return -1;
		else
		   {
				//return larger of the two heights
				if(r_height >= l_height)
					return r_height;
				else
					return l_height;
		   }
	}
	
	//implementing a binary search tree
	public void insertNode(Node trav)
		{
			if(head == null)
				head = trav;
			else
			    {
					Node temp = head;
					Node prev = head;
					while(temp != null)
						{
							prev = temp;
							if(trav.data <= temp.data)
							temp = temp.left;
							else
								temp = temp.right;
						}
					if(trav.data <= prev.data)
							prev.left = trav;
							else
								prev.right = trav;
				}
		}
		
	public void inOrderTrav(Node trav)
	{
	//in order traversal
		if(trav == null)
			return ;
			
		inOrderTrav(trav.left);
		System.out.println("Node val = "+trav.data);
		inOrderTrav(trav.right);
	}
}

public class CheckBalTree
{
	public static void main(String args[])throws IOException
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//System.out.println("Enter number of nodes");
			int []arr = {8,3,10,1,6,4,7,14,13,9};
			BalTree btree =  new BalTree();
			
			for (int i=0;i<arr.length;i++)
			{
				Node node = new Node();
				node.data=arr[i];
			    btree.insertNode(node);
			}
			
			System.out.println("Inorder traversal \n");
			btree.inOrderTrav(btree.head);
			int diff = btree.balancedTree(btree.head);
			if(diff == -1)
			System.out.println("Unbalanced tree");
			else
			System.out.println("Balanced tree");
		}
}