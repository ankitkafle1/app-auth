package com.auth.app;

import java.time.Instant;

public record UserLocationResponse(
		String userId,
		String userName,
		String phoneNumber,
		double latitude,
		double longitude,
		int accuracyMeters,
		Instant capturedAt) {
}
