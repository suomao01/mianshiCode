package java8;/*
 * @Description $
 * @Date 2021/4/9$ 17:44$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */

import java.util.Arrays;

public class StreamDemo{
    public static void main(String[] args) {
        Arrays.asList(1,3,2,5)
                .stream()
                .filter(i->i/2==1)
                .forEach(System.out::println);
    }
}
