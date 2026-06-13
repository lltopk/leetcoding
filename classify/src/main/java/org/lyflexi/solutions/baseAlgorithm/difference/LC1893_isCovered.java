package org.lyflexi.solutions.baseAlgorithm.difference;

/**
 * 1893. 检查是否区域内所有整数都被覆盖
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
 *
 * 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
 *
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 * 示例 2：
 *
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *
 *
 * 提示：
 *
 * 1 <= ranges.length <= 50
 * 1 <= starti <= endi <= 50
 * 1 <= left <= right <= 50
 */

/**
 * 差分数组
 */
public class LC1893_isCovered {
    public boolean isCovered(int[][] ranges, int left, int right) {
        //求覆盖次数, 这可以用差分数组, 设其原数组为任意点位中, 被任意区间覆盖的次数

        int N = 51;
        //1. 确定差分数组长度, 由于用到性质2, 因此长度再加1
        int[] diff = new int[N+1];
        for(int[] range: ranges){
            diff[range[0]]++;
            diff[range[1]+1]--;
        }

        int s = 0;
        for(int i=0; i<N+1; i++){
            s+=diff[i];
            if(i>=left && i<=right && s<=0){
                return false;
            }
        }

        return true;

    }
}
