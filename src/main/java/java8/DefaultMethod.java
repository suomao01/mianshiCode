package java8;
/*
 * @Description ava8中允许接口中包含具有具体实现的方法，这种方法被称为“默认方法”，使用default关键字修饰$
 * @Date 2021/4/19$ 17:27$
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
public interface DefaultMethod {

    String notDefault();

    default String defaultInterface(){
        return "I am default Method";
    };

    DefaultMethod d = new DefaultMethod() {
        @Override
        public String notDefault() {
            return "我不是一个default方法，实现类需要重写这个方法";
        }
    };
    public static void main(String[] args) {
        System.out.println(d.defaultInterface());
        System.out.println(d.notDefault());
    }
}

