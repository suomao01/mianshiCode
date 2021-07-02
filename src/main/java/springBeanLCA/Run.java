package springBeanLCA;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springBeanLCA.service.OrderService;
import springBeanLCA.service.impl.OrderServiceImpl;

public class Run {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("application.xml");
        OrderServiceImpl orderService = (OrderServiceImpl) applicationContext.getBean("orderService");
        orderService.addOrder();
    }
}
