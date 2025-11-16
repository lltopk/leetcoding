package org.lyflexi.solutions.binarysearch;

import java.util.Arrays;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/26 16:56
 * @description
 * 1498. 满足条件的子序列数目
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
 *
 * 由于答案可能很大，请将结果对 109 + 7 取余后返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,5,6,7], target = 9
 * 输出：4
 * 解释：有 4 个子序列满足该条件。
 * [3] -> 最小元素 + 最大元素 <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * 示例 2：
 *
 * 输入：nums = [3,3,6,8], target = 10
 * 输出：6
 * 解释：有 6 个子序列满足该条件。（nums 中可以有重复数字）
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * 示例 3：
 *
 * 输入：nums = [2,3,3,4,6,7], target = 12
 * 输出：61
 * 解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
 * 有效序列总数为（63 - 2 = 61）
 */
public class LC1498_numSubseq {
    //2^10 + 7
    final static int MOD = 1_000_000_007;

    public static void main(String[] args) {
        int[] ints = {3, 3, 6, 8};
        numSubseq(ints,10);
    }
    public static int numSubseq(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] pows = new int[n];
        createSamllPow(pows);
        int  rs = 0;
        for(int i = 0;i<n;i++){
            if(nums[i]*2>target){
                break;
            }
            //固定点nums[i]
            int l = i+1, r = n;
            while(l<r){
                int midIndex = l + ((r-l)>>1);

                if(nums[i]+nums[midIndex]>target){
                    r = midIndex;
                }else{//求右界
                    l = midIndex+1;
                }
            }

            //注意: 这里需要对和进行取模, 进一步防止溢出
            rs =(rs+ pows[l-i-1])%MOD;

        }
        return rs;

    }

    private static void createSamllPow(int [] pows){
        pows[0] = 1;
        int n = pows.length;
        for(int i = 1;i<n;i++){
            pows[i] = (pows[i-1]*2)%MOD;
        }
    }
}
