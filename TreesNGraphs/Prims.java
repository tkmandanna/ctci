import java.io.*;
//Prim's algorithm .. used to find a minimum spanning tree
public class Prims
{
	
	public static void main(String args[])
		{
			int [][]arr = { {-1,3,1,6,-1,-1},
							{3,-1,5,-1,3,-1},
							{1,5,-1,5,6,4},
							{6,-1,5,-1,-1,2},
							{-1,3,6,-1,-1,6},
							{-1,-1,4,2,6,-1}};
							
			int v = 6;
			int []visited={1,0,0,0,0,0};//starting from the first vertex
			int me=-1; //variable to  store minimum edge
			int nv=-1;//next vertex to be added to min spanning tree
			int ne=0;
			int cost =0;
			System.out.println("Selected vertex = 0");
			while(ne < (v-1)) //while number of edges selected is < than no. of vertices-1
			{
				me=-1;
				nv=-1;
				for(int i=0;i<v;i++)// go through edges of all vertices currently in MST
				{
					if(visited[i]==1)
					{
						//this node is already part of the minimum spanning tree..check all it's outgoing edges now
						for(int j=0;j<v;j++)
							{
								if(arr[i][j]!=-1)
								{
									if(visited[j]!=1)
									{
									/* node j has not been visited yet..else both i and j have already been visited and edge i-j is selected a cycle would form*/
										if(me==-1)
										{
											me=arr[i][j];
											nv=j; //i already exists in the minimum spanning tree
										}
									    else if(arr[i][j]<me)
										{
											me=arr[i][j];
											nv=j; //i already exists in the minimum spanning tree
										}
									}
								}
							}	
					}
				}
				System.out.println("ne = "+ne);
				System.out.println("Selected edge cost = "+me);
				System.out.println("Selected vertex = "+nv);
				visited[nv]=1;//marking this vertex as visited
				cost+=me;//adding cost 
				ne++;//incrementing number of edges
				System.out.println("ne = "+ne);
			}
			System.out.println("Total cost = "+cost);
		}
}