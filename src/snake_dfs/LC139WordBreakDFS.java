package snake_dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class LC139WordBreakDFS {

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        return dfs(0, s, set);
    }

    private boolean dfs(int idx, String s, HashSet<String> wordDict) {
        if (idx == s.length()) {
            return true;
        }
        for (int i = idx; i < s.length(); i++) {
            if (!wordDict.contains(s.substring(idx, i + 1))) {
                continue;
            }
            if (dfs(i + 1, s, wordDict)) {
                return true;
            };
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        //["leet","code"]
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        LC139WordBreakDFS sol = new LC139WordBreakDFS();
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
