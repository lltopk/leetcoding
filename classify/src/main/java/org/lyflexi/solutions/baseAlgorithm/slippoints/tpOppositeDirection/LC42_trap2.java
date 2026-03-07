package org.lyflexi.solutions.baseAlgorithm.slippoints.tpOppositeDirection;

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
 * 前缀最大值数组
 * 后缀最大值数组
 *
 * 空间优化. 只计算当前前缀最大值和后缀最大值, 而不存储前缀最大值项和后缀最大值项
 */
public class LC42_trap2 {
    public int trap(int[] height) {
        //以height =   [0,1,0,2,1,0,1,3,2,1,2,1]为例
        //前缀最大值数组=[0,1,1,2,2,2,2,3,3,3,3,3]
        //后缀最大值数组=[3,3,3,3,3,3,3,3,2,2,2,1]
        //优化. 只计算当前前缀最大值和后缀最大值, 而不存储前缀最大值项和后缀最大值项
        int n = height.length;
        int l = 0, r = n -1;
        int preMax = 0;//求最大
        int sufMax = 0;//求最大
        int ans = 0;
        //l==r的时候, 也有可能要接水
        while(l<=r){
            preMax = Math.max(preMax, height[l]);
            sufMax = Math.max(sufMax, height[r]);
            if(preMax < sufMax){
                ans += (preMax-height[l])*1;
                l++;
            }else{//preMax = sufMax, 先移动l或r都可以
                ans +=(sufMax -height[r])*1;
                r--;
            }
        }

        return ans;
    }
}
