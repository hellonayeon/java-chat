package dto.response;

import domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserListDto {

    List<User> users;

    public UserListDto(String message) {
        users = new ArrayList<>();

        String[] userValues = message.split("\\|");
        for(String uValue : userValues) {
            String[] value = uValue.split(",");
            users.add(new User(value[0], value[1]));
        }

    }

    public List<User> getUsers() {
        return users;
    }
}
