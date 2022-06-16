package exception;

public class ChatRoomNotFoundException extends Exception {

    String chatRoomName;

    public ChatRoomNotFoundException(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    @Override
    public String getMessage() {
        return "chat room = [" + chatRoomName + "] is not found.";
    }
}
