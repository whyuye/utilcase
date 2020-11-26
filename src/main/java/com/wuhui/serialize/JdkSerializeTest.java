package com.wuhui.serialize;

import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class JdkSerializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User("username", 18, "username_18");
        System.out.println(User.Fields.name_age);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(user);

        System.out.println(bos.toByteArray().length);
        System.out.println(bos.toString().getBytes(StandardCharsets.UTF_8).length);
//        final byte[] bytes = bos.toByteArray();
//        final byte[] bytes1 = Arrays.copyOf(bytes, bytes.length - 1);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis); // 需要对应的序列化数据实现序列化接口
        User oldUser = (User) ois.readObject();
        System.out.println(oldUser);
    }
}

@Value
@FieldNameConstants
class User implements Serializable {

    private String name;

    private Integer age;

    private String name_age;
}
