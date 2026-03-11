package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMax;

public class LC1004_longestOnes {
    //不定长滑动窗口求最大
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int ans = 0, l = 0;
        int cnt0 = 0;
        for(int i = 0; i<n ; i++){
            cnt0+=nums[i] == 0?1:0;
            while(cnt0 >k){
                cnt0-=nums[l]==0?1:0;
                l++;
            }
            ans = Math.max(ans, i - l +1);
        }

        return ans;
    }
}
