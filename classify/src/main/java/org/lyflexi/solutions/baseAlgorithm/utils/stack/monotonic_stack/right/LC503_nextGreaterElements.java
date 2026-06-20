package org.lyflexi.solutions.baseAlgorithm.utils.stack.monotonic_stack.right;

/**
 * 503. 下一个更大元素 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 *
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,3,4,3]
 * 输出: [2,3,4,-1,4]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 339,091/490K
 * 通过率
 * 69.2%
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 单调栈, 从右往左维护, 求right[]
 */
public class LC503_nextGreaterElements {
    public int[] nextGreaterElements(int[] nums) {
        int[] numsNew = new int[nums.length*2];
        for(int i = 0; i< nums.length; i++){
            numsNew[i] = numsNew[i+nums.length]= nums[i];
        }
        int[] right = new int[nums.length * 2];
        Arrays.fill(right, -1);
        Deque<Integer> sk = new ArrayDeque<>();
        for(int i = numsNew.length - 1; i>=0; i--){
            while(! sk.isEmpty() && numsNew[sk.peek()] <= numsNew[i]){
                sk.pop();
            }
            if(! sk.isEmpty()){
                right[i] = numsNew[sk.peek()];
            }
            sk.push(i);
        }

        int[] ret = new int[nums.length];
        for(int i = 0; i< nums.length; i++){
            ret[i] = right[i];
        }
        return ret;
    }
}
