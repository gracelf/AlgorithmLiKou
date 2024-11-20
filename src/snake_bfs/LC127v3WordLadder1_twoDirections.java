package snake_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class LC127v3WordLadder1_twoDirections {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {//no queue any more, use hashset to store words in a level
        HashSet<String> words = new HashSet(wordList);

        // greatly reduce complexity in this edge case
        if (!words.contains(endWord)) {
            return 0;
        }

        HashSet<String> beginSet = new HashSet();
        beginSet.add(beginWord);

        HashSet<String> endSet = new HashSet();
        endSet.add(endWord);

        int step = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                HashSet<String> tempSet = beginSet;
                beginSet = endSet;
                endSet = tempSet;
            }

            //no more queue, hashset replaces "queue" and "queue divider" in the other solution
            HashSet<String> nextLevel = new HashSet<>();
            for (String cur : beginSet) {
                char[] chars = cur.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != temp) {
                            chars[i] = c;
                            String word = String.valueOf(chars);
                            if (endSet.contains(word)) {
                                return step + 1;
                            }
                            if (words.contains(word)) {
                                nextLevel.add(word);
                                words.remove(word);//ok to remove here, we don't need all path
                            }
                        }
                    }
                    chars[i] = temp;
                }
            }
            beginSet = nextLevel;//move to next level elements, like elements in the queue in next divider
            step++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        LC127v3WordLadder1_twoDirections sol = new LC127v3WordLadder1_twoDirections();
        int res = sol.ladderLength("hit", "cog", test);
        System.out.println("result: " + res);
    }
}
