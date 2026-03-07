package org.lyflexi.solutions.baseAlgorithm.slippoints.tpOppositeDirection;

/**
 * @Author: ly
 * @Date: 2024/2/11 17:21
 */
/*
*
11. 盛最多水的容器：双指针

给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
返回容器可以储存的最大水量。
说明：你不能倾斜容器。
*
* 示例1：
*输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

* 示例 2：
输入：height = [1,1]
输出：1

*
* */

/**
 * 相向双指针
 */
public class LC11_MaxWaterArea {

    public int maxArea(int[] height) {
        int l = 0, r = height.length -1;
        int ans = 0; //求最大
        //相向双指针在趋近的过程中, 想要形成更大的面积, 则必须要移动短板
        while(l<r){
            int area = (r-l)* Math.min(height[r], height[l]);
            ans = Math.max(ans, area);
            if(height[l]>height[r]){
                r--;
            }else{//当height[l]==height[r]无所谓移动谁
                l++;
            }
        }

        return ans;
    }
}
