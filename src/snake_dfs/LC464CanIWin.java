package snake_dfs;

import java.util.Arrays;

/**
 *
 */
public class LC464CanIWin {

    public boolean canIwin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        boolean[] pool = new boolean[maxChoosableInteger + 1]; //length/size = 11 => index 0 - 10
        Arrays.fill(pool, true);
        return dfs(pool, 0, desiredTotal);
    }

    private boolean dfs(boolean[] pool, int sum, int desiredTotal) {
        if (sum >= desiredTotal) { // no chance to choose
            return false;
        }
        for (int i = 1; i < pool.length; i++) { //i <10
            if (pool[i]) {
                pool[i] = false;
                boolean res = dfs(pool, sum + i, desiredTotal);
                pool[i] = true;
                if (!res) { //at any choosen i, if opponent fail, return true; same logic for opponent
                    return true;
                }

            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean[] tst = new boolean[10];
        System.out.println("array size:  " + tst.length);
        LC464CanIWin sol = new LC464CanIWin();
        boolean test = sol.canIwin(10, 11);
        System.out.println("test 1, expected false: " + test);
        boolean test2 = sol.canIwin(10, 1);
        System.out.println("test 2, expected true:  " + test2);
        boolean test3 = sol.canIwin(10, 15);
        System.out.println("test 3, expected true: " + test3);
    }

}
