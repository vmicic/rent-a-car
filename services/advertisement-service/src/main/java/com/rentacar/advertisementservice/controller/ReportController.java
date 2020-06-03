package com.rentacar.advertisementservice.controller;

import com.rentacar.advertisementservice.domain.dto.ReportDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reports")
public class ReportController {

	@PostMapping()
	public String createReport(@RequestBody ReportDTO newReport) {
		return "createReport endpoint invoked with parameters kmTraveled: " + newReport.getKmTraveled() +
				", comment: " + newReport.getComment() + ", reservationId: " + newReport.getReservationId();
	}
	
	@GetMapping("/{id}")
	public String getReport(@PathVariable Long id) {
		return "getReport endpoint invoked with id: " + id;
	}
	
	@GetMapping()
	public String getAllReports() {
		return "getAllReports endpoint invoked.";
	}
	
	@PutMapping("/{id}")
	public String updateReport(@PathVariable Long id, @RequestBody ReportDTO newReport) {
		return "updateReport endpoint invoked with parameters id: " + id + ", kmTraveled: " + newReport.getKmTraveled() +
				", comment: " + newReport.getComment() + ", reservationId: " + newReport.getReservationId();
	}
	
	@DeleteMapping("/{id}")
	public String deleteReport(@PathVariable Long id) {
		return "deleteReport endpoint invoked with id: " + id;
	}
	
	@GetMapping("/statistics")
	public String getStatistics() {
		return "getStatistics endpoint invoked.";
	}
}
