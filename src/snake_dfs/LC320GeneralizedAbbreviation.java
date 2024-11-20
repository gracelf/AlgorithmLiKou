package snake_dfs;

import java.util.*;

/**
 *
 */
public class LC320GeneralizedAbbreviation {

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        char[] charWord = word.toCharArray();
        dfs(res, path, charWord, 0, 0);
        return res;

    }

    public void dfs(List<String> res, StringBuilder path, char[] word, int index, int count) {
        int length = path.length();
        if (index == word.length) {
            if (count > 0) {
                path.append(count);
                //count = 0; //no need
            }
            res.add(path.toString());
            //important, back tracking
            path.setLength(length);
            return;
        }
        //num
        dfs(res, path, word, index + 1, count + 1);
        //letter
        if (count > 0) {
            path.append(count);
            count = 0;
        }
        path.append(word[index]);
        dfs(res, path, word, index + 1, count);
        path.setLength(length);
    }

    public static void main(String[] args) {
        LC320GeneralizedAbbreviation test = new LC320GeneralizedAbbreviation();
        String word = "word";
        List<String> res = test.generateAbbreviations(word);
        System.out.println(res.size() + " results: " + res);
    }

}
