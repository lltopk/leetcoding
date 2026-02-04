/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hasee
 */
public class LC3508_Router {
    private record Packet(int source, int destination, int timestamp){} 

    private record Pair(List<Integer> timestamps, int start){}
    
    //二分预备数组, 通过标记list中头元素等价删除效果， 可以不用移动元素达到O1复杂度
    //不能使用Deque是因为， 二分查找需要get(mid)， 而Deque不支持get
    private Map<Integer, Pair> map;

    //依据拿到的出队元素的dest， 添加新的元素到指定的二分数组中
    //还可以判断容量以及判断重复
    private Deque<Packet> deque;

    //hashset是有必要的, 因为deque.contains(o)是个循环实现, 复杂度高, 会超时
    //而hashset.contains(o)是哈希O1
    private final Set<Packet> set = new HashSet<>(); // Packet 集合

    private int limit;

    public LC3508_Router(int memoryLimit) {
        limit = memoryLimit;
        map = new HashMap<>();
        deque = new ArrayDeque<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source,destination,timestamp);

        if(set.contains(packet)){
            return false;
        }

        if(deque.size()==limit){
            forwardPacket();
        }

        deque.offer(packet);
        set.add(packet);
        map.computeIfAbsent(packet.destination, k -> {return new Pair(new ArrayList<>(), 0);}).timestamps.add(timestamp);

        return true;
    }
    
    public int[] forwardPacket() {
        if(deque.isEmpty()){
            return new int[]{};
        }
        Packet packet = deque.pop();

        set.remove(packet);
        map.compute(packet.destination, (k,v)->{ return new Pair(v.timestamps, v.start+1);});
        return new int[]{packet.source, packet.destination, packet.timestamp};
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        Pair pair = map.get(destination);
        if(pair == null){
            return 0;
        }
        int l = lowerBound(pair.timestamps, startTime, pair.start);
        int r = lowerBound(pair.timestamps, endTime+1, pair.start);

        return r-l;
    }

    private int lowerBound(List<Integer> timestamps, int target, int start){
        int n = timestamps.size();
        int l = start, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(timestamps.get(mid)<target){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}
