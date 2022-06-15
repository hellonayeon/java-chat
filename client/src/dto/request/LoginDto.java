package dto.request;

import dto.type.MessageType;

public class LoginDto extends DTO {

    String id;

    String name;

    public LoginDto(String id, String name) {
        super(MessageType.LOGIN);

        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + id + "," + name;
    }
}
