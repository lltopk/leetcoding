package com.hm.companyydtx.coding;

import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/2 15:10
 */


/*
622. 设计循环队列
使用数组去实现循环队列
循环队列的一个好处是我们可以利用这个队列之前用过的空间。
在一个普通队列里，一旦一个队列满了我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
*
*/


public class Q1_LoopQueueByArray {
        private int front;
        //rear指针是一定时时刻刻指向最后一个元素的下一个位置的,这是一定的，是客观存在的，请保持习惯
        private int rear;
        private int capacity;
        private int[] elements;//循环队列内部维护的数组，尺寸会比初始化容量多1

        public Q1_LoopQueueByArray(int k) {
            //队列判空的条件是front==rear
            //因为rear指针是一定时时刻刻指向最后一个元素的下一个位置的，所以队列判满的条件也是front==tail无法区分.


            //为了区分开队列空和队列满，这里想了一个办法，创建数组的时候多一个格子，多出来的格子不放元素，只是为了创造队列满的条件：(rear+1)%capacity == front
            capacity = k + 1;
            elements = new int[capacity];
            rear = front = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            elements[rear] = value;//循环队列不可以用rear++，下面要进行除模运算
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;//循环队列front指针往前走，就相当于抛弃了当前的元素，因为rear后续enQueue的时候会覆盖无效元素
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return elements[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            //因为数组是循环的，当rear-1=0不代表数组中仅有1个元素，防止数组越界
            return elements[(rear - 1 + capacity) % capacity];//rear日常指向末位元素的下一个位置
        }

        public boolean isEmpty() {
            return rear == front;//队列空状态
        }

        public boolean isFull() {
            return ((rear + 1) % capacity) == front;//队列满状态，rear和front挨着
        }

        /*["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
        *
        * [[3],[1],[2],[3],[4],[],[],[],[4],[]]
        *
        * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer k = Integer.parseInt(scanner.nextLine());
        Integer item1 = Integer.parseInt(scanner.nextLine());
        Integer item2 = Integer.parseInt(scanner.nextLine());
        Integer item3 = Integer.parseInt(scanner.nextLine());
        Integer item4 = Integer.parseInt(scanner.nextLine());
        Integer item5 = Integer.parseInt(scanner.nextLine());


        Q1_LoopQueueByArray ques03LoopQueue = new Q1_LoopQueueByArray(k);
        System.out.println(ques03LoopQueue.enQueue(item1));
        System.out.println(ques03LoopQueue.enQueue(item2));
        System.out.println(ques03LoopQueue.enQueue(item3));
        System.out.println(ques03LoopQueue.enQueue(item4));

        System.out.println(ques03LoopQueue.Rear());
        System.out.println(ques03LoopQueue.isFull());
        System.out.println(ques03LoopQueue.deQueue());
        System.out.println(ques03LoopQueue.enQueue(item5));
        System.out.println(ques03LoopQueue.Rear());

    }

}
