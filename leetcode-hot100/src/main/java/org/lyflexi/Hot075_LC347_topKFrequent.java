package org.lyflexi;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,1,1,2,2,3], k = 2
 * 
 * 输出：[1,2]
 * 
 * 示例 2：
 * 
 * 输入：nums = [1], k = 1
 * 
 * 输出：[1]
 * 
 * 示例 3：
 * 
 * 输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * 
 * 输出：[1,2]
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 10^5
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * - k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 
 * - 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * 
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */

/**
 * 第三步
 * 
 * 倒序遍历 $buckets$，把 $buckets[c]$ 中的元素加到答案中。
 * 
 * 一旦答案的长度等于 $k$，就立刻返回答案。
 * 注 1：题目保证答案唯一，所以一定会出现答案长度恰好等于 $k$ 的情况。
 * 注 2：可以按任意顺序返回答案。比如示例 1 返回 $[1,2]$ 还是 $[2,1]$ 都是正确的。
 */
public class Hot075_LC347_topKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 第一步：统计每个元素的出现次数
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
        }
        int maxCnt = Collections.max(cnt.values());

        // 第二步：把出现次数相同的元素，放到同一个桶中
        List<Integer>[] buckets = new ArrayList[maxCnt + 1];
        Arrays.setAll(buckets, i -> new ArrayList<>());
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            buckets[e.getValue()].add(e.getKey());
        }

        // 第三步：倒序遍历 buckets，把出现次数前 k 大的元素加入答案
        int[] ans = new int[k];
        int j = 0;
        for (int i = maxCnt; j < k; i--) {
            // 注意题目保证答案唯一，一定会出现某次循环结束后 j 恰好等于 k 的情况
            for (int x : buckets[i]) {
                ans[j++] = x;
            }
        }
        return ans;
    }
}
