package ThreadPool.config;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 版权 (C) 锋创科技有限公司
 * 作者: Lihaiyong
 * 日期: 30/06/2020 18:18
 * 描述: 线程池生产类
 * 版本: @since 1.0.0
 * 修改:
 */
public class ThreadPoolFactory{

    private static ThreadPoolExecutor threadPoolExecutor;

    /**
     * 创建线程池 , 超出阻塞线程数的阻塞线程会被丢弃
     * @Date 30/06/2020 19:17
     * @param maximumPoolSize     线程池维护最大线程数
     * @param blockingqueSize     阻塞中的最大线程数
     * @param poolName            线程池名称
     * @return java.util.concurrent.ThreadPoolExecutor
     **/
    public static ThreadPoolExecutor initPool( int maximumPoolSize,
                                               int blockingqueSize,
                                               String poolName,
                                               RejectedExecutionHandler handler){
        if(threadPoolExecutor==null){
            synchronized (ThreadPoolFactory.class){
                threadPoolExecutor = new ThreadPoolExecutor(
                        2,               //一直存在的核心线程 // Runtime.getRuntime().availableProcessors() 获取的cpu核心线程数也就是计算资源
                        maximumPoolSize,            //最大线程数
                        10,                         //除了核心线程以外的线程的存活时间
                        TimeUnit.SECONDS,           //上个存活时间的单位
                        new LinkedBlockingDeque<Runnable>(blockingqueSize),     //排队阻塞任务的最大数量
                        new MyThreadFactory(poolName),                          //线程工厂
                        handler                        //阻塞任务超出的处理逻辑
                );
                return threadPoolExecutor;
            }
        }
        return threadPoolExecutor;
    }

    public static class MyThreadFactory implements ThreadFactory{
        private String groupName;
        private AtomicInteger nextId = new AtomicInteger(1);

        public MyThreadFactory(String groupName) {
            this.groupName = "ThreadPoolFactory ---" + groupName + "---worker---";
        }

        @Override
        public Thread newThread(@NotNull Runnable r) {
            String threadName = groupName + nextId.incrementAndGet();
            Thread thread = new Thread(null,r,threadName,0);
            return thread;
        }
    }

}
