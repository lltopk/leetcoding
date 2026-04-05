package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_last_update_cnt_helper;

/**
 * 624. 数组列表中的最大距离
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 *
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
 *
 * 返回最大距离。
 *
 * 示例 1：
 *
 * 输入：[[1,2,3],[4,5],[1,2,3]]
 * 输出：4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 * 示例 2：
 *
 * 输入：arrays = [[1],[1]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == arrays.length
 * 2 <= m <= 105
 * 1 <= arrays[i].length <= 500
 * -104 <= arrays[i][j] <= 104
 * arrays[i] 以 升序 排序。
 * 所有数组中最多有 105 个整数。
 */

import java.util.List;

/**
 * 枚举技巧： 枚举右维护左
 */
public class LC624_maxDistance {
    //枚举技巧： 枚举右维护左
    public int maxDistance(List<List<Integer>> arrays) {
        //怎么利用各个数组有序的条件呢？  可以直接O(1)访问每个数组的第一个元素为局部最小值， 每个数组的最后一个元素为局部最大值
        int ans = 0;
        int preMin = Integer.MAX_VALUE/2;//防止减法溢出
        int preMax = Integer.MIN_VALUE/2;//题目元素中存在负数
        int n = arrays.size();
        for(int r = 0; r< n; r++){
            List<Integer> list = arrays.get(r);
            //假设遍历到右端点, 当前最大差有两种算法
            //1. preMax - list.get(0)
            //2. list.get(list.size()-1) - preMin
            ans = Math.max(ans, Math.max(preMax - list.get(0), list.get(list.size()-1) - preMin));
            preMin = Math.min(preMin, list.get(0));
            preMax = Math.max(preMax, list.get(list.size() - 1));
        }

        //不能最后return preMax - preMin, 因为有可能最大值和最小值出现在同一个数组， 不符合题意
        return ans;
    }
}
