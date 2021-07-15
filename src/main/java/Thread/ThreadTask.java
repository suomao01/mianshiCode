package Thread;

import java.util.concurrent.*;

public class ThreadTask implements Callable {

    public static void go() {
        System.out.println("do a");
    }

    @Override
    public Object call() throws Exception {
        return "狗狗欧冠";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new ThreadTask());
        Object o = future.get();
        System.out.println(o.toString());
        executorService.shutdown();
    }
}
