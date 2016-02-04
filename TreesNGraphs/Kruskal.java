import java.util.*;
public class Kruskal
{
	public static int []visited={0,0,0,0,0,0};//visited array
	
	public static List<Set<Integer>>connComponents;  /*set  of  connected components...an edge can be selected only if it's vertices belong to two diff connected components else it will form a cycle*/
	
	public static int connComp = 1; /*variable to count the number of connected components in the graph..atleast 1 connected component in the graph..*/
	
	public void setVisitedUsingArr(int v1,int v2)
	{
	    /*you can use reverse mapping wherein if two nodes belong to the same connected component, they have the same
		visited[i] value. When an edge is selected, and the two vertices already belong to different connected components then
		all the visited[i] of one of the connected components should take the value of the other connected component.*/
		
		/*
		Else, if one of vertices has not yet been discoverd yet it takes the visited[i] of the other connected component
		and if both nodes have not been discovered yet, they both take a new Conn Comp value for visited[i]
		
		An edge cannot be selected if it's visited[v1] == visited[v2]; because  this will form a cycle
		*/
	}
	
	public static void setVisited(int i,int j)
	{
		ListIterator<Set<Integer>> listIterator = connComponents.listIterator();
			Set<Integer> v1Set =null;
			Set<Integer> v2Set = null;
			while(listIterator.hasNext())
			{
				v1Set = listIterator.next();
				if(v1Set.contains(new Integer(i)))
				{
					System.out.println("i = "+i);
				   break;
				}
				
			}
			
		listIterator = connComponents.listIterator();
		int remIndex=-1;
		while(listIterator.hasNext())
			{
				v2Set = listIterator.next();
				remIndex++;
				if(v2Set.contains(new Integer(j)))
				{
					System.out.println("j = "+j);
				   
				   break;
				}
				
			}
		System.out.println("remIndex = "+remIndex);
		if(remIndex !=-1) 
		{
		/* both vertices have already been discovered but are in different connected components and so must be linked or the union of the two components must be formed*/
			
			System.out.println("Printing v1 set");
			for(Integer temp : v1Set)
			{
				System.out.print(temp.toString()+",");
			}
			System.out.println("\nPrinting v2 set");
			for(Integer temp : v2Set)
			{
				System.out.print(temp.toString()+",");
			}
			connComponents.remove(remIndex); //removing connComp of node j and adding it to node i
			v1Set.addAll(v2Set);
		}
		else
		{
			//Both vertices are newly discovered and so must belong to a new connected component
			System.out.println("\nNewly discovered Vertices = "+i+" "+j+"\n");
			v1Set = new HashSet<Integer>();
			v1Set.add(new Integer(i));
			v1Set.add(new Integer(j));
			connComponents.add(v1Set);
			return;
		}
		
	}
	
	public static boolean ifCycle(int i,int j)
	{
		if(connComponents == null)
		{
			connComponents = new LinkedList<Set<Integer>>();
			Set<Integer> connSet = new HashSet<Integer>();
			connSet.add(new Integer(i));
			connSet.add(new Integer(j));
			
			connComponents.add(connSet);
		}
		else
		{
			ListIterator<Set<Integer>> listIterator = connComponents.listIterator();
			Set<Integer> v1Set ;
			Set<Integer> v2Set;
			while(listIterator.hasNext())
			{
				v1Set = listIterator.next();
				if(v1Set.contains(new Integer(i)))
				{
				   if(v1Set.contains(new Integer(j)))
				   return true; //both are in same connected component
				}
				
				if(v1Set.contains(new Integer(j)))
				{
				   if(v1Set.contains(new Integer(i)))
				   return true; //both are in same connected component
				}
			}
			
		}
		
		return false;
	}
	
	public static boolean ifCycleUsingVisited(int i,int j)
	{
		if((visited[i]==0)&&(visited[j]==0))
		{
			//both the vertices have not yet been discoverd..they belong to a new Connected Component
			visited[i]=connComp;
			visited[j]=connComp;
			
			connComp++; //incrementing 
		}
		return false;
	}
	public static void main(String args[])
	{
		int [][]arr = { {-1,3,1,6,-1,-1},
						{-1,-1,5,-1,3,-1},
						{-1,-1,-1,5,6,4},
						{-1,-1,-1,-1,-1,2},
						{-1,-1,-1,-1,-1,6},
						{-1,-1,-1,-1,-1,-1}};
							
		
		int v =6;
		int []visited={0,0,0,0,0,0};
		
		//edge [] e_arr = new edge
		
		int noe=0; //count number of selected edges 
		System.out.println("Starting traversals\n");
		while(noe<(v-1)) //for 6 vertices 5 edges are selected
		{
			int v1=0;
			int v2=0; //vertices of the selected edge 
			int min_edge = -1;
			for(int i=0;i<v;i++)
			{
				
				for(int j=i+1;j<v;j++) //main diagonal all -1s
				{
					if(arr[i][j]!=-1)
					{
						System.out.println("min_edge = "+min_edge);
						System.out.println("arr ["+i+"]["+j+"] = "+arr[i][j]);
						if((min_edge == -1)&&!ifCycle(i,j)) //min_edge cannot be set a vertice that can cause a cycle
						{	
							min_edge = arr[i][j];
							v1=i;  //what if first non -1 edge is also the minimum edge
							v2=j;
						}
						else if((arr[i][j]<min_edge)&&!ifCycle(i,j)) //not <= because we can multiple min edges of the same weight
						{
						//edge weight is lower than min edge and the both vertices have not already been visited or else a cycle would form
							min_edge = arr[i][j];
							System.out.println("Temporary min edge between "+i+" "+j+" = "+min_edge);
							v1=i;
							v2=j;
						}
						
					}
					
				}
			}
			
			setVisited(v1,v2); //function to mark visited so that cycles are not formed
			
			arr[v1][v2]=-1; //marking this edge so it's not selected again
			noe++;//incrementing number of selected edges
			System.out.println("Selected edge between "+v1+" "+v2+" = "+min_edge);
		}
	}
}