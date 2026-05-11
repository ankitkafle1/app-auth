package com.auth.app;

import java.time.Instant;

public record DriverLocationResponse(
		String vehicleNumber,
		GeoLocation location,
		Instant capturedAt) {

	public static DriverLocationResponse from(DriverLocationRequest request) {
		return new DriverLocationResponse(
				request.vehicleNumber(),
				request.location(),
				request.capturedAt());	}
}
