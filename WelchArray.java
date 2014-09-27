package exhaustiveSearch;
import java.math.BigInteger;
import java.util.Scanner;
public class WelchArray
{
	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		int inc = 0;	
		System.out.println("Enter Order N");
		@SuppressWarnings("resource")
		Scanner Nin = new Scanner(System.in);
		int N = Nin.nextInt();
		int p = N + 1;
		String currentArray = null;
		int increment = 0;	
		int rootamount = 0;
		int[] rootarray = new int[N];
		final long startTime = System.currentTimeMillis();		
		BigInteger prim = BigInteger.valueOf(p);	
		BigInteger bool = BigInteger.valueOf(0);		
		BigInteger[] res0array = new BigInteger[prim.intValue()];	
		for (BigInteger i = BigInteger.valueOf(1); i.compareTo(prim) < 0; i = i.add(BigInteger.valueOf(1)))
		{
			for (BigInteger j = BigInteger.valueOf(1); j.compareTo(prim) < 0; j = j.add(BigInteger.valueOf(1)))
			{
				res0array[j.intValue()] = i.modPow(j, prim);
				currentArray = currentArray.valueOf(i);
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
						inc++;
						rootarray[rootamount] = i.intValue();
					}
				}
			}
		}
		for (int rootloop=1;rootloop<rootamount+1;rootloop++)
		{	
			int[] res1array = new int[N];						
			int alpha = rootarray[rootloop];				
			for (int i=0;i<N;i++)
			{	
				res1array[i] = (((int)Math.pow(alpha, i) % p));
			}
			for (increment=0;increment<(N-1);increment++)
			{
				for (int shift = 0; shift < increment; shift++)
			    {
			        int first = res1array[0];
			        System.arraycopy( res1array, 1, res1array, 0, res1array.length - 1 );
			        res1array[res1array.length - 1] = first;         
			    }
				inc++;
			}
			inc --;
		}
		final long endTime = System.currentTimeMillis();
	    double output = (endTime - startTime)/1000.0;
	    System.out.println("Exhaustive search took " + output + " seconds to find "+ inc+" unique costas arrays for order N = " + N);
	}
}