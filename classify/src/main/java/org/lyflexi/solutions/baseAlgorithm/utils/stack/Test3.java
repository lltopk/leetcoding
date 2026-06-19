package org.lyflexi.solutions.baseAlgorithm.utils.stack;


public class Test3 {
    /**
     * Java中的引用地址值传递, 而不是引用传递
     *
     * 核心把握住是否有对内存本身进行修改即可
     * @param args
     */
    public static void main(String[] args) {
        //p1指向空间
        StringBuilder p1 = new StringBuilder("abc");
        StringBuilder p2 = new StringBuilder();
        //p2指向相同的空间
        p2 = p1;
        //p1指向新的空间, 不影响p2指向旧的空间
        p1 = new  StringBuilder("def");
        System.out.println("p1: "+p1);
        System.out.println("p2: "+p2);
        System.out.println("----------");

        //除非明确让p2也指向新的空间
        p2 = p1;
        System.out.println("p2: "+p2);
        System.out.println("----------");

        noChangeP1P2(p1);
        System.out.println("p1: "+p1);
        System.out.println("p2: "+p2);
        System.out.println("----------");

        changeP1P2(p1);
        System.out.println("p1: "+p1);
        System.out.println("p2: "+p2);
    }

    /**
     * 副本指针p指向新的地址空间, 不对原有地址造成影响
     * @param p
     */
    private static void noChangeP1P2(StringBuilder p) {
        p = new StringBuilder("ghi");
    }

    /**
     * 副本指针p修改了原有空间的内容
     * @param p
     */
    private static void changeP1P2(StringBuilder p) {
        p.setLength(0);
    }

}
