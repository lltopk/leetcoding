package org.lyflexi.solutions.Ⅲdivideconquer;

/**
 * @author hasee
 * @description V1.0
 * 33. 搜索旋转排序数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 * @create 2025/10/5 18:06
 */
public class LC33_search {
       //一次二分
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n;

        while(l<r){
            int mid = (l+r)>>1;
            
            //自定义二分比较
            if(compareInc(nums, mid, target)){
                l = mid +1;
            }else{
                r = mid;
            }
        }

        if(l == n || nums[l]!=target){
            return -1;
        }

        return l;
    }

    private boolean compareInc(int[] nums, int mid , int target){
        int n = nums.length;
        int last = nums[n-1];

        //左边有序数组
        if(nums[mid]>last){
            return nums[mid]<target || target<= last;
        }else{//右边有序数组
            return nums[mid]<target && target <= last;
        }

    }
}