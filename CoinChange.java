/*
You are given some tokens printed with unique numbers on them and an integer T.
Your task is to find the least number of tokens that you need to make up the 
value T, by adding the numbers printed on all the tokens. 
If you cannot make the value T, by any combination of the tokens, return -1.

NOTE: Assume that you have an unlimited set of tokens of each number type.

Input Format:
-------------
Line-1: Space separated integers tokens[], number printed on tokens.
Line-2: An integer T.

Output Format:
--------------
Print an integer, minimum number of tokens to make the value T.


Sample Input-1:
---------------
1 2 5
11

Sample Output-1:
----------------
3

Explanation:
------------
5+5+1 = 11


Sample Input-2:
---------------
2 4
15

Sample Output-2:
----------------
-1


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CoinChange 
{
	public int coinChangeMem(int[] coins, int amount) 
	{
		if (amount < 1) return 0;
		return coinChange(coins, amount, new int[amount]);
	}

	private int coinChange(int[] coins, int rem, int[] count) 
	{
		if (rem < 0) return -1;
		if (rem == 0) return 0;
		if (count[rem - 1] != 0) return count[rem - 1];
		int min = Integer.MAX_VALUE;
		for (int coin : coins) 
		{
		  int res = coinChange(coins, rem - coin, count);
		  if (res >= 0 && res < min)
			min = 1 + res;
		}
		count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[rem - 1];
	}

	public static  int coinChangeDP(int[] coins, int amount) 
	{                            
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		System.out.println(Arrays.toString(dp));
		for (int i = 1; i <= amount; i++) 
		{
			for (int j = 0; j < coins.length; j++) 
			{
				if (coins[j] <= i) 
				{
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		System.out.println(Arrays.toString(dp));
		return dp[amount] > amount ? -1 : dp[amount];
    }
	
	public static void main(String args[] ) throws IOException 
	{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String[] numline = str.split(" ");
		int[] coins = Arrays.asList(numline).stream().mapToInt(Integer::parseInt).toArray();
		int amount=Integer.parseInt( br.readLine());
		System.out.println(coinChangeDP(coins,amount));   	
	}
}

/*
==== testcases ====
case =1
input =2 5 3 6
10
output =2

case =2
input =1 2 3
4
output =2

case =3
input =2 3 4 5
16
output =4

case =4
input =3 8 5 9 7 2 4
50
output =6

case =5
input =2 3 5 7
189
output =27

case =6
input =1 2 3 4
2578
output =645

case =7
input =2 4 7 9
5434
output =604

case =8
input =1 2 3 4 5 6 7 8 9
7645646
output =849517

*/