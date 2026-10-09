package org.lyflexi;

import java.util.*;

/**
 * 138. 随机链表的复制
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * 
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 
 * 返回复制链表的头节点。
 * 
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * 
 * - val：一个表示 Node.val 的整数。
 * 
 * - random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * 
 * 示例 1：
 * 
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 
 * 示例 2：
 * 
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 
 * 示例 3：
 * 
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 
 * 提示：
 * 
 * - 0 <= n <= 1000
 * 
 * - -10^4 <= Node.val <= 10^4
 * 
 * - Node.random 为 null 或指向链表中的节点。
 */

/**
 * 思路
 * 
 * 如果没有 $random$ 指针，只需在遍历链表的同时，依次复制每个节点（创建新节点并复制 $val$），添加在新链表的末尾。
 * 
 * 有 $random$ 指针，问题就变得复杂了，我们需要知道 $random$ 指向的那个节点，在新链表中是哪个节点。
 * 
 * 所以必须记录原链表节点到新链表节点的映射（map）。这样可以通过原链表 $random$ 指向的节点，知道新链表的 $random$ 应该指向哪个节点。
 * 
 * 难道要用哈希表吗？不需要，我们可以把新链表和旧链表「混在一起」。
 * 
 * 例如链表 $1\to 2\to 3$，依次复制每个节点（创建新节点并复制 $val$ 和 $next$），把新节点直接插到原节点的后面，形成一个交错链表：
 * 
 * $$
 * 1\to1'\to 2\to 2'\to 3\to 3'
 * $$
 * 
 * 如此一来，原链表节点的下一个节点，就是其对应的新链表节点了！
 * 
 * 然后遍历这个交错链表，假如节点 $1$ 的 $random$ 指向节点 $3$，那么就把新节点 $1'$ 的 $random$ 指向节点 $3$ 的下一个节点 $3'$，这样就完成了对 $random$ 指针的复制。
 * 
 * 最后，从交错链表中分离出 $1'\to 2'\to 3'$，即为深拷贝后的链表。做法类似 328. 奇偶链表。
 * 
 * ⚠注意：不能只删除节点 $1,2,3$，因为题目要求原链表的 $next$ 不能修改。（用 Python 的同学可以先看第一份代码，再看第二份代码）
 */
public class Hot032_LC138_copyRandomList {
    public Node copyRandomList(Node head) {
        // 复制每个节点，把新节点直接插到原节点的后面
        for (Node cur = head; cur != null; cur = cur.next.next) {
            cur.next = new Node(cur.val, cur.next);
        }

        // 遍历交错链表中的原链表节点
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                // 要复制的 random 是 cur.random 的下一个节点
                cur.next.random = cur.random.next;
            }
        }

        // 把交错链表分离成两个链表
        Node dummy = new Node(0);
        Node tail = dummy;
        for (Node cur = head; cur != null; cur = cur.next, tail = tail.next) {
            Node copy = cur.next; // 新节点
            tail.next = copy; // 把新节点插在 tail 的后面，构建新的链表
            cur.next = copy.next; // 恢复原节点的 next
        }

        return dummy.next;
    }
}
