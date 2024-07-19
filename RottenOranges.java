import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>(); //Q stores co-ordinates(i,j) values of rotten grid
        int fresh = 0;
        int m = grid.length; //row
        int n = grid[0].length; //column

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) fresh++; //increase count of fresh oranges
                else if(grid[i][j] == 2) q.add(new int[]{i,j}); //add rotten oranges to q
            }
        }

        if(fresh == 0) return 0; //in initial traversal, if no fresh oranges are there, then return 0 time

        int[][] directions = new int[][]{{0,1}, {1, 0}, {-1, 0}, {0,-1}}; //all four edges(children) of each grid
        int time = 0;

        while(!q.isEmpty()) {
            int size = q.size();

            for(int i=0; i<size; i++) {
                int[] currentGrid = q.poll(); //get the current grid out
                for(int[] dir : directions) { //check all directions from current grid
                    assert currentGrid != null;
                    int ngr = currentGrid[0] + dir[0]; //for each direction, add row
                    int ngc = currentGrid[1] + dir[1]; //and column, to find new grid

                    if(ngr >= 0 && ngc >= 0 && ngr < m && ngc < n && //boundary check for all grids
                            grid[ngr][ngc] == 1) { //if the grid is fresh
                        grid[ngr][ngc] = 2; //make it rotten
                        fresh--; //decrement the count of total fresh oranges
                        q.add(new int[]{ngr, ngc}); //add this newly rotten grid into q
                    }
                }
            }
            time++; //increment time only at each level of BFS, as all children of same level turn rotten at once
        }
        if(fresh == 0) return time-1; //return time only if all fresh oranges are turned rotten
        //as we also increment the time even after last step, we decrease the time by 1 to offset.
            // Alternatively, we can also start with time = -1.
        else return -1; //if there is at least one orange left, return -1 as we cannot turn all oranges to rotten
    }

    public static void main(String[] args) {
        int[][] grid1 = new int[][]{{1,1,0}, {0,1,1}, {1,2,1}};
        System.out.println("Time taken for all oranges in " + Arrays.deepToString(grid1) +
                " to rot is: " + orangesRotting(grid1));

        int[][] grid2 = new int[][]{{1,0}, {1,1}, {2,0}, {0,2}, {1,0}, {0,1}};
        System.out.println("Time taken for all oranges in " + Arrays.deepToString(grid2) +
                " to rot is: " + orangesRotting(grid2));
    }
}