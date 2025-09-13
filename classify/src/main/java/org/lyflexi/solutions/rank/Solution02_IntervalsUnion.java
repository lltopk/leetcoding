package org.lyflexi.solutions.rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/3/20 12:21
 */

/*
* 56. 合并区间
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。


示例 1：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
*
示例 2：
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
* */
public class Solution02_IntervalsUnion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入行数和列数
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of columns:");
        int columns = scanner.nextInt();
        // 创建二维数组
        int[][] array = new int[rows][columns];
        // 输入数组元素
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        // 输出输入的二维数组
        System.out.println(Arrays.stream(merge(array)));
        scanner.close();
    }
    public static int[][] merge(int[][] intervals) {
        List<int[]> answer = new ArrayList<>();

        Arrays.sort(intervals,(int[] a , int[] b)->{
            return a[0] - b[0];
        });

        //初始化答案
        answer.add(intervals[0]);

        for (int i=1;i<intervals.length;i++){
            int[] interval = intervals[i];
            int[] lastInterval = answer.get(answer.size()-1);
            //情况一，追加答案
            if (lastInterval[1]<interval[0]){
                answer.add(interval);
            } else  if (lastInterval[1]>=interval[0]){//情况二，合并后的结果覆盖末位元素
                int[] newTail = new int[]{lastInterval[0],Math.max(lastInterval[1],interval[1])};
                answer.remove(lastInterval);
                answer.add(newTail);
            }
        }
        return answer.toArray(new int[answer.size()][]);

    }
}
