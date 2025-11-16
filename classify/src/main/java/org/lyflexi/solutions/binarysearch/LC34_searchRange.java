package org.lyflexi.solutions.binarysearch;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/10/3 17:07
 */
public class LC34_searchRange {
    public int[] searchRange(int[] nums, int target) {

        int l = getLBorder(nums,target);
        int r = getRBorder(nums,target);
        // 验证 target 是否真的存在
        // 1. target大于数组或者小于数组(位于数组两端之外)
        // 2. target在数组内, 但不等于数组元素
        if (l == -1 || r == -1 || nums[l] != target || nums[r] != target) {
            return new int[] {-1,-1};
        }
        return new int[] {l,r};
    }

    private int getLBorder(int[] nums,int target){
        int n = nums.length;
        int left = 0;
        int lBorder = -1;
        //区间不变量
        int right = n;
        while(left<right){
            int mIndex = (left+right)>>1;
            //重复元素求左边界
            if(nums[mIndex]>=target){
                right = mIndex;
                lBorder = mIndex;
            }else{
                left = mIndex+1;
            }
        }

        return lBorder;
    }


    private int getRBorder(int[] nums,int target){
        int n = nums.length;
        int left = 0;
        //区间不变量
        int right = n;
        int rBoard = -1;
        while(left<right){
            int mIndex = (left+right)>>1;
            //重复元素求右边界
            if(nums[mIndex]<=target){
                left = mIndex+1;
                rBoard = mIndex;
            }else {
                right = mIndex;
            }
        }
        return rBoard;
    }
}
