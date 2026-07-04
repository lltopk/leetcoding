package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.pair;

/**
 * 3478. 选出和最大的 K 个元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个整数数组，nums1 和 nums2，长度均为 n，以及一个正整数 k 。
 *
 * 对从 0 到 n - 1 每个下标 i ，执行下述操作：
 *
 * 找出所有满足 nums1[j] 小于 nums1[i] 的下标 j 。
 * 从这些下标对应的 nums2[j] 中选出 至多 k 个，并 最大化 这些值的总和作为结果。
 * 返回一个长度为 n 的数组 answer ，其中 answer[i] 表示对应下标 i 的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2
 *
 * 输出：[80,30,0,80,50]
 *
 * 解释：
 *
 * 对于 i = 0 ：满足 nums1[j] < nums1[0] 的下标为 [1, 2, 4] ，选出其中值最大的两个，结果为 50 + 30 = 80 。
 * 对于 i = 1 ：满足 nums1[j] < nums1[1] 的下标为 [2] ，只能选择这个值，结果为 30 。
 * 对于 i = 2 ：不存在满足 nums1[j] < nums1[2] 的下标，结果为 0 。
 * 对于 i = 3 ：满足 nums1[j] < nums1[3] 的下标为 [0, 1, 2, 4] ，选出其中值最大的两个，结果为 50 + 30 = 80 。
 * 对于 i = 4 ：满足 nums1[j] < nums1[4] 的下标为 [1, 2] ，选出其中值最大的两个，结果为 30 + 20 = 50 。
 * 示例 2：
 *
 * 输入：nums1 = [2,2,2,2], nums2 = [3,1,2,3], k = 1
 *
 * 输出：[0,0,0,0]
 *
 * 解释：由于 nums1 中的所有元素相等，不存在满足条件 nums1[j] < nums1[i]，所有位置的结果都是 0 。
 *
 *
 *
 * 提示：
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 1 <= nums1[i], nums2[i] <= 106
 * 1 <= k <= n
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 5,069/12.7K
 * 通过率
 * 39.8%
 */

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * pair优先队列 + 分组循环兼容相同元素
 */
public class LC3478_findMaxSum {
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            a[i] = new int[]{nums1[i], nums2[i], i};
        }
        //排序后的左边索引自然满足: j < i, 这样就可以枚举右, 维护左(累加nums2[j])
        Arrays.sort(a, (p, q) -> p[0] - q[0]);
        //x:[1,2,3,4,5]
        //y:[30,20,50,10,40]
        //idx: [2,1,4,0,3]

        long[] ans = new long[n];
        //小顶堆, 新的nums[j]入堆之后, 当堆满的时候, s每次减去堆顶, 就能保证s最大
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long s = 0;
        //找出所有满足 nums1[j] 小于 nums1[i] 的下标 j , 因此不包括当前元素, 所以先计算上轮答案, 后入堆
        for (int i = 0; i < n; ) {
            int start = i;

            // 题目中nums1存在相同元素, 找到所有相同的 nums1[i]，这些数的答案和前面相同元素答案的一样
            while (i < n && a[i][0] == a[start][0]) {
                ans[a[i][2]] = s;
                i++;
            }

            //后入堆, 计算出S给下轮循环用. 并且注意要把当前组[start, i)都入堆, 而不仅仅是最初的a[start][0];
            for(int j = start; j<i; j++){
                s += a[j][1];
                pq.offer(a[j][1]);
                if (pq.size() > k) {
                    s -= pq.poll();
                }
            }
        }
        return ans;
    }
}
