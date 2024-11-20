package snake_dfs;

import java.util.*;

/**
 *
 */
public class LC90Subset2_too_complicated_notRecommended {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, int index, List<Integer> path) {
        //base case, index reach the last one + 1 = length
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            System.out.println("1. adding to result, path: " + path + ", because index = 3");
            return;
        }

        for (int i = index; i <= nums.length; i++) {
            System.out.println(" =======in recursion =>> index: " + index + ", i = " + i + ", path: " + path);
            if (i == nums.length) {
                res.add(new ArrayList<>(path));
                System.out.println("2. adding to result, path: " + path +", because i == 3");
                return;
            }
            if (i != index && nums[i] == nums[i - 1]) { //i!=index
                System.out.println("***continue case, index: " + index + ", i: " + i);
                continue;
            }
            // add    
            path.add(nums[i]);
            System.out.println("added, path becomes: " + path + ", nums[i]: " + nums[i] + ", index: " + index + ", i: " + i );
            dfs(res, nums, i + 1, path);
            //back tracking
            path.remove(path.size() - 1);
            // not add
            System.out.println("NOT adding to path: " + path + ", nums[i]: " + nums[i] + ", index: " + index + ", i: " + i );
//            System.out.println("path: " + path);
//            dfs(res, nums, i + 1, path);
        }
    }

    public static void main(String[] args) {
        LC90Subset2_too_complicated_notRecommended test = new LC90Subset2_too_complicated_notRecommended();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> res = test.subsetsWithDup(nums);
        //System.out.println("num 3:  " + nums[3] + "num 2: " + nums[2]); java.lang.ArrayIndexOutOfBoundsException
        System.out.println("result: " + res);

    }
}
