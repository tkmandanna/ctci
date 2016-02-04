import java.io.*;
import java.util.*;
class Node
	{
		Node next;
		int data;
		
		public void setData(int data)
		{
			this.data=data;
		}
			
		public int getData()
		{
			return data;
		}
		
		public boolean append(Node node)
		{
			if(node != null)
				{
					next= node;
					return true;
				}
			else
				return false;
		}
		
	}
	
public class LList
{
	public Node head;
	public static BufferedReader br;
	public static void main(String args[]) throws IOException
		{
			br = new BufferedReader(new InputStreamReader(System.in));
			
			int n=0;
			while(n!=-1)
			{
				System.out.println("Enter number of nodes\n");
				n = Integer.parseInt(br.readLine());
				LList list = new LList();
				list.createList(n);
				list.printList();
				list.removeDuplicates2();
				list.printList();
			}
			
		}
		
	public void createList(int n)
	{
		head = new Node();
		Node trav = head;
		Random rg = new Random();
		int limit = n;
		head.setData(rg.nextInt(limit));
		for(int i=1;i<n;i++)
			{
				Node temp = new Node();
				temp.setData(rg.nextInt(limit));
				trav.append(temp);
				trav=temp;
				
			}
	}
		
	public void printList()
	{
		Node trav = head;
		while(trav!=null)
		{
			System.out.print(trav.getData()+", ");
			trav =trav.next;
		}
		System.out.println("");
	}
	
	public void removeDuplicates() //O(n^2) algo to delete duplicates in an unsorted linked list
	{
		Node trav1=head;
		
		
		while(trav1 !=null)
		{
			Node trav2=trav1.next;  //always begin at the sublist..all elements behind are unique
			Node prev=trav1;
			
			while(trav2 != null)
			{
				if(trav2.getData()== trav1.getData()) //delete duplicate element
					{
						prev.next=trav2.next;
						trav2.next=null;
						trav2=prev.next;
					}
				else //don't forget this..go to next element
					{
						prev=trav2;
						trav2=trav2.next;
					}
			}
			trav1=trav1.next;
		}
		
	}
	
	public void removeDuplicates2() //O(n^2) algo to delete duplicates in an unsorted linked list
	{
		Node trav1=head;
		HashMap<Integer,Boolean> hmap= new HashMap<Integer,Boolean>();//use boolean instead of references ..makes it easier
		while(trav1!=null)
		{
			int key=trav1.getData();
			hmap.put(key,false); //set this to false..because entry exists in map we know this element exists in the list
								 //while removing duplicates, it's set to true when element is encountered for the first time
								 //for remaining occurences, when the hashmap value is retrieved as true, that element is deleted
			trav1=trav1.next;
		}
		
		trav1=head;
		Node prev=head;
		
		do 
		{
			/*
				use do while so that trav1 will go ahead of prev after first pass ..else this creates problems with a list with first 2 elements as 
				duplicates..i.e. 4,4,5,6....
			*/
			int key=trav1.getData();
			
			
			if(hmap.get(key)==false)
				{
					//element has been encountered for the first time..delete all remaining occurrences on later on in the list
					hmap.put(key,true);
					prev=trav1;
					trav1=trav1.next;
				}
			else
				{
					//element is duplicate since key-value has already been set to true in hashmap..therefore delete it
					prev.next=trav1.next;
					trav1.next=null;
					trav1=prev.next;
				}
					
		}while(trav1!=null);

	}
	
	
}