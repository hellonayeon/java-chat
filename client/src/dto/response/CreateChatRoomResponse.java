package dto.response;

public class CreateChatRoomResponse {

    String id;

    String name;

    public CreateChatRoomResponse(String message) {
        String[] value = message.split(",");
        this.id = value[0];
        this.name = value[1];
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
