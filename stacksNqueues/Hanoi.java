import java.io.*;
import java.util.*;
import java.lang.Math.*;

class disk
{
	int n;
	String from;
	String with;
	String to;
}

public class Hanoi
{
	public static void main(String args[])throws IOException
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter n");
			int n=Integer.parseInt(br.readLine());
			
			iterativeTowers(n,"A","B","C");
		}
	
	public static void towers(int n,String from,String with,String to)
	{
		if(n==1)
		{
			System.out.println(n+" - "+from+" => "+to);
			return;
		}
		else
		{
			towers(n-1,from,to,with);
			System.out.println(n+" - "+from+" => "+to);
			towers(n-1,with,from,to);
		}
	}
	
	public static void iterativeTowers(int n,String from,String with, String to)
		{
		 //http://www.geeksforgeeks.org/iterative-tower-of-hanoi/ for tutorial
			double moves = Math.pow(2,n) ;
			moves--;
			Stack<Integer>A = new Stack<Integer>();
			Stack<Integer>B = new Stack<Integer>();
			Stack<Integer>C = new Stack<Integer>();
			
			for(int i=n;i>0;i--)
			{
				A.push(i);
			}
			for(int i=1;i<=moves;i++)
			{
				if(i%3==1)
				{
					System.out.print(i);
					legalMove(A,from,C,to);
				}
				if(i%3==2)
				{
					System.out.print(i);
					legalMove(A,from,B,with);
				}
				if(i%3==0)
				{
					System.out.print(i);
					legalMove(B,with,C,to);
				}
			}
		}
		
	public static void legalMove(Stack<Integer>A,String from,Stack<Integer>B,String to)
	{
		int a = 0;
		if(!A.isEmpty())
		a = A.pop();
		int b = 0;
		if(!B.isEmpty())
		b=B.pop();
		
		if((a<b) && (b!=0)&&(a!=0))  // if a or b is empty ..then that stack is empty
		{
			B.push(b);
			B.push(a);
			System.out.println("- "+from+" => "+to);
		}
		else if(b==0)
		{
		    B.push(a);
			System.out.println("- "+from+" => "+to);
		}
		else
		{  
		    if(a!=0)
			A.push(a);
			A.push(b);
			System.out.println("- "+to+" => "+from);
		}
	}
}