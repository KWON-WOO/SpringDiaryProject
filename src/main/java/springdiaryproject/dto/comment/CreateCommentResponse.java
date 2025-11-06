package springdiaryproject.dto.comment;

import lombok.Getter;
import springdiaryproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Schedule schedule;

    public CreateCommentResponse(Schedule schedule, Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.schedule = schedule;
        this.id = id;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public CreateCommentResponse(CommentDto dto) {
        this.schedule = dto.getSchedule();
        this.id = dto.getId();
        this.content = dto.getContent();
        this.name = dto.getName();
        this.createdAt = dto.getCreatedAt();
        this.modifiedAt = dto.getModifiedAt();
    }
}
