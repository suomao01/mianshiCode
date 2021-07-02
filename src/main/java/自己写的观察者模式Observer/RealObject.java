package 自己写的观察者模式Observer;/*
 * @Description $
 * @Date 2021/3/29$ 14:47$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */

public class RealObject implements Observer {
    public void update() {
        System.out.println("接收到到了通知");
    }
}
