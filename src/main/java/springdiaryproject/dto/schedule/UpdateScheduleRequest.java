package springdiaryproject.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    @Size(max=30)
    @NotBlank
    private String title;
    @NotBlank(message= "아이디를 입력해주세요")
    private String name;
    @NotBlank(message= "비밀번호를 입력해주세요")
    private String password;
}
