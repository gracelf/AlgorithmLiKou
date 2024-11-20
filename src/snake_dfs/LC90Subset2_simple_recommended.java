package snake_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class LC90Subset2_simple_recommended {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        dfs(res, path, nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int idx) {
        System.out.println("path to be add: " + path + ", index: " + idx);
        res.add(new ArrayList<>(path));

        for (int i = idx; i < nums.length; i++) {
            if (i != idx && nums[i] == nums[i - 1]) {
                continue; // skip
            }
            path.add(nums[i]);
            dfs(res, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC90Subset2_simple_recommended test = new LC90Subset2_simple_recommended();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> res = test.subsetsWithDup(nums);
        System.out.println("result: " + res);
    }

}
