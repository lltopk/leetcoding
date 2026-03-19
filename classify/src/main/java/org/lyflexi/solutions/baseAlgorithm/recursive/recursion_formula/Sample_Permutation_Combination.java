package org.lyflexi.solutions.baseAlgorithm.recursive.recursion_formula;

public class Sample_Permutation_Combination {
    public static void main(String[] args) {
        System.out.println(permutation(2,4));
        System.out.println(permutation(4,2));

        System.out.println(combination(2,4));
        System.out.println(combination(4,2));

        System.out.println(ddppCombination(2,4));
        System.out.println(ddppCombination(4,2));
    }

    /**
     * 求排列总数 A(a,b) = !a/!(a-b) = a* !(a-1)/!((a-1) - (b-1)) = a*A(a-1, b-1)
     * @param a
     * @param b
     * @return
     */
    private static Integer permutation(Integer a, Integer b) {
        //非法校验, 不参与计算
        if (a == null || b == null) {
            return -1;
        }
        //非法校验, 不参与计算
        if (a <0 || b <0) {
            return -1;
        }
        //非法校验, 不参与计算
        if (a < b){
            return -1;
        }

        //递归出口 A(a, 0) = 1
        if (b == 0) {
            return 1;
        }
        return a * permutation(a - 1, b-1);
    }

    /**
     * 求组合总数 C(a,b) = !a/(!b!(a-b)) = a* !(a-1)/(!b!(a-b))
     * = (a-b)* !(a-1)/(!b!(a-b)) + b* !(a-1)/(!b!(a-b))
     * = !(a-1)/(!b!(a-b-1)) + !(a-1)/(!(b-1)!(a-b))
     * = C(a-1,b) + C(a-1, b-1)
     * @param a
     * @param b
     * @return
     */
    private static Integer combination(Integer a, Integer b) {
        //非法校验, 不参与计算
        if (a == null || b == null) {
            return -1;
        }
        //非法校验, 不参与计算
        if (a <0 || b <0) {
            return -1;
        }
        //非法校验, 不参与计算
        if (a < b){
            return -1;
        }

        //递归出口 C(a, 0) 和 C(a, a)都直接等于1
        if (b == 0 || a.equals(b)) {
            return 1;
        }
        b = Math.min(b, a - b);//利用组合的对称性C(a, b) == C(a, a-b), 降低递归深度
        return combination(a - 1, b) + combination(a - 1, b -1 );
    }

    /**
     * 上面递归求组合是指数级时间复杂度2^a
     *
     * 动态规划降低时间复杂度为a(a+1)/2
     * @param a
     * @param b
     * @return
     */
    private static Integer ddppCombination(Integer a, Integer b) {
        //非法校验, 不参与计算
        if (a == null || b == null) {
            return -1;
        }
        //非法校验, 不参与计算
        if (a <0 || b <0) {
            return -1;
        }
        //非法校验, 不参与计算
        if (a < b){
            return -1;
        }

        //索引i,j为数字, dp[i][j]数组值为组合结果
        int[][] dp = new int[a + 1][b + 1];

        //确定好递归式之后, 开始填表
        for (int i = 0; i < a+1; i++) {
            //Math.min(i, b): j大于i的计算无意义, 并且可以避免当i=0的时候进入else导致dp[-1]越界
            for (int j = 0; j <= Math.min(i, b); j++) {
                if (j == 0 || i == j){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }
            }
        }

        return dp[a][b];
    }
}
