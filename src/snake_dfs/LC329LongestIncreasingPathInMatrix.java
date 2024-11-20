package snake_dfs;

/**
 *
 */
public class LC329LongestIncreasingPathInMatrix {

    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j) {
//        //this can skip because of the condition next in the 4 direction
//        if (i > matrix.length - 1 || i < 0 || j > matrix[0].length - 1 || j < 0) {
//            return 0;
//        }
        int max = 0;
        for (int[] dir : DIRS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            if (ii < matrix.length && ii >= 0 && jj < matrix[0].length && jj >= 0 && matrix[ii][jj] > matrix[i][j]) {
                max = Math.max(max, dfs(matrix, ii, jj));
            }
        }
        return max + 1;
    }

    public static void main(String[] args) {
        LC329LongestIncreasingPathInMatrix sol = new LC329LongestIncreasingPathInMatrix();
        int[][] matrix1 = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println("result: " + sol.longestIncreasingPath(matrix1));
    }

}
