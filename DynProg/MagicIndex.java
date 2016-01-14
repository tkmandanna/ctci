import java.io.*;
public class MagicIndex
{
	public static int findMagicIndex(int[] arr,int start,int end)
	{
		int mid=(start+end)/2;
		System.out.println("mid = "+mid);
		if((mid == start)||(mid == arr.length-1))
		{
			if(arr[mid]==mid)
			return mid;
			else
			return -1;
		}

		if(arr[mid]==mid)
		return mid;

		if(arr[mid]>mid)
		{
			//check left half
			return findMagicIndex(arr,start,mid);
		}
		else
		{
			return findMagicIndex(arr,mid,end);
		}
	}	

	public static void main(String[] args)
	{
		int arr[]={-1,0,2,4,7,9,11,15};
		System.out.println(""+findMagicIndex(arr,0,7));

		int []arr2={-1,0,3,4,7,9,11,15};
		int result = findMagicIndex(arr2,0,7); 
                System.out.println(""+result);
		
	}
}
