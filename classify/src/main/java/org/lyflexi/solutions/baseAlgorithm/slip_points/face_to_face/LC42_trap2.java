package org.lyflexi.solutions.baseAlgorithm.slip_points.face_to_face;

/**
 * 42. 接雨水
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */

/**
 * 一个循环中, 同时计算前缀最大值数组  后缀最大值数组
 */
public class LC42_trap2 {
    public int trap(int[] height) {
        int n = height.length;
        int[] preMax = new int[n]; // preMax[i] 表示从 height[0] 到 height[i] 的最大值
        preMax[0] = height[0];
        int[] sufMax = new int[n]; // sufMax[i] 表示从 height[i] 到 height[n-1] 的最大值
        sufMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], height[i]);
            sufMax[n - i - 1] = Math.max(sufMax[n - i], height[n - i - 1]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(preMax[i], sufMax[i]) - height[i]; // 累加每个水桶能接多少水
        }
        return ans;
    }
}
