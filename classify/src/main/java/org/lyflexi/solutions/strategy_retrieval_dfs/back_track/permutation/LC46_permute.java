package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,741,697/2.2M
 * 通过率
 * 80.2%
 */

/**
 * 全排列的定义就是每层必须要选， 所以不能用选与不选（输入视角）
 *
 * 只能使用枚举思路（答案视角）
 */
public class LC46_permute {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int[] visit = new int[nums.length];
        dfs(nums, new ArrayList<>(), visit, 0);
        return ret;
    }


    private void dfs(int[] nums, List<Integer> path, int[] visit, int i){
        if (i == nums.length){
            ret.add(new ArrayList<>(path));
            return;
        }
        //在下面的循环中
        //i代表层数：当前递归层， 直到层深度等于nums.length
        //j代表当前层中剩余可选的数字范围：假设在i层三种可能性分别是(1,2,3)，那么i+1层的可能性是(2,3) (1,3) (1,2), 那么i+2层的可能性是(3) (2) (3) (1) (2) (1) 若n=3的全排列就到底了
        //根据上述例子， 因此虽然j可以从头开始重新选， 但都需要跳过上一层的自己
        for(int j = 0; j<nums.length; j++){
            if(visit[j] == 0){
                path.add(nums[j]);
                visit[j] = 1;//布尔数组标记， 保证下面层数从头再选一轮的时候跳过上一轮的自己
                dfs(nums, path, visit, i + 1);
                path.remove(path.size() - 1);
                visit[j] = 0;
            }
        }
    }
}
