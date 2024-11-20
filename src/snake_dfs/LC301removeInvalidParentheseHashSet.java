package snake_dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class LC301removeInvalidParentheseHashSet {

    public List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        int removeL = 0;
        int removeR = 0;
        int[] count = calculateRemoveCount(s);
        removeL = count[0];
        removeR = count[1];
        StringBuilder path = new StringBuilder();
        dfs(res, path, s, 0, removeL, removeR, 0);
        return new ArrayList<>(res);
    }

    private int[] calculateRemoveCount(String s) {
        int removeL = 0;
        int removeR = 0;
        int delta = 0;
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '(') {
                delta += 1;
            } else if (ch == ')') {
                if (delta > 0) {
                    delta -= 1;
                } else {
                    removeR++;
                }
            }
        }
        removeL = delta;
        return new int[]{removeL, removeR};

    }

    private void dfs(Set<String> res, StringBuilder path, String s, int index, int removeL, int removeR, int delta) {
        //success basecase
        if (index == s.length() && removeL == 0 && removeR == 0 && delta == 0) {
            res.add(path.toString());
            return;
        }
        //fail basecase
        if (index >= s.length() || removeL < 0 || removeR < 0 || delta < 0) {
            return;
        }

        char ch = s.charAt(index);
        int len = path.length();
        if (ch == '(') {
            //remove
            dfs(res, path, s, index + 1, removeL - 1, removeR, delta);
            //keep 
            path.append("(");
            dfs(res, path, s, index + 1, removeL, removeR, delta + 1);
            path.setLength(len);
        } else if (ch == ')') {
            //remove
            dfs(res, path, s, index + 1, removeL, removeR - 1, delta);
            //keep 
            path.append(")");
            dfs(res, path, s, index + 1, removeL, removeR, delta - 1);
            path.setLength(len);

        } else {
            path.append(String.valueOf(ch));
            dfs(res, path, s, index + 1, removeL, removeR, delta);
            path.setLength(len);
        }

    }

    public static void main(String[] args) {
        LC301removeInvalidParentheseHashSet test = new LC301removeInvalidParentheseHashSet();
        String s = "()())()";
        List<String> res = test.removeInvalidParentheses(s);
        System.out.println(res);
    }
}
