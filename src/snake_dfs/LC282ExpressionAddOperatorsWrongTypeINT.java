package snake_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC282ExpressionAddOperatorsWrongTypeINT {

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(res, path, num, 0, target, 0, 0);
        return res;

    }

    public void dfs(List<String> res, StringBuilder path, String num, int index, int target, int sum, int lastVal) {
        //success base case
        if (index == num.length() && sum == target) {
            res.add(path.toString());
            return;
        }

        int val = 0;
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
                if(path.toString().equals("-2147483648")){
                    System.out.println("here!");
                }
                dfs(res, path, num, i + 1, target, sum - val, -val);
                path.setLength(len);
                // * 
                path.append("*" + val);
                dfs(res, path, num, i + 1, target, sum - lastVal + val * lastVal, val * lastVal);
                path.setLength(len);
            } else {
                //first val, just add the num to path
                path.append(val);
                if(path.toString().equals("-2147483648")){
                    System.out.println("here!");
                }
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
        LC282ExpressionAddOperatorsWrongTypeINT test = new LC282ExpressionAddOperatorsWrongTypeINT();      
        List<String> result = test.addOperators("2147483648", -2147483648);
        System.out.println(result);
        
        int testInt = 214748364*10+8;
        System.out.println(testInt);//-2147483648
    }

}