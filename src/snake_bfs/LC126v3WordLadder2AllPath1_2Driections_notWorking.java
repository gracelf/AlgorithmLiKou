package snake_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * v2p, p as in practice, not much difference as in v1
 */
public class LC126v3WordLadder2AllPath1_2Driections_notWorking {

    private HashMap<String, List<String>> beginGraph = new HashMap<>();
    private HashMap<String, List<String>> endGraph = new HashMap<>();
    private String beginWord = null, endWord = null;

    public List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> words = new HashSet(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!words.contains(endWord)) {
            return res;
        }
        words.remove(beginWord);
        words.remove(endWord);

        this.beginWord = beginWord;
        this.endWord = endWord;

        beginGraph = new HashMap<>();
        endGraph = new HashMap<>();

        HashSet<String> beginSet = new HashSet<>();
        HashSet<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        boolean isOver = false;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            HashMap<String, List<String>> graph = beginGraph;
            boolean beginToEnd = true;
            if (beginSet.size() > endSet.size()) {
                HashSet<String> temp = endSet;
                endSet = beginSet;
                beginSet = temp;
                beginToEnd = false;
                graph = endGraph;
            }

            HashSet<String> nextLevelList = new HashSet();
            HashSet<String> sourceList = new HashSet();
            for (String cur : beginSet) {
                char[] chars = cur.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != temp) {
                            chars[i] = c;
                            String next = String.valueOf(chars);
                            if (words.contains(next) || endSet.contains(next)) { //even if words doesn't contain, use endSet to make 2 parts meet
                                if (endSet.contains(next)) {
                                    sourceList.add(next);
                                    isOver = true;
                                }

                                if (!nextLevelList.contains(next)) {
                                    nextLevelList.add(next);
                                }
                                if (!graph.keySet().contains(next)) {
                                    ArrayList<String> fromList = new ArrayList<>();
                                    graph.put(next, fromList);
                                }
                                graph.get(next).add(cur);
                            }
                        }
                        chars[i] = temp;
                    }
                }
                words.removeAll(nextLevelList);
                if (beginToEnd) {
                    beginSet = nextLevelList;
                } else {
                    endSet = nextLevelList;
                }
            }

            if (isOver) {
                for (String source : sourceList) {
                    List<String> path = new LinkedList<>();
                    path.add(source);
                    dfs(res, path, source, source, true);
                }
                return res;
            }
        }
        return res;
    }

    private void dfs(List<List<String>> res, List<String> path, String cur, String source, boolean beginToEnd) {
        //base case??
//        if (cur.equals(beginWord)) {
//            res.add(new ArrayList<>(path));
//            return;
//        }

        if (cur.equals(endWord) && beginToEnd) {
            dfs(res, path, source, source, false);
        } else if (cur.equals(beginWord) && !beginToEnd) {
            res.add(new ArrayList<>(path));
        } else {
            List<String> nextList
                    = beginToEnd ? endGraph.get(cur) : beginGraph.get(cur);
            for (String next : nextList) {
                if (beginToEnd) {
                    path.add(next);
                    dfs(res, path, next, source, beginToEnd);
                    path.remove(path.size() - 1);
                } else {
                    path.add(0, next);
                    dfs(res, path, next, source, beginToEnd);
                    path.remove(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        LC126v3WordLadder2AllPath1_2Driections_notWorking sol = new LC126v3WordLadder2AllPath1_2Driections_notWorking();
        List<List<String>> res = sol.ladderLength("hit", "cog", test);
        System.out.println("result=====");
        PrintMethod.printListOfStringList(res);
    }

}
