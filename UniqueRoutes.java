/*
A professional thief entered into a floor in a building,
The floor has M*N inter connected rooms, the thief can enter into any room 
from any other room. And there are few rooms closed from inside, so the thief 
cannot enter into them. Initially the thief is at room[0][0] and has to exit 
from room[m-1][n-1].

You will be given the array room[][], filled with either 0 or 1.
Here, 1-indiactes a closed room, 0-indiactes a open room.
Your task is to find and print the number of unique escape routes 
from room[0][0] and room[m-1][n-1]. And the thief can move only in 
two directions one is forward direction and other is downward direction.


Input Format:
-------------
Line-1: Two space separated integers, M and N.
Next M lines: N space separated integers, either 0 or 1.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
3 4
0 0 0 0
0 1 0 0
0 0 1 0

Sample Output-1:
----------------
2


Sample Input-2:
---------------
4 4
0 0 0 0
0 0 1 0
1 0 0 0
0 0 1 0

Sample Output-2:
----------------
3


==== testcases ====
case =1
input =5 8
0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0
0 1 0 0 0 1 0 0
0 0 1 0 0 0 0 0
0 0 0 0 1 0 0 0
output =34

case =2
input =6 8
0 0 0 0 0 0 0 0
0 1 0 0 0 1 0 0
0 0 0 1 0 0 0 0
0 0 0 0 0 1 0 0
0 1 0 0 0 0 0 0
0 0 0 0 1 0 0 0
output =63

case =3
input =7 10
0 0 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 1
0 1 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 1 0
0 0 0 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
output =889

case =4
input =7 10
0 0 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 1
0 1 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 1 0
0 0 0 1 0 0 0 1 0 0
0 1 0 0 0 0 0 0 0 0
output =286

case =5
input =9 11
0 0 0 0 0 0 0 0 0 1 0
0 0 0 0 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 1 0 0 0 0 1 0 0 0 0
0 0 0 1 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 1 0 0 0 1
0 1 0 0 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0 0 0 0
output =5664

case =6
input =10 10
0 0 0 0 1 0 0 0 0 0
1 0 0 0 0 0 0 1 0 0
0 0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0 1
0 0 0 0 1 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 1
0 1 0 0 1 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0
output =2163

case =7
input =10 15
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 1 0 0 1
0 0 0 0 0 0 1 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 1 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 1 0 0 1 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 1 0 0 0 0
output =42934

case =8
input =10 15
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0 1 0 0 0 0 0
0 0 0 0 0 1 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1 0 0 1 0 0 1
0 0 0 0 0 0 1 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 1 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 0 1 0 0 1 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 1 0 0 0 0
output =11832


*/
import java.util.*;

/*
Algorithm

1) If the first cell i.e. obstacleGrid[0,0] contains 1, this means there 
is an obstacle in the first cell. Hence the thief won't be able to make any move 
and we would return the number of ways as 0.

2) Otherwise, if obstacleGrid[0,0] has a 0 originally we set it to 1 and move ahead.

3) Iterate the first row. If a cell originally contains a 1, this means the 
current cell has an obstacle and shouldn't contribute to any path. 
Hence, set the value of that cell to 0. Otherwise, set it to the value
of previous cell i.e. 

obstacleGrid[i,j] = obstacleGrid[i,j-1]

4) Iterate the first column. If a cell originally contains a 1, 
this means the current cell has an obstacle and shouldn't contribute to any path. 
Hence, set the value of that cell to 0. Otherwise, 
set it to the value of previous cell i.e. 

obstacleGrid[i,j] = obstacleGrid[i-1,j]

5) Now, iterate through the array starting from cell obstacleGrid[1,1].
If a cell originally doesn't contain any obstacle then the number of ways of 
reaching that cell would be the sum of number of ways of reaching the cell 
above it and number of ways of reaching the cell to the left of it.

obstacleGrid[i,j] = obstacleGrid[i-1,j] + obstacleGrid[i,j-1]

6) If a cell contains an obstacle set it to 0 and continue. 
This is done to make sure it doesn't contribute to any other path.

Time Complexity: O(M*N)
The rectangular grid given to us is of size M*N and we process each cell just once.
Space Complexity: O(1). We are utilizing the obstacleGrid as the DP array. Hence, no extra space.
*/

/*
4 4
0 0 0 0
0 0 1 0
1 0 0 0
0 0 1 0

*/

class UniqueRoutes 
{
    public int UniqueEscapeRoutes(int[][] obstacleGrid) 
	{
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) 
		{
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) 
		{
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) 
		{
            for (int j = 1; j < C; j++) 
			{
                if (obstacleGrid[i][j] == 0) 
				{
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } 
				else 
				{
                    obstacleGrid[i][j] = 0;
                }
            }
        }

		System.out.println(Arrays.deepToString(obstacleGrid));
        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }

	public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
		int[][] grid=new int[m][n];
		for(int i=0;i<m;i++)
		for(int j=0;j<n;j++)
			grid[i][j]=sc.nextInt();
		System.out.println(new UniqueRoutes().UniqueEscapeRoutes(grid));
    }

}