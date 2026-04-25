package org.lyflexi.solutions.baseAlgorithm.queue.monotonic_queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 239. 滑动窗口最大值
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */

/**
 * 由于每个元素入堆最多1次, 出队最多1次, 因此总的时间复杂度是2*N, 即On
 */
public class LC239_maxSlidingWindow {
    //固定长度滑动窗口 + 优先级队列(堆)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //用双端队列实现单调队列, (单调递减队列)
        Deque<Integer> q = new ArrayDeque<>();
        int l = 0;
        int[] ans = new int[n - k + 1];
        for(int r = 0; r<n ; r++){
            // 1. 右边入
            //当队尾元素与nums[r]相等时, 也移除队尾元素, 这不影响答案, 还能够保证单调队列元素不重复
            while(!q.isEmpty() && nums[q.getLast()] <= nums[r]){
                q.removeLast();
                //单调队列中元素个数可能会小于窗口大小, 这是正常的比如4 2 1遇到了3, 就会剩下4 3
            }

            //单调队列中一般存储的是元素下标, 方便下面和窗口左界l0进行判断
            q.addLast(r);


            //2. 左边出
            int l0 = r - k + 1;
            //由于每次入队最多1次, 因此出队也至多只有1次, 所以下面用if即可
            if(q.getFirst() < l0){
                q.removeFirst();
            }

            //3. 当存在左端点时候(>=0), 记录答案
            if(l0 >=0){
                ans[l = l0] = nums[q.getFirst()];
            }
        }

        return ans;
    }
}
