import java.io.*;
import java.util.*;

class Node
{
	int data;
	Node left;
	Node right; //0 is left //1 is right
}

class Btree
{
	static boolean flag = false; //check if tree is balanced 
	private Node root;
	
	public void insertNode(int data)
	{
		if(root == null)
		{
			root = new Node();
			root.data = data;
			//root.child = new ArrayList<Node>();
		}
		else
		{
			//implementing Binary Search Tree for now
			Node trav = root;
			Node prev;
			
			do
			{
				if(trav.data>=data)
				{
					prev=trav;
					trav=trav.left;
				}
				else
				{
					prev=trav;
					trav=trav.right;
				}
			}while(trav!=null);

			Node temp = new Node();
			temp.data = data;
			if(prev.data>data)
				prev.left=temp;
			else
				prev.right=temp;
					
		}
	}	

	public Node getRoot()
	{
		return root;
	}

	public void preOrder(Node trav)
	{
		if(trav == null)
		return ;
		System.out.print(" "+trav.data);
		preOrder(trav.left);
		preOrder(trav.right);
	}
	
	public void postOrder(Node trav)
	{
		if(trav==null)
		return;
		
		postOrder(trav.left);
		postOrder(trav.right);
		System.out.print(" "+trav.data);
		
	}

	public void inOrder(Node trav)
	{
		if(trav==null)
		return;

		inOrder(trav.left);
		System.out.print(" "+trav.data);
		inOrder(trav.right);
	}

	public int ifBalanced(Node trav)
	{
		//if difference in height of sub-trees is more than 1 then the tree is not height balanced.
		int hr = 0; //stores height of right subtree
		int hl = 0; //stores height of left subtree

		if(trav == null)
		return 0;

		if(trav.left!=null)
		hl=1+ifBalanced(trav.left);

		if(trav.right!=null)
		hr=1+ifBalanced(trav.right);

		return (hl-hr);
	}
	
}

public class BalancedTree
{
	public static void main(String[] args)throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;

		//Integer []in_arr=new Integer[];
		int []in_arr2= new int[] {9,7,6,8,11,10,12};
		Btree bt = new Btree();
		for(int i=0; i < 7;i++)
		{
			bt.insertNode(new Integer(in_arr2[i]));
		}
		while(choice != -1)	
		{
			System.out.println("1 : Enter node 2: Search node 3: Pre-Order traversal 4: Post-order traversal 5: In-order traversal 6:DFS 7:Delete node 8:BFS 9:IfBalanced");
			choice = Integer.parseInt(br.readLine());
			switch(choice)
			{
				case 1:
				System.out.println("Enter data\n");
				int inp = Integer.parseInt(br.readLine());
				bt.insertNode(inp);
				break;

				case 3:
				System.out.println("Printing pre-order data \n\n");	
				bt.preOrder(bt.getRoot());
				System.out.println("");
				break;			

				case 4:
                                System.out.println("Printing post-order data \n\n");
                                bt.postOrder(bt.getRoot());
                                System.out.println("");
                                break;

				case 5:
                                System.out.println("Printing in-order data \n\n");
                                bt.inOrder(bt.getRoot());
                                System.out.println("");
                                break;
	
				case 9:
				System.out.println("Printing differences in heights \n ");
				System.out.println(" "+bt.ifBalanced(bt.getRoot()));
				break;


			}
		}
	}
}
