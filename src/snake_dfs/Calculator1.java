package snake_dfs;

/**
 *
 */
public class Calculator1 {

    public int calculate(String input) {
        int sum = 0;
        int lastVal = 0;
        int val = 0;
        int len = input.length();
        char sign = '+';
        char[] chars = input.toCharArray();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(chars[i])) {
                val = val * 10 + (chars[i] - '0');
            }
            if (!Character.isDigit(chars[i]) && !Character.isWhitespace(chars[i]) || i == len - 1) {
                if (sign == '+') {
                    sum += val;
                    lastVal = val;
                } else if (sign == '-') {
                    sum -= val;
                    lastVal = -val;
                } else if (sign == '*') {
                    sum = sum - lastVal + lastVal * val;
                    lastVal *= val;
                } else if (sign == '/') {
                    sum = sum - lastVal + lastVal / val;
                    lastVal /= val;
                }
                val = 0;
                sign = chars[i];
            }
        }
        return sum;
    }

//        public int calculateSampleCode(String s) {
//        int len = s.length();
//        int res = 0;
//        int val = 0;
//        int lastVal = 0;
//        char sign = '+';
//
//        for (int i = 0; i < len; i++) {
//            char cur = s.charAt(i);
//            if (Character.isDigit(cur)) {
//                val = val * 10 + (cur - '0');
//            }
//            if (!Character.isDigit(cur) && !Character.isWhitespace(cur) || 
//            i == len - 1) {
//                if (sign == '+') {
//                    res += val;
//                    lastVal = val;
//                } else if (sign == '-') {
//                    res -= val;
//                    lastVal = -val;
//                } else if (sign == '*') {
//                    res = res - lastVal + lastVal * val;
//                    lastVal *= val;
//                } else if (sign == '/') {
//                    res = res - lastVal + lastVal / val;
//                    lastVal /= val;
//                }
//                val = 0;
//                sign = cur;
//            }
//        }
//        return res;
//    }
    public static void main(String[] args) {
        String input1 = "18+2*4-5/2";
        Calculator1 cal = new Calculator1();
        int res = cal.calculate(input1);

        System.out.println("test 1 result: " + res);
        System.out.println("white space: " + Character.isWhitespace(' '));
    }

}
