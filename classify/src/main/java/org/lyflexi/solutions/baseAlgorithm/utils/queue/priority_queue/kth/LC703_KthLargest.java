package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.kth;

/**
 * 703. 数据流中的第 K 大元素
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *
 * 请实现 KthLargest 类：
 *
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 *
 * 输出：[null, 4, 5, 5, 8, 8]
 *
 * 解释：
 *
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3); // 返回 4
 * kthLargest.add(5); // 返回 5
 * kthLargest.add(10); // 返回 5
 * kthLargest.add(9); // 返回 8
 * kthLargest.add(4); // 返回 8
 *
 *
 *
 * 示例 2：
 *
 * 输入：
 * ["KthLargest", "add", "add", "add", "add"]
 * [[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]
 *
 * 输出：[null, 7, 7, 7, 8]
 *
 * 解释：
 *
 * KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
 * kthLargest.add(2); // 返回 7
 * kthLargest.add(10); // 返回 7
 * kthLargest.add(9); // 返回 7
 * kthLargest.add(9); // 返回 8
 *
 *
 * 提示：
 * 0 <= nums.length <= 104
 * 1 <= k <= nums.length + 1
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 119,920/223.9K
 * 通过率
 * 53.6%
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列
 */
public class LC703_KthLargest {
    Queue<Integer> priorityQueue;
    int k = 0;

    /**
     虽然求第k大, 但还是定义小顶对, 避免堆顶弹出大元素就覆水难收了
     */
    public LC703_KthLargest(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>((a, b) -> a - b);
        for(int x: nums){
            priorityQueue.offer(x);
        }
        this.k = k;
    }

    public int add(int val) {
        priorityQueue.offer(val);
        //反正小元素以后也不会care
        while(priorityQueue.size() > k){
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
}
