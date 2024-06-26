

package snake_dfs;

import java.util.*;

/**
 *
 */
public class LC49GroupAnagram {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        // c.c.
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // sort a string
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            
            String key = String.valueOf(charArray);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            } 
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

}
