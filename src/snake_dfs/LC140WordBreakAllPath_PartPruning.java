package snake_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class LC140WordBreakAllPath_PartPruning {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        boolean[] mem = new boolean[s.length() + 1];
        Arrays.fill(mem, true);
        for (String str : wordDict) {
            set.add(str);
        }
        dfs(0, s, set, res, path, mem);
        return res;
    }

    private void dfs(int i, String s, HashSet<String> wordDict, List<String> res, StringBuilder path, boolean[] mem) {
        if (i == s.length()) {
            res.add(path.toString());
            mem[i] = true;
        }
        int size = res.size();
        for (int idx = i; idx < s.length(); idx++) {
            if (!wordDict.contains(s.substring(i, idx + 1))) {
                continue;
            }

            if (!mem[idx]) { // if false, skip the update, otherwise, will try
                return;
            }

            int len = path.length();
            if (len == 0) {
                path.append(s.substring(i, idx + 1));
            } else {
                path.append(" " + s.substring(i, idx + 1));
            }
            dfs(idx + 1, s, wordDict, res, path, mem);
            path.setLength(len);
            if (res.size() == size) {
                mem[idx] = false;
            }
        }
    }

    public static void main(String[] args) {
        String s = "leetcode";
        //["leet","code"]
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        LC140WordBreakAllPath_PartPruning sol = new LC140WordBreakAllPath_PartPruning();
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
