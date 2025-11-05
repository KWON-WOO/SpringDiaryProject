package springdiaryproject.controller;

import org.springframework.web.bind.annotation.*;
import springdiaryproject.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<GetScheduleResponse>> printUserSchedule(@RequestParam(required=false) String name){
        List<GetScheduleResponse> result = diaryService.getUserSchedules(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleResponse> printScheduleById(@PathVariable Long id) {
        GetScheduleResponse result = diaryService.getScheduleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PatchMapping("/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequest request) {
        UpdateScheduleResponse result = diaryService.updateSchedule(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleRequest password) {
        diaryService.deleteSchedule(id, password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
