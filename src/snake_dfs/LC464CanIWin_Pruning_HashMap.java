package snake_dfs;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 */
public class LC464CanIWin_Pruning_HashMap {

    public boolean canIwin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        //pool initializing 0001111 if maxChoosableInteger = 4;
        int pool = (1 << maxChoosableInteger) - 1;
        HashMap<Integer, Boolean> mem = new HashMap<>();
        //System.out.println("pool: " + pool + ", mem size: " + mem.length);
        return dfs(pool, 0, desiredTotal, mem, maxChoosableInteger);
    }

    private boolean dfs(int pool, int sum, int desiredTotal, HashMap<Integer, Boolean> mem, int maxChoosableInteger) {
        if (sum >= desiredTotal) { // no chance to choose
            return false;
        }
        Boolean val = mem.get(pool);
        if (val != null) {
            return val;
        }
        int mask = 1;
        for (int i = 1; i < maxChoosableInteger + 1; i++) {
            if ((pool & (mask << (i - 1))) != 0) { //if true, available for choose
                pool &= ~(mask << (i - 1));
                //pool -= mask;
                boolean res = dfs(pool, sum + i, desiredTotal, mem, maxChoosableInteger);
                //revert pool right after dfs
                pool |= (mask << (i - 1));
                //pool += mask;
                if (!res) { //at any choosen i, if opponent fail, return true; same logic for opponent
                    mem.put(pool, Boolean.TRUE);
                    return true;
                }
            }
        }
        mem.put(pool, Boolean.FALSE);
        return false;
    }

    public static void main(String[] args) {
        LC464CanIWin_Pruning_HashMap sol = new LC464CanIWin_Pruning_HashMap();
        boolean test = sol.canIwin(10, 11);
        System.out.println("test 1, expected false: " + test);
        boolean test2 = sol.canIwin(10, 1);
        System.out.println("test 2, expected true:  " + test2);
        boolean test3 = sol.canIwin(10, 15);
        System.out.println("test 3, expected true: " + test3);
    }

}
