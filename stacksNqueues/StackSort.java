import java.io.*;
import java.util.*;

class MyStack<T> extends Stack<T>
	{
		public String name ;
		public MyStack(String name)
		{
			this.name=name;
		}
	}
public class StackSort
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of integers\n");
		int n=Integer.parseInt(br.readLine());

		Random randomGen = new Random();

		MyStack<Integer>mainStk = new MyStack<Integer>("A"); //similar to towers of Hanoi
	        MyStack<Integer>auxStk = new MyStack<Integer>("B");  //auxiliary Stack
        	MyStack<Integer>finStk = new MyStack<Integer>("C");  // final result place 

		System.out.println("Inputting numbers now\n");
		for(int i=n;i>0;i--)
		{
			Integer inp = new Integer(randomGen.nextInt(50));
			mainStk.push(inp);
			System.out.println(inp.toString());
			
		}		
		
		

		//towersOfHanoi(mainStk,auxStk,finStk,n);
		sortStack(mainStk);

		System.out.println("Printing sorted list \n");
		if(mainStk.isEmpty())
		System.out.println("Main stk is empty\n");
		while(!mainStk.isEmpty())
		{
			System.out.println("Element = "+mainStk.pop().toString());
		}
	}

	public static void towersOfHanoi(MyStack<Integer>mainStk,MyStack<Integer>auxStk,MyStack<Integer>finStk,int n)
	{
		//System.out.println("Towers of Hanoi "+mainStk.name+" "+auxStk.name+" "+finStk.name+" "+n);
		/*if(n==1)
			{
				Integer top = mainStk.pop();
                        System.out.println("Pushing "+top.toString()+" from "+mainStk.name+" to "+finStk.name);
                        towersOfHanoi(auxStk,mainStk,finStk,n-1);

			}*/
		if(n>=1)
		{
			/*System.out.println("In return condition n ="+n);
			if(mainStk.isEmpty())
			System.out.println(mainStk.name+" is Empty");
			else
			System.out.println(mainStk.name+" is not Empty");
			*/
			towersOfHanoi(mainStk,finStk,auxStk,n-1);
			Integer top = mainStk.pop();
                        System.out.println("Pushing "+top.toString()+" from "+mainStk.name+" to "+finStk.name);
                        finStk.push(top);
			towersOfHanoi(auxStk,mainStk,finStk,n-1);
			return ;	
		}
		/*else
		{
			towersOfHanoi(mainStk,finStk,auxStk,n-1);
			Integer top = mainStk.pop();
			finStk.push(top);
			System.out.println("Pushing "+top.toString()+" from "+mainStk.name+" to "+finStk.name);
			towersOfHanoi(auxStk,mainStk,finStk,n-1);
			
		}*/
	}
	public static void sortStack(Stack<Integer>mainStk)
	{
		 Stack<Integer> left = new Stack<Integer>();
                        Stack<Integer> right = new Stack<Integer>();
		if(!mainStk.isEmpty())
		{
			Integer pivot = mainStk.pop();
		//	Stack<Integer> left = new Stack<Integer>();
		//	Stack<Integer> right = new Stack<Integer>();
			System.out.println("pivot = "+pivot.toString());
			while(!mainStk.isEmpty())
			{
				Integer top = mainStk.pop();
				if(top<=pivot)
				left.push(top);
				else
				right.push(top);
			}
			sortStack(left);
			System.out.println("Returning from left\n");
			sortStack(right);
			System.out.println("Returning from right\n");
			/*
			This is wrong ..because once the left and right stacks are sorted..if they're popped , their relative order gets reversed.
			while(!left.isEmpty())
			{
				Integer top = left.pop();
				mainStk.push(top);
			}

			mainStk.push(pivot);

			while(!right.isEmpty())
        	        {
                	        Integer top = right.pop();
                        	mainStk.push(top);
                	}*/
			if(!left.isEmpty())
			System.out.println("Left peek = "+left.peek().toString());
			if(!right.isEmpty())
			System.out.println("Right peek = "+right.peek().toString());

			System.out.println("Pushing pivot now = "+pivot.toString());
			left.push(pivot); //all greater elements are below pivot
			while(!right.isEmpty())
                        {
                                Integer top = right.pop();
				System.out.println("Pushing left element "+top.toString());
                                mainStk.push(top);
                        }
			while(!mainStk.isEmpty())
                        {
                                Integer top = mainStk.pop();
                                //System.out.println("Pushing left element "+top.toString());
                                left.push(top);
                        }
		//	 if(!left.isEmpty())
                  //      System.out.println("Left end peek = "+left.peek().toString());

			mainStk = left; //change reference ..because this is the only one that is returned.
				 if(!left.isEmpty())
		                 System.out.println("Left end peek = "+mainStk.peek().toString());
	
		}
		else
			return;
	
	}
}
