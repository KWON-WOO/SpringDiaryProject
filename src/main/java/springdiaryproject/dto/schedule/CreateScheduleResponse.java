package springdiaryproject.dto.schedule;

import lombok.Getter;
import springdiaryproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id,
                                  String title,
                                  String content,
                                  String name,
                                  LocalDateTime createdAt,
                                  LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public CreateScheduleResponse(ScheduleDto dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.name = dto.getName();
        this.createdAt = dto.getCreatedAt();
        this.modifiedAt = dto.getModifiedAt();
    }
}
