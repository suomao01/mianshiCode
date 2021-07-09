package 集合;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class list {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i+"k");
        }
//        list.add("2k");
        //for i
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).equals("2k")){
//                System.out.println("remove");
//                list.remove(i);
//            }
//            System.out.println(list.get(i));
//        }
//
        //迭代器
        Iterator<String> iterator =list.iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals("2k")){
                iterator.remove();
            }
//            System.out.println(iterator.next());
        }
        System.out.println(list);

        //for each
//        for (String str:list) {
//            System.out.println(str);
//            list.remove(str);
//            System.out.println(str);
//        }
    }
}
