package org.lyflexi;

import java.util.*;

/**
 * 215. 数组中的第K个最大元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 
 * 示例 1:
 * 
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 
 * 示例 2:
 * 
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * 
 * 提示：
 * 
 * - 1 <= k <= nums.length <= 10^5
 * 
 * - -10^4 <= nums[i] <= 10^4
 */

/**
 * 核心思路
 * 
 * 第 $k$ 大元素在升序数组中的下标是 $n-k$。
 * 
 * 1. 在 $nums$ 中随机选择一个基准元素 $pivot$。关于为什么要随机，见文末答疑。
 * 2. 划分 $nums$。通过交换，把 $<pivot$ 的元素放在 $pivot$ 的左侧，把 $>= pivot$ 的元素放在 $pivot$ 的右侧。如此划分可以让我们粗略地排序 $nums$。划分后，$pivot$ 此刻的位置就等于 $pivot$ 在升序数组中的位置。
 * 3. 设 $pivot$ 在 $nums$ 中的下标为 $i$。
 *     - 如果 $i = n-k$，那么答案就是 $pivot$。
 *     - 如果 $i > n-k$，说明答案在 $pivot$ 左侧，我们在其中寻找，回到第一步。
 *     - 如果 $i < n-k$，说明答案在 $pivot$ 右侧，我们在其中寻找，回到第一步。
 *     - 这类似 二分查找，只要我们每次能把问题的规模缩小一半，就可以用 $O(n)$ 时间解决（见复杂度分析）。
 *     - 问题规模缩小后，相当于在 $nums$ 的一个子数组中，继续划分子数组，寻找答案。
 * 
 * 然而，如果按照 $<pivot$ 和 $>=pivot$ 划分数组，这个做法会在数组包含大量重复元素时，划分后的 $i$ 往往是子数组第一个元素的下标，算法会退化至 $O(n^2)$。
 * 
 * 解决办法：修改第二步，把 $<$ 改成 $<=$，也就是把 $<= pivot$ 的元素放在 $pivot$ 的左侧，把 $>=pivot$ 的元素放在 $pivot$ 的右侧。特别地，如果子数组所有元素都相同，这样做可以完美地返回子数组的中心下标（见代码），避免复杂度退化。
 * 
 * 具体要如何交换元素？实现细节见代码注释。
 */
public class Hot074_LC215_findKthLargest {
    private static final Random rand = new Random();

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int targetIndex = n - k; // 第 k 大元素在升序数组中的下标是 n - k
        int left = 0;
        int right = n - 1; // 闭区间
        while (true) {
            int i = partition(nums, left, right);
            if (i == targetIndex) {
                // 找到第 k 大元素
                return nums[i];
            }
            if (i > targetIndex) {
                // 第 k 大元素在 [left, i - 1] 中
                right = i - 1;
            } else {
                // 第 k 大元素在 [i + 1, right] 中
                left = i + 1;
            }
        }
    }

    // 在子数组 [left, right] 中随机选择一个基准元素 pivot
    // 根据 pivot 重新排列子数组 [left, right]
    // 重新排列后，<= pivot 的元素都在 pivot 的左侧，>= pivot 的元素都在 pivot 的右侧
    // 返回 pivot 在重新排列后的 nums 中的下标
    // 特别地，如果子数组的所有元素都等于 pivot，我们会返回子数组的中心下标，避免退化
    private int partition(int[] nums, int left, int right) {
        // 1. 在子数组 [left, right] 中随机选择一个基准元素 pivot
        int i = left + rand.nextInt(right - left + 1);
        int pivot = nums[i];
        // 把 pivot 与子数组第一个元素交换，避免 pivot 干扰后续划分，从而简化实现逻辑
        swap(nums, i, left);

        // 2. 相向双指针遍历子数组 [left + 1, right]
        // 循环不变量：在循环过程中，子数组的数据分布始终如下图
        // [ pivot | <=pivot | 尚未遍历 | >=pivot ]
        //   ^                 ^     ^         ^
        //   left              i     j         right

        i = left + 1;
        int j = right;
        while (true) {
            while (i <= j && nums[i] < pivot) {
                i++;
            }
            // 此时 nums[i] >= pivot

            while (i <= j && nums[j] > pivot) {
                j--;
            }
            // 此时 nums[j] <= pivot

            if (i >= j) {
                break;
            }

            // 维持循环不变量
            swap(nums, i, j);
            i++;
            j--;
        }

        // 循环结束后
        // [ pivot | <=pivot | >=pivot ]
        //   ^             ^   ^     ^
        //   left          j   i     right

        // 3. 把 pivot 与 nums[j] 交换，完成划分（partition）
        // 为什么与 j 交换？
        // 如果与 i 交换，可能会出现 i = right + 1 的情况，已经下标越界了，无法交换
        // 另一个原因是如果 nums[i] > pivot，交换会导致一个大于 pivot 的数出现在子数组最左边，不是有效划分
        // 与 j 交换，即使 j = left，交换也不会出错
        swap(nums, left, j);

        // 交换后
        // [ <=pivot | pivot | >=pivot ]
        //               ^
        //               j

        // 返回 pivot 的下标
        return j;
    }

    // 交换 nums[i] 与 nums[j]
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
