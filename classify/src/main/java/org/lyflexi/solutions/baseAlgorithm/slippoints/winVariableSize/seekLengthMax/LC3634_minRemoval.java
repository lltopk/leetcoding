package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMax;

/**
 * 3634. 使数组平衡的最少移除数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 k。
 *
 * 如果一个数组的 最大 元素的值 至多 是其 最小 元素的 k 倍，则该数组被称为是 平衡 的。
 *
 * 你可以从 nums 中移除 任意 数量的元素，但不能使其变为 空 数组。
 *
 * 返回为了使剩余数组平衡，需要移除的元素的 最小 数量。
 *
 * 注意：大小为 1 的数组被认为是平衡的，因为其最大值和最小值相等，且条件总是成立。
 *
 *
 *
 * 示例 1:
 *
 * 输入：nums = [2,1,5], k = 2
 *
 * 输出：1
 *
 * 解释：
 *
 * 移除 nums[2] = 5 得到 nums = [2, 1]。
 * 现在 max = 2, min = 1，且 max <= min * k，因为 2 <= 1 * 2。因此，答案是 1。
 * 示例 2:
 *
 * 输入：nums = [1,6,2,9], k = 3
 *
 * 输出：2
 *
 * 解释：
 *
 * 移除 nums[0] = 1 和 nums[3] = 9 得到 nums = [6, 2]。
 * 现在 max = 6, min = 2，且 max <= min * k，因为 6 <= 2 * 3。因此，答案是 2。
 * 示例 3:
 *
 * 输入：nums = [4,6], k = 2
 *
 * 输出：0
 *
 * 解释：
 *
 * 由于 nums 已经平衡，因为 6 <= 4 * 2，所以不需要移除任何元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 105
 */

import java.util.Arrays;

/**
 * 不定长滑动窗口求最大
 */
public class LC3634_minRemoval {
    //不定长度的滑动窗口求长度， 求最小舍弃的长度
    //but 发现代码模板逻辑不成立
    //so 正难则反， 求最大保留的长度maxRemained，最终答案为n-maxRemained
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxRemained = 0;
        int l = 0;
        for(int i = 0; i<n; i++){
            //数组已经排过序， 所以直接和nums[l]比较即可
            while(nums[i] > (long)nums[l]*k){
                l++;
            }
            //求得maxRemained, 最后舍弃n-maxRemained, 因此每个当前窗口不用考虑后面的i
            maxRemained = Math.max(maxRemained, i - l + 1);
        }

        return n - maxRemained;

    }
}
