package org.lyflexi;

import java.util.*;

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
 * 示例 1：
 * 
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 
 * 示例 2：
 * 
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 
 * 示例 3：
 * 
 * 输入：nums = [1,0,1,2]
 * 输出：3
 * 
 * 提示：
 * 
 * - 0 <= nums.length <= 10^5
 * 
 * - -10^9 <= nums[i] <= 10^9
 */

/**
 * 小优化：设 $m$ 为 $nums$ 中的不同元素个数（即哈希集合的大小）。各个连续序列（链）是互相独立的，如果我们发现其中一条链的长度至少为 $\dfrac{m}{2}$（长度乘 $2$ 大于等于 $m$），由于不可能还有一条长度大于 $\dfrac{m}{2}$ 的链（否则这两条链的长度之和就超过 $m$ 了），答案不会再增大，此时可以直接返回答案。
 */
public class Hot003_LC128_longestConsecutive2 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num); // 把 nums 转成哈希集合
        }
        int m = st.size();

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
            if (ans * 2 >= m) {
                break;
            }
        }
        return ans;
    }
}
