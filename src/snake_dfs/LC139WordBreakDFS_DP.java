package snake_dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class LC139WordBreakDFS_DP {

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        //initialize dp
        int len = s.length();
        boolean[] dp = new boolean[len + 1]; //default is false
        dp[len] = true;
        //if(set contains s.substring(i, j+1)) =>dp[i] = dp[j], fill dp backawards
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len; j++) {
                if (dp[j] && set.contains(s.substring(i, j))) { //tip: put dp[j] before, if false, won't check if contains
                    dp[i] = true;
                    break;//once found the postive case, end the j loop
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        //["leet","code"]
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        LC139WordBreakDFS_DP sol = new LC139WordBreakDFS_DP();
        boolean res = sol.wordBreak(s, wordDict);
        System.out.println(res);
        //test case 2, aaaaaaa
        String s2 = "aaaaaaa";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("aaaa");
        wordDict2.add("aaa");
        boolean res2 = sol.wordBreak(s2, wordDict2);
        System.out.println(res2);

    }

}
