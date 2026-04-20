package com.Dayroom.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dayroom.project.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
	//특정 유저의 전체 일정 조회
	List<Schedule> findByUserIdOrderByDateAsc(Long userId);

}
