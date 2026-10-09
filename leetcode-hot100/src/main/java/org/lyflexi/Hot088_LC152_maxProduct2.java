package org.lyflexi;

import java.util.*;

/**
 * 152. 乘积最大子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 
 * 测试用例的答案是一个 32-位 整数。
 * 
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 * 
 * 示例 1:
 * 
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 
 * 示例 2:
 * 
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 
 * 提示:
 * 
 * - 1 <= nums.length <= 2 * 10^4
 * 
 * - -10 <= nums[i] <= 10
 * 
 * - nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 */

/**
 * 写法二（空间优化）
 * 
 * 由于计算 $f_{max}[i]$ 和 $f_{min}[i]$ 只会用到 $f_{max}[i-1]$ 和 $f_{min}[i-1]$，不会用到更早的状态，所以可以用两个变量 $f_{max}$ 和 $f_{min}$ 滚动计算。具体请看视频讲解 动态规划入门：从记忆化搜索到递推。
 * 
 * 状态转移方程简化为：
 * 
 * $$
 * \begin{aligned}
 * f_{max} &= max(f_{max}\cdot x, f_{min}\cdot x, x) \\
 * f_{min} &= min(f_{max}\cdot x, f_{min}\cdot x, x) \\
 * \end{aligned}
 * $$
 * 
 * 注意这两个式子要同时计算。
 * 
 * 代码实现时，可以初始化 $f_{max} = f_{min} = 1$，因为 $1$ 乘以 $nums[0]$ 等于 $nums[0]$，这样我们可以从下标 $0$ 开始遍历 $nums$，代码写起来更简单。
 */
public class Hot088_LC152_maxProduct2 {
    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE; // 注意答案可能是负数
        int fMax = 1;
        int fMin = 1;
        for (int x : nums) {
            int mx = fMax;
            fMax = Math.max(Math.max(fMax * x, fMin * x), x);
            fMin = Math.min(Math.min(mx * x, fMin * x), x);
            ans = Math.max(ans, fMax);
        }
        return ans;
    }
}
