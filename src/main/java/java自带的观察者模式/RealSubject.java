package java自带的观察者模式;/*
 * @Description $
 * @Date 2021/3/29$ 14:53$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */

import java.util.Observable;
import java.util.Observer;

public class RealSubject extends Observable {

    public void makeChanged(){
        setChanged();
        notifyObservers();
    }
}

class RealObserver implements Observer{

    public void update(Observable o, Object arg) {
        System.out.println("调用了--》");
    }

    public static void main(String[] args) {
        RealSubject subject = new RealSubject();
        RealObserver realObserver = new RealObserver();
        subject.addObserver(realObserver);
        subject.makeChanged();
    }
}
