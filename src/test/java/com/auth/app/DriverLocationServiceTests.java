package com.auth.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

class DriverLocationServiceTests {

	private final DriverLocationService service = new DriverLocationService();

	@Test
	void storesAndFindsLastKnownLocationByVehicleNumber() {
		DriverLocationRequest request = locationRequest();

		service.save(request);

		assertThat(service.findLastKnownLocation("BA 2 KHA 1234", null, Instant.parse("2026-05-11T16:00:00Z")))
				.contains(DriverLocationResponse.from(request));
	}

	@Test
	void rejectsInvalidLatitude() {
		DriverLocationRequest request = new DriverLocationRequest(
				"BA 2 KHA 1234",
				new GeoLocation(127.7181, 85.3254),
				Instant.parse("2026-05-11T15:43:05Z"));

		assertThatThrownBy(() -> service.save(request))
				.isInstanceOf(ResponseStatusException.class)
				.hasMessageContaining("location.latitude");
	}

	private DriverLocationRequest locationRequest() {
		return new DriverLocationRequest(
				"BA 2 KHA 1234",
				new GeoLocation(27.7181, 85.3254),
				Instant.parse("2026-05-11T15:43:05Z"));
	}
}
