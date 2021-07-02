package 锁;
/**
 * @Description synchronize 锁相关
 * synchronized保证了线程的原子性。(被保护的代码块是一次被执行的，没有任何线程会同时访问)
 * synchronized还保证了可见性。(当执行完synchronized之后，修改后的变量对其他的线程是可见的)
 * Java中的synchronized，通过使用内置锁，来实现对变量的同步操作，进而实现了对变量操作的原子性和其他线程对变量的可见性，从而确保了并发情况下的线程安全。
 * @Date 2021/6/15 14:13
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
public class SynchRonizedUtil {

    static int count =0;
    public static void doSomeThing(int i){
        int counted = count++;
        System.out.println(counted);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
           doSomeThing(1);
        }
    }

}
