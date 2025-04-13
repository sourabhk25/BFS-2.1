// Time Complexity : O(n * m)
// Space Complexity : O(n * m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach - - Count fresh oranges and enqueue all initially rotten oranges with their position and time.
//- Perform BFS and spread rot to adjacent fresh oranges, track time.
//- If after BFS not all fresh oranges are rotten, return -1; else return time taken.


import java.util.LinkedList;
import java.util.Queue;

class Tuple {
    int row;
    int col;
    int time;
    Tuple(int r, int c, int t) {
        this.row = r;
        this.col = c;
        this.time = t;
    }
}

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;    //no. of rows
        int m = grid[0].length; //no. of cols
        Queue<Tuple> queue = new LinkedList<>(); //queue to store tuples
        int[][] vis = new int[n][m];    //array to store which is visted or not
        int cntFresh = 0;   //count of fresh oranges
        //loop through grid add intial rotten oranges to queue
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 2) {
                    queue.add(new Tuple(i, j, 0));  //add to queue
                    vis[i][j] = 2;  //mark status of rotten orange
                } else {
                    vis[i][j] = 0;  //mark vis as empty cell
                }

                if(grid[i][j] == 1) {   //if fresh orange found increment count
                    cntFresh++;
                }
            }
        }

        int tm = 0; //variable to store output
        //creating deltas for row and column
        int drow[] = {0, 0, -1, 1};    //up, down, left, right
        int dcol[] = {-1, 1, 0, 0};    //up, down, left, right
        int cnt = 0;    //variable to count how many oranges are rotten during BFS

        //starting BFS
        while(!queue.isEmpty()) {
            //get all required parts from peek element
            int r = queue.peek().row;
            int c = queue.peek().col;
            int t = queue.peek().time;
            queue.remove(); //then remove it after use
            tm = Math.max(tm, t);   //update tm as max time
            //loop 4 times through delta arrays to get all 4 neighbours for that element
            for(int i=0; i<4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 && grid[nrow][ncol]==1) {
                    queue.add(new Tuple(nrow, ncol, t + 1));
                    vis[nrow][ncol] = 2;
                    cnt++;
                }
            }
        }

        if(cnt != cntFresh) {   //if any remaining fresh oranges present then return -1
            return -1;
        }

        return tm;
    }

    public static void main(String[] args) {
        RottingOranges solution = new RottingOranges();

        int[][] grid1 = {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };

        System.out.println("Test case 1 (Expected 4): " + solution.orangesRotting(grid1));
    }
}
