package org.lyflexi.solutions.baseAlgorithm.slippoints;

public class Test3 {
    public static void main(String[] args) {
        //128个ascii码
        int[] dict = new int[128];

        //计算机会自动将ascii字符转为对应的数值
        dict['a'] = 1;//等价于dict[97]

        for (int i = 0; i < 128; i++) {
            System.out.println(i + ": " +dict[i]);
        }
    }
}
