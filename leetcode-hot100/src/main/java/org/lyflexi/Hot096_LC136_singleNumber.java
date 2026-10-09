package org.lyflexi;

import java.util.*;

/**
 * 136. 只出现一次的数字
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * 
 * 示例 1 ：
 * 
 * 输入：nums = [2,2,1]
 * 
 * 输出：1
 * 
 * 示例 2 ：
 * 
 * 输入：nums = [4,1,2,1,2]
 * 
 * 输出：4
 * 
 * 示例 3 ：
 * 
 * 输入：nums = [1]
 * 
 * 输出：1
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 3 * 10^4
 * 
 * - -3 * 10^4 <= nums[i] <= 3 * 10^4
 * 
 * - 除了某个元素只出现一次以外，其余每个元素均出现两次。
 */

/**
 * 利用异或运算 $a\oplus a = 0$ 的性质，我们可以用异或来「消除」所有出现了两次的元素，最后剩下的一定是只出现一次的元素。
 * 
 * 例如 $nums=[4,1,2,1,2]$，把所有元素异或：
 * 
 * $$
 * \begin{aligned}
 * &4\oplus 1\oplus 2\oplus 1\oplus 2\\
 * =\ &4\oplus (1\oplus 1)\oplus(2\oplus 2)\\
 * =\ &4\oplus 0 \oplus 0\\
 * =\ &4
 * \end{aligned}
 * $$
 * 
 * 其中用到了异或运算的交换律 $a\oplus b = b\oplus a$，以及结合律 $(a\oplus b) \oplus c  = a\oplus (b\oplus c)$（类比加法）。
 * 
 * 代码中，初始化 $ans=0$ 是因为 $0\oplus a = a$，相当于我们从第一个数开始，和其他数异或。
 */
public class Hot096_LC136_singleNumber {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
}
