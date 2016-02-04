import java.io.*;
public class Deadlock
{
	static class Friend
	{
		public String name;
	
		public Friend(String name)
		{
			this.name = name;			
		}
		
		public synchronized void bow(Friend friend)
		{
			System.out.println(""+name+" is bowing to "+friend.name);
			friend.bowBack(this);
		}

		public synchronized void bowBack(Friend friend)
		{
			System.out.println(friend.name+" is bowing back to"+name);
		}
	}
	public static void main(String args[])
	{
		final Friend butch  = new Friend("Butch");
		final Friend sundance = new Friend("Sundance");
		
		new Thread (new Runnable(){

			public void run()
			{
				butch.bow(sundance);
			}
		}).start();

		new Thread(new Runnable(){

			public void run()
			{
				sundance.bow(butch);
			}
		}).start();
	}
}
