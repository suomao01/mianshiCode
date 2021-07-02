package ThreadPool;
/*
 * @Description join方法的调用，当前线程中阻塞等待其他线程执行完再执行当前线程
 * @Date 2021/3/29$ 17:15$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
public class ThreadLearnForJoin extends Thread {
    static long currentTimeMillis;
    static int count = 0;
    static Thread thread = null;
    static Thread thread1 = null;
    public static synchronized void test() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束了" + count++);
    }
    public static void doSomeThing(){
        try {
            for (int i = 0; i < 10; i++) {
                thread = new Thread(ThreadLearnForJoin::test, Thread.currentThread().getName());
                thread.start();
                thread.join();
                Thread.sleep(1);
            }
            System.out.println(System.currentTimeMillis() - currentTimeMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws InterruptedException {
        currentTimeMillis = System.currentTimeMillis();
        //java8启动线程
        thread1 = new Thread(ThreadLearnForJoin::doSomeThing);
        thread1.start();
    }

}
