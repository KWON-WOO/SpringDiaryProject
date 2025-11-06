package springdiaryproject.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    @Size(max=30)
    private String title;
    @Size(max=200)
    private String content;
    @NotBlank(message= "아이디를 입력해주세요")
    private String name;
    @NotBlank(message= "비밀번호를 입력해주세요")
    private String password;
}
