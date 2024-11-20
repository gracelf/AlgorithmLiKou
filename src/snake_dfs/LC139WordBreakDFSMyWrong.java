package snake_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC139WordBreakDFSMyWrong {

    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(0, s, wordDict);
    }

    private boolean dfs(int idx, String s, List<String> wordDict) {
        if (idx == s.length()) {
            return true;
        }
        for (int i = idx; i < s.length(); i++) {
            if (wordDict.contains(s.substring(idx, i + 1))) {
                ////wrong here =>should only return true when dfs result is true, if false, continue search
                return dfs(i + 1, s, wordDict);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //test case 1, ["leet","code"]
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        LC139WordBreakDFSMyWrong sol = new LC139WordBreakDFSMyWrong();
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
