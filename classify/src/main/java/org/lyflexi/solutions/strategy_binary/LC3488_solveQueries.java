/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_binary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hasee
 * 3488. 距离最小相等元素查询
已解答
中等
相关标签
premium lock icon
相关企业
提示
给你一个 环形 数组 nums 和一个数组 queries 。

对于每个查询 i ，你需要找到以下内容：

数组 nums 中下标 queries[i] 处的元素与 任意 其他下标 j（满足 nums[j] == nums[queries[i]]）之间的 最小 距离。如果不存在这样的下标 j，则该查询的结果为 -1 。
返回一个数组 answer，其大小与 queries 相同，其中 answer[i] 表示查询i的结果。

 

示例 1：

输入： nums = [1,3,1,4,1,3,2], queries = [0,3,5]

输出： [2,-1,3]

解释：

查询 0：下标 queries[0] = 0 处的元素为 nums[0] = 1 。最近的相同值下标为 2，距离为 2。
查询 1：下标 queries[1] = 3 处的元素为 nums[3] = 4 。不存在其他包含值 4 的下标，因此结果为 -1。
查询 2：下标 queries[2] = 5 处的元素为 nums[5] = 3 。最近的相同值下标为 1，距离为 3（沿着循环路径：5 -> 6 -> 0 -> 1）。
示例 2：

输入： nums = [1,2,3,4], queries = [0,1,2,3]

输出： [-1,-1,-1,-1]

解释：

数组 nums 中的每个值都是唯一的，因此没有下标与查询的元素值相同。所有查询的结果均为 -1。

 

提示：

1 <= queries.length <= nums.length <= 105
1 <= nums[i] <= 106
0 <= queries[i] < nums.length
 */
public class LC3488_solveQueries {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int n = nums.length;
        /**
         * 看示例 1，所有 1 的下标列表是 p=[0,2,4]。
            由于 nums 是循环数组：

            在下标列表前面添加 4−n=−3，相当于认为在 −3 下标处也有一个 1。
            在下标列表末尾添加 0+n=7，相当于认为在 7 下标处也有一个 1。
            修改后的下标列表为 p=[−3,0,2,4,7]。

            左右哨兵分别用于避免两端点需要循环求解的情况: 
            左哨兵给左端点用: 0-(-3)==循环求与下标0的距离==6->5->4=3
            右哨兵给右端点用: 7-4==循环求与下标4的距离==5->6->0=3
         */
        for (List<Integer> p : indices.values()) {
            // 前后各加一个哨兵
            int i0 = p.get(0);
            // 左哨兵: p.get(p.size() - 1) - n给p.get(0)用
            p.add(0, p.get(p.size() - 1) - n);
            // 右哨兵: p.get(0)+nums.length给p.get(p.size() - 1)用
            p.add(i0 + n);
        }

        List<Integer> ans = new ArrayList<>(queries.length); // 预分配空间
        for (int i=0 ;i<queries.length;i++) {
            List<Integer> p = indices.get(nums[queries[i]]);
            if (p.size() == 3) {
                ans.add(-1);
            } else {
                //二分结果j: 小下标
                int j = Collections.binarySearch(p, queries[i]);
                //p.get(j): ==大下标
                // ans.add(Math.min(p.get(j) - p.get(j - 1), p.get(j + 1) - p.get(j)));
                //queries[i] == p.get(j)
                ans.add(Math.min(queries[i] - p.get(j - 1), p.get(j + 1) - queries[i]));
            }
        }
        return ans;
    }
}
