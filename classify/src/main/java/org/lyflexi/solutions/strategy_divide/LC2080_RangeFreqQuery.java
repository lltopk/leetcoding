/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hasee
 * 2080. 区间内查询数字的频率
已解答
中等
相关标签
premium lock icon
相关企业
提示
请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。

子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。

请你实现 RangeFreqQuery 类：

RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。

 

示例 1：

输入：
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
输出：
[null, 1, 2]

解释：
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
 

提示：

1 <= arr.length <= 105
1 <= arr[i], value <= 104
0 <= left <= right < arr.length
调用 query 不超过 105 次。
 */
public class LC2080_RangeFreqQuery {
  Map<Integer,List<Integer>> map = new HashMap<>();
    public LC2080_RangeFreqQuery(int[] arr) {
        for(int i = 0;i<arr.length;i++){
            map.computeIfAbsent(arr[i],key->{return new ArrayList<Integer>();}).add(i);
        }
    }
    
    //如何将On降为logn： 二分优化
    //1. 预处理构造<v, 索引数组>
    //2. 对索引数组a进行两次二分， 返回lowerBound(a,right+1)-lowerBound(a,left), 返回的是索引数组的索引差值
    public int query(int left, int right, int value) {
        List<Integer> a = map.get(value);
        if(a == null){
            return 0;
        }
        // int r = lowerBound(a, right);
        // int l = lowerBound(a, left);
        // if(r==a.size() || a.get(r)>right){//right没找到
        //     return r-l;
        // }
        // return r-l+1;

        //看到很多同学有疑问，为什么求“a 中的第一个 >right 的数的下标”， 而不是“a 中的第一个 >=right 的数的下标”。
        // 这里解释下为什么是 "第一个>right的数的下标"。
        // 思考过程：
        // 先忘记上面的结论。
        // 一开始想到的是，我们要找的是“等于right”的下标。
        // a.如果等于right下标存在。
        // 则可以直接获取下标值right_index，那么元素个数就是right_index - left_index + 1

        // b.如果等于right下标不存在。
        // 则二分查找算法会返回“大于right的第一个数的下标”，当然这里有个前提：二分查找算法需要使用“lower_bound算法(bitsect.bisect_left)”。
        // 假设“大于right的第一个数的下标”值是bigger_than_right_index, 这里又有两种情况：
        // 1.bigger_than_right_index真实存在。则元素个数就是bigger_than_right_index - left_index (这里不需要减一，因为元素个数不包含bigger_than_right_index本身）
        // 2.bigger_than_right_index不存在。这时bigger_than_right_index是“下标数组的长度”，计算合法的元素个数刚好还是通过bigger_than_right_index - left_index这个公式。

        // a和b两种情况计算最终答案的公式不一样，一个是right_index - left_index + 1, 一个是bigger_than_right_index - left_index。
        // 不如统一一下，a中就算等于right下标存在，我们也可以获取"大于right的第一个数的下标"，这样也可以通过bigger_than_right_index - left_index获取最终结果。

        // 所以，情况b是包含情况a的。
        // 我们只需要获取 "第一个>right的数的下标"，就可以计算正确答案。
        int r = lowerBound(a, right+1);
        int l = lowerBound(a, left);
        return r-l;

    }
    private int lowerBound(List<Integer> a, int target){
        int n = a.size();
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(a.get(mid)<target){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}
