package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.handson;

/**
 * 1046. 最后一块石头的重量
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 *
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 129,182/196.6K
 * 通过率
 * 65.7%
 */

/**
 * 手写堆结构
 */
public class LC1046_lastStoneWeight {

    private int size;//用变量记录长度, 避免循环数组计算长度

    public int lastStoneWeight(int[] stones) {
        size = stones.length;

        heapify(stones);

        while (size > 1) {
            int v1 = pop(stones);
            int v2 = pop(stones);

            if (v1 != v2) {
                offer(stones, v1 - v2);
            }
        }

        return size == 0 ? 0 : stones[0];
    }

    // 原地堆化（最大堆）
    // 堆化可以保证 h[0] 是堆顶元素，且 h[i] >= max(h[2*i+1], h[2*i+2])
    private void heapify(int[] h) {
        // 倒着遍历，从而保证 i 的左右子树一定是堆，那么 sink(h, i) 就可以把左右子树合并成一个堆
        // 下标 >= h.length / 2 的元素是二叉树的叶子，无需下沉
        for (int i = size / 2 - 1; i >= 0; i--) {
            sink(h, i);
        }
    }

    // 把 h[i] 不断下沉，每次找左右儿子中最大的交换，直到 i 的左右儿子都 <= h[i] 时停止
    private void sink(int[] h, int i) {
        while (true) {
            int j = 2 * i + 1;

            if (j >= size) {
                break;
            }
            // i 的右儿子比 i 的左儿子大
            if (j + 1 < size && h[j + 1] > h[j]) {
                j++;
            }
            // 说明 i 的左右儿子都 <= h[i]，停止下沉
            if (h[i] >= h[j]) {
                break;
            }

            swap(h, i, j);
            i = j;
        }
    }

    // 上浮, 新的元素从队尾入堆, 需要上浮调整堆结构
    private void swim(int[] h, int i) {
        while (i > 0) {
            int p = (i - 1) / 2;

            if (h[p] >= h[i]) {
                break;
            }

            swap(h, p, i);
            i = p;
        }
    }

    // 出堆（删除最大值）
    private int pop(int[] h) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }

        int ans = h[0];

        swap(h, 0, size - 1);

        size--;

        if (size > 0) {
            sink(h, 0);
        }

        return ans;
    }

    // 入堆
    private void offer(int[] h, int val) {
        h[size] = val;
        swim(h, size);
        size++;
    }

    // 交换 h[i] 和 h[j]
    private void swap(int[] h, int i, int j) {
        int tmp = h[i];
        h[i] = h[j];
        h[j] = tmp;
    }
}
