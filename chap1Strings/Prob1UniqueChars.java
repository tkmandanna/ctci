import java.io.*;
import java.util.*;

public class Prob1UniqueChars
	{
		public static void  main(String args[])throws IOException
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true)
			{
				System.out.println("Enter String");
				String temp=br.readLine();
				
				if(temp.equals("exit"))
				return;

				if(uniqueStr(temp))
					System.out.println("Unique str");
				else
					System.out.println("Not Unique");
	
			}
		}

		public static boolean uniqueStr(String str)
		{

			HashMap<Character,Integer> hm= new HashMap<Character,Integer>();

			if(str == null)
			return false;
			else
				{
					str=str.trim();
					if(str.equals(""))
					return false;
				}

			for(int i=0;i<str.length();i++)
			{
				if(hm.containsKey(str.charAt(i)))
					return false;
				else
					hm.put(str.charAt(i),1);				
			}

			return true;
		}
	}
