package org.lyflexi.solutions.baseAlgorithm.prefix_sum.dis_sum;

/**
 * 2615. 等值距离和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 。现有一个长度等于 nums.length 的数组 arr 。对于满足 nums[j] == nums[i] 且 j != i 的所有 j ，arr[i] 等于所有 |i - j| 之和。如果不存在这样的 j ，则令 arr[i] 等于 0 。
 *
 * 返回数组 arr 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,1,1,2]
 * 输出：[5,0,3,4,0]
 * 解释：
 * i = 0 ，nums[0] == nums[2] 且 nums[0] == nums[3] 。因此，arr[0] = |0 - 2| + |0 - 3| = 5 。
 * i = 1 ，arr[1] = 0 因为不存在值等于 3 的其他下标。
 * i = 2 ，nums[2] == nums[0] 且 nums[2] == nums[3] 。因此，arr[2] = |2 - 0| + |2 - 3| = 3 。
 * i = 3 ，nums[3] == nums[0] 且 nums[3] == nums[2] 。因此，arr[3] = |3 - 0| + |3 - 2| = 4 。
 * i = 4 ，arr[4] = 0 因为不存在值等于 2 的其他下标。
 * 示例 2：
 *
 * 输入：nums = [0,5,3]
 * 输出：[0,0,0]
 * 解释：因为 nums 中的元素互不相同，对于所有 i ，都有 arr[i] = 0 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 *
 *
 * 注意：本题与 2121. 相同元素的间隔之和 相同。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用前缀和解决距离和问题
 */
public class LC2615_distance {
    //分组加前缀和, 归一计算
    public long[] distance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for(int i =0; i<n; i++){
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        long[] ans = new long[n];
        for(List<Integer> list: map.values()){
            //分组后的list是有序的, 值为原数组索引
            int size = list.size();
            long[] preS = new long[size+1];
            for(int i =0;i<size; i++){
                preS[i+1] = preS[i] + list.get(i);
            }
            //由于list是有序的, 因此距离和计算可以去掉绝对值, 合并计算
            for(int i =0;i<size; i++){
                int target = list.get(i);
                //求前i个距离和
                long sum1 = (long)target*i - preS[i];
                //求后size-i个距离和
                long sum2 = preS[size] - preS[i] - (long)target*(size - i);
                ans[target] = sum1+sum2;
            }
        }
        return ans;
    }
}
