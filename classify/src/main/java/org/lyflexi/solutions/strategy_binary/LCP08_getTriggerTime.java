/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_binary;

/**
 *
 * @author hasee
 */
public class LCP08_getTriggerTime {

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int m = increase.length;
        int[][] orderdArr = new int[m][3];
        orderdArr[0] = increase[0];
        for(int i = 1;i < m; i++){
            for(int j = 0; j<3; j++){
                orderdArr[i][j] = increase[i][j]+ orderdArr[i-1][j];
            }
        }

        int[] res = new int[requirements.length];

        for(int i = 0;i<requirements.length; i++){
            if(requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0){
                res[i] = 0;
                continue;
            }
            //直接对二位数组进行二分
            int l = lowerBound0(orderdArr, requirements[i]);
            if(l==m){
                res[i] = -1;
            }else{
                res[i] = l+1;
            }
        }

        return res;
    }

    private int lowerBound0(int[][] arr, int[] target){
        int len = arr.length;
        int l = 0, r = len;
        while(l<r){

            int mid = l + ((r-l)>>1);
            if(arr[mid][0]<target[0] || arr[mid][1]<target[1] || arr[mid][2]<target[2] ){
                l = mid+1;
            }else{
                r = mid;
            }
        }

        return l;
    }
}