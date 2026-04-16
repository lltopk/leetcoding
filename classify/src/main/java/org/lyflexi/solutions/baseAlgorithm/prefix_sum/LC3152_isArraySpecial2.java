package org.lyflexi.solutions.baseAlgorithm.prefix_sum;

/**
 * 3152. 特殊数组 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 *
 * 你有一个整数数组 nums 和一个二维整数矩阵 queries，对于 queries[i] = [fromi, toi]，请你帮助你检查 子数组 nums[fromi..toi] 是不是一个 特殊数组 。
 *
 * 返回布尔数组 answer，如果 nums[fromi..toi] 是特殊数组，则 answer[i] 为 true ，否则，answer[i] 为 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,1,2,6], queries = [[0,4]]
 *
 * 输出：[false]
 *
 * 解释：
 *
 * 子数组是 [3,4,1,2,6]。2 和 6 都是偶数。
 *
 * 示例 2：
 *
 * 输入：nums = [4,3,1,6], queries = [[0,2],[2,3]]
 *
 * 输出：[false,true]
 *
 * 解释：
 *
 * 子数组是 [4,3,1]。3 和 1 都是奇数。因此这个查询的答案是 false。
 * 子数组是 [1,6]。只有一对：(1,6)，且包含了奇偶性不同的数字。因此这个查询的答案是 true。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 */

/**
 * 前缀和+预处理数组+位运算
 */
public class LC3152_isArraySpecial2 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length, newLen = n-1;
        //预处理数组, 将长度为n的nums数组转换为n-1对奇偶对判断
        int[] a = new int[newLen];
        for(int i = 0; i<n-1;i++){
            //第二个^1操作是对末尾的二进制位取反
            //最后的&1操作是用来对两次异或后的结果求模2
            a[i] = (nums[i+1] ^ nums[i] ^1) & 1;
        }

        //左闭右开公式
        int[] preS = new int[newLen+1];
        for(int i = 0; i<newLen; i++){
            preS[i+1] = preS[i] + a[i];
        }

        boolean[] ans = new boolean[queries.length];
        int idx = 0;
        for(int[] query: queries){
            //对应区间query长度-1个元素a[i], a[]的区间和恰好是preS[query[1]] - preS[query[0]], query[1]不用再加1了
            //如nums[x1,x2,x3], 对应于a[y1,y2]
            ans[idx++] = (preS[query[1]] - preS[query[0]])>0? false: true;
        }

        return ans;
    }
}
