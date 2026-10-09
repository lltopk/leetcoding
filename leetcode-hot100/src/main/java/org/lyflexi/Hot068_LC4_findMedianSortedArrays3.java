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
 * 写法二（小优化）
 * 
 * 实际上，$a_i<= b_{j+1}$ 和 $a_{i+1} > b_j$ 这两个条件不需要都判断，我们只需要判断其中一个即可。为什么？且听我说。
 * 
 * 由于 $a$ 和 $b$ 是有序的，随着 $i$ 的不断变大，$j$ 的不断变小，$a_{i+1} > b_j$ 会从「不成立」变成「成立」。
 * 
 * 把循环条件改成：如果 $a_{i+1} <= b_j$，继续循环；如果 $a_{i+1} > b_j$，退出循环。
 * 
 * 退出循环之后，除了可以说明 $a_{i+1} > b_j$ 成立外，还有一个隐含的性质：在退出循环之前的最后一轮循环，我们在比较哪两个数？正好就是 $a_i$ 和 $b_{j+1}$！并且这两个数的大小关系是 $a_i<= b_{j+1}$。
 */
public class Hot068_LC4_findMedianSortedArrays3 {
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
        a[0] = b[0] = Integer.MIN_VALUE; // 最左边插入 -∞
        a[m + 1] = b[n + 1] = Integer.MAX_VALUE; // 最右边插入 ∞
        System.arraycopy(nums1, 0, a, 1, m); // 数组没法直接插入，只能 copy
        System.arraycopy(nums2, 0, b, 1, n);

        // 枚举 nums1 有 i 个数在第一组
        // 那么 nums2 有 j = (m + n + 1) / 2 - i 个数在第一组
        int i = 0;
        int j = (m + n + 1) / 2;
        while (a[i + 1] <= b[j]) {
            i++; // 继续枚举
            j--;
        }

        int max1 = Math.max(a[i], b[j]); // 第一组的最大值
        int min2 = Math.min(a[i + 1], b[j + 1]); // 第二组的最小值
        return (m + n) % 2 > 0 ? max1 : (max1 + min2) / 2.0;
    }
}
