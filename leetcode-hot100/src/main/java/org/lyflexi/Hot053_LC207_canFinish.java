package org.lyflexi;

import java.util.*;

/**
 * 207. 课程表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [a_i, b_i] ，表示如果要学习课程 a_i 则 必须 先学习课程  b_i_ 。
 * 
 * - 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 
 * 示例 1：
 * 
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 
 * 示例 2：
 * 
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * 
 * 提示：
 * 
 * - 1 <= numCourses <= 2000
 * 
 * - 0 <= prerequisites.length <= 5000
 * 
 * - prerequisites[i].length == 2
 * 
 * - 0 <= a_i, b_i < numCourses
 * 
 * - prerequisites[i] 中的所有课程对 互不相同
 */

/**
 * 具体思路
 * 
 * 对于每个节点 $x$，都定义三种颜色值（状态值）：
 * 
 * $0$：节点 $x$ 尚未被访问到。
 * $1$：节点 $x$ 正在访问中，$dfs(x)$ 尚未结束。
 * $2$：节点 $x$ 已经完全访问完毕。注意这还说明从 $x$ 出发无法找到环。所以当我们遇到状态值为 $2$ 的节点 $x$ 时，无需递归 $x$。
 * 
 * ⚠误区：不能只用两种状态表示节点「没有访问过」和「访问过」。如上图，我们先 DFS 访问 $0\to 1\to 2$，再访问 $0\to 2$，此时 $0$ 的邻居 $2$ 已经访问过，但这并不能表示此时就找到了环。
 * 
 * 算法流程：
 * 
 * 1. 建图：把每个 $prerequisites[i]=[a,b]$ 看成一条有向边 $b\to a$，构建一个有向图 $g$。
 * 2. 创建长为 $numCourses$ 的颜色数组 $colors$，所有元素值初始化成 $0$。
 * 3. 遍历 $colors$，如果 $colors[i]=0$，则调用递归函数 $dfs(i)$。
 * 4. 执行 $dfs(x)$：
 *     1. 首先标记 $colors[x]=1$，表示节点 $x$ 正在访问中。
 *     2. 然后遍历 $x$ 的邻居 $y$。如果 $colors[y]=1$，则找到环，返回 $true$。如果 $colors[y]=0$（没有访问过）且 $dfs(y)$ 返回了 $true$，那么 $dfs(x)$ 也返回 $true$。
 *     3. 如果没有找到环，那么先标记 $colors[x]=2$，表示 $x$ 已经完全访问完毕，然后返回 $false$。
 * 5. 如果 $dfs(i)$ 返回 $true$，那么找到了环，返回 $false$。
 * 6. 如果遍历完所有节点也没有找到环，返回 $true$。
 */
public class Hot053_LC207_canFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] g = new ArrayList[numCourses];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] p : prerequisites) {
            g[p[1]].add(p[0]);
        }

        int[] colors = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (colors[i] == 0 && dfs(i, g, colors)) {
                return false; // 有环
            }
        }
        return true; // 没有环
    }

    // 返回 true 表示找到了环
    private boolean dfs(int x, List<Integer>[] g, int[] colors) {
        colors[x] = 1; // x 正在访问中
        for (int y : g[x]) {
            // 情况一：colors[y] == 1，表示发生循环依赖，找到了环
            // 情况二：colors[y] == 0，没有访问过 y，继续递归 y 获取信息
            // 情况三：colors[y] == 2，重复访问 y 只会重蹈覆辙，和之前一样无法找到环，跳过
            if (colors[y] == 1 || colors[y] == 0 && dfs(y, g, colors)) {
                return true; // 找到了环
            }
        }
        colors[x] = 2; // x 完全访问完毕，从 x 出发无法找到环
        return false; // 没有找到环
    }
}
