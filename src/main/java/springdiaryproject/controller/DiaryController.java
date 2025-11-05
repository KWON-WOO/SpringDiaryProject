package springdiaryproject.controller;

import springdiaryproject.dto.CreateScheduleRequest;
import springdiaryproject.dto.CreateScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springdiaryproject.service.DiaryService;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse savedSchedule = diaryService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }
}
