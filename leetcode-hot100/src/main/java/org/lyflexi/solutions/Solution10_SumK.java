package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/20 10:27
 */
/*给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。

示例 1：
输入：nums = [1,1,1], k = 2
输出：2

示例 2：
输入：nums = [1,2,3], k = 3
输出：2
 */


/*解法：前缀和+HashMap
代码与两数之和很像，只是我们把key抽象为每一个以i结尾的前缀和

计算了第一个位置到第i个位置的和只是得到了i处的前缀和，现在假如你计算到i的前缀和是a，后面某个i+j处的前缀和是a+k，那么中间这j个数的和就是k，从而答案+1，

所以中间和为k的这j个数根本不用算！！！

在每次求完当前位置前缀和的时候，都判断一下之前有没有前缀和等于当前前缀和减掉k，就能确定有没有一段夹在中间的子数组和为k，从而解决你的问题*/
public class Solution10_SumK {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(scanner.nextLine());
        System.out.println(subarraySum(nums,k));
    }

    public static int subarraySum(int[] nums, int k) {
        int answer = 0;
        int n = nums.length;
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //考虑当前前缀正好等于k的情况
        map.put(0,1);
        for (int i = 0; i < n; i++) {
            preSum+=nums[i];

            if (map.containsKey(preSum-k)){
                //map的keySet里面只有preSum和preSum-k，没有k
                answer+=map.get(preSum-k);
            }

            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }

        return answer;
    }
}
