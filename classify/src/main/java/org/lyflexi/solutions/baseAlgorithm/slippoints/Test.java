package org.lyflexi.solutions.baseAlgorithm.slippoints;

public class Test {
    public static void main(String[] args) {
        int[] arrivals = {1, 2, 1, 3, 1};
        int w = 4;
        int m = 2;
        
        System.out.println("arrivals = " + java.util.Arrays.toString(arrivals));
        System.out.println("w = " + w);
        System.out.println("m = " + m);
        
        int result = minArrivalsToDiscard(arrivals, w, m);
        System.out.println("最少需要丢弃的货物数量: " + result);
    }

    public static int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int n = arrivals.length;
        int ans = 0;
        int l = 0;
        int[] helper = new int[100001];//辅助字典
        for(int i = 0; i< n; i++){
            helper[arrivals[i]]++;

            if(i - l + 1 < w){
                if(helper[arrivals[i]] > m){
                    ans++;
                    helper[arrivals[i]]--;//表示丢弃
                    arrivals[i] = 0;//表示丢弃
                }
            }else if(i - l + 1 == w){
                if(helper[arrivals[i]] > m){
                    ans++;
                    helper[arrivals[i]]--;//表示丢弃
                    arrivals[i] = 0;//表示丢弃
                }

                //如果窗口左边界值为0, 表示贪心提前丢弃了窗口右边界
                //此时helper[0]--避免重复丢弃helper[有效货物], 且不会对答案造成影响
                //如果重复丢弃了helper[有效货物], 则最终答案会变小
                helper[arrivals[l]]--;
                //移动窗口左边界, 为下次循环做准备
                l++;
            }
        }

        return ans;
    }
}
