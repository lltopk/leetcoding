package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntLongerLegal;

/**
 * 2537. 统计好子数组的数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中 好 子数组的数目。
 *
 * 一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j] ，那么称它是一个 好 子数组。
 *
 * 子数组 是原数组中一段连续 非空 的元素序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], k = 10
 * 输出：1
 * 解释：唯一的好子数组是这个数组本身。
 * 示例 2：
 *
 * 输入：nums = [3,1,4,3,2,2,4], k = 2
 * 输出：4
 * 解释：总共有 4 个不同的好子数组：
 * - [3,1,4,3,2,2] 有 2 对。
 * - [3,1,4,3,2,2,4] 有 3 对。
 * - [1,4,3,2,2,4] 有 2 对。
 * - [4,3,2,2,4] 有 2 对。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 109
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 非固定尺寸滑动窗口求个数, 越长越合法
 */
public class LC2537_countGood {
    //不定长滑动窗口求个数， 越长越合法
    public long countGood(int[] nums, int k) {
        int n = nums.length, l = 0;
        long ans = 0;
        Map<Integer, Integer> dict = new HashMap<>();
        int helper = 0;//记录总对数

        for(int r  = 0; r<n ;r++){
            //思路1： 当前窗口内x有c个， 那么当x进入窗口， 贡献c个总对数
            helper+=dict.getOrDefault(nums[r], 0);

            dict.merge(nums[r], 1, Integer::sum);
            while(helper >=k ){
                //思路2： 当前窗口内x有c个， 那么当x移出窗口， 损失c-1个总对数
                helper-=dict.get(nums[l])-1;

                if(dict.merge(nums[l], -1, Integer::sum) == 0){
                    dict.remove(nums[l]);
                }
                l++;
            }
            ans+=l;
        }
        return ans;
    }

    //超时了
    // private boolean shrink(Map<Integer, Integer> dict, int k ){
    //     int cnt = 0;
    //     for(Integer key: dict.keySet()){
    //         //v最小是1， 因为当v是0的时候已经被移除了
    //         int v = dict.get(key);
    //         if(v == 1){
    //             continue;
    //         }
    //         //2: 1
    //         //3: 2+1
    //         //4: 3+2+1
    //         //5: 4+3+2+1
    //         //v: (1+v-1)*(v-1)/2 = v*(v-1)/2
    //         cnt+=v*(v-1)/2;
    //     }

    //     return cnt>=k;
    // }
}
