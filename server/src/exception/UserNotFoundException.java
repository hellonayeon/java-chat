package exception;

public class UserNotFoundException extends Exception {

    String userId;

    public UserNotFoundException(String userId) {
        this.userId = userId;
    }


    @Override
    public String getMessage() {
        return "user id = [" + userId + "] not found.";
    }
}
