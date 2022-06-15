package dto.request;

import dto.type.MessageType;

public abstract class DTO {
    MessageType type;

    public DTO(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + ":";
    }
}
