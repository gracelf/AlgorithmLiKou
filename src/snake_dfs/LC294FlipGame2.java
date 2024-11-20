package snake_dfs;

/**
 * logic, at each +, flip or not
 */
public class LC294FlipGame2 {

    public boolean canWin(String currentState) {
        char[] current = currentState.toCharArray();
        return dfs(current);
    }

    private boolean dfs(char[] current) {
        for (int i = 0; i < current.length - 1; i++) {
            if (current[i] == '+' && current[i + 1] == '+') {
                current[i] = '-';
                current[i + 1] = '-';
                boolean res = dfs(current);
                //reverse
                current[i] = '+';
                current[i + 1] = '+';
                if (!res) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC294FlipGame2 sol = new LC294FlipGame2();
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
