package springdiaryproject.dto.comment;

import lombok.Getter;
import springdiaryproject.entity.Comment;
import springdiaryproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private Long id;
    private String content;
    private String name;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Schedule schedule;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.name = comment.getName();
        this.password = comment.getPassword();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.schedule = comment.getSchedule();
    }
}
