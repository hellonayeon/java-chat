package dto.request;

public class CreateChatRoomDto {

    String name;

    String userId;

    public CreateChatRoomDto(String message) {
        String[] value = message.split(",");

        this.name = value[0];
        this.userId = value[1];
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
