package org.lyflexi.solutions.strategy_binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/20 22:18
 * @description
 * 658. 找到 K 个最接近的元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 * 输入：arr = [1,1,2,3,4,5], k = 4, x = -1
 * 输出：[1,1,2,3]
 */
public class LC658_findClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, n = arr.length ,  r = n;
        while(l<r){
            int midIndex = l + ((r-l)>>1);
            if(arr[midIndex]>x){
                r = midIndex;
            }else if(arr[midIndex]<x){
                l = midIndex +1;
            }else{
                //找到目标元素, 用双指针进一步计算
                l = midIndex;
                return tp(l,k,arr,x);
            }
        }

        //没找到目标元素, 最好办了
        if(l == 0){
            return Arrays.stream(Arrays.copyOfRange(arr,0,k))
                    .boxed()
                    .collect(Collectors.toList());
        }

        if(l==n){
            return Arrays.stream(Arrays.copyOfRange(arr,n-k,n))
                    .boxed()
                    .collect(Collectors.toList());
        }

        return tp(l,k,arr,x);
    }

    private List<Integer> tp(int mid, int k, int[] arr, int x){
        //arr[mid]不一定等于x, 因此双指针初始化相邻[mid-1,mid], 与目标x进行比较
        //不能设置[mid-1,mid+1], 会漏掉mid
        //不能设置[mid,mid], 会重复mid
        int l = mid-1,r= mid;
        int c = 0;
        int n = arr.length;
        List<Integer> rs = new ArrayList<Integer>();
        while(c<k){
            if(l<0){
                r++;
                c++;
                continue;
            }

            if(r==n){
                l--;
                c++;
                continue;
            }

            //约定两边距离相等  先用左边
            if(l>=0 && r<n && Math.abs(arr[l]-x)<=Math.abs(arr[r]-x)){
                l--;
            }else if(l>=0 && r<n && Math.abs(arr[l]-x)>Math.abs(arr[r]-x)){
                r++;
            }
            c++;
        }
        System.out.println(l);
        System.out.println(r);
        for(int i = l+1;i<r;i++){
            rs.add(arr[i]);
        }
        return rs;
    }
}
