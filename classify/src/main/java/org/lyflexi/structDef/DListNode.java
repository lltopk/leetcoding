package org.lyflexi.structDef;

/**
 * @Author: ly
 * @Date: 2024/2/10 13:37
 */
public class DListNode {
    private int key;
    private int value;//用于已有key的新增，覆盖元素
    public DListNode pre;
    public DListNode next;

    public DListNode(int key, int value){
        this.key = key;
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public Integer getKey(){
        return this.key;
    }


}
