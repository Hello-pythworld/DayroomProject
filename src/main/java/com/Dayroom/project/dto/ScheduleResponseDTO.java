package com.Dayroom.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.Dayroom.project.entity.Schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDTO {

	private Long id;
	private String title;
	private String description;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public static ScheduleResponseDTO from(Schedule schedule) {
		return ScheduleResponseDTO.builder()
				.id(schedule.getId())
				.title(schedule.getTitle())
				.description(schedule.getDescription())
				.date(schedule.getDate())
				.startTime(schedule.getStartTime())
				.endTime(schedule.getEndTime())
				.build();
	}
}
