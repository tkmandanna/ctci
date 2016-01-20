import java.io.*;
public class SimpleThreads
{
	
	private static void threadMessage(String str)
	{
		String threadName = Thread.currentThread().getName();
		System.out.println("thread name : "+threadName+": "+str);
	}
	private static class MsgInfo implements Runnable
	{
		public void run()
		{
			//String threadName = Thread.currentThread().getName();
			String [] strArr = {"Hello world","My name","is","Mandanna Thekkada.","I like running"};

			try
			{
				for(int i=0;i<strArr.length;i++)
				{
			
					threadMessage(strArr[i]);
					Thread.sleep(4000);
				}
			}
			catch(InterruptedException ie)
                                {
                                        threadMessage("I wasn't done.");
                                        ie.printStackTrace();
                                }

		}

	}

	public static void main(String[] args)
	{
		Thread t = new Thread(new SimpleThreads.MsgInfo(),"t1");
		t.start();

		if(args.length>0)
		{
			try
			{
				long patience = Long.parseLong(args[0])*1000;
				long startTime = System.currentTimeMillis();	
				while(t.isAlive())
				{
					threadMessage("Still waiting");
					t.join(1000);
					if((System.currentTimeMillis()-startTime)>patience)
					{
						threadMessage("Tired of waiting");
						t.interrupt();
						t.join();
					}

				}
			}
			catch(NumberFormatException ne)
			{
				ne.printStackTrace();
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
		threadMessage("The End");
	}
}
