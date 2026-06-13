package org.lyflexi.solutions.baseAlgorithm.difference;

/**
 * 3355. 零数组变换 I
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个长度为 n 的整数数组 nums 和一个二维数组 queries，其中 queries[i] = [li, ri]。
 *
 * 对于每个查询 queries[i]：
 *
 * 在 nums 的下标范围 [li, ri] 内选择一个下标 子集。
 * 将选中的每个下标对应的元素值减 1。
 * 零数组 是指所有元素都等于 0 的数组。
 *
 * 如果在按顺序处理所有查询后，可以将 nums 转换为 零数组 ，则返回 true，否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入： nums = [1,0,1], queries = [[0,2]]
 *
 * 输出： true
 *
 * 解释：
 *
 * 对于 i = 0：
 * 选择下标子集 [0, 2] 并将这些下标处的值减 1。
 * 数组将变为 [0, 0, 0]，这是一个零数组。
 * 示例 2：
 *
 * 输入： nums = [4,3,2,1], queries = [[1,3],[0,2]]
 *
 * 输出： false
 *
 * 解释：
 *
 * 对于 i = 0：
 * 选择下标子集 [1, 2, 3] 并将这些下标处的值减 1。
 * 数组将变为 [4, 2, 1, 0]。
 * 对于 i = 1：
 * 选择下标子集 [0, 1, 2] 并将这些下标处的值减 1。
 * 数组将变为 [3, 1, 0, 0]，这不是一个零数组。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= li <= ri < nums.length
 */

/**
 * 差分数组
 */
public class LC3355_isZeroArray {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        //1. 差分数组要用到性质2, 因此长度+1
        int[] diff = new int[n+1];

        //2. 计算差分数组
        for(int[] query: queries){
            diff[query[0]]++;
            diff[query[1]+1]--;
        }

        //3. 对差分数组求和, 代表的是该位置对于nums[i]来说应当减去的次数
        int s = 0;
        for(int i = 0; i<n; i++){
            s += diff[i];
            if(s < nums[i]){//nums[i] - s无法到0
                return false;
            }
        }

        return true;
    }
}
