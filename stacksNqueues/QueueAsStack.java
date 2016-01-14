import java.io.*;
import java.util.*;

/*
	Find out how to extend generic types like Stack
*/
class MyStack<T> extends Stack<T>
	{
		public static int size=3;
	}
public class QueueAsStack
{
	
	public static MyStack<Integer> main_stk;
        public static MyStack<Integer> aux_stk;
        public static MyStack<Integer> peek_stk;

        public static boolean isQEmpty()
	{
		if(main_stk.isEmpty()&&aux_stk.isEmpty())	
		{
			return true;
		}
		return false;
	}

	public static void qEnqueue(Integer inp)
	{
		while(!aux_stk.isEmpty())
		{
		   Integer res=aux_stk.pop();
		   main_stk.push(res);	
		}
		main_stk.push(inp);
	}
	public static Integer qDeque()
	{
		
		if(aux_stk.isEmpty())
		{
			while(!main_stk.isEmpty())
			{
				Integer res = main_stk.pop();
				aux_stk.push(res);
			}
			Integer res = aux_stk.pop();
			return res;
		}
		else
		{
			return aux_stk.pop();
		}
	}

        public static Integer qPeek()
	{
		if(aux_stk.isEmpty())
                {
                        while(!main_stk.isEmpty())
                        {
                                Integer res = main_stk.pop();
                                aux_stk.push(res);
                        }
                        Integer res = aux_stk.peek();
                        return res;
                }
                else
                {
                        return aux_stk.peek();
                }

	} 
	public static void main(String args[])throws IOException
	{
		main_stk = new MyStack<Integer>();
                aux_stk = new MyStack<Integer>();
                peek_stk=aux_stk;

		int choice =0;
		while(choice !=-1)
		{
			System.out.println("1: enqueue 2:dequeue 3:peek ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			choice=Integer.parseInt(br.readLine());

			switch(choice)
			{
				case 1:
				System.out.println("Enter number");
				int input = Integer.parseInt(br.readLine());
                                QueueAsStack.qEnqueue(new Integer(input));
				break;	
				
			        case 2: 
				if(QueueAsStack.isQEmpty())
				{
					System.out.println("Queue is empty");
					continue;

				}
				Integer output = QueueAsStack.qDeque();	
                                System.out.println("Dequeued value is "+output.toString());
				break;

                                case 3:
				Integer p_int = QueueAsStack.qPeek();
                                System.out.println("Peeked value = "+p_int.toString());
                                break;

				default:
				System.out.println("Wrong choice");
				break;
			}
			
		}
	}
}
