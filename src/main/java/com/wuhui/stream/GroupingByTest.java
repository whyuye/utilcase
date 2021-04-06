package com.wuhui.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingByTest {

    public static void main(String[] args) {
        long curTime = System.currentTimeMillis();
        User user1 = new User(curTime, "user1", 10);
        User user2 = new User(curTime, "user1", 10);
        User user3 = new User(curTime+100, "user2", 18);
        User user4 = new User(curTime+100, "user2", 18);
        User user5 = new User(curTime+50, "user2", 20);

        List<User> users = Lists.newArrayList(user1, user2, user3, user4, user5);

        Map<User, List<User>> user2UserListMap = users.stream().collect(Collectors.groupingBy(Function.identity()));
        System.out.println(user2UserListMap.size());
        System.out.println(user2UserListMap);
    }
}

@Data
@AllArgsConstructor
class User {

    private Long timeExpired;

    private String name;

    private Integer age;
}

@Data
class UserDTO {

    private Long timeExpired;

    private String name;

    private Integer age;
}