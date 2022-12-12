/*
In Turkey, an ice cream parlour gives an offer to a lucky kid.
The parlour keep N icecream cups in a row, and there are different flavours 
of icecreams, where i-th cup filled with the flavour[i] type of ice cream.

The kid can pick the continuous set of ice cream cups, where the cups filled
with the icecreams of atmost two different flavours. The kid wants to 
pick maximum number of icecream cups from the row.

You will be given the integer array, flavours[] of size N.
Your task is to help the kid to pick the maximum number of icecream cups 
with atmost two different flavours.


Input Format:
-------------
Line-1: An integer, number of icecreams.
Line-2: N space separated integers, flavours[] 

Output Format:
--------------
Print an integer result, maximum number of icecream cups can be picked.


Sample Input-1:
---------------
10
1 2 3 1 1 3 3 2 3 2

Sample Output-1:
----------------
5

Explanation:
------------
The kid can pick the continuous set of icecream cups as follows: 3 1 1 3 3
Where the cups are filled with two different flavours, 1 and 3.



Sample Input-2:
---------------
10
2 1 1 3 2 1 3 0 0 3

Sample Output-2:
----------------
4

*/
import java.util.*;

/*
7
1 2 3 2 2 1 4
*/

/* Algorithm: totalIcecreamBF
1) Initialize max_picked as 0.
2) Iterate over left, the left index of the subarray.
3) For every subarray start at index left, we iterate over every index right to 
fix the end of subarray, and calculate the types of Icecreams in this subarray.
	If there are no more than 2 types, this subarray is valid, we update max_picked
	with the length of this subarray.
	Otherwise, the current subarray, as well as all the longer subarrays 
	(with the same left index left) are invalid. Move on to the next left index left + 1.
4) Once we finish the iteration, return max_picked as the maximum number of Icecreams we can collect.

Time complexity: O(n^2): Two nested loops

Space complexity: O(1)

During the iteration, we need to count the number of types in every possible subarray 
and update the maximum length. Since we used the early stop method, 
thus the types will never exceed 3. Therefore, the space complexity is O(1)
*/

class maxIcecream
{
    public int totalIcecreamBF(int[] Icecreams) 
	{
        // Maximum number of Icecreams we can pick
        int maxPicked = 0;
        
        // Iterate over the left index left of subarrays.
        for (int left = 0; left < Icecreams.length; ++left) {
            // Empty set to count the type of Icecreams.
            Set<Integer> basket = new HashSet<>();
            int right = left;
            
            // Iterate over the right index right of subarrays.
            while (right < Icecreams.length) {
                // Early stop. If adding this Icecream makes 3 types of Icecream,
                // we should stop the inner loop.
                if (!basket.contains(Icecreams[right]) && basket.size() == 2)
                    break;
                
                // Otherwise, update the number of this Icecream.
                basket.add(Icecreams[right]);
                right++;
            }
            
            // Update maxPicked.
            maxPicked = Math.max(maxPicked, right - left);
        }
        
        // Return maxPicked as the maximum length of valid subarray.
        // (maximum number of Icecreams we can pick).
        return maxPicked;
    }

/* Algorithm: totalIcecream (sliding window)
1)Initialize max_picked = 0 as the maximum Icecreams we can collect, 
and use hash map basket to record the types of Icecreams in the current window.

2) Start with an empty window having left = 0 and right = 0 as its left and right index.

3) We iterate over right and add Icecreams[right] to this window.

	If there are no more than 2 types of Icecreams, this subarray is valid.
	Otherwise, we need to keep removing Icecreams from the left side until there are
	only 2 types of Icecreams in the window.

	Then we update max_picked as max(max_picked, right - left + 1).

4) Once we finish iterating, return max_picked as the maximum number of Icecreams we can collect.

Time complexity: O(n)

Both indexes left and right only monotonically increased during the iteration,
thus we have at most 2n steps.
At each step, we update the hash set by addition or deletion of one Icecream, which takes constant time.
In summary, the overall time complexity is O(n)

Space complexity: O(1)
*/

    public int totalIcecream(int[] Icecreams) 
	{
        // We use a hash map 'basket' to store the number of each type of Icecream.
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxPicked = 0;
        
        // Add Icecream from the right index (right) of the window.
        for (int right = 0; right < Icecreams.length; ++right) 
		{
            basket.put(Icecreams[right], basket.getOrDefault(Icecreams[right], 0) + 1);

            // If the current window has more than 2 types of Icecream,
            // we remove Icecream from the left index (left) of the window,
            // until the window has only 2 types of Icecream.
            while (basket.size() > 2) 
			{
                basket.put(Icecreams[left], basket.get(Icecreams[left]) - 1);
                if (basket.get(Icecreams[left]) == 0)
                    basket.remove(Icecreams[left]);
                left++;
            }
            
            // Update maxPicked
            maxPicked = Math.max(maxPicked, right - left + 1);
        }
        
        // Return maxPicked as the maximum number of Icecreams we can collect
        return maxPicked;
    }

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int parlour[] = new int[n];
		for(int i = 0; i < n; i++)
			parlour[i] = sc.nextInt();
		System.out.println(new maxIcecream().totalIcecream(parlour));
	}
}

/*
==== testcases ====
case =1
input =20
3 2 1 3 2 3 1 1 2 1 2 3 1 2 1 2 1 3 3 3
output =5

case =2
input =20
1 1 3 2 2 3 3 1 1 3 3 3 3 2 3 3 1 1 2 1
output =8

case =3
input =50
2 1 1 1 5 2 2 5 5 5 5 3 4 3 1 4 4 2 1 1 2 5 5 3 2 3 2 1 4 1 5 5 5 4 5 2 3 1 4 5 1 2 4 1 4 4 4 2 3 4
output =7

case =4
input =50
4 1 3 3 1 4 1 4 3 2 1 2 4 4 2 2 3 1 4 3 4 3 3 2 4 1 1 1 2 3 3 1 4 3 2 4 3 3 4 2 2 1 1 2 1 3 4 4 3 2
output =6

case =5
input =100
4 3 2 1 4 3 3 4 3 1 1 2 4 4 2 2 4 2 3 3 4 4 2 3 4 4 2 2 3 4 1 2 3 3 1 4 2 4 4 1 2 4 4 4 1 4 1 1 1 4 1 4 3 3 1 4 2 4 1 1 4 3 1 2 1 2 3 4 1 2 4 4 2 1 3 1 1 2 2 3 1 2 1 1 2 1 4 4 1 4 2 4 4 4 3 1 2 1 1 4
output =11

case =6
input =100
4 4 1 1 3 3 2 5 4 5 3 2 2 4 4 1 5 5 4 4 4 3 1 3 5 2 5 1 4 4 1 5 5 4 2 5 5 2 3 2 1 4 4 5 5 3 3 2 3 3 4 2 5 2 1 2 3 1 2 1 4 3 5 2 5 1 3 4 1 3 4 3 2 4 4 1 2 3 1 2 4 4 5 3 5 1 2 2 5 5 5 2 5 2 5 2 5 2 3 4
output =12

case =7
input =500
4 1 1 4 2 3 4 3 1 2 4 4 1 4 1 4 3 1 2 2 3 2 4 2 2 1 4 2 1 2 4 4 1 4 4 3 3 4 2 1 2 2 4 1 3 2 2 2 4 4 2 1 4 3 1 4 1 1 4 2 4 3 1 4 2 4 4 3 2 2 4 3 4 1 1 3 1 4 3 1 2 2 3 1 1 1 1 1 1 1 3 4 2 4 3 4 3 4 2 4 2 1 1 2 3 2 2 1 3 2 4 2 3 2 2 1 1 2 3 2 1 3 3 1 2 1 1 3 4 1 3 4 4 1 3 2 1 1 2 1 4 3 4 3 4 1 4 3 4 1 4 2 2 4 3 2 4 2 3 3 3 1 2 3 2 4 1 1 3 1 4 2 1 4 2 3 2 2 1 4 1 3 4 4 2 2 4 4 1 4 2 2 2 4 3 4 4 2 4 4 1 4 3 3 2 4 4 3 3 4 1 1 1 3 2 1 2 4 2 1 2 1 1 3 1 3 4 3 2 2 2 3 4 2 2 3 1 4 3 2 3 3 4 2 2 2 1 3 3 2 1 4 3 4 4 1 3 2 1 1 3 3 2 3 2 4 4 3 2 4 4 1 1 1 3 4 1 2 1 4 2 3 1 4 2 3 4 3 1 3 4 1 1 2 2 4 1 1 2 3 2 1 4 3 1 1 4 3 1 2 3 1 2 1 4 3 3 4 3 4 3 3 2 4 4 4 2 4 2 1 4 2 4 2 1 3 4 1 4 3 4 2 4 1 1 1 2 4 1 3 3 3 2 3 4 2 4 1 4 3 4 3 3 3 4 1 3 1 4 4 1 1 3 3 2 2 1 1 1 3 4 2 4 2 4 4 2 4 1 2 3 3 1 2 4 4 3 1 2 2 3 1 2 4 1 2 3 3 1 1 1 3 2 3 2 3 3 3 2 3 3 3 2 3 3 4 3 4 2 1 3 3 2 2 3 2 3 2 3 4 4 2 2 4 4 1 4 2 3 1 4 4 3 2 1 1 2 4 4 3 1 2 1 4 4 1 2 3 3 4 1 1 2 1 2 3 4 1 4 2 3 3 2 3 2 2 2 2 1 4 1 3 3 2 3 3 2 2 1 2
output =14

case =8
input =1000
3 3 3 4 1 3 1 3 1 5 4 4 2 3 2 4 1 3 2 4 1 2 3 2 5 2 1 1 4 5 5 3 4 1 5 3 3 1 2 1 5 4 5 4 4 2 1 3 1 4 4 4 5 2 2 3 3 5 2 4 5 5 1 3 1 5 4 4 2 3 3 5 1 3 2 2 1 1 1 3 3 3 4 2 5 3 1 4 3 2 5 4 2 2 3 1 4 5 4 5 5 4 5 4 2 1 4 2 1 1 2 1 4 5 4 1 5 4 4 3 2 4 4 2 1 2 2 4 2 1 4 2 2 2 2 5 5 5 1 1 5 3 3 3 5 1 3 2 2 2 2 2 4 5 1 2 2 1 2 1 3 4 1 4 3 4 3 4 3 3 1 1 4 5 3 3 3 3 1 4 5 3 3 3 4 2 4 2 3 3 1 2 2 1 1 5 3 3 4 1 1 2 5 3 1 4 4 1 1 3 2 1 1 4 5 4 5 5 3 1 3 1 2 5 4 5 5 3 3 5 5 4 2 5 5 4 3 4 2 5 4 2 3 1 2 2 5 5 2 4 1 2 3 5 3 4 3 1 2 4 1 1 1 1 2 2 3 4 5 1 4 3 4 3 5 2 2 3 1 5 2 1 2 4 2 2 2 5 4 4 4 4 4 5 1 1 2 5 4 5 2 1 3 3 2 1 3 4 3 3 3 3 4 3 2 4 1 1 1 5 5 1 1 1 2 5 4 4 1 4 2 2 4 5 5 3 4 2 1 3 3 4 2 1 4 1 3 5 1 3 5 1 3 1 4 4 4 4 1 3 2 5 4 4 3 1 4 1 4 2 3 1 5 5 4 2 4 5 4 1 1 4 5 3 5 2 3 1 2 3 5 1 1 4 4 5 2 1 5 4 4 1 3 1 1 5 2 1 5 2 2 1 3 5 4 4 4 1 5 4 2 3 3 4 2 2 1 1 1 4 2 4 2 5 4 2 2 3 4 1 4 2 3 3 2 2 1 5 1 1 1 1 3 1 1 4 5 5 5 2 2 5 4 2 1 4 3 3 2 1 2 2 4 1 2 1 2 3 4 2 3 3 3 2 2 5 1 3 1 1 4 3 2 2 3 3 1 1 2 1 4 1 3 4 2 4 1 3 3 4 4 1 2 5 5 1 5 2 5 3 3 5 4 2 1 2 5 1 5 1 5 3 2 2 3 5 2 3 1 3 3 4 2 2 3 3 1 1 4 5 3 4 1 4 4 1 2 1 2 1 4 2 4 3 3 3 5 3 1 1 4 4 4 3 3 3 1 2 3 1 1 1 3 4 2 2 3 4 4 1 1 3 5 2 3 3 2 3 2 1 1 5 4 1 3 4 3 2 2 3 5 5 3 3 3 3 1 2 5 3 1 4 5 2 1 5 2 5 1 3 3 2 5 2 1 4 5 1 5 4 4 2 3 5 5 5 2 4 2 5 5 5 3 1 5 3 1 3 1 5 1 1 5 5 1 1 1 2 3 2 3 4 1 2 2 1 4 2 3 1 5 4 1 1 4 1 1 5 5 5 4 3 4 4 2 5 1 1 1 4 4 3 2 5 2 4 5 3 1 5 3 3 5 4 5 3 4 4 3 3 5 3 4 1 5 1 4 1 5 2 1 1 4 1 2 2 1 1 4 3 3 1 3 2 1 5 4 3 2 5 3 4 4 3 1 1 5 1 4 5 4 2 3 1 1 1 4 5 2 4 5 3 3 2 4 3 2 2 2 4 5 5 4 3 4 5 2 3 2 3 4 5 1 3 5 4 5 4 5 1 3 4 3 1 4 1 3 2 1 2 5 3 2 3 2 3 4 5 5 2 2 5 1 1 1 1 1 5 5 3 2 2 1 1 5 3 1 5 3 4 4 3 1 1 3 2 3 5 5 3 1 3 1 5 1 4 2 5 4 2 3 5 1 1 5 2 5 5 4 2 3 5 2 3 4 4 4 4 3 3 1 5 5 4 3 2 1 5 2 4 3 1 4 4 2 1 3 5 1 5 3 1 5 1 2 4 4 1 5 4 3 2 1 3 1 1 1 1 1 3 4 2 4 1 3 2 3 5 5 4 4 4 2 5 1 3 4 1 5 1 4 1 2 2 4 2 2 3 3 4 2 3 2 1 2 1 1 1 3 5 5 2 2 3 3 1 3 1 4 3 2 5 5 5 5 1 3 4 4 3 5 3 3 5 2 1 2 3 4 3 2 4 3 2 3 1 5 3 1 1
output =9

*/
