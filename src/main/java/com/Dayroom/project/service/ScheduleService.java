package com.Dayroom.project.service;

import java.util.List;

import com.Dayroom.project.dto.ScheduleRequestDTO;
import com.Dayroom.project.dto.ScheduleResponseDTO;

public interface ScheduleService {
	
	ScheduleResponseDTO createSchedule(String username, ScheduleRequestDTO request);
	
	List<ScheduleResponseDTO> getMySchedules(String username);
	
	ScheduleResponseDTO updateSchedule(String username, Long scheduleId, ScheduleRequestDTO request);
	
	void deleteSchedule(String username,Long scheduleId);
}
