这里只是临时的算法调试仓库，主要是下文的经验总结：

> Reference and Recommend hello-algo By krahets：https://www.hello-algo.com/en
>
> GitHub：https://github.com/krahets/hello-algo

## VSCode Config

VSCode插件安装

- Language Server for Java by Apache NetBeans-netbeans
- leetcode-ccagml

VSCode调试Java：.vscode/launch.json

```json
{
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java+",
            "request": "launch",
            "name": "Launch Java App"
        }
    ]
}
```

leetcode账户：cyce-13

## 核心篇

### 分支判断

卫语句

* 优先考虑失败、异常、中断、退出等直接返回的情况。
* if条件尽可能变得简短，这样else也会更加清晰，除非你清楚所有的可能性

德摩根定律

* `if/while(b1||b2||b3) + if/while(!b1&&!b2&&!b3) == 1`
* `if/while(b1&&b2&&b3) + if/while(!b1||!b2||!b3) == 1`

### 递归问题 `Recursion`

- 属于“自上而下”的解决问题，由大到小拆解问题递归三要素，终止条件、递归调用（子问题）、返回结果（归）
- 当递归函数有返回值，子递归调用必须return，请注意这有别于终止条件的返回
- 当递归函数无返回值，收集结果（归）应当在递归参数当中体现
- 禁止状态变量的自增自减++--或者v=v+n或者v=v-n，即使v不是引用变量。因为会导致本来修改后的状态变量值按理是要传递给下一层递归的，结果在传递之前你把上一层（当前层）的状态变量也给修改了。正确的传参姿势是v+n/v-n直接塞给递归函数
- 禁止定义局部变量，由于反复递归导致局部变量被反复初始化，最终局部变量将毫无意义

### 回溯问题 `BackTracking`

In Example One, visiting each node starts a "trial". And passing a leaf node or the `return` statement to going back to the parent node suggests "retreat".

- 寻解trial，基于递归函数的共享状态变量 `state `，如当前路径  `path:List<TreeNode>`收集当前状态的答案。注意结果集 `res: List<List<TreeNode>````>` 并不属于trial
- 回溯retreat，伴随着向上归的动作中复原当前状态state，一般回溯retreat语句位于程序末尾
- 剪枝prune，通过return提前返回程序剪掉下面的枝叶，属于优化性能的手段。要注意剪枝语句return和trial语句的相对位置，但当trial语句在return语句之前，return之前必须再次retreat，因为提前trial了节点信息。当trial语句在return语句之后，那就没必要在return之前二次retreat
- 经典应用场景有二叉树dfs，全排列permutation，子集和subSetSum

### 链表迭代 `Listnode`

* 迭代 `iteration`属于“自下而上”的解决问题，由小到大解决问题
* 表现形式 `for/while`
* 单向链表 `ListNode`
* 双向链表
* 循环链表

### 二叉树 `TreeNode`

* 递归：深度优先dfs递归遍历
* 迭代：广度优先bfs层序遍历

### 二叉搜索树 `BST`

* 递归：中序遍历天然有序
* 迭代：二分查找
* 迭代：叶子插入（二分查找+叶子插入）
* 迭代+递归：按出度 `0|1|2`删除算法

### 字符串 `String`

1. 求字符 `charAt(index)`
2. 求数值 `charAt(index)-'0'`

### 双指针 `Point`

* `while|if|continue|break`
* 二分查找法
* 快慢指针法
* 滑动窗口法
* 跨数组多指针

### 哈希表 `Hashmap`

* 查找
* 缓存

### 栈 `Stack`

* `push/pop/peek`

### 队列 `Queue`

* `Queue`是顶级接口，实现类有 `LinkedList`，`PriorityQueue`
* `offer/poll`

### 动态规划 `Dynamic Programing`

* 推导转移方程并填充 `dp[]`数组

### 排序问题 `Sorting`

## 框架篇

### 二叉检索树

### 回溯框架

回溯框架0：

- state，是trial共享待回溯变量，如共享路径变量
- choices，是输入集合，如树节点集合集合
- res，答案收集器

```java
/* Backtracking algorithm framework */
void backtrack(State state, List<Choice> choices, List<State> res) {
    // Check if it's a solution
    if (isSolution(state)) {
        // Record the solution
        recordSolution(state, res);
        // Stop searching
        return;
    }
    // Iterate through all choices
    for (Choice choice : choices) {
        // Prune: check if the choice is valid
        if (!isValid(state, choice)) {
	    continue/break;
        }
        // Trial: make a choice, update the state
        makeChoice(state, choice);
        backtrack(state, choices, res);
        // Retreat: undo the choice, revert to the previous state
        undoChoice(state, choice);
    }
}
```

回溯框架1：全排列

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

回溯框架2：全排列Ⅱ

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

回溯框架3：子集和问题Ⅰ

- 给定一个正整数nums数组和一个目标正整数目标，找到所有可能的组合，使得组合中元素的总和等于目标。给定的数组没有重复的元素，每个元素可以多次选择。

To implement this pruning, given the input array [x1,x2,…,xn], the choice sequence in the search process should be [x(i1),x(i2),…,x(im)], which needs to satisfy i1≤i2≤⋯≤im.  **Any choice sequence that does not meet this condition will cause duplicates and should be pruned** .

So we initialize the variable `start`, which indicates the starting index point for traversal.  **After making the choice xi, set the next round to start from index  i** . This will ensure the choice sequence satisfies i1≤i2≤⋯≤im, thereby ensuring the uniqueness of the subsets.

* **Before starting the search, must sort the array `nums`.**
* In the traversal of all choices, **end the loop directly when the subset sum exceeds `target`** , as subsequent elements are larger and their subset sum will definitely exceed `target`.
* Eliminate the element sum variable `total`,  **by performing subtraction on `target` to count the element sum** . When `target` equals 0, record the solution.This way, one function parameter "total" can be omitted

```java
/* Backtracking algorithm: Subset Sum I */
void backtrack(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
    // When the subset sum equals target, record the solution
    if (target == 0) {
        res.add(new ArrayList<>(state));
        return;
    }
    // Traverse all choices
    // Pruning two: start traversing from start to avoid generating duplicate subsets
    for (int i = start; i < choices.length; i++) {
        // Pruning one: if the subset sum exceeds target, end the loop immediately
        // This is because the array is sorted, and later elements are larger, so the subset sum will definitely exceed target
        if (target - choices[i] < 0) {
            break;
        }
        // Attempt: make a choice, update target, start
        state.add(choices[i]);
        // Proceed to the next round of selection
        backtrack(state, target - choices[i], choices, i, res);//下次的start还可以是i，因为元素可以重复选择
        // Retract: undo the choice, restore to the previous state
        state.remove(state.size() - 1);
    }
}

/* Solve Subset Sum I */
List<List<Integer>> subsetSumI(int[] nums, int target) {
    List<Integer> state = new ArrayList<>(); // State (subset)
    Arrays.sort(nums); // Sort nums
    int start = 0; // Start point for traversal
    List<List<Integer>> res = new ArrayList<>(); // Result list (subset list)
    backtrack(state, target, nums, start, res);
    return res;
}
```

回溯框架4：子集和问题Ⅱ

- 给定一个正整数nums数组和一个目标正整数目标，找到所有可能的组合，使得组合中元素的总和等于目标。给定的数组可能包含重复的元素，每个元素只能选择一次。请将这些组合作为列表返回，该列表不应包含重复的组合。

Compared to the previous question,  **this question's input array may contain duplicate elements** , introducing new problems. For example, given the array [4,4^,5] and target element 8, the first round code's output results in [4,4],[4,4^], resulting in duplicate subsets , the second round code's output results in [4^,4],[4^,4^], also resulting in duplicate subsets. To solve this issue

- **Each array element can only be chosen once** . Fortunately, we can also use the variable `start` to meet this constraint: after making the choice xi, set the next round to start from index i+1 going forward.  [4,4],[4,4^],[4^,4],[4^,4^]->[4,4^],[4^,4]（纵向剪枝）
- **Another, equal elements could be chosen, but we need to limit equal elements to being chosen only once per round** . The implementation is quite clever: since the array is sorted, equal elements are adjacent. This means that in a certain round of choices, if the current element is equal to its left-hand element, it means it has already been chosen, so skip the current element directly.[4,4^],[4^,4]->[4,4^]（横向剪枝）

为什么 `i > start` 才开始判断第四次剪枝？为什么 `choices[i] == choices[i - 1]`能够判断同一层的重复项？

当你在某一层开始遍历（比如 `start = 0`），你可能会遇到多个连续相同的元素。假设这些元素是从索引 `start` 开始的：

* 对于第一个元素（`i == start`），你总是会尝试将其加入到当前状态中，因为它代表了该层（俯瞰整个树结构的某一层）首次遇到该值的机会。
* **只要是在递归调用之前的程序代码都属于当前层，递归调用代码所在行说明准备进入下一层，递归调用之后的程序代码是回溯说明要返回上一层**。所以在递归语句之前执行 `choices[i] == choices[i - 1]`，并且 `i > start`，这表明你已经在当前层尝试过这个值了。因此，为了避免产生重复组合，你应该跳过这次尝试。

```java
/* Backtracking algorithm: Subset Sum II */
void backtrack(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
    // When the subset sum equals target, record the solution
    if (target == 0) {
        res.add(new ArrayList<>(state));
        return;
    }
    // Traverse all choices
    // Pruning two: start traversing from start to avoid generating duplicate subsets
    for (int i = start; i < choices.length; i++) {
        // Pruning one: if the subset sum exceeds target, end the loop immediately
        // This is because the array is sorted, and later elements are larger, so the subset sum will definitely exceed target
        if (target - choices[i] < 0) {
            break;
        }
        // Pruning four: if the element equals the left element, it indicates that the search branch is repeated, skip it
	// 对于root.mid, 要求当root.mid.val==root.left.val的时候直接剪掉
	// 所以这个判断必须保留 i > start只剪同一层即不同分支的重复项（只要是在递归调用之前的程序代码都属于当前层），
	// 允许不同层选相同值（递归调用代码所在行说明准备进入下一层，前后两次递归说明是相邻层，相邻层允许选相同值）
        if (i > start && choices[i] == choices[i - 1]) {
            continue;
        }
        // Attempt: make a choice, update target, start
        state.add(choices[i]);
        // Proceed to the next round of selection
	// Pruning three: start traversing from start（i+1） to avoid repeatedly selecting the same element
        backtrack(state, target - choices[i], choices, i + 1, res);//下次的start不可以是i，因为元素不可以重复选择了
        // Retract: undo the choice, restore to the previous state
        state.remove(state.size() - 1);
    }
}

/* Solve Subset Sum II */
List<List<Integer>> subsetSumII(int[] nums, int target) {
    List<Integer> state = new ArrayList<>(); // State (subset)
    Arrays.sort(nums); // Sort nums
    int start = 0; // Start point for traversal
    List<List<Integer>> res = new ArrayList<>(); // Result list (subset list)
    backtrack(state, target, nums, start, res);
    return res;
}
```

### 手写堆

下面以大根堆为例手写堆的插入操作，取出操作，以及建堆操作。小根堆同理

自底向上的方式建堆(Floyd算法)效率要高于默认的自顶向下建堆，方式如下

- 从末尾节点的父节点 `parent(size() - 1)`开始逆序迭代节点，因为末尾节点的left和right必然大于size，无法下沉
- 迭代执行节点下沉shiftDown，直到索引为0的节点下沉完成

原因是自底向上建堆利用了完全二叉树的特性，大部分节点位于底层，下沉次数极小。数学证明自底向上建堆复杂度为线性On

```java
/* Constructor, build heap based on input list */
MaxHeap(List<Integer> nums) {
    // Add all list elements into the heap
    maxHeap = new ArrayList<>(nums);
    // Heapify all nodes except leaves
    for (int i = parent(size() - 1); i >= 0; i--) {
        siftDown(i);
    }
}
```
