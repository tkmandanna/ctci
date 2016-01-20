import java.io.*;
import java.util.Random;

public class PC
{
	static class Drop
	{
		boolean empty;
		String message;
		/*
			if empty = true, consumer must wait;
			if empty = false, producer must wait;
		*/

		public Drop()
		{
			empty=true;
		}

		public synchronized void put(String msg)
		{
			try
			{
			while(empty==false)
			{

				wait();/* thread can woken by notify and not just the one it is waiting for..so once it wake ups it
				must check again if empty is true this time around. If it is true then it can exit the while , put the message
				,set empty to false and then notifyAll()*/
			}
			
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
			empty=false;
			this.message = msg;

			notifyAll();

		}

		public synchronized String take()
		{
			try
			{
			while(empty==true)
			{
				wait();
			}
			}
                        catch (InterruptedException ie)
                        {
                                ie.printStackTrace();
                        }
	
			empty = true;
			System.out.println("Received this message = "+message);
			notifyAll();	
			return message;		
		}
			
	}

	static class Producer implements Runnable
	{
		private Drop drop;

		public Producer(Drop drop)
		{ 	
			this.drop=drop;
		}

		public void run()
		{
			String []arr={"My","name","is","Mandanna"," ","Thekkada."};
			Random random=new Random();
			for(int i=0;i<arr.length;i++)
			{
				drop.put(arr[i]);
				try
				{
					Thread.sleep(random.nextInt(5000));
				}
	                        catch (InterruptedException ie)
        	                {
                	                ie.printStackTrace();
                        	}

			}
			drop.put("done");
		}
	}

	static class Consumer implements Runnable
	{
		private Drop drop;
		public Consumer(Drop drop)
		{
			this.drop = drop;
		}
		
		public void run()
		{
			String message="";
			Random random = new Random();
			do{
				message = drop.take();
				try
				{
					Thread.sleep(random.nextInt(5000));
				}
				
	                        catch (InterruptedException ie)
        	                {
                	                ie.printStackTrace();
                        	}

				
			}while(!message.equals("done"));
			
		}
	}

	public static void main(String args[])
	{
		Drop drop = new Drop();
		Thread t1 = new Thread(new Producer(drop));
		Thread t2 = new Thread(new Consumer(drop));

		t1.start();
		t2.start();
	}
}
