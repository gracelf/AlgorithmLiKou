package snake_bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 */
public class PourWaterProblem {

    class State {

        int water1;
        int water2;

        State(int water1, int water2) {
            this.water1 = water1;
            this.water2 = water2;
        }

        @Override
        public String toString() {
            return water1 + "," + water2;
        }
    }

    public int pourWater(int capcity1, int capacity2, int target) {
        if (capcity1 < target && capacity2 < target) {
            throw new IllegalArgumentException("Impossible!");
        }
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        State init = new State(0, 0);
        queue.offer(init);
        visited.add(init.toString());
        int numSteps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                State cur = queue.poll();
                if (cur.water1 == target || cur.water2 == target) {
                    return numSteps;
                }
                // generate unvisited next states and enqueue
                List<State> nexts = generateNextStates(cur, visited, capcity1, capacity2);
                queue.addAll(nexts);
            }
            numSteps++;
        }
        // Theoretically cannot reach here for valid input
        throw new RuntimeException("Impossible to change to target state.");
    }

    private List<State> generateNextStates(State cur, Set<String> visited, int c1, int c2) {
        List<State> res = new LinkedList<>();
        int cur1 = cur.water1;
        int cur2 = cur.water2;
        // fill A
        State fillA = new State(c1, cur2);
        if (visited.add(fillA.toString())) {
            res.add(fillA);
        }
        // fill B
        State fillB = new State(cur1, c2);
        if (visited.add(fillB.toString())) {
            res.add(fillB);
        }
        // empty A
        State emptyA = new State(0, cur2);
        if (visited.add(emptyA.toString())) {
            res.add(emptyA);
        }
        // empty B
        State emptyB = new State(cur1, 0);
        if (visited.add(emptyB.toString())) {
            res.add(emptyB);
        }
        int next1 = 0, next2 = 0;
        // pour A -> B
        if (cur2 == c2) { // B is full
            next1 = cur1;
            next2 = cur2;
        } else if (cur1 < c2 - cur2) { // A cant fill B
            next1 = 0;
            next2 = cur1 + cur2;
        } else { // fill B with A
            next1 = cur1 - (c2 - cur2);
            next2 = c2;
        }
        State pourA = new State(next1, next2);
        if (visited.add(pourA.toString())) {
            res.add(pourA);
        }
        // pour B -> A
        if (cur1 == c1) { // A is full
            next1 = cur1;
            next2 = cur2;
        } else if (cur2 < c1 - cur1) { // B cant fill A
            next1 = cur1 + cur2;
            next2 = 0;
        } else { // fill A with B
            next1 = c1;
            next2 = cur2 - (c1 - cur1);
        }
        State pourB = new State(next1, next2);
        if (visited.add(pourB.toString())) {
            res.add(pourB);
        }
        return res;
    }

    public static void main(String[] args) {
        PourWaterProblem pourWaterProblem = new PourWaterProblem();
        int c1 = 5;
        int c2 = 3;
        int target = 4;
        int output = pourWaterProblem.pourWater(c1, c2, target);
        System.out.println("--- Test ---");
        System.out.println("Testing: 5, 3 -> 4");
        System.out.println("Output: " + output);
        System.out.println("Expected: " + 6);
        System.out.println();
        System.out.println("One Solution: ");
        System.out.println("fill5 -> pour5to3 -> empty3 -> pour5to3 -> fill5 -> pour5to3");
        System.out.println(" 5,0  ->   2, 3   ->  2, 0  ->   0, 2   ->  5,2  ->   4, 3");
    }

}
