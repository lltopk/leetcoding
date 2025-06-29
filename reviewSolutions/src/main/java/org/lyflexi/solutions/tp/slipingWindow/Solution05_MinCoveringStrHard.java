package org.lyflexi.solutions.tp.slipingWindow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/1 11:14
 */

/*76. 最小覆盖子串-最难的滑动窗口（窗口大小可伸缩，是不固定的）
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
注意：
对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。


示例 1：
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。


示例 2：
输入：s = "a", t = "a"
输出："a"
解释：整个字符串 s 是最小覆盖子串。

示例 3:
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。*/
public class Solution05_MinCoveringStrHard {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String t = in.nextLine();
        System.out.println(minWindow(s,t));
    }


    //记录start指针和窗口大小，return s.substring(start,start+minWindow);就不会超内存了
    public static String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int ansL = 0, ansR = tLen-1;
        int minWindow = Integer.MAX_VALUE;
        int start = 0;
        if (sLen<tLen) return "";

        if (tLen==1){
            for (int i = 0; i < sLen; i++) {
                if (s.charAt(i)==t.charAt(0)){
                    return String.valueOf(t.charAt(0));
                }
            }
            return "";
        }
        if (s.equals(t)){
            return t;
        }
        //使用map来存储串t，key为char，value为频次
        HashMap<Character, Integer> tMap = new HashMap<>();
        //使用map来截取串s，key为char，value为频次
        HashMap<Character, Integer> sMap = new HashMap<>();

        for (int i = 0; i < tLen; i++) {
            Character Lkey = t.charAt(i);
            tMap.put(Lkey, tMap.getOrDefault(Lkey,0)+1);

            Character sKey = s.charAt(i);
            sMap.put(sKey, sMap.getOrDefault(sKey,0)+1);
        }


        //手写map比较函数
        if (compareMap(sMap, tMap)) {
            return s.substring(ansL,ansR+1);
        }


        /*
         * 窗口大小不固定：用while
         *
         * 右窗口下标在一次循环当中会持续递增的，
         * 左窗口下标在一次循环当中会持续减少的，
         * */
        ansR++;
        while(ansR<sLen && ansL<=ansR){

//            动态扩张右窗口
            while (!compareMap(sMap, tMap)&&ansR<sLen) {
                Character rKey = s.charAt(ansR);
                sMap.put(rKey,sMap.getOrDefault(rKey,0)+1);
                ansR++;
            }

//            动态缩小左窗口
            while (compareMap(sMap, tMap)){

                if (ansR-ansL==tLen&&compareMap(sMap, tMap)){
                    return s.substring(ansL, ansR);
                }

                if (ansR - ansL < minWindow) {
                    start = ansL;
                    minWindow = ansR - ansL;
                }

                //左窗口右移
                Character lKey = s.charAt(ansL);
                Integer cnt = sMap.get(lKey);
                if (cnt==1){
                    sMap.remove(lKey);
                }else {
                    sMap.put(lKey,--cnt);
                }
                ansL++;

            }
        }


        return minWindow == Integer.MAX_VALUE ? "" :s.substring(start,start+minWindow);
    }


    //使用set集合超出了内存限制
//    public static String minWindow(String s, String t) {
//        int sLen = s.length();
//        int tLen = t.length();
//        int ansL = 0, ansR = tLen-1;
//        int minWindow = Integer.MAX_VALUE;
//        if (sLen<tLen) return "";
//
//        if (tLen==1){
//            for (int i = 0; i < sLen; i++) {
//                if (s.charAt(i)==t.charAt(0)){
//                    return String.valueOf(t.charAt(0));
//                }
//            }
//            return "";
//        }
//        if (s.equals(t)){
//            return t;
//        }
//        //使用map来存储串t，key为char，value为频次
//        HashMap<Character, Integer> tMap = new HashMap<>();
//        //使用map来截取串s，key为char，value为频次
//        HashMap<Character, Integer> sMap = new HashMap<>();
//
//        for (int i = 0; i < tLen; i++) {
//            Character Lkey = t.charAt(i);
//            tMap.put(Lkey, tMap.getOrDefault(Lkey,0)+1);
//
//            Character sKey = s.charAt(i);
//            sMap.put(sKey, sMap.getOrDefault(sKey,0)+1);
//        }
//        HashSet<String> strSet = new HashSet<>();
//
//
//        //手写map比较函数
//        if (compareMap(sMap, tMap)) {
////            minWindow = Math.min(ansR-ansL,minWindow);
////            strSet.add(s.substring(ansL,ansR+1));
//            return s.substring(ansL,ansR+1);
//        }
//
//
//
////         窗口大小不固定：用while
////         右窗口下标在一次循环当中会持续递增的，
////         左窗口下标在一次循环当中会持续减少的，
//
//        ansR++;
//        while(ansR<sLen && ansL<=ansR){
//
////            动态扩张右窗口
//            while (!compareMap(sMap, tMap)&&ansR<sLen) {
//                Character rKey = s.charAt(ansR);
//                sMap.put(rKey,sMap.getOrDefault(rKey,0)+1);
//                ansR++;
//            }
//
////            动态缩小左窗口
//            while (compareMap(sMap, tMap)){
//
//                if (ansR-ansL==tLen&&compareMap(sMap, tMap)){
//                    return s.substring(ansL, ansR);
//                }
//
//                minWindow = Math.min(ansR - ansL, minWindow);
//                strSet.add(s.substring(ansL, ansR));
//
////                if (ansL==0){
////                    minWindow = Math.min(ansR-ansL,minWindow);
////                    strSet.add(s.substring(ansL,ansR));
////                }else {
////                    minWindow = Math.min(ansR-ansL+1,minWindow);
////                    strSet.add(s.substring(ansL-1,ansR));
////                }
//
//                //左窗口右移
//                Character lKey = s.charAt(ansL);
//                Integer cnt = sMap.get(lKey);
//                if (cnt==1){
//                    sMap.remove(lKey);
//                }else {
//                    sMap.put(lKey,--cnt);
//                }
//                ansL++;
//
//            }
//        }
//
//        Optional<String> opt = strSet.stream().min(Comparator.comparing(String::length));
//
//        boolean present = opt.isPresent();
//
//        return present?opt.get():"";
//    }



    private static boolean compareMap(Map sMap,Map tMap){
        //tMap是确定的
        Iterator iterator = tMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            Character key = (Character)entry.getKey();
            Integer value = (Integer)entry.getValue();

            if ((Integer)sMap.getOrDefault(key,0) < value) {
                return false;
            }
        }
        return true;
    }
}
