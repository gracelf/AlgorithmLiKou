package snake_dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class LC140WordBreakAllPath {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        dfs(0, s, set, res, path);
        return res;
    }

    private void dfs(int i, String s, HashSet<String> wordDict, List<String> res, StringBuilder path) {
        if (i == s.length()) {
            res.add(path.toString());
        }
        for (int idx = i; idx < s.length(); idx++) {
            if (!wordDict.contains(s.substring(i, idx + 1))) {
                continue;
            }

            int len = path.length();
            if (len == 0) {
                path.append(s.substring(i, idx + 1));
            } else {
                path.append(" " + s.substring(i, idx + 1));
            }
            dfs(idx+1, s, wordDict, res, path);
            path.setLength(len);
        }
    }

    public static void main(String[] args) {
        String s = "leetcode";
        //["leet","code"]
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        LC140WordBreakAllPath sol = new LC140WordBreakAllPath();
        List<String> res = sol.wordBreak(s, wordDict);
        System.out.println(res);
        //test case 2, aaaaaaa
        String s2 = "aaaaaaa";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("aaaa");
        wordDict2.add("aaa");
        List<String> res2 = sol.wordBreak(s2, wordDict2);
        System.out.println(res2);
    }

}
