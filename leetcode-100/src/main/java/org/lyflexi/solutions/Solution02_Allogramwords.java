package org.lyflexi.solutions;

import java.util.*;

/**
 * @Author: ly
 * @Date: 2024/1/9 10:23
 */
/*给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的所有字母得到的一个新单词。



示例 1:

输入: strs = ["eat","tea","tan","ate","nat","bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:

输入: strs = [""]
输出: [[""]]
示例 3:

输入: strs = ["a"]
输出: [["a"]]*/

public class Solution02_Allogramwords {
    //2024.1.9题解
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] strs = Arrays.stream(scanner.nextLine().split(",")).toArray(String[]::new);
        System.out.println(groupAnagrams(strs).toString());
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
//        map的key是这一组异位词的唯一标识String
//        map的value是这一组异位词List<String>
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
//            将按照字母排序后的String作为key
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
//            map数组更新
            ArrayList<String> innerList = map.get(key);
            if (innerList == null) {
                innerList = new ArrayList<>();
            }
            innerList.add(str);
            map.put(key, innerList);


        }
        return new ArrayList<List<String>>((map.values()));
    }

/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strs = Arrays.stream(scanner.nextLine().split(",")).toArray(String[]::new);
        System.out.println(groupAnagrams(strs).toString());
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            //将排序后的单词作为唯一的key
            String key = new String(array);
            //value是一个list，代表当前组所有的元素
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }*/

}
