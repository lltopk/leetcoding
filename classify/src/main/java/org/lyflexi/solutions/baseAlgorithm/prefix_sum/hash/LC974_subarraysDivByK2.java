package org.lyflexi.solutions.baseAlgorithm.prefix_sum.hash;

/**
 * 974. 和可被 K 整除的子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个整数数组 nums 和一个整数 k ，返回其中元素之和可被 k 整除的非空 子数组 的数目。
 *
 * 子数组 是数组中 连续 的部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,0,-2,-3,1], k = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 k = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * 示例 2:
 *
 * 输入: nums = [5], k = 9
 * 输出: 0
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * 2 <= k <= 104
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和与哈希表
 */
public class LC974_subarraysDivByK2 {
    public int subarraysDivByK(int[] nums, int k) {
        //(s2 - s1) % k == 0, 根据加减法取模公式(s2% k - s1% k) % k == 0
        //可得s2% k = s1% k, 问题转化为两变量问题, 可用哈希表
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        //需要注意的是, 要初始化哈希, cnt[0] = 1;目的是满足前缀和本身就可以被k整除的情况
        map.put(0, 1);
        int preS = 0;
        int ans  =0;
        for(int i = 0 ;i <n; i++){
            //被模数preS + nums[i]有可能是负数
            // preS = ((preS + nums[i]) % k + k) % k;

            //在计算过程中((s + x )%k + k) % k 等价于 (s + x % k + k) % k, 两者都是用同余定理解决负数问题, 只是对象不同,
            // 但s+x出现负数根本上是由负数x引起的, 因此对后者使用同余定理会更加合理, 请教灵神我的理解对吗?@灵茶山艾府
            preS = (preS + nums[i]%k + k) % k;
            ans += map.getOrDefault(preS, 0);
            map.merge(preS, 1, Integer::sum);

        }
        return ans;
    }
}
