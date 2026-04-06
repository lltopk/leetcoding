红黑树有时候很有用, 实现了Key的有序存储, 与O(1)访问

在Java中有两个集合都用到了红黑树
- TreeSet
- TreeMap


## TreeSet
快速查找
```java
TreeSet<Integer> treeSet = new TreeSet<>();

//获取比key小的key
treeSet.lower(key);
//获取比key大的key
treeSet.higher(key);
//获取最后一个key
treeSet.last(key);
//获取第一个key
treeSet.first(key);
```

## TreeMap
快速查找
```java
TreeMap<Integer, Integer> treeMap = new TreeMap<>();

//获取比key小的key
treeMap.lowerKey(key);
//获取比key大的key
treeMap.higherKey(key);
//获取最后一个key
treeMap.lastKey()
//获取第一个key
treeMap.firstKey()
```