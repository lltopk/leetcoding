package org.lyflexi.solutions.baseAlgorithm.utils.stack;


import java.util.Stack;

public class Test3_1 {
    /**
     * Java中的引用地址值传递, 而不是引用传递
     *
     * 核心把握住是否有对内存本身进行修改即可
     * @param args
     */
    public static void main(String[] args) {
        //p1指向空间
        StringBuilder p1 = new StringBuilder("abc");
        TargetObject targetObject = new TargetObject(p1);

        //影响对象内的
        p1.setLength(1);
        p1.setCharAt(0,'d');
        System.out.println(targetObject.p);

        //不影响对象内的p
        p1 = new StringBuilder("efg");
        System.out.println(targetObject.p);
    }

    static class TargetObject{
        public StringBuilder p;

        public TargetObject(StringBuilder p){
            this.p = p;
        }

        public void setP(StringBuilder p){
            this.p = p;
        }
    }
}
