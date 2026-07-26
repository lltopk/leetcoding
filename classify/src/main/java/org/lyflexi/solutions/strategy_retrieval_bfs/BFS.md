广度优先遍历

## 二叉树BFS
见LC102. 二叉树的层序遍历

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
            List<Integer> layer = new ArrayList<>(cur.size());
            List<TreeNode> nxt = new ArrayList<>();
            for(TreeNode node: cur){
                layer.add(node.val);
                //如果是逆序层序遍历, 则下面两个if交换顺序即可
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
            List<Integer> layer = new ArrayList<>();
            int n = deque.size();
            while(n-- > 0){
                TreeNode node = deque.poll();
                layer.add(node.val);
                //如果是逆序层序遍历, 则下面两个if交换顺序即可
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