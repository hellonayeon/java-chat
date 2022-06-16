package dto.request;

public class ProgramExitRequest {

    String userId;

    public ProgramExitRequest(String message) {
        userId = message;
    }

    public String getUserId() {
        return userId;
    }
}
