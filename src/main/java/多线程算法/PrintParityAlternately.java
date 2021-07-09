package 多线程算法;

import java.util.Arrays;
import java.util.List;

/**
 * 创建两个线程，交替打奇偶
 */
public class PrintParityAlternately {

    private static int count = 0;

    private static List list=Arrays.asList("a","A","ai","b","B","bi","c","C","ci","d","D");

    private static Object o = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (o) {
                //奇数
                while (count < 10) {
                    int a = ++count;
                    System.out.println(Thread.currentThread().getName() + "******" + list.get(a));
                    System.out.println(Thread.currentThread().getState()+"第一个");
                    try {
                        o.notify();
                        if(count==9){
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                //偶数
                while (count < 10) {
                    int a = ++count;
                    System.out.println(Thread.currentThread().getName() + "******" + list.get(a));
                    System.out.println(Thread.currentThread().getState()+"第二个");
                    try {
                        o.notify();
                        if(count==9){
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                //偶数
                while (count < 10) {
                    int a = ++count;
                    System.out.println(Thread.currentThread().getName() + "******" + list.get(a));
                    System.out.println(Thread.currentThread().getState()+"第三个");
                    try {
                        o.notify();
                        if(count==9){
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
