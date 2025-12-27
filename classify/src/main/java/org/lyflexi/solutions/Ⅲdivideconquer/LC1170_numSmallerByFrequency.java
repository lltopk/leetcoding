package org.lyflexi.solutions.Ⅲdivideconquer;

import java.util.Arrays;

/**
 * 1170. 比较字符串最小字母出现频次
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。
 *
 * 例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。
 *
 * 现在，给你两个字符串数组待查表 queries 和词汇表 words 。对于每次查询 queries[i] ，需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W 表示词汇表 words 中的每个词。
 *
 * 请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：queries = ["cbd"], words = ["zaaaz"]
 * 输出：[1]
 * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
 * 示例 2：
 *
 * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * 输出：[1,2]
 * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
 */
public class LC1170_numSmallerByFrequency {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {

        int n = words.length;
        int[] cntSources = new int[n];
        //words数组为二分数组, 求预处理之后排序, 求≥
        for(int i = 0;i<words.length;i++){
            String item = words[i];
            int cnt = preHandle(item);
            cntSources[i] = cnt;
        }
        Arrays.sort(cntSources);

        int m = queries.length;
        int[] ans = new int[m];

        for(int i = 0;i<m;i++){
            //n - (>x)
            ans[i]= n - lowerBound(cntSources,preHandle(queries[i])+1);
        }

        return ans;

    }

    private int lowerBound(int[] nums, int target){
        int n = nums.length;
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]<target){
                l = mid+1;
            }else{
                r = mid;
            }

        }
        return l;
    }

    private int preHandle(String str){
        //初始化最小值引用, 赋予max
        char minChar = 'z';
        int cnt = 0;
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if(c<minChar){
                minChar = c;
                cnt = 1;
            }else if(c== minChar){
                cnt++;
            }else{
                //minChar < c, skip
            }
        }

        return cnt;
    }
}
