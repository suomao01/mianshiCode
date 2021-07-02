package 序列化;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;

/**
 * @Description 序列化：把Java对象转换为字节序列。反序列化：把字节序列恢复为原先的Java对象。
 * @Date 2021/4/20 10:20
 * @Author VparkFC-Mr.Suo
 * @Since version-1.0
 */
public class JAVASerializable {

    public static void serialize() throws IOException {
        Student student = new Student();
        student.setName("CodeSheep");
        student.setAge( 18 );
        student.setSex( 1 );

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream( new FileOutputStream( new File("student.txt") ) );
        objectOutputStream.writeObject( student );
        objectOutputStream.close();

        System.out.println("序列化成功！已经生成student.txt文件");
        System.out.println("==============================================");
    }

    @SneakyThrows
    public static void main(String[] args) {
        serialize();
    }
}
@Data
class Student implements Serializable{
    private String name;
    private Integer age;
    private Integer sex;
}
