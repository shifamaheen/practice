/*
Mr Subodh is given an array arr[] of M integers between 0 to 10000.
And he is also given another integer P.
Subodh has to update each integer in the arr[]:
Subodh is allowed to update the arr[i], as follows:
	- arr[i] = arr[i] + P (or) arr[i] = arr[i] - P.

Subodh has to find the updated score as the difference between 
maximum element in the arr[] to the minimum element arr[] after all the
elements in the arr[] updated, and return the minimum update score.

Input Format:
-------------
Line-1: Two space separated integers M and P.
Line-2: M space separated integers, integer > 0.

Output Format:
--------------
Print an integer result.

Sample Input-1:
---------------
3 2
2 6 3

Sample Output-1:
----------------
1

Explanation: 
------------
Update the array as [4, 4, 5]. The score is maximum(arr) - minimum(arr)
=> 5 - 4 = 1.


Sample Input-2:
---------------
4 3
1 11 8 4

Sample Output-2:
----------------
4

Explanation: 
------------
Update the array as [4, 8, 5, 7]. The score is maximum(arr) - minimum(arr) 
=> 8 - 4 = 4

*/
import java.util.*;

class SmallestRange 
{
    public int smallestRangeII(int[] nums, int diff) 
	{
        int n = nums.length;
        Arrays.sort(nums);
        int result = nums[n-1] - nums[0];
		System.out.println("initial result " + result);
		int min = nums[0] +diff;
		int max = nums[n-1] - diff;

        for (int i = 0; i < nums.length - 1; ++i) 
		{
			int low = Math.min(min, nums[i + 1] - diff);
            int high = Math.max(max, nums[i] + diff);

			System.out.println("high " + high + " low " + low);
            result = Math.min(result, high - low);
			System.out.println("result " + result);
        }
        return result;
    }
	
	public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        int k=scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
			nums[i]=scan.nextInt();
		System.out.println(new SmallestRange().smallestRangeII(nums,k));
	}
}

/*



==== testcases ====
case =1
input =3 2
2 6 3
output =1

case =2
input =4 3
1 11 8 4
output =4

case =3
input =5 3
1 2 4 5 10
output =4

case =4
input =7 4
10 3 2 7 20 5 10
output =10

case =5
input =4 6
17 10 5 11
output =6

case =6
input =11 2
17 10 5 11 12 13 20 25 5 6 7
output =16

case =7
input =11 5
17 10 5 11 12 13 20 25 5 6 7
output =10

case =8
input =11 10
1 7 2 8 4 9 17 3 45 56 89
output =68


*/