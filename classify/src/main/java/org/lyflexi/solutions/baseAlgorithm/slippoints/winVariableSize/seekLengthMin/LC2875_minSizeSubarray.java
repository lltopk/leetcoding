package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMin;

/**
 * 2875. 无限数组的最短子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的数组 nums 和一个整数 target 。
 *
 * 下标从 0 开始的数组 infinite_nums 是通过无限地将 nums 的元素追加到自己之后生成的。
 *
 * 请你从 infinite_nums 中找出满足 元素和 等于 target 的 最短 子数组，并返回该子数组的长度。如果不存在满足条件的子数组，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], target = 5
 * 输出：2
 * 解释：在这个例子中 infinite_nums = [1,2,3,1,2,3,1,2,...] 。
 * 区间 [1,2] 内的子数组的元素和等于 target = 5 ，且长度 length = 2 。
 * 可以证明，当元素和等于目标值 target = 5 时，2 是子数组的最短长度。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,2,3], target = 4
 * 输出：2
 * 解释：在这个例子中 infinite_nums = [1,1,1,2,3,1,1,1,2,3,1,1,...].
 * 区间 [4,5] 内的子数组的元素和等于 target = 4 ，且长度 length = 2 。
 * 可以证明，当元素和等于目标值 target = 4 时，2 是子数组的最短长度。
 * 示例 3：
 *
 * 输入：nums = [2,4,6,8], target = 3
 * 输出：-1
 * 解释：在这个例子中 infinite_nums = [2,4,6,8,2,4,6,8,...] 。
 * 可以证明，不存在元素和等于目标值 target = 3 的子数组。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= target <= 109
 */

/**
 * 不定尺寸滑动窗口求最小
 */
public class LC2875_minSizeSubarray {
    public int minSizeSubarray(int[] nums, int target) {
        //不定长滑动窗口求最小
        //由于target/sum是个定值, 因此(target/sum)*n是子数组和为target其中的定值长度部分
        // 接下来, 问题转化在两个连续的nums中, 求和为target%sum的子数组最小长度
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i<n; i++){
            sum+=nums[i];
        }
        int minLen = 2*n + 1;
        int l = 0;
        long helper = 0;
        for(int r =0; r< n*2; r++){
            helper+=nums[r%n];
            //窗口收缩条件
            while(helper > target%sum){
                helper-=nums[l%n];
                l++;
            }

            //由于是求最小, 这里要特判符合要求, 否则会计算错误
            if(helper == target%sum){
                minLen = Math.min(minLen, r - l + 1);
            }
        }

        return minLen == 2*n+1?-1: minLen + (int)(target/sum)*n;
    }
}
