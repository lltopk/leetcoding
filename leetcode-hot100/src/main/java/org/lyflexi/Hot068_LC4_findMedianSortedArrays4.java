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
 * 写法一
 * 
 * 注意在数组前面插入元素的时间复杂度是线性的，所以和上面的复杂度分析一样，都是 $O(n+m)$。
 * 
 * 真正满足题目时间复杂度要求的是后面的写法二。
 */
public class Hot068_LC4_findMedianSortedArrays4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            // 交换 nums1 和 nums2，保证下面的 i 可以从 0 开始枚举
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int[] a = new int[m + 2];
        int[] b = new int[n + 2];
        a[0] = b[0] = Integer.MIN_VALUE;
        a[m + 1] = b[n + 1] = Integer.MAX_VALUE;
        System.arraycopy(nums1, 0, a, 1, m);
        System.arraycopy(nums2, 0, b, 1, n);

        // 循环不变量：a[left] <= b[j+1]
        // 循环不变量：a[right] > b[j+1]
        int left = 0;
        int right = m + 1;
        while (left + 1 < right) { // 开区间 (left, right) 不为空
            int i = left + (right - left) / 2;
            int j = (m + n + 1) / 2 - i;
            if (a[i] <= b[j + 1]) {
                left = i; // 缩小二分区间为 (i, right)
            } else {
                right = i; // 缩小二分区间为 (left, i)
            }
        }

        // 此时 left 等于 right-1
        // a[left] <= b[j+1] 且 a[right] > b[(j-1)+1] = b[j]，所以答案是 i=left
        int i = left;
        int j = (m + n + 1) / 2 - i;
        int max1 = Math.max(a[i], b[j]);
        int min2 = Math.min(a[i + 1], b[j + 1]);
        return (m + n) % 2 > 0 ? max1 : (max1 + min2) / 2.0;
    }
}
