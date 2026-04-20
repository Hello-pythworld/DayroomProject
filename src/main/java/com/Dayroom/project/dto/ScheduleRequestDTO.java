package com.Dayroom.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDTO {

	private String title;
	private String description;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
}
