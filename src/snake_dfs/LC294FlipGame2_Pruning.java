package snake_dfs;

import java.util.Arrays;
import java.util.HashMap;

/**
 * logic, at each +, flip or not
 */
public class LC294FlipGame2_Pruning {

    public boolean canWin(String currentState) {
        char[] current = currentState.toCharArray();
        HashMap<String, Boolean> mem = new HashMap<>();
        return dfs(current, mem);
    }

    private boolean dfs(char[] current, HashMap<String, Boolean> mem) {
        String key = Arrays.toString(current);
        if (mem.containsKey(key)) { // clean and simple
            return mem.get(key);
        }

        for (int i = 0; i < current.length - 1; i++) {
            if (current[i] == '+' && current[i + 1] == '+') {
                current[i] = '-';
                current[i + 1] = '-';
                boolean res = dfs(current, mem); //check and set in the dfs lower level, no need to add to map here, will be duplicate work
                //reverse
                current[i] = '+';
                current[i + 1] = '+';
                if (!res) {
                    mem.put(key, true);// key is original variable key
                    return true;
                }
            }
        }
        mem.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        LC294FlipGame2_Pruning sol = new LC294FlipGame2_Pruning();
        //testcase 1
        String test1 = "++++";
        boolean res1 = sol.canWin(test1);
        System.out.println("test result 1:" + res1);
        //testcase 2
        String test2 = "+++++";
        boolean res2 = sol.canWin(test2);
        System.out.println("test result 2:" + res2);

    }

}
