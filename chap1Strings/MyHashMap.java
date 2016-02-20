import java.io.*;
import java.util.*;

class MyDummy
{
	String dummy;
	public MyDummy(String str)
	{
		this.dummy = str;
	}
}

public class MyHashMap<KType,VType>
{

	public static int bktCount=5; //initial number of buckets in hashmap

	class Node<KType,VType>
	{
		KType key;
		VType value;

		public Node(KType key,VType value)
		{
			this.key = key;
			this.value = value;
		}
	}

	ArrayList<LinkedList<Node>> myMap;

	public MyHashMap()
	{
		myMap = new ArrayList<LinkedList<Node>>(bktCount);// need to fill array list because it is empty initially
		/*passing bkt Count as constructor only implies that array list will wait until it has 5 nodes until it 
	         reallocates internal structures. It does not imply arraylist already has 5 empty elements.
		*/
		for(int i=0;i<bktCount;i++)
			{
				LinkedList<Node>temp = new LinkedList<Node>();
				myMap.add(temp);
			}
	}

	public void insert(KType key,VType value)
	{

		int hashKey = getHashKey(key);

		Node<KType,VType> node= new Node<KType,VType>(key,value);
		myMap.get(hashKey).add(node);

	}

	public VType get(KType key)
	{
		int hashKey =getHashKey(key);
		LinkedList<Node>possibleValues= myMap.get(hashKey);
		//check for possible values is not null here
		for(Node node: possibleValues)
		{
			if(node.key == key)
			return (VType)node.value;		
		}

		return null;
	}

	public void remove(KType key)
	{
		int hashKey=getHashKey(key);
		LinkedList<Node> list = myMap.get(hashKey);

		Iterator<Node>listIterator = list.listIterator();
		while(listIterator.hasNext())
		{
			Node node = listIterator.next();
			System.out.println("Current node key = "+node.key);
			if(node.key==key)
			{
				System.out.println("key has been found\n");
				listIterator.remove();
			}
		}
	}
	protected int getHashKey(KType key) // simple hashing function
	{
		
		System.out.println(key.toString().length());
		System.out.println("Hashcode = "+key.hashCode());
		return key.hashCode() % bktCount;
		
	}

	public static void main(String args[])
	{
		
		//MyHashMap<String,Integer> myHashMap =new MyHashMap<String,Integer>();	
		//MyHashMap<MyDummy,Integer> myHashMap =new MyHashMap<MyDummy,Integer>();		
		//MyHashMap<String,Prob1UniqueChars> myHashMap =new MyHashMap<String,Prob1UniqueChars>();	
		MyHashMap<MyDummy,Prob1UniqueChars> myHashMap =new MyHashMap<MyDummy,Prob1UniqueChars>();
		//myHashMap.insert("abc",new Integer(5));
		myHashMap.insert(new MyDummy("def"),new Prob1UniqueChars());
		//System.out.println("Result = "+myHashMap.get("abc"));
		myHashMap.insert(new MyDummy("abc"),new Prob1UniqueChars());
		System.out.println("Result = "+myHashMap.get(new MyDummy("def")).toString());
		System.out.println("Removing node def");
		myHashMap.remove(new MyDummy("def"));
	}	
}
