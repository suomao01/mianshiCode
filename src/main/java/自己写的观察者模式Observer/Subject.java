package 自己写的观察者模式Observer;

/*观察者模式概念：在对象之间定义了一对多的依赖，这样一来，当一个对象改变状态，依赖它的对象会收到通知并自动更新。
观察者模式主要有两个角色

Subject 观察主题对象，也可以叫被观察或者被订阅对象
Observer 观察者或者订阅者对象，当Subject有变动，就会通知到每一个Observer


 * @Description 观察者主题对象
 * @Date 2021/3/29 14:38
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
public interface Subject {

    /**
     * 订阅操作
     * @param observer
     */
    void attach(Observer observer);

    /**
     * 取消订阅操作
     */
    void detach(Observer observer);

    /**
     * 通知变动
     */
    void notifyChanged();

}
