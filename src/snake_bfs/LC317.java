

package snake_bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class LC317 {
    private static final int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int shortestDistance(int[][] grid) {
        //c.c
        int row = grid.length;
        int col = grid[0].length;
        //pass into bfs method
        int[][] res = new int[row][col];
        //for each "1", do bfs
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if(grid[i][j]==1){
                    bfs(grid, res, i, j);
                }
            }
        }

        //post processing result
        int min = Integer.MAX_VALUE;
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (grid[i][j]==0){
                    //System.out.println("i:" +i + ", j:" + j);
                    min = Math.min(min,res[i][j]);

                }
            }
        }
        return min == Integer.MAX_VALUE? -1: min;
        
    }

    private void bfs(int[][] grid, int[][] res, int m, int n){
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col]; 
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(m*col+n);
        int minLen = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while(size -- >0){
                int curr = queue.poll();
                int i = curr/col;
                int j = curr%col;
                for (int[] dir: directions){
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if(ii>=0 && ii<row && jj>=0 & jj<col && grid[ii][jj]==0 && !visited[ii][jj]){
                        res[ii][jj] += minLen;
                        visited[ii][jj] = true;
                        queue.offer(ii*col+jj);
                    }
                }
            }
            minLen += 1;
        }

        //!!!!if not visited, set to "2"
        refreshVisitedBorad(visited, grid);
    }

    private void refreshVisitedBorad(boolean[][] visited, int[][] grid){
        int row = visited.length;
        int col = visited[0].length;
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if(!visited[i][j] && grid[i][j] == 0){
                    grid[i][j] = 2;
                }
            }
        }
    }
    
    public static void main(String[] args){
        int[][] grid = new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        LC317 sol = new LC317();
        int res = sol.shortestDistance(grid);
        System.out.println("res:" + res);
    }
}
