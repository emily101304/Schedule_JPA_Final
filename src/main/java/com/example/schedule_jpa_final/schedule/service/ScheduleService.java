package com.example.schedule_jpa_final.schedule.service;

import com.example.schedule_jpa_final.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.schedule_jpa_final.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedule_jpa_final.schedule.dto.response.ScheduleResponseDto;
import com.example.schedule_jpa_final.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedule_jpa_final.schedule.dto.response.ScheduleUpdateResponseDto;
import com.example.schedule_jpa_final.schedule.entity.Schedule;
import com.example.schedule_jpa_final.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto save(ScheduleSaveRequestDto dto) {

        Schedule schedule = new Schedule(
                dto.getUsername(),
                dto.getTitle(),
                dto.getContent()
        );

        scheduleRepository.save(schedule);

        return new ScheduleSaveResponseDto(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream().
                map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getUserName(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다!")
        );

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponseDto update(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다!")
        );

        schedule.update(
                dto.getTitle(),
                dto.getContent()
        );

        scheduleRepository.save(schedule);

        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
