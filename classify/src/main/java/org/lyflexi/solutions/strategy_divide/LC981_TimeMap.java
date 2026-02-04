package org.lyflexi.solutions.strategy_divide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/12 16:39
 * @description
 * 981. 基于时间的键值存储
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 设计一个基于时间的键值数据结构，该结构可以在不同时间戳存储对应同一个键的多个值，并针对特定时间戳检索键对应的值。
 *
 * 实现 TimeMap 类：
 *
 * TimeMap() 初始化数据结构对象
 * void set(String key, String value, int timestamp) 存储给定时间戳 timestamp 时的键 key 和值 value。
 * String get(String key, int timestamp) 返回一个值，该值在之前调用了 set，其中 timestamp_prev <= timestamp 。如果有多个这样的值，它将返回与最大  timestamp_prev 关联的值。如果没有值，则返回空字符串（""）。
 *
 * 示例 1：
 *
 * 输入：
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * 输出：
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 *
 * 解释：
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // 存储键 "foo" 和值 "bar" ，时间戳 timestamp = 1
 * timeMap.get("foo", 1);         // 返回 "bar"
 * timeMap.get("foo", 3);         // 返回 "bar", 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"） 。
 * timeMap.set("foo", "bar2", 4); // 存储键 "foo" 和值 "bar2" ，时间戳 timestamp = 4
 * timeMap.get("foo", 4);         // 返回 "bar2"
 * timeMap.get("foo", 5);         // 返回 "bar2"
 *
 *
 * 提示：
 *
 * 1 <= key.length, value.length <= 100
 * key 和 value 由小写英文字母和数字组成
 * 1 <= timestamp <= 107
 * set 操作中的时间戳 timestamp 都是严格递增的
 * 最多调用 set 和 get 操作 2 * 105 次
 */
public class LC981_TimeMap {
    private Map<String, List<Entry>> store;

    /**
     * 哈希  + 二分
     */
    public LC981_TimeMap() {
        store = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(store.get(key) == null){
            store.put(key,new ArrayList<Entry>(){{add(new Entry(value,timestamp));}});
            return;
        }
        List<Entry> entrys = store.get(key);
        entrys.add(new Entry(value,timestamp));
        return;
    }

    public String get(String key, int timestamp) {
        if(!store.containsKey(key)){
            return "";
        }
        List<Entry> entrys = store.get(key);

        // Collections.sort(entrys,(e1,e2)->{return e1.timestamp-e2.timestamp;});

        int l = 0,r = entrys.size();

        while(l<r){
            int midIdx = l+((r-l)>>1);
            if(entrys.get(midIdx).timestamp>timestamp){
                r = midIdx;
            }else if(entrys.get(midIdx).timestamp<timestamp){
                l = midIdx +1;
            }else{
                l = r = midIdx;
                return entrys.get(l).v;
            }
        }

        //小于最小值
        if(l==0){
            return "";
        }

        return entrys.get(l-1).v;
    }


    class Entry{
        public String v;
        public Integer timestamp;
        public Entry(String v,Integer timestamp){
            this.v = v;
            this.timestamp = timestamp;
        }
    }
}
