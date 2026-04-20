package com.Dayroom.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dayroom.project.dto.ScheduleRequestDTO;
import com.Dayroom.project.dto.ScheduleResponseDTO;
import com.Dayroom.project.entity.Schedule;
import com.Dayroom.project.entity.User;
import com.Dayroom.project.repository.ScheduleRepository;
import com.Dayroom.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;
	
	// 일정 생성
	@Override
	@Transactional
	public ScheduleResponseDTO createSchedule(String username, ScheduleRequestDTO request) {
		User user = getUser(username);
		
		Schedule schedule = Schedule.builder()
				.user(user)
				.title(request.getTitle())
				.description(request.getDescription())
				.date(request.getDate())
				.startTime(request.getStartTime())
				.endTime(request.getEndTime())
				.build();
		
		Schedule saved = scheduleRepository.save(schedule);
		return ScheduleResponseDTO.from(saved);
	}
	@Override
	@Transactional(readOnly = true)
	public List<ScheduleResponseDTO> getMySchedules(String username) {
		User user = getUser(username);
		
		return scheduleRepository.findByUserIdOrderByDateAsc(user.getId())
				.stream()
				.map(ScheduleResponseDTO::from)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public ScheduleResponseDTO updateSchedule(String username, Long scheduleId, ScheduleRequestDTO request) {
		Schedule schedule = getScheduleWithOwnerCheck(username, scheduleId);
		schedule.setTitle(request.getTitle());
	
		schedule.setDescription(request.getDescription());
		schedule.setDate(request.getDate());
		schedule.setStartTime(request.getStartTime());
		schedule.setEndTime(request.getEndTime());
		scheduleRepository.save(schedule);		
		return ScheduleResponseDTO.from(schedule);
	}
	// ✅ 클래스 하단에 반드시 추가
	private User getUser(String username) {
	    return userRepository.findByUsername(username)
	        .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
	}

	private Schedule getScheduleWithOwnerCheck(String username, Long scheduleId) {
	    Schedule schedule = scheduleRepository.findById(scheduleId)
	        .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
	    if (!schedule.getUser().getUsername().equals(username)) {
	        throw new RuntimeException("권한이 없습니다.");
	    }
	    return schedule;
	}
	@Override
	@Transactional
	public void deleteSchedule(String username, Long scheduleId) {
	    Schedule schedule = getScheduleWithOwnerCheck(username, scheduleId);
	    scheduleRepository.delete(schedule);
	}
}
