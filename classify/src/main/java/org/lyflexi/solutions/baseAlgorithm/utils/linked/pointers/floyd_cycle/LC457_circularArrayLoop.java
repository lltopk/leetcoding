package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers.floyd_cycle;

/**
 * 457. 环形数组是否存在循环
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 *
 * 如果 nums[i] 是正数，向前（下标递增方向）移动 nums[i] 步
 * 如果 nums[i] 是负数，向后（下标递减方向）移动 abs(nums[i]) 步
 * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 *
 * 数组中的 循环 由长度为 k 的下标序列 seq 标识：
 *
 * 遵循上述移动规则将导致一组重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * k > 1
 * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,-1,1,2,2]
 * 输出：true
 * 解释：图片展示了节点间如何连接。白色节点向前跳跃，而红色节点向后跳跃。
 * 我们可以看到存在循环，按下标 0 -> 2 -> 3 -> 0 --> ...，并且其中的所有节点都是白色（以相同方向跳跃）。
 * 示例 2：
 *
 *
 * 输入：nums = [-1,-2,-3,-4,-5,6]
 * 输出：false
 * 解释：图片展示了节点间如何连接。白色节点向前跳跃，而红色节点向后跳跃。
 * 唯一的循环长度为 1，所以返回 false。
 * 示例 3：
 *
 *
 * 输入：nums = [1,-1,5,1,4]
 * 输出：true
 * 解释：图片展示了节点间如何连接。白色节点向前跳跃，而红色节点向后跳跃。
 * 我们可以看到存在循环，按下标 0 --> 1 --> 0 --> ...，当它的大小大于 1 时，它有一个向前跳的节点和一个向后跳的节点，所以 它不是一个循环。
 * 我们可以看到存在循环，按下标 3 --> 4 --> 3 --> ...，并且其中的所有节点都是白色（以相同方向跳跃）。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 *
 *
 * 进阶：你能设计一个时间复杂度为 O(n) 且额外空间复杂度为 O(1) 的算法吗？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 42,665/96.6K
 * 通过率
 * 44.2%
 */

/**
 * 枚举每个nums[i], 并以nums[i]为起点建图
 *
 * 然后用Floyd判断是否存在环
 */
public class LC457_circularArrayLoop {
    /**
     就地建图, 当前位置指向下一个位置, 每个位置都作为一个节点
     Floyd判断成环
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //从当前遍历节点i开始建图
            int slow = i, fast = i;
            // slow只是跟随者, 因此要判断的是fast+1, fast+2是否和nums[i]同号
            // 换言之只有判断fast+1, fast+2都与nums[i]同号, slow才有资格跟随
            while (nums[i] * nums[next(nums, fast)] > 0 && nums[i] * nums[next(nums, next(nums, fast))] > 0) {
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
                if (slow == fast) {
                    //题目要求环不能只有1个节点
                    if (slow != next(nums, slow)) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n; // 保证返回值在 [0,n) 中
    }
}
