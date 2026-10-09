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
 * 写法一：三次遍历
 */
public class Hot073_LC84_largestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= h) {
                st.pop();
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        int[] right = new int[n];
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            int h = heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= h) {
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }
        return ans;
    }
}
