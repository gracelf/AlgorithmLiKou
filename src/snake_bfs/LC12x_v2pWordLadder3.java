package snake_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class LC12x_v2pWordLadder3 {

    public List<String> ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<String> res = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        HashSet<String> dict = new HashSet(wordList);
        HashMap<String, String> graph = new HashMap();

        queue.offer(beginWord);
        dict.remove(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                System.out.println("cur: " + cur);
                List<String> nexts = transform(cur, dict);
                for (String next : nexts) {
                    graph.put(next, cur);
                    queue.offer(next);
                    dict.remove(next);//remove even this level is not done

                    if (next.equals(endWord)) {
                        res = printPath(graph, beginWord, next);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        LC12x_v2pWordLadder3 sol = new LC12x_v2pWordLadder3();
        List<String> res = sol.ladderLength("hit", "cog", test);
        //[hit, hot, dot, dog, cog]
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

    private List<String> printPath(HashMap<String, String> graph, String beginWord, String cur) {
        List<String> res = new ArrayList();
        res.add(0, cur); //cur starts from endWord
        while (!cur.equals(beginWord)) {
            String next = graph.get(cur);
            res.add(0, next);
            cur = next; //loop
        }
        return res;
    }

}
