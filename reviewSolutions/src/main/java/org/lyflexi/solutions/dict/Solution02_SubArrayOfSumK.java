package org.lyflexi.solutions.dict;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/20 10:27
 */
/*
560. 和为 K 的子数组
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
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

计算了第一个位置到第i个位置的和只是得到了i处的前缀和a，后面某个i+j处的前缀和是a+k，那么中间这j个数的和就是k，从而答案+1，

所以中间和为k的这j个数根本不用算！！！
*/
public class Solution02_SubArrayOfSumK {

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
        //以前缀和为键,出现次数为值
        HashMap<Integer, Integer> map = new HashMap<>();
        //若preSum-k=0则value应赋值为1，比如111求和为k的子数组个数答案是2
        map.put(0,1);
        for (int i = 0; i < n; i++) {
            preSum+=nums[i];

            if (map.containsKey(preSum-k)){
                //遍历到当前索引位置，和为key的子数组的个数
                answer+=map.get(preSum-k);
            }

            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }

        return answer;
    }
}
