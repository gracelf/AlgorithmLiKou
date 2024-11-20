package snake_dfs;

import java.util.Arrays;

/**
 *
 */
public class LC464CanIWin_Pruning_BooleanArray {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        // pool initializing 0001111 if maxChoosableInteger = 4;
        int pool = (1 << maxChoosableInteger) - 1;
        Boolean[] mem = new Boolean[pool + 1];
        // System.out.println("pool: " + pool + ", mem size: " + mem.length);
        return dfs(pool, 0, desiredTotal, mem, maxChoosableInteger);
    }

    private boolean dfs(int pool, int sum, int desiredTotal, Boolean[] mem, int maxChoosableInteger) {
        if (mem[pool] != null) {
            return mem[pool];
        }
        if (sum >= desiredTotal) { // no chance to choose
            return false;
        }

        for (int i = 1; i < maxChoosableInteger + 1; i++) {
            int mask = 1 << (i - 1);
            if ((pool & mask) != 0) { // if true, available for choose, !!!wrong condition: (pool & mask) == 1
                // pool &= ~(mask << (i - 1));
                pool -= mask;
                boolean res = dfs(pool, sum + i, desiredTotal, mem, maxChoosableInteger);
                // revert pool right after dfs
                // pool |= (mask << (i - 1));
                pool += mask;
                if (!res) { // at any choosen i, if opponent fail, return true; same logic for opponent
                    mem[pool] = true;
                    return true;
                }
            }
        }
        mem[pool] = false;
        return false;
    }

    public static void main(String[] args) {
        LC464CanIWin_Pruning_BooleanArray sol = new LC464CanIWin_Pruning_BooleanArray();
        boolean test = sol.canIWin(10, 11);
        System.out.println("test 1, expected false: " + test);
        boolean test2 = sol.canIWin(10, 1);
        System.out.println("test 2, expected true:  " + test2);
        boolean test3 = sol.canIWin(10, 15);
        System.out.println("test 3, expected true: " + test3);
    }

}
