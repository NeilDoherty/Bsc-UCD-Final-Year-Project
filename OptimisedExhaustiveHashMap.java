package exhaustiveSearch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class OptimisedExhaustiveHashMap
{
	static Map<Integer, String> permutation = new HashMap<Integer, String>();
	public static void main(String[] args)
	{
		System.out.println("Enter Order N");
		@SuppressWarnings("resource")
		Scanner Nin = new Scanner(System.in);
		int N = Nin.nextInt();
	    int[] n = new int[N];
	    int CostasArray[] = new int[N];
	    for (int i = 0 ; i < N ; i ++)
	    {
	    	CostasArray[i] = N;
	    }
	    final long startTime = System.currentTimeMillis();
	    int result = printPermutations(n, CostasArray, 0);
	    final long endTime = System.currentTimeMillis();
	    double output = (endTime - startTime)/1000.0;
	    System.out.println("Exhaustive search took " + output + " seconds to find " + result + " unique costas arrays for order N = " + N);
	}	
	static int instanceCounter = 0;	
	public static int printPermutations(int[] n, int[] CostasArray, int index)
	{	
		int bool = 0;
		int k = 0;
		int backtrack = 0;
		int[] oparray = new int[n.length];
		int[] path = new int[(n.length)*(n.length)];
		int[] path2 = new int[(n.length)*(n.length)];
		String[] append = new String[(n.length)*(n.length)];	
		for (int i=0; i<n.length-1; i++)
		{	
			oparray[i] = n[i] - n[i+1];		
		}		
		for (int i=0; i<n.length-1; i++)
		{	
			if (oparray[i] != 0)
			{
				for (int j=1; j<n.length; j++)
				{
					if (i+j < n.length)
					{
						path[i] = n[i] - n[i+j];
						
						for (int x=0; x<i; x++) 
						{
							if (path[i] == path[k])
							{	
								backtrack = 1;
								bool = 1;
							}
						}
						String test = (Integer.toString(path[i]) + Integer.toString(i - (i+j)));
						append[k] = test;
						path2[k] = path[i];
						k++;
					}
				}
			}
		}		
		if (backtrack == 0)
		{
			for (int i=0; i<k; i++)
			{
				for (int j=1; j<k; j++)
			    {
					if ((append[i].equals(append[j])) && (i != j))
					{
						bool = 1;
					}
			    }
			}
		}		
		if (backtrack == 0)
		{
			for (int i=0; i<n.length-1; i++) 
			{
			    for (int j=i+1; j<n.length; j++)
			    {		    	
			    	if (n[i] == n[j])
			        {
			           bool = 1;
			        }			    	
			    	if (oparray[i] == oparray[j])
			        {
			           bool = 1;
			        }	
			        if ((n[i] > n.length) || (n[i] < 1)) 
			        {
			        	bool = 1;
			        }	
			        if (i == n.length-2)
			        {
			        	if ((n[j] > n.length) || (n[j] < 1))
			        	{
			        		bool = 1;
			        	}
			        }      
			    }
			}
		}		
	    if (index == n.length)
	    {	
	    	if (bool != 1)
	        {	
		    	instanceCounter++;
		    	permutation.put(instanceCounter, Arrays.toString(n));
		    }    	
	        return 1;
	    }	    
	    for (int i = 0; i <= CostasArray[index]; i++)
	    { 	    		    	
	    	n[index] = i;
	    	printPermutations(n, CostasArray, index+1);
	    }
		return instanceCounter;
	}
}

/*
int alarm = 0;
int counter = 0;
int[] n = {1,2,4,5};
String[] checker = new String[(n.length)*(n.length)];
int[] oparray = new int[(n.length)*(n.length)];

for (int i=0; i<n.length-1; i++)
{
	for (int j=1; j<n.length; j++)
	{
		if (i+j < n.length)
		{	
			oparray[i] = n[i] - n[i+j];				
			String test = (Integer.toString(oparray[i]) + Integer.toString(i - (i+j)));
			//System.out.println("distance from " + n[i] + " to " + n[i+j] + " is " + test);
			checker[counter] = test;
			counter ++;
		}
	}
}
System.out.println(Arrays.toString(checker));
for (int i = 0 ; i < counter ; i++)
{
	for (int j = 0 ; j < counter ; j++)
	{
		//System.out.println("checker[i] is " + checker[i] + " and checker[j] is " + checker[j] + " and i is " + i + " and j is " +j);
		if ((checker[i].equals(checker[j])) && (i != j))
		{
			alarm = 1;
		}
	}
}
System.out.println("alarm is " + alarm);*/