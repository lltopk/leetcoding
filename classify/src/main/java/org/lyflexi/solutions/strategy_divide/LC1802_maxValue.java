package org.lyflexi.solutions.strategy_divide;

/**
 * 1802. 有界数组中指定下标处的最大值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 *
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 * 示例 2：
 *
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= n <= maxSum <= 109
 * 0 <= index < n
 */

/**
 * 二分答案
 */
public class LC1802_maxValue {
    public int maxValue(int n, int index, int maxSum) {
        //nums 中所有元素之和不超过 maxSum, 且要求目标值sums[index]最大, 则目标值一定是峰值, 其左右两侧递减1, 直至1持续

        //可以二分答案求最大
        int l = 0, r = maxSum+1;
        while(l + 1 < r){
            int mid = l + ((r-l)>>1);
            if(checkInc(mid, n, index, maxSum)){
                l = mid;
            }else{
                r = mid;
            }
        }

        return l;
    }

    private boolean checkInc(int mid , int n, int index, int maxSum){
        int lLength = index;
        int rLength = n - index -1;
        return mid + calSequence(mid, lLength) + calSequence(mid, rLength) <=maxSum;
    }

    /**
     等差±1的计算公式都是一样求和计算, 只是需要判断最小值恰好是第一个1, 还是说维持1
     */
    private long calSequence(int bigger, int length){
        if(length <= bigger - 2){
            //完全等差, 且满足最小值题意不为0, length最大是bigger-2
            int min = bigger  - length;
            return (long)(bigger-1+min)*length/2;
        }else{
            //最后多了很多1维持
            long sum1 = (long)(bigger-1+1)*(bigger - 1)/2;
            int sum2 = length - (bigger - 1);
            return sum1 + sum2;
        }
    }
}
