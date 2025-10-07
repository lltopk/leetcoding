## 算法框架
已涵盖的算法框架如下:
- 回溯框架
- 手写堆结构
- 机灵的二分查找

### 回溯框架

标准的回溯框架如下:
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

#### 子集和问题Ⅰ

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

#### 子集和问题Ⅱ

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

### 手写堆结构
给定一个数组Heap<Integer>默认平铺开作为初始二叉树,  数组索引为i, 基本算数api如下
```java
    /* 获取左子节点的索引 */
    int left(int i) {
        return 2 * i + 1;
    }
    /* 获取右子节点的索引 */
    int right(int i) {
        return 2 * i + 2;
    }
    /* 获取父节点的索引 */
    int parent(int i) {
        return (i - 1) / 2; // 向下整除
    }
    /* 访问堆顶元素 */
    int peek() {
        return maxHeap.get(0);
    }
```
#### 建堆
通过调整给定的初始二叉树, 建堆方式如下, 大根堆/小根堆没有本质差别: 
- 从最后一个非叶子节点`parent(size() - 1)`开始逆序迭代节点，原因是由于叶节点没有子节点，因此它们天然就是合法的子堆，无须堆化, 无须下沉
- 迭代执行节点下沉shift down，直到索引为0的节点下沉完成

上述过程是自底向上的方式建堆(Floyd算法, 逆序shift down), 当高层节点调整时，其子树已经是堆了，效率高, 因此高层节点下沉次数极小。

自底向上建堆效率要高于传统的自顶向下建堆(借助入堆操作shift up, 默认插入n次, nlogn)

数学证明自底向上建堆复杂度为线性On
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
其中siftDown(i)的具体逻辑见下文的出堆操作
#### 入堆
堆建好之后, 如何入堆? 首先给数组add元素, 然后通过shiftup维护堆结构, logn
```java
/* 元素入堆 */
void push(int val) {
    // 添加节点
    maxHeap.add(val);
    // 从底至顶堆化
    siftUp(size() - 1);
}

/* 从节点 i 开始，从底至顶堆化 */
void siftUp(int i) {
    while (true) {
        // 获取节点 i 的父节点
        int p = parent(i);
        // 当“越过根节点”或“节点无须修复”时，结束堆化
        if (p < 0 || maxHeap.get(i) <= maxHeap.get(p))
            break;
        // 交换两节点
        swap(i, p);
        // 循环向上堆化
        i = p;
    }
}
```
#### 出堆
堆建好之后, 如何出堆? 为了尽量减少元素索引的变动, 先交换堆顶元素与末元素。记录好堆顶元素，然后将末元素从列表中删除 ,最后通过shiftdown维护堆结构, logn, 最后返回记录的堆顶元素
```java
/* 元素出堆 */
int pop() {
    // 判空处理
    if (isEmpty())
        throw new IndexOutOfBoundsException();
    // 交换根节点与最右叶节点（交换首元素与尾元素）
    swap(0, size() - 1);
    // 删除节点
    int val = maxHeap.remove(size() - 1);
    // 从顶至底堆化
    siftDown(0);
    // 返回堆顶元素
    return val;
}

/* 从节点 i 开始，从顶至底堆化 */
void siftDown(int i) {
    while (true) {
        // 判断节点 i, l, r 中值最大的节点，记为 ma
        int l = left(i), r = right(i), ma = i;
        if (l < size() && maxHeap.get(l) > maxHeap.get(ma))
            ma = l;
        if (r < size() && maxHeap.get(r) > maxHeap.get(ma))
            ma = r;
        // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出
        if (ma == i)
            break;
        // 交换两节点
        swap(i, ma);
        // 循环向下堆化
        i = ma;
    }
}
```

### 机灵的二分查找
大K问题会导致堆排序klogn超时, 就要用到二分查找来优化了 

区间不变量原则[left.right), 此时
- right的初始值是n, 不是n-1
- while的条件是left<right, 不是left<=right
- if(nums[mindex]>target)的处理是right = mindex, 不是right = mindex-1

```java
    public int search(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        //区间不变量[l,r)
        int right = n;//①
        while(left<right){//②
            int mindex = (left+right) >>1;
            if(nums[mindex]>target){
                right = mindex;//③
            }else if(nums[mindex]<target){
                left = mindex+1;
            }else {
                left = right = mindex;
                return left;
            }
        }
        return -1;
    }
}
```
约定`[left,right)`而不是`[left,right]`的潜在好处是, 循环退出时，唯一可能的情况就是 left == right。 原因如下
- 每次循环:left 只会增加 (left = mid + 1)
- 每次循环:right 只会减少或保持 (right = mid)
所以每次循环 left 只能移动到不超过 right。


#### 重复元素求左界
```java
   private int getLBorder(int[] nums,int target){
        int n = nums.length;
        int left = 0;
        int lBorder = -1;
        //区间不变量
        int right = n;
        while(left<right){
            int mIndex = (left+right)>>1;
            //重复元素求左边界
            if(nums[mIndex]>=target){
                right = mIndex;
                lBorder = mIndex;
            }else{
                left = mIndex+1;
            }
        }

        return lBorder;
    }
```
#### 重复元素求右界
```java
    private int getRBorder(int[] nums,int target){
        int n = nums.length;
        int left = 0;
        //区间不变量
        int right = n;
        int rBoard = -1;
        while(left<right){
            int mIndex = (left+right)>>1;
            //重复元素求右边界
            if(nums[mIndex]<=target){
                left = mIndex+1;
                rBoard = mIndex;
            }else {
                right = mIndex;
            }
        }
        return rBoard;
    }
```

#### 特殊情况目标值不固定
参见LC153,LC154, LC162, 目标值一直在变化, 这种题目必须初始化right为n-1, 这是有别于标准二分模板[l,r)的地方

### TODO 二叉检索树