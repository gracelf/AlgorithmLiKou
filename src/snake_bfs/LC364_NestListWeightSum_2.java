package snake_bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * example [1, [4, [6] ] ] 
 * preSum represent the sum of all previous integer 
 * when going through each level in the queue, this amount will be added to the sum accumulative. 
 * this way, the sum in the previous level will be counted again in the current level, until the end of the level, equivalent as to multiply the level sum to the reversed depth
 * (as in maxDepth - (the depth of the integer) + 1)
 * sum is the result
 * 
 * demo:
 * preSum  |  sum +=preSum 
 * 1                 1 
 * 5                 6 
 * 11              17
 */
public class LC364_NestListWeightSum_2 {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sum = 0;
        int preSum = 0;

        Queue<NestedInteger> queue = new LinkedList<>();
        // queue initialization 
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                NestedInteger next = queue.poll();
                if (next.isInteger()) {
                    preSum += next.getInteger();
                    queue.remove(next);
                } else {
                    for (NestedInteger ni : next.getList()) {
                        queue.offer(ni);
                    }
                }
            }
            sum += preSum;
        }
        return sum;
    }

    public interface NestedInteger {
        // Constructor initializes an empty nested list.
        //public NestedInteger();

        // Constructor initializes a single integer.
        //public NestedInteger(int value);
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
