package com.Dayroom.project.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.Dayroom.project.dto.ScheduleRequestDTO;
import com.Dayroom.project.dto.ScheduleResponseDTO;
import com.Dayroom.project.service.ScheduleService;
import jakarta.validation.Valid;       // ← @Valid
import lombok.RequiredArgsConstructor; // ← @RequiredArgsConstructor

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;
	
	@PostMapping
	public ResponseEntity<ScheduleResponseDTO> createSchedule(
			@AuthenticationPrincipal UserDetails userDetails,
			@Valid @RequestBody ScheduleRequestDTO request) {
		ScheduleResponseDTO response = scheduleService.createSchedule(
			userDetails.getUsername(), request);
		return ResponseEntity.ok(response);
	}
	@GetMapping
	public ResponseEntity<List<ScheduleResponseDTO>> getMySchedules(
			@AuthenticationPrincipal UserDetails userDetails) {
		List<ScheduleResponseDTO> response = scheduleService.getMySchedules(
				userDetails.getUsername());
		return ResponseEntity.ok(response);
	}
	@PutMapping("/{scheduleId}")
	public ResponseEntity<ScheduleResponseDTO> updateSchedule(
			@AuthenticationPrincipal UserDetails userDetails,
			@PathVariable Long scheduleId,
			@Valid @RequestBody ScheduleRequestDTO request) {
		ScheduleResponseDTO response = scheduleService.updateSchedule(
			userDetails.getUsername(), scheduleId, request);
		return ResponseEntity.ok(response);
	}
	@DeleteMapping("/{scheduleId}")
	public ResponseEntity<Void> deleteSchedule(
	        @AuthenticationPrincipal UserDetails userDetails,
	        @PathVariable Long scheduleId) {
	    scheduleService.deleteSchedule(userDetails.getUsername(), scheduleId);
	    return ResponseEntity.noContent().build();
	}
}
