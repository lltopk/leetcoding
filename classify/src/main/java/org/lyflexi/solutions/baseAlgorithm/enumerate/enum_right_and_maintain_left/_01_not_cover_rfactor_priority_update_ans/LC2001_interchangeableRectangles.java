package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_not_cover_rfactor_priority_update_ans;

/**
 * 2001. 可互换矩形的组数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 用一个下标从 0 开始的二维整数数组 rectangles 来表示 n 个矩形，其中 rectangles[i] = [widthi, heighti] 表示第 i 个矩形的宽度和高度。
 *
 * 如果两个矩形 i 和 j（i < j）的宽高比相同，则认为这两个矩形 可互换 。更规范的说法是，两个矩形满足 widthi/heighti == widthj/heightj（使用实数除法而非整数除法），则认为这两个矩形 可互换 。
 *
 * 计算并返回 rectangles 中有多少对 可互换 矩形。
 *
 *
 *
 * 示例 1：
 *
 * 输入：rectangles = [[4,8],[3,6],[10,20],[15,30]]
 * 输出：6
 * 解释：下面按下标（从 0 开始）列出可互换矩形的配对情况：
 * - 矩形 0 和矩形 1 ：4/8 == 3/6
 * - 矩形 0 和矩形 2 ：4/8 == 10/20
 * - 矩形 0 和矩形 3 ：4/8 == 15/30
 * - 矩形 1 和矩形 2 ：3/6 == 10/20
 * - 矩形 1 和矩形 3 ：3/6 == 15/30
 * - 矩形 2 和矩形 3 ：10/20 == 15/30
 * 示例 2：
 *
 * 输入：rectangles = [[4,5],[7,8]]
 * 输出：0
 * 解释：不存在成对的可互换矩形。
 *
 *
 * 提示：
 *
 * n == rectangles.length
 * 1 <= n <= 105
 * rectangles[i].length == 2
 * 1 <= widthi, heighti <= 105
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举技巧: 枚举右, 维护左
 */
public class LC2001_interchangeableRectangles {    public long interchangeableRectangles(int[][] rectangles) {
    int n = rectangles.length;
    long ans = 0;
    Map<Double, Integer> map = new HashMap<>();//记录宽高比的次数
    for(int r = 0; r<n; r++){
        int[] rectangle = rectangles[r];
        if(map.containsKey(rectangle[0]*1.0/rectangle[1])){
            ans+=map.get(rectangle[0]*1.0/rectangle[1]);
        }
        map.merge(rectangle[0]*1.0/rectangle[1], 1 , Integer::sum);
    }

    return ans;
}

}
