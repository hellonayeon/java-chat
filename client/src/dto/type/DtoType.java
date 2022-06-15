package dto.type;

public enum DtoType {
    INIT,

    // [request] client -> server
    LOGIN,
    CREATE_CHAT,
    ENTER_CHAT, EXIT_CHAT,
    MESSAGE,

    // [response] server -> client
    USER_LIST, CHAT_LIST
}
