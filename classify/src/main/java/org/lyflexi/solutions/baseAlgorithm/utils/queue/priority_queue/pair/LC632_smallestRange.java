package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.pair;

/**
 * 632. 最小区间
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 示例 2：
 *
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 *
 *
 * 提示：
 *
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] 按非递减顺序排列
 *
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 48,764/75.6K
 * 通过率
 * 64.5%
 */

import java.util.List;
import java.util.PriorityQueue;

/**
 * pair三元组 + 优先级队列
 */
public class LC632_smallestRange {
    //每次枚举各个list的最左端点, 计算局部覆盖区间：[min(list1.get(0, list2.get(0), list.get(0))), max(list1.get(0, list2.get(0), list.get(0)))]
    //左端点：需要一个数据结构，支持添加元素、计算最小值、**删除**最小值。这可以用最小堆维护。堆顶（最小元素）就是左端点。
    //右端点：记作 r。一开始 r 为每个列表第一个元素的最大值。当我们去掉列表第一个元素时，就用列表的第二个元素更新 r 的最大值。依此类推。
    public int[] smallestRange(List<List<Integer>> nums) {
        //存储三元组: 元素本身 元素属于哪个list 元素在原始list中的下标
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int r = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            // 把每个列表的第一个元素入堆
            int x = nums.get(i).get(0);
            pq.offer(new int[]{x, i, 0});
            r = Math.max(r, x);
        }
        // 第一个局部覆盖区间的左右端点
        int ansL = pq.peek()[0];
        int ansR = r;

        //找下一个最小的j, 循环条件是, j小于当前列表长度
        while(pq.peek()[2] + 1 < nums.get(pq.peek()[1]).size()){
            int[] peek = pq.poll();
            peek[0] = nums.get(peek[1]).get(++peek[2]); // 堆顶列表的下一个元素
            //新的局部覆盖区间的左右端点
            r = Math.max(r, peek[0]);
            pq.offer(peek);
            int l  = pq.peek()[0];
            if(r - l < ansR - ansL){
                ansL = l;
                ansR = r;
            }
        }
        return new int[] {ansL, ansR};
    }
}
