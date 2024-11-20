package snake_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC78Subset_simple {

    public List<List<Integer>> subset(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, new ArrayList<>(), 0);
        return res;
    }

    //simple stardard method
    private void dfs(int[] nums, List<List<Integer>> res, ArrayList<Integer> path, int index) {
        //base case
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
        }

        if (index < nums.length) {//logic: binary, add or not add
            path.add(nums[index]);
            dfs(nums, res, path, index + 1);
            path.remove(path.size() - 1);
            dfs(nums, res, path, index + 1);
        }
    }

    public static void main(String[] args) {
        LC78Subset_simple test = new LC78Subset_simple();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = test.subset(nums);
        System.out.println("subset method 1 result: " + res);
        List<List<Integer>> res2 = test.subset2(nums);
        System.out.println(" subset method 2 result: " + res2);
    }

    //recommended, will extend base on this method to deal with duplicate
    public List<List<Integer>> subset2(int[] nums) {
        List<List<Integer>> res2 = new ArrayList<>();
        dfs2(nums, res2, new ArrayList<>(), 0);
        return res2;
    }

    private void dfs2(int[] nums, List<List<Integer>> res, ArrayList<Integer> path, int index) {
        System.out.println("path to be add: " + path + ", index: " + index);
        //when using index and i for the iteration, always add to result
        res.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs2(nums, res, path, i + 1);
            path.remove(path.size() - 1);//after this, no need for another dfs, path will be add to result
        }
    }

}
