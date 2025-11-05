package springdiaryproject.controller;

import org.springframework.web.bind.annotation.*;
import springdiaryproject.dto.CreateScheduleRequest;
import springdiaryproject.dto.CreateScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import springdiaryproject.dto.GetUserScheduleResponse;
import springdiaryproject.service.DiaryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    /** ResponseEntity.status(HttpStatus.CRUD).body(response)*/
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse savedSchedule = diaryService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetUserScheduleResponse>> printUserSchedule(@RequestParam String name){
        List<GetUserScheduleResponse> result = diaryService.getUserSchedules(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
