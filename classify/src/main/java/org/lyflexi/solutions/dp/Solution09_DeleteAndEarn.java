package org.lyflexi.solutions.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/6 10:37
 */


/*740. 删除并获得点数

给你一个整数数组 nums ，你可以对它进行一些操作。
每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

示例 1：
输入：nums = [3,4,2]
输出：6
解释：
删除 4 获得 4 个点数，因此 3 也被删除。
之后，删除 2 获得 2 个点数。总共获得 6 个点数。
*
*
示例 2：
输入：nums = [2,2,3,3,3,4]
输出：9
解释：
删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
* */
class Solution09_DeleteAndEarn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(deleteAndEarn(array));

    }
    public static int deleteAndEarn(int[] nums) {
        int[] statisticArray = structArray(nums);
        return rob(statisticArray);
    }


    /** 此题将数组转化为，以0~maxItem为下标，Item的个数为新Item的新数组，对新数组应用打家劫舍
     * 最后注意一点，新的dp公式要时刻乘以新索引：f(x) = Math.max(f(x-2)+新muns[x-2]*(x-2),f(x-1))
     * */

    private static int[] structArray(int[] nums){//以0~maxItem为新下标，Item的个数为新Item，构造新数组

        int maxItem = Arrays.stream(nums).max().orElse(0);
        int[] statisticArray = new int[maxItem+1];

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            statisticArray[val]++;
        }

        return statisticArray;
    }

    private static int rob(int[] nums){
        int n = nums.length;

        if(n==1){
            return nums[0];
        }
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0],nums[1]);

        //数学公式//f(x) = Math.max(f(x-2)+nums[x]*(x),f(x-1))
        for (int i = 3; i < n+1; i++) {
            //代码公式
            dp[i] = Math.max(dp[i-2]+(i-1)*nums[i-1], dp[i-1]);
        }

        return dp[n];
    }
}
