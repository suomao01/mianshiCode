package springBeanLCA.service.impl;

import springBeanLCA.service.OrderService;

/**
 * @Description 订单实现类
 * @Date 2021/5/28 13:56
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder() {
        System.out.println("添加订单成功");
    }
}
