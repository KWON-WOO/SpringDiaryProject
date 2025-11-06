package springdiaryproject.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import springdiaryproject.dto.comment.CommentDto;
import springdiaryproject.dto.comment.CreateCommentRequest;
import springdiaryproject.dto.comment.CreateCommentResponse;
import springdiaryproject.dto.comment.GetCommentResponse;
import springdiaryproject.dto.schedule.*;
import springdiaryproject.exception.CommentOverflowException;
import springdiaryproject.exception.NotFoundScheduleException;
import springdiaryproject.exception.PasswordMisMatchException;
import springdiaryproject.exception.ServiceExceptionHandler;
import springdiaryproject.entity.Comment;
import springdiaryproject.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springdiaryproject.repository.CommentRepository;
import springdiaryproject.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private ServiceExceptionHandler exception = new ServiceExceptionHandler();

    @Transactional
    public CreateScheduleResponse saveSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(new ScheduleDto(request));

        ScheduleDto dto = new ScheduleDto(scheduleRepository.save(schedule));

        return new CreateScheduleResponse(dto);
    }

    @Transactional
    public List<GetScheduleResponse> getUserSchedules(String userName){
        List<Schedule> schedules = (userName == null)?
                        scheduleRepository.findAllByOrderByModifiedAtDesc(): scheduleRepository.findByNameOrderByModifiedAtDesc(userName);

        List<GetScheduleResponse> dtos = new ArrayList<>();

        schedules.stream().forEach(schedule -> dtos.add(new GetScheduleResponse(new ScheduleDto(schedule))));

        return dtos;
    }

    @Transactional
    public GetScheduleResponse getScheduleById(Long id) {
        Schedule schedule = getSchedule(id);
        List<Comment> comments = commentRepository.findByScheduleOrderByModifiedAtDesc(getSchedule(id));
        List<GetCommentResponse> dtos = new ArrayList<>();

        comments.stream().forEach(comment -> dtos.add(new GetCommentResponse(new CommentDto(comment))));

        return new GetScheduleResponse(new ScheduleDto(schedule, dtos));
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long id, UpdateScheduleRequest request) {
        boolean passwordFlag;
        Schedule schedule = getSchedule(id);

        passwordFlag = passwordTask(request.getPassword(), schedule.getPassword());

        if (passwordFlag) {
            schedule.setTitle(request.getTitle());
            schedule.setName(request.getName());
        } else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "패스워드가 일치하지 않음");

        return new UpdateScheduleResponse(
                schedule.getId(), schedule.getTitle(),
                schedule.getName(), schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long id, DeleteScheduleRequest pwd){
        Schedule schedule = getSchedule(id);

        if (passwordTask(pwd.getPassword(), schedule.getPassword()))
            scheduleRepository.deleteById(id);
    }

    @Transactional
    public CreateCommentResponse saveComment(Long id, CreateCommentRequest request) {
         if (commentRepository.countByScheduleId(id) >= 10) throw new CommentOverflowException();

        Comment comment = new Comment(request.getContent(), request.getName(), request.getPassword());

        comment.setSchedule(getSchedule(id));

        CommentDto dto = new CommentDto(commentRepository.save(comment));

        return new CreateCommentResponse(dto);
    }

    public boolean passwordTask(String str1, String str2){
        if (!str1.equals(str2)) throw new PasswordMisMatchException();
        return true;
    }

    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new NotFoundScheduleException()
        );
    }
}
