package 集合;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class list {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i+"k");
        }
        System.out.println(list.size());
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("2k")){
                System.out.println("remove");
                list.remove(i);
            }
            System.out.println(list.get(i));
        }
        Iterator<String> iterator =list.iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals("3k")){
                iterator.remove();
            }
            System.out.println(iterator.next());
        }
        System.out.println(list);
    }
}
