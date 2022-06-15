package thread;

public class ClientExitException extends Exception {
    @Override
    public String getMessage() {
        return "종료한 클라이언트 입니다.";
    }
}
