package springdiaryproject.dto.schedule;

import lombok.Getter;
import springdiaryproject.dto.comment.GetCommentResponse;
import springdiaryproject.entity.Comment;
import springdiaryproject.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleDto {
    private Long id;
    private String title;
    private String content;
    private String name;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<GetCommentResponse> comments;

    public ScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.name = schedule.getName();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.comments = null;
    }
    public ScheduleDto(Schedule schedule, List<GetCommentResponse> dtos) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.name = schedule.getName();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.comments = dtos;
    }
    public ScheduleDto(CreateScheduleRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.name = request.getName();
        this.password = request.getPassword();
    }
}
