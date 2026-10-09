package org.lyflexi;

import java.util.*;

/**
 * 416. 分割等和子集
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 200
 * 
 * - 1 <= nums[i] <= 100
 */

/**
 * 四、空间优化
 * 
 * 观察上面的状态转移方程，在计算 $f[i+1]$ 时，只会用到 $f[i]$，不会用到比 $i$ 更早的状态。
 * 
 * 因此可以去掉第一个维度，反复利用同一个一维数组。
 * 
 * 状态转移方程改为
 * 
 * $$
 * f[j] = f[j] \lor f[j-nums[i]]
 * $$
 * 
 * 初始值 $f[0]= true$。
 * 
 * 答案为 $f[s/2]$。
 * 
 * 具体例子，以及为什么要倒序遍历 $j$，请看 0-1 背包视频讲解。
 * 
 * 此外，设前 $i$ 个数的和为 $s'$，由于子序列的元素和不可能比 $s'$ 还大，$j$ 可以从 $min(s',s/2)$ 开始倒着枚举。比如 $nums$ 前两个数的和等于 $5$，那么我们无法在前两个数中，选出一个元素和大于 $5$ 的子序列，所以对于 $j>5$ 的 $f$ 值，一定是 $false$，无需计算。
 * 
 * 此外，可以在循环中提前判断 $f[s/2]$ 是否为 $true$，是就直接返回 $true$。
 */
public class Hot089_LC416_canPartition4 {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        s /= 2; // 注意这里把 s 减半了

        boolean[] f = new boolean[s + 1];
        f[0] = true;
        int s2 = 0;
        for (int x : nums) {
            s2 = Math.min(s2 + x, s);
            for (int j = s2; j >= x; j--) {
                f[j] = f[j] || f[j - x];
            }
            if (f[s]) {
                return true;
            }
        }
        return false;
    }
}
