package 自己写的观察者模式Observer;
/*
 * @Description
 * @Date 2021/3/29$ 14:48
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
public class Run {

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Observer observer = new RealObject();
//        subject.attach(observer);
        subject.notifyChanged();
    }
}
