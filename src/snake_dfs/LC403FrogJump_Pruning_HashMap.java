package snake_dfs;

import java.util.HashMap;

/**
 *
 */
public class LC403FrogJump_Pruning_HashMap {

    public boolean canCross(int[] stones) {
        HashMap<Integer, Integer> stonesMap = new HashMap<>();//stone position/val,index pair
        for (int i = 0; i < stones.length; i++) {
            stonesMap.put(stones[i], i);
        }
        //pruning memorization
        HashMap<Integer, Boolean[]> mem = new HashMap<>(); //k and boolean[stone position] pair
        return dfs(stones, 0, 0, stonesMap, mem);
    }

    private boolean dfs(int[] stones, int idx, int jumpStep, HashMap<Integer, Integer> stonesMap, HashMap<Integer, Boolean[]> mem) {
        if (mem.containsKey(jumpStep)) {
            Boolean[] res = mem.get(jumpStep);
            if (res[idx] != null) {
                return res[idx];
            }
        }

        if (idx == stones.length - 1) {
            setMem(mem, jumpStep, idx, true, stones.length); // no need to say mem = setMem(mem, jumpStep, idx, true, stones.length);
            return true;
        }
        int min = Math.max(jumpStep - 1, 0);
        for (int i = min; i < jumpStep + 2; i++) {
            if (!stonesMap.containsKey(stones[idx] + i)) {
                continue;
            }
            int nextIdx = stonesMap.get(stones[idx] + i);
            if (nextIdx > idx && dfs(stones, nextIdx, i, stonesMap, mem)) {
                setMem(mem, jumpStep, idx, true, stones.length);
                return true;
            }
        }
        setMem(mem, jumpStep, idx, false, stones.length);
        return false;
    }

    private HashMap<Integer, Boolean[]> setMem(HashMap<Integer, Boolean[]> mem, int k, int idx, boolean res, int length) {
        if (mem.containsKey(k)) {
            Boolean[] temp = mem.get(k);
            temp[idx] = res;
            return mem;
        } else {
            Boolean[] temp = new Boolean[length];
            temp[idx] = res;
            mem.put(k, temp);
        }
        return mem;
    }

    public static void main(String[] args) {
        LC403FrogJump_Pruning_HashMap sol = new LC403FrogJump_Pruning_HashMap();
        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println("test 1 res: " + sol.canCross(stones));
        int[] stones2 = new int[]{0, 1, 2, 3, 4, 8, 9, 11};
        System.out.println("test 2 res: " + sol.canCross(stones2));
    }
}
