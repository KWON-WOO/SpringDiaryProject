package springdiaryproject.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @Size(max=100)
    @NotBlank(message="내용을 입력해주세요")
    private String content;
    @NotBlank(message= "아이디를 입력해주세요")
    private String name;
    @NotBlank(message= "비밀번호를 입력해주세요")
    private String password;
}
