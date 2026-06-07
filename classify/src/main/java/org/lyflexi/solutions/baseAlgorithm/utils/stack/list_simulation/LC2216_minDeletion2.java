package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 2216. 美化数组的最少删除数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
 *
 * nums.length 为偶数
 * 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
 * 注意，空数组同样认为是美丽数组。
 *
 * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
 *
 * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,3,5]
 * 输出：1
 * 解释：可以删除 nums[0] 或 nums[1] ，这样得到的 nums = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。
 * 示例 2：
 *
 * 输入：nums = [1,1,2,2,3,3]
 * 输出：2
 * 解释：可以删除 nums[0] 和 nums[5] ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 用栈来做, 用列表模拟栈, 最后栈中的元素就是收集的答案, 然后用原数组长度做差
 *
 * 策略是保留第一个偶数索引
 */
public class LC2216_minDeletion2 {
    public int minDeletion(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        for(int num: nums){
            if(! stack.isEmpty() && (stack.size() & 1) == 1 && stack.get(stack.size() - 1) == num){
                //当前栈顶索引为偶数, 保持即可
            }else{
                stack.add(num);
            }
        }

        //题目要求答案长度必须是偶数, 如果是奇数则再删除一个数即可
        int n = (stack.size() & 1) == 1? stack.size() - 1: stack.size();
        return nums.length - n;
    }
}
