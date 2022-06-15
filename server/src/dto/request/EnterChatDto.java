package dto.request;

public class EnterChatDto {

    String chatRoomId;

    String userId;

    public EnterChatDto(String message) {
        String[] value = message.split(",");
        chatRoomId = value[0];
        userId = value[1];
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public String getUserId() {
        return userId;
    }
}
