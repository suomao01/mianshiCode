package ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        int tasks = 10;
        int corePoolSize = 2;//核心线程池数量
        int maximumPoolSize = 4;//最大线程池大小
        long keepAliveTime = 10;//线程最大空闲时间
        TimeUnit unit = TimeUnit.SECONDS;//最大空闲时间单位
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        //线程工厂工厂
        ThreadFactory threadFactory = new NameTreadFactory();
        //线程计数器
        CountDownLatch latch = new CountDownLatch(tasks);
        //拒绝策略
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        //初始化线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
        for (int i = 1; i <= tasks; i++) {
            MyTask task = new MyTask(String.valueOf(i),latch);
            executor.execute(task);
        }
        latch.await();
        executor.shutdown();
        System.out.println("over");
    }

    /**
     * @Description 线程工厂
     * @Date 2021/3/31 9:44
     * @Author VparkFC-Mr.Suo
     * @Since version-1.0
     */
    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }

    }

    /**
     * @Description 拒绝策略
     * @Date 2021/3/31 9:44
     * @Author VparkFC-Mr.Suo
     * @Since version-1.0
     */
    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    /**
     * @Description 线程任务
     * @Date 2021/3/31 9:44
     * @Author VparkFC-Mr.Suo
     * @Since version-1.0
     */
    static class MyTask implements Runnable {
        private String name;
        private CountDownLatch latch;

        public MyTask(String name,CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
                latch.countDown();
                System.out.println(latch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
