package org.lyflexi.solutions.baseAlgorithm.enumerate;

public class Test {
    public static void main(String[] args) {
        int[] ints = {3,5,0,3,4};
        System.out.println(find132pattern(ints));
    }
    private static boolean find132pattern(int[] nums) {
        int n = nums.length;
        int pre = nums[0];//前缀最小值

        int[] sufMax = new int[n];//后缀最大值, 是个状态数组, 其索引大于等于当前枚举j
        sufMax[n-1] = nums[n-1];
        int[] sufMin = new int[n];//后缀最小值, 是个状态数组, 其索引大于等于当前枚举j
        sufMin[n-1] = nums[n-1];
        for(int j = n-2; j>2; j--){
            sufMax[j] = Math.max(nums[j], sufMax[j+1]);
            sufMin[j] = Math.min(nums[j], sufMin[j+1]);
        }

        for(int j = 1; j<n-1; j++){

            //降低复杂度, 当
            //1. i的最小值大于最大值k
            //2. k的最小值大于枚举值j
            if(pre >= sufMax[j+1] || sufMin[j+1]>=nums[j]){
                pre = Math.min(pre, nums[j]);
                continue;
            }

            for(int k = j+1; k<n; k++){
                if(pre < nums[k] && nums[k] < nums[j]){
                    return true;
                }
            }
            pre = Math.min(pre, nums[j]);
        }

        return false;
    }
}
