package org.lyflexi.solutions.baseAlgorithm.diff.interval;

/**
 * 732. 我的日程安排表 III
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 当 k 个日程存在一些非空交集时（即, k 个日程包含了一些相同时间），就会产生 k 次预订。
 *
 * 给你一些日程安排 [startTime, endTime) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 *
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 *
 * MyCalendarThree() 初始化对象。
 * int book(int startTime, int endTime) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, 1, 1, 2, 3, 3, 3]
 *
 * 解释：
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
 * myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
 * myCalendarThree.book(5, 10); // 返回 3
 * myCalendarThree.book(25, 55); // 返回 3
 *
 *
 * 提示：
 *
 * 0 <= startTime < endTime <= 109
 * 每个测试用例，调用 book 函数最多不超过 400次
 */

import java.util.Map;
import java.util.TreeMap;

/**
 * 差分思想
 */
public class LC732_MyCalendarThree {
    //区间自带两个点位, 因此可以用差分数组
    //我们并不知道区间的个数, 但又必须保证差分数组有序才能用, 所以这里用TreeMap, Key既是start也是end
    TreeMap<Integer, Integer> treeMap;

    public LC732_MyCalendarThree() {
        treeMap = new TreeMap<Integer, Integer>();
    }

    public int book(int start, int end) {
        int s = 0, ans = 0;
        treeMap.merge(start, 1, Integer::sum);
        treeMap.merge(end, -1, Integer::sum);
        for(Map.Entry<Integer, Integer> entry: treeMap.entrySet()){
            //加入新的区间之后, 计算每个点位(起点)的日程数目
            s += entry.getValue();
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
