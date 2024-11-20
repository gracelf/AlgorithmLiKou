package snake_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class LC329LongestIncreasingPathInMatrix_AllPath_lecture {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<List<Integer>> findLongestPaths(int[][] matrix) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = 0;
        List<Integer> startList = new ArrayList<>();
        //max val from [i][j] point on
        int[][] mem = new int[rows][cols];
        //step 1:build graph
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int l = dfs(matrix, i, j, cols, mem, graph);
                if (l > max) {
                    max = l;
                    startList.clear();
                    startList.add(i * cols + j);
                } else if (l == max) {
                    startList.add(i * cols + j);
                }
            }
        }
        System.out.println("start list: " + startList);// 7 is right
        System.out.println("graph" + graph);//so far looks ok
        //step 2: populate result 
        List<List<Integer>> res = new ArrayList<>();
        for (Integer startPoint : startList) {
            List<Integer> path = new ArrayList<>();
            path.add(matrix[startPoint / cols][startPoint % cols]);
            dfs(matrix, res, startPoint, path, graph);
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j, int cols, int[][] mem, HashMap<Integer, List<Integer>> graph) {
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int max = 0;
        List<Integer> edges = new ArrayList<>();
        for (int[] dir : DIRECTIONS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            if (ii < matrix.length && ii >= 0 && jj < matrix[0].length && jj >= 0 && matrix[ii][jj] > matrix[i][j]) {
                int l = dfs(matrix, ii, jj, cols, mem, graph);
                if (l > max) {
                    max = l;
                    edges.clear();
                    edges.add(ii * cols + jj);
                } else if (l == max) {
                    edges.add(ii * cols + jj);
                }
            }
        }
        // mim value is 1
        mem[i][j] = max + 1;
        graph.put(i * cols + j, edges);
        return max + 1;
    }

    private void dfs(int[][] matrix, List<List<Integer>> res, Integer startPoint, List<Integer> path, HashMap<Integer, List<Integer>> graph) {
        List<Integer> nexts = graph.get(startPoint);
        if (nexts.isEmpty()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int next : nexts) {
            int len = path.size();
            int col = matrix[0].length;
            int val = matrix[next / col][next % col];
            path.add(val);
            dfs(matrix, res, next, path, graph);
            path = path.subList(0, len);
        }
    }

    public static void main(String[] args) {
        LC329LongestIncreasingPathInMatrix_AllPath_lecture sol = new LC329LongestIncreasingPathInMatrix_AllPath_lecture();
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        //expected result [1, 2, 6, 9]
        List<List<Integer>> longestPaths = sol.findLongestPaths(matrix);
        PrintMethod.printListOfList(longestPaths);

        //test case 2
        int[][] matrix2 = {{9, 9, 4}, {6, 9, 8}, {2, 1, 1}};
        //expected result [1, 2, 6, 9]
        List<List<Integer>> longestPaths2 = sol.findLongestPaths(matrix2);
        PrintMethod.printListOfList(longestPaths2);

        //test case 3
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> longestPaths3 = sol.findLongestPaths(matrix3);
        PrintMethod.printListOfList(longestPaths3);
    }

}
