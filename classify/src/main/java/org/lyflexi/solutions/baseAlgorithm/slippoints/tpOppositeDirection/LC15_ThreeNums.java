package org.lyflexi.solutions.baseAlgorithm.slippoints.tpOppositeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/11 20:58
 */

/*15. 三数之和
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
请你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。


示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。

示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。


示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
*/

/**
 * 相向双指针
 */
public class LC15_ThreeNums {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        //枚举第一个数, 转化为两数之和
        for(int i = 0; i< nums.length -2; i++){
            //去重
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            //优化1. 当前枚举nums[i]下, 最小和大于0, 整个程序可以停止了
            if(nums[i] + nums[i+1] + nums[i+2] > 0){
                break;
            }
            //优化2. 当前枚举nums[i]下, 最大和小于0, 则不用跑下面的双指针了
            if(nums[i] + nums[nums.length -1] + nums[nums.length -2] < 0){
                continue;
            }

            //求nums[j] + nums[k] == -nums[i]
            int j = i+1, k = nums.length-1;

            //这里要加while循环, 因为枚举当前nums[i]可能会收集多对j, k
            while(j<k){
                if(nums[j] + nums[k] == -nums[i]){
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[j]);
                    item.add(nums[k]);
                    ans.add(item);
                    j++;
                    while(j<k && nums[j] == nums[j-1]){
                        j++;
                    }

                    k--;
                    while(j<k && nums[k] == nums[k+1]){
                        k--;
                    }
                }else if(nums[j] + nums[k] < -nums[i]){
                    j++;
                }else{
                    k--;
                }
            }

        }

        return ans;
    }
}
