package com.hackerrank.day4;

/**
 * @author Nirley Gupta
 * 04-Mar-2017
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


/*
 * 
 * 
 * 
 * Input Format
 * n - No of numbers
 * a1, a2 ..... an - Numbers
 * 
 * 1<=n<=10 power 9
 *  1<=a1*a2*.....an<=10 power 15
 * 
 * Output Format
 * 
 * LCM of the product m would be displayed
 * 
 * 
 * Sample Input 0
 * 10
 * 829 455 345 229 123 121 111 14 13 12
 * 
 * Sample Output 0
 * LCM of the above numbers is 4376041608398460
 * 
 */

public class LowestCommonMultiple {
	
	static ArrayList<Integer> primes=new ArrayList<Integer>();
	HashMap<Integer,Integer> primeFactors=new HashMap<Integer,Integer>();
	long lcmProduct=1;
	
	
	/*Sieve Approach of Primes*/
	static void populatePrime(int max)
	{
		boolean[] isPrime=new boolean[max+1];
		
		Arrays.fill(isPrime, true);
		
		for(int i=2;i<=max;i++)
			if(isPrime[i])
				for(int j=i*2;j<=max;j=j+i)
					isPrime[j]=false;
	
		for(int i=2;i<=max;i++)
			if(isPrime[i])
				primes.add(i);
		
	}
	
	/* Prime Factorization */
	public void computeLCM(int[] input,int n)
	{
	
		for(int i=0;i<n;i++)
		{
			
			if(primes.contains(input[i]))
				{
				primeFactors.put(input[i], 1);
				continue;
				}
			
			int baseCheck=input[i]/2;
			
			
			for(int j=0;j<primes.size();j++)
			{
				int element=primes.get(j);
				
				if(element>baseCheck)
					break;
				
				if(input[i]%element==0)
					{
					int count=0;
					
					while(input[i]%element==0)
					{
						input[i]/=element;
						count++;
					}
					
					if(primeFactors.containsKey(element))
						count= count>primeFactors.get(element)?count:primeFactors.get(element);
					
					primeFactors.put(element,count);
					}
				
				
				
			}
		}
		
		for(Entry<Integer,Integer> e:primeFactors.entrySet())
		{
			lcmProduct*=(long)Math.pow(e.getKey(),e.getValue());
		}
		
		
	}
	
	
	public static void main(String[] agrs)
	{
		int max=0;
		Scanner scan=null;
		
		try
		{
		scan=new Scanner(System.in);
		
		// Number of Integer
		int n=scan.nextInt();
		int[] input=new int[n];
		
		for(int i=0;i<n;i++)
		{
			input[i]=scan.nextInt();
			if(max<input[i]) max=input[i];
		}
		
		long start=System.currentTimeMillis();
		
		populatePrime(max);
		LowestCommonMultiple lcm=new LowestCommonMultiple();
		lcm.computeLCM(input,n);
		
		long end=System.currentTimeMillis();
		
		System.out.println("LCM of the above numbers is " + lcm.lcmProduct);
		System.out.println("Time Taken : " + (end-start)/1000 +" seconds");
		
		}
		finally
		{
			if(scan!=null) scan.close();
		}
	}
	

}
