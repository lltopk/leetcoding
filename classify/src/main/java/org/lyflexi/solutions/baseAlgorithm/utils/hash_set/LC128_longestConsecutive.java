package org.lyflexi.solutions.baseAlgorithm.utils.hash_set;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 示例 3：
 *
 * 输入：nums = [1,0,1,2]
 * 输出：3
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,712,568/3.5M
 * 通过率
 * 48.7%
 */
public class LC128_longestConsecutive {
    /**
     * 遍历元素的时候，要遍历哈希集合，而不是 nums, 避免超时
     *
     * 如果 nums=[1,1,1,…,1,2,3,4,5,…]（前一半都是 1），遍历 nums 的做法会导致每个 1 都跑一个 O(n) 的循环，总的循环次数是 O(n^2)，会超时。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num); // 把 nums 转成哈希集合
        }
        int m = st.size(), ret = 0;
        for(int x: st){
            if (st.contains(x - 1)) { // 如果 x 不是序列的起点，直接跳过
                continue;
            }
            // x 是序列的起点
            int y = x + 1;
            while (st.contains(y)) { // 不断查找下一个数是否在哈希集合中
                y++;
            }
            // 循环结束后，y-1 是最后一个在哈希集合中的数
            ret = Math.max(ret, y - x); // 从 x 到 y-1 一共 y-x 个数
            if (ret * 2 >= m) {//独立连续子序列长度如果大于m/2, 则必定是最长的, 不可能还有另外长度的大于m/2
                break;
            }
        }
        return ret;
    }
}
