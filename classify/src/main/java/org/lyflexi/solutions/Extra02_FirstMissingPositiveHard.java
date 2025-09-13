package org.lyflexi.solutions;

/**
 * @Author: ly
 * @Date: 2024/3/24 10:54
 */
public class Extra02_FirstMissingPositiveHard {
//    以题目中的[3, 4, -1, 1] 为例，最终的数组应当为 [1, -1, 3, 4]，我们就可以知道缺失的数为 2。
//     那怎么将数组进行转换呢？


    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            // 对于遍历到的数 nums[i]，如果 nums[i]∈[1,N]，我们就知道nums[i]应当出现在数组中的nums[i]−1的位置，因此交换 nums[i]和nums[nums[i] - 1]
            // 交换后的nums[nums[i] - 1]，可能还在 [1,N]的范围内，所以我们需要while进行交换操作，
            // 直到 nums[i] = nums[nums[i] - 1] ,结束交换

            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
