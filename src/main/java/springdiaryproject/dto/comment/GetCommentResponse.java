package springdiaryproject.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long id;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public GetCommentResponse(CommentDto dto) {
        this.id = dto.getId();
        this.content = dto.getContent();
        this.name = dto.getName();
        this.createdAt = dto.getCreatedAt();
        this.modifiedAt = dto.getModifiedAt();
    }
}
