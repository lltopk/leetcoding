package org.lyflexi.solutions.bis;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/2 21:12
 * @description
 * 287. 寻找重复数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3 :
 *
 * 输入：nums = [3,3,3,3,3]
 * 输出：3
 */
public class LC287_findDuplicate {

    public int findDuplicate(int[] nums) {
        // l从1开始，[l,r)虽然是索引，但同时覆盖了nums数组中所有的可能值
        int l = 1, n = nums.length, r = n;
        while(l<r){
            int midIndex = l + ((r-l)>>1);
            int cnt = 0;//代表原数组中小于等于midIndex的元素个数
            for(int i = 0;i<n;i++){
                if(nums[i]<=midIndex){
                    cnt++;
                }
            }
            //cnt作为元素构成的新的数组newNums, 值一定是递增的, 因此可以使用二分模板
            //比较对象转化为, 在newNums中, 求newNums[i]>i情况下的第一个i
            if(cnt<=midIndex){
                l = midIndex +1;
            }else{
                r = midIndex;
                // return midIndex;//不可
            }
        }

        return l;
    }
}