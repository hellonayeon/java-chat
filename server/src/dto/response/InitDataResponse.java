package dto.response;

import domain.ChatRoom;
import domain.User;
import dto.type.DtoType;

import java.util.List;

public class InitDataResponse extends DTO {

    private List<ChatRoom> chatRooms;

    private List<User> users;


    public InitDataResponse(List<ChatRoom> chatRooms, List<User> users) {
        super(DtoType.LOGIN);

        this.chatRooms = chatRooms;
        this.users = users;
    }

    @Override
    public String toString() {
        String str = super.toString();

        if (chatRooms.size() != 0) {
            for (ChatRoom chatRoom : chatRooms) {
                str += chatRoom.getName() + "|";
            }
            str = str.substring(0, str.length()-1);

        }
        str += "+";


        for (User user : users) {
            str += user.getId() + "," + user.getName() + "|";
        }
        str = str.substring(0, str.length()-1);

        return str;
    }
}
