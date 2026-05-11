package com.auth.app;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/driver-locations")
public class DriverLocationController {

	private final DriverLocationService driverLocationService;

	public DriverLocationController(DriverLocationService driverLocationService) {
		this.driverLocationService = driverLocationService;
	}

	@PostMapping
	public ResponseEntity<DriverLocationResponse> updateLocation(@RequestBody DriverLocationRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(driverLocationService.save(request));
	}

	@GetMapping("/last-known")
	public DriverLocationResponse getLastKnownLocation(
			@RequestParam(required = false) String vehicleNumber,
			@RequestParam(required = false) String phoneNumber) {

		if (isBlank(vehicleNumber) && isBlank(phoneNumber)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "vehicleNumber or phoneNumber is required");
		}

		return driverLocationService.findLastKnownLocation(vehicleNumber, phoneNumber, Instant.now())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver location not found"));
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
