package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers.floyd_cycle;

import java.util.HashSet;
import java.util.Set;

/**
 * 1015. 可被 K 整除的最小整数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
 *
 * 返回 n 的长度。如果不存在这样的 n ，就返回-1。
 *
 * 注意： n 可能不符合 64 位带符号整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 1
 * 输出：1
 * 解释：最小的答案是 n = 1，其长度为 1。
 * 示例 2：
 *
 * 输入：k = 2
 * 输出：-1
 * 解释：不存在可被 2 整除的正整数 n 。
 * 示例 3：
 *
 * 输入：k = 3
 * 输出：3
 * 解释：最小的答案是 n = 111，其长度为 3。
 *
 *
 * 提示：
 *
 * 1 <= k <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 45,148/89.4K
 * 通过率
 * 50.5%
 */

/**
 * 结论: 当不断的执行x = (x*10 + 1) % k;之后, 若迟迟没有得0, 就会陷入循环
 */
public class LC1015_smallestRepunitDivByK {
    public int smallestRepunitDivByK(int k) {
        int ret = 0, x = 1%k;//初始化模k是因为当输入k=1的时候, 答案是1
        Set<Integer> mySet = new HashSet<>();
        while(x > 0 && mySet.add(x)){
            x = (x*10 + 1) % k;
        }
        if(x == 0){
            return mySet.size() + 1;
        }
        return -1;
    }
}
