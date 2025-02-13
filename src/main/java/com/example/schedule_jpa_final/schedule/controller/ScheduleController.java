package com.example.schedule_jpa_final.schedule.controller;

import com.example.schedule_jpa_final.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.schedule_jpa_final.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedule_jpa_final.schedule.dto.response.ScheduleResponseDto;
import com.example.schedule_jpa_final.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedule_jpa_final.schedule.dto.response.ScheduleUpdateResponseDto;
import com.example.schedule_jpa_final.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> save(
            @RequestBody ScheduleSaveRequestDto dto
    ) {
        return ResponseEntity.ok(scheduleService.save(dto));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleUpdateResponseDto> update(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto dto
            ) {
        return ResponseEntity.ok(scheduleService.update(id,dto));
    }

    @DeleteMapping("/schedules/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.deleteById(id);
    }
}
