package com.example.schedule_jpa_final.schedule.repository;

import com.example.schedule_jpa_final.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
