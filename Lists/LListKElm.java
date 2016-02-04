import java.io.*;
//program to print kth node from the last
class Node
{
	Node next;
	int data;
}

class LList
{
	Node head;
	
	public void createList(int n)
	{
		if(head == null)
			head = new Node();
			
		Node trav = head;
		
		for(int i=0;i<n;i++)
		{
			trav.data=(i+1);
			if(i==(n-1))
			break;
			trav.next=new Node();
			
			trav=trav.next;
		}
	}
	
	public void kthElem(int k)
	{
		if(head == null)
		return ;
		Node trav1=head;
		Node travk=head;
		
		for(int i=0;i<k;i++)
		{
			if(travk==null) //decide carefully if travk or trav.next == null here..this is for, 1st element from last is last element itself
			{
				System.out.println("List is not long enough");
				return;
			}
			travk=travk.next;
		}
		
		while(travk!=null)
		{
			travk=travk.next;
			trav1=trav1.next;
		}
		
		System.out.println("ans = "+trav1.data);
	}
	public void printList()
	{
		Node trav = head;
		
		while(trav!=null)
		{
			System.out.print(""+trav.data+", ");
			trav=trav.next;
		}
		System.out.println(" ");
	}
}

public class LListKElm
{
	static BufferedReader br ;
	
	public static void main(String args[])throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int len=1;
		while(len!=0)
		{
				System.out.println("Enter number of nodes");
				
				len=Integer.parseInt(br.readLine());
				 
				if (len == 0) // add this to exit program else null pointer exceptions can occur elsewhere
				continue;
				
				System.out.println("Enter k");
				
				int k=Integer.parseInt(br.readLine());
				LList llist = new LList();
				llist.createList(len);
				llist.printList();
				llist.kthElem(k);
				

		}
	}
	
}