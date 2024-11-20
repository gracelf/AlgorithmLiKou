package snake_bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class LC339_v2p_NestListWeightSum_1 {

    /**
     * // This is the interface that allows for creating nested lists. // You
     * should not implement it, or speculate about its implementation public
     * interface NestedInteger { // Constructor initializes an empty nested
     * list. public NestedInteger();
     *
     *     // Constructor initializes a single integer. public NestedInteger(int
     * value);
     *
     *     // @return true if this NestedInteger holds a single integer, rather
     * than a nested list. public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds
     * a single integer // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     *
     *     // Set this NestedInteger to hold a single integer. public void
     * setInteger(int value);
     *
     *     // Set this NestedInteger to hold a nested list and adds a nested
     * integer to it. public void add(NestedInteger ni);
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a
     * nested list // Return empty list if this NestedInteger holds a single
     * integer public List<NestedInteger> getList(); }
     */
    public int depthSum(List<NestedInteger> nestedList) {
        //c.c
        int sum = 0;
        int level = 1;

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
                    sum += level * next.getInteger();
                    queue.remove(next);
                } else {
                    for (NestedInteger ni : next.getList()) {
                        queue.offer(ni);
                    }
                }
            }
            level++;
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
