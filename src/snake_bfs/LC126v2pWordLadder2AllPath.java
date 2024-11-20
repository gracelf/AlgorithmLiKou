package snake_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * v2p, p as in practice, not much difference as in v1
 */
public class LC126v2pWordLadder2AllPath {

    public List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> words = new HashSet(wordList);
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> graph = new HashMap<>();
        boolean isOver = false;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            HashSet<String> nextLevel = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                System.out.println("cur: " + cur);
                List<String> toList = transform(cur, words);
                for (String word : toList) {
                    if (word.equals(endWord)) {
                        isOver = true;
                    }
                    queue.offer(word);
                    nextLevel.add(word);
                    if (!graph.keySet().contains(word)) {
                        ArrayList<String> fromList = new ArrayList<>();
                        graph.put(word, fromList);
                    }
                    graph.get(word).add(cur);
                }
            }
            words.removeAll(nextLevel);
        }
        if (isOver) {
            List<String> path = new LinkedList<>();
            path.add(endWord);
            dfs(res, path, endWord, beginWord, graph);
            return res;
        }
        return res;
    }

    private void dfs(List<List<String>> res, List<String> path, String cur, String beginWord, HashMap<String, List<String>> graph) {
        //base case
        //Not recommended, the following code is basically the same function as one recursion in a dfs, duplicated code, not idea
//        if (graph.get(cur) != null && graph.get(cur).contains(beginWord)) {
//            System.out.println("true" + cur + beginWord);
//            path.add(0, beginWord);
//            res.add(new ArrayList<>(path));//cannot be res.add(path);
//            path.remove(0);//back tracing
//            return;//!!!
//        }

        //recommended
        if (cur.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        List<String> fromList = graph.get(cur);
        for (String fromWord : fromList) {
            path.add(0, fromWord);
            dfs(res, path, fromWord, beginWord, graph);
            path.remove(0);
        }
    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        LC126v2pWordLadder2AllPath sol = new LC126v2pWordLadder2AllPath();
        List<List<String>> res = sol.ladderLength("hit", "cog", test);
        System.out.println("result=====");
        PrintMethod.printListOfStringList(res);
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
