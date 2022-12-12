/*
Mr. Param is working with Strings.
He is given a String S. He has to find the palindromes in S, 
A palindrome can be a substring of S (Strictly substrings only, not subsequences).

Your task is to find the count the number of palindromes can be formed from S.

NOTE: Count each occurence of palindromes if duplicate substrings found. 

Input Format:
-------------
A string S

Output Format:
--------------
Print an integer, number of palindromes.


Sample Input-1:
---------------
divider

Sample Output-1:
----------------
9

Explanation:
-------------
Palindromes are d, i, v, i, d, e, r, ivi, divid

Sample Input-2:
---------------
abcdef

Sample Output-2:
----------------
6

Explanation:
-------------
Palindromes are a, b, c, d, e, f.

*/


/*
Multiple palindromes have the same centers. If we choose a center, we can
continue to expand around it as long as we can make larger and larger palindromes.

Let's take the string "lever" as an example: if you choose the character 'v' as 
the center, one can see that the palindromes "v" and "eve" are possible. 
However, the final expansion "lever" is not a palindrome (the first and last characters don't match).

Algorithm

1) We choose all possible centers for potential palindromes:

	Every single character in the string is a center for possible odd-length palindromes
	Every pair of consecutive characters in the string is a center for possible even-length palindromes.

2)For every center, we can expand around it as long as we get 
palindromes (i.e. the first and last characters should match).

Time Complexity: O(N^2)

The total time taken in this approach is dictated by two variables:
	The number of possible palindromic centers we process.
	How much time we spend processing each center.

The number of possible palindromic centers is 2N−1: 
there are N single character centers and N−1 consecutive character pairs as centers.

Each center can potentially expand to the length of the string,
so time spent on each center is linear on average. 
Thus total time spent is N*(2N−1)= N^2

Space Complexity: O(1) 
*/

import java.util.*;

class Palindrome 
{
    int count = 1;
	public int countSubstrings(String s) 
	{
		if(s.length()==0) 
			return 0;

		for(int i = 0; i < s.length()-1; i++)
		{
			// odd-length palindromes, single character center
			countPalindromesAroundCenter(s, i, i); 
			
			// even-length palindromes, consecutive characters center
			countPalindromesAroundCenter(s, i, i + 1);   
		}
		return count;
	}    

	private void countPalindromesAroundCenter(String s, int lo, int hi) 
	{
		while (lo >= 0 && hi < s.length()) 
		{
            if (s.charAt(lo) != s.charAt(hi))
                break;      // the first and last characters don't match!

            // expand around the center
            lo--;
            hi++;
            count++;
        }		
	}
	
	public static void main(String args[])
	{
		String S=new Scanner(System.in).next();
		System.out.println(new Palindrome().countSubstrings(S));
	}
}

/*
==== testcases ====
case =1
input =radarracecar
output =19

case =2
input =tattarrattat
output =24

case =3
input =nolemonnomelon
output =21

case =4
input =wasitcatisawinmygymmadam
output =29

case =5
input =wasitcatisawinmygymmadammadamitsaradarborroworrob
output =74

case =6
input =indeedyouneedstresseddesserts
output =44

case =7
input =civicradarrefersteponnopets
output =40

case =8
input =civicradarrefersteponnopetsindeedyouneedstresseddessertsmadamitsaradarborroworrob
output =123

case =9
input =aaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
output =989

case =10
input =aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
output =3160
*/