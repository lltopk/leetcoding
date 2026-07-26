广度优先遍历

## 二叉树BFS
见LC102. 二叉树的层序遍历

层序遍历可以有两种数据结构来实现, 两个数组 或者 1个队列, 但方式都是相同的
- 第一个循环中: 初始化当前层变量/集合
- 第二个循环中: 计算当前层
- 第二个循环中: 计算下一层

方法一: 两个数组, cur nxt
```java
/**
 * 两个数组
 */
public class LC102_levelOrder2 {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<TreeNode> cur = List.of(root);
        while(! cur.isEmpty()){
            //0. 初始化当前层变量/集合
            List<Integer> layer = new ArrayList<>(cur.size());
            List<TreeNode> nxt = new ArrayList<>();
            for(TreeNode node: cur){
                //1. 计算当前层
                layer.add(node.val);
                //2. 计算下一层, 如果是逆序层序遍历, 则下面两个if交换顺序即可
                if(node.left != null){
                    nxt.add(node.left);
                }
                if(node.right !=null){
                    nxt.add(node.right);
                }
            }
            cur = nxt;
            ret.add(layer);
        }
        return ret;
    }
}
```

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
            //0. 初始化当前层变量/集合
            List<Integer> layer = new ArrayList<>();
            int n = deque.size();
            while(n-- > 0){
                //1. 计算当前层
                TreeNode node = deque.poll();
                layer.add(node.val);
                //2. 计算下一层, 如果是逆序层序遍历, 则下面两个if交换顺序即可
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            ret.add(layer);
        }
        return ret;
    }
}
```

## 网格图BFS