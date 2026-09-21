package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/10 9:54
 */

/*
* 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1：
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
*
示例 2：
输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9*/
public class Solution03_LongestConsecutive {
//    2024.1.10
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(longestConsecutive(array));
    }

    public static int longestConsecutive(int[] nums) {
        //用于o1的哈希比较
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }//去重不会影响大局

        int gLongest = 1;
        for (int num:set){
            if (!set.contains(num-1)){//如果hash表中存在num-1，则说明num已经被计算过了，应当跳过！
                int localLongest = 1;
                while (set.contains(++num)){//判断是否存在num，用hash表可以优化到o1
                    localLongest++;
                }
                return Math.max(gLongest,localLongest);
            }
        }
        return 1;
    }
/*    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }//去重后不影响连续序列的长度计算

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {//如果我们哈希表有num-1，则num一定被计算过了，此时跳过
                int currentStreak = 1;

                while (num_set.contains(++num)) {//使用hash查看一个数是否存在能优化至 O(1)的时间复杂度。
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }*/
/*    public static int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }*/
}
