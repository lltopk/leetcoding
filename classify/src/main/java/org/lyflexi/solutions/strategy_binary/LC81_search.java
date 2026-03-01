package org.lyflexi.solutions.strategy_binary;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/2 19:39
 * @description
 * 81. 搜索旋转排序数组 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * 你必须尽可能减少整个操作步骤。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 */
public class LC81_search {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n;
        //循环不变量
        while(left<right){
            int midIndex = (left+right)>>1;
            if(nums[midIndex] == target){
                return true;
            }
            //对于数组中有重复元素的情况，二分查找时可能会有 a[l]=a[mid]=a[r]，此时无法判断区间 [l,mid] 和区间 [mid+1,r] 哪个是有序的。
            if(nums[midIndex] == nums[left] && nums[midIndex] == nums[right-1]){
                left++;
                right--;
                continue;
            }
            //落入大数组, 只知道左值, 先左移, 比V1: LC33版不同的是这里比较的是left, 而不是0
            if(nums[midIndex]>=nums[left]){
                if(nums[midIndex]>target && target>=nums[left]){
                    right = midIndex ;
                }else{
                    left = midIndex +1;
                }
            }else{//落入小数组. 只知道右值, 先右移, 比V1版不同的是这里比较的是right-1, 而不是n-1
                if(nums[midIndex]<target && target<=nums[right-1]){
                    left = midIndex +1;
                }else{
                    right = midIndex ;
                }
            }
        }
        return false;
    }
}
