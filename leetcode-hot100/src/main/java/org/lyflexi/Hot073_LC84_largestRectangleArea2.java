package org.lyflexi;

import java.util.*;

/**
 * 84. 柱状图中最大的矩形
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 
 * 示例 1:
 * 
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 
 * 示例 2：
 * 
 * 输入： heights = [2,4]
 * 输出： 4
 * 
 * 提示：
 * 
 * - 1 <= heights.length <=10^5
 * 
 * - 0 <= heights[i] <= 10^4
 */

/**
 * 写法二：两次遍历
 * 
 * 为了做到两次遍历，以及写法三的一次遍历，首先，把 $right[i]$ 的定义略作修改，调整为：在 $i$ 右侧的小于或等于 $h=heights[i]$ 的最近元素的下标。
 * 
 * 如果 $heights$ 中没有相同的元素，这样修改不影响 $right[i]$。
 * 
 * 如果 $heights$ 中有相同的元素呢？比如 $heights=[1,3,4,3,2]$，左边那个 $3$ 的 $right[i]$ 会变小，导致矩形面积变小，这是否会导致计算错误？
 * 
 * 不会。注意在这种情况下，这两个高为 $3$ 的柱子，对应的矩形面积（在写法一中）是一样大的，虽然（在写法二中）左边那个 $3$ 的矩形面积变小了，但右边那个 $3$ 的矩形面积是不变的，所以我们不会错过正确答案。
 * 
 * 修改 $right[i]$ 的定义后，我们可以把 $left$ 和 $right$ 合在一起计算，从而减少一次遍历：
 * 
 * - 在计算 $left$ 的过程中，如果栈顶元素 $>=heights[i]$，那么 $i$ 就是栈顶元素的 $right$。
 */
public class Hot073_LC84_largestRectangleArea2 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= h) {
                right[st.pop()] = i;
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }
        return ans;
    }
}
