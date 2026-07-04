package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.n_sequences;

/**
 * 1792. 最大平均通过率
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 classes ，其中 classes[i] = [passi, totali] ，表示你提前知道了第 i 个班级总共有 totali 个学生，其中只有 passi 个学生可以通过考试。
 *
 * 给你一个整数 extraStudents ，表示额外有 extraStudents 个聪明的学生，他们 一定 能通过任何班级的期末考。你需要给这 extraStudents 个学生每人都安排一个班级，使得 所有 班级的 平均 通过率 最大 。
 *
 * 一个班级的 通过率 等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率 是所有班级的通过率之和除以班级数目。
 *
 * 请你返回在安排这 extraStudents 个学生去对应班级后的 最大 平均通过率。与标准答案误差范围在 10-5 以内的结果都会视为正确结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * 输出：0.78333
 * 解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
 * 示例 2：
 *
 * 输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * 输出：0.53485
 *
 *
 * 提示：
 *
 * 1 <= classes.length <= 105
 * classes[i].length == 2
 * 1 <= passi <= totali <= 105
 * 1 <= extraStudents <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 39,969/64.9K
 * 通过率
 * 61.6%
 */

import java.util.PriorityQueue;

/**
 * K个单调序列  +  优先级队列
 */
public class LC1792_maxAverageRatio {
    //我们关注的是通过率增量
    private double gain(int p, int t) {
        return (double) (p + 1) / (t + 1) - (double) p / t;
    }
    //事实上, 随着增加的学生越来越多，通过率的增量是递减（非递增）的
    //这相当于给了我们n个单调递减序列, 想象成一个二维数组int[N][], 所有班级的通过率之和的最大值相当于： 给你 n 个递减（非递增）序列，第一项必选（链表头, 初始通过率），然后再从剩余N个链表中选出最大的 extraStudents 个数（增量），计算这 n+extraStudents 数的元素和。
    // 在计算层面我们只是依据增长率做贪心, 但是无需具体求解出增长率, 我们可以先累加增长量, 最后统一计算平均增长率
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(
                        gain(b[0], b[1]),
                        gain(a[0], a[1])
                )
        );
        for(int[] cls: classes){
            pq.offer(cls);
        }

        while (extraStudents-- > 0) {
            int[] cur = pq.poll();
            //关键, 每次加入1个万能学生之后, 分子分母都得加1
            cur[0]++;
            cur[1]++;
            //再入堆, 因为全堆排序是动态调整的
            pq.offer(cur);
        }
        double ret = 0;
        while (!pq.isEmpty()) {
            int[] c = pq.poll();
            ret += (double) c[0] / c[1];
        }
        return ret / classes.length;
    }
}
