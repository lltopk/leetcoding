package org.lyflexi.solutions.Ⅲdivideconquer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/18 16:09
 * @description
 * 1146. 快照数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 实现支持下列接口的「快照数组」- SnapshotArray：
 *
 * SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
 * void set(index, val) - 会将指定索引 index 处的元素设置为 val。
 * int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
 * int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
 *
 *
 * 示例：
 *
 * 输入：["SnapshotArray","set","snap","set","get"]
 *      [[3],[0,5],[],[0,6],[0,0]]
 * 输出：[null,null,0,null,5]
 * 解释：
 * SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
 * snapshotArr.set(0,5);  // 令 array[0] = 5
 * snapshotArr.snap();  // 获取快照，返回 snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
 *
 */
public class LC1146_SnapshotArray {
    int[] arr;
    //Key并不是快照id, 而是数组索引
    //Value并不是整个快照数组(全数组复制时间复杂度太高) , 而是当前索引下的值的修改记录 pair(快照id, 快照值)
    //这样我们就可以将pair[0]组成目标数组, 根据二分寻找pair[0], 即为目标快照ID
    Map<Integer, List<int[]>> itemHistoryMap = new HashMap<>();
    int snapCnt = 0;//?
    public LC1146_SnapshotArray(int length) {
        arr = new int[length];
    }

    //并不是每次set都代表新的快照id, 当前快照id取决于用户明确调用snap()函数
    public void set(int index, int val) {
        if(index>=arr.length){
            return;
        }
        this.arr[index] = val;
        List<int[]> itemHistory = itemHistoryMap.get(index);
        if(itemHistory == null){
            itemHistory = new ArrayList<>();
            itemHistory.add(new int[]{snapCnt,val});
        }else{
            itemHistory.add(new int[]{snapCnt,val});//假设快照id可以重复, 即同快照下连续多次set
        }
        //快照id默认就是升序的
        itemHistoryMap.put(index,itemHistory);
    }

    public int snap() {
        return snapCnt++;
    }

    public int get(int index, int snap_id) {
        if(index>=arr.length){
            return 0;
        }

        List<int[]> itemSnap = itemHistoryMap.get(index);
        if(itemSnap == null){
            return 0;
        }

        int l = 0, n = itemSnap.size(), r = n;
        //假设快照id重复, 找右界
        int rb = -1;
        while(l<r){
            int midIndex = l + ((r-l)>>1);
            if(itemSnap.get(midIndex)[0]<=snap_id){
                l = midIndex +1;
                rb = midIndex;
            }else if(itemSnap.get(midIndex)[0] > snap_id){
                r = midIndex;
            }
        }
        // ✅ 没找到任何 <= snap_id 的记录，返回默认值 0
        if (rb == -1) {
            return 0;
        }
        return itemSnap.get(rb)[1];
    }
}
