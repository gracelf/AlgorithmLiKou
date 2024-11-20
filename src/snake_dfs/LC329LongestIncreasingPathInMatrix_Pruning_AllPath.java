package snake_dfs;

import java.util.Arrays;

/**
 *
 */
public class LC329LongestIncreasingPathInMatrix_Pruning_AllPath {

    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        //max val from [i][j] point on
        int[][] mem = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, mem));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] mem) {
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int max = 0;
        for (int[] dir : DIRS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            if (ii < matrix.length && ii >= 0 && jj < matrix[0].length && jj >= 0 && matrix[ii][jj] > matrix[i][j]) {
                max = Math.max(max, dfs(matrix, ii, jj, mem));
            }
        }
        // mim value is 1
        mem[i][j] = max + 1;
        return max + 1;
    }

    public static void main(String[] args) {
        LC329LongestIncreasingPathInMatrix_Pruning_AllPath sol = new LC329LongestIncreasingPathInMatrix_Pruning_AllPath();
        int[][] matrix1 = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println("result: " + sol.longestIncreasingPath(matrix1));
    }
}
