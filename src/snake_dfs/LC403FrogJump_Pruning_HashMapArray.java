package snake_dfs;

import java.util.HashMap;

/**
 *
 */
public class LC403FrogJump_Pruning_HashMapArray {

    public boolean canCross(int[] stones) {
        HashMap<Integer, Integer> stonesMap = new HashMap<>();//stone position/val,index pair
        //pruning memorization
        HashMap<Integer, Boolean>[] mem = new HashMap[stones.length]; //at each [stone position], k and boolean pair
        for (int i = 0; i < stones.length; i++) {
            stonesMap.put(stones[i], i);
            mem[i] = new HashMap<>();
        }
        return dfs(stones, 0, 0, stonesMap, mem);
    }

    private boolean dfs(int[] stones, int idx, int jumpStep, HashMap<Integer, Integer> stonesMap, HashMap<Integer, Boolean>[] mem) {
        HashMap<Integer, Boolean> currMem = mem[idx];
        if (currMem.containsKey(jumpStep)) {
            return currMem.get(jumpStep);
        }

        if (idx == stones.length - 1) {
            currMem.put(jumpStep, Boolean.TRUE);
            return true;
        }
        int min = Math.max(jumpStep - 1, 0);
        for (int i = min; i < jumpStep + 2; i++) {
            if (!stonesMap.containsKey(stones[idx] + i)) {
                continue;
            }
            int nextIdx = stonesMap.get(stones[idx] + i);
            if (nextIdx > idx && dfs(stones, nextIdx, i, stonesMap, mem)) {
                currMem.put(jumpStep, Boolean.TRUE);
                return true;
            }
        }
        currMem.put(jumpStep, Boolean.FALSE);
        return false;
    }

    public static void main(String[] args) {
        LC403FrogJump_Pruning_HashMapArray sol = new LC403FrogJump_Pruning_HashMapArray();
        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println("test 1 res: " + sol.canCross(stones));
        int[] stones2 = new int[]{0, 1, 2, 3, 4, 8, 9, 11};
        System.out.println("test 2 res: " + sol.canCross(stones2));
    }
}
