package dto.request;

import dto.type.DtoType;

public class LoginRequest extends DTO {

    String id;

    String name;

    public LoginRequest(String id, String name) {
        super(DtoType.LOGIN);

        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + id + "," + name;
    }
}
