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
public class LC1146_SnapshotArray2 {
   //不全部复制数组， 这里的history结构只是用于存储对应索引位置的修改记录， 内部的int[0]是快照号， int[1]是实际值. 对int[]数组使用标准的二分查找
    private Map<Integer, List<int[]>> history = new HashMap<>();
    private int snap = 0;
    public LC1146_SnapshotArray2(int length) {
        for(int i = 0;i<length;i++){
            int[] item = new int[2];
            item[0] = 0;
            item[1] = 0;//初始所有元素都是0
            List<int[]> list = new ArrayList<>();
            list.add(item);
            history.put(i,list);
        }
    }
    
    /**
     * 索引处的快照链并不严格连续， 可以跳版本（因为某版本并不一定会对所有索引位置做修改）， 只要是趋势递增即可
     */
    public void set(int index, int val) {
        List<int[]> list = history.get(index);
        //这里不能调用snap()函数，函数会让snap值升版
        int version = snap;
        //JDK移除元素需要耗费时间复杂度， 所以不要去比较版本做移除, 即使int[]中存在版本项目项， 也不影响二分查找
        int[] item = new int[2];
        item[0] = version;
        item[1] = val;
        list.add(item);
    }
    
    public int snap() {
        return snap++;
    }
    
    public int get(int index, int snap_id) {
        List<int[]> list = history.get(index);

        //求<=x的最大下标
        int l = lowerBound(list,snap_id+1)-1;

        return list.get(l)[1];
    }

    private int lowerBound(List<int[]> list,int targetVersion){
        int n = list.size();
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(list.get(mid)[0]<targetVersion){
                l = mid+1;

            }else{
                r = mid;
            }
        }
        return l;
    }
}
