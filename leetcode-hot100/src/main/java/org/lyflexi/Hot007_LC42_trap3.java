package org.lyflexi;

import java.util.*;

/**
 * 42. 接雨水
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 示例 1：
 * 
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 
 * 示例 2：
 * 
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 
 * 提示：
 * 
 * - n == height.length
 * 
 * - 1 <= n <= 2 * 10^4
 * 
 * - 0 <= height[i] <= 10^5
 */

/**
 * 方法三：单调栈
 * 
 * 请看 单调栈【基础算法精讲 26】。
 * 
 * 上面的方法相当于「竖着」计算面积，单调栈的做法相当于「横着」计算面积。
 * 
 * 这个方法可以总结成 $16$ 个字：找上一个更大元素，在找的过程中填坑。
 * 
 * 注意 $while$ 中加了等号，这可以让栈中没有重复元素，从而在有很多重复元素的情况下，使用更少的空间。
 * 点评：看复杂度的话，单调栈不如双指针的做法。但如果输入的 $height$ 是一个流（stream），只能从左到右遍历，那么单调栈（在这种场景下）就是不错的方法了。
 */
public class Hot007_LC42_trap3 {
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            while (!st.isEmpty() && height[st.peek()] <= h) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int left = st.peek();
                int dh = Math.min(height[left], height[i]) - bottomH; // 面积的高
                ans += dh * (i - left - 1);
            }
            st.push(i);
        }
        return ans;
    }
}
