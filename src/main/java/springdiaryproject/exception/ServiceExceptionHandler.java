package springdiaryproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j(topic="SERVICE_EXCEPTION_HANDLER")
@ControllerAdvice
public class ServiceExceptionHandler {
    private String message;
    private int status;
    private List<FieldError> errors;

    @ExceptionHandler(CommentOverflowException.class)
    public ResponseEntity<String> commentOverflow(Long id) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글 수가 상한치에 도달하였습니다.");

    }

    @ExceptionHandler(NotFoundScheduleException.class)
    public ResponseEntity<String> notFoundSchedule() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾지 못했습니다.");
    }

    @ExceptionHandler(PasswordMisMatchException.class)
    public ResponseEntity<String> passwordMisMatch() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
    }
}
