import java.io.*;
/*
	This is a program to find the magic index in an array. The magic index of an array is "i" where arr[i]=i.
	The array is sorted.
*/
public class MagicIndex
{
	public static int findMagicIndex(int[] arr,int start,int end)
	{
		int mid=(start+end)/2;
		if((mid == start)||(mid == arr.length-1))
		{
			
			if(arr[mid]==mid)
			return mid;//when the magic index is found if the start and end index differ by one
			else
			return -1; //the magic index does not exist in the array
		}

		if(arr[mid]==mid)
		return mid;// if the magic index is found

		if(arr[mid]>mid)
		{
			//check left half because all arr[i] will be > i to the right of mid.
			return findMagicIndex(arr,start,mid);
		}
		else
		{
			//check the right half
			return findMagicIndex(arr,mid,end);
		}
	}	

	public static int findMagicIndexWithDup(int []arr,int start,int end)
	{
		int mid = (start + end)/2;
		
		if(mid == start)
                {
                        
                        if(arr[mid]==mid)
                        return mid;//when the magic index is found if the start and end index differ by one
                        else
                        return -1; //the magic index does not exist in the array
                }

			if(arr[mid]==mid)
			return mid;
	
			int left = Math.min(arr[mid],mid-1); 
		/*
			let's assume arr[5]=2..and arr.size = 10..then all elements to the left of arr[5]<=2..so the magic index
			can be 0,1 or 2
		*/
			int leftResult = findMagicIndexWithDup(arr,start,left);
			if(leftResult>=0)
			return leftResult;

		//searching right sub array because couldn't find magic index in left sub array
			int right = Math.max(arr[mid],mid+1);
			int rightResult = findMagicIndexWithDup(arr,right,end);
			if(rightResult>=0)
			return rightResult;
		

		return -1;
	}

	public static void main(String[] args)
	{
		int []arr={-1,0,2,4,7,9,11,15};
		int result = findMagicIndex(arr,0,arr.length-1);
		System.out.println(""+result);

		int []arr2={-1,0,3,4,7,9,11,15};
		result = findMagicIndex(arr2,0,arr2.length-1); 
                System.out.println(""+result);

		int []arr3={2,2,4,4,4,8,12,15};
		//result = findMagicIndexWithDup(arr3,0,arr3.length-1);
		System.out.println(""+result);
		System.out.println(""+findMagicIndexWithDup(arr3,0,arr3.length-1));
	
		
		
	}
}
