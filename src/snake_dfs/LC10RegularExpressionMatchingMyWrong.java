package snake_dfs;

/**
 *
 */
public class LC10RegularExpressionMatchingMyWrong {

    public boolean isMatch(String s, String p) {
        return dfs(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean dfs(char[] s, char[] p, int idxS, int idxP) {
        //base case, reached end
        if (idxP == p.length) {
            return idxS == s.length; // return  false for some branches, but not the final output
        }
        //case 1: next p/pattern is not *
        if (idxP == p.length - 1 || p[idxP + 1] != '*') {
            if (idxS < s.length && isMatch(s[idxS], p[idxP])) {
                return dfs(s, p, idxS + 1, idxP + 1);
            } else {
                return false;
            }
        } //else condition,  next p/pattern is *, dfs
        else {
            int i = idxS; // should be idxS to deal with test case 3
            System.out.println(i + "p: " + idxP);
            while (i < s.length && (isMatch(s[i], p[idxP]))) {
                if (dfs(s, p, i + 1, idxP + 2)) {
                    return true;
                }
                i++;
                System.out.println(i);
            }
            return false;
        }
    }

    // branching logic base on current idxP is '*' or not  
    private boolean myDfs(char[] s, char[] p, int idxS, int idxP) {
        if (idxS == s.length && idxP == p.length) {
            return true;
        }
        //case 1: current p/pattern is not *
        if (idxS < s.length && idxP < p.length && p[idxP] != '*' && isMatch(s[idxS], p[idxP])) {
            return dfs(s, p, idxS + 1, idxP + 1);
        } //case 2:  current p/pattern is *, dfs
        else if (idxS < s.length && idxP < p.length && p[idxP] == '*') {
            int i = idxS - 1;
            while (s[i] == s[idxS]) {
                if (dfs(s, p, i + 1, idxP + 1)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    private boolean isMatch(char s, char p) {
        if (s == p || p == '.') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LC10RegularExpressionMatchingMyWrong sol = new LC10RegularExpressionMatchingMyWrong();
        // test case 1
        String s = "ab";
        String p = ".*";
        System.out.println("test case 1 result: " + sol.isMatch(s, p));
        // test case 2
        String s2 = "aaaaabc";
        String p2 = "a*bc";
        System.out.println("test case 2 result: " + sol.isMatch(s2, p2));
        // test case 3, expect result is true
        String s3 = "abb";
        String p3 = "c*a*b*";
        System.out.println("test case 3 result: " + sol.isMatch(s3, p3));

    }
}
