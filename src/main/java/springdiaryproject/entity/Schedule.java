package springdiaryproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springdiaryproject.dto.schedule.ScheduleDto;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Setter
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public Schedule(ScheduleDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.name = dto.getName();
        this.password = dto.getPassword();
    }
}
