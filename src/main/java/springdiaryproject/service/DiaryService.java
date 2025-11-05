package springdiaryproject.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import springdiaryproject.dto.*;
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
    public List<GetScheduleResponse> getUserSchedules(String userName){
        List<Schedule> schedules = userName == null?
                        scheduleRepository.findAllByOrderByModifiedAtDesc():scheduleRepository.findByName(userName);

        List<GetScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule: schedules) {
            dtos.add(new GetScheduleResponse(
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

    @Transactional
    public GetScheduleResponse getScheduleById(Long id) {
        Schedule schedule = getSchedule(id);

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
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
                schedule.getId(),
                schedule.getTitle(),
                schedule.getName(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long id, DeleteScheduleRequest password){
        Schedule schedule = getSchedule(id);

        if (passwordTask(password.getPassword(), schedule.getPassword()))
            scheduleRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "패스워드가 일치하지 않음");
    }


    public boolean passwordTask(String str1, String str2){
        if (!str1.equals(str2)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "패스워드가 일치하지 않음");
        return true;
    }

    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 ID")
        );
    }
}
