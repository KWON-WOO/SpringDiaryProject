package springdiaryproject.service;

import springdiaryproject.dto.CreateScheduleRequest;
import springdiaryproject.dto.CreateScheduleResponse;
import springdiaryproject.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springdiaryproject.repository.ReplyRepository;
import springdiaryproject.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final ScheduleRepository scheduleRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );

    }
}
