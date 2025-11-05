package springdiaryproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String content;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String password;
    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    public Comment(String content, String name, String password){
        this.content = content;
        this.name = name;
        this.password = password;
    }
    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
    }
}
