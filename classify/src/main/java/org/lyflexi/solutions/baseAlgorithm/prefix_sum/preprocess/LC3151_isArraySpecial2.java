package org.lyflexi.solutions.baseAlgorithm.prefix_sum.preprocess;

/**
 * 3151. 特殊数组 I
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组。换句话说，每一对中的元素 必须 有一个是偶数，另一个是奇数。
 *
 * 你有一个整数数组 nums。如果 nums 是一个 特殊数组 ，返回 true，否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1]
 *
 * 输出：true
 *
 * 解释：
 *
 * 只有一个元素，所以答案为 true。
 *
 * 示例 2：
 *
 * 输入：nums = [2,1,4]
 *
 * 输出：true
 *
 * 解释：
 *
 * 只有两对相邻元素： (2,1) 和 (1,4)，它们都包含了奇偶性不同的数字，因此答案为 true。
 *
 * 示例 3：
 *
 * 输入：nums = [4,3,1,6]
 *
 * 输出：false
 *
 * 解释：
 *
 * nums[1] 和 nums[2] 都是奇数。因此答案为 false。
 *
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */

/**
 * 前缀和, 预处理前缀和, 位运算
 */
public class LC3151_isArraySpecial2 {
    public boolean isArraySpecial(int[] nums) {
        int n = nums.length, newLen = n-1;
        //预处理数组, 将长度为n的nums数组转换为n-1对奇偶对判断
        int[] a = new int[newLen];
        for(int i = 0; i<n-1;i++){
            //第二个^1操作是对两数异或后末尾二进制位取反
            //最后的&1操作是用来对上述结果求模2
            a[i] = (nums[i+1] ^ nums[i] ^1) & 1;
        }

        //左闭右开公式
        int[] preS = new int[newLen+1];
        for(int i = 0; i<newLen; i++){
            preS[i+1] = preS[i] + a[i];
        }

        //总共n-1个元素a[i], 区间和a[0,n-1]恰好是preS[n] - preS[0], n不用再加1了
        return (preS[n-1] - preS[0])>0? false: true;
    }
}
