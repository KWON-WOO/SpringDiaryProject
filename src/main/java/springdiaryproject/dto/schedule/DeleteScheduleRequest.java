package springdiaryproject.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleRequest {
    @NotBlank(message= "비밀번호를 입력해주세요")
    private String password;
}
