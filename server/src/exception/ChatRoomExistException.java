package exception;

public class ChatRoomExistException extends Exception {

    String chatRoomName;

    public ChatRoomExistException(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    @Override
    public String getMessage() {
        return "chat room = [" + chatRoomName + "] exist.";
    }
}
