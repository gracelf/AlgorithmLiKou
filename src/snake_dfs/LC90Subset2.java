package snake_dfs;

import java.util.*;

/**
 *
 */
public class LC90Subset2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, int index, List<Integer> path) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            System.out.println("1. adding to result, path: " + path);
            return;
        }

        for (int i = index; i <= nums.length; i++) {
            System.out.println("!!index: " + index +  ", i = " + i + ", result path: " + path);
            if (i == nums.length) {
                res.add(new ArrayList<>(path));
                System.out.println("2. adding to result, path: " + path);
                return;
            }
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            // add    
            path.add(nums[i]);
            System.out.println("adding to path: " + path + ", i: " + i + ", index: " + index);
            dfs(res, nums, i + 1, path);
            path.remove(path.size() - 1);
            // not add
//            System.out.println("path: " + path);
//            dfs(res, nums, i + 1, path);
        }
    }
    
    public static void main(String[] args){
        LC90Subset2 test = new LC90Subset2();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> res= test.subsetsWithDup(nums);
        System.out.println("result: " + res);
    
    }
}
