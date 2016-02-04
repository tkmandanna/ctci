import java.io.*;
import java.util.Random;
// if a element in a matrix is zero, make that row and column zero
public class CRzero
	{
		public static BufferedReader br;
		public static void main(String args[]) throws IOException
			{
				br = new BufferedReader(new InputStreamReader(System.in));
				CRzero crz = new CRzero();
				int n=1;
				while(n!=0)
				{
					System.out.print("Enter order");
					n=Integer.parseInt(br.readLine());
					
					int arr[][]=new int[n][n];
					crz.acceptMatrix(arr,n);
					crz.printMatrix(arr,n);
					crz.convertZeros(arr,n);
					
					System.out.println("\n result \n");
					crz.printMatrix(arr,n);
					if(n==0)
					break;
				}
				
			}
			
		public void convertZeros(int arr[][],int n)
			{
				boolean columnZ=false;
				boolean rowZ=false;
				for(int j=0;j<n;j++)
					{
						if(arr[0][j]==0)
						{
							rowZ=true;
						}
					}
					
				for(int i=0;i<n;i++)
					{
						if(arr[i][0]==0)
						{
							columnZ=true;
						}
					}
					
				for(int i=1;i<n;i++)
					{
						for(int j=1;j<n;j++)
							{
								if(arr[i][j]==0)
								{
									arr[0][j]=0;
									arr[i][0]=0;
									System.out.println("zero element = "+i+" "+j);
								}
							}
					}
					
				for(int j=1;j<n;j++)
					{
						if(arr[0][j]==0)
						{
						//leave the zeroth column element out
							for(int i=1;i<n;i++)
							{
								arr[i][j]=0;
							}
						}
					}
				System.out.println("\n setting all columns to zero \n");
				printMatrix(arr,n);
				
				for(int i=1;i<n;i++)
					{
						if(arr[i][0]==0)
						{
						//leave the zeroth row element out
							for(int j=1;j<n;j++)
							{
								arr[i][j]=0;
							}
						}
					}
				System.out.println("\n setting all rows to zero \n");
				printMatrix(arr,n);
				
				if(rowZ)
				{
					for(int j=0;j<n;j++)
					{
						arr[0][j]=0;
					}
				}
				
				if(columnZ)
				{
					for(int i=0;i<n;i++)
					{
						arr[i][0]=0;
					}
				}
			}
		public void acceptMatrix(int arr[][],int n)throws IOException
			{
				for(int i=0;i<n;i++)
					{
						for(int j=0;j<n;j++)
							{
								arr[i][j]=i*n+j+1;
							}
					}
					
				System.out.println("Enter number of random zeros");
				int zCount = Integer.parseInt(br.readLine());
				
				for(int i=0;i<zCount;i++)
				{
					Random rg = new Random();
					int row=rg.nextInt(n);
					int col=rg.nextInt(n);
					System.out.println("\n 0 at "+row+" "+col);
					arr[row][col]=0;
				}
			}
			
		public void printMatrix(int arr[][],int n)
			{
				for(int i=0;i<n;i++)
					{
						for(int j=0;j<n;j++)
							{
								System.out.print(arr[i][j]+" ");
							}
						System.out.println(" ");
					}
			}
	}