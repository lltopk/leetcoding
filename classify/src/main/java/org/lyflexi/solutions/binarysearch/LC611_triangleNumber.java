package org.lyflexi.solutions.binarysearch;

import java.util.Arrays;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/26 15:16
 * @description
 * 611. 有效三角形的个数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 示例 2:
 *
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 */
public class LC611_triangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int rs = 0;
        for(int i =0;i<n-2;i++){
            for(int j = i+1;j<n-1;j++){
                int target = nums[i]+nums[j];
                int l = j+1, r = n ;
                int lB = -1;
                while(l<r){
                    int midIndex = l+((r-l)>>1);
                    //求左界
                    if(nums[midIndex]>=target){
                        r = midIndex;
                    }else if(nums[midIndex]<target){
                        l = midIndex+1;
                    }
                }

                rs += l-1-j;
            }
        }
        return rs;
    }
}
