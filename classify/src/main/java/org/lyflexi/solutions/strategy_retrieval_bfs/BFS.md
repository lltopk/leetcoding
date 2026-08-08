广度优先遍历

## 二叉树BFS
见LC102. 二叉树的层序遍历

层序遍历可以有两种数据结构来实现, 两个数组 或者 1个队列, 但方式都是相同的
- 初始化当前数据结构： `curList` 或 `dqeue`
- 第一个循环中: 初始化业务属性对象`bizList` 以及 当前层下要计算的下层变量`nxtList`
- 第二个循环中: 同时计算当前层和下一层, `opr(bizList) + opr(nxtList)` 或 `opr(bizList) + opr(deque)`

### 两个数组
方法一: 两个数组, curList nxtList, 类似于链表当循环结束后, 前进`curList` 到 `nxtList`
```java
/**
 * 两个数组
 */
public class LC102_levelOrder2 {
    List<List<Integer>> ret = new ArrayList<>();
    List<TreeNode> curList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        curList.add(root);
        while(! curList.isEmpty()){
            //初始化业务属性对象`bizList` 以及 当前层下要计算的下层变量`nxtList`
            List<Integer> bizList = new ArrayList<>(curList.size());
            List<TreeNode> nxtList = new ArrayList<>();
            for(TreeNode node: curList){
                bizList.add(node.val);
                //如果是逆序层序遍历, 则下面两个if交换顺序即可
                if(node.left != null){
                    nxtList.add(node.left);
                }
                if(node.right !=null){
                    nxtList.add(node.right);
                }
            }
            curList = nxtList;
            ret.add(bizList);
        }
        return ret;
    }
}
```

依然是两个数组, 但变成了副本`bak` 和 `curList`, 有了副本`bak`就有资格在循环前重置引用`curList = new ArrayList();`, 这样可以省去循环最后的引用更新操作`curList = nxtList`
```java
public class LC102_levelOrder3 {
    List<List<Integer>> ret = new ArrayList<>();
    List<TreeNode> curList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        curList.add(root);
        while(! curList.isEmpty()){
            //初始化业务属性对象`bizList`
            List<Integer> bizList = new ArrayList<>(curList.size());
            //引用bak指向curList的堆地址
            List<TreeNode> bak = curList;
            //引用curList指向新的空白堆地址
            curList = new ArrayList();
            for(TreeNode node: bak){
                //同时计算当前层和下一层
                bizList.add(node.val);
                //如果是逆序层序遍历, 则下面两个if交换顺序即可
                if(node.left != null){
                    curList.add(node.left);
                }
                if(node.right !=null){
                    curList.add(node.right);
                }
            }
            ret.add(bizList);
        }
        return ret;
    }
}
```

### 一个队列

方法二: 一个队列 deque
```java
/**
 * 队列
 */
public class LC102_levelOrder {
    List<List<Integer>> ret = new ArrayList<>();
    Deque<TreeNode> deque = new ArrayDeque<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        deque.offer(root);
        while(! deque.isEmpty()){
            //初始化业务属性对象`bizList`
            List<Integer> bizList = new ArrayList<>();
            int n = deque.size();
            while(n-- > 0){
                //同时计算当前层和下一层
                TreeNode node = deque.poll();
                bizList.add(node.val);
                //如果是逆序层序遍历, 则下面两个if交换顺序即可
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            ret.add(bizList);
        }
        return ret;
    }
}
```

## 网格图BFS
网格图 BFS 与二叉树 BFS 的区别是，二叉树只会往下访问左右儿子，不会往上访问父节点；而网格图存在左右横跳的可能，因此也要创建`boolean[][] vis` 或是 原地修改 `int[][]grid`作为标记

网格图 BFS 适用于需要计算最短距离（最短路）的题目。 DFS 是不撞南墙不回头；**BFS网格图 是往水塘中扔石头（起点），荡起一圈圈涟漪（先访问近的，再访问远的）。**

网格图 BFS 的核心不是保证访问顺序是FIFO, 也就是一层一层扩散。因此队列的作用的分层扩散, 则第一次到达即最优

### 单源最短路 求全部
单源(输入)到其余点最短路, 定义距离矩阵`int[][] dis = new int[m][n];`存储起始点位 到 其余所有点位的最短距离, 

因为已经存储了起始点位 到 其余所有点位的单源最短路`int[][] dis`, 因此下次遍历方向的时候直接对上个距离`+1`即可: `dis[x][y] = dis[p[0]][p[1]] + 1;`, 

此时主体由单源(输入)起点变成了各个终点, 因此需要拿出所有的队列节点进行计算, 各个节点均参与贡献 `+ 1`
```java
while (!q.isEmpty()) {
    int[] p = q.poll();
    for (int[] dir : DIRS){
        
    }
}
```
通过存储距离矩阵`int[][] dis = new int[m][n];`, 有了对各个终点的记忆化之后, 就无需分层计算了
```java
class Solution {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下

    // 返回从 (startX, startY) 出发，到其余格子的最短距离
    // 时间复杂度 O(mn)
    public int[][] bfsGrid(char[][] grid, int startX, int startY) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int[] row : dis) {
            Arrays.fill(row, -1);
        }
        
        //队列存初始坐标
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startX, startY});
        
        dis[startX][startY] = 0;//重复性访问标记
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int[] dir : DIRS) {
                //下一个方向的坐标(x, y)
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                // 这里 grid[x][y] == '.' 表示有效位置
                // 这里 dis[x][y] < 0 表示未重复访问
                if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] == '.' && dis[x][y] < 0) {
                    dis[x][y] = dis[p[0]][p[1]] + 1;//泛起涟漪, 同时相当于重复性访问标记(先到的一定是最短的)
                    q.offer(new int[]{x, y});
                }
            }
        }

        return dis;
    }
}
```

### 单源最短路 求最短
如果不需要求单源(输入)到其余所有点位的最短距离, 比如只要求到边界的最短距离, 此时我们就无需定义距离矩阵`int[][] dis = new int[m][n];`

用一个变量如`dis`记录距离即可, 但要注意每层只贡献距离`1`, 而不是每个元素贡献距离`1`, 因此需要对队列分层计算. 否则就变成了求涟漪经过的所有点的个数了 不可不可... 
```txt
//每层对于dis只贡献1次
for(int dis = 0; ! deque.isEmpty(); dis++){
    int n = deque.size();
    while(n-- > 0){
        int[] pop = deque.pop();
        for(int[] dir: DIRS){
            
        }
    }
}
```
见LC1926. 迷宫中离入口最近的出口
```java
public class LC1926_nearestExit2 {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下
    public int nearestExit(char[][] maze, int[] entrance) {

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(entrance);
        //访问标记
        maze[entrance[0]][entrance[1]] = '+';

        //for循环条件是! deque.isEmpty(), 并且直接迭代计算dis++
        // 这样做的好处是突出dis++就是在当前层计算1次, 而不是对层中计算n次
        for(int dis = 0; ! deque.isEmpty(); dis++){
            int n = deque.size();
            while(n-- > 0){
                int[] pop = deque.pop();
                for(int[] dir: DIRS){
                    int i0 = pop[0] + dir[0];
                    int j0 = pop[1] + dir[1];
                    //有效条件
                    if(i0 >= 0 && i0 < maze.length && j0 >= 0 && j0 < maze[0].length && maze[i0][j0] == '.'){
                        //第一次恰好到边界, 即为最近距离
                        if(i0 == 0 || i0 == maze.length - 1 || j0 == 0 || j0 == maze[0].length - 1){
                            return dis + 1;
                        }
                        maze[i0][j0] = '+';//访问标记
                        deque.offer(new int[]{i0, j0});
                    }
                }
            }
        }
        return -1;
    }
}
```

如果节点本身携带距离`deque.offer(new int[]{entrance[0], entrance[1], 0});`, 那么也可以达到分层计算的效果, 并且可以省去`dis`变量
```java
public class LC1926_nearestExit3 {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下

    public int nearestExit(char[][] maze, int[] entrance) {

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{entrance[0], entrance[1], 0});
        //访问标记
        maze[entrance[0]][entrance[1]] = '+';
        while (!deque.isEmpty()) {
            int n = deque.size();
            while (n-- > 0) {
                int[] pop = deque.pop();
                for (int[] dir : DIRS) {
                    int i0 = pop[0] + dir[0];
                    int j0 = pop[1] + dir[1];
                    //有效条件
                    if (i0 >= 0 && i0 < maze.length && j0 >= 0 && j0 < maze[0].length && maze[i0][j0] == '.') {
                        //第一次恰好到边界, 即为最近距离
                        if (i0 == 0 || i0 == maze.length - 1 || j0 == 0 || j0 == maze[0].length - 1) {
                            return pop[2] + 1;
                        }
                        maze[i0][j0] = '+';//访问标记
                        deque.offer(new int[]{i0, j0, pop[2] + 1});
                    }
                }
            }
        }
        return -1;
    }
}
```
### 多源最短路 求全部
见LC542. 01 矩阵, 求每个元素到最近的0的距离, 可以看到附近的0不止一个, 因此属于多源

这种题目我们往往会将所有的源(这里是0值坐标)入队列, 然后继续套模板
```java
public class LC542_updateMatrix {
    int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];

        Queue<int[]> q = new ArrayDeque<>();
        // 把所有的 0 加入队列
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] pos = q.poll();
                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] == 1) {
                        mat[x][y] = 0; // 标记为已访问
                        ans[x][y] = ans[pos[0]][pos[1]] + 1;
                        q.add(new int[]{x, y});
                    }
                }
            }
        }
        return ans;
    }
}
```

### 多源最短路 求最短
见LC994. 腐烂的橘子
```java
public class LC994_orangesRotting {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 四方向
    /**
     孤岛橘子永远不会腐烂

     2 1 1
     0 1 1
     1 0 1
     为了判断是否有永远不会腐烂的橘子，我们可以统计初始新鲜橘子的个数 fresh。
     在 BFS 中，每有一个新鲜橘子被腐烂，就把 fresh 减一，这样最后如果发现 fresh>0，就意味着有橘子永远不会腐烂，返回 −1。
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++; // 统计新鲜橘子个数
                } else if (grid[i][j] == 2) {
                    q.add(new int[]{i, j}); // 将一开始就腐烂的橘子, 作为多源点
                }
            }
        }
        
        //下面BFS模拟腐烂过程
        int ans = 0;
        for (; fresh > 0 && !q.isEmpty(); ans++) {
            int size = q.size();
            while (size-- > 0) {
                int[] pos = q.pop();
                for (int[] d : DIRECTIONS) { // 四方向
                    int i = pos[0] + d[0];
                    int j = pos[1] + d[1];
                    if (0 <= i && i < m && 0 <= j && j < n && grid[i][j] == 1) { // 新鲜橘子
                        fresh--;
                        grid[i][j] = 2; // 变成腐烂橘子
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        return fresh > 0 ? -1 : ans;
    }
}
```
