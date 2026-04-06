package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_middle;

/**
 * 1534. 统计好三元组
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
 *
 * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
 *
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * 其中 |x| 表示 x 的绝对值。
 *
 * 返回 好三元组的数量 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
 * 输出：4
 * 解释：一共有 4 个好三元组：[(3,0,1), (3,0,1), (3,1,1), (0,1,1)] 。
 * 示例 2：
 *
 * 输入：arr = [1,1,2,2,3], a = 0, b = 0, c = 1
 * 输出：0
 * 解释：不存在满足所有条件的三元组。
 *
 *
 * 提示：
 *
 * 3 <= arr.length <= 100
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三变量 枚举中间
 */
public class LC1534_countGoodTriplets {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, ans = 0;
        //技巧1: 想要排序, 又不想要改变原数组的顺序, 可以创建索引数组, 其值是"原数组升序排列"后的索引顺序
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i->i);
        Arrays.sort(idx, (i, j) -> arr[i] - arr[j]);

        //O(n)
        for(int j: idx){
            List<Integer> sortedLeft = new ArrayList<>();
            //O(n)
            for(int i: idx){
                if(i< j && Math.abs(arr[i] - arr[j]) <=a){
                    sortedLeft.add(arr[i]);
                }
            }
            List<Integer> sortedRight = new ArrayList<>();
            //O(n)
            for(int k: idx){
                if(k> j && Math.abs(arr[k] - arr[j]) <=b){
                    sortedRight.add(arr[k]);
                }
            }

            //技巧2: 从两个有序数组sortedLeft,sortedRight, sortedLeft出个x, 在sortedRight中寻找二者差值的绝对值小于等于c的个数
            //等价于遍历sortedLeft, 求和len(<=x+c) - len(<x-c)
            // 复杂度从O(n^2)->O(2n)
            int k1 = 0;
            int k2 = 0;
            for(int x: sortedLeft){
                while(k2<sortedRight.size() && sortedRight.get(k2) <= x+c){
                    k2++;
                }
                while(k1<sortedRight.size() && sortedRight.get(k1) < x-c){
                    k1++;
                }
                ans+=k2-k1;
            }

        }

        return ans;
    }

    //上述整体复杂度O(n^2)
}
