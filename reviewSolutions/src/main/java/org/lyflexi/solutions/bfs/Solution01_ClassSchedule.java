package org.lyflexi.solutions.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/3/24 14:18
 */

/*
* 207. 课程表
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。



示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
*
示例 2：
输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
* */
public class Solution01_ClassSchedule {
    // 存储有向图，索引代表当前节点，形如
    // 0| 1 2 3
    // 4| 5 6 7
    // 8| 9 10 11
    List<List<Integer>> edges;
    // 存储每个节点的入度，入度计数
    int[] indegree;


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        //设置图的最大容量或许用不完但没关系
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        //入度数组，容量为numCourses
        indegree = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indegree[info[0]];
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            //课程编号就是0~numCourses-1
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        //遍历到的课程数量
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                --indegree[v];
                //在任何时候（包括初始情况）环上的节点的入度都不会为0，所以这些节点永远不会被加入队列~
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        //如果图中有环，visited 一定小于 numCourses。有环至少存在一个顶点其入度始终不为0，始终无法被访问
        return visited == numCourses;
    }
}
