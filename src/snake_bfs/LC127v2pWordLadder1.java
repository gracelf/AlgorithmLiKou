package snake_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *v2p, p as in practice, not much difference as in v1
 */
public class LC127v2pWordLadder1 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> words = new HashSet(wordList);
        int step = 1;
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                System.out.println("cur: " + cur);
                List<String> res = transform(cur, words);
                for (String word : res) {
                    if (word.equals(endWord)) {
                        return step + 1;
                    }
                    queue.offer(word);
                    words.remove(word);
                }
            }
            step++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        LC127v2pWordLadder1 sol = new LC127v2pWordLadder1();
        int res = sol.ladderLength("hit", "cog", test);
        System.out.println("result: " + res);
    }

    private List<String> transform(String cur, HashSet<String> words) {
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != temp) {
                    chars[i] = c;
                    //String word = chars.toString(); not working
                    String word = String.valueOf(chars);
                    if (words.contains(word)) {
                        res.add(word);
                    }
                }// if condition ends here, only under this condition, we need to update chars and check in hashmap if exists
            }
            chars[i] = temp;
        }
        System.out.println(res);
        return res;
    }

}
