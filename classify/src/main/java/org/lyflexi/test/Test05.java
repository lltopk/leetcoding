package org.lyflexi.test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Test05 {
    //测试找不到大于target的最终left
    // 此时left == n
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        System.out.println(search(nums, 6));
    }
    public static int search(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        //区间不变量[l,r)
        int right = n;
        while(left<right){
            int mindex = (left+right) >>1;
            if(nums[mindex] == target){
                left = right = mindex;
                return left;
            }
            if(nums[mindex]>target){
                right = mindex;
            }else if(nums[mindex]<target){
                left = mindex+1;
            }
        }
//        return -1;

        return left;
    }

}