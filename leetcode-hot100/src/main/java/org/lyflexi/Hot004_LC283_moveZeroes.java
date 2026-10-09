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
 * 方法一：把 nums 当作栈
 * 
 * 用一个栈记录非零元素。
 * 
 * 看示例 1，$nums=[0,1,0,3,12]$。
 * 
 * |  $i$ | $nums[i]$  |  $nums[i]$ 是否入栈 | 栈 |
 * |---|:---:|:---:|---|
 * | $0$  |  $0$ | 否 | $[]$ |
 * | $1$  |  $1$ | 是 | $[1]$ |
 * | $2$  |  $0$ | 否 | $[1]$ |
 * | $3$  |  $3$ | 是 | $[1,3]$ |
 * | $4$  |  $12$ | 是 | $[1,3,12]$ |
 * 
 * 最后，在栈的末尾添加两个 $0$，即为答案 $[1,3,12,0,0]$。
 * 
 * 为了做到 $O(1)$ 空间复杂度，直接把 $nums$ 当作栈，用一个变量 $stackSize$ 表示栈的大小，初始值为 $0$。
 * 
 * 入栈就是把 $nums[stackSize]$ 置为 $nums[i]$，然后把 $stackSize$ 加一。
 * 
 * 最后把 $nums$ 中的下标从 $stackSize$ 到 $n-1$ 的数都置为 $0$。
 */
public class Hot004_LC283_moveZeroes {
    public void moveZeroes(int[] nums) {
        int stackSize = 0;
        for (int x : nums) {
            if (x != 0) {
                nums[stackSize++] = x; // 把 x 入栈
            }
        }
        Arrays.fill(nums, stackSize, nums.length, 0);
    }
}
