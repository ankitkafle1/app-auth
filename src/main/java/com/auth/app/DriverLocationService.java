package com.auth.app;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DriverLocationService {

	private final ConcurrentMap<String, DriverLocationResponse> locationsByVehicleNumber = new ConcurrentHashMap<>();
	private final ConcurrentMap<String, String> vehicleNumberByPhoneNumber = new ConcurrentHashMap<>();

	public DriverLocationResponse save(DriverLocationRequest request) {
		validate(request);
		DriverLocationResponse response = DriverLocationResponse.from(request);
		String vehicleNumber = normalize(request.vehicleNumber());
		locationsByVehicleNumber.put(vehicleNumber, response);
		return response;
	}

	public Optional<DriverLocationResponse> findLastKnownLocation(String vehicleNumber, String phoneNumber, Instant now) {
		DriverLocationResponse response = null;
		if (!isBlank(vehicleNumber)) {
			response = locationsByVehicleNumber.get(normalize(vehicleNumber));
		}
		if (response == null && !isBlank(phoneNumber)) {
			String mappedVehicleNumber = vehicleNumberByPhoneNumber.get(normalize(phoneNumber));
			response = mappedVehicleNumber == null ? null : locationsByVehicleNumber.get(mappedVehicleNumber);
		}
		return Optional.of(response);
	}

	private void validate(DriverLocationRequest request) {
		if (request == null) {
			throw badRequest("Request body is required");
		}
		if (isBlank(request.vehicleNumber())) {
			throw badRequest("vehicleNumber is required");
		}
		if (request.location() == null) {
			throw badRequest("location is required");
		}
		if (request.location().latitude() < -90 || request.location().latitude() > 90) {
			throw badRequest("location.latitude must be between -90 and 90");
		}
		if (request.location().longitude() < -180 || request.location().longitude() > 180) {
			throw badRequest("location.longitude must be between -180 and 180");
		}
		if (request.capturedAt() == null) {
			throw badRequest("capturedAt is required");
		}
	}

	private ResponseStatusException badRequest(String message) {
		return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}

private String normalize(String value) {
		return value.trim().toUpperCase();
	}
}
