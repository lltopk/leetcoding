package org.lyflexi.solutions.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/3/24 14:18
 */

/*
* 210. 课程表 II
现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。


示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
*
示例 2：
输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
*
示例 3：
输入：numCourses = 1, prerequisites = []
输出：[0]

* */
public class Solution02_ClassScheduleⅡ {
    // 存储有向图，索引代表当前节点，形如
    // 0| 1 2 3
    // 4| 5 6 7
    // 8| 9 10 11
    List<List<Integer>> edges;
    // 存储每个节点的入度，入度计数
    int[] indegree;

    // 存储答案
    int[] answer;
    // 答案下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        answer = new int[numCourses];
        int index = 0;

        edges = new ArrayList<List<Integer>>();
        // 设置图的最大容量或许用不完但没关系
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        // 入度数组，容量为numCourses
        indegree = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indegree[info[0]];
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            // 课程编号就是0~numCourses-1
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 遍历到的课程数量
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            answer[index++] = u;
            for (int v : edges.get(u)) {
                --indegree[v];
                // 在任何时候（包括初始情况）环上的节点的入度都不会为0，所以这些节点永远不会被加入队列~
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if(index!=numCourses){
            return new int[]{};
        }
        return answer;
    }
}

