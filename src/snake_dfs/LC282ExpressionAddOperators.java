package snake_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC282ExpressionAddOperators {

    static final Calculator1 cal = new Calculator1();

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(res, path, num, 0, target);
        return res;

    }

    public void dfs(List<String> res, StringBuilder path, String num, int index, int target) {
        //success base case
        if (index == num.length() && cal.calculate(path.toString()) == target) {
            res.add(path.toString());
        }

        int val = 0;
        for (int i = index; i < num.length(); i++) {
            val = val * 10 + (num.charAt(i) - '0');
            int len = path.length();

            if (len > 0) {
                // + 
                path.append("+" + val);
                dfs(res, path, num, i + 1, target);
                path.setLength(len);
                                // -
                path.append("-" + val);
                dfs(res, path, num, i + 1, target);
                path.setLength(len);
                                // * 
                path.append("*" + val);
                dfs(res, path, num, i + 1, target);
                path.setLength(len);
            } else {
                path.append(val);
                dfs(res, path, num, i + 1, target);
                path.setLength(len);

            }
        }

    }

    public static void main(String[] args) {
        String input1 = "123";
        int target = 6;
        LC282ExpressionAddOperators test = new LC282ExpressionAddOperators();
        List<String> res = test.addOperators(input1, target);

        System.out.println("test 1 result: " + res);
    }

}
