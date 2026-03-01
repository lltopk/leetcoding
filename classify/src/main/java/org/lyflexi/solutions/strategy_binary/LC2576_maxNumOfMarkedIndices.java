package org.lyflexi.solutions.strategy_binary;

/**
 * 2576. 求出最多标记下标
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 。
 *
 * 一开始，所有下标都没有被标记。你可以执行以下操作任意次：
 *
 * 选择两个 互不相同且未标记 的下标 i 和 j ，满足 2 * nums[i] <= nums[j] ，标记下标 i 和 j 。
 * 请你执行上述操作任意次，返回 nums 中最多可以标记的下标数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,5,2,4]
 * 输出：2
 * 解释：第一次操作中，选择 i = 2 和 j = 1 ，操作可以执行的原因是 2 * nums[2] <= nums[1] ，标记下标 2 和 1 。
 * 没有其他更多可执行的操作，所以答案为 2 。
 * 示例 2：
 *
 * 输入：nums = [9,2,5,4]
 * 输出：4
 * 解释：第一次操作中，选择 i = 3 和 j = 0 ，操作可以执行的原因是 2 * nums[3] <= nums[0] ，标记下标 3 和 0 。
 * 第二次操作中，选择 i = 1 和 j = 2 ，操作可以执行的原因是 2 * nums[1] <= nums[2] ，标记下标 1 和 2 。
 * 没有其他更多可执行的操作，所以答案为 4 。
 * 示例 3：
 *
 * 输入：nums = [7,6,8]
 * 输出：0
 * 解释：没有任何可以执行的操作，所以答案为 0 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */

import java.util.Arrays;

/**
 * 二分答案
 *
 * 破题点: 对nums从小到大排序后，如果存在 k 对匹配，那么一定可以让最小的 k 个数与最大的 k 个数匹配。
 * 反证法：(否后推否前)
 * 如果最小的 k 个数无法和最大的 k 个数匹配，则任意 k 对都无法匹配
 *
 */
public class LC2576_maxNumOfMarkedIndices {
    //i , i + n - k
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums.length/2 + 1;//开区间写法, r向上取整
        while(l + 1 < r){
            int mid = l + ((r-l)>>1);
            if(checkInc(mid, nums)){
                l = mid;
            }else{
                r = mid;
            }
        }
        return l*2;
    }

    private boolean checkInc(int mid, int[] nums){
        int n = nums.length;
        //期望的是0~mid都满足2 * nums[i] <= nums[i+ n-mid]
        for(int i = 0; i< mid; i++){
            //但凡有不满足的, 就降低二分答案
            if(2 * nums[i] > nums[i+ n-mid]){
                return false;
            }
        }
        //等号给到l
        return true;
    }
}
