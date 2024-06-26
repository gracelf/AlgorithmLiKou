

package snake_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class LC417 {
    private static final int[][] directions = new int[][]{{0,-1}, {0, 1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            Queue<Integer> queue = new LinkedList<>();
            boolean[][] pacific = new boolean[row][col];
            boolean[][] atlantic = new boolean[row][col];
            List<List<Integer>> res = new ArrayList<>();
            //load pacific
            loadPO(queue, heights, pacific);
            //bfs pacific
            bfs(res, queue, heights, pacific, atlantic);
            //load atlantic, fill boolean 
            loadAO(queue, heights, atlantic);
            //bfs 
            bfs(res, queue, heights, atlantic, pacific);
            //done in previous code=>find union of pacific and atlantic to find res
            return res;
    }

    private void bfs(List<List<Integer>> res, Queue<Integer> queue, int[][] heights, boolean[][] self, boolean[][] other){
        int row = heights.length;
        int col = heights[0].length;
        while(!queue.isEmpty()){
            //int size = queue.size();
            //while (size -- >0){//divider in queue, can be skipped in this case
                int cur = queue.poll();
                int i = cur/col;
                int j = cur%col;
                //check ""other"" boolean broad here, so items first in the queue (edges) won't be missed in res
                if (other[i][j]){
                    res.add(Arrays.asList(i,j));
                }
                for (int[] dir: directions){
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if(ii>=0 && ii<row && jj>=0 && jj<col && heights[ii][jj]>= heights[i][j]
                    && !self[ii][jj]){
                        self[ii][jj] = true;
                        queue.offer(ii*col+jj);
                        // if(other[ii][jj]){
                        //     res.add(Arrays.asList(ii, jj));
                        // }
                    }
                }
            //}
        }
    }

    private void loadPO(Queue<Integer> queue, int[][] heights, boolean[][] pacific) {
        int row = heights.length;
        int col = heights[0].length;
        //add first row
        for (int j=0; j<col; j++){
            queue.offer(j);
            pacific[0][j] = true;
        }
        //add first col
        for (int i=1; i<row; i++){
            queue.offer(i*col);
            pacific[i][0] = true;
        }
    }

    private void loadAO(Queue<Integer> queue, int[][] heights, boolean[][] atlantic) {
        int row = heights.length;
        int col = heights[0].length;
        //add last col
        for (int i=0; i<row; i++){
            queue.offer(i*col+col-1);
            atlantic[i][col-1] = true;
        }
        //add last row
        for (int j=0; j<col-1; j++){
            queue.offer((row-1)*col +j);
            atlantic[row-1][j] = true;
        }
    }
}
