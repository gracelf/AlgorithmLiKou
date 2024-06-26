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
    
    public static void main(String[] args){
        int[][] grid = new int[][] {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
        LC694DistinctIsland sol = new LC694DistinctIsland();
        int res  = sol.numDistinctIslands(grid);
        System.out.println("res: " + res);
    }

}
