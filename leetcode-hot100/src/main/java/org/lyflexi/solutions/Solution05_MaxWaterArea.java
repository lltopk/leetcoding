package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.Scanner;
/*11. 盛最多水的容器：解法：相向双指针
* 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
返回容器可以储存的最大水量。
*
* 示例 1
* 输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
*
示例 2：
输入：height = [1,1]
输出：1
*
* */
/**
 * @Author: ly
 * @Date: 2024/1/13 11:36
 */
public class Solution05_MaxWaterArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(maxArea(array));
    }
    public static int maxArea(int[] height) {
        int n = height.length;
        //双指针
        int left = 0;
        int right = n-1;
        int answer = 0;
        while(left<right){
            int innerArae = Math.min(height[left],height[right])*(right-left);
            answer = Math.max(answer,innerArae);

            //直觉告诉我们，移动高度较小的指针
            //因为移动指针的后果是，1.区域宽度必定减小，2.移动高度较小的指针有可能遇到更高的高度，整体的结果才有可能增大
            if (height[left]<=height[right]){
                left++;
            }else{
                right--;
            }
        }

        return answer;
    }
}
