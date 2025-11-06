package springdiaryproject.exception;

public class PasswordMisMatchException extends RuntimeException{
    public PasswordMisMatchException() {
        super("패스워드가 일치하지 않습니다");
    }
}
