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
 * 写法三：一次遍历
 * 
 * 写法二告诉我们，栈顶出栈时，当前下标就是栈顶的 $right$。
 * 
 * 如果此刻能顺带求出栈顶的 $left$，那不就能一步到位，一次遍历就搞定了？
 * 
 * 想一想，栈顶的 $left$ 在哪？
 * 
 * 由于单调栈是底小顶大的，栈顶下面那个柱子的高度一定比栈顶小，所以栈顶下面的值就是 $left$。
 * 
 * 为简化代码逻辑，可以在一开始把 $-1$ 入栈，当作哨兵。当栈中只有一个数的时候，栈顶下面那个数刚好就是 $-1$，对应 $left[i]=-1$ 的情况。
 * 
 * 此外，循环结束的时候，栈中还有数据，这些数据也要计算矩形面积。处理这种情况可以再写一个循环，但更简单的办法是，往 $heights$ 的末尾加一个 $-1$（或者任意 $<= min(heights)$ 的数），从而保证循环结束的时候，栈一定是空的（不包括哨兵）。
 */
public class Hot073_LC84_largestRectangleArea4 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] st = new int[n + 1];
        int top = -1; // 栈顶下标
        st[++top] = -1; // 在栈中只有一个数的时候，栈顶的「下面那个数」是 -1，对应 left[i] = -1 的情况
        int ans = 0;
        for (int right = 0; right <= n; right++) {
            int h = right < n ? heights[right] : -1;
            while (top > 0 && heights[st[top]] >= h) {
                int i = st[top--]; // 矩形的高（的下标）
                int left = st[top]; // 栈顶下面那个数就是 left
                ans = Math.max(ans, heights[i] * (right - left - 1));
            }
            st[++top] = right;
        }
        return ans;
    }
}
