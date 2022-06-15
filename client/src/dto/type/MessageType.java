package dto.type;

public enum MessageType {
    INIT,

    // [request] client -> server
    LOGIN,
    CREATE_CHAT, CREATE_CHAT_SUCCESS,
    ENTER_CHAT, EXIT_CHAT,
    SEND_MESSAGE,

    // [response] server -> client
    USER_LIST, CHAT_LIST
}
