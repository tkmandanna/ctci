import java.util.*;
import java.io.*;

class MyStack
{
	Stack<Integer> stack;
	static int size=1;
	
	public MyStack()
	{
		stack = new Stack<Integer>();
	}
}

public class SetOfStacks
{
	public static ArrayList<MyStack> stkSet= new ArrayList<MyStack>();
	public static int cur_stk=0;
	public static int ne=0;
	
	public static MyStack get(int i)
	{
		Object[] myArr = stkSet.toArray();
		return (MyStack)myArr[i];
	}
	static BufferedReader br;
	public static void main(String args[])throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		while(MyStack.size!=0)
		{
			System.out.println("Enter size");
			MyStack.size=Integer.parseInt(br.readLine());
			if(MyStack.size==0)
			return;
			
			System.out.println("Enter num of elements");
			int n = Integer.parseInt(br.readLine());
			
			fillStack(n);
		}
		popAll();
	}
	
	public static void fillStack(int n)
	{
		for(int i=1;i<=n;i++)
		{
			System.out.print("pushing i = "+i);
			if(ne==0)
			{
				SetOfStacks.stkSet.add(new MyStack());
				SetOfStacks.cur_stk=0;
				System.out.println(" in stack "+SetOfStacks.cur_stk);
				MyStack mstk = SetOfStacks.get(0);
				mstk.stack.push(new Integer(i));
				ne++;
			}
			else
			{
				if((ne%MyStack.size==0)) //current stack is full..start adding to new stack
				{
					SetOfStacks.cur_stk++;
					System.out.println(" in stack "+SetOfStacks.cur_stk);
					System.out.println("Current stack = "+SetOfStacks.cur_stk);
					ne++;
					SetOfStacks.stkSet.add(new MyStack());
					MyStack mstk = SetOfStacks.get(cur_stk);
					mstk.stack.push(new Integer(i));
				}
				else //current stack is not full ..continue adding to it
				{
					ne++;
					System.out.println(" in stack "+SetOfStacks.cur_stk);
					MyStack mstk = SetOfStacks.get(cur_stk);
					mstk.stack.push(new Integer(i));
				}
			}
		}
		System.out.println("ne = "+ne);
	}
	
	public static void popAll()
	{
		MyStack mstk = SetOfStacks.get(0);
		while(!mstk.stack.empty())
		{
			Integer out = popStack();
			System.out.println("Element popped = "+out.toString()+"current stack = "+cur_stk);
		}
	}
	public static Integer popStack()
	{
		
			
			MyStack mstk = SetOfStacks.get(cur_stk);
			if(mstk.stack.empty())
			cur_stk--;
			mstk = SetOfStacks.get(cur_stk);
			ne--;
			return mstk.stack.pop();
		
	}
}