package dto.request;

public class ExitChatDto {

    String chatRoomId;

    String userId;

    public ExitChatDto(String message) {
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
