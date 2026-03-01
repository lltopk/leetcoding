package org.lyflexi.solutions.strategy_binary;

/**
 1870. 准时到达的列车最小时速
 已解答
 中等
 相关标签
 premium lock icon
 相关企业
 提示
 给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。

 每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。

 例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
 返回能满足你在时限前到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。

 生成的测试用例保证答案不超过 107 ，且 hour 的 小数点后最多存在两位数字 。



 示例 1：

 输入：dist = [1,3,2], hour = 6
 输出：1
 解释：速度为 1 时：
 - 第 1 趟列车运行需要 1/1 = 1 小时。
 - 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。
 - 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3 趟列车运行需要 2/1 = 2 小时。
 - 你将会恰好在第 6 小时到达。
 示例 2：

 输入：dist = [1,3,2], hour = 2.7
 输出：3
 解释：速度为 3 时：
 - 第 1 趟列车运行需要 1/3 = 0.33333 小时。
 - 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。
 - 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3 趟列车运行需要 2/3 = 0.66667 小时。
 - 你将会在第 2.66667 小时到达。
 示例 3：

 输入：dist = [1,3,2], hour = 1.9
 输出：-1
 解释：不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。


 提示：

 n == dist.length
 1 <= n <= 105
 1 <= dist[i] <= 105
 1 <= hour <= 109
 hours 中，小数点后最多存在两位数字
 */

/**
 * 二分答案
 * 求下界公式a/b
 * 求上界公式 (a+b-1)/b
 * 求真实值公式a/(b*1.0) 或者 (double)a/b
 */
public class LC1870_minSpeedOnTime {
    public int minSpeedOnTime(int[] dist, double hour) {
        //[1, maxDist+1)
        int l = 0;
        int r = 1000000000+1;

        //无解, 比如1.9  dist.length == 3
        if (hour <= dist.length - 1) {
            return -1;
        }
        while(l + 1<r){
            int mid = l + ((r-l)>>1);
            if(checkInc(mid, dist, hour)){
                l = mid;
            }else{
                r = mid;
            }
        }

        return r ;
    }

    private boolean checkInc(int mid, int[] dist, double hour){
        double sumT = 0;
        for(int i = 0; i<dist.length-1; i++){
            sumT += (dist[i]+mid-1)/mid;
        }
        //最后一段要求真实值, 如2/3 = 0.66667, 此时需要2/3.0 或者 (double)2/3
        return sumT+ dist[dist.length-1]/(mid*1.0) > hour;

    }
}
