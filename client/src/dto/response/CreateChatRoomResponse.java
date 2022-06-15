package dto.response;

public class CreateChatRoomResponse {

    String name;

    public CreateChatRoomResponse(String message) {
        this.name = message;
    }

    public String getName() {
        return name;
    }
}
