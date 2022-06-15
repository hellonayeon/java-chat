package dto.request;

public class ExitChatRequest {

    String chatRoomName;

    String userId;

    public ExitChatRequest(String message) {
        String[] value = message.split(",");
        chatRoomName = value[0];
        userId = value[1];
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public String getUserId() {
        return userId;
    }
}
