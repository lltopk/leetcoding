package org.lyflexi.solutions.baseAlgorithm.enum_skill;

/**
 * 485. 最大连续 1 的个数
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * 示例 2:
 *
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1.
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 324,145/522.2K
 * 通过率
 * 62.1%
 */

/**
 *
 */
public class LC485_findMaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0, cnt = 0;
        for(int v: nums){
            if(v == 0){
                cnt = 0;
            }else{
                cnt++;
                ret = Math.max(ret, cnt);
            }
        }
        return ret;
    }
}
