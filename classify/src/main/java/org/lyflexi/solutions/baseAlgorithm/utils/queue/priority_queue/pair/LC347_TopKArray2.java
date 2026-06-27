package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.pair;

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
 *
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
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 958,701/1.5M
 * 通过率
 * 65.9%
 */

/**
 * pair优先队列 + 哈希
 */
public class LC347_TopKArray2 {
    //面试官? 试试不用优先级队列?
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        int maxCnt = 0;
        for(int x: nums){
            cntMap.merge(x, 1, Integer::sum);
            maxCnt = Math.max(maxCnt, cntMap.get(x));
        }

        //桶排序, 将次数相同的元素归为一桶
        //下标为元素次数, 值为同次数的元素集合
        List<Integer>[] buckets = new ArrayList[maxCnt+1];
        Arrays.setAll(buckets, idx -> new ArrayList<>());
        for(int key: cntMap.keySet()){
            int cnt = cntMap.get(key);
            buckets[cnt].add(key);
        }

        int[] ret = new int[k];
        int j = 0;
        //反向扫描
        for(int i = maxCnt; j < k; i--){
            //竖向扫描
            for(int num: buckets[i]){
                ret[j++] = num;
            }
        }
        return ret;
    }
}
