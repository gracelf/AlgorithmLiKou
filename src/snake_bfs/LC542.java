

package snake_bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class LC542 {
    private static final int[][] directions = new int[][] {{0,1}, {1,0}, {-1,0}, {0,-1}};

    public int[][] updateMatrix(int[][] mat) {     
        //find all 0, add to queue
        Queue<Integer> queue = loadZeros(mat);
        //bfs
        return bfs(mat, queue);
        
    }
    private int[][] bfs(int[][] mat, Queue<Integer> queue){
        //c.c
        if(mat==null || mat.length == 0 || mat[0] ==null || mat[0].length ==0){
            throw new IllegalArgumentException("matrix is not valid");
        }

        int row = mat.length; 
        int col = mat[0].length;
        int[][] res = new int[row][col];
        int minLength = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while(size-->0){
                int curr = queue.poll();
                int i = curr/col; 
                int j = curr%col;
                for (int[] dir: directions){
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii>=0 && ii <row && jj >=0 && jj<col && 
                    mat[ii][jj]==1 && res[ii][jj]==0){
                        queue.offer(ii*col + jj);
                        res[ii][jj] = minLength;
                    }

                }
            }
            minLength++;
        }
        return res;
        
    }

    private Queue<Integer> loadZeros(int[][] mat){
        int row = mat.length; 
        int col = mat[0].length;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i<row; i++){
            for (int j = 0; j< col; j++){
                if (mat[i][j]==0) queue.offer(i*col + j);
            }
        }
        return queue;
    }
}
