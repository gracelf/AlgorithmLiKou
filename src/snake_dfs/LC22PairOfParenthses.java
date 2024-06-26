

package snake_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC22PairOfParenthses {

public List<String> addOperators(String num, long target) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(res, path, num, 0, target, 0L, 0L);
        return res;

    }

    public void dfs(List<String> res, StringBuilder path, String num, int index, long target, long sum, long lastVal) {
        //success base case
        if (index == num.length() && sum == target) {
            res.add(path.toString());
            return;
        }

        long val = 0;
        for (int i = index; i < num.length(); i++) {
            val = val * 10 + (num.charAt(i) - '0');
            int len = path.length();

            //add +/-/* before the val
            if (len > 0) {
                // + 
                path.append("+" + val);
                dfs(res, path, num, i + 1, target, sum + val, val);
                path.setLength(len);
                // -
                path.append("-" + val);
                dfs(res, path, num, i + 1, target, sum - val, -val);
                path.setLength(len);
                // * 
                path.append("*" + val);
                dfs(res, path, num, i + 1, target, sum - lastVal + val * lastVal, val * lastVal);
                path.setLength(len);
            } else {
                //first val, just add the num to path
                path.append(val);
                dfs(res, path, num, i + 1, target, val, val);
                path.setLength(len);
            }
            //break after adding 0, 0 is allowed, only 0X not allowed
            if (val == 0) {
                break;
            }
        }

    }
    
    public static void main(String[] args){
        LC22PairOfParenthses test = new LC22PairOfParenthses();      
        List<String> result = test.addOperators("2147483648", -2147483648L);
        System.out.println(result);
    }
}
