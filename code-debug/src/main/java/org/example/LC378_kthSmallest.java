package org.example;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/10/3 17:06
 */
public class LC378_kthSmallest {
    public int kthSmallest(int[][] matrix, int k) {
        //已经是有序矩阵
        int left = matrix[0][0];
        int n = matrix.length;
        int right = matrix[n-1][n-1];
        while(left<right){
            int mid = (left+right)>>1;
            //题干中有重复的数字, 所以这里要用>= , 而不是>
            if(countLessMid(matrix,mid)>=k){
                right = mid;
            }else if(countLessMid(matrix,mid)<k){
                left = mid +1;
            }
        }

        return left;
    }

    /**
     求小于target的个数
     */
    private int countLessMid(int[][] matrix, int target){
        int n = matrix.length;
        //选点  左下角
        int i = n-1;
        int j = 0;
        int rs = 0;
        while(i>=0 && j<n){
            if(matrix[i][j]<=target){
                rs += i+1;
                j++;
            }else{
                i--;
            }
        }

        return rs;
    }
}
