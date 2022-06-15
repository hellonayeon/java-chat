package dto.response;

import domain.User;
import dto.type.MessageType;

import java.util.List;

public class UserListDto extends DTO {

    List<User> users;

    public UserListDto(List<User> users) {
        super(MessageType.USER_LIST);

        this.users = users;
    }

    @Override
    public String toString() {
        String str = super.toString();
        for (User user : users) {
            str += user.getId() + "," + user.getName() + "|";

        }

        return str.substring(0, str.length() - 1);
    }
}
