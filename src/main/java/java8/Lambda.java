package java8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description Lambda可定义为一种简洁、可传递的匿名函数，它是推动Java8发布的最重要新特性
 *              Lambda本质上是一个函数，虽然它不属于某个特定的类，但具备参数列表、函数主体、返回类型，甚至能够抛出异常
 *              Lambda是匿名的，它没有具体的函数名称
 *              Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中）
 *              Lambda可以使代码变的更加简洁
 *            基本语法
 *              参数列表->表达式
 *              参数列表->{表达式集合}
 *              需要注意的是lambda表达式默认隐含了return关键字，在单个表达式中，我们无需在写return关键字，也无需写花括号。
 *              只有当表达式是一个集合的时候，才需要写花括号及return关键字
 * @Date 2021/4/13 10:16
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */



/*
*
*  1. 实现Runnable线程案例
*    使用() -> {} 替代匿名类：
* */
public class Lambda implements Runnable {

    public static void main(String[] args) {
        Runnable runnable = new Lambda();
        Thread thread = new Thread(runnable);
        thread.start();

        Runnable runnable1= ()-> System.out.println("lambda 多线程表达式");
        Thread thread1 = new Thread(runnable1);
        thread1.start();
    }
    @Override
    public void run() {
        System.out.println("普通方式");
    }
}

/**
 * 2.实现事件处理
 * 如果你曾经做过Swing 编程，你将永远不会忘记编写事件侦听器代码。使用lambda表达式如下所示写出更好的事件侦听器的代码。
 */
class JButtonTest{
    public static void main(String[] args) {
        //before Java8
        JButton show =  new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("without lambda expression is boring");
            }
        });

        // Java 8 way:
        show.addActionListener((e) -> {
            System.out.println("Action !! Lambda expressions Rocks");
        });
    }
}

/**
 *
 * 3.使用Lambda表达式遍历List集合
 */
class ForEachList{
    public static void main(String[] args) {
        List list = Arrays.asList("Java","Scala","C++","Python","Docker");
        //java8遍历
        list.forEach(System.out::println);
    }
}

/**
 * 使用Lambda表达式和函数接口
 * 为了支持函数编程，Java 8加入了一个新的包java.util.function，其中有一个接口java.util.function.Predicate是支持Lambda函数编程
 */
class PredicateFunction{
    //Even better
    public static void filter(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name)))
                .forEach((name) -> {System.out.println(name + " ");
                });
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Scala","C++","Python","Docker");
        System.out.println("Language which starts with J");
        filter(list,str->str.startsWith("J"));
        System.out.println("Language which ends with a");
        filter(list,str->str.endsWith("a"));
        System.out.println("Language which all");
        filter(list,str->true);
        System.out.println("Print no language");
        filter(list,str->false);
        System.out.println("Language whose length greater than 5");
        filter(list,str->str.length()>=5);
    }
}

/**
 * 复杂的结合Predicate 使用
 * java.util.function.Predicate提供and(), or() 和 xor()可以进行逻辑操作，比如为了得到一串字符串中以"J"开头的4个长度
 */
class PredicateUseMore{
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Scala","C++","Python","Pyth","Docker");
        Predicate<String> startsWithJ = (str) -> str.startsWith("P");
        Predicate<String> fourLetterLong  = (n) -> n.length() >= 4;
        list.stream().filter(startsWithJ.and(fourLetterLong)).forEach(System.out::println);

    }
}

/**
 *
 *  Lambda实现Map和Reduce
 *
 */
class LambdaForMapAndReduce{

    public static void main(String[] args) {
        /**
         * 最流行的函数编程概念是map，它允许你改变你的对象，
         * 在这个案例中，我们将costBeforeTeax集合中每个元素改变了增加一定的数值，
         * 我们将Lambda表达式 x -> x*x传送map()方法，这将应用到stream中所有元素。
         * 然后我们使用 forEach() 打印出这个集合的元素.
         */
//        List<String> list = Arrays.asList("Java","Scala","C++","Python","Pyth","Docker");
//        list.stream().map(str->str.concat("-1"))
//                .forEach(System.out::println);

        /**
         * reduce() 是将集合中所有值结合进一个，Reduce类似SQL语句中的sum(), avg() 或count(),
         */

        // Applying 12% VAT on each purchase
        // Old way:
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println(total);
        //java8
        List<Integer> costBeforeTax_java8 = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax_java8.stream()
                                    .map(cost->cost+.12*cost)
                                    .reduce((i,j)-> {
                                        System.out.println("i"+i);
                                        System.out.println("j"+j);
                                        return i+j;
                                    })
                                    .ifPresent(System.out::println);
    }

}

/**
 * 通过filtering 创建一个字符串String的集合
 * Filtering是对大型Collection操作的一个通用操作，
 * Stream提供filter()方法，接受一个Predicate对象，意味着你能传送lambda表达式作为一个过滤逻辑进入这个方法：
 */
class FilteringUse{
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Scala","C++","Python","Pyth","Docker");
        list.stream().filter(x -> x.length() > 4)
                .collect(Collectors.toList()).forEach(s-> System.out.println(s));
    }
}

/**
 * 8.对集合中每个元素应用函数
 * 我们经常需要对集合中元素运用一定的功能，如表中的每个元素乘以或除以一个值等等.
 */
class ElementFunction{
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Scala","C++","Python","Pyth","Docker");
        list.stream().map(s->s.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
    }
}

/**
 * 9.通过复制不同的值创建一个子列表
 * 使用Stream的distinct()方法过滤集合中重复元素。
 */
class CopyToUse{
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Scala","C++","C++","Python","Pyth","Docker");
        list.stream().distinct().collect(Collectors.toList()).forEach(s-> System.out.println(s));
    }
}


/**
 * 计算List中的元素的最大值，最小值，总和及平均值
 */
class Calculated{
    public static void main(String[] args) {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics intSummaryStatistics = primes.stream().mapToInt(s -> s).summaryStatistics();
        System.out.println(intSummaryStatistics);
        System.out.println(intSummaryStatistics.getMax());
    }
}
