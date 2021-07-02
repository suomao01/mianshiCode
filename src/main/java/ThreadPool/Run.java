package ThreadPool;/*
 * @Description $
 * @Date 2021/3/30$ 9:25$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
import ThreadPool.config.MyIgnorePolicy;
import ThreadPool.config.ThreadPoolFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class Run {

    static class TaskThread implements Runnable {

        CountDownLatch latch;
        int i;
        public TaskThread(int i,CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }
        @Override
        public String toString() {
            return "MyTask [name=" + i + "]";
        }
        public TaskThread() {

        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName()+"任务："+i+"task is done");
                latch.countDown();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        //处理的业务数量
        int threadNum = 10;
        // 线程执行次数计数器
        CountDownLatch latch = new CountDownLatch(threadNum);
        //超出线程 执行拒绝策略
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        // 创建线程池
        ThreadPoolExecutor executor = ThreadPoolFactory.initPool(6, 3, "胡萝卜线程池",handler);
        // 预启动所有核心线程
        executor.prestartAllCoreThreads();
        // 执行业务
        for (int i = 0; i <threadNum; i++) {
            executor.execute(new TaskThread(i,latch));
        }
        System.out.println("Task Start!");
        latch.await();
        System.out.println("All Task is Done!");
        executor.shutdown();
    }

}

