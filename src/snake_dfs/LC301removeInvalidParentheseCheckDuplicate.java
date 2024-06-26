

package snake_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC301removeInvalidParentheseCheckDuplicate {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        int removeL = 0;
        int removeR = 0;
        int[] count = calculateRemoveCount(s);
        removeL = count[0];
        removeR = count[1];
        StringBuilder path = new StringBuilder();
        System.out.println("remove L: "+removeL + ", remove r: "+ removeR);
        dfs(res,path,s.toCharArray(),0,removeL, removeR, 0);
        return res;    
    }

    private int[] calculateRemoveCount(String s){
        int removeL = 0;
        int removeR = 0;
        int delta = 0; 
        char[] chars = s.toCharArray();
        for(char ch: chars){
            if(ch=='(') {
                delta +=1;
            }
            else if (ch==')') {
                if(delta >0) {
                    delta -=1;
                } else {
                    removeR++;
                }
            }
        } removeL = delta;
        return new int[]{removeL, removeR};

    }

    private void dfs(List<String> res, StringBuilder path, char[] s, int index, int removeL, int removeR, int delta){
        if (index == 4){
            System.out.println("index =4 => " +  index + ", delta, left, right = "+ delta + "," + removeL + "," + removeR + ", path: " + path.toString() );
        
        }
        //success basecase
        if(index == s.length && removeL == 0 && removeR == 0 && delta == 0){
            System.out.println("path: " + path.toString());
            res.add(path.toString());
            return;
        }
        if(index >= s.length|| removeL < 0 || removeR < 0 || delta < 0){
            return;
        }

        char ch = s[index];
        int len = path.length();
        //System.out.println("path: " + path.toString());

        if (ch == '('){
            //remove
            dfs(res, path, s, index+1, removeL-1, removeR, delta );
            //keep 
            int rep = 1;
            while (index+rep< s.length && s[index+rep] == s[index]){
                rep++;
            }
            path.append(s, index, rep);
            System.out.println("at (, index = " +  index + ", rep = "+ rep + ", path: " + path.toString());
            dfs(res, path, s, index +rep, removeL, removeR, delta + rep);
            path.setLength(len);
        } else if (ch == ')'){
            //remove
            dfs(res, path, s, index+1, removeL, removeR-1, delta );
            //keep 
             int rep = 1;
            while (index+rep< s.length && s[index+rep] == s[index]){
                rep++;
            }
            path.append(s, index, rep);
            System.out.println("at ), index = " +  index + ", rep = "+ rep + ", path: " + path.toString());
            dfs(res, path, s, index +rep, removeL, removeR, delta - rep);
            path.setLength(len);
        } else {
            path.append(String.valueOf(ch));
            dfs(res, path, s, index +1, removeL, removeR, delta);
            path.setLength(len);
        }
    }
    
    public static void main(String[] args){
        LC301removeInvalidParentheseCheckDuplicate test = new LC301removeInvalidParentheseCheckDuplicate();
//        String s = "()())()";
//        List<String> res = test.removeInvalidParentheses(s);
//        System.out.println(res);
        
        //test 2
        String s2 = "()))abc";
        List<String> res2 = test.removeInvalidParentheses(s2);
        System.out.println(res2);
    }
}
