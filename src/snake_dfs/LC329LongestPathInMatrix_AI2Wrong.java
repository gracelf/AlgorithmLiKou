

package snake_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class LC329LongestPathInMatrix_AI2Wrong {

    private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static List<List<Integer>> longestPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int maxLen = 0;
        List<List<Integer>> longestPaths = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                List<List<Integer>> paths = dfs(matrix, i, j, dp);
                int pathLen = paths.get(0).size();

                if (pathLen > maxLen) {
                    maxLen = pathLen;
                    longestPaths = paths;
                } else if (pathLen == maxLen) {
                    longestPaths.addAll(paths);
                }
            }
        }

        return longestPaths;
    }

    private static List<List<Integer>> dfs(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            result.get(0).add(matrix[i][j]);
            return result;
        }

        List<List<Integer>> result = new ArrayList<>();
        int maxPathLen = 1;

        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
                List<List<Integer>> paths = dfs(matrix, x, y, dp);
                for (List<Integer> path : paths) {
                    if (path.size() + 1 > maxPathLen) {
                        maxPathLen = path.size() + 1;
                        result.clear();
                    }

                    if (path.size() + 1 == maxPathLen) {
                        List<Integer> newPath = new ArrayList<>();
                        newPath.add(matrix[i][j]);
                        newPath.addAll(path);
                        result.add(newPath);
                    }
                }
            }
        }

        dp[i][j] = maxPathLen;
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> longestPaths = longestPath(matrix);
        System.out.println(longestPaths);
    }
}