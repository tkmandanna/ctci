import java.io.*;

public class Dijkstra
{
	public static void main(String args[])
		{
			int [][]arr = { {0,4,-1,-1,-1,-1,-1,8,-1},
							{4,-1,8,-1,-1,-1,-1,11,-1},
							{-1,8,-1,7,-1,4,-1,-1,2},
							{-1,-1,7,-1,9,14,-1,-1,-1},
							{-1,-1,-1,9,-1,10,-1,-1,-1},
							{-1,-1,4,14,10,-1,2,-1,-1},
							{-1,-1,-1,-1,-1,2,-1,1,6},
							{8,11,-1,-1,-1,-1,1,-1,7},
							{-1,-1,2,-1,-1,-1,6,7,-1}};
							
			int src =0; //source vertex
			int v = 9;//number of vertices
			int minV = src; //minimum vertex or next closest neighbour to be explored
			int minDist =-1; //distance from source to current node being explored
			int []visited = {0,0,0,0,0,0,0,0,0}; //set to mark vertices that have been visited already
			
			
			for(int i=src;i<v;i++)
			{
				//finding closest neighbour
				//resetting next closest neighbour values
				minV=-1;
				minDist=-1;
				/* this loop runs in O(v) but can be improved using a min priority queue implemented using a fibonacci heap to 
				improve running time to O((E)+V(log(V)))..need to learn to use an adjacency list*/
				for(int k=0;k<v;k++)
				{
					if(minDist == -1)
					{
						if(arr[src][k]!=-1)
						{
							if(visited[k]==0)//this vertice has not been visited..could be the next closest neighbour
							{
								minDist = arr[src][k];
								minV = k;
							}
						}
					}
					else
					{
						if(arr[src][k]!=-1)
						{
							if(arr[src][k]<minDist)
							{
								if(visited[k]==0)//this vertice has not been visited..could be the next closest neighbour
								{
									minDist = arr[src][k];
									minV=k;
								}
							}
						}
					}
					
				}
				visited[minV]=1;//marking selected node as visited
				System.out.println("This is the closest neighbour "+minV+" dist = "+minDist);
				
				//closest known neighbour
				for(int j=0;j<v;j++)
					{
						if(arr[minV][j]!= -1)
							{
								if(minV!=j) //this node has already been visited
								{
									
									int new_dist=arr[minV][j]+minDist;
									if(arr[src][j]==-1)
										{
										arr[src][j]=new_dist; 
										/*path to this node from the source has been discovered for the first time*/
										System.out.println("Node "+j+"has been discovered with distance = "+new_dist);
										}
									else if(new_dist < arr[src][j])
									{
										/*newly calculated distance is lower than currently know distance to node j from source vertex*/
										System.out.println("Old distance = "+arr[src][j]);
										System.out.println("New distance = "+new_dist);
										arr[src][j]=new_dist;
									}
								}
							}
						
					}
			}
			
			System.out.println("Printing Minimum Spanning Tree");
			for(int i=src;i<v;i++)
			{
				System.out.println("vertex = "+i+" dist = "+arr[src][i]);
			}
		}
}