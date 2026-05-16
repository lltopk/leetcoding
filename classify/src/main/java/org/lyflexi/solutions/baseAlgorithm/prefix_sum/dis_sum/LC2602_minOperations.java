package org.lyflexi.solutions.baseAlgorithm.prefix_sum.dis_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2602. 使数组元素全部相等的最少操作次数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个正整数数组 nums 。
 *
 * 同时给你一个长度为 m 的整数数组 queries 。第 i 个查询中，你需要将 nums 中所有元素变成 queries[i] 。你可以执行以下操作 任意 次：
 *
 * 将数组里一个元素 增大 或者 减小 1 。
 * 请你返回一个长度为 m 的数组 answer ，其中 answer[i]是将 nums 中所有元素变成 queries[i] 的 最少 操作次数。
 *
 * 注意，每次查询后，数组变回最开始的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,1,6,8], queries = [1,5]
 * 输出：[14,10]
 * 解释：第一个查询，我们可以执行以下操作：
 * - 将 nums[0] 减小 2 次，nums = [1,1,6,8] 。
 * - 将 nums[2] 减小 5 次，nums = [1,1,1,8] 。
 * - 将 nums[3] 减小 7 次，nums = [1,1,1,1] 。
 * 第一个查询的总操作次数为 2 + 5 + 7 = 14 。
 * 第二个查询，我们可以执行以下操作：
 * - 将 nums[0] 增大 2 次，nums = [5,1,6,8] 。
 * - 将 nums[1] 增大 4 次，nums = [5,5,6,8] 。
 * - 将 nums[2] 减小 1 次，nums = [5,5,5,8] 。
 * - 将 nums[3] 减小 3 次，nums = [5,5,5,5] 。
 * 第二个查询的总操作次数为 2 + 4 + 1 + 3 = 10 。
 * 示例 2：
 *
 * 输入：nums = [2,9,6,3], queries = [10]
 * 输出：[20]
 * 解释：我们可以将数组中所有元素都增大到 10 ，总操作次数为 8 + 1 + 4 + 7 = 20 。
 *
 *
 * 提示：
 *
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 105
 * 1 <= nums[i], queries[i] <= 109
 */

/**
 * 用前缀和解决距离和问题
 *
 * 前缀和 + 排序二分.  分组计算
 */
public class LC2602_minOperations {
    public List<Long> minOperations(int[] nums, int[] queries) {
        //排序求前缀和,  因为小于target的距离计算方式一致, 大于target的距离计算方式也一致
        Arrays.sort(nums);
        int n = nums.length, k = queries.length;
        long[] preS = new long[n+1];
        for(int i = 0; i<n; i++){
            preS[i+1] = preS[i] + nums[i];
        }
        List<Long> ans = new ArrayList<>();
        for(int i = 0; i<k; i++){
            int target = queries[i];
            //这个j很妙, 二分默认求大于等于target的最小值, 用j表示个数
            // 若未找到, 则j恰好代表小于的个数.  若找到, dis(nums[j], target)==0, 不影响答案
            int j = lowerBound(nums, target);
            //小于target的距离计算方式一致
            long sum1 = (long)j*target - preS[j];
            //大于target的距离计算方式也一致
            long sum2 = preS[n] - preS[j] - (long)(n-j)*target;
            ans.add(sum1+sum2);
        }
        return ans;
    }

    private int lowerBound(int[] nums, int target){
        int l = 0, r = nums.length;
        while(l< r){
            int mid = l + ((r-l)>>1);
            if(nums[mid] < target){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}
