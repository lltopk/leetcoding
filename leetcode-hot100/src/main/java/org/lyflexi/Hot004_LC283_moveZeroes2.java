package org.lyflexi;

import java.util.*;

/**
 * 283. 移动零
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 
 * 示例 1:
 * 
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 
 * 示例 2:
 * 
 * 输入: nums = [0]
 * 输出: [0]
 * 
 * 提示:
 * 
 * - 1 <= nums.length <= 10^4
 * 
 * - -2^31 <= nums[i] <= 2^31 - 1
 * 
 * 进阶：你能尽量减少完成的操作次数吗？
 */

/**
 * 答疑
 * 
 * 问：如果 $nums$ 的前几个数都不是 $0$ 呢？
 * 
 * 答：$start_0$ 会和 $i$ 同时向右移动，直到遇到 $0$（或者到达数组末尾）为止。
 */
public class Hot004_LC283_moveZeroes2 {
    public void moveZeroes(int[] nums) {
        /*
        循环不变量：在循环过程中，nums 的数据分布始终如下图
        [ 非零元素 | 零元素 | 尚未遍历 ]
                    ^       ^
                    start0  i
        */
        int start0 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 交换 nums[i] 和 nums[start0]
                int tmp = nums[i];
                nums[i] = nums[start0];
                nums[start0] = tmp;
                start0++;
            }
        }
    }
}
