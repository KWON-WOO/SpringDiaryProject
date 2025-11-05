package springdiaryproject.repository;

import springdiaryproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import springdiaryproject.entity.Schedule;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countByScheduleId(Long id);
    List<Comment> findByScheduleOrderByModifiedAtDesc(Schedule schedule);
}
