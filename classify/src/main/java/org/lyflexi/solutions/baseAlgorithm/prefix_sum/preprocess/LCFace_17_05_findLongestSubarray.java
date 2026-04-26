package org.lyflexi.solutions.baseAlgorithm.prefix_sum.preprocess;

/**
 * 面试题 17.05. 字母与数字
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 *
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 *
 * 示例 1：
 *
 * 输入：["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 *
 * 输出：["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 * 示例 2：
 *
 * 输入：["A","A"]
 *
 * 输出：[]
 * 提示：
 *
 * array.length <= 100000
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 预处理前缀和 + 哈希
 */
public class LCFace_17_05_findLongestSubarray {
    public String[] findLongestSubarray(String[] array) {
        //把 数字 看成 −1，把字母看成 1, 计算和为 0 的最长子数组。这样就有了子数组和的比较对象target == 0
        int n = array.length;
        int[] preS = new int[n+1];
        for(int i = 1; i<n+1; i++){
            char c = array[i-1].charAt(0);
            int x = 0;
            if(Character.isAlphabetic(c)){
                x = 1;
            }else if(Character.isDigit(c)){
                x = -1;
            }
            preS[i] = preS[i-1] + x;
        }

        //符合要求的子数组 [begin,end)
        int begin = 0, end = 0;
        //存储最早出现的下标
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//适配前缀和本身就是子数组的情况
        for(int r = 0; r< n; r++){
            //s2 - s1 == 0, 即s2 == s1, 求len(s2 -s1)
            int s1 = preS[r + 1];
            if(map.containsKey(s1)){
                if(r - map.get(s1) > end - begin){
                    end = r + 1;
                    begin = map.get(s1)+1;
                }
            }else{
                //store earliest idx
                map.put(s1, r);
            }

        }

        String[] sub = new String[end - begin];
        System.arraycopy(array, begin, sub, 0, sub.length);
        return sub;
    }
}
