package 自己写的观察者模式Observer;
/*
 * @Description $
 * @Date 2021/3/29$ 14:45$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */

import java.util.ArrayList;
import java.util.List;

public class RealSubject implements Subject {

    private List<Observer> observerList = new ArrayList();

    public void attach(Observer observer) {
        observerList.add(observer);
    }

    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyChanged() {
        for (Observer o :observerList) {
            o.update();
        }
    }
}
