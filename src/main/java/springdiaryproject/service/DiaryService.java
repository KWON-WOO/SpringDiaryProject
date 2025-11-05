package springdiaryproject.service;

import springdiaryproject.dto.CreateScheduleRequest;
import springdiaryproject.dto.CreateScheduleResponse;
import springdiaryproject.dto.GetUserScheduleResponse;
import springdiaryproject.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springdiaryproject.repository.ReplyRepository;
import springdiaryproject.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public List<GetUserScheduleResponse> getUserSchedules(String userName){
        List<Schedule> schedules = scheduleRepository.findByName(userName);
        List<GetUserScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule: schedules) {
            dtos.add(new GetUserScheduleResponse(
               schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            ));
        }
        return dtos;
    }
}
