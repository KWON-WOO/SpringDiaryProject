package springdiaryproject.exception;

public class CommentOverflowException extends RuntimeException{
    public CommentOverflowException() {
        super("최대 댓글 수에 도달했습니다.");
    }
}
