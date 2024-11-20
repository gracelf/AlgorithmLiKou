package snake_bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class LC317v2ShortestDistanceFromAllBuildings {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int shortestDistance(int[][] grid) {
        //corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        //get row and col count
        int row = grid.length;
        int col = grid[0].length;
        //load all buildings
        Queue<Integer> buildings = loadOnes(grid);
        //initial value is 0
        int[][] res = new int[row][col];

        //bfs for each building
        while (!buildings.isEmpty()) {
            int building = buildings.poll();
            System.out.println("building: " + building);
            bfs(building, grid, res, row, col);
            PrintMethod.print2DArray(res); // print after calculation for each building
            resetGrid(res, grid);
        }

        PrintMethod.print2DArray(res);
        int shortestDistance = getShortestDistance(res, grid);
        return shortestDistance;

    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        LC317v2ShortestDistanceFromAllBuildings sol = new LC317v2ShortestDistanceFromAllBuildings();
        System.out.println("result is " + sol.shortestDistance(grid));
    }

    private Queue<Integer> loadOnes(int[][] grid) {
        Queue<Integer> ones = new LinkedList<>();
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    ones.add(i * col + j);
                }
            }
        }
        return ones;
    }

    private void bfs(int building, int[][] grid, int[][] res, int row, int col) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(building);
        Boolean[][] visited = new Boolean[row][col];
        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int point = queue.poll();
                int i = point / col;
                int j = point % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < row && jj >= 0 && jj < col && grid[ii][jj] == 0 && visited[ii][jj] != Boolean.TRUE) {
                        res[ii][jj] += minLen;
                        visited[ii][jj] = true;
                        queue.offer(ii * col + jj);
                    }
                }
            }
            minLen++;
        }
    }

    private int getShortestDistance(int[][] res, int[][] grid) {
        int shortestDistance = -1;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                int val = res[i][j];
                int mat = grid[i][j];
                if (val != 0 && mat == 0) {
                    shortestDistance = (shortestDistance == -1) ? val : Math.min(val, shortestDistance);
                }
            }
        }
        return shortestDistance;
    }

    private void resetGrid(int[][] res, int[][] grid) {
        // if result[i][j] == 0, set grid[i][j] == 2
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                if (res[i][j] == 0) {
                    grid[i][j] = 2;
                }
            }
        }
    }
}
