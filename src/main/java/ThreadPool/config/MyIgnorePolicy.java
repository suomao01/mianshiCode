package ThreadPool.config;
/*
 * @Description 拒绝策略$
 * @Date 2021/3/30$ 20:24$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyIgnorePolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        if (!e.isShutdown()) {
            r.run();
        }
        doLog(r, e);
    }

    private void doLog(Runnable r, ThreadPoolExecutor e) {
        // 可做日志记录等
        System.err.println( r.toString() + " rejected");
    }
}
