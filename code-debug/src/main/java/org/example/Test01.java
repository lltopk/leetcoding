package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Test01 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }

    public int nthUglyNumber(int n) {
        List<Long> ls =  new ArrayList<Long>();
        Queue<Long> mixHeap = new PriorityQueue<>((a,b)->{
            return Long.compare(a,b);
        });
        mixHeap.add(1L);
        while(!mixHeap.isEmpty()){
            Long v = mixHeap.poll();
            if(!ls.contains(v)){
                ls.add(v);
                if(ls.size() == n){
                    break;
                }
                mixHeap.add(v*2);
                mixHeap.add(v*3);
                mixHeap.add(v*5);
            }
        }
        return ls.get(n-1).intValue();
    }
}