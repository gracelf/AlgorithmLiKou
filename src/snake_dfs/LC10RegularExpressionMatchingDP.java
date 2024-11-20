package snake_dfs;

/**
 *
 */
public class LC10RegularExpressionMatchingDP {

    public boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        //dp initialization, from last to beginning
        dp[lenS][lenP] = true;

        int k = lenP - 2;
        while (k >= 0) {
            if (p.charAt(k + 1) == '*') {
                dp[lenS][k] = true;
            } else {
                break;
            }
            k -= 2;
        }

        // for loop from end to start, goal is to determine dp[i][j] from dp[i+1][j+1]
        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    continue;
                }
                //if p[j+1]!='*'
                if (j + 1 >= lenP || p.charAt(j + 1) != '*') {
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                } //if p[j+1]=='*'
                else {
                    int idx = i - 1;
                    dp[i][j] = false;
                    while (idx < lenS && (idx < i || p.charAt(j) == '.' || p.charAt(j) == s.charAt(idx))) {
                        if (dp[idx + 1][j + 2]) { // start from dp[i][j+2]
                            System.out.println("idx: " + idx + ", i: " + i + ", j: " + j + ", dp[i][j] is set to true" );
                            dp[i][j] = true; // not dp[idx][j]
                            break;
                        }
                        idx++;
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        LC10RegularExpressionMatchingDP sol = new LC10RegularExpressionMatchingDP();
        // test case 1
        String s = "ab";
        String p = ".*";
        //System.out.println("test case 1 result: " + sol.isMatch(s, p));
        // test case 2
        String s2 = "aaaabc";
        String p2 = "a*bc";
        //System.out.println("test case 2 result: " + sol.isMatch(s2, p2));
        // test case 3, expect result is true
        String s3 = "abb";
        String p3 = "c*a*b*";
        System.out.println("test case 3 result: " + sol.isMatch(s3, p3));
    }
}
