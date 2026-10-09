package org.lyflexi;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 
 * 返回 滑动窗口中的最大值 。
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
 * 
 * 示例 2：
 * 
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 10^5
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * - 1 <= k <= nums.length
 */

/**
 * 答疑
 * 
 * 问：为什么代码中的「队首离开窗口」写的是 $if$ 而不是 $while$？
 * 
 * 答：注意窗口在一步一步地向右滑动（像只蜗牛），所以元素也是一个一个地离开窗口，不会出现同一轮循环中有多个元素离开窗口的情况。
 */
public class Hot011_LC239_maxSlidingWindow2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1]; // 窗口个数
        Deque<Integer> q = new ArrayDeque<>(); // 更快的写法见【Java 数组】

        for (int i = 0; i < n; i++) {
            // 1. 右边入
            while (!q.isEmpty() && nums[q.getLast()] <= nums[i]) {
                q.removeLast(); // 维护 q 的单调性
            }
            q.addLast(i); // 注意保存的是下标，这样下面可以判断队首是否离开窗口

            // 2. 左边出
            int left = i - k + 1; // 窗口左端点
            if (q.getFirst() < left) { // 队首离开窗口
                q.removeFirst();
            }

            // 3. 在窗口左端点处记录答案
            if (left >= 0) {
                // 由于队首到队尾单调递减，所以窗口最大值就在队首
                ans[left] = nums[q.getFirst()];
            }
        }

        return ans;
    }
}
