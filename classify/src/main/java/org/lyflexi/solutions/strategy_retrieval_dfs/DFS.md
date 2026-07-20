DFS深度搜索分为「自底向上归」和「自顶向下递」, 命名方式关注的是深度信息的获取, 但本质都是由浅入深递归

## 数学归纳法
使用DFS/递归来解决问题往往是存在相同子问题, 如何证明子问题能够推导出目标问题, 请运用初高中知识, 数学归纳法:

数学归纳法（Mathematical Induction, MI）是一种数学证明方法，通常被用于证明某个给定命题`P(n)`自然数范围内对任意`n`都成立，即无穷多个情况`P(1),P(2),P(3)……`都成立。

通常形式表述如下：设P(n)是一个冠以自然数n的命题。如果满足以下两个条件：
1. 归纳奠基: `P(1)`成立 
2. 归纳步骤: 对于任意`k≥1` ，假设`P(k)`成立，能够推导出`P(k+1)`也成立

那么对于所有自然数`n`, `P(n)`都成立。在递归代码具体的执行层面, **还需加上递归出口**, 以计算二叉树深度为例LC104. 二叉树的最大深度

一. 自底向上归: 自底向上需要递到最底部, 然后才开始在归的返回途中计算答案. 因此递归程序自带栈隐式的记忆每个节点的信息,来模拟节点后进先出归，弹栈的效果, 因此空间复杂度是`O(N)`。
- 归纳奠基: 当前节点为`null`直接返回高度`0`, 注意`null`代表当前节点连叶子节点都不是
- 归纳步骤: 先递归调用左右子问题, 然后计算当前高度为`return max(左子问题, 右子问题) + 1`, 因此递归函数通常需要返回值

二. 自顶向下递: 自顶向下是在向下递的过程中完成计算的，归的时候只是简单的函数返回不参与计算，但栈仍然负责保存返回地址以及层中的变量信息等, 最终层层返回到根节点的调用者手里才算闭环
- 归纳奠基: 定义新的递归函数`void dfs(...args, ...state, ...layers)`, 并给新的递归函数传入初始值, `void dfs(...args, ...state, ...0)`
- 归纳步骤: 先取出高度进行计算`ret = Math.max(++depth, ret);`, 然后递归调用左右子问题, 因此递归函数通常无需返回值

**递归参数讨论:** 如果递归函数没有返回值即`void`, 你就需要自己定义成员变量`ret`(习惯于是类级别)来统计答案, 同时也要定义并区分函数参数
```java
RetType ret;
public retType main(...args){
    stateRef1 = new StateRef;
    stateRef2 = new StateRef;
    stateRef3 = new StateRef;
    dfs(...args, stateRef1, stateRef2, stateRef3, ...0);
    return ret;
}
private void dfs(...args, ...stateRef, ...layers){
    if(condition){
        ret += or ret.add
    }
    dfs(...args, ...stateRef, ...layers++ or ...layers--)
}
```
上述两类函数参数, 分别是基本参数引用参数`path` 和 基本参数`x`, 可以单独出现, 也可以同时出现
- 函数引用参数`...stateRef`: 如`path`, 用于统计递归状态, 这种常用于回溯问题, 需要记得还原现场
- 函数基本参数`...layers`: 由于基本类型不会对不同递归层级的状态造成影响, 对于每层递归是独立的参数, 所以可以表示二叉树高度, 或者表示递归层数


## 二叉树DFS
带着问题去做下面的题目：

问题一: 一般来说，DFS 的递归边界是空节点。在什么情况下，要额外把叶子节点作为递归边界？

问题二: 在什么情况下，DFS 需要有返回值？什么情况下不需要有返回值？:
- 通常来说自底向上的递归函数要带有返回值, 因为是在归中计算
- 自顶向下的递归函数的返回值通常是`void`, 因为通常在递的过程中已经计算了答案. 但有的时候自顶向下也可以利用自底向上的整体思维(要有返回值给到父节点), 真正决定是否需要返回值的是父节点是否需要利用子节点计算出来的结果



### 自顶向下DFS
二叉树泛前序遍历

### 自底向上DFS
二叉树泛后序遍历

## 二叉树应用
本质依然是基于自顶向下DFS / 自底向DFS 的应用

### 二叉树高度
### 相同/对称/翻转/平衡
### 二叉树直径
### 最近公共祖先
### 二叉搜索树BST
二叉树中序遍历, Binary Search Tree的特点是中序遍历有序
### 创建二叉树
## 最优性剪枝
DFS过程中, 如果在已知条件下可以不必多余深入递归, 则提前返回, 能够大幅降低时间复杂度, 见LC111. 二叉树的最小深度
```java
/**
 * 自顶向下
 */
public class LC111_minDepth2 {
    int ret = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root, 0);
        return ret;
    }
    private void dfs(TreeNode root, int depth){
        //其中|| ++depth > ret 意思是最优性剪枝, 继续向下递归也不会让 ret 变小，直接返回。
        if(root == null || ++depth > ret){
            return;
        }
        if(root.left == null && root.right == null){
            ret = Math.min(ret, depth);
            return;
        }
        dfs(root.left, depth);
        dfs(root.right, depth);
    }
}
```

## 一般树(N叉树)




## 通用回溯类问题
回溯问题思考过程, 回溯三问: 
1. 当前操作: 枚举`path[i]`要填入的字母
2. 子问题: 构造字符串`≥i`的部分, 尝试能否把`[i, n)`加入答案
3. 下一个子问题: 构造字符串`≥i+1`的部分, 尝试能否把`[i+1, n)`加入答案, 其中`≥i+1`的部分就对应于二叉树的两个分支有两个路线， 或者对应于`N`叉树的`N`个分支有`N`个路线

回溯问题代码模板: 一般来说回溯问题的解法都是自顶向下`dfs(i) -> dfs(i+1)`直到`i == n`, 因此可以定义如下递归函数
```java
RetType ret;
public retType main(...args){
    stateRef1 = new StateRef;
    stateRef2 = new StateRef;
    stateRef3 = new StateRef;
    dfs(...args, stateRef1, stateRef2, stateRef3, ...0);
    return ret;
}
private void dfs(...args, ...stateRef, ...layers){
    if (layers.match(condition)) {
        ret.add(new ArrayList<>(state));
        return;
    }
    
    //模板一. 选与不选(输入视角)
    dfs(...args, ...stateRef, ...layers + 1 or -1);//不选
    makeChoice(...stateRef, ...args[...layers]);//选
    dfs(...args, ...stateRef, ...layers + 1 or -1);
    undoChoice(...stateRef);

    //模板二. 枚举选哪个(答案视角)
    for(int i = 0/layer; i<...args.length; i++){
        makeChoice(...stateRef, ...args[...layers]);
        dfs(...args, ...stateRef, ...layers + 1) or -1;
        undoChoice(...stateRef);
    }
}
```
定义类成员变量`ret`接收答案, 然后澄清下`dfs(...args, ...stateRef, ...layers)`中的各个参数
- `...args`: 代表题目的输入参数
- `...stateRef`: 如当前路径`path`, 代表回溯对象, 当前路径`dfs`结束之后, 要先还原现场才能弹出到下一条`dfs`路径
- `...layers`: 代表层中的变量如`start/depth`, 层中变量基本类型即可, 各个层间独立不受影响, 在归的途中是被栈记忆过的

时间复杂度分析, 先不考虑剪枝优化, 由于回溯会在二叉树/N叉树上穷举所有可能性, 这些可能性相当于答案的个数, 同时每个答案`path`都要复制一份作为结果这需要`O(n)`, 因此总的时间复杂度为`O(n* 可能性);`
- 子集问题：`O(n/2 ·2^n)`, 因为子集不要求元素数为`n`, 因此平均复制时间是`O(n/2)`, 其中可能性是二叉树所有节点个数, 即等比数列求和相当于`O(2^n)`
- 组合问题：`O(k·C(n,k))`, 组合的复制时间是`O(k)`表示取`k`个, 其中可能性是`C(n,k)`
- 排列问题：`O(n⋅n!)` 其中可能性是`O(n⋅n!)`, 其中`O(!n)`是要远远大于子集型`O(2^n)`的, 这有点违背直觉， 毕竟排列型的叶子节点往往都是单节点。可以从数学证明来纠正你的直觉, 其实画图可以看出当`n`很大的时候阶乘在前几层中已经爆炸了

模板一. 选与不选(输入视角), 见LC78. 子集
```java
class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, new ArrayList<>(), 0);
        return ret;
    }

    private void dfs(int[] nums, List<Integer> path, int i){
        if(i == nums.length){
            ret.add(new ArrayList<>(path));
            return;
        }

        //不选
        dfs(nums, path, i+1);
        //选
        path.add(nums[i]);
        dfs(nums, path, i+1);
        path.remove(path.size() - 1);
    }
}
```

模板二. 枚举选哪个(答案视角), 见LC17. 电话号码的字母组合
```java
class Solution {
    private String[] mapping = new String[]{"", "",  "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> ret  = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        //digits的长度就是最终要选的次数
        char[] path = new char[digits.length()];
        //回溯出口就是i == digits.length();
        dfs(digits, path, 0);
        return ret;
    }

    private void dfs(String digits, char[] path, int i){
        if(i == digits.length()){
            ret.add(new String(path));
            return;
        }
        //当前层可以可以枚举选哪些
        for(char c: mapping[digits.charAt(i) - '0'].toCharArray()){
            path[i] = c;//由于这里是覆盖, 而不是添加, 所以下面不需要恢复现场
            dfs(digits, path, i+1);
        }
    }
}
```


### 二叉树回溯
回溯`BackTracking`本质是搜索树上的DFS， 先理解二叉树上的回溯, 再来学习一般情况下的回溯。

见LC437. 路径总和 III
```java
public class LC437_pathSum {
    int ret = 0;
    /**
     枚举右维护左
     前缀和
     dfs
     回溯
     */
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0l, 1);//单独初始化前缀和[0, )本身就是解的情况
        dfs(root, targetSum, cnt, 0l);
        return ret;
    }
    private void dfs(TreeNode root, int targetSum, Map<Long, Integer> cnt, long preS){
        if(root == null){
            return;
        }
        preS += root.val;
        //s2 - s1 = k
        ret += cnt.getOrDefault(preS - targetSum, 0);
        cnt.merge(preS, 1, Integer::sum);
        dfs(root.left, targetSum, cnt, preS);
        dfs(root.right, targetSum, cnt, preS);
        //恢复现场
        cnt.merge(preS, -1, Integer::sum);
    }
}
```


### 子集型回溯

### 划分型回溯
形如字符串`"abc"`, 可以把分割线（逗号）看成是可以「选或不选」的东西，本质是子集型回溯。

- 见LC131. 分割回文串
- 见LC93. 复原 IP 地址

### 组合型回溯 理解剪枝
见LC77. 组合, 当剩余可选数不足剩余的`k`个的时候， 可以剪枝提前返回来优化递归函数
- 可以正向选与不选回溯
- 可以逆向选与不选回溯（递归函数可以少传1个参数）
- 可以正向枚举回溯
- 可以逆向枚举回溯（递归函数可以少传1个参数）

更多组合题单： 见LC39. 组合总和; LC40. 组合总和 II; 216. 组合总和 III; 22. 括号生成

### 排列型回溯
见LC46. 全排列, 与其他回溯类问题不同的是, 全排列的定义就是每层必须要选, 所以不能用选与不选（输入视角）, 只能使用枚举思路（答案视角）

```java
/**
 * 全排列的定义就是每层必须要选， 所以不能用选与不选（输入视角）
 * 
 * 只能使用枚举思路（答案视角）
 */
public class LC46_permute {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int[] visit = new int[nums.length];
        dfs(nums, new ArrayList<>(), visit, 0);
        return ret;
    }


    private void dfs(int[] nums, List<Integer> path, int[] visit, int i){
        if (i == nums.length){
            ret.add(new ArrayList<>(path));
            return;
        }
        //在下面的循环中
        //i代表层数：当前递归层， 直到层深度等于nums.length
        //j代表当前层中剩余可选的数字范围：假设在i层三种可能性分别是(1,2,3)，那么i+1层的可能性是(2,3) (1,3) (1,2), 那么i+2层的可能性是(3) (2) (3) (1) (2) (1) 若n=3的全排列就到底了
        //根据上述例子， 因此虽然j可以从头开始重新选， 但都需要跳过上一层的自己
        for(int j = 0; j<nums.length; j++){
            if(visit[j] == 0){
                path.add(nums[j]);
                visit[j] = 1;//布尔数组标记， 保证下面层数从头再选一轮的时候跳过上一轮的自己
                dfs(nums, path, visit, i + 1);
                path.remove(path.size() - 1);
                visit[j] = 0;
            }
        }
    }
}
```
更多排列回溯问题见LC51. N 皇后; 52. N 皇后 II

### 有重复元素的回溯
见LC子集 II ; 组合总和 II ;全排列 II


### 搜索类问题


## 网格图DFS
