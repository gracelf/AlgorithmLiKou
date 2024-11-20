package snake_dfs;

/**
 *
 */
public class LC200NumberOfIsland {

    public int numOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] visited = new int[row][col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == 0 && grid[i][j] == 1) {
                    res += dfsWrong(grid, visited, row, col, i, j);
                    System.out.println("i: " + i + ", j: " + j + ", result: " + res);
                }

            }
        }
        return res;
    }

    private int dfs(int[][] grid, int[][] visited, int row, int col, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0 || visited[i][j] == 1) {
            return 0;
        }
        visited[i][j] = 1;
        // 4 direction
        dfs(grid, visited, row, col, i + 1, j);
        dfs(grid, visited, row, col, i - 1, j);
        dfs(grid, visited, row, col, i, j + 1);
        dfs(grid, visited, row, col, i, j - 1);
        return 1;
    }

    private int dfsWrong(int[][] grid, int[][] visited, int row, int col, int i, int j) {
        if (i >= 0 && i < row && j >= 0 && j < col) {
            if (visited[i][j] == 0 && grid[i][j] == 1) {
                visited[i][j] = 1;
                // 4 directions, mark all the connect point to visited
                dfs(grid, visited, row, col, i + 1, j);
                dfs(grid, visited, row, col, i - 1, j);
                dfs(grid, visited, row, col, i, j + 1);
                dfs(grid, visited, row, col, i, j - 1);
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LC200NumberOfIsland sol = new LC200NumberOfIsland();
        //test 1
        int[][] grid = {{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int result = sol.numOfIsland(grid);
        System.out.println("result: " + result);
        //test 2, [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
        int[][] grid2 = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};
        int result2 = sol.numOfIsland(grid2);
        System.out.println("result2: " + result2);
    }

}
