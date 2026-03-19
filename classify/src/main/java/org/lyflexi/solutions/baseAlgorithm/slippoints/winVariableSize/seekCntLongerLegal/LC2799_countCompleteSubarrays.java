package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntLongerLegal;

/**
 * 2799. 统计完全子数组的数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个由 正 整数组成的数组 nums 。
 *
 * 如果数组中的某个子数组满足下述条件，则称之为 完全子数组 ：
 *
 * 子数组中 不同 元素的数目等于整个数组不同元素的数目。
 * 返回数组中 完全子数组 的数目。
 *
 * 子数组 是数组中的一个连续非空序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,1,2,2]
 * 输出：4
 * 解释：完全子数组有：[1,3,1,2]、[1,3,1,2,2]、[3,1,2] 和 [3,1,2,2] 。
 * 示例 2：
 *
 * 输入：nums = [5,5,5,5]
 * 输出：10
 * 解释：数组仅由整数 5 组成，所以任意子数组都满足完全子数组的条件。子数组的总数为 10 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2000
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 不定长滑动窗口求个数, 越长越合法
 */
public class LC2799_countCompleteSubarrays {
    public int countCompleteSubarrays(int[] nums) {
        //非固定尺寸滑动窗口求个数， 越长越合法
        int n = nums.length, l = 0, ans = 0;
        //整个数组不同元素的个数
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            set.add(nums[i]);
        }
        int k = set.size();

        Map<Integer, Integer> dict = new HashMap<>();
        for(int r = 0; r < n; r++){
            dict.merge(nums[r], 1 ,Integer::sum);
            while(dict.size() == k){

                // if(dict.get(nums[l]) == 1){
                //     dict.remove(nums[l]);
                // }else{
                //     dict.merge(nums[l], -1, Integer::sum);
                // }

                //返回--后nums[l]的频次结果， 如果等于0了
                if(dict.merge(nums[l], -1, Integer::sum) == 0){
                    dict.remove(nums[l]);
                }
                l++;
            }

            ans +=l;
        }
        return ans;
    }
}
