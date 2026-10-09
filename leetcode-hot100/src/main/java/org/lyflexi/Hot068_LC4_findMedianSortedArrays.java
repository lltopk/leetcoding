package org.lyflexi;

import java.util.*;

/**
 * 4. 寻找两个正序数组的中位数
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 
 * 示例 1：
 * 
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 
 * 示例 2：
 * 
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 
 * 提示：
 * 
 * - nums1.length == m
 * 
 * - nums2.length == n
 * 
 * - 0 <= m <= 1000
 * 
 * - 0 <= n <= 1000
 * 
 * - 1 <= m + n <= 2000
 * 
 * - -10^6 <= nums1[i], nums2[i] <= 10^6
 */

/**
 * 一、引入：均匀分组
 * 
 * 这里的关键是「均匀分组」，每组 $5$ 个数，只要第一组的最大值 $<=$ 第二组的最小值，我们就找到了答案。
 * 
 * 怎么想到要均匀分组的？请看百科中关于中位数的介绍：
 * 中位数……可将数值集合划分为大小相等的两部分。
 * 
 * 设 $merged$ 为 $a+b$ 排序后的数组。
 * 
 * 第 $k$ 小的数在 $merged$ 中的下标为 $k-1$，也就是 $<=ft\lceil\dfrac{m+n}{2}\right\rceil - 1 = <=ft\lfloor\dfrac{m+n+1}{2}\right\rfloor - 1 = <=ft\lfloor\dfrac{m+n-1}{2}\right\rfloor$。
 */
public class Hot068_LC4_findMedianSortedArrays {
    public double findMedianSortedArrays(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] merged = new int[m + n];
        System.arraycopy(a, 0, merged, 0, m);
        System.arraycopy(b, 0, merged, m, n);
        Arrays.sort(merged);

        int s = m + n;
        int k = (s - 1) / 2;
        return s % 2 > 0 ? merged[k] : (merged[k] + merged[k + 1]) / 2.0;
    }
}
