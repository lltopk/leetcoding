package org.lyflexi.solutions.baseAlgorithm.diff.interval;

/**
 * 731. 我的日程安排表 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 实现一个程序来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 *
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生 三重预订。
 *
 * 事件能够用一对整数 startTime 和 endTime 表示，在一个半开区间的时间 [startTime, endTime) 上预定。实数 x 的范围为  startTime <= x < endTime。
 *
 * 实现 MyCalendarTwo 类：
 *
 * MyCalendarTwo() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, true, true, true, false, true, true]
 *
 * 解释：
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // 返回 True，能够预定该日程。
 * myCalendarTwo.book(50, 60); // 返回 True，能够预定该日程。
 * myCalendarTwo.book(10, 40); // 返回 True，该日程能够被重复预定。
 * myCalendarTwo.book(5, 15);  // 返回 False，该日程导致了三重预定，所以不能预定。
 * myCalendarTwo.book(5, 10); // 返回 True，能够预定该日程，因为它不使用已经双重预订的时间 10。
 * myCalendarTwo.book(25, 55); // 返回 True，能够预定该日程，因为时间段 [25, 40) 将被第三个日程重复预定，时间段 [40, 50) 将被单独预定，而时间段 [50, 55) 将被第二个日程重复预定。
 *
 *
 * 提示：
 *
 * 0 <= start < end <= 109
 * 最多调用 book 1000 次。
 */

import java.util.Map;
import java.util.TreeMap;

/**
 * 差分思想
 */
public class LC731_MyCalendarTwo {
    //区间自带两个点位, 因此可以用差分数组
    //我们并不知道区间的个数, 但又必须保证差分数组有序才能用, 所以这里用TreeMap, Key既是start也是end
    TreeMap<Integer, Integer> treeMap;

    public LC731_MyCalendarTwo() {
        treeMap = new TreeMap<Integer, Integer>();
    }

    public boolean book(int start, int end) {
        int s = 0;
        treeMap.merge(start, 1, Integer::sum);
        treeMap.merge(end, -1, Integer::sum);
        for(Map.Entry<Integer, Integer> entry: treeMap.entrySet()){
            //加入新的区间之后, 计算每个点位(起点)的日程数目
            s += entry.getValue();
            if(s>2){
                //题目要求返回 false 并且不要将该日程安排添加到日历中。
                treeMap.merge(start, -1, Integer::sum);
                treeMap.merge(end, 1, Integer::sum);
                return false;
            }
        }

        return true;
    }
}
