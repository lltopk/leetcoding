红黑树有时候很有用, 其键的集合`keys`是有序的, 达到二分访问目标元素的效果, 查找时间复杂度是logn

在Java中有两个集合都用到了红黑树
- TreeSet: 适用于不需要计数的场景, 比如迭代求最大/最小
- TreeMap: 适用于计数场景, 统计总量/总数


## TreeSet
快速查找, 适用于不需要计数的场景, 比如迭代求最大/最小
```java
TreeSet<Integer> treeSet = new TreeSet<>();

//获取比key小的key, 严格小于
treeSet.lower(key);
//获取比key小的key, 小于等于
treeSet.floor(key);
//获取比key大的key, 严格大于
treeSet.higher(key);
//获取比key大的key, 大于等于
treeSet.ceiling(key);
//获取最后一个key
treeSet.last(key);
//获取第一个key
treeSet.first(key);
```

## TreeMap
和`TreeSet`的`api`类似, 由于是`map`, 多了获取`entry`的`api`
```java
TreeMap<Integer, Integer> treeMap = new TreeMap<>();

//获取比key小的key, 严格小于
treeMap.lowerKey(key);
//获取比key大的key, 严格大于
treeMap.higherKey(key);
//获取比key小的entry, 严格小于
treeMap.lowerEntry(key);
//获取比key大的entry, 严格大于
treeMap.higherEntry(key);

//获取比key小的key, 小于等于
treeMap.floorKey(key);
//获取比key大的key, 大于等于
treeMap.ceilingKey(key);
//获取比key小的entry, 小于等于
treeMap.floorEntry(key);
//获取比key大的entry, 大于等于
treeMap.ceilingEntry(key);


//获取最后一个key
treeMap.lastKey()
//获取第一个key
treeMap.firstKey()
```