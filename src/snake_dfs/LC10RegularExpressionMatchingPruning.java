package snake_dfs;

/**
 *
 */
public class LC10RegularExpressionMatchingPruning {

    public boolean isMatch(String s, String p) {
        Boolean[][] mem = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s.toCharArray(), p.toCharArray(), 0, 0, mem);
    }

    private boolean dfs(char[] s, char[] p, int idxS, int idxP, Boolean[][] mem) {
        System.out.println("====dfs " + "idxS: " + idxS + ", idxP: " + idxP);
        if (mem[idxS][idxP] != null) {
            return mem[idxS][idxP];
        }
        //base case, reached end
        if (idxP == p.length) {
            System.out.println("case 0====");
            boolean res = (idxS == s.length);
            mem[idxS][idxP] = res;
            System.out.println("base case, return: " + res + ", idxS: " + idxS + ", idxP: " + idxP);
            return res;
        } //case 1: next p/pattern is not *
        else if (idxP == p.length - 1 || p[idxP + 1] != '*') {
            System.out.println("case 1====");
            if (idxS < s.length && isMatch(s[idxS], p[idxP])) {
                boolean res = dfs(s, p, idxS + 1, idxP + 1, mem);
                mem[idxS][idxP] = res;
                return res;
            } else {
                System.out.println("return false case 1");
                mem[idxS][idxP] = false;
                return false;
            }
        } //case 2: next p/pattern is *, dfs
        else {
            System.out.println("case 2====");
            int i = idxS - 1; // for test case 3
            System.out.println("i; " + i + ", p: " + idxP);
            while (i < s.length && (i < idxS || isMatch(s[i], p[idxP]))) {
                System.out.println("in while loop, i; " + i + ", p: " + idxP);
                if (dfs(s, p, i + 1, idxP + 2, mem)) {
                    mem[idxS][idxP] = true;
                    System.out.println("in while loop dfs return true");
                    return true;
                }
                i++;
                //System.out.println(i);
            }
            System.out.println("return false case 2, i = " + i);
            mem[idxS][idxP] = false;
            return false;
        }
    }

    private boolean isMatch(char s, char p) {
        if (s == p || p == '.') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LC10RegularExpressionMatchingPruning sol = new LC10RegularExpressionMatchingPruning();
        // test case 1
        String s = "ab";
        String p = ".*";
        System.out.println("test case 1 result: " + sol.isMatch(s, p));
        // test case 2
        String s2 = "aaaabc";
        String p2 = "a*bc";
        System.out.println("test case 2 result: " + sol.isMatch(s2, p2));
        // test case 3, expect result is true
        String s3 = "abb";
        String p3 = "c*a*b*";
        System.out.println("test case 3 result: " + sol.isMatch(s3, p3));

    }
}
