package mianJing;

import java.util.Arrays;

/**
 *
 */
public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] charIndex = new int[128]; //ASCII value range (0–127)
        Arrays.fill(charIndex, -1);
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (charIndex[s.charAt(right)] >= left) {
                printArray(charIndex);//ASCII code
                System.out.println("s.charAt(right): " + s.charAt(right) + ", char index: " + charIndex[s.charAt(right)]);
                //Get the ASCII Value of a Character in Java, https://www.baeldung.com/java-character-ascii-value
                char c = 'a';
                System.out.println((int) c);
                left = charIndex[s.charAt(right)] + 1;
            }
            charIndex[s.charAt(right)] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abccba";
        LongestSubstringWithoutRepeating sol = new LongestSubstringWithoutRepeating();
        System.out.println(sol.lengthOfLongestSubstring(s));
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
            if (array[i] == 2) {
                System.out.println("  ");
                System.out.println("index when value is 2: " + i);
            }

        }
    }
}
