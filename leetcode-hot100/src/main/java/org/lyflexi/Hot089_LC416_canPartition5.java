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
 * 附：bitset 做法
 * 
 * 把布尔数组压缩成一个二进制数，二进制数从低到高第 $i$ 位是 $0$，表示布尔数组的第 $i$ 个元素是 $false$；从低到高第 $i$ 位是 $1$，表示布尔数组的第 $i$ 个元素是 $true$。
 * 
 * 转移方程等价于，把 $f$ 中的每个比特位增加 $x=nums[i]$，即左移 $x$ 位，然后跟原来 $f$ 计算 OR。前者对应选 $x$，后者对应不选 $x$。
 * 
 * 判断 $f[s]$ 是否为 $true$，等价于判断 $f$ 的第 $s$ 位是否为 $1$，即 (f >> s & 1) == 1。
 */
import java.math.BigInteger;

public class Hot089_LC416_canPartition5 {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        s /= 2;

        BigInteger f = BigInteger.ONE;
        for (int x : nums) {
            f = f.or(f.shiftLeft(x)); // f |= f << x;
        }
        return f.testBit(s); // 判断 f 中第 s 位是否为 1
    }
}
