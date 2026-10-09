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
 * 方法二：相向双指针（一次遍历）
 * 
 * 设现在算出了前缀 $[0, left]$ 的最大高度 $preMax[left]$，以及后缀 $[right,n-1]$ 的最大高度 $sufMax[right]$。中间的柱子 $[left+1,right-1]$ 尚未遍历，不知道有多高。在这种情况下，我们能否直接确定 $left$ 或者 $right$ 处的接水量？
 * 
 * 分类讨论：
 * 
 * - 如果 $preMax[left] <= sufMax[right]$，由于 $sufMax[right]<= sufMax[left]$（包含的数越多，最大值越大），所以 $preMax[left] <= sufMax[right]<= sufMax[left]$，所以 $min(preMax[left],sufMax[left]) = preMax[left]$，$left$ 处的接水量就是 $preMax[left] - height[left]$。
 * - 如果 $preMax[left] >= sufMax[right]$，由于 $preMax[left]<= preMax[right]$（包含的数越多，最大值越大），所以 $sufMax[right] <= preMax[left]<= preMax[right]$，所以 $min(preMax[right],sufMax[right]) = sufMax[right]$，$right$ 处的接水量就是 $sufMax[right] - height[right]$。
 * 
 * 这意味着，在没有遍历完 $height$ 数组的情况下，也能算出接水量。这引出了如下相向双指针（一次遍历）做法。
 * 注：代码实现时，$while$ 循环可以不加等号。因为在「谁小移动谁」的规则下，相遇的位置一定是最高的柱子，这个柱子是无法接水的。
 */
public class Hot007_LC42_trap2 {
    public int trap(int[] height) {
        int ans = 0;
        int preMax = 0; // 前缀最大值，随着左指针 left 的移动而更新
        int sufMax = 0; // 后缀最大值，随着右指针 right 的移动而更新
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);
            if (preMax < sufMax) { // 可以确定 left 处的接水量
                ans += preMax - height[left];
                left++; // 搞定了 left，现在问题缩小到 [left+1, right]
            } else { // 可以确定 right 处的接水量
                ans += sufMax - height[right];
                right--; // 搞定了 right，现在问题缩小到 [left, right-1]
            }
        }

        return ans;
    }
}
