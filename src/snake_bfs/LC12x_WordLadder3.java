package snake_bfs;

import java.util.*;

/**
 * return any shortest path
 */
public class LC12x_WordLadder3 {

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        // c.c.
        List<String> res = new ArrayList<>();
        HashSet<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dict.remove(beginWord);
        HashMap<String, String> graph = new HashMap<>();
        //boolean isOver = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //HashSet<String> nextLevelSet = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = transform(cur, dict);
                for (String next : nexts) {
                    queue.offer(next);
                    graph.put(next, cur);
                    dict.remove(next);
                    if (next.equals(endWord)) {
                        printPath(res, next, beginWord, graph);
                    }
                }
            }
            //dict.removeAll(nextLevelSet);
            // if (isOver) {  // 还原path(DFS)
            //     List<String> path = new LinkedList<>();
            //     path.add(endWord);
            //     // start from endworld back to beginword
            //     dfs(res, path, endWord, beginWord, graph);
            //     return res;
            // }

        }
        return res;
    }

    private void printPath(List<String> res, String cur, String beginWord, HashMap<String, String> graph) {
        res.add(cur);
        while (!cur.equals(beginWord)) { // wrong condition !graph.isEmpty() &&
            String next = graph.get(cur);
            res.add(0, next);
            graph.remove(cur);
            cur = next;
        }
        System.out.println("graph: " + graph);

    }

    private List<String> transform(String cur, HashSet<String> dict) {
        List<String> nexts = new ArrayList<>();
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (chars[i] == c) {
                    continue;
                }
                chars[i] = c;
                String newStr = new String(chars);
                if (dict.contains(newStr)) {
                    nexts.add(newStr);
                }
            }
            chars[i] = temp;
        }
        return nexts;
    }

//    private void dfs(List<List<String>> res, List<String> path, String cur,
//                     String beginWord, HashMap<String, List<String>> graph) {
//        if (cur.equals(beginWord)) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        for (String next : graph.get(cur)) {
//            path.add(0, next);
//            dfs(res, path, next, beginWord, graph);
//            path.remove(0);
//        }
//    }
    public static void main(String[] args) {
        LC12x_WordLadder3 sol = new LC12x_WordLadder3();
        //test case 1$
        List<String> list = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<String> res = sol.findLadders("hit", "cog", list);
        System.out.println("test case 1: res: " + res);

        //test case 2
        List<String> sourceList = Arrays.asList("hot", "dot", "lot", "cot", "cog");
        List<String> res2 = sol.findLadders("hit", "cog", sourceList);
        System.out.println("test case 2: res: " + res2);
    }

}
