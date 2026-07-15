package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers.floyd_cycle;

/**
 * 287. 寻找重复数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3 :
 *
 * 输入：nums = [3,3,3,3,3]
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 666,223/981.9K
 * 通过率
 * 67.8%
 */

/**
 * Floyd判圈算法
 */
public class LC287_findDuplicate {
    /**
     用当前idx节点指向->nums[idx]节点来建图
     然后使用Floyd判圈算法: 快慢指针, 如果有环, 则慢指针与快指针一定会在环中相遇
     */
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        do{
            fast = nums[nums[fast]];
            slow = nums[slow];
        }while(fast != slow);

        //重新定义个起点指针, Floyd判圈算法必定会使得p和slow在打结点相遇
        int p = 0;
        while(p != slow){
            p = nums[p];
            slow = nums[slow];
        }
        return p;
    }
}
