/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_binary;

/**
 *
 * @author hasee
 * 
 * 911. 在线选举
已解答
中等
相关标签
premium lock icon
相关企业
给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。

对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。

在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。

实现 TopVotedCandidate 类：

TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 
示例：

输入：
["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
输出：
[null, 0, 1, 1, 0, 0, 1]

解释：
TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
topVotedCandidate.q(15); // 返回 0
topVotedCandidate.q(24); // 返回 0
topVotedCandidate.q(8); // 返回 1
 */
public class LC911_TopVotedCandidate {

    int [] helper;//记录每个人的票数, 是个离散数组
    int [] sortedPersons;//升序数组, 值是人, 是个连续数组, 并且可以有重复值
    int [] times;
    public LC911_TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;

        int len = persons.length;
        helper = new int [len];
        sortedPersons = new int [len];
        int maxVotedp = -1;//当前times[i]票数最大的人
        //once loop for preProcess
        for(int i = 0; i <len ;i ++){
            helper[persons[i]]++;

            //平票的时候, 存新人persons[i]
            if(maxVotedp == -1 || helper[persons[i]]>=helper[maxVotedp]){
                maxVotedp = persons[i];
            }

            sortedPersons[i] = maxVotedp;
        }


    }
    
    public int q(int t) {
        // 找到小于等于x的最大时间下标(<=x)
        int l = lowerBound(t+1)-1;
        return sortedPersons[l];
    }

    private int lowerBound(int target){
        int n = times.length;
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(times[mid]<target){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}
