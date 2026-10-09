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
 * 三、1:1 翻译成递推
 * 
 * 我们可以去掉递归中的「递」，只保留「归」的部分，即自底向上计算。
 * 
 * 具体来说，$f[i][j]$ 的定义和 $dfs(i,j)$ 的定义是一样的，都表示能否从 $nums[0]$ 到 $nums[i]$ 中选出一个和恰好等于 $j$ 的子序列。
 * 
 * 相应的递推式（状态转移方程）也和 $dfs$ 一样：
 * 
 * $$
 * f[i][j] =
 * \begin{cases}
 * f[i-1][j], & j < nums[i]     \\
 * f[i-1][j-nums[i]] \lor f[i-1][j], & j >= nums[i]     \\
 * \end{cases}
 * $$
 * 
 * 但是，这种定义方式没有状态能表示递归边界，即 $i=-1$ 的情况。
 * 
 * 解决办法：在二维数组 $f$ 的最上边插入一排状态，那么其余状态全部向下偏移一位，把 $f[i]$ 改为 $f[i+1]$，把 $f[i-1]$ 改为 $f[i]$。
 * 
 * 修改后，$f[i+1][j]$ 表示能否从 $nums[0]$ 到 $nums[i]$ 中选出一个和为 $j$ 的子序列。$f[0]$ 对应递归边界。
 * 
 * 修改后的递推式为
 * 
 * $$
 * f[i+1][j] =
 * \begin{cases}
 * f[i][j], & j < nums[i]     \\
 * f[i][j-nums[i]] \lor f[i][j], & j >= nums[i]     \\
 * \end{cases}
 * $$
 * 问：为什么 $nums$ 的下标不用变？
 * 答：既然是在 $f$ 的最上边插入一排状态，那么就只需要修改和 $f$ 有关的下标，其余任何逻辑都无需修改。或者说，如果把 $nums[i]$ 也改成 $nums[i+1]$，那么 $nums[0]$ 就被我们给忽略掉了。
 * 
 * 初始值 $f[0][0]=true$，翻译自递归边界 $dfs(-1,0)=true$。其余值初始化成 $false$。
 * 
 * 答案为 $f[n][s/2]$，翻译自递归入口 $dfs(n-1,s/2)$。
 */
public class Hot089_LC416_canPartition3 {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        s /= 2; // 注意这里把 s 减半了

        int n = nums.length;
        boolean[][] f = new boolean[n + 1][s + 1];
        f[0][0] = true;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = 0; j <= s; j++) {
                f[i + 1][j] = j >= x && f[i][j - x] || f[i][j];
            }
        }
        return f[n][s];
    }
}
