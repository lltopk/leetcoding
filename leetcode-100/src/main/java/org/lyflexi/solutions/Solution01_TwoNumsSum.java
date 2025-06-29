package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/8 14:44
 */

/*给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]
*/
public class Solution01_TwoNumsSum {
    /*2024.1.8日题解*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt((item)->{return Integer.parseInt(item);}).toArray();
//        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt((item)-> Integer.parseInt(item)).toArray();
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(scanner.nextLine());

        int[] res = twoSum(nums, target);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int pair = target-nums[i];
            if (map.containsKey(pair)){
                return new int[]{i,map.get(pair)};
            }
            map.put(nums[i],i);
        }
        return null;
    }

/*    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(in.nextLine());

        int[] res = twoSum(nums,target);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int pair = target - nums[i];
            if (hashtable.containsKey(pair)) {
                return new int[]{hashtable.get(pair), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }*/

}
