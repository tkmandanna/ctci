import java.io.*;

public class SameOnes
{
	public static void bruteForce(int num)
	{
		System.out.println("ones in "+num+" =  "+countOnes(num));
	}	

	public static int countOnes(int num)
	{
		int count = 0;
		int temp_num=num;
		while(num>0)
		{
			count+=(num%2);
			num=num/2;
		}
		System.out.println(temp_num+" "+Integer.toBinaryString(temp_num)+" "+count);
		return count;
	}
	
	public static void bruteNextBiggest(int num)
	{
		int one_ct_orig = countOnes(num);
		int temp = num+1;
		while(countOnes(temp)!=one_ct_orig)
		{
			temp++;
		}
		System.out.println(" \n Next biggest ");
		System.out.println(countOnes(temp));
	}

	public static void bruteNextSmallest(int num)
        {
                int one_ct_orig = countOnes(num);
                int temp = num-1;
                while(countOnes(temp)!=one_ct_orig)
                {
                        temp--;
                }
                System.out.println(" \n Next smallest ");
                System.out.println(countOnes(temp));
        }

	public static void main(String args[])
	{
		System.out.println("\n For 15\n");
		bruteNextBiggest(15);
		//bruteNextSmallest(15);
		System.out.println("\n For 12\n");
                bruteNextBiggest(12);
		System.out.println("\n For 23 \n");
		//bruteNextBiggest(23);
		//bruteNextSmallest(23);
	
		/*System.out.println(Integer.toBinaryString(100))	;
		System.out.println(countOnes(100));
		System.out.println(Integer.toBinaryString(86)) ;
		System.out.println(countOnes(86));
		System.out.println(Integer.toBinaryString(13453)) ;
		System.out.println(countOnes(13453));*/

	}
}
