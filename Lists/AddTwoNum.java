import java.io.*;

//Adding  two long integers using linked lists..traverse the list in reverse using recursion
//right now does only for integers of equal length
// 5 star problem

/* Currently we use only one result list in this solution, but to make it easier..we can use two result list and the concatenate them
One of the result list is to store the remainder section of the larger number and the smaller number ..the other list will be store carry of first addition and unadded portion of larger number ..later the two lists can be concatenated.*/
class Node
{
	int data;
	Node next;
}

class NList //list to store a number which each node containing a single digit
{
	Node head;
	
	public void acceptNum(String n)
	{
		
		head = new Node();
		
		Node trav = head ;
		Node prev = head;
		
		trav.data=n.charAt(0)-48;
		
		for(int i=1;i<n.length();i++)
		{
			trav = new Node();
			trav.data=n.charAt(i)-48;
			
			prev.next=trav;
			prev=trav;
		}
	}
	
	public void printNum()
	{
		Node trav = head;
		while(trav!=null)
		{
			System.out.print(trav.data);
			trav=trav.next;
		}
		System.out.println();
	}
}

public class AddTwoNum
{
	static BufferedReader br;
	public static void main(String args[])throws IOException
		{
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter num1 ");
			String n1 = br.readLine();
			int l1 = n1.length();
			
			System.out.println("Enter num2 ");
			String n2 = br.readLine();
			
			int l2=n2.length();
			//creating linked lists to store both numbers
			NList num1 = new NList();
			num1.acceptNum(n1);
			num1.printNum();
			NList num2 = new NList();
			num2.acceptNum(n2);
			num2.printNum();
			
			NList resList = new NList();
			
			 
			Node res=new Node();
			resList.head = res;
			
			Node prev =res;
			//create list to store results
			
			
			Node beginPrevNode1=num1.head;  //if the two numbers are not of equal length then the addition will not necessarily begin at the head node
			Node beginPrevNode2=num2.head;  // so if two numbers are 345 and 56789 then beginPrevNode is 2nd number's 6 and the addition initially is between only
											// 345 and 789 only..then the carries are added to the remaining digits i.e. 56 of the 2nd number
			boolean n1Bigger=false;     //boolean variables to mark if the numbers are not of equal length then which one is bigger
			boolean n2Bigger=false;
			int carry;
			if(l1!=l2)
			{
				int diff=l1-l2;
				if(diff>0)
					{
						for(int i=1;i<n1.length();i++) //remember this bug ..always causes extra nodes otherwise at the end of the list..practice creating n number of nodes
							{
								res= new Node();
								res.data=0;
								prev.next=res;
								prev=res;
							}
						beginPrevNode1=returnBeginNode(num1,diff);
						Node tempResHead = returnBeginNode(resList,diff);
						
						carry = addNum(beginPrevNode1.next,beginPrevNode2,tempResHead.next);//no next for list 2 since list 2 begins at head itself 
						System.out.println("beginPrevNode1.next = "+beginPrevNode1.next.data);
						System.out.println("beginPrevNode2.next = "+beginPrevNode2.data);
						
						//beginResPrevNode=returnBeginNode(resList,diff);
						carry=addRemainingCarry(carry,num1.head,beginPrevNode1,resList.head);
					}
				else
					{
						for(int i=1;i<n2.length();i++) //remember this bug ..always causes extra nodes otherwise at the end of the list..practice creating n number of nodes
							{
								res= new Node();
								res.data=0;
								prev.next=res;
								prev=res;
							}
						diff=diff*-1; //need this ..or returnBegin Node will not work..
						beginPrevNode2=returnBeginNode(num2,diff);
						Node tempResHead = returnBeginNode(resList,diff);/*this is done..so if n1=99 , n2 = 9999, then we want to add n1 and the last 2 digits of n2 ..and store results in res [2],[3] if res was an array res [0][1][2][3]..then tempResHead  = res [1]*/ 
						
						carry = addNum(beginPrevNode1,beginPrevNode2.next,tempResHead.next); //no next for list 1 since list 1 begins at head itself 
						System.out.println("beginPrevNode1.next = "+beginPrevNode1.data);
						System.out.println("beginPrevNode2.next = "+beginPrevNode2.next.data);
						
						carry=addRemainingCarry(carry,num2.head,beginPrevNode2,resList.head);
					}
				//adding returned carry to remainder of bigger list still to be implemented here.wq
				
				//resPrev will contain the results when carry  is added to remainder of the bigger number..the remaining result will be backwards from resPrev to res.head
				
				
			}
			else
				carry = addNum(beginPrevNode1,beginPrevNode2,resList.head);  //the prev nodes are at head since the numbers are of equal length
			
			
			 
			
			if(carry!=0)
			{
				Node carryNode= new Node();
				carryNode.data=carry;
				carryNode.next=resList.head;
				resList.head=carryNode;
			}
			
			resList.printNum();
			//check for negative numbers
			//always create empty result list filled with required number of nodes beforehand..no. of nodes should be longest number + 1 extra node for carry
			//this way  we don't need to worry about node creation during the addition
		}
	
	public static Node returnBeginNode(NList nlist,int diff)
	{
		Node trav = nlist.head;
		Node prev=trav;
		System.out.println("diff = "+diff);
		for(int i=0;i<diff;i++)
		{
			if(trav!=nlist.head)
			{
				prev=trav;
				System.out.println("prev.data = "+prev.data);
			}
			trav=trav.next;
		}
		return prev;
	}
	
	//if the two numbers are of unequal length
	public static int addRemainingCarry(int carry,Node node,Node end,Node res)
	{
		if(node == end)
		{
			res.data=(carry+node.data)%10;
			return ((carry+node.data)/10);
		}
		else
		{
			int sum=(node.data+addRemainingCarry(carry,node.next,end,res.next));
			res.data=sum%10;
			return (sum/10);
		}
	}
	
	public static int addNum(Node n1,Node n2,Node res)
	{
		if((n1.next==null)||(n2.next==null)) // end of the list has been reached
		{
			res.data=(n1.data+n2.data)%10;
			//add last two digits..return carry for addition of next pair of most significant digits
			int carry = (n1.data+n2.data)/10;
			return carry;
		}
		else
		{
		    //get carry from addition of rest of list..then add current elements ..return carry for addition of next most significant digit
			int carry = addNum(n1.next,n2.next,res.next);
			res.data=(n1.data+n2.data+carry)%10;
			carry = (n1.data+n2.data+carry)/10;
			return carry;
			
		}
	}
	
}