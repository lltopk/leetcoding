package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.pair;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 373. 查找和最小的 K 对数字
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 *
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [[1,2],[1,4],[1,6]]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 *
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [[1,1],[1,1]]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 *
 * 提示:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 和 nums2 均为 升序排列
 * 1 <= k <= 104
 * k <= nums1.length * nums2.length
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 131,024/303.1K
 * 通过率
 * 43.2%
 */
public class LC373_kSmallestPairs {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //存储nums1的索引 和 nums2的索引
        Queue<int[]> q = new PriorityQueue<>((a, b)->{
            return nums1[a[0]]+nums2[a[1]] - nums1[b[0]]-nums2[b[1]];
        }
        );
        int len1 = nums1.length;
        int len2 = nums2.length;
        //1. 出堆i,j (小根堆)
        //2. 下次入堆(两个可能性, i+1,j 或者 i,j+1), 这会导致当类似于出堆(1,2)和出堆(2,1), 下次入堆会存在重复的(2,2)
        // 姑我们约定i不要涨, 只让j涨, 这样可以避免重复元素
        for(int i =0; i< Math.min(len1, k); i++){
            //i不涨, 那就一开始用所有的i初始化堆
            q.offer(new int[]{i,0});
        }
        List<List<Integer>> ret = new ArrayList<>();
        //循环出堆k次
        while(k-- >0){
            int[] peek = q.poll();
            ret.add(List.of(nums1[peek[0]],nums2[peek[1]]));
            if(peek[1]+1<len2){
                q.offer(new int[]{peek[0], peek[1]+1});
            }
        }
        return ret;
    }
}
