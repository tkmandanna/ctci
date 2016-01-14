import java.util.*;
import java.io.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class Trial
{
	public static void main(String args[])
	{
		List<Integer> tempList;
		tempList = Arrays.asList(new Integer(1), new Integer(2));
		ArrayList<Integer> newList = new ArrayList<Integer>(tempList);

		for(Integer temp : newList)
		{
			System.out.println(temp.toString());
		}
		//CopyOnWriteArrayList<Integer> copList = new CopyOnWriteArrayList<Integer>(newList);
		ArrayList<Integer> copList = new ArrayList<Integer>(newList);
		copList.add(new Integer(3));
                List<Integer> absList = copList.subList(0,copList.size());
		for(Integer temp : absList)
		System.out.println(temp.toString());
		
	}
}
