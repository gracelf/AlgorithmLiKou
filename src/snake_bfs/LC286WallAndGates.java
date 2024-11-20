package snake_bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class LC286WallAndGates {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void wallsAndGates(int[][] rooms) {
        //corner case
        int row = rooms.length;
        int col = rooms[0].length;
        int minLen = 1;
        Queue<Integer> gates = loadGate(rooms);

        //process gates until no more gate in the queue
        while (!gates.isEmpty()) {
            int queueSize = gates.size();
            //process each level
            while (queueSize-- > 0) {
                int gate = gates.poll();
                int i = gate / col;
                int j = gate % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < row && jj >= 0 && jj < col && rooms[ii][jj] == Integer.MAX_VALUE && rooms[i][j] != -1) {
                        gates.offer(ii * col + jj);
                        rooms[ii][jj] = minLen;
                    }
                }
            }
            minLen++;
        }
    }

    private Queue<Integer> loadGate(int[][] rooms) {
        Queue<Integer> gates = new LinkedList<>();
        int rows = rooms.length;
        int col = rooms[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    gates.offer(i * col + j);
                }
            }
        }
        return gates;
    }

    public static void main(String[] args) {
        int[][] rooms = {{2147483647, -1, 0, 2147483647},
        {2147483647, 2147483647, 2147483647, -1},
        {2147483647, -1, 2147483647, -1},
        {0, -1, 2147483647, 2147483647}};
        print2DArray(rooms);//before the update
        LC286WallAndGates solution = new LC286WallAndGates();
        solution.wallsAndGates(rooms);
        System.out.println("after the update: ");
        print2DArray(rooms);//after the update

    }

    public static void print2DArray(int[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(array[i][j] + ",");
            }
            System.out.println();
        }
    }

}
