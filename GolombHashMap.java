package exhaustiveSearch;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class GolombHashMap
{
	static Map<Integer, String> permutation = new HashMap<Integer, String>();
	public static void main(String[] args)
	{
		System.out.println("Enter Order N");
		@SuppressWarnings("resource")
		Scanner Nin = new Scanner(System.in);
		int N = Nin.nextInt();
		int q = N + 2;		
		int bool2 = 0;
		int myCount = 0; 		
		int increment = 1;		
		int alpha = 0;
		int beta = 0;		
		final long startTime = System.currentTimeMillis();		
		int rootamount = 0;
		String[] dupearray = new String[N*N];		
		int[] rootarray = new int[N];
		int[] costasarray = new int[N+1];			
		int[] shiftarray = new int[N];	
		BigInteger prim = BigInteger.valueOf(q);	
		BigInteger bool = BigInteger.valueOf(0);
		BigInteger[] res0array = new BigInteger[prim.intValue()];
		for (BigInteger i = BigInteger.valueOf(1); i.compareTo(prim) < 0; i = i.add(BigInteger.valueOf(1)))
		{
			for (BigInteger j = BigInteger.valueOf(1); j.compareTo(prim) < 0; j = j.add(BigInteger.valueOf(1)))
			{
				res0array[j.intValue()] = i.modPow(j, prim);
				bool = BigInteger.valueOf(0);
				if (j.equals(prim.subtract(BigInteger.valueOf(1))))
				{
					for (BigInteger k = BigInteger.valueOf(1); k.compareTo(prim.subtract(BigInteger.valueOf(1))) < 0; k = k.add(BigInteger.valueOf(1)))
					{
						if (res0array[j.intValue()].equals(res0array[k.intValue()]))
						{
							bool = BigInteger.valueOf(1);
						}
					}
					if (bool.equals(BigInteger.valueOf(0)))
					{
						rootamount++;
						rootarray[rootamount] = i.intValue();
					}
				}		
			}
		}
		for (int rootloop=1;rootloop<rootamount+1;rootloop++)
		{	
			for (int rootloop1=1;rootloop1<rootamount+1;rootloop1++)
			{
				alpha = rootarray[increment];
				beta = rootarray[rootloop1];;
				int[] res1array = new int[N+1];
				int[] res2array = new int[N+1];			
				for (int i=1;i<=N;i++)
				{	
					res1array[i] = ((int)Math.pow(alpha, i) % q);
				}				
				for (int j=1;j<=N;j++)
				{
					res2array[j] = ((1 - (int)Math.pow(alpha, j)) % q);
					res2array[j] += q;			
					float res = (float) (Math.log(res2array[j])/Math.log(beta));	
					if (res != Math.round(res))
					{	
						for (int k=1;k<=N;k++)
						{	
							if (res2array[j] == res1array[k])
							{
								res = (int)Math.pow(alpha, k);
								res = (float) (Math.log(res)/Math.log(beta));
							}
						}
					}
					int output = (int) res;
					costasarray[j] = output;
				}		
				for (int f = 0; f < N ; f++)
				{
					shiftarray[f] = costasarray[f+1];
				}
				int[] n = new int[shiftarray.length];
				printPermutations(n, shiftarray);
				if (printPermutations(n, shiftarray) == 1)
				{
					for (int i = 1 ; i <= N+1 ; i++)
					{
						if (Arrays.toString(shiftarray).equals(dupearray[i]))
						{
							bool2 = 1;
						}
					}
					if (bool2 == 0)
					{
						myCount++;
						dupearray[myCount] = Arrays.toString(shiftarray);
						permutation.put(myCount, Arrays.toString(shiftarray));
					}
				}
				for (int circshift = 1 ; circshift < N ; circshift ++)
				{
					leftShift(shiftarray, 1);	
					int[] n1 = new int[shiftarray.length];
					printPermutations(n, shiftarray);
					if (printPermutations(n1, shiftarray) == 1)
					{
						for (int i = 1 ; i <= N+1 ; i++)
						{
							if (Arrays.toString(shiftarray).equals(dupearray[i]))
							{
								bool2 = 1;
							}
						}
						if (bool2 == 0)
						{
							myCount++;
							dupearray[myCount] = Arrays.toString(shiftarray);
							permutation.put(myCount, Arrays.toString(shiftarray));
						}
					}
				}	
		}
		increment++;
	}
	final long endTime = System.currentTimeMillis();
	double output = (endTime - startTime)/1000.0;
	System.out.println("Golomb search took " + output + " seconds to find " + myCount + " unique costas arrays for order N = " + N);
	}	
	public static void leftShift(int[] array, int n)
	{
	    for (int shift = 0; shift < n; shift++)
	    {
	        int first = array[0];
	        System.arraycopy( array, 1, array, 0, array.length - 1 );
	        array[array.length - 1] = first;
	    }
	}	
	public static int printPermutations(int[] n, int[] CostasArray)
	{	
		n = CostasArray;
		int bool = 0;
		int k = 0;
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
			for (int j=1; j<n.length; j++)
			{
				if (i+j < n.length)
				{	
					path[i] = n[i] - n[i+j];				
					for (int x=0; x<i; x++) 
					{
						if (path[i] == path[k])
						{
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
	    if (bool == 1)
	    {
		   	return 0;
	    }
		else
		{	
		    return 1;
		}
	}
}