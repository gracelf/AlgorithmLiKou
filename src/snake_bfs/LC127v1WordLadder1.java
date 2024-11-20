package snake_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class LC127v1WordLadder1 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //c.c
        HashSet<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dict.remove(beginWord);
        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curr = queue.poll();
                List<String> words = transform(curr, dict);
                for (String word : words) {
                    if (word.equals(endWord)) {
                        return minLen + 1;
                    }
                    queue.offer(word);
                    //!!!! mark visited
                    dict.remove(word);
                }
            }
            minLen++;
        }
        return 0;
    }

    private List<String> transform(String curr, HashSet<String> dict) {
        List<String> res = new ArrayList<>();
        char[] currChar = curr.toCharArray();
        for (int i = 0; i < currChar.length; i++) {
            char temp = currChar[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != temp) {
                    currChar[i] = c;
                    String tempStr = String.valueOf(currChar);
                    if (dict.contains(tempStr)) {
                        res.add(tempStr);
                    }
                }
            }
            //revert changes on char array
            currChar[i] = temp;
        }
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        //test case 1
        List<String> list = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        LC127v1WordLadder1 sol = new LC127v1WordLadder1();
        int res = sol.ladderLength("hit", "cog", list);
        System.out.println("test case 1: res: " + res);

        //test case 2
        List<String> sourceList = Arrays.asList("hot", "dot", "dog", "lot", "log");
        int res2 = sol.ladderLength("hit", "cog", sourceList);
        System.out.println("test case 2: res: " + res2);
    }

}
