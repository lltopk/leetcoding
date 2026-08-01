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