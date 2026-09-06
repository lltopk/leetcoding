package org.lyflexi.solutions.baseAlgorithm.utils.hash_map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
public class LC560_SubArrayOfSumK {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(scanner.nextLine());
        System.out.println(subarraySum(nums,k));
    }

    /**
     前缀和, 并且因为是两个变量用哈希, 枚举右维护左
     */
    public static int subarraySum(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int preS = 0;
        //存储某个前缀和(左)出现了多少次
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);//单独初始化前缀和[0, )本身就是解的情况, 注意不能是map.put(0, 0)
        for(int r = 0; r< n; r++){
            preS += nums[r];
            //s2 - s1 = k
            ans += map.getOrDefault(preS - k, 0);
            map.merge(preS, 1 ,Integer::sum);
        }
        return ans;
    }
}
