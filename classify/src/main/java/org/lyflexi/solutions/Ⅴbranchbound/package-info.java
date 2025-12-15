package org.lyflexi.solutions.Ⅴbranchbound;

/**
 * 分支界限法:
 *
 * 分支限界法（Branch and BoundMethod）是数理科学领域的一种算法，采用广度优先或最小耗费（最大效益）优先的方式搜索解空间树，用于寻找满足约束条件的最优解
 *
 * 举例 B&B 四要素：LeetCode 1091. 二进制矩阵中的最短路径（Shortest Path in Binary Matrix）
 * 1.分支（Branching）   每个格子生成最多 8 个邻居（下一步可能位置）
 * 2.限界（Bounding）	   如果某个格子已经被访问过，说明已有更短或相等的路径到达它 → 新路径不可能更优，直接剪枝
 * 3.搜索策略	           使用队列（FIFO），即广度优先，保证第一次到达终点就是最短路径
 * 4.无回溯	           一旦访问一个格子，就标记为已访问，永不撤销（不是 DFS，不恢复状态）
 */

