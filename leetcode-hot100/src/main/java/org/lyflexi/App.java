package org.lyflexi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    /**
     * 输入A B C任务, 并行执行, 要求输出结果是有序的A结果 B结果 C结果
     * @param tasks
     * @param executor
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    static List<String> runTools(
            List<Callable<String>> tasks,
            ExecutorService executor)
            throws InterruptedException, ExecutionException{
        List<Future<String>> futureList = new ArrayList<>();
        List<String> ret = new ArrayList<>();
        for (Callable<String> task : tasks) {
            Future<String> future = executor.submit(task);
            futureList.add(future);
        }

        for (Future<String> future : futureList) {
            ret.add(future.get());
        }

        return ret;

    }

}
