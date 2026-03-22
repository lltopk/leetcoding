package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntJust;

/**
 * 992. K 个不同整数的子数组
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个正整数数组 nums和一个整数 k，返回 nums 中 「好子数组」 的数目。
 *
 * 如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
 *
 * 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
 * 子数组 是数组的 连续 部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,1,2,3], k = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 *
 * 输入：nums = [1,2,1,3,4], k = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 不定长恰好型滑动窗口, 两个滑动窗口相减longerLegal(nums, k) - longerLegal(nums, k+1);
 */
public class LC992_subarraysWithKDistinct {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int geCnt0 = longerLegal(nums, k);
        int geCnt1 = longerLegal(nums, k+1);
        return geCnt0 - geCnt1;
    }

    private int longerLegal(int[] nums, int target){
        int n = nums.length, l = 0, ans = 0;
        //用set会导致while循环过早退出, 原因在于你误以为set.remove(number)之后窗口内就不存在number了, 但实际窗口内元素种类可能依然满足k
        //根本点在于set无法代表窗口内的真实状况
        // Set<Integer> set = new HashSet<>();

        //正确的做法是用HashMap统计
        Map<Integer, Integer> dict = new HashMap<>();
        int cnt = 0;
        for(int r = 0; r< n; r++){
            dict.merge(nums[r], 1, Integer::sum);
            if(dict.get(nums[r]) ==1){
                cnt++;
            }
            while(cnt >=target){

                if(dict.merge(nums[l], -1, Integer::sum) == 0){
                    cnt--;
                }
                l++;
            }

            ans+=l;
        }
        return ans;
    }
}
