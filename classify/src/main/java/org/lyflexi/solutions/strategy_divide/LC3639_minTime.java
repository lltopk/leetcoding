/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

/**
 *
 * @author hasee
 * 
 * 3639. 变为活跃状态的最小时间
已解答
中等
相关标签
premium lock icon
相关企业
提示
给你一个长度为 n 的字符串 s 和一个整数数组 order，其中 order 是范围 [0, n - 1] 内数字的一个 排列。

从时间 t = 0 开始，在每个时间点，将字符串 s 中下标为 order[t] 的字符替换为 '*'。

如果 子字符串 包含 至少 一个 '*' ，则认为该子字符串有效。

如果字符串中 有效子字符串 的总数大于或等于 k，则称该字符串为 活跃 字符串。

返回字符串 s 变为 活跃 状态的最小时间 t。如果无法变为活跃状态，返回 -1。

 

示例 1:

输入: s = "abc", order = [1,0,2], k = 2

输出: 0

解释:

t	order[t]	修改后的 s	有效子字符串	计数	激活状态
(计数 >= k)
0	1	"a*c"	"*", "a*", "*c", "a*c"	4	是
字符串 s 在 t = 0 时变为激活状态。因此，答案是 0。

示例 2:

输入: s = "cat", order = [0,2,1], k = 6

输出: 2

解释:

t	order[t]	修改后的 s	有效子字符串	计数	激活状态
(计数 >= k)
0	0	"*at"	"*", "*a", "*at"	3	否
1	2	"*a*"	"*", "*a", "*a*", "a*", "*"	5	否
2	1	"***"	所有子字符串(包含 '*')	6	是
字符串 s 在 t = 2 时变为激活状态。因此，答案是 2。

示例 3:

输入: s = "xy", order = [0,1], k = 4

输出: -1

解释:

即使完成所有替换，也无法得到 k = 4 个有效子字符串。因此，答案是 -1。

 

提示:

1 <= n == s.length <= 105
order.length == n
0 <= order[i] <= n - 1
s 由小写英文字母组成。
order 是从 0 到 n - 1 的整数排列。
1 <= k <= 109
 */
public class LC3639_minTime {

    public int minTime(String s, int[] order, int k) {
        int len = s.length();

        //对字符串修改字符api有点记忆负担并且性能差, 因此用标记数组来替代
        int [] mark = new int[len];
        for(int i =0;i<len;i++){
            mark[i] = -1;
        }
        //时间越长, 星号越多, 带*子串数量大于k的可能性越大, 因此视时间t为二分元素
        int l = 0, r = len;

        //特殊情况, 所有字符都是* , 这是个等差数列, 即以i(lastM==i本身)为终点的连续字串的个数为i+1
        long maxProvider = (long)len*((0+1)+(len-1+1))/2;
        if(maxProvider < k){
            return -1;
        }

        while(l<r){
            int mid = l + ((r-l)>>1);
            if(checkInc(mid, mark, order, k)){
                l = mid + 1;
            }else{
                r = mid;
            }
        }

        return l;
    }

    private boolean checkInc(int mid , int[] mark, int[] order, int k){
        for (int i = 0; i <=mid; i++) {
            mark[order[i]] = mid;
        }
        long cnt = 0;
        int lastM = -1;

        //整个数组的子问题解累加
        for(int j =0; j<mark.length; j++){
            if(mark[j] == mid){
                lastM = j;
            }
            //以j(前面最近的*位置是lastM)为终点的连续子串的个数: lastM+1
            //[0,j],[1,j],[2,j],…,[last,j]
            cnt += lastM+1;
        }
        return cnt<k;
    }

    /**
     * 每次check清空辅助数组: Arrays.fill(mark, -1);
     */
//    public int minTime(String s, int[] order, int k) {
//        int len = s.length();
//
//        //对字符串修改字符api有点记忆负担并且性能差, 因此用标记数组来替代
//        int [] mark = new int[len];
//        for(int i =0;i<len;i++){
//            mark[i] = -1;
//        }
//        //时间越长, 星号越多, 带*子串数量大于k的可能性越大, 因此视时间t为二分元素
//        int l = 0, r = len;
//
//        //特殊情况, 所有字符都是* , 这是个等差数列, 即以i(lastM==i本身)为终点的连续字串的个数为i+1
//        long maxProvider = (long)len*((0+1)+(len-1+1))/2;
//        if(maxProvider < k){
//            return -1;
//        }
//
//        while(l<r){
//            int mid = l + ((r-l)>>1);
//            if(checkInc(mid, mark, order, k)){
//                l = mid + 1;
//            }else{
//                r = mid;
//            }
//        }
//
//        return l;
//    }
//
//    private boolean checkInc(int mid , int[] mark, int[] order, int k){
//        Arrays.fill(mark, -1);
//        for (int i = 0; i <=mid; i++) {
//            mark[order[i]] = 1;
//        }
//        long cnt = 0;
//        int lastM = -1;
//
//        //整个数组的子问题解累加
//        for(int j =0; j<mark.length; j++){
//            if(mark[j] == 1){
//                lastM = j;
//            }
//            //以j(前面最近的*位置是lastM)为终点的连续子串的个数: lastM+1
//            //[0,j],[1,j],[2,j],…,[last,j]
//            cnt += lastM+1;
//        }
//        return cnt<k;
//    }

}
