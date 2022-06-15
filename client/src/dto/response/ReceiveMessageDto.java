package dto.response;

public class ReceiveMessageDto {

    private String chatRoomId;

    private String userId;

    private String message;

    public ReceiveMessageDto(String message) {
        String[] value = message.split(",");
        this.chatRoomId = value[0];
        this.userId = value[1];
        this.message = value[2];
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
