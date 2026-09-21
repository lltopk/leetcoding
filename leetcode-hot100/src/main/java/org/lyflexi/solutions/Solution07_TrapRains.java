package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/15 11:39
 */

/*
42. 接雨水:
* 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
*
* 示例1：
* 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
*
*
示例 2：
输入：height = [4,2,0,3,2,5]
输出：9
*
* */
public class Solution07_TrapRains {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
    System.out.println(trap(array));
    }

    //    解法二，采取空间换时间，将时间复杂度降为on，空间复杂度也提升为on
    //每次只算每一格子的蓄水量为，【（前缀最大高度-后缀最大高度）的最小值-当前柱子高度】*1
    public static int trap(int[] height) {
        int n = height.length;
        int answer = 0;
        //leftMaxHeights数组和height数组的下标是对应关系
        int[] leftMaxHeights = new int[n];

        //rightMaxHeights数组和height数组的下标也是对应关系
        int[] rightMaxHeights = new int[n];

        //最左边的柱子没有leftMaxHeight，但是要把它赋值为height[0],不然前缀序列会启动错误
        leftMaxHeights[0] = height[0];
        //找前缀序列，从前往后找leftMaxHeights，
        for (int i = 1; i <n; i++) {
            //leftMaxHeights[i]的前缀的最大值leftMaxHeights[i-1]，与当前height[i]作比较
            leftMaxHeights[i] = Math.max(leftMaxHeights[i-1], height[i]);
        }

        //最右边的柱子没有rightMaxHeight，但是要把它赋值为height[n-1]，不然后缀序列会启动错误
        rightMaxHeights[n-1] = height[n-1];
        //找后缀序列，从后往前找rightMaxHeights
        for (int i = n-2; i >=0; i--) {
            //rightMaxHeights[i]的后缀的最大值rightMaxHeights[i+1]，与当前height[i]做比较
            rightMaxHeights[i] = Math.max(rightMaxHeights[i+1], height[i]);
        }

        for (int i = 0; i < n; i++) {
            int maxHeight = Math.min(leftMaxHeights[i], rightMaxHeights[i]);
            if (maxHeight > height[i]) {
                answer += maxHeight-height[i];

            }
        }
        return answer;
    }


    //public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
//    System.out.println(trap(array));
//}
//    解法一，时间复杂度为on2，空间复杂度为o1
//    public static int trap(int[] height) {
//        int n = height.length;
//        int answer = 0;
//        //第一个柱子，和最后一个柱子是不会接水的，所以下标从 1 到 length - 2
//        for (int i = 1; i < n-1; i++) {
//            //这两高度不能声明为全局的,一旦声明为全局的就不具备普适性了
//            int leftMaxHeight = height[0];
//            //这两高度不能声明为全局的,一旦声明为全局的就不具备普适性了
//            int rightMaxHeight = height[n-1];
//            for (int j = i-1; j >=0 ; j--) {
//                leftMaxHeight = Math.max(leftMaxHeight,height[j]);
//            }
//            for (int j = i+1 ;j <n ; j++) {
//                rightMaxHeight = Math.max(rightMaxHeight,height[j]);
//            }
//
//            int minHeight = Math.min(leftMaxHeight,rightMaxHeight);
//            //只有Math.min(leftMaxHeight,rightMaxHeight)>height[i],当前的柱子才能接到水
//            if(minHeight>height[i]){
//                int innerAnswer = minHeight-height[i];
//                answer = answer+innerAnswer;
//            }
//            //Math.min(leftMaxHeight,rightMaxHeight)<height[i]，当前的柱子接不到水
//            //Math.min(leftMaxHeight,rightMaxHeight)==height[i]，当前的柱子接不到水
//        }
//
//
//        return answer;
//    }

}

