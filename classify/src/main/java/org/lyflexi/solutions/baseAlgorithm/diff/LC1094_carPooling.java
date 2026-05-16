package org.lyflexi.solutions.baseAlgorithm.diff;

/**
 * 1094. 拼车
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 *
 * 给定整数 capacity 和一个数组 trips ,  trips[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 *
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * 示例 2：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 */

/**
 * 差分数组, 性质1
 */
public class LC1094_carPooling {
    //差分数组
    public boolean carPooling(int[][] trips, int capacity) {
        //1. 确定差分数组的长度, 题目中给定的0 <= fromi < toi <= 1000
        int N = 1001;
        int[] diff = new int[1001];

        //2. 计算差分数组
        //差分数组只关注from和to点位, 其他点位无需计算因为是保持不变的
        // 由于差分数组的用处是累加还原回原数组, 因此非from和to的点位用0表示即可
        for(int[] trip: trips){
            int x = trip[0], from = trip[1], to = trip[2];
            diff[from] += x;
            diff[to] -=x;
        }

        //3. 差分数组求和, 还原原数组
        int s = 0;//空间优化, 不需要完全存储原数组
        for(int x: diff){
            s+=x;
            if(s > capacity){
                return false;
            }
        }

        return true;
    }
}
