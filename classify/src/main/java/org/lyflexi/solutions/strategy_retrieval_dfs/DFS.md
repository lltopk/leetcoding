DFS深度搜索分为「自底向上归」和「自顶向下递」, 命名方式关注的是深度信息的获取, 但本质都是由浅入深递归

## 数学归纳法
使用DFS/递归来解决问题往往是存在相同子问题, 如何证明子问题能够推导出目标问题, 请运用初高中知识, 数学归纳法:

数学归纳法（Mathematical Induction, MI）是一种数学证明方法，通常被用于证明某个给定命题`P(n)`自然数范围内对任意`n`都成立，即无穷多个情况`P(1),P(2),P(3)……`都成立。

通常形式表述如下：设P(n)是一个冠以自然数n的命题。如果满足以下两个条件：
1. 归纳奠基: `P(1)`成立 
2. 归纳步骤: 对于任意`k≥1` ，假设`P(k)`成立，能够推导出`P(k+1)`也成立

那么对于所有自然数`n`, `P(n)`都成立。在递归代码具体的执行层面, **还需加上递归出口**, 以计算二叉树深度为例LC104. 二叉树的最大深度

一. 自底向上归
- 归纳奠基: 当前节点为`null`直接返回高度`0`, 注意`null`代表当前节点连叶子节点都不是
- 归纳步骤: 先递归调用左右子问题, 然后计算当前高度为`max(左子问题, 右子问题) + 1`

自底向上需要递到最底部, 然后才开始在归的返回途中计算答案. 因此递归程序自带栈隐式的记忆每个节点的信息,来模拟节点后进先出归，弹栈的效果, 因此空间复杂度是`O(N)`。


二. 自顶向下递
- 归纳奠基: 定义新的递归函数`dfs(...args, ret, state, ...layers)`, 并给新的递归函数传入初始值, `dfs(...args, ret, state, ...0)`
- 归纳步骤: 先取出高度进行计算`ret = Math.max(++depth, ret);`, 然后递归调用左右子问题
- 成员变量: 如果`ret`不是成员变量, 那么必须是引用类型才能传入递归函数`dfs(...args, ret, state, ...layers)`

自顶向下是在向下递的过程中完成计算的，归的时候只是简单的函数返回不参与计算，但栈仍然负责
1. 保存返回地址，递归完了需要返回到哪里
2. 保存变量信息，比如每层的`depth`是多少

无论是自底向上还是自顶向下哪种方式，最终都要层层返回到根节点的调用者手里，只是自顶向下的归的过程中没有参与计算而已。


## 二叉树DFS
带着问题去做下面的题目：
- 一般来说，DFS 的递归边界是空节点。在什么情况下，要额外把叶子节点作为递归边界？
- 在什么情况下，DFS 需要有返回值？什么情况下不需要有返回值？
- 在什么情况下，题目更适合用自顶向下的方法解决？什么情况下更适合用自底向上的方法解决？


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
3. 下一个子问题: 构造字符串`≥i+1`的部分, 尝试能否把`[i+1, n)`加入答案

回溯问题代码模板: 一般来说回溯问题的解法都是自顶向下`dfs(i) -> dfs(i+1)`直到`i == n`, 因此可以定义如下递归函数
```java
private void dfs(...args, ret, state, ...layers){
    if (layers) {
        ret.add(new ArrayList<>(state));
        return;
    }
    //模板一. 选与不选(输入视角)
    makeChoice(state, ...args[...layers]);
    dfs(...args, ret, state, ...layers + 1);
    undoChoice(state);
    
    //模板二. 枚举选哪个(答案视角)
    for(int i = 0/layer; i<...args.length; i++){
        makeChoice(state, ...args[...layers]);
        dfs(...args, ret, state, ...layers + 1);
        undoChoice(state);
    }
}
```
其中
- `...args`: 代表题目的输入参数
- `ret`: 代表答案, 是个引用类型
- `state`: 如当前路径`path`, 代表回溯对象, 当前路径`dfs`结束之后, 要先还原现场才能弹出到下一条`dfs`路径
- `...layers`: 代表层中的变量如`start/depth`, 层中变量基本类型即可, 各个层间独立不受影响, 在归的途中是被栈记忆过的

时间复杂度分析, 先不考虑剪枝优化, 由于回溯会穷举整棵二叉树/或者叶子节点共`2^n`, 同时要复制每个答案`path`需要`O(n)`, 因此总的时间复杂度为`O(n* 2^n);`

模板一. 选与不选(输入视角), 见LC78. 子集
```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(nums, ret, new ArrayList<>(), 0);
        return ret;
    }

    private void dfs(int[] nums, List<List<Integer>> ret, List<Integer> path, int i){
        if(i == nums.length){
            ret.add(new ArrayList<>(path));
            return;
        }

        //不选
        dfs(nums, ret, path, i+1);
        //选
        path.add(nums[i]);
        dfs(nums, ret, path, i+1);
        path.remove(path.size() - 1);
    }
}
```

模板二. 枚举选哪个(答案视角), 见LC17. 电话号码的字母组合
```java
class Solution {
    private String[] mapping = new String[]{"", "",  "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> ret  = new ArrayList<>();
        //digits的长度就是最终要选的次数
        char[] path = new char[digits.length()];
        //回溯出口就是i == digits.length();
        dfs(digits, ret, path, 0);
        return ret;
    }

    private void dfs(String digits, List<String> ret, char[] path, int i){
        if(i == digits.length()){
            ret.add(new String(path));
            return;
        }
        //当前层可以可以枚举选哪些
        for(char c: mapping[digits.charAt(i) - '0'].toCharArray()){
            path[i] = c;//由于这里是覆盖, 而不是添加, 所以下面不需要恢复现场
            dfs(digits, ret, path, i+1);
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

#### 全排列

- 求解不含重复数字的输入数组的所有 **不重复全排列**

```java
/* Backtracking algorithm: Permutation I */
void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
    // When the state length equals the number of elements, record the solution
    if (state.size() == choices.length) {
        res.add(new ArrayList<Integer>(state));
        return;
    }
    // Traverse all choices
    for (int i = 0; i < choices.length; i++) {
        int choice = choices[i];
        // Pruning: do not allow repeated selection of elements
        if (selected[i]) {
	        continue;
        }
        // Attempt: make a choice, update the state
        selected[i] = true;
        state.add(choice);
        // Proceed to the next round of selection
        backtrack(state, choices, selected, res);
        // Retract: undo the choice, restore to the previous state
        selected[i] = false;
        state.remove(state.size() - 1);
    }
}

/* Permutation I */
List<List<Integer>> permutationsI(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    backtrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
    return res;
}
```

#### 全排列Ⅱ

- 求解包含重复数字的输入数组的所有 **不重复全排列**

Although both `selected` and `duplicated` serve as pruning mechanisms, they target different issues:

* **Repeated-choice pruning** (via `selected`): There is a single `selected` array for the entire search, indicating which elements are already in the current state. This prevents the same element from appearing more than once in `state`.（纵向剪枝）
* **Equal-element pruning** (via `duplicated`): Each call to the `backtrack` function uses its own `duplicated` set, recording which elements have already been chosen in that specific iteration (`for` loop). This ensures that equal elements are selected only once per round of choices.（横向剪枝）

```java
/* Backtracking algorithm: Permutation II */
void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
    // When the state length equals the number of elements, record the solution
    if (state.size() == choices.length) {
        res.add(new ArrayList<Integer>(state));
        return;
    }
    // Traverse all choices，每次重建意味着递归的每一层duplicated变量是独立的。窥探整个递归树的全貌，同一层节点如果存在相同值，要提前剪枝
    Set<Integer> duplicated = new HashSet<Integer>();
    for (int i = 0; i < choices.length; i++) {
        int choice = choices[i];
        // Pruning: do not allow repeated selection of elements and do not allow repeated selection of equal elements
        if (selected[i] || duplicated.contains(choice)) {
	        continue;
        }
        // Attempt: make a choice, update the state
	// 由于递归回溯时会返回到上一级，而上一级别有自己的 duplicated 集合，因此不需要从duplicated集合中移除元素。对duplicated来说不用回溯retreat
        duplicated.add(choice); // Record selected element values
        selected[i] = true;
        state.add(choice);
        // Proceed to the next round of selection
        backtrack(state, choices, selected, res);
        // Retract: undo the choice, restore to the previous state
        selected[i] = false;
        state.remove(state.size() - 1);
    }
}

/* Permutation II */
List<List<Integer>> permutationsII(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    backtrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
    return res;
}
```

### 有重复元素的回溯
见LC子集 II ; 组合总和 II ;全排列 II


### 搜索类问题


## 网格图DFS
