package org.lyflexi;

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
 * 1,761,048/3.6M
 * 通过率
 * 48.7%
 */

/**
 * HashSet: 把 nums 中的数都放入一个哈希集合中，这样可以 O(1) 判断数字是否在 nums 中。
 *
 * 如果 x−1 在哈希集合中，则不以 x 为起点。为什么？因为以 x−1 为起点计算出的序列长度，一定比以 x 为起点计算出的序列长度要长！这样可以避免大量重复计算。
 * 比如 nums=[3,2,4,5]，从 3 开始，我们可以找到 3,4,5 这个连续序列；而从 2 开始，我们可以找到 2,3,4,5 这个连续序列，一定比从 3 开始的序列更长。
 *
 */
public class Hot003_LC128_longestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num); // 把 nums 转成哈希集合
        }

        int ans = 0;
        for (int x : st) { // 遍历哈希集合
            if (st.contains(x - 1)) { // 如果 x 不是序列的起点，直接跳过
                continue;
            }
            // x 是序列的起点
            int y = x + 1;
            while (st.contains(y)) { // 不断查找下一个数是否在哈希集合中
                y++;
            }
            // 循环结束后，y-1 是最后一个在哈希集合中的数
            ans = Math.max(ans, y - x); // 从 x 到 y-1 一共 y-x 个数
        }
        return ans;
    }
}
