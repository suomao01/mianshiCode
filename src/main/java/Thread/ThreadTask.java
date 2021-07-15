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
        FutureTask<Integer> futureTask = new FutureTask<>(new ThreadTask());
        executorService.submit(futureTask);
//        Future future = executorService.submit(new ThreadTask());
        Object o = futureTask.get();
        System.out.println(o.toString());
        executorService.shutdown();
    }
}
