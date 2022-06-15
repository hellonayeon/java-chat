package dto.request;

public class LoginRequest {

    String id;

    String name;

    public LoginRequest(String message) {
        String[] value = message.split(",");
        id = value[0];
        name = value[1];
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
