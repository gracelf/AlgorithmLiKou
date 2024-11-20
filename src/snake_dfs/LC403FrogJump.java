package snake_dfs;

import java.util.HashMap;

/**
 *
 */
public class LC403FrogJump {

    public boolean canCross(int[] stones) {
        HashMap<Integer, Integer> stonesMap = new HashMap<>();//stone position/val,index pair
        for (int i = 0; i < stones.length; i++) {
            stonesMap.put(stones[i], i);
        }
        return dfs(stones, 0, 0, stonesMap);
    }

    private boolean dfs(int[] stones, int idx, int jumpStep, HashMap<Integer, Integer> stonesMap) {
        if (idx == stones.length - 1) {
            return true;
        }
        int min = Math.max(jumpStep - 1, 0);
        for (int i = min; i < jumpStep + 2; i++) {
            if (!stonesMap.containsKey(stones[idx] + i)) {
                continue;
            }
            int nextIdx = stonesMap.get(stones[idx] + i);
            if (nextIdx > idx && dfs(stones, nextIdx, i, stonesMap)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC403FrogJump sol = new LC403FrogJump();
        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println("test 1 res: " + sol.canCross(stones));
        int[] stones2 = new int[]{0, 1, 2, 3, 4, 8, 9, 11};
        System.out.println("test 2 res: " + sol.canCross(stones2));

    }

}
