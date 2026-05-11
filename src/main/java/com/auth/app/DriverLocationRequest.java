package com.auth.app;

import java.time.Instant;

public record DriverLocationRequest(
		String vehicleNumber,
		GeoLocation location,
		Instant capturedAt) {
}
