package snake_dfs;

import java.util.HashSet;

/**
 *
 */
public class LC694DistinctIsland {

    public int numDistinctIslands(int[][] grid) {
        // TODO: corner case
        int row = grid.length, col = grid[0].length;
        HashSet<String> res = new HashSet<>();
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    // new path for each starting point
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, visited, "s");
                    res.add(path.toString());
                    System.out.println(path.toString());
                }
            }
        }
        return res.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder path, boolean[][] visited, String dir) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        path.append(dir);
        dfs(grid, i, j - 1, path, visited, "l");
        dfs(grid, i, j + 1, path, visited, "r");
        dfs(grid, i - 1, j, path, visited, "u");
        dfs(grid, i + 1, j, path, visited, "d");
        path.append("b"); // to deal the case with "T" shape island
    }

    public static void main(String[] args) {
        int[][] grid0 = new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
        LC694DistinctIsland sol = new LC694DistinctIsland();
        int res = sol.numDistinctIslands(grid0);
        System.out.println("result for test case 0: " + res);

        //test 1
        int[][] grid = {{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int result = sol.numDistinctIslands(grid);
        System.out.println("result for test case 1: " + result);
        //test 2, [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
        int[][] grid2 = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};
        int result2 = sol.numDistinctIslands(grid2);
        System.out.println("result for test case 2: " + result2);
        //test 3
        int[][] grid3 = {{0, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {1, 1, 1, 0, 0}, {0, 1, 0, 0, 0}};
        int result3 = sol.numDistinctIslands(grid3);
        System.out.println("result for test case 3: " + result3);
    }

}
